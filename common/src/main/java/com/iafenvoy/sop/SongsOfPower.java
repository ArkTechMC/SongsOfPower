package com.iafenvoy.sop;

import com.iafenvoy.jupiter.ServerConfigManager;
import com.iafenvoy.jupiter.malilib.config.ConfigManager;
import com.iafenvoy.sop.config.SopConfig;
import com.iafenvoy.sop.power.PowerCategory;
import com.iafenvoy.sop.power.SongPowerData;
import com.iafenvoy.sop.registry.*;
import com.mojang.logging.LogUtils;
import dev.architectury.networking.NetworkManager;
import net.minecraft.entity.player.PlayerEntity;
import org.slf4j.Logger;

public class SongsOfPower {
    public static final String MOD_ID = "sop";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        ConfigManager.getInstance().registerConfigHandler(SopConfig.INSTANCE);
        ConfigManager.getInstance().registerServerConfig(SopConfig.INSTANCE, ServerConfigManager.PermissionChecker.IS_OPERATOR);

        SopBlocks.REGISTRY.register();
        SopBlockEntities.REGISTRY.register();
        SopEntities.REGISTRY.register();
        SopItems.REGISTRY.register();
        SopItemGroups.REGISTRY.register();
        SopParticles.REGISTRY.register();
        SopSounds.REGISTRY.register();
        SopCommands.init();
        SopGameRules.init();
        SopPowers.init();
    }

    public static void process(){
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, Static.KEYBINDING_SYNC, (buf, context) -> {
            PlayerEntity player = context.getPlayer();
            PowerCategory type = buf.readEnumConstant(PowerCategory.class);
            SongPowerData data = SongPowerData.byPlayer(player);
            if (!player.isSpectator() && data.isEnabled())
                context.queue(() -> data.get(type).keyPress());
        });
    }
}
