package com.iafenvoy.sop.registry;

import com.iafenvoy.sop.SongsOfPower;
import com.iafenvoy.sop.item.ShrineDebugItem;
import com.iafenvoy.sop.item.impl.ProtepointShieldItem;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Supplier;

public final class SopItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(SongsOfPower.MOD_ID, RegistryKeys.ITEM);

    public static final RegistrySupplier<ShrineDebugItem> SHRINE_DEBUG = register("shrine_debug", ShrineDebugItem::new);
    //Fake Items, should not use in game without song power.
    public static final RegistrySupplier<Item> PROTEPOINT_SHIELD = register("protepoint_shield", ProtepointShieldItem::create);

    private static <T extends Item> RegistrySupplier<T> register(String id, Supplier<T> item) {
        return REGISTRY.register(id, item);
    }

    public static void init() {
        CreativeTabRegistry.append(SopItemGroups.MAIN, SHRINE_DEBUG);
    }
}
