package com.iafenvoy.sop.power.type;

import com.iafenvoy.sop.power.PowerCategory;
import com.iafenvoy.sop.power.SongPowerDataHolder;

public non-sealed abstract class DummySongPower extends AbstractSongPower<DummySongPower> {
    public static final DummySongPower EMPTY = new DummySongPower("", null) {
        @Override
        protected void applyInternal(SongPowerDataHolder holder) {
        }
    };

    public DummySongPower(String id, PowerCategory category) {
        super(id, category);
    }

    @Override
    protected PowerType getType() {
        return PowerType.DUMMY;
    }

    @Override
    protected DummySongPower get() {
        return this;
    }
}
