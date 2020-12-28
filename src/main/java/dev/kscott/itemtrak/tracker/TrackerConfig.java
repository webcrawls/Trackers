package dev.kscott.itemtrak.tracker;

import net.kyori.adventure.text.Component;
import org.checkerframework.checker.nullness.qual.NonNull;

public class TrackerConfig {

    private final @NonNull String id;

    private final @NonNull Component format;

    public TrackerConfig(
            final @NonNull String id,
            final @NonNull Component format
    ) {
        this.id = id;
        this.format = format;
    }

    public String getId() {
        return id;
    }

    public Component getFormat() {
        return format;
    }
}
