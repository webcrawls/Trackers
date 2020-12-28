package dev.kscott.itemtrak.command;

import cloud.commandframework.Command;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.paper.PaperCommandManager;
import com.google.inject.Inject;
import dev.kscott.itemtrak.tracker.TrackerConfig;
import dev.kscott.itemtrak.tracker.TrackerRegistry;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;

public class CommandItemTrak {

    private final @NonNull PaperCommandManager<CommandSender> manager;

    private final @NonNull TrackerRegistry trackerRegistry;

    @Inject
    public CommandItemTrak(
            final @NonNull PaperCommandManager<CommandSender> manager,
            final @NonNull TrackerRegistry trackerRegistry
    ) {
        this.manager = manager;
        this.trackerRegistry = trackerRegistry;

        final Command.Builder<CommandSender> builder = this.manager.commandBuilder("itemtrak", "it");

        builder.handler(this::handleCommandItemTrak);
    }

    private void handleCommandItemTrak(final @NonNull CommandContext<CommandSender> context) {
        for (final TrackerConfig trackerConfig : trackerRegistry.getTrackerConfigs()) {
            context.getSender().sendMessage(trackerConfig.getId());
        }
    }


}
