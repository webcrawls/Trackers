package sh.kaden.trackers.tracker;

import org.checkerframework.checker.nullness.qual.NonNull;

public class TrackerData {

    private final @NonNull TrackerConfig trackerConfig;

    private int count;

    public TrackerData(final @NonNull TrackerConfig trackerConfig, final int count) {
        this.trackerConfig = trackerConfig;
        this.count = count;
    }

    public void incrementCount() {
        count++;
    }

    public void incrementCount(final int amount) {
        count += amount;
    }

    public int getCount() {
        return count;
    }

    public @NonNull TrackerConfig getTrackerConfig() {
        return trackerConfig;
    }
}
