package com.nextplugins.bonusmine.api.bonus;

import com.nextplugins.bonusmine.collection.RandomCollection;
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

    private final String id;

    private final List<Material> blocks;

    private final int level;
    private final double chance;

    private final RandomCollection<Reward> rewards;

}
