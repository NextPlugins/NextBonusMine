package com.nextplugins.bonusmine.listener;

import com.nextplugins.bonusmine.api.bonus.BonusMine;
import com.nextplugins.bonusmine.configuration.ConfigValue;
import com.nextplugins.bonusmine.manager.BonusChestManager;
import com.nextplugins.bonusmine.manager.BonusMineManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

@RequiredArgsConstructor
public final class BlockBreakListener implements Listener {

    private final ConfigValue configValue;
    private final BonusMineManager bonusManager;
    private final BonusChestManager chestManager;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final Block clickedBlock = event.getClickedBlock();

        if (clickedBlock == null) return;

        if (!configValue.worlds.contains(player.getWorld().getName())) return;

        BonusMine bonus = bonusManager.getRandomBonus();

        if (!hasChance(bonus.getChance())) return;

        chestManager.createBonusChest(bonus, player, clickedBlock.getLocation());
    }

    private boolean hasChance(double percent) {
        return Math.random() * 100.0 <= percent;
    }

}
