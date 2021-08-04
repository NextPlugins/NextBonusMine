package br.com.nextplugins.bonusmine.util;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;

public final class FireworkUtil {

    private static final Random random = new Random();

    public static void spawnFirework(Location location) {
        final Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        final FireworkMeta fireworkMeta = firework.getFireworkMeta();

        fireworkMeta.setPower(5);
        fireworkMeta.addEffect(FireworkEffect.builder()
            .withColor(generateRandomColor())
            .withFade(generateRandomColor())
            .trail(false)
            .build()
        );

        firework.setFireworkMeta(fireworkMeta);
        firework.detonate();
    }

    private static Color generateRandomColor() {
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);

        return Color.fromBGR(blue, green, red);
    }

}
