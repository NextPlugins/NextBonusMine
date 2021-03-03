package com.nextplugins.nextbonusmine.manager;

import com.google.common.collect.Lists;
import com.nextplugins.nextbonusmine.api.bonus.BonusMine;
import com.nextplugins.nextbonusmine.parser.BonusMineParser;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

@Getter
public class BonusMineManager {

    protected static final BonusMineParser bonusMineParser = new BonusMineParser();

    private final List<BonusMine> bonus = Lists.newArrayList();

    public void loadAll(ConfigurationSection section) {
        bonusMineParser.parseListSection(section).forEach(this::register);
    }

    private void register(BonusMine bonusMine) {
        this.bonus.add(bonusMine);
    }


}
