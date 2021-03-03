package com.nextplugins.nextbonusmine.parser;

import com.google.common.collect.Lists;
import com.nextplugins.nextbonusmine.api.bonus.BonusMine;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */
public class BonusMineParser {

    protected static final RewardParser rewardParser = new RewardParser();

    public List<BonusMine> parseListSection(ConfigurationSection section) {

        List<BonusMine> bonusMine = Lists.newArrayList();
        for (String key : section.getKeys(false)) {
            bonusMine.add(parseSection(section.getConfigurationSection(key)));
        }

        return bonusMine;

    }

    public BonusMine parseSection(ConfigurationSection section) {

        return BonusMine.builder()
                .level(section.getInt("level"))
                .chance(section.getInt("chance"))
                .block(Material.valueOf(section.getString("material")))
                .rewardList(rewardParser.parseListSection(section.getConfigurationSection("rewards")))
                .build();

    }

}
