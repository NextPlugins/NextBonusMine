package com.nextplugins.bonusmine.configuration;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.stream.Collectors;

public final class ConfigValue {

    private final FileConfiguration configuration;

    public int expireTime;

    public boolean enableHolograms;
    public List<String> hologramStyle;

    public List<String> worlds;

    public ConfigValue(FileConfiguration configuration) {
        this.configuration = configuration;

        expireTime = configuration.getInt("features.expireTime");
        enableHolograms = configuration.getBoolean("features.holograms.enable");
        hologramStyle = messages("features.holograms.style");
        worlds = messages("features.worlds");
    }

    public String message(String path) {
        return this.colored(configuration.getString(path));
    }

    public List<String> messages(String path) {
        return configuration.getStringList(path).stream()
                .map(this::colored)
                .collect(Collectors.toList());
    }

    public String colored(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
