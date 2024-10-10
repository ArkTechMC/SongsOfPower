package com.iafenvoy.sop;

import com.iafenvoy.sop.world.sound.ServerSongCubeSoundManager;
import com.iafenvoy.sop.world.sound.SongCubeSoundManager;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class Static {
    public static final Identifier KEYBINDING_SYNC = new Identifier(SongsOfPower.MOD_ID, "keybinding_sync");
    public static final Identifier SONG_CUBE_DATA_SYNC = new Identifier(SongsOfPower.MOD_ID, "song_cube_data_sync");
    public static final UUID PROTESPHERE_UUID = UUID.fromString("babda2da-13e9-4096-baba-933bb695e319");
    public static final UUID MOBILIGLIDE_UUID = UUID.fromString("3db28b1d-3fec-4a31-9f50-6c04f30e33f1");
    public static SongCubeSoundManager songCubeSoundManager = new ServerSongCubeSoundManager();
    //Forge Only
    public static final Identifier CAPABILITY_SYNC = new Identifier(SongsOfPower.MOD_ID, "capability_sync");
}
