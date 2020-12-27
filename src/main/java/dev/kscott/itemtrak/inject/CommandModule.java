package dev.kscott.itemtrak.inject;

import cloud.commandframework.CommandManager;
import cloud.commandframework.bukkit.CloudBukkitCapabilities;
import cloud.commandframework.execution.AsynchronousCommandExecutionCoordinator;
import cloud.commandframework.paper.PaperCommandManager;
import com.google.inject.AbstractModule;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.function.Function;

/**
 * Provides the CommandManager
 */
public final class CommandModule extends AbstractModule {

    /**
     * PaperCommandManager reference
     */
    private final @NonNull PaperCommandManager<CommandSender> commandManager;

    /**
     * Initializes the PaperCommandManager
     *
     * @param plugin JavaPlugin reference
     */
    public CommandModule(final @NonNull JavaPlugin plugin) {
        final @NonNull Function<CommandSender, CommandSender> mapper = Function.identity();

        try {
            commandManager = new PaperCommandManager<>(
                    plugin,
                    AsynchronousCommandExecutionCoordinator.simpleCoordinator(),
                    mapper,
                    mapper
            );

            if (commandManager.queryCapability(CloudBukkitCapabilities.ASYNCHRONOUS_COMPLETION)) {
                commandManager.registerAsynchronousCompletions();
            }


        } catch (Exception e) {
            throw new RuntimeException("Failed to register command manager!", e);
        }
    }

    /**
     * Binds CommandManager and PaperCommandManager
     */
    @Override
    public final void configure() {
        this.bind(CommandManager.class).toInstance(commandManager);
        this.bind(PaperCommandManager.class).toInstance(commandManager);
    }



}
