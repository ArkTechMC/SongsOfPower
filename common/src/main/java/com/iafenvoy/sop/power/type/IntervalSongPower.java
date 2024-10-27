package com.iafenvoy.sop.power.type;

import com.iafenvoy.neptune.util.Timeout;
import com.iafenvoy.sop.power.PowerCategory;
import com.iafenvoy.sop.power.SongPowerDataHolder;

import java.util.function.ToIntFunction;

public final class IntervalSongPower extends AbstractSongPower<IntervalSongPower> {
    private ToIntFunction<SongPowerDataHolder> times = data -> 0;
    private ToIntFunction<SongPowerDataHolder> interval = data -> 0;

    public IntervalSongPower(String id, PowerCategory category) {
        super(id, category);
    }

    public IntervalSongPower setInterval(int interval) {
        return this.setInterval(data -> interval);
    }

    public IntervalSongPower setInterval(ToIntFunction<SongPowerDataHolder> interval) {
        this.interval = interval;
        return this;
    }

    public IntervalSongPower setTimes(int times) {
        return this.setTimes(data -> times);
    }

    public IntervalSongPower setTimes(ToIntFunction<SongPowerDataHolder> times) {
        this.times = times;
        return this;
    }

    @Override
    protected void applyInternal(SongPowerDataHolder holder) {
        playSound(holder, this.applySound);
        this.apply.accept(holder);
        if (holder.isCancelled()) return;
        holder.cooldown();
        Timeout.create(this.interval.applyAsInt(holder), this.times.applyAsInt(holder), () -> this.apply.accept(holder));
    }

    @Override
    protected PowerType getType() {
        return PowerType.INTERVAL;
    }

    @Override
    public int getPrimaryCooldown(SongPowerDataHolder data) {
        return super.getPrimaryCooldown(data) + this.interval.applyAsInt(data) * this.times.applyAsInt(data);
    }

    @Override
    protected IntervalSongPower get() {
        return this;
    }
}
