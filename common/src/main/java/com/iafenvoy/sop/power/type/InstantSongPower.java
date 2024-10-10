package com.iafenvoy.sop.power.type;

import com.iafenvoy.sop.power.PowerCategory;
import com.iafenvoy.sop.power.SongPowerDataHolder;

public final class InstantSongPower extends AbstractSongPower<InstantSongPower> {
    public InstantSongPower(String id, PowerCategory category) {
        super(id, category);
    }

    @Override
    protected void applyInternal(SongPowerDataHolder holder) {
        this.apply.accept(holder);
        if (!holder.isCancelled()) {
            holder.cooldown();
            playSound(holder, this.applySound);
        }
    }

    @Override
    protected PowerType getType() {
        return PowerType.INSTANT;
    }

    @Override
    protected InstantSongPower get() {
        return this;
    }
}
