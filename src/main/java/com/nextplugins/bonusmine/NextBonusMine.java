package com.nextplugins.bonusmine;

import com.github.eikefab.libs.yamladapter.ConfigAdapter;
import com.nextplugins.bonusmine.api.bonus.BonusMine;
import com.nextplugins.bonusmine.configuration.ConfigValue;
import com.nextplugins.bonusmine.configuration.adapter.BonusMineAdapter;
import com.nextplugins.bonusmine.manager.BonusChestManager;
import com.nextplugins.bonusmine.manager.BonusMineManager;
import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class NextBonusMine extends JavaPlugin {

    /**
     * Metrics plugin id (used for statistics)
     */
    private static final int PLUGIN_ID = 10042;

    private final BonusMineManager bonusMineManager = new BonusMineManager(this);
    private final BonusChestManager bonusChestManager = new BonusChestManager(this);

    private final ConfigValue configValue = new ConfigValue(this.getConfig());

    private ConfigAdapter<BonusMine> bonusMineAdapter;

    @Override
    public void onLoad() {
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        loadBonus();
        configureBStats();

        this.getLogger().info("Plugin startup successfully");
    }

    private void loadBonus() {
        bonusMineAdapter = new ConfigAdapter<>(getConfig());

        bonusMineAdapter.adapt("bonus", BonusMineAdapter.class);
    }

    private void configureBStats() {
        new Metrics(this, PLUGIN_ID);

        this.getLogger().info("Enabled bStats successfully, statistics enabled");
    }

}
