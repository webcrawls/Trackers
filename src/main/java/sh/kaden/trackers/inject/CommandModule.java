package sh.kaden.trackers.inject;

import cloud.commandframework.CommandManager;
import cloud.commandframework.bukkit.CloudBukkitCapabilities;
import cloud.commandframework.execution.AsynchronousCommandExecutionCoordinator;
import cloud.commandframework.paper.PaperCommandManager;
import cloud.commandframework.tasks.TaskRecipe;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.function.Function;

/**
 * Provides the CommandManager
 */
public final class CommandModule extends AbstractModule {

    /**
     * PaperCommandManager reference.
     */
    private final @NonNull PaperCommandManager<CommandSender> commandManager;

    /**
     * Initializes the PaperCommandManager.
     *
     * @param plugin JavaPlugin reference.
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
            e.printStackTrace();
            throw new RuntimeException("Failed to register command manager!");
        }
    }

    /**
     * Provides the CommandManager.
     *
     * @return {@link this#commandManager}
     */
    @Provides
    @Singleton
    public final CommandManager<CommandSender> providesCommandManager() {
        return this.commandManager;
    }

    /**
     * Provides the PaperCommandManager.
     *
     * @return {@link this#commandManager}
     */
    @Provides
    @Singleton
    public final PaperCommandManager<CommandSender> providesPaperCommandManager() {
        return this.commandManager;
    }

}
