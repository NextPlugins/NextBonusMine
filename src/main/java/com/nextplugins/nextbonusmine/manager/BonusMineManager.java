package com.nextplugins.nextbonusmine.manager;

import com.google.common.collect.Lists;
import com.nextplugins.nextbonusmine.api.bonus.BonusMine;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

@Getter
public class BonusMineManager {

    protected static final

    private final List<BonusMine> bonus = Lists.newArrayList();

    public void loadAll(ConfigurationSection section) {

    }

    private void register(BonusMine bonusMine) {

    }


}
