package me.P4CTO.obbydestructor;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class ObbyDestructor extends JavaPlugin {

	private static ObbyDestructor instance;
	public static final String TAG = ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "ObbyDestructor" + ChatColor.DARK_AQUA + "> " + ChatColor.YELLOW;

	public static ObbyDestructor getJavaPlugin() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;

        getLogger().info("|--------------------------------------------------|");
        getLogger().info("| ObsidianDestructor has initialized successfully! |");
        getLogger().info("|--------------------------------------------------|");

		if (!(new File(getDataFolder(), "config.yml")).exists()) {
			saveDefaultConfig();
		}

		Bukkit.getServer().getPluginManager().registerEvents(new ObbyListener(), this);
		this.getCommand("obd").setExecutor(new ObbyCommand());
	}

	@Override
	public void onDisable() {
		saveConfig();
	}
}
