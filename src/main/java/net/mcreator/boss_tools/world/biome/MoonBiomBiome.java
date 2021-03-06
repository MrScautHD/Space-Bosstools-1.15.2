
package net.mcreator.boss_tools.world.biome;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.Biome;

import net.mcreator.boss_tools.block.MoonsandBlock;
import net.mcreator.boss_tools.block.MoonStoneBlock;
import net.mcreator.boss_tools.BossToolsModElements;

@BossToolsModElements.ModElement.Tag
public class MoonBiomBiome extends BossToolsModElements.ModElement {
	@ObjectHolder("boss_tools:moon_biom")
	public static final CustomBiome biome = null;
	public MoonBiomBiome(BossToolsModElements instance) {
		super(instance, 187);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new CustomBiome());
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
	}
	static class CustomBiome extends Biome {
		public CustomBiome() {
			super(new Biome.Builder().downfall(0f).depth(2f).scale(0.02f).temperature(1.6f).precipitation(Biome.RainType.NONE)
					.category(Biome.Category.NONE).waterColor(4159204).waterFogColor(329011).parent("boss_tools:moon_biom")
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(MoonsandBlock.block.getDefaultState(),
							MoonStoneBlock.block.getDefaultState(), MoonStoneBlock.block.getDefaultState())));
			setRegistryName("moon_biom");
			DefaultBiomeFeatures.addCarvers(this);
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getGrassColor(double posX, double posZ) {
			return -16724992;
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getFoliageColor() {
			return -16724992;
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getSkyColor() {
			return -16777216;
		}
	}
}
