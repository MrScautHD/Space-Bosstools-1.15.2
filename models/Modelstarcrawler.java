// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelstarcrawler extends EntityModel<Entity> {
	private final ModelRenderer Body;
	private final ModelRenderer arm1g;
	private final ModelRenderer Arm1;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer Limb1;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer Hand1;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer arm2g;
	private final ModelRenderer Arm2;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer Limb2;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer Hand2;
	private final ModelRenderer cube_r11;
	private final ModelRenderer cube_r12;
	private final ModelRenderer arm3g;
	private final ModelRenderer Arm3;
	private final ModelRenderer cube_r13;
	private final ModelRenderer cube_r14;
	private final ModelRenderer Limb3;
	private final ModelRenderer cube_r15;
	private final ModelRenderer cube_r16;
	private final ModelRenderer Hand3;
	private final ModelRenderer cube_r17;
	private final ModelRenderer cube_r18;
	private final ModelRenderer arm4g;
	private final ModelRenderer Arm4;
	private final ModelRenderer cube_r19;
	private final ModelRenderer cube_r20;
	private final ModelRenderer Limb4;
	private final ModelRenderer cube_r21;
	private final ModelRenderer cube_r22;
	private final ModelRenderer Hand4;
	private final ModelRenderer cube_r23;
	private final ModelRenderer cube_r24;

	public Modelstarcrawler() {
		textureWidth = 128;
		textureHeight = 128;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.setTextureOffset(0, 0).addBox(-8.0F, -13.0F, -8.0F, 16.0F, 10.0F, 16.0F, 0.0F, false);
		Body.setTextureOffset(0, 26).addBox(-7.0F, -9.0F, -7.0F, 14.0F, 9.0F, 14.0F, 0.0F, false);

		arm1g = new ModelRenderer(this);
		arm1g.setRotationPoint(0.0F, 18.3F, 6.75F);

		Arm1 = new ModelRenderer(this);
		Arm1.setRotationPoint(0.0F, 0.0F, 0.0F);
		arm1g.addChild(Arm1);
		Arm1.setTextureOffset(48, 48).addBox(-6.0F, -5.2F, 0.25F, 12.0F, 8.0F, 8.0F, 0.0F, false);
		Arm1.setTextureOffset(42, 26).addBox(-5.0F, 2.8F, -1.75F, 10.0F, 3.0F, 9.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(-5.0F, 3.0F, 4.25F);
		Arm1.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 3.1416F, -0.3491F);
		cube_r1.setTextureOffset(48, 64).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(5.0F, 3.0F, 3.25F);
		Arm1.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.3491F);
		cube_r2.setTextureOffset(58, 64).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		Limb1 = new ModelRenderer(this);
		Limb1.setRotationPoint(0.0F, 0.0F, 7.0F);
		arm1g.addChild(Limb1);
		Limb1.setTextureOffset(48, 0).addBox(-5.0F, -4.3F, 0.25F, 10.0F, 7.0F, 9.0F, 0.0F, false);
		Limb1.setTextureOffset(0, 64).addBox(-4.0F, 2.7F, -0.75F, 8.0F, 3.0F, 9.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-4.0F, 3.0F, 5.25F);
		Limb1.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 3.1416F, -0.3491F);
		cube_r3.setTextureOffset(59, 38).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(4.0F, 3.0F, 4.25F);
		Limb1.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.3491F);
		cube_r4.setTextureOffset(57, 16).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		Hand1 = new ModelRenderer(this);
		Hand1.setRotationPoint(0.0F, 0.1F, 15.0F);
		arm1g.addChild(Hand1);
		Hand1.setTextureOffset(0, 49).addBox(-4.0F, -3.3F, 0.25F, 8.0F, 6.0F, 9.0F, 0.0F, false);
		Hand1.setTextureOffset(25, 55).addBox(-3.0F, 2.7F, -0.75F, 6.0F, 3.0F, 9.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(-3.0F, 2.9F, 5.25F);
		Hand1.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 3.1416F, -0.3491F);
		cube_r5.setTextureOffset(39, 49).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(3.0F, 2.9F, 4.25F);
		Hand1.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.3491F);
		cube_r6.setTextureOffset(49, 38).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		arm2g = new ModelRenderer(this);
		arm2g.setRotationPoint(0.0F, 18.4F, -7.75F);

		Arm2 = new ModelRenderer(this);
		Arm2.setRotationPoint(0.0F, -0.1F, 1.0F);
		arm2g.addChild(Arm2);
		setRotationAngle(Arm2, 0.0F, 3.1416F, 0.0F);
		Arm2.setTextureOffset(48, 48).addBox(-6.0F, -5.2F, 0.25F, 12.0F, 8.0F, 8.0F, 0.0F, false);
		Arm2.setTextureOffset(42, 26).addBox(-5.0F, 2.8F, -1.75F, 10.0F, 3.0F, 9.0F, 0.0F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(-5.0F, 3.0F, 4.25F);
		Arm2.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 3.1416F, -0.3491F);
		cube_r7.setTextureOffset(48, 64).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(5.0F, 3.0F, 3.25F);
		Arm2.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, 0.3491F);
		cube_r8.setTextureOffset(58, 64).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		Limb2 = new ModelRenderer(this);
		Limb2.setRotationPoint(0.0F, -0.1F, -6.0F);
		arm2g.addChild(Limb2);
		setRotationAngle(Limb2, 0.0F, 3.1416F, 0.0F);
		Limb2.setTextureOffset(48, 0).addBox(-5.0F, -4.3F, 0.25F, 10.0F, 7.0F, 9.0F, 0.0F, false);
		Limb2.setTextureOffset(0, 64).addBox(-4.0F, 2.7F, -0.75F, 8.0F, 3.0F, 9.0F, 0.0F, false);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(-4.0F, 3.0F, 5.25F);
		Limb2.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 3.1416F, -0.3491F);
		cube_r9.setTextureOffset(59, 38).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(4.0F, 3.0F, 4.25F);
		Limb2.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, 0.3491F);
		cube_r10.setTextureOffset(57, 16).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		Hand2 = new ModelRenderer(this);
		Hand2.setRotationPoint(0.0F, 0.0F, -19.0F);
		arm2g.addChild(Hand2);
		Hand2.setTextureOffset(0, 49).addBox(-4.0F, -3.3F, -4.75F, 8.0F, 6.0F, 9.0F, 0.0F, false);
		Hand2.setTextureOffset(25, 55).addBox(-3.0F, 2.7F, -3.75F, 6.0F, 3.0F, 9.0F, 0.0F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(-3.0F, 2.9F, 0.25F);
		Hand2.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 3.1416F, -0.3491F);
		cube_r11.setTextureOffset(39, 49).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		cube_r12 = new ModelRenderer(this);
		cube_r12.setRotationPoint(3.0F, 2.9F, -0.75F);
		Hand2.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.0F, 0.0F, 0.3491F);
		cube_r12.setTextureOffset(49, 38).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		arm3g = new ModelRenderer(this);
		arm3g.setRotationPoint(-7.0F, 24.0F, 0.0F);

		Arm3 = new ModelRenderer(this);
		Arm3.setRotationPoint(0.25F, -5.7F, 0.0F);
		arm3g.addChild(Arm3);
		setRotationAngle(Arm3, 0.0F, -1.5708F, 0.0F);
		Arm3.setTextureOffset(48, 48).addBox(-6.0F, -5.2F, 0.25F, 12.0F, 8.0F, 8.0F, 0.0F, false);
		Arm3.setTextureOffset(42, 26).addBox(-5.0F, 2.8F, -1.75F, 10.0F, 3.0F, 9.0F, 0.0F, false);

		cube_r13 = new ModelRenderer(this);
		cube_r13.setRotationPoint(-5.0F, 3.0F, 4.25F);
		Arm3.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.0F, 3.1416F, -0.3491F);
		cube_r13.setTextureOffset(48, 64).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		cube_r14 = new ModelRenderer(this);
		cube_r14.setRotationPoint(5.0F, 3.0F, 3.25F);
		Arm3.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.0F, 0.0F, 0.3491F);
		cube_r14.setTextureOffset(58, 64).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		Limb3 = new ModelRenderer(this);
		Limb3.setRotationPoint(-6.75F, -5.7F, 0.0F);
		arm3g.addChild(Limb3);
		setRotationAngle(Limb3, 0.0F, -1.5708F, 0.0F);
		Limb3.setTextureOffset(48, 0).addBox(-5.0F, -4.3F, 0.25F, 10.0F, 7.0F, 9.0F, 0.0F, false);
		Limb3.setTextureOffset(0, 64).addBox(-4.0F, 2.7F, -0.75F, 8.0F, 3.0F, 9.0F, 0.0F, false);

		cube_r15 = new ModelRenderer(this);
		cube_r15.setRotationPoint(-4.0F, 3.0F, 5.25F);
		Limb3.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.0F, 3.1416F, -0.3491F);
		cube_r15.setTextureOffset(59, 38).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		cube_r16 = new ModelRenderer(this);
		cube_r16.setRotationPoint(4.0F, 3.0F, 4.25F);
		Limb3.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.0F, 0.0F, 0.3491F);
		cube_r16.setTextureOffset(57, 16).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		Hand3 = new ModelRenderer(this);
		Hand3.setRotationPoint(-14.75F, -5.6F, 0.0F);
		arm3g.addChild(Hand3);
		setRotationAngle(Hand3, 0.0F, -1.5708F, 0.0F);
		Hand3.setTextureOffset(0, 49).addBox(-4.0F, -3.3F, 0.25F, 8.0F, 6.0F, 9.0F, 0.0F, false);
		Hand3.setTextureOffset(25, 55).addBox(-3.0F, 2.7F, -0.75F, 6.0F, 3.0F, 9.0F, 0.0F, false);

		cube_r17 = new ModelRenderer(this);
		cube_r17.setRotationPoint(-3.0F, 2.9F, 5.25F);
		Hand3.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.0F, 3.1416F, -0.3491F);
		cube_r17.setTextureOffset(39, 49).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		cube_r18 = new ModelRenderer(this);
		cube_r18.setRotationPoint(3.0F, 2.9F, 4.25F);
		Hand3.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.0F, 0.0F, 0.3491F);
		cube_r18.setTextureOffset(49, 38).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		arm4g = new ModelRenderer(this);
		arm4g.setRotationPoint(8.0F, 24.0F, 0.0F);

		Arm4 = new ModelRenderer(this);
		Arm4.setRotationPoint(-1.25F, -5.7F, 0.0F);
		arm4g.addChild(Arm4);
		setRotationAngle(Arm4, 0.0F, 1.5708F, 0.0F);
		Arm4.setTextureOffset(48, 48).addBox(-6.0F, -5.2F, 0.25F, 12.0F, 8.0F, 8.0F, 0.0F, false);
		Arm4.setTextureOffset(42, 26).addBox(-5.0F, 2.8F, -1.75F, 10.0F, 3.0F, 9.0F, 0.0F, false);

		cube_r19 = new ModelRenderer(this);
		cube_r19.setRotationPoint(-5.0F, 3.0F, 4.25F);
		Arm4.addChild(cube_r19);
		setRotationAngle(cube_r19, 0.0F, 3.1416F, -0.3491F);
		cube_r19.setTextureOffset(48, 64).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		cube_r20 = new ModelRenderer(this);
		cube_r20.setRotationPoint(5.0F, 3.0F, 3.25F);
		Arm4.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.0F, 0.0F, 0.3491F);
		cube_r20.setTextureOffset(58, 64).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		Limb4 = new ModelRenderer(this);
		Limb4.setRotationPoint(5.75F, -5.7F, 0.0F);
		arm4g.addChild(Limb4);
		setRotationAngle(Limb4, 0.0F, 1.5708F, 0.0F);
		Limb4.setTextureOffset(48, 0).addBox(-5.0F, -4.3F, 0.25F, 10.0F, 7.0F, 9.0F, 0.0F, false);
		Limb4.setTextureOffset(0, 64).addBox(-4.0F, 2.7F, -0.75F, 8.0F, 3.0F, 9.0F, 0.0F, false);

		cube_r21 = new ModelRenderer(this);
		cube_r21.setRotationPoint(-4.0F, 3.0F, 5.25F);
		Limb4.addChild(cube_r21);
		setRotationAngle(cube_r21, 0.0F, 3.1416F, -0.3491F);
		cube_r21.setTextureOffset(59, 38).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		cube_r22 = new ModelRenderer(this);
		cube_r22.setRotationPoint(4.0F, 3.0F, 4.25F);
		Limb4.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.0F, 0.0F, 0.3491F);
		cube_r22.setTextureOffset(57, 16).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		Hand4 = new ModelRenderer(this);
		Hand4.setRotationPoint(13.75F, -5.6F, 0.0F);
		arm4g.addChild(Hand4);
		setRotationAngle(Hand4, 0.0F, 1.5708F, 0.0F);
		Hand4.setTextureOffset(0, 49).addBox(-4.0F, -3.3F, 0.25F, 8.0F, 6.0F, 9.0F, 0.0F, false);
		Hand4.setTextureOffset(25, 55).addBox(-3.0F, 2.7F, -0.75F, 6.0F, 3.0F, 9.0F, 0.0F, false);

		cube_r23 = new ModelRenderer(this);
		cube_r23.setRotationPoint(-3.0F, 2.9F, 5.25F);
		Hand4.addChild(cube_r23);
		setRotationAngle(cube_r23, 0.0F, 3.1416F, -0.3491F);
		cube_r23.setTextureOffset(39, 49).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);

		cube_r24 = new ModelRenderer(this);
		cube_r24.setRotationPoint(3.0F, 2.9F, 4.25F);
		Hand4.addChild(cube_r24);
		setRotationAngle(cube_r24, 0.0F, 0.0F, 0.3491F);
		cube_r24.setTextureOffset(49, 38).addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
		arm1g.render(matrixStack, buffer, packedLight, packedOverlay);
		arm2g.render(matrixStack, buffer, packedLight, packedOverlay);
		arm3g.render(matrixStack, buffer, packedLight, packedOverlay);
		arm4g.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}