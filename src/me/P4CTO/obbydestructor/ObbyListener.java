package me.P4CTO.obbydestructor;

import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ObbyListener implements Listener {

	public int hitTimes = ObbyDestructor.getJavaPlugin().getConfig().getInt("hit-times");
	private int hitCount = 0;
	private HashMap<Block, Material> obbyBlocks = new HashMap<Block, Material>();

	public boolean allowTNT = ObbyDestructor.getJavaPlugin().getConfig().getBoolean("allow-tnt");
	public boolean allowCreeper = ObbyDestructor.getJavaPlugin().getConfig().getBoolean("allow-creeper");
	public boolean allowFireball = ObbyDestructor.getJavaPlugin().getConfig().getBoolean("allow-fireball");
	public boolean allowWaterProtection = ObbyDestructor.getJavaPlugin().getConfig().getBoolean("water-protect");

	public int radius = ObbyDestructor.getJavaPlugin().getConfig().getInt("radius");

	public ObbyListener() {

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onTNTExplode(EntityExplodeEvent event) {

		if (event.getEntityType() == EntityType.PRIMED_TNT && !allowTNT) {
			return;

		} else if (event.getEntityType() == EntityType.CREEPER && !allowCreeper) {
			return;

		} else if (event.getEntityType() == EntityType.FIREBALL && !allowFireball) {
			return;
		}

		Location loc = event.getEntity().getLocation();

		for (int x = -radius; x <= radius; x++) {
			for (int y = -radius; y <= radius; y++) {
				for (int z = -radius; z <= radius; z++) {

					Block blocks = loc.getWorld().getBlockAt(loc.getBlockX() + x, loc.getBlockY() + y, loc.getBlockZ() + z);

					if (blocks.getType().equals(Material.OBSIDIAN)) {
						this.obbyBlocks.put(blocks, blocks.getType());
					}
				}
			}
		}

		this.hitCount += 1;

		HashSet<Block> expired = new HashSet<Block>();

		for (Block b : obbyBlocks.keySet()) {

			if (hitCount >= hitTimes) {
				expired.add(b);
			}
		}

		for (final Block b : expired) {

			this.hitCount = hitCount / 2;

			Location bLoc = b.getLocation();

			new BukkitRunnable() {

				@Override
				public void run() {

					hitCount = 0;
					obbyBlocks.remove(b);
				}
			}.runTaskLater(ObbyDestructor.getJavaPlugin(), 20 * 35);

			if (allowWaterProtection) {

				for (int x = bLoc.getBlockX(); x <= bLoc.getBlockX() + 1; x++) {
					for (int y = bLoc.getBlockY(); y <= bLoc.getBlockY() + 1; y++) {
						for (int z = bLoc.getBlockZ(); z <= bLoc.getBlockZ() + 1; z++) {

							Block prot = bLoc.getWorld().getBlockAt(x, y, z);

							if (prot.isLiquid() || bLoc.getBlock().isLiquid()) {
								this.obbyBlocks.remove(b);
								return;
							}
						}
					}
				}
			}

			b.breakNaturally();
			obbyBlocks.remove(b);
		}
	}
}
