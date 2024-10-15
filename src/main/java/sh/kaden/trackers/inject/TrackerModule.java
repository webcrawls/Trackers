package sh.kaden.trackers.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import sh.kaden.trackers.tracker.TrackerManager;
import sh.kaden.trackers.tracker.TrackerRegistry;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

public class TrackerModule extends AbstractModule {

    @Override
    public final void configure() {
        final @NonNull TrackerRegistry trackerRegistry = new TrackerRegistry();

        this.bind(TrackerRegistry.class)
                .annotatedWith(Names.named("TrackerRegistry"))
                .toInstance(trackerRegistry);

        this.bind(TrackerRegistry.class)
                .toInstance(trackerRegistry);
    }

    @Provides
    @Singleton
    public @NonNull TrackerManager providesTrackerManager(
            final @NonNull TrackerRegistry registry,
            final @NonNull JavaPlugin plugin
    ) {
        System.out.println(registry.getTrackerConfigs());
        return new TrackerManager(plugin, registry);
    }

}
