package br.com.nextplugins.bonusmine.listener;

import br.com.nextplugins.bonusmine.api.bonus.BonusMine;
import br.com.nextplugins.bonusmine.configuration.ConfigValue;
import br.com.nextplugins.bonusmine.manager.BonusChestManager;
import br.com.nextplugins.bonusmine.manager.BonusMineManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

@RequiredArgsConstructor
public final class BlockBreakListener implements Listener {

    private final ConfigValue configValue;
    private final BonusMineManager bonusManager;
    private final BonusChestManager chestManager;

    @EventHandler
    public void onInteract(BlockBreakEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getBlock();

        if (!configValue.worlds.contains(player.getWorld().getName())) return;

        final BonusMine bonus = bonusManager.getRandomBonus();

        if (!hasChance(bonus.getChance())) return;

        chestManager.createBonusChest(bonus, player, block.getLocation());
    }

    private boolean hasChance(double percent) {
        return Math.random() * 100.0 <= percent;
    }

}
