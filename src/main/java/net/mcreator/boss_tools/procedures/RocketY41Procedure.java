package net.mcreator.boss_tools.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.boss_tools.entity.RocketTier3Entity;
import net.mcreator.boss_tools.entity.RocketTier2Entity;
import net.mcreator.boss_tools.entity.RocketEntity;
import net.mcreator.boss_tools.BossToolsModElements;
import net.mcreator.boss_tools.BossToolsMod;

import java.util.Map;

@BossToolsModElements.ModElement.Tag
public class RocketY41Procedure extends BossToolsModElements.ModElement {
	public RocketY41Procedure(BossToolsModElements instance) {
		super(instance, 683);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				BossToolsMod.LOGGER.warn("Failed to load dependency entity for procedure RocketY41!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity.getPosY()) >= 400) && ((!((entity.getPosY()) >= 410)) && (((entity.getRidingEntity()) instanceof RocketEntity.CustomEntity)
				|| (((entity.getRidingEntity()) instanceof RocketTier2Entity.CustomEntity)
						|| ((entity.getRidingEntity()) instanceof RocketTier3Entity.CustomEntity)))))) {
			return (true);
		}
		return (false);
	}
}
