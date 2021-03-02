package com.nextplugins.nextbonusmine;

import com.nextplugins.nextbonusmine.manager.BonusMineManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class NextBonusMine extends JavaPlugin {

    /**
     * Metrics plugin id (used for statistics)
     */
    private static final int PLUGIN_ID = 10042;

    private final BonusMineManager bonusMineManager = new BonusMineManager();

    @Override
    public void onEnable() {

        configureBStats();

        this.getLogger().info("Plugin startup successfully");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void configureBStats() {

        new Metrics(this, PLUGIN_ID);
        this.getLogger().info("Enabled bStats successfully, statistics enabled");

    }
}
