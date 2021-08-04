package br.com.nextplugins.bonusmine;

import br.com.nextplugins.bonusmine.api.bonus.BonusMine;
import br.com.nextplugins.bonusmine.configuration.ConfigValue;
import br.com.nextplugins.bonusmine.configuration.adapter.BonusMineAdapter;
import br.com.nextplugins.bonusmine.listener.BlockBreakListener;
import br.com.nextplugins.bonusmine.listener.PlayerInteractListener;
import br.com.nextplugins.bonusmine.manager.BonusChestManager;
import br.com.nextplugins.bonusmine.manager.BonusMineManager;
import com.github.eikefab.libs.yamladapter.ConfigAdapter;
import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.PluginManager;
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
        listeners();
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

    private void listeners() {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(
                new BlockBreakListener(
                        this.configValue,
                        this.bonusMineManager,
                        this.bonusChestManager
                ),
                this
        );

        pluginManager.registerEvents(
                new PlayerInteractListener(
                        this.configValue,
                        this.bonusChestManager
                ),
                this
        );
    }

}
