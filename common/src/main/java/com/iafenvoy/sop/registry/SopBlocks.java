package com.iafenvoy.sop.registry;

import com.iafenvoy.sop.SongsOfPower;
import com.iafenvoy.sop.item.block.AggressiumSongCubeBlock;
import com.iafenvoy.sop.item.block.MobiliumSongCubeBlock;
import com.iafenvoy.sop.item.block.ProtisiumSongCubeBlock;
import com.iafenvoy.sop.item.block.SupportiumSongCubeBlock;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Rarity;

import java.util.function.Supplier;

public class SopBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(SongsOfPower.MOD_ID, RegistryKeys.BLOCK);
    //Block
    public static final RegistrySupplier<AggressiumSongCubeBlock> AGGRESSIUM_SONG = register("aggressium_song", AggressiumSongCubeBlock::new);
    public static final RegistrySupplier<MobiliumSongCubeBlock> MOBILIUM_SONG = register("mobilium_song", MobiliumSongCubeBlock::new);
    public static final RegistrySupplier<ProtisiumSongCubeBlock> PROTISIUM_SONG = register("protisium_song", ProtisiumSongCubeBlock::new);
    public static final RegistrySupplier<SupportiumSongCubeBlock> SUPPORTIUM_SONG = register("supportium_song", SupportiumSongCubeBlock::new);

    private static <T extends Block> RegistrySupplier<T> register(String id, Supplier<T> block) {
        RegistrySupplier<T> r = REGISTRY.register(id, block);
        SopItems.REGISTRY.register(id, () -> new BlockItem(r.get(), new Item.Settings().rarity(Rarity.EPIC).maxCount(1).arch$tab(SopItemGroups.MAIN)));
        return r;
    }
}
