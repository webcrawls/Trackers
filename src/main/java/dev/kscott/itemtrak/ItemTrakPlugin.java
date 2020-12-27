package dev.kscott.itemtrak;

import com.google.inject.Guice;
import com.google.inject.Injector;
import dev.kscott.itemtrak.inject.PluginModule;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * The ItemTrak base plugin class.
 */
public final class ItemTrakPlugin extends JavaPlugin {

    /**
     * Registers important plugin stuff
     */
    @Override
    public void onEnable() {
        final @NonNull Injector injector = Guice.createInjector(
                new PluginModule(this)
        );

    }
}
