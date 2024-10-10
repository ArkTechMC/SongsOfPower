package com.iafenvoy.sop.world.sound;

import com.iafenvoy.sop.power.PowerCategory;
import com.iafenvoy.sop.registry.SopSounds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.AbstractSoundInstance;
import net.minecraft.client.sound.TickableSoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.LocalRandom;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class ClientSongCubeSoundManager implements SongCubeSoundManager {
    private static final Map<BlockPos, SongCubeSoundInstance> INSTANCES = new HashMap<>();
    private static final int MAX_DISTANCE = 10;

    @Override
    public boolean nearEnough(BlockPos pos) {
        assert MinecraftClient.getInstance().player != null;
        return MinecraftClient.getInstance().player.getBlockPos().getSquaredDistance(pos) < MAX_DISTANCE * MAX_DISTANCE;
    }

    @Override
    public void startPlaying(BlockPos pos, PowerCategory category) {
        if (category == null) return;
        if (!INSTANCES.containsKey(pos)) INSTANCES.put(pos, new SongCubeSoundInstance(pos, category));
        INSTANCES.get(pos).start();
    }

    @Override
    public void stopPlaying(BlockPos pos) {
        if (!INSTANCES.containsKey(pos)) return;
        INSTANCES.get(pos).stop();
    }

    @Override
    public void destroy(BlockPos pos) {
        SongCubeSoundInstance instance = INSTANCES.remove(pos);
        if (instance != null) instance.stop();
    }

    private static class SongCubeSoundInstance extends AbstractSoundInstance implements TickableSoundInstance {
        private static final Supplier<MinecraftClient> client = MinecraftClient::getInstance;
        private boolean playing;

        public SongCubeSoundInstance(BlockPos pos, PowerCategory category) {
            super(switch (category) {
                case AGGRESSIUM -> SopSounds.AGGRESSIUM.get();
                case MOBILIUM -> SopSounds.MOBILIUM.get();
                case PROTISIUM -> SopSounds.PROTISIUM.get();
                case SUPPORTIUM -> SopSounds.SUPPORTIUM.get();
            }, SoundCategory.BLOCKS, new LocalRandom(0));
            this.repeat = true;
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
            this.playing = false;
        }

        public void start() {
            if (!this.playing) {
                client.get().getSoundManager().play(this);
                this.playing = true;
            }
        }

        public void stop() {
            if (this.playing) {
                client.get().getSoundManager().stop(this);
                this.playing = false;
            }
        }

        @Override
        public boolean isDone() {
            return false;
        }

        @Override
        public void tick() {
            ClientPlayerEntity player = client.get().player;
            assert player != null;
            double distance = Math.sqrt(player.squaredDistanceTo(this.x, this.y, this.z));
            if (distance > MAX_DISTANCE) this.stop();
            else this.volume = (float) (1 - 0.9 * distance / MAX_DISTANCE);
        }
    }
}
