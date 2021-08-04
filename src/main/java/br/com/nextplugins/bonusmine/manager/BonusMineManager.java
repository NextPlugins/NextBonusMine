package br.com.nextplugins.bonusmine.manager;

import com.google.common.collect.Lists;
import br.com.nextplugins.bonusmine.NextBonusMine;
import br.com.nextplugins.bonusmine.api.bonus.BonusMine;
import lombok.Data;

import java.util.List;
import java.util.Random;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

@Data
public class BonusMineManager {

    private final NextBonusMine plugin;

    private static final Random random = new Random();

    public BonusMine getRandomBonus() {
        List<BonusMine> bonus = Lists.newLinkedList(plugin.getBonusMineAdapter().getValues());

        return bonus.get(random.nextInt(bonus.size()));
    }

}
