package net.wavemc.pvp.cooldown1;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class WaveCooldownAPI {

    @Getter
    @NonNull
    private String name;

    @Getter
    @NonNull
    private Long duration;
    private long startTime = System.currentTimeMillis();

    public double getPercentage() {
        return (getRemaining() * 100) / duration;
    }

    public double getRemaining() {
        long endTime = startTime + TimeUnit.SECONDS.toMillis(duration);
        return (-(System.currentTimeMillis() - endTime)) / 1000D;
    }

    public boolean expired() {
        return getRemaining() < 0D;
    }
}
