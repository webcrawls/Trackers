package dev.kscott.itemtrak.data;

import cloud.commandframework.paper.PaperCommandManager;
import com.google.inject.Inject;
import dev.kscott.itemtrak.tracker.TrackerConfig;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlite3.SQLitePlugin;
import sun.rmi.rmic.Names;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Handles interactions with the SQLite database.
 */
public class DataManager {

    private final @NonNull Map<@NonNull UUID, Map<@NonNull String, @NonNull TrackerConfig>> itemTrackerMap;

    private final @NonNull JavaPlugin plugin;

    private final @NonNull Jdbi jdbi;

    private final @NonNull String databasePath;

    private final @NonNull PaperCommandManager<CommandSender> commandManager;

    private final @NonNull NamespacedKey trackerItemKey;

    /**
     * Constructs the DataManager and initializes the Jdbi connection.
     *
     * @param plugin JavaPlugin reference.
     */
    @Inject
    public DataManager(
            final @NonNull JavaPlugin plugin,
            final @NonNull PaperCommandManager<CommandSender> commandManager
    ) {
        this.plugin = plugin;
        this.databasePath = Paths.get(plugin.getDataFolder().toString(), "items.db").toString();

        this.trackerItemKey = new NamespacedKey(plugin, "itemtrak_item_uuid");

        this.jdbi = Jdbi.create("jdbc:sqlite:" + databasePath)
                .installPlugin(new SQLitePlugin());

        this.itemTrackerMap = new HashMap<>();

        this.commandManager = commandManager;
    }

    public @Nullable Map<String, TrackerConfig> getTrackerData(final @NonNull UUID uuid) {
        return this.itemTrackerMap.get(uuid);
    }

    public @NonNull UUID initializeTracker(final @NonNull ItemStack itemStack, final @NonNull TrackerConfig trackerConfig) {

        final @NonNull ItemMeta itemMeta = itemStack.hasItemMeta()
                ? itemStack.getItemMeta()
                : Bukkit.getItemFactory().getItemMeta(itemStack.getType());

        final @NonNull UUID itemUuid = itemMeta.getPersistentDataContainer().has(trackerItemKey, PersistentDataType.STRING) ?
                UUID.fromString(itemMeta.getPersistentDataContainer().get(trackerItemKey, PersistentDataType.STRING)) :
                generateUuid();

        final @NonNull Map<String, TrackerConfig> trackerConfigs = itemTrackerMap.computeIfAbsent(itemUuid, uuid -> new HashMap<>());
    }

    public @NonNull Map<String, TrackerConfig> getTrackerConfig(final @NonNull UUID uuid) {

    }

    /**
     * Generates a completely unique UUID, guaranteeing no database collisions.
     *
     * @return a UUID
     */
    private UUID generateUuid() {
        return UUID.randomUUID();
    }

}
