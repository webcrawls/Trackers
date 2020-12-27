package dev.kscott.itemtrak.command.itemtrak;

import cloud.commandframework.Command;
import dev.kscott.itemtrak.command.ITSubcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class SubcommandHelp extends ITSubcommand {

    private final Command.Builder<CommandSender> builder;

    public SubcommandHelp(final Command.Builder<CommandSender> builder) {
        this.builder = builder;
    }

    protected Command.Builder<CommandSender> getBuilder() {
        return builder.literal("help")
                .handler(context -> {
                    final @NonNull CommandSender sender = context.getSender();

                    final boolean isPlayer = sender instanceof Player;

                    sender.sendMessage("you need help");
                });
    }
}
