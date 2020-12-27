package dev.kscott.itemtrak.command;

import cloud.commandframework.Command;
import cloud.commandframework.CommandManager;
import cloud.commandframework.context.CommandContext;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;

public abstract class ITCommand {

    protected final @NonNull CommandManager<CommandSender> commandManager;

    protected final @NonNull BukkitAudiences audiences;

    protected final @NonNull String name;

    protected final @NonNull String[] aliases;

    protected final Command.Builder<CommandSender> builder;

    public ITCommand(
            final @NonNull CommandManager<CommandSender> commandManager,
            final @NonNull BukkitAudiences audiences,
            final @NonNull String name,
            final @NonNull String... aliases
    ) {
        this.commandManager = commandManager;
        this.audiences = audiences;
        this.name = name;
        this.aliases = aliases;

        builder = commandManager.commandBuilder(name, aliases);

        this.commandManager.command(builder.handler(this::handleCommand));
    }

    protected abstract void handleCommand(final @NonNull CommandContext<CommandSender> sender);

    protected void registerSubcommand(final @NonNull ITSubcommand command) {
        this.commandManager.command(command.getBuilder());
    }

}
