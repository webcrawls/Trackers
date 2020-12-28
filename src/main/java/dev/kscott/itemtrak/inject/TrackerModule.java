package dev.kscott.itemtrak.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import dev.kscott.itemtrak.tracker.TrackerManager;
import dev.kscott.itemtrak.tracker.TrackerRegistry;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

public class TrackerModule extends AbstractModule {

    @Override
    public final void configure() {
        this.bind(TrackerRegistry.class)
                .annotatedWith(Names.named("TrackerRegistry"))
                .toInstance(new TrackerRegistry());
    }

    @Provides
    @Singleton
    public @NonNull TrackerManager providesTrackerManager(
            @Named("TrackerRegistry") final @NonNull TrackerRegistry registry,
            @Named("JavaPlugin") final @NonNull JavaPlugin plugin
    ) {
        return new TrackerManager(plugin, registry);
    }

}
