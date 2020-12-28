package dev.kscott.itemtrak;

import com.google.inject.Guice;
import com.google.inject.Injector;
import dev.kscott.itemtrak.command.CommandItemTrak;
import dev.kscott.itemtrak.inject.CommandModule;
import dev.kscott.itemtrak.inject.PluginModule;
import dev.kscott.itemtrak.inject.TrackerModule;
import dev.kscott.itemtrak.tracker.TrackerConfig;
import dev.kscott.itemtrak.tracker.TrackerRegistry;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * The ItemTrak base plugin class.
 */
public final class ItemTrakPlugin extends JavaPlugin {

    /**
     * Registers important plugin stuff.
     */
    @Override
    public void onEnable() {
        final @NonNull Injector injector = Guice.createInjector(
                new PluginModule(this),
                new CommandModule(this),
                new TrackerModule()
        );

        final @NonNull TrackerRegistry trackerRegistry = injector.getInstance(TrackerRegistry.class);

        trackerRegistry.registerTrackerConfig(new TrackerConfig("poop", MiniMessage.get().parse("<aqua>test</aqua>")));

        injector.getInstance(CommandItemTrak.class);
    }
}
