package com.iafenvoy.sop.fabric;

import com.iafenvoy.jupiter.render.screen.ConfigSelectScreen;
import com.iafenvoy.sop.config.SopConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.text.Text;

public class ModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new ConfigSelectScreen<>(Text.translatable("config.sop.config.title"), parent, SopConfig.INSTANCE, null);
    }
}
