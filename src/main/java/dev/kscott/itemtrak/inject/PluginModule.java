package dev.kscott.itemtrak.inject;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import dev.kscott.itemtrak.ItemTrakPlugin;
import net.kyori.adventure.platform.AudienceProvider;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
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
     * BukkitAudiences reference.
     */
    private final @NonNull BukkitAudiences audiences;

    /**
     * Constructs PluginModule.
     *
     * @param plugin ItemTrakPlugin reference.
     */
    public PluginModule(final @NonNull ItemTrakPlugin plugin) {
        this.plugin = plugin;
        this.audiences = BukkitAudiences.create(plugin);
    }

    /**
     * Configures Guice to provide plugin and audience stuff
     */
    @Override
    public final void configure() {
        this.bind(JavaPlugin.class)
                .toInstance(plugin);
        this.bind(ItemTrakPlugin.class)
                .toInstance(plugin);

        this.bind(BukkitAudiences.class).toInstance(audiences);
    }

}
