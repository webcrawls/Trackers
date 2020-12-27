package dev.kscott.itemtrak.inject;

import com.google.inject.AbstractModule;
import dev.kscott.itemtrak.ItemTrakPlugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Provides JavaPlugin and ItemTrakPlugin.
 */
public final class PluginModule extends AbstractModule {

    /**
     * ItemTrakPlugin reference.
     */
    private final @NonNull ItemTrakPlugin plugin;

    /**
     * Constructs PluginModule.
     *
     * @param plugin ItemTrakPlugin reference.
     */
    public PluginModule(final @NonNull ItemTrakPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public final void configure() {
        this.bind(JavaPlugin.class).toInstance(plugin);
        this.bind(ItemTrakPlugin.class).toInstance(plugin);
    }

}
