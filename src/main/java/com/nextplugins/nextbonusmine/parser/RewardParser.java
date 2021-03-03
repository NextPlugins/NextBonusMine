package com.nextplugins.nextbonusmine.parser;

import com.google.common.collect.Lists;
import com.nextplugins.nextbonusmine.api.bonus.Reward;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */
public class RewardParser {

    public List<Reward> parseListSection(ConfigurationSection section) {

        List<Reward> rewards = Lists.newArrayList();
        for (String key : section.getKeys(false)) {
            rewards.add(parseSection(section.getConfigurationSection(key)));
        }

        return rewards;

    }

    public Reward parseSection(ConfigurationSection section) {

        return Reward.builder()
                .chance(section.getInt("chance"))
                .command(section.getStringList("command"))
                .build();

    }

}
