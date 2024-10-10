package com.iafenvoy.sop.item.impl.forge;

import net.minecraft.item.Item;
import net.minecraft.item.ShieldItem;

public class ProtepointShieldItemImpl extends ShieldItem {
    public ProtepointShieldItemImpl() {
        super(new Settings().maxDamage(10000));
    }

    public static Item create() {
        return new ProtepointShieldItemImpl();
    }
}
