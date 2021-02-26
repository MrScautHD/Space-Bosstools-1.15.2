package net.mcreator.boss_tools.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.boss_tools.world.dimension.UmlaufbahnerdeDimension;
import net.mcreator.boss_tools.world.dimension.OrbitMoonDimension;
import net.mcreator.boss_tools.world.dimension.OrbitMercuryDimension;
import net.mcreator.boss_tools.world.dimension.OrbitMarsDimension;
import net.mcreator.boss_tools.world.dimension.MoonDimension;
import net.mcreator.boss_tools.world.dimension.MercuryDimension;
import net.mcreator.boss_tools.world.dimension.MarsDimension;
import net.mcreator.boss_tools.BossToolsModElements;
import net.mcreator.boss_tools.BossToolsMod;

import java.util.Map;

@BossToolsModElements.ModElement.Tag
public class TorchOnBlockRightClickedProcedure extends BossToolsModElements.ModElement {
	public TorchOnBlockRightClickedProcedure(BossToolsModElements instance) {
		super(instance, 167);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				BossToolsMod.LOGGER.warn("Failed to load dependency entity for procedure TorchOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				BossToolsMod.LOGGER.warn("Failed to load dependency x for procedure TorchOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				BossToolsMod.LOGGER.warn("Failed to load dependency y for procedure TorchOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				BossToolsMod.LOGGER.warn("Failed to load dependency z for procedure TorchOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				BossToolsMod.LOGGER.warn("Failed to load dependency world for procedure TorchOnBlockRightClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!(((world.getDimension().getType().getId()) == (MoonDimension.type.getId())) || (((world.getDimension().getType()
				.getId()) == (MarsDimension.type.getId()))
				|| (((world.getDimension().getType().getId()) == (UmlaufbahnerdeDimension.type.getId()))
						|| (((world.getDimension().getType().getId()) == (MercuryDimension.type.getId()))
								|| (((world.getDimension().getType().getId()) == (OrbitMoonDimension.type.getId()))
										|| (((world.getDimension().getType().getId()) == (OrbitMarsDimension.type.getId()))
												|| ((world.getDimension().getType().getId()) == (OrbitMercuryDimension.type.getId())))))))))) {
			if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
					.getItem() == new ItemStack(Items.FLINT_AND_STEEL, (int) (1)).getItem())) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.TORCH.getDefaultState(), 3);
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.flintandsteel.use")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.flintandsteel.use")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
				}
			}
		}
	}
}
