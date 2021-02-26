package net.mcreator.boss_tools.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.world.GameType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.Minecraft;

import net.mcreator.boss_tools.world.dimension.UmlaufbahnerdeDimension;
import net.mcreator.boss_tools.world.dimension.OrbitMoonDimension;
import net.mcreator.boss_tools.world.dimension.OrbitMercuryDimension;
import net.mcreator.boss_tools.world.dimension.OrbitMarsDimension;
import net.mcreator.boss_tools.world.dimension.MoonDimension;
import net.mcreator.boss_tools.world.dimension.MercuryDimension;
import net.mcreator.boss_tools.world.dimension.MarsDimension;
import net.mcreator.boss_tools.potion.BulletGeneratorPotion;
import net.mcreator.boss_tools.BossToolsModElements;
import net.mcreator.boss_tools.BossToolsMod;

import java.util.Map;
import java.util.Collection;

@BossToolsModElements.ModElement.Tag
public class SpaceArmorBodyTickEventProcedure extends BossToolsModElements.ModElement {
	public SpaceArmorBodyTickEventProcedure(BossToolsModElements instance) {
		super(instance, 156);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				BossToolsMod.LOGGER.warn("Failed to load dependency entity for procedure SpaceArmorBodyTickEvent!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				BossToolsMod.LOGGER.warn("Failed to load dependency itemstack for procedure SpaceArmorBodyTickEvent!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				BossToolsMod.LOGGER.warn("Failed to load dependency world for procedure SpaceArmorBodyTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!(new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == BulletGeneratorPotion.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity)))) {
			if ((new Object() {
				public boolean checkGamemode(Entity _ent) {
					if (_ent instanceof ServerPlayerEntity) {
						return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.SURVIVAL;
					} else if (_ent instanceof PlayerEntity && _ent.world.isRemote) {
						NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
								.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
						return _npi != null && _npi.getGameType() == GameType.SURVIVAL;
					}
					return false;
				}
			}.checkGamemode(entity))) {
				if ((((world.getDimension().getType().getId()) == (MoonDimension.type.getId())) || (((world.getDimension().getType()
						.getId()) == (MarsDimension.type.getId()))
						|| (((world.getDimension().getType().getId()) == (UmlaufbahnerdeDimension.type.getId()))
								|| (((world.getDimension().getType().getId()) == (MercuryDimension.type.getId()))
										|| (((world.getDimension().getType().getId()) == (OrbitMoonDimension.type.getId()))
												|| (((world.getDimension().getType().getId()) == (OrbitMarsDimension.type.getId()))
														|| ((world.getDimension().getType().getId()) == (OrbitMercuryDimension.type.getId()))))))))) {
					if ((((itemstack).getOrCreateTag().getDouble("Energy")) >= 1)) {
						(itemstack).getOrCreateTag().putDouble("Energytick", (((itemstack).getOrCreateTag().getDouble("Energytick")) + 1));
						if ((((itemstack).getOrCreateTag().getDouble("Energytick")) >= 2)) {
							(itemstack).getOrCreateTag().putDouble("Energy", (((itemstack).getOrCreateTag().getDouble("Energy")) - 1));
							(itemstack).getOrCreateTag().putDouble("Energytick", 0);
						}
						entity.getPersistentData().putBoolean("SpaceSuitC", (true));
					}
				}
				if ((((itemstack).getOrCreateTag().getDouble("Energy")) <= 1)) {
					entity.getPersistentData().putBoolean("SpaceSuitC", (false));
				}
				if ((((itemstack).getOrCreateTag().getDouble("Energy")) == 1000)) {
					if (entity instanceof PlayerEntity && !entity.world.isRemote) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cWARNING! \u00A761000 \u00A79Oxygen!"), (false));
					}
					if (entity instanceof PlayerEntity && !entity.world.isRemote) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cWARNING! \u00A761000 \u00A79Oxygen!"), (true));
					}
				}
				if ((((itemstack).getOrCreateTag().getDouble("Energy")) == 1)) {
					if (entity instanceof PlayerEntity && !entity.world.isRemote) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cWARNING! \u00A760 \u00A79Oxygen!"), (false));
					}
					if (entity instanceof PlayerEntity && !entity.world.isRemote) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cWARNING! \u00A760 \u00A79Oxygen!"), (true));
					}
				}
			}
		}
	}
}
