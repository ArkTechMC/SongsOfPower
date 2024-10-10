package com.iafenvoy.sop.item.impl.forge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class ProtepointShieldItemImpl extends ShieldItem {
    public ProtepointShieldItemImpl() {
        super(new Settings().maxDamage(10000));
    }

    public static Item create() {
        return new ProtepointShieldItemImpl();
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!stack.isOf(this)) return;
        if (entity instanceof PlayerEntity player && player.getOffHandStack() == stack) return;
        stack.setDamage(this.getMaxDamage() + 1);
        stack.setCount(0);
    }
}
