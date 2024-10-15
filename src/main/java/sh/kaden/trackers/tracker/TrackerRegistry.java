package sh.kaden.trackers.tracker;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TrackerRegistry {

    private final @NonNull Map<String, TrackerConfig> trackerMap;

    public TrackerRegistry() {
        this.trackerMap = new HashMap<>();
    }

    public void registerTrackerConfig(final @NonNull TrackerConfig tracker) {
        this.trackerMap.put(tracker.getId(), tracker);
    }

    public @Nullable TrackerConfig getTrackerConfig(final @NonNull String id) {
        return trackerMap.get(id);
    }

    public Collection<TrackerConfig> getTrackerConfigs() {
        return trackerMap.values();
    }

}
