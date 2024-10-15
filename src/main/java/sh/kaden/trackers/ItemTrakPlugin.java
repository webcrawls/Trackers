package sh.kaden.trackers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import sh.kaden.trackers.command.CommandItemTrak;
import sh.kaden.trackers.data.DataManager;
import sh.kaden.trackers.inject.CommandModule;
import sh.kaden.trackers.inject.PluginModule;
import sh.kaden.trackers.inject.TrackerModule;
import sh.kaden.trackers.tracker.TrackerConfig;
import sh.kaden.trackers.tracker.TrackerRegistry;
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

        injector.getInstance(DataManager.class);

        injector.getInstance(CommandItemTrak.class);
    }
}
