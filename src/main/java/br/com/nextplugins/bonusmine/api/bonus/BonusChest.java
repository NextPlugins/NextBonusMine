package br.com.nextplugins.bonusmine.api.bonus;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import lombok.Builder;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Data
@Builder
public class BonusChest {

    private Player owner;
    private BonusMine bonusMine;
    private Location location;
    private Hologram hologram;

}
