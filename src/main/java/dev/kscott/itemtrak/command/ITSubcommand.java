package dev.kscott.itemtrak.command;

import cloud.commandframework.Command;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.execution.CommandExecutionHandler;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;

public abstract class ITSubcommand {

    protected abstract Command.Builder<CommandSender> getBuilder();

}
