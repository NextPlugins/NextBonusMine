package com.nextplugins.bonusmine.api.bonus;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

@Data
@Builder
public class Reward {

    private final double chance;
    private final List<String> commands;

}
