package dev.kscott.itemtrak.db;

import com.google.inject.Inject;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlite3.SQLitePlugin;

import java.nio.file.Paths;

public class DatabaseManager {

    private final @NonNull JavaPlugin plugin;

    private final @NonNull Jdbi jdbi;

    private final @NonNull String databasePath;

    @Inject
    /**
     *
     */
    public DatabaseManager(final @NonNull JavaPlugin plugin) {
        this.plugin = plugin;
        this.databasePath = Paths.get(plugin.getDataFolder().toString(), "items.db").toString();

        this.jdbi = Jdbi.create("jdbc:sqlite:" + databasePath)
                .installPlugin(new SQLitePlugin());
    }

}
