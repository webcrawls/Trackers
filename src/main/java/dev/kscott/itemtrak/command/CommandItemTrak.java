package dev.kscott.itemtrak.command;

import cloud.commandframework.Command;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.paper.PaperCommandManager;
import com.google.inject.Inject;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;

public class CommandItemTrak {

    private final @NonNull PaperCommandManager<CommandSender> manager;

    @Inject
    public CommandItemTrak(
            final @NonNull PaperCommandManager<CommandSender> manager
    ) {
        this.manager = manager;

        final Command.Builder<CommandSender> builder = this.manager.commandBuilder("itemtrak", "it");

        builder.handler(this::handleCommandItemTrak);
    }

    private void handleCommandItemTrak(final @NonNull CommandContext<CommandSender> context) {
        context.getSender().sendMessage("poop");
    }


}
