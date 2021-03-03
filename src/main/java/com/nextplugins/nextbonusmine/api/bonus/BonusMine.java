package com.nextplugins.nextbonusmine.api.bonus;

import lombok.Builder;
import lombok.Data;
import org.bukkit.Material;

import java.util.List;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

@Data
@Builder
public class BonusMine {

    private final Material block;

    private final int level;
    private final int chance;

    private final List<Reward> rewardList;

}
