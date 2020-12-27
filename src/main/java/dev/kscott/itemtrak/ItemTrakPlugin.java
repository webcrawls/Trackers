package dev.kscott.itemtrak;

import cloud.commandframework.paper.PaperCommandManager;
import com.google.inject.Guice;
import com.google.inject.Injector;
import dev.kscott.itemtrak.command.itemtrak.CommandItemTrak;
import dev.kscott.itemtrak.command.ITCommand;
import dev.kscott.itemtrak.inject.CommandModule;
import dev.kscott.itemtrak.inject.PluginModule;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Arrays;
import java.util.List;

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
                new CommandModule(this)
        );

        initializeCommands(injector);
    }

    /**
     * Initializes ItemTrak commands.
     */
    private void initializeCommands(final @NonNull Injector injector) {
        final @NonNull List<Class<? extends ITCommand>> commands = Arrays.asList(
                CommandItemTrak.class
        );

        commands.forEach(injector::getInstance);
    }

}
