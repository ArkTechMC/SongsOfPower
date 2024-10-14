package com.iafenvoy.sop.registry;

import com.iafenvoy.sop.SongsOfPower;
import com.iafenvoy.sop.item.MobilibouncePlatformBlockItem;
import com.iafenvoy.sop.item.block.AggressiumSongCubeBlock;
import com.iafenvoy.sop.item.block.MobiliumSongCubeBlock;
import com.iafenvoy.sop.item.block.ProtisiumSongCubeBlock;
import com.iafenvoy.sop.item.block.SupportiumSongCubeBlock;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GlassBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Rarity;

import java.util.function.Function;
import java.util.function.Supplier;

public final class SopBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(SongsOfPower.MOD_ID, RegistryKeys.BLOCK);
    //Block
    public static final RegistrySupplier<AggressiumSongCubeBlock> AGGRESSIUM_SONG = register("aggressium_song", AggressiumSongCubeBlock::new, block -> new BlockItem(block, new Item.Settings().rarity(Rarity.EPIC).maxCount(1)));
    public static final RegistrySupplier<MobiliumSongCubeBlock> MOBILIUM_SONG = register("mobilium_song", MobiliumSongCubeBlock::new, block -> new BlockItem(block, new Item.Settings().rarity(Rarity.EPIC).maxCount(1)));
    public static final RegistrySupplier<ProtisiumSongCubeBlock> PROTISIUM_SONG = register("protisium_song", ProtisiumSongCubeBlock::new, block -> new BlockItem(block, new Item.Settings().rarity(Rarity.EPIC).maxCount(1)));
    public static final RegistrySupplier<SupportiumSongCubeBlock> SUPPORTIUM_SONG = register("supportium_song", SupportiumSongCubeBlock::new, block -> new BlockItem(block, new Item.Settings().rarity(Rarity.EPIC).maxCount(1)));
    //Fake Blocks, should not use in game without song power.
    public static final RegistrySupplier<GlassBlock> MOBILIBOUNCE_PLATFORM = register("mobilibounce_platform", () -> new GlassBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK).dropsNothing().nonOpaque().emissiveLighting((state, world, pos) -> true).luminance(state -> 15).jumpVelocityMultiplier(3)), MobilibouncePlatformBlockItem::new);

    private static <T extends Block> RegistrySupplier<T> register(String id, Supplier<T> block, Function<Block, Item> itemBuilder) {
        RegistrySupplier<T> r = REGISTRY.register(id, block);
        SopItems.REGISTRY.register(id, () -> itemBuilder.apply(r.get()));
        return r;
    }

    public static void init() {
        CreativeTabRegistry.append(SopItemGroups.MAIN, AGGRESSIUM_SONG);
        CreativeTabRegistry.append(SopItemGroups.MAIN, MOBILIUM_SONG);
        CreativeTabRegistry.append(SopItemGroups.MAIN, PROTISIUM_SONG);
        CreativeTabRegistry.append(SopItemGroups.MAIN, SUPPORTIUM_SONG);
    }
}
