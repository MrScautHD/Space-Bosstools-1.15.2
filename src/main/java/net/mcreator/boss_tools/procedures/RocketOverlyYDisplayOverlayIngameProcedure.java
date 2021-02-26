package net.mcreator.boss_tools.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.entity.Entity;

import net.mcreator.boss_tools.world.dimension.UmlaufbahnerdeDimension;
import net.mcreator.boss_tools.world.dimension.OrbitMoonDimension;
import net.mcreator.boss_tools.world.dimension.OrbitMercuryDimension;
import net.mcreator.boss_tools.world.dimension.OrbitMarsDimension;
import net.mcreator.boss_tools.world.dimension.MoonDimension;
import net.mcreator.boss_tools.world.dimension.MercuryDimension;
import net.mcreator.boss_tools.world.dimension.MarsDimension;
import net.mcreator.boss_tools.entity.RocketTier3Entity;
import net.mcreator.boss_tools.entity.RocketTier2Entity;
import net.mcreator.boss_tools.entity.RocketEntity;
import net.mcreator.boss_tools.BossToolsModElements;
import net.mcreator.boss_tools.BossToolsMod;

import java.util.Map;

@BossToolsModElements.ModElement.Tag
public class RocketOverlyYDisplayOverlayIngameProcedure extends BossToolsModElements.ModElement {
	public RocketOverlyYDisplayOverlayIngameProcedure(BossToolsModElements instance) {
		super(instance, 574);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				BossToolsMod.LOGGER.warn("Failed to load dependency entity for procedure RocketOverlyYDisplayOverlayIngame!");
			return false;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				BossToolsMod.LOGGER.warn("Failed to load dependency world for procedure RocketOverlyYDisplayOverlayIngame!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!((world.getDimension().getType().getId()) == (MoonDimension.type.getId())))) {
			if ((!((world.getDimension().getType().getId()) == (MarsDimension.type.getId())))) {
				if ((!((world.getDimension().getType().getId()) == (UmlaufbahnerdeDimension.type.getId())))) {
					if ((!((world.getDimension().getType().getId()) == (MercuryDimension.type.getId())))) {
						if ((!((world.getDimension().getType().getId()) == (OrbitMoonDimension.type.getId())))) {
							if ((!((world.getDimension().getType().getId()) == (OrbitMarsDimension.type.getId())))) {
								if ((!((world.getDimension().getType().getId()) == (OrbitMercuryDimension.type.getId())))) {
									if ((((entity.getRidingEntity()) instanceof RocketEntity.CustomEntity)
											|| (((entity.getRidingEntity()) instanceof RocketTier2Entity.CustomEntity)
													|| ((entity.getRidingEntity()) instanceof RocketTier3Entity.CustomEntity)))) {
										return (true);
									}
								}
							}
						}
					}
				}
			}
		}
		return (false);
	}
}
