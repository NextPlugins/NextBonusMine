package com.nextplugins.bonusmine.listener;

import com.nextplugins.bonusmine.api.bonus.BonusChest;
import com.nextplugins.bonusmine.configuration.ConfigValue;
import com.nextplugins.bonusmine.manager.BonusChestManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

@RequiredArgsConstructor
public final class PlayerInteractListener implements Listener {

    private final ConfigValue configValue;
    private final BonusChestManager chestManager;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();

        if (clickedBlock == null) return;
        if (clickedBlock.getType() != Material.CHEST) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        BonusChest chest = chestManager.getChest(clickedBlock.getLocation());

        if (chest == null) return;

        chestManager.openChest(chest);
        for (String message : configValue.chestOpen) {
            player.sendMessage(message);
        }
    }

}
