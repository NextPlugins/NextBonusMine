package com.nextplugins.bonusmine.manager;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.google.common.collect.Lists;
import com.nextplugins.bonusmine.NextBonusMine;
import com.nextplugins.bonusmine.api.bonus.BonusChest;
import com.nextplugins.bonusmine.api.bonus.BonusMine;
import com.nextplugins.bonusmine.api.bonus.Reward;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

@RequiredArgsConstructor
public final class BonusChestManager {

    private final NextBonusMine plugin;

    @Getter
    private final List<BonusChest> bonusChests = Lists.newLinkedList();

    public void createBonusChest(BonusMine bonusMine, Player player, Location location) {
        Block block = location.getBlock();

        block.setType(Material.CHEST);

        BonusChest.BonusChestBuilder bonusChestBuilder = BonusChest.builder();

        if (plugin.getConfigValue().enableHolograms) {
            Location hologramLocation = block.getLocation().clone().add(0, 2, 0);

            bonusChestBuilder.hologram(createChestHologram(bonusMine, hologramLocation));
        }

        BonusChest bonusChest = bonusChestBuilder
                .owner(player)
                .location(location)
                .bonusMine(bonusMine)
                .build();

        bonusChests.add(bonusChest);
    }

    public Hologram createChestHologram(BonusMine bonusMine, Location location) {
        Hologram hologram = HologramsAPI.createHologram(plugin, location);

        for (String line : plugin.getConfigValue().hologramStyle) {
            hologram.appendTextLine(
                    line.replace("@level", String.valueOf(bonusMine.getLevel()))
            );
        }

        return hologram;
    }

    public void openChest(BonusChest bonusChest) {
        bonusChest.getLocation().getBlock().setType(Material.AIR);
        bonusChest.getHologram().delete();

        Reward reward = bonusChest.getBonusMine().getRewards().next();

        for (String command : reward.getCommands()) {
            Bukkit.dispatchCommand(
                    Bukkit.getConsoleSender(),
                    command.replace("@player", bonusChest.getOwner().getName())
            );
        }

        bonusChests.remove(bonusChest);
    }

}
