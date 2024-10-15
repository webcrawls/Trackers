package sh.kaden.trackers.tracker;

import com.google.inject.Inject;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TrackerManager {

    private final @NonNull JavaPlugin plugin;

    private final @NonNull TrackerRegistry registry;

    private final @NonNull NamespacedKey trackerIdsKey;

    @Inject
    public TrackerManager(
            final @NonNull JavaPlugin plugin,
            final @NonNull TrackerRegistry registry
    ) {
        this.plugin = plugin;
        this.registry = registry;
        this.trackerIdsKey = new NamespacedKey(plugin, "itemtrakIds");
    }


    /**
     * Applies a tracker to an item. Will fail if the tracker already exists on the item, or the tracker doesn't exist.
     * <p>
     * - Gets the TrackerConfig using id (will fail if tracker doesn't exist)
     * - Gets the ItemMeta (will create if null)
     * - Adds TrackerConfig id to ItemMeta's PDC
     * - Adds lore using generated ID prefix (&1&3 kinda thing)
     * - Stores lore ID prefix in PDC
     *
     * @param id        The ID of the {@link TrackerConfig} to apply
     * @param itemStack The {@link ItemStack} to apply tracker to
     */
    public void applyTracking(final @NonNull String id, final @NonNull ItemStack itemStack) {
        final @Nullable TrackerConfig trackerConfig = this.registry.getTrackerConfig(id);

        if (trackerConfig == null) {
            // TODO warn
            return;
        }

        applyTracking(trackerConfig, itemStack);
    }

    public void applyTracking(final @NonNull TrackerConfig trackerConfig, final @NonNull ItemStack itemStack) {
        @Nullable ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta == null) {
            itemMeta = Bukkit.getItemFactory().getItemMeta(itemStack.getType());
        }

        @Nullable String trackerIds = itemMeta.getPersistentDataContainer().get(trackerIdsKey, PersistentDataType.STRING);

        if (trackerIds == null) {
            trackerIds = trackerConfig.getId();
        } else {
            trackerIds = trackerIds + "," +trackerConfig.getId();
        }

        itemMeta.getPersistentDataContainer().set(trackerIdsKey, PersistentDataType.STRING, trackerIds);

        final @NonNull List<String> lore = itemMeta.hasLore() ? itemMeta.getLore() : new ArrayList<>();

        final @NonNull String trackerString =  LegacyComponentSerializer.legacySection().serialize(trackerConfig.getFormat());

        lore.add(trackerString);

        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);
    }

}
