package br.com.nextplugins.bonusmine.manager;

import br.com.nextplugins.bonusmine.NextBonusMine;
import br.com.nextplugins.bonusmine.api.bonus.BonusChest;
import br.com.nextplugins.bonusmine.api.bonus.BonusMine;
import br.com.nextplugins.bonusmine.api.bonus.Reward;
import br.com.nextplugins.bonusmine.util.FireworkUtil;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.google.common.collect.Lists;
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
            Location hologramLocation = location.clone();

            bonusChestBuilder.hologram(createChestHologram(bonusMine, hologramLocation));
        }

        BonusChest bonusChest = bonusChestBuilder
            .owner(player)
            .location(location)
            .bonusMine(bonusMine)
            .build();

        bonusChests.add(bonusChest);
    }

    public Hologram createChestHologram(BonusMine bonusMine, Location hologramLocation) {
        final double x = hologramLocation.getX();
        final double z = hologramLocation.getZ();

        final Location centeredLocation = hologramLocation.add(x > 0 ? 0.5 : -0.5, 2.0, z > 0 ? 0.5 : -0.5);

        Hologram hologram = HologramsAPI.createHologram(plugin, centeredLocation);

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

        FireworkUtil.spawnFirework(bonusChest.getLocation());

        bonusChests.remove(bonusChest);
    }

    public BonusChest getChest(Location location) {
        BonusChest bonusChest = null;

        for (BonusChest chest : bonusChests) {
            if (chest.getLocation().equals(location)) {
                bonusChest = chest;
            }
        }

        return bonusChest;
    }

}
