package br.com.nextplugins.bonusmine.configuration.adapter;

import br.com.nextplugins.bonusmine.api.bonus.BonusMine;
import br.com.nextplugins.bonusmine.api.bonus.Reward;
import br.com.nextplugins.bonusmine.collection.RandomCollection;
import com.github.eikefab.libs.yamladapter.YamlAdapter;
import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Objects;

public final class BonusMineAdapter implements YamlAdapter<BonusMine> {

    @Override
    public BonusMine adapt(String key, ConfigurationSection section) {
        return BonusMine.builder()
                .id(key)
                .blocks(materials(section))
                .chance(section.getDouble("chance"))
                .level(section.getInt("level"))
                .rewards(rewards(Objects.requireNonNull(section.getConfigurationSection("rewards"))))
                .build();
    }

    @Override
    public ConfigurationSection adapt(BonusMine value) {
        return null;
    }

    private List<Material> materials(ConfigurationSection section) {
        List<Material> blocks = Lists.newLinkedList();

        for (String material : section.getStringList("materials")) {
            blocks.add(Material.valueOf(material));
        }

        return blocks;
    }

    private RandomCollection<Reward> rewards(ConfigurationSection section) {
        RandomCollection<Reward> rewards = new RandomCollection<>();

        for (String reward : section.getKeys(false)) {
            rewards.add(section.getDouble(reward + ".chance"),
                    Reward.builder()
                            .chance(section.getDouble(reward + ".chance"))
                            .commands(section.getStringList(reward + ".commands"))
                            .build()
            );
        }

        return rewards;
    }

}
