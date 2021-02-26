
package net.mcreator.boss_tools.painting;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.entity.item.PaintingType;

import net.mcreator.boss_tools.BossToolsModElements;

@BossToolsModElements.ModElement.Tag
public class PaintingplanetmarthPainting extends BossToolsModElements.ModElement {
	public PaintingplanetmarthPainting(BossToolsModElements instance) {
		super(instance, 564);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerPaintingType(RegistryEvent.Register<PaintingType> event) {
		event.getRegistry().register(new PaintingType(64, 32).setRegistryName("paintingplanetmarth"));
	}
}
