package me.P4CTO.obbydestructor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ObbyCommand extends ObbyListener implements CommandExecutor {

	public ObbyCommand() {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender.isOp()) {

			if (args.length == 0) {

				sender.sendMessage(ChatColor.DARK_AQUA + "----" + ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "ObbyDestructor Help" + ChatColor.DARK_AQUA + "----");
				sender.sendMessage(ChatColor.YELLOW + "/obd allow creeper - Allows obsidian to be destroyed by creepers.");
				sender.sendMessage(ChatColor.YELLOW + "/odb allow tnt - Allows obsidian to be destroyed by TNT.");
				sender.sendMessage(ChatColor.YELLOW + "/odb allow fireball - Allows obsidian to be destroyed by fireballs.");
				sender.sendMessage(ChatColor.YELLOW + "/obd ht <amount> - Allows the obsidian to be destroyed by that certain amount of hits.");
				sender.sendMessage(ChatColor.YELLOW + "/obd radius <amount> - Allows the obsidian in that radius to be destroyed.");
				sender.sendMessage(ChatColor.YELLOW + "/obd wp - Allows obsidian to be protected by water.");
				sender.sendMessage(ChatColor.YELLOW + "/obd reload - Reloads the configuration to fix entity and hit time bugs.");

			} else if (args.length == 1) {

				if (args[0].equalsIgnoreCase("reload")) {
					ObbyDestructor.getJavaPlugin().reloadConfig();
					sender.sendMessage(ObbyDestructor.TAG + "Configuration reloaded!");

				} else if (args[0].equalsIgnoreCase("wp")) {

					if (allowWaterProtection) {
						ObbyDestructor.getJavaPlugin().getConfig().set("water-protect", false);

						sender.sendMessage(ObbyDestructor.TAG + "Water Protection disabled!");
						sender.sendMessage(ObbyDestructor.TAG + "Please reload the server to apply all the changes in the configuration.");
						ObbyDestructor.getJavaPlugin().saveConfig();

					} else {

						ObbyDestructor.getJavaPlugin().getConfig().set("water-protect", true);

						sender.sendMessage(ObbyDestructor.TAG + "Water Protection enabled!");
						sender.sendMessage(ObbyDestructor.TAG + "Please reload the server to apply all the changes in the configuration.");
						ObbyDestructor.getJavaPlugin().saveConfig();
					}
				} else {

					sender.sendMessage(ChatColor.RED + "Invalid arguments!");
				}

			} else if (args.length == 2) {

				if (args[0].equalsIgnoreCase("allow")) {

					if (args[1].equalsIgnoreCase("creeper")) {

						if (allowCreeper) {
							ObbyDestructor.getJavaPlugin().getConfig().set("allow-creeper", false);

							sender.sendMessage(ObbyDestructor.TAG + "Creepers disabled!");
							sender.sendMessage(ObbyDestructor.TAG + "Please reload the server to apply all the changes in the configuration.");
							ObbyDestructor.getJavaPlugin().saveConfig();

						} else {
							ObbyDestructor.getJavaPlugin().getConfig().set("allow-creeper", true);

							sender.sendMessage(ObbyDestructor.TAG + "Creepers enabled!");
							sender.sendMessage(ObbyDestructor.TAG + "Please reload the server to apply all the changes in the configuration.");
							ObbyDestructor.getJavaPlugin().saveConfig();
						}

					} else if (args[1].equalsIgnoreCase("tnt")) {

						if (allowTNT) {
							ObbyDestructor.getJavaPlugin().getConfig().set("allow-tnt", false);

							sender.sendMessage(ObbyDestructor.TAG + "TNT disabled!");
							sender.sendMessage(ObbyDestructor.TAG + "Please reload the server to apply all the changes in the configuration.");
							ObbyDestructor.getJavaPlugin().saveConfig();

						} else {
							ObbyDestructor.getJavaPlugin().getConfig().set("allow-tnt", true);

							sender.sendMessage(ObbyDestructor.TAG + "TNT enabled!");
							sender.sendMessage(ObbyDestructor.TAG + "Please reload the server to apply all the changes in the configuration.");
							ObbyDestructor.getJavaPlugin().saveConfig();
						}

					} else if (args[1].equalsIgnoreCase("fireball")) {

						if (allowFireball) {
							ObbyDestructor.getJavaPlugin().getConfig().set("allow-fireball", false);

							sender.sendMessage(ObbyDestructor.TAG + "Fireballs disabled!");
							sender.sendMessage(ObbyDestructor.TAG + "Please reload the server to apply all the changes in the configuration.");
							ObbyDestructor.getJavaPlugin().saveConfig();

						} else {
							ObbyDestructor.getJavaPlugin().getConfig().set("allow-fireball", true);

							sender.sendMessage(ObbyDestructor.TAG + "Fireballs enabled!");
							sender.sendMessage(ObbyDestructor.TAG + "Please reload the server to apply all the changes in the configuration.");
							ObbyDestructor.getJavaPlugin().saveConfig();
						}

					} else {

						sender.sendMessage(ChatColor.RED + "Invalid entity! Entities: TNT, Creeper Fireball, SmallFireball");
					}

				} else if (args[0].equalsIgnoreCase("ht")) {

					int ht = Integer.parseInt(args[1]);
					ObbyDestructor.getJavaPlugin().getConfig().set("hit-times", ht);

					sender.sendMessage(ObbyDestructor.TAG + "Obsidian can now get destroyed on " + ht + " hits.");
					sender.sendMessage(ObbyDestructor.TAG + "Please reload the server to apply all the changes in the configuration.");
					ObbyDestructor.getJavaPlugin().saveConfig();

				} else if (args[0].equalsIgnoreCase("radius")) {

					int rdius = Integer.parseInt(args[1]);
					ObbyDestructor.getJavaPlugin().getConfig().set("radius", rdius);

					sender.sendMessage(ObbyDestructor.TAG + "Obsidian in a radius of " + rdius + " blocks from the exploding entity can now get destroyed.");
					sender.sendMessage(ObbyDestructor.TAG + "Please reload the server to apply all the changes in the configuration.");
					ObbyDestructor.getJavaPlugin().saveConfig();

				} else {
					sender.sendMessage(ChatColor.RED + "Invalid arguments!");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Invalid amount of arguments!");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Access denied! Only administrators can use this command.");
		}
		return false;
	}
}
