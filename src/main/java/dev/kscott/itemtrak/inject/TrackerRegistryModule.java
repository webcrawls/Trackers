package dev.kscott.itemtrak.inject;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import dev.kscott.itemtrak.tracker.TrackerRegistry;
import org.checkerframework.checker.nullness.qual.NonNull;

public class TrackerRegistryModule extends AbstractModule {

    final @NonNull TrackerRegistry registry;

    public TrackerRegistryModule() {
        this.registry = new TrackerRegistry();
    }

    @Override
    public final void configure() {
        this.bind(TrackerRegistry.class)
                .annotatedWith(Names.named("TrackerRegistry"))
                .toInstance(this.registry);
    }

}
