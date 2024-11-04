package com.iafenvoy.sop.render;

import com.iafenvoy.sop.SongsOfPower;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class RenderConstants {
    public static final Identifier WHITE_TEXTURE = new Identifier(SongsOfPower.MOD_ID, "textures/white.png");
}
