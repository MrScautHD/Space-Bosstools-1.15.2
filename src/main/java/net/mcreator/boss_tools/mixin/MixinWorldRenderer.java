
package net.mcreator.boss_tools.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.dimension.DimensionType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.Minecraft;

import net.mcreator.boss_tools.world.dimension.UmlaufbahnerdeDimension;
import net.mcreator.boss_tools.world.dimension.OrbitMoonDimension;
import net.mcreator.boss_tools.world.dimension.OrbitMercuryDimension;
import net.mcreator.boss_tools.world.dimension.OrbitMarsDimension;
import net.mcreator.boss_tools.world.dimension.MoonDimension;
import net.mcreator.boss_tools.world.dimension.MercuryDimension;
import net.mcreator.boss_tools.world.dimension.MarsDimension;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.matrix.MatrixStack;

@Mixin(WorldRenderer.class)
public class MixinWorldRenderer {
	private static final ResourceLocation SUN_TEXTUREST = new ResourceLocation("boss_tools", "textures/sky/sun.png");
	// private static final ResourceLocation EARTH_TEXTURES = new
	// ResourceLocation("boss_tools", "textures/environment/earth.png");
	// private static final ResourceLocation PHOBOS_TEXTURES = new
	// ResourceLocation("boss_tools", "textures/environment/phobos.png");
	private static final ResourceLocation MOON_TEXTUREST = new ResourceLocation("boss_tools", "textures/sky/moon.png");
	private static final ResourceLocation EARTH = new ResourceLocation("boss_tools", "textures/sky/earth.png");
	private static final ResourceLocation EARTH_LIGHT_TEXTURES = new ResourceLocation("boss_tools", "textures/sky/earth_light.png");
	private static final ResourceLocation PHOBOS = new ResourceLocation("boss_tools", "textures/sky/phobos.png");
	private static final ResourceLocation DEIMOS = new ResourceLocation("boss_tools", "textures/sky/deimos.png");
	private static final ResourceLocation MARS = new ResourceLocation("boss_tools", "textures/sky/mars.png");
	private static final ResourceLocation PLUTO = new ResourceLocation("boss_tools", "textures/sky/mercury.png");
	// private static final ResourceLocation MARS_TEXTURES = new
	// ResourceLocation("boss_tools", "textures/environment/mars.png");
	// private static final ResourceLocation PLUTO_TEXTURES = new
	// ResourceLocation("boss_tools", "textures/environment/pluto.png");
	private static final ResourceLocation SKY_TEXTURE = new ResourceLocation("boss_tools", "textures/sky/sky.png");
	private Minecraft mc = Minecraft.getInstance();
	@Inject(at = @At("HEAD"), method = "Lnet/minecraft/client/renderer/WorldRenderer;renderSky(Lcom/mojang/blaze3d/matrix/MatrixStack;F)V")
	public void renderSky(MatrixStack matrixStackIn, float partialTicks, CallbackInfo ci) {
		DimensionType dimensionType = Minecraft.getInstance().world.dimension.getType();
		if (dimensionType == MoonDimension.type) {
			this.renderSkyMoon(matrixStackIn, partialTicks);
		}
		if (dimensionType == MarsDimension.type) {
			renderMarsSky(matrixStackIn, partialTicks);
		}
		if (dimensionType == MercuryDimension.type) {
			renderSkyMercury(matrixStackIn, partialTicks);
		}
		if (dimensionType == UmlaufbahnerdeDimension.type) {
			renderSkyOverworldOrbit(matrixStackIn, partialTicks);
		}
		if (dimensionType == OrbitMoonDimension.type) {
			renderSkyMoonOrbit(matrixStackIn, partialTicks);
		}
		if (dimensionType == OrbitMarsDimension.type) {
			renderSkyMarsOrbit(matrixStackIn, partialTicks);
		}
		if (dimensionType == OrbitMercuryDimension.type) {
			renderSkyMercuryOrbit(matrixStackIn, partialTicks);
		}
	}

