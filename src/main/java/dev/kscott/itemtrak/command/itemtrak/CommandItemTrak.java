package dev.kscott.itemtrak.command.itemtrak;

import cloud.commandframework.CommandManager;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.paper.PaperCommandManager;
import com.google.inject.Inject;
import dev.kscott.itemtrak.command.ITCommand;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Provides /itemtrak
 */
public class CommandItemTrak extends ITCommand {

    /**
     * BukkitAudiences reference.
     */
    private final @NonNull BukkitAudiences audiences;

    /**
     * Constructs BukkitAudiences
     *
     * @param audiences BukkitAudiences reference
     */
    @Inject
    public CommandItemTrak(
            final @NonNull CommandManager<CommandSender> commandManager,
            final @NonNull BukkitAudiences audiences
    ) {
        super(commandManager, audiences,"itemtrak", "it");
        this.audiences = audiences;
        this.registerSubcommand(new SubcommandHelp(this.builder));
    }

    /**
     * Handles /itemtrak.
     *
     * @param context The command context.
     */
    protected void handleCommand(final @NonNull CommandContext<CommandSender> context) {
        final @NonNull CommandSender sender = context.getSender();

        final @NonNull Audience audience = audiences.sender(sender);

        final boolean isPlayer = sender instanceof Player;

        if (isPlayer) {
            audience.sendMessage(Component.text("sup nerd xD"));
        }

    }
}
