package sh.kaden.trackers.command;

import cloud.commandframework.Command;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.paper.PaperCommandManager;
import com.google.inject.Inject;
import sh.kaden.trackers.tracker.TrackerManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class CommandItemTrak {

    private final @NonNull PaperCommandManager<CommandSender> manager;

    private final @NonNull TrackerManager trackerManager;

    @Inject
    public CommandItemTrak(
            final @NonNull PaperCommandManager<CommandSender> manager,
            final @NonNull TrackerManager trackerManager
    ) {
        this.manager = manager;
        this.trackerManager = trackerManager;

        final Command.Builder<CommandSender> builder = this.manager.commandBuilder("itemtrak", "it");

        this.manager.command(builder.handler(this::handleCommandItemTrak));

        this.manager.command(
                builder.literal("apply")
                        .handler(this::handleSubcommandApply)
        );
    }

    private void handleCommandItemTrak(final @NonNull CommandContext<CommandSender> context) {
    }

    private void handleSubcommandApply(final @NonNull CommandContext<CommandSender> context) {
        final @NonNull CommandSender sender = context.getSender();

        final boolean isPlayer = sender instanceof Player;

        if (isPlayer) {
            final @NonNull Player player = (Player) sender;

            final @Nullable ItemStack itemStack = player.getInventory().getItemInMainHand();

            if (itemStack == null || itemStack.getType().isAir()) {
                sender.sendMessage("You must be holding an item.");
                return;
            }

            this.trackerManager.applyTracking("poop", itemStack);
        }
    }


}