	private void renderSkyMoon(MatrixStack matrixStack, float partialTicks) {
		ClientWorld world = mc.world;
		RenderSystem.disableTexture();
		Vec3d vector3d = world.getSkyColor(mc.gameRenderer.getActiveRenderInfo().getBlockPos(), partialTicks);
		float f = (float) vector3d.x;
		float f1 = (float) vector3d.y;
		float f2 = (float) vector3d.z;
		FogRenderer.applyFog();
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		RenderSystem.depthMask(false);
		RenderSystem.enableFog();
		RenderSystem.color3f(f, f1, f2);
		mc.worldRenderer.skyVBO.bindBuffer();
		mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
		mc.worldRenderer.skyVBO.draw(matrixStack.getLast().getMatrix(), 7);
		VertexBuffer.unbindBuffer();
		mc.worldRenderer.skyVertexFormat.clearBufferState();
		Matrix4f matrix4f1 = matrixStack.getLast().getMatrix();
		RenderSystem.enableAlphaTest();
		RenderSystem.enableTexture();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		mc.getTextureManager().bindTexture(SKY_TEXTURE);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -100, 8f, -100).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 100, 8f, -100).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 100, 8f, 100).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -100, 8f, 100).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		RenderSystem.disableTexture();
		RenderSystem.disableFog();
		RenderSystem.disableAlphaTest();
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		float[] afloat = world.dimension.calcSunriseSunsetColors(world.getCelestialAngleRadians(partialTicks), partialTicks);
		if (afloat != null) {
			RenderSystem.disableTexture();
			RenderSystem.shadeModel(7425);
			matrixStack.push();
			matrixStack.rotate(Vector3f.XP.rotationDegrees(90.0F));
			float f3 = MathHelper.sin(world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F;
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(f3));
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
			float f4 = afloat[0];
			float f5 = afloat[1];
			float f6 = afloat[2];
			Matrix4f matrix4f = matrixStack.getLast().getMatrix();
			bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
			bufferbuilder.pos(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();
			for (int j = 0; j <= 16; ++j) {
				float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
				float f8 = MathHelper.sin(f7);
				float f9 = MathHelper.cos(f7);
				bufferbuilder.pos(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F)
						.endVertex();
			}
			bufferbuilder.finishDrawing();
			WorldVertexBufferUploader.draw(bufferbuilder);
			matrixStack.pop();
			RenderSystem.shadeModel(7424);
		}
		RenderSystem.enableTexture();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		matrixStack.push();
		float f11 = 1.0F - world.getRainStrength(partialTicks);// Rrain basiss ist es auf
		// 1.0F
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, f11);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
		matrixStack.rotate(Vector3f.XP.rotationDegrees(world.getCelestialAngleRadians(partialTicks) * 60.0F)); // in
																												// 1.16.4
																												// 360
																												// in
																												// 1.15.2
																												// 60
		matrix4f1 = matrixStack.getLast().getMatrix();
		float f12 = 30.0F;
		mc.getTextureManager().bindTexture(SUN_TEXTUREST);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -f12, 100.0F, -f12).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, f12, 100.0F, -f12).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, f12, 100.0F, f12).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -f12, 100.0F, f12).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		f12 = 30.0F;
		mc.getTextureManager().bindTexture(EARTH);
		int k = world.getMoonPhase();
		int l = k % 4;
		int i1 = k / 4 % 2;
		float f13 = (float) (l + 0) / 4.0F;
		float f14 = (float) (i1 + 0) / 2.0F;
		float f15 = (float) (l + 1) / 4.0F;
		float f16 = (float) (i1 + 1) / 2.0F;
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -9, -100.0F, 9).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 9, -100.0F, 9).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 9, -100.0F, -9).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -9, -100.0F, -9).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		mc.getTextureManager().bindTexture(EARTH_LIGHT_TEXTURES);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -25, -100.0F, 25).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 25, -100.0F, 25).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 25, -100.0F, -25).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -25, -100.0F, -25).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		RenderSystem.disableTexture();
		// f11 = 1000.0F;// Star Brightness
		// float f10 = world.getStarBrightness(partialTicks) * f11;
		// f11
		float f10 = 1.0F;
		if (f10 > 0.0F) {
			RenderSystem.color4f(f10, f10, f10, f10);
			mc.worldRenderer.starVBO.bindBuffer();
			mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
			mc.worldRenderer.starVBO.draw(matrixStack.getLast().getMatrix(), 7);
			VertexBuffer.unbindBuffer();
			mc.worldRenderer.skyVertexFormat.clearBufferState();
		}
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.disableBlend();
		RenderSystem.enableAlphaTest();
		RenderSystem.enableFog();
		matrixStack.pop();
		RenderSystem.disableTexture();
		RenderSystem.color3f(0.0F, 0.0F, 0.0F);
		double d0 = 2.0F;// mc.player.getEyePosition(partialTicks).y -
							// world.getWorldInfo().getVoidFogHeight();
		if (d0 < 0.0D) {
			matrixStack.push();
			matrixStack.translate(0.0D, 12.0D, 0.0D);
			mc.worldRenderer.sky2VBO.bindBuffer();
			mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
			mc.worldRenderer.sky2VBO.draw(matrixStack.getLast().getMatrix(), 7);
			VertexBuffer.unbindBuffer();
			mc.worldRenderer.skyVertexFormat.clearBufferState();
			matrixStack.pop();
		}
		// if (world.func_239132_a_().func_239216_b_()) {
		RenderSystem.color3f(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
		/*
		 * } else { RenderSystem.color3f(f, f1, f2); }
		 */
		RenderSystem.enableTexture();
		RenderSystem.depthMask(true);
		RenderSystem.disableFog();
	}

	// mars
	private void renderMarsSky(MatrixStack matrixStack, float partialTicks) {
		ClientWorld world = mc.world;
		RenderSystem.disableTexture();
		Vec3d vector3d = world.getSkyColor(mc.gameRenderer.getActiveRenderInfo().getBlockPos(), partialTicks);
		float f = (float) vector3d.x;
		float f1 = (float) vector3d.y;
		float f2 = (float) vector3d.z;
		FogRenderer.applyFog();
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		RenderSystem.depthMask(false);
		RenderSystem.enableFog();
		RenderSystem.color3f(f, f1, f2);
		mc.worldRenderer.skyVBO.bindBuffer();
		mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
		mc.worldRenderer.skyVBO.draw(matrixStack.getLast().getMatrix(), 7);
		VertexBuffer.unbindBuffer();
		mc.worldRenderer.skyVertexFormat.clearBufferState();
		Matrix4f matrix4f1 = matrixStack.getLast().getMatrix();
		RenderSystem.enableAlphaTest();
		RenderSystem.enableTexture();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		mc.getTextureManager().bindTexture(SKY_TEXTURE);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -100, 8f, -100).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 100, 8f, -100).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 100, 8f, 100).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -100, 8f, 100).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		RenderSystem.disableTexture();
		RenderSystem.disableFog();
		RenderSystem.disableAlphaTest();
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		float[] afloat = world.dimension.calcSunriseSunsetColors(world.getCelestialAngleRadians(partialTicks), partialTicks);
		if (afloat != null) {
			RenderSystem.disableTexture();
			RenderSystem.shadeModel(7425);
			matrixStack.push();
			matrixStack.rotate(Vector3f.XP.rotationDegrees(90.0F));
			float f3 = MathHelper.sin(world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F;
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(f3));
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
			float f4 = afloat[0];
			float f5 = afloat[1];
			float f6 = afloat[2];
			Matrix4f matrix4f = matrixStack.getLast().getMatrix();
			bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
			bufferbuilder.pos(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();
			for (int j = 0; j <= 16; ++j) {
				float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
				float f8 = MathHelper.sin(f7);
				float f9 = MathHelper.cos(f7);
				bufferbuilder.pos(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F)
						.endVertex();
			}
			bufferbuilder.finishDrawing();
			WorldVertexBufferUploader.draw(bufferbuilder);
			matrixStack.pop();
			RenderSystem.shadeModel(7424);
		}
		RenderSystem.enableTexture();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		matrixStack.push();
		float f11 = 1.0F - world.getRainStrength(partialTicks);// Rrain basiss ist es auf
		// 1.0F
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, f11);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
		matrixStack.rotate(Vector3f.XP.rotationDegrees(world.getCelestialAngleRadians(partialTicks) * 60.0F)); // in
																												// 1.16.4
																												// 360
																												// in
																												// 1.15.2
																												// 60
		matrix4f1 = matrixStack.getLast().getMatrix();
		float f12 = 30.0F;
		mc.getTextureManager().bindTexture(SUN_TEXTUREST);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -f12, 100.0F, -f12).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, f12, 100.0F, -f12).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, f12, 100.0F, f12).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -f12, 100.0F, f12).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		f12 = 30.0F;
		/*
		 * mc.getTextureManager().bindTexture(EARTH); int k = world.getMoonPhase(); int
		 * l = k % 4; int i1 = k / 4 % 2; float f13 = (float) (l + 0) / 4.0F; float f14
		 * = (float) (i1 + 0) / 2.0F; float f15 = (float) (l + 1) / 4.0F; float f16 =
		 * (float) (i1 + 1) / 2.0F; bufferbuilder.begin(7,
		 * DefaultVertexFormats.POSITION_TEX); bufferbuilder.pos(matrix4f1, -9, -100.0F,
		 * 9).tex(0.0F, 0.0F).endVertex(); bufferbuilder.pos(matrix4f1, 9, -100.0F,
		 * 9).tex(1.0F, 0.0F).endVertex(); bufferbuilder.pos(matrix4f1, 9, -100.0F,
		 * -9).tex(1.0F, 1.0F).endVertex(); bufferbuilder.pos(matrix4f1, -9, -100.0F,
		 * -9).tex(0.0F, 1.0F).endVertex(); bufferbuilder.finishDrawing();
		 * WorldVertexBufferUploader.draw(bufferbuilder);
		 */
		mc.getTextureManager().bindTexture(PHOBOS);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(-130.0F));
		matrixStack.rotate(Vector3f.ZP.rotationDegrees(100.0F));
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -3, -100.0F, 3).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 3, -100.0F, 3).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 3, -100.0F, -3).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -3, -100.0F, -3).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		mc.getTextureManager().bindTexture(EARTH);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(-130.0F));
		matrixStack.rotate(Vector3f.ZP.rotationDegrees(210.0F));
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -1, -100.0F, 1).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 1, -100.0F, 1).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 1, -100.0F, -1).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -1, -100.0F, -1).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		mc.getTextureManager().bindTexture(DEIMOS);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(-110.0F));
		matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -4, -100.0F, 4).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 4, -100.0F, 4).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 4, -100.0F, -4).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -4, -100.0F, -4).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		RenderSystem.disableTexture();
		// f11 = 1000.0F;// Star Brightness
		// float f10 = world.getStarBrightness(partialTicks) * f11;
		// f11
		float f10 = 1.0F;
		if (f10 > 0.0F) {
			RenderSystem.color4f(f10, f10, f10, f10);
			mc.worldRenderer.starVBO.bindBuffer();
			mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
			mc.worldRenderer.starVBO.draw(matrixStack.getLast().getMatrix(), 7);
			VertexBuffer.unbindBuffer();
			mc.worldRenderer.skyVertexFormat.clearBufferState();
		}
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.disableBlend();
		RenderSystem.enableAlphaTest();
		RenderSystem.enableFog();
		matrixStack.pop();
		RenderSystem.disableTexture();
		RenderSystem.color3f(0.0F, 0.0F, 0.0F);
		double d0 = 2.0F;// mc.player.getEyePosition(partialTicks).y -
							// world.getWorldInfo().getVoidFogHeight();
		if (d0 < 0.0D) {
			matrixStack.push();
			matrixStack.translate(0.0D, 12.0D, 0.0D);
			mc.worldRenderer.sky2VBO.bindBuffer();
			mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
			mc.worldRenderer.sky2VBO.draw(matrixStack.getLast().getMatrix(), 7);
			VertexBuffer.unbindBuffer();
			mc.worldRenderer.skyVertexFormat.clearBufferState();
			matrixStack.pop();
		}
		// if (world.func_239132_a_().func_239216_b_()) {
		RenderSystem.color3f(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
		/*
		 * } else { RenderSystem.color3f(f, f1, f2); }
		 */
		RenderSystem.enableTexture();
		RenderSystem.depthMask(true);
		RenderSystem.disableFog();
	}

	// Mercury
	private void renderSkyMercury(MatrixStack matrixStack, float partialTicks) {
		ClientWorld world = mc.world;
		RenderSystem.disableTexture();
		Vec3d vector3d = world.getSkyColor(mc.gameRenderer.getActiveRenderInfo().getBlockPos(), partialTicks);
		float f = (float) vector3d.x;
		float f1 = (float) vector3d.y;
		float f2 = (float) vector3d.z;
		FogRenderer.applyFog();
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		RenderSystem.depthMask(false);
		RenderSystem.enableFog();
		RenderSystem.color3f(f, f1, f2);
		mc.worldRenderer.skyVBO.bindBuffer();
		mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
		mc.worldRenderer.skyVBO.draw(matrixStack.getLast().getMatrix(), 7);
		VertexBuffer.unbindBuffer();
		mc.worldRenderer.skyVertexFormat.clearBufferState();
		Matrix4f matrix4f1 = matrixStack.getLast().getMatrix();
		RenderSystem.enableAlphaTest();
		RenderSystem.enableTexture();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		mc.getTextureManager().bindTexture(SKY_TEXTURE);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -100, 8f, -100).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 100, 8f, -100).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 100, 8f, 100).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -100, 8f, 100).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		RenderSystem.disableTexture();
		RenderSystem.disableFog();
		RenderSystem.disableAlphaTest();
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		float[] afloat = world.dimension.calcSunriseSunsetColors(world.getCelestialAngleRadians(partialTicks), partialTicks);
		if (afloat != null) {
			RenderSystem.disableTexture();
			RenderSystem.shadeModel(7425);
			matrixStack.push();
			matrixStack.rotate(Vector3f.XP.rotationDegrees(90.0F));
			float f3 = MathHelper.sin(world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F;
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(f3));
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
			float f4 = afloat[0];
			float f5 = afloat[1];
			float f6 = afloat[2];
			Matrix4f matrix4f = matrixStack.getLast().getMatrix();
			bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
			bufferbuilder.pos(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();
			for (int j = 0; j <= 16; ++j) {
				float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
				float f8 = MathHelper.sin(f7);
				float f9 = MathHelper.cos(f7);
				bufferbuilder.pos(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F)
						.endVertex();
			}
			bufferbuilder.finishDrawing();
			WorldVertexBufferUploader.draw(bufferbuilder);
			matrixStack.pop();
			RenderSystem.shadeModel(7424);
		}
		RenderSystem.enableTexture();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		matrixStack.push();
		float f11 = 1.0F - world.getRainStrength(partialTicks);// Rrain basiss ist es auf
		// 1.0F
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, f11);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
		matrixStack.rotate(Vector3f.XP.rotationDegrees(world.getCelestialAngleRadians(partialTicks) * 60.0F)); // in
																												// 1.16.4
																												// 360
																												// in
																												// 1.15.2
																												// 60
		matrix4f1 = matrixStack.getLast().getMatrix();
		float f12 = 30.0F;
		mc.getTextureManager().bindTexture(SUN_TEXTUREST);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -60, 100.0F, -60).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 60, 100.0F, -60).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 60, 100.0F, 60).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -60, 100.0F, 60).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		f12 = 30.0F;
		mc.getTextureManager().bindTexture(SKY_TEXTURE);
		int k = world.getMoonPhase();
		int l = k % 4;
		int i1 = k / 4 % 2;
		float f13 = (float) (l + 0) / 4.0F;
		float f14 = (float) (i1 + 0) / 2.0F;
		float f15 = (float) (l + 1) / 4.0F;
		float f16 = (float) (i1 + 1) / 2.0F;
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -9, -100.0F, 9).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 9, -100.0F, 9).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 9, -100.0F, -9).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -9, -100.0F, -9).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		RenderSystem.disableTexture();
		// f11 = 1000.0F;// Star Brightness
		// float f10 = world.getStarBrightness(partialTicks) * f11;
		// f11
		float f10 = 1.0F;
		if (f10 > 0.0F) {
			RenderSystem.color4f(f10, f10, f10, f10);
			mc.worldRenderer.starVBO.bindBuffer();
			mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
			mc.worldRenderer.starVBO.draw(matrixStack.getLast().getMatrix(), 7);
			VertexBuffer.unbindBuffer();
			mc.worldRenderer.skyVertexFormat.clearBufferState();
		}
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.disableBlend();
		RenderSystem.enableAlphaTest();
		RenderSystem.enableFog();
		matrixStack.pop();
		RenderSystem.disableTexture();
		RenderSystem.color3f(0.0F, 0.0F, 0.0F);
		double d0 = 2.0F;// mc.player.getEyePosition(partialTicks).y -
							// world.getWorldInfo().getVoidFogHeight();
		if (d0 < 0.0D) {
			matrixStack.push();
			matrixStack.translate(0.0D, 12.0D, 0.0D);
			mc.worldRenderer.sky2VBO.bindBuffer();
			mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
			mc.worldRenderer.sky2VBO.draw(matrixStack.getLast().getMatrix(), 7);
			VertexBuffer.unbindBuffer();
			mc.worldRenderer.skyVertexFormat.clearBufferState();
			matrixStack.pop();
		}
		// if (world.func_239132_a_().func_239216_b_()) {
		RenderSystem.color3f(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
		/*
		 * } else { RenderSystem.color3f(f, f1, f2); }
		 */
		RenderSystem.enableTexture();
		RenderSystem.depthMask(true);
		RenderSystem.disableFog();
	}

	// Overworld Orbit
	private void renderSkyOverworldOrbit(MatrixStack matrixStack, float partialTicks) {
		ClientWorld world = mc.world;
		RenderSystem.disableTexture();
		Vec3d vector3d = world.getSkyColor(mc.gameRenderer.getActiveRenderInfo().getBlockPos(), partialTicks);
		float f = (float) vector3d.x;
		float f1 = (float) vector3d.y;
		float f2 = (float) vector3d.z;
		FogRenderer.applyFog();
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		RenderSystem.depthMask(false);
		RenderSystem.enableFog();
		RenderSystem.color3f(f, f1, f2);
		mc.worldRenderer.skyVBO.bindBuffer();
		mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
		mc.worldRenderer.skyVBO.draw(matrixStack.getLast().getMatrix(), 7);
		VertexBuffer.unbindBuffer();
		mc.worldRenderer.skyVertexFormat.clearBufferState();
		Matrix4f matrix4f1 = matrixStack.getLast().getMatrix();
		RenderSystem.enableAlphaTest();
		RenderSystem.enableTexture();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		mc.getTextureManager().bindTexture(SKY_TEXTURE);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -100, 8f, -100).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 100, 8f, -100).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 100, 8f, 100).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -100, 8f, 100).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		RenderSystem.disableTexture();
		RenderSystem.disableFog();
		RenderSystem.disableAlphaTest();
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		float[] afloat = world.dimension.calcSunriseSunsetColors(world.getCelestialAngleRadians(partialTicks), partialTicks);
		if (afloat != null) {
			RenderSystem.disableTexture();
			RenderSystem.shadeModel(7425);
			matrixStack.push();
			matrixStack.rotate(Vector3f.XP.rotationDegrees(90.0F));
			float f3 = MathHelper.sin(world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F;
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(f3));
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
			float f4 = afloat[0];
			float f5 = afloat[1];
			float f6 = afloat[2];
			Matrix4f matrix4f = matrixStack.getLast().getMatrix();
			bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
			bufferbuilder.pos(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();
			for (int j = 0; j <= 16; ++j) {
				float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
				float f8 = MathHelper.sin(f7);
				float f9 = MathHelper.cos(f7);
				bufferbuilder.pos(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F)
						.endVertex();
			}
			bufferbuilder.finishDrawing();
			WorldVertexBufferUploader.draw(bufferbuilder);
			matrixStack.pop();
			RenderSystem.shadeModel(7424);
		}
		RenderSystem.enableTexture();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		matrixStack.push();
		float f11 = 1.0F - world.getRainStrength(partialTicks);// Rrain basiss ist es auf
		// 1.0F
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, f11);
		// main Planet
		matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
		matrixStack.rotate(Vector3f.XP.rotationDegrees(0.0F /* world.func_242415_f(partialTicks) * 360.0F */));
		matrix4f1 = matrixStack.getLast().getMatrix();
		mc.getTextureManager().bindTexture(EARTH);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		float f17 = (float) mc.player.getEyePosition(partialTicks).y /*- world.getWorldInfo().getVoidFogHeight()*/;
		bufferbuilder.pos(matrix4f1, -40.0F, -f17 - 18.0F, 40.0F).tex(0.0F, 0.0F).endVertex(); // 350 is nice but fps xD
		bufferbuilder.pos(matrix4f1, 40.0F, -f17 - 18.0F, 40.0F).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 40.0F, -f17 - 18.0F, -40.0F).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -40.0F, -f17 - 18.0F, -40.0F).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
		matrixStack.rotate(Vector3f.XP.rotationDegrees(world.getCelestialAngleRadians(partialTicks) * 60.0F)); // in
																												// 1.16.4
																												// 360
																												// in
																												// 1.15.2
																												// 60
		matrix4f1 = matrixStack.getLast().getMatrix();
		float f12 = 30.0F;
		mc.getTextureManager().bindTexture(SUN_TEXTUREST);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -f12, 100.0F, -f12).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, f12, 100.0F, -f12).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, f12, 100.0F, f12).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -f12, 100.0F, f12).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		f12 = 30.0F;
		mc.getTextureManager().bindTexture(MOON_TEXTUREST);
		int k = world.getMoonPhase();
		int l = k % 4;
		int i1 = k / 4 % 2;
		float f13 = (float) (l + 0) / 4.0F;
		float f14 = (float) (i1 + 0) / 2.0F;
		float f15 = (float) (l + 1) / 4.0F;
		float f16 = (float) (i1 + 1) / 2.0F;
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -9, -100.0F, 9).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 9, -100.0F, 9).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 9, -100.0F, -9).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -9, -100.0F, -9).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		// matrixStack.rotate(Vector3f.XP.rotationDegrees(world.getCelestialAngleRadians(partialTicks)
		// * 60.0F));
		RenderSystem.disableTexture();
		// f11 = 1000.0F;// Star Brightness
		// float f10 = world.getStarBrightness(partialTicks) * f11;
		// f11
		float f10 = 1.0F;
		if (f10 > 0.0F) {
			RenderSystem.color4f(f10, f10, f10, f10);
			mc.worldRenderer.starVBO.bindBuffer();
			mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
			mc.worldRenderer.starVBO.draw(matrixStack.getLast().getMatrix(), 7);
			VertexBuffer.unbindBuffer();
			mc.worldRenderer.skyVertexFormat.clearBufferState();
		}
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.disableBlend();
		RenderSystem.enableAlphaTest();
		RenderSystem.enableFog();
		matrixStack.pop();
		RenderSystem.disableTexture();
		RenderSystem.color3f(0.0F, 0.0F, 0.0F);
		double d0 = 2.0F;// mc.player.getEyePosition(partialTicks).y -
							// world.getWorldInfo().getVoidFogHeight();
		if (d0 < 0.0D) {
			matrixStack.push();
			matrixStack.translate(0.0D, 12.0D, 0.0D);
			mc.worldRenderer.sky2VBO.bindBuffer();
			mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
			mc.worldRenderer.sky2VBO.draw(matrixStack.getLast().getMatrix(), 7);
			VertexBuffer.unbindBuffer();
			mc.worldRenderer.skyVertexFormat.clearBufferState();
			matrixStack.pop();
		}
		// if (world.func_239132_a_().func_239216_b_()) {
		RenderSystem.color3f(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
		/*
		 * } else { RenderSystem.color3f(f, f1, f2); }
		 */
		RenderSystem.enableTexture();
		RenderSystem.depthMask(true);
		RenderSystem.disableFog();
	}

	// Moon Orbit
	private void renderSkyMoonOrbit(MatrixStack matrixStack, float partialTicks) {
		ClientWorld world = mc.world;
		RenderSystem.disableTexture();
		Vec3d vector3d = world.getSkyColor(mc.gameRenderer.getActiveRenderInfo().getBlockPos(), partialTicks);
		float f = (float) vector3d.x;
		float f1 = (float) vector3d.y;
		float f2 = (float) vector3d.z;
		FogRenderer.applyFog();
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		RenderSystem.depthMask(false);
		RenderSystem.enableFog();
		RenderSystem.color3f(f, f1, f2);
		mc.worldRenderer.skyVBO.bindBuffer();
		mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
		mc.worldRenderer.skyVBO.draw(matrixStack.getLast().getMatrix(), 7);
		VertexBuffer.unbindBuffer();
		mc.worldRenderer.skyVertexFormat.clearBufferState();
		Matrix4f matrix4f1 = matrixStack.getLast().getMatrix();
		RenderSystem.enableAlphaTest();
		RenderSystem.enableTexture();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		mc.getTextureManager().bindTexture(SKY_TEXTURE);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -100, 8f, -100).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 100, 8f, -100).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 100, 8f, 100).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -100, 8f, 100).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		RenderSystem.disableTexture();
		RenderSystem.disableFog();
		RenderSystem.disableAlphaTest();
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		float[] afloat = world.dimension.calcSunriseSunsetColors(world.getCelestialAngleRadians(partialTicks), partialTicks);
		if (afloat != null) {
			RenderSystem.disableTexture();
			RenderSystem.shadeModel(7425);
			matrixStack.push();
			matrixStack.rotate(Vector3f.XP.rotationDegrees(90.0F));
			float f3 = MathHelper.sin(world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F;
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(f3));
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
			float f4 = afloat[0];
			float f5 = afloat[1];
			float f6 = afloat[2];
			Matrix4f matrix4f = matrixStack.getLast().getMatrix();
			bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
			bufferbuilder.pos(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();
			for (int j = 0; j <= 16; ++j) {
				float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
				float f8 = MathHelper.sin(f7);
				float f9 = MathHelper.cos(f7);
				bufferbuilder.pos(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F)
						.endVertex();
			}
			bufferbuilder.finishDrawing();
			WorldVertexBufferUploader.draw(bufferbuilder);
			matrixStack.pop();
			RenderSystem.shadeModel(7424);
		}
		RenderSystem.enableTexture();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		matrixStack.push();
		float f11 = 1.0F - world.getRainStrength(partialTicks);// Rrain basiss ist es auf
		// 1.0F
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, f11);
		// main Planet
		matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
		matrixStack.rotate(Vector3f.XP.rotationDegrees(0.0F /* world.func_242415_f(partialTicks) * 360.0F */));
		matrix4f1 = matrixStack.getLast().getMatrix();
		mc.getTextureManager().bindTexture(MOON_TEXTUREST);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		float f17 = (float) mc.player.getEyePosition(partialTicks).y /*- world.getWorldInfo().getVoidFogHeight()*/;
		bufferbuilder.pos(matrix4f1, -25.0F, -f17 - 18.0F, 25.0F).tex(0.0F, 0.0F).endVertex(); // 350 is nice but fps xD
		bufferbuilder.pos(matrix4f1, 25.0F, -f17 - 18.0F, 25.0F).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 25.0F, -f17 - 18.0F, -25.0F).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -25.0F, -f17 - 18.0F, -25.0F).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
		matrixStack.rotate(Vector3f.XP.rotationDegrees(world.getCelestialAngleRadians(partialTicks) * 60.0F)); // in
																												// 1.16.4
																												// 360
																												// in
																												// 1.15.2
																												// 60
		matrix4f1 = matrixStack.getLast().getMatrix();
		float f12 = 30.0F;
		mc.getTextureManager().bindTexture(SUN_TEXTUREST);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -f12, 100.0F, -f12).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, f12, 100.0F, -f12).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, f12, 100.0F, f12).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -f12, 100.0F, f12).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		f12 = 30.0F;
		mc.getTextureManager().bindTexture(SKY_TEXTURE);
		int k = world.getMoonPhase();
		int l = k % 4;
		int i1 = k / 4 % 2;
		float f13 = (float) (l + 0) / 4.0F;
		float f14 = (float) (i1 + 0) / 2.0F;
		float f15 = (float) (l + 1) / 4.0F;
		float f16 = (float) (i1 + 1) / 2.0F;
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -9, -100.0F, 9).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 9, -100.0F, 9).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 9, -100.0F, -9).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -9, -100.0F, -9).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		// matrixStack.rotate(Vector3f.XP.rotationDegrees(world.getCelestialAngleRadians(partialTicks)
		// * 60.0F));
		RenderSystem.disableTexture();
		// f11 = 1000.0F;// Star Brightness
		// float f10 = world.getStarBrightness(partialTicks) * f11;
		// f11
		float f10 = 1.0F;
		if (f10 > 0.0F) {
			RenderSystem.color4f(f10, f10, f10, f10);
			mc.worldRenderer.starVBO.bindBuffer();
			mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
			mc.worldRenderer.starVBO.draw(matrixStack.getLast().getMatrix(), 7);
			VertexBuffer.unbindBuffer();
			mc.worldRenderer.skyVertexFormat.clearBufferState();
		}
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.disableBlend();
		RenderSystem.enableAlphaTest();
		RenderSystem.enableFog();
		matrixStack.pop();
		RenderSystem.disableTexture();
		RenderSystem.color3f(0.0F, 0.0F, 0.0F);
		double d0 = 2.0F;// mc.player.getEyePosition(partialTicks).y -
							// world.getWorldInfo().getVoidFogHeight();
		if (d0 < 0.0D) {
			matrixStack.push();
			matrixStack.translate(0.0D, 12.0D, 0.0D);
			mc.worldRenderer.sky2VBO.bindBuffer();
			mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
			mc.worldRenderer.sky2VBO.draw(matrixStack.getLast().getMatrix(), 7);
			VertexBuffer.unbindBuffer();
			mc.worldRenderer.skyVertexFormat.clearBufferState();
			matrixStack.pop();
		}
		// if (world.func_239132_a_().func_239216_b_()) {
		RenderSystem.color3f(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
		/*
		 * } else { RenderSystem.color3f(f, f1, f2); }
		 */
		RenderSystem.enableTexture();
		RenderSystem.depthMask(true);
		RenderSystem.disableFog();
	}

	// Orbit Mars
	private void renderSkyMarsOrbit(MatrixStack matrixStack, float partialTicks) {
		ClientWorld world = mc.world;
		RenderSystem.disableTexture();
		Vec3d vector3d = world.getSkyColor(mc.gameRenderer.getActiveRenderInfo().getBlockPos(), partialTicks);
		float f = (float) vector3d.x;
		float f1 = (float) vector3d.y;
		float f2 = (float) vector3d.z;
		FogRenderer.applyFog();
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		RenderSystem.depthMask(false);
		RenderSystem.enableFog();
		RenderSystem.color3f(f, f1, f2);
		mc.worldRenderer.skyVBO.bindBuffer();
		mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
		mc.worldRenderer.skyVBO.draw(matrixStack.getLast().getMatrix(), 7);
		VertexBuffer.unbindBuffer();
		mc.worldRenderer.skyVertexFormat.clearBufferState();
		Matrix4f matrix4f1 = matrixStack.getLast().getMatrix();
		RenderSystem.enableAlphaTest();
		RenderSystem.enableTexture();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		mc.getTextureManager().bindTexture(SKY_TEXTURE);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -100, 8f, -100).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 100, 8f, -100).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 100, 8f, 100).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -100, 8f, 100).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		RenderSystem.disableTexture();
		RenderSystem.disableFog();
		RenderSystem.disableAlphaTest();
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		float[] afloat = world.dimension.calcSunriseSunsetColors(world.getCelestialAngleRadians(partialTicks), partialTicks);
		if (afloat != null) {
			RenderSystem.disableTexture();
			RenderSystem.shadeModel(7425);
			matrixStack.push();
			matrixStack.rotate(Vector3f.XP.rotationDegrees(90.0F));
			float f3 = MathHelper.sin(world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F;
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(f3));
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
			float f4 = afloat[0];
			float f5 = afloat[1];
			float f6 = afloat[2];
			Matrix4f matrix4f = matrixStack.getLast().getMatrix();
			bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
			bufferbuilder.pos(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();
			for (int j = 0; j <= 16; ++j) {
				float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
				float f8 = MathHelper.sin(f7);
				float f9 = MathHelper.cos(f7);
				bufferbuilder.pos(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F)
						.endVertex();
			}
			bufferbuilder.finishDrawing();
			WorldVertexBufferUploader.draw(bufferbuilder);
			matrixStack.pop();
			RenderSystem.shadeModel(7424);
		}
		RenderSystem.enableTexture();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		matrixStack.push();
		float f11 = 1.0F - world.getRainStrength(partialTicks);// Rrain basiss ist es auf
		// 1.0F
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, f11);
		// main Planet
		matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
		matrixStack.rotate(Vector3f.XP.rotationDegrees(0.0F /* world.func_242415_f(partialTicks) * 360.0F */));
		matrix4f1 = matrixStack.getLast().getMatrix();
		mc.getTextureManager().bindTexture(MARS);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		float f17 = (float) mc.player.getEyePosition(partialTicks).y /*- world.getWorldInfo().getVoidFogHeight()*/;
		bufferbuilder.pos(matrix4f1, -45.0F, -f17 - 18.0F, 45.0F).tex(0.0F, 0.0F).endVertex(); // 350 is nice but fps xD
		bufferbuilder.pos(matrix4f1, 45.0F, -f17 - 18.0F, 45.0F).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 45.0F, -f17 - 18.0F, -45.0F).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -45.0F, -f17 - 18.0F, -45.0F).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
		matrixStack.rotate(Vector3f.XP.rotationDegrees(world.getCelestialAngleRadians(partialTicks) * 60.0F)); // in
																												// 1.16.4
																												// 360
																												// in
																												// 1.15.2
																												// 60
		matrix4f1 = matrixStack.getLast().getMatrix();
		float f12 = 30.0F;
		mc.getTextureManager().bindTexture(SUN_TEXTUREST);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -f12, 100.0F, -f12).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, f12, 100.0F, -f12).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, f12, 100.0F, f12).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -f12, 100.0F, f12).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		f12 = 30.0F;
		mc.getTextureManager().bindTexture(SKY_TEXTURE);
		int k = world.getMoonPhase();
		int l = k % 4;
		int i1 = k / 4 % 2;
		float f13 = (float) (l + 0) / 4.0F;
		float f14 = (float) (i1 + 0) / 2.0F;
		float f15 = (float) (l + 1) / 4.0F;
		float f16 = (float) (i1 + 1) / 2.0F;
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -9, -100.0F, 9).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 9, -100.0F, 9).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 9, -100.0F, -9).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -9, -100.0F, -9).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		// matrixStack.rotate(Vector3f.XP.rotationDegrees(world.getCelestialAngleRadians(partialTicks)
		// * 60.0F));
		RenderSystem.disableTexture();
		// f11 = 1000.0F;// Star Brightness
		// float f10 = world.getStarBrightness(partialTicks) * f11;
		// f11
		float f10 = 1.0F;
		if (f10 > 0.0F) {
			RenderSystem.color4f(f10, f10, f10, f10);
			mc.worldRenderer.starVBO.bindBuffer();
			mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
			mc.worldRenderer.starVBO.draw(matrixStack.getLast().getMatrix(), 7);
			VertexBuffer.unbindBuffer();
			mc.worldRenderer.skyVertexFormat.clearBufferState();
		}
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.disableBlend();
		RenderSystem.enableAlphaTest();
		RenderSystem.enableFog();
		matrixStack.pop();
		RenderSystem.disableTexture();
		RenderSystem.color3f(0.0F, 0.0F, 0.0F);
		double d0 = 2.0F;// mc.player.getEyePosition(partialTicks).y -
							// world.getWorldInfo().getVoidFogHeight();
		if (d0 < 0.0D) {
			matrixStack.push();
			matrixStack.translate(0.0D, 12.0D, 0.0D);
			mc.worldRenderer.sky2VBO.bindBuffer();
			mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
			mc.worldRenderer.sky2VBO.draw(matrixStack.getLast().getMatrix(), 7);
			VertexBuffer.unbindBuffer();
			mc.worldRenderer.skyVertexFormat.clearBufferState();
			matrixStack.pop();
		}
		// if (world.func_239132_a_().func_239216_b_()) {
		RenderSystem.color3f(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
		/*
		 * } else { RenderSystem.color3f(f, f1, f2); }
		 */
		RenderSystem.enableTexture();
		RenderSystem.depthMask(true);
		RenderSystem.disableFog();
	}

	// Orbit Mercury
	private void renderSkyMercuryOrbit(MatrixStack matrixStack, float partialTicks) {
		ClientWorld world = mc.world;
		RenderSystem.disableTexture();
		Vec3d vector3d = world.getSkyColor(mc.gameRenderer.getActiveRenderInfo().getBlockPos(), partialTicks);
		float f = (float) vector3d.x;
		float f1 = (float) vector3d.y;
		float f2 = (float) vector3d.z;
		FogRenderer.applyFog();
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		RenderSystem.depthMask(false);
		RenderSystem.enableFog();
		RenderSystem.color3f(f, f1, f2);
		mc.worldRenderer.skyVBO.bindBuffer();
		mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
		mc.worldRenderer.skyVBO.draw(matrixStack.getLast().getMatrix(), 7);
		VertexBuffer.unbindBuffer();
		mc.worldRenderer.skyVertexFormat.clearBufferState();
		Matrix4f matrix4f1 = matrixStack.getLast().getMatrix();
		RenderSystem.enableAlphaTest();
		RenderSystem.enableTexture();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		mc.getTextureManager().bindTexture(SKY_TEXTURE);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -100, 8f, -100).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 100, 8f, -100).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 100, 8f, 100).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -100, 8f, 100).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		RenderSystem.disableTexture();
		RenderSystem.disableFog();
		RenderSystem.disableAlphaTest();
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		float[] afloat = world.dimension.calcSunriseSunsetColors(world.getCelestialAngleRadians(partialTicks), partialTicks);
		if (afloat != null) {
			RenderSystem.disableTexture();
			RenderSystem.shadeModel(7425);
			matrixStack.push();
			matrixStack.rotate(Vector3f.XP.rotationDegrees(90.0F));
			float f3 = MathHelper.sin(world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F;
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(f3));
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
			float f4 = afloat[0];
			float f5 = afloat[1];
			float f6 = afloat[2];
			Matrix4f matrix4f = matrixStack.getLast().getMatrix();
			bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
			bufferbuilder.pos(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();
			for (int j = 0; j <= 16; ++j) {
				float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
				float f8 = MathHelper.sin(f7);
				float f9 = MathHelper.cos(f7);
				bufferbuilder.pos(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F)
						.endVertex();
			}
			bufferbuilder.finishDrawing();
			WorldVertexBufferUploader.draw(bufferbuilder);
			matrixStack.pop();
			RenderSystem.shadeModel(7424);
		}
		RenderSystem.enableTexture();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		matrixStack.push();
		float f11 = 1.0F - world.getRainStrength(partialTicks);// Rrain basiss ist es auf
		// 1.0F
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, f11);
		// main Planet
		matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
		matrixStack.rotate(Vector3f.XP.rotationDegrees(0.0F /* world.func_242415_f(partialTicks) * 360.0F */));
		matrix4f1 = matrixStack.getLast().getMatrix();
		mc.getTextureManager().bindTexture(PLUTO);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		float f17 = (float) mc.player.getEyePosition(partialTicks).y /*- world.getWorldInfo().getVoidFogHeight()*/;
		bufferbuilder.pos(matrix4f1, -45.0F, -f17 - 18.0F, 45.0F).tex(0.0F, 0.0F).endVertex(); // 350 is nice but fps xD
		bufferbuilder.pos(matrix4f1, 45.0F, -f17 - 18.0F, 45.0F).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 45.0F, -f17 - 18.0F, -45.0F).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -45.0F, -f17 - 18.0F, -45.0F).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
		matrixStack.rotate(Vector3f.XP.rotationDegrees(world.getCelestialAngleRadians(partialTicks) * 60.0F)); // in
																												// 1.16.4
																												// 360
																												// in
																												// 1.15.2
																												// 60
		matrix4f1 = matrixStack.getLast().getMatrix();
		float f12 = 45.0F;
		mc.getTextureManager().bindTexture(SUN_TEXTUREST);
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -f12, 100.0F, -f12).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, f12, 100.0F, -f12).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, f12, 100.0F, f12).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -f12, 100.0F, f12).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		f12 = 30.0F;
		mc.getTextureManager().bindTexture(SKY_TEXTURE);
		int k = world.getMoonPhase();
		int l = k % 4;
		int i1 = k / 4 % 2;
		float f13 = (float) (l + 0) / 4.0F;
		float f14 = (float) (i1 + 0) / 2.0F;
		float f15 = (float) (l + 1) / 4.0F;
		float f16 = (float) (i1 + 1) / 2.0F;
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(matrix4f1, -9, -100.0F, 9).tex(0.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 9, -100.0F, 9).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(matrix4f1, 9, -100.0F, -9).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(matrix4f1, -9, -100.0F, -9).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		// matrixStack.rotate(Vector3f.XP.rotationDegrees(world.getCelestialAngleRadians(partialTicks)
		// * 60.0F));
		RenderSystem.disableTexture();
		// f11 = 1000.0F;// Star Brightness
		// float f10 = world.getStarBrightness(partialTicks) * f11;
		// f11
		float f10 = 1.0F;
		if (f10 > 0.0F) {
			RenderSystem.color4f(f10, f10, f10, f10);
			mc.worldRenderer.starVBO.bindBuffer();
			mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
			mc.worldRenderer.starVBO.draw(matrixStack.getLast().getMatrix(), 7);
			VertexBuffer.unbindBuffer();
			mc.worldRenderer.skyVertexFormat.clearBufferState();
		}
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.disableBlend();
		RenderSystem.enableAlphaTest();
		RenderSystem.enableFog();
		matrixStack.pop();
		RenderSystem.disableTexture();
		RenderSystem.color3f(0.0F, 0.0F, 0.0F);
		double d0 = 2.0F;// mc.player.getEyePosition(partialTicks).y -
							// world.getWorldInfo().getVoidFogHeight();
		if (d0 < 0.0D) {
			matrixStack.push();
			matrixStack.translate(0.0D, 12.0D, 0.0D);
			mc.worldRenderer.sky2VBO.bindBuffer();
			mc.worldRenderer.skyVertexFormat.setupBufferState(0L);
			mc.worldRenderer.sky2VBO.draw(matrixStack.getLast().getMatrix(), 7);
			VertexBuffer.unbindBuffer();
			mc.worldRenderer.skyVertexFormat.clearBufferState();
			matrixStack.pop();
		}
		// if (world.func_239132_a_().func_239216_b_()) {
		RenderSystem.color3f(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
		/*
		 * } else { RenderSystem.color3f(f, f1, f2); }
		 */
		RenderSystem.enableTexture();
		RenderSystem.depthMask(true);
		RenderSystem.disableFog();
	}
}
