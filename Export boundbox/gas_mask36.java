// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


public class gas_mask extends EntityModel<Entity> {
	private final ModelRenderer mask;
	private final ModelRenderer cube_r1;

	public gas_mask() {
		textureWidth = 64;
		textureHeight = 64;

		mask = new ModelRenderer(this);
		mask.setRotationPoint(0.0F, 10.15F, -3.425F);
		mask.setTextureOffset(11, 21).addBox(-3.0F, -4.65F, -3.075F, 1.0F, 1.0F, 9.0F, 0.0F, false);
		mask.setTextureOffset(22, 6).addBox(-3.0F, -3.65F, -4.075F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(22, 4).addBox(-3.0F, -2.65F, -4.075F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(22, 8).addBox(2.0F, -3.65F, -4.075F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(0, 20).addBox(2.0F, -4.65F, -3.075F, 1.0F, 1.0F, 9.0F, 0.0F, false);
		mask.setTextureOffset(11, 11).addBox(4.0F, -1.65F, -3.075F, 1.0F, 1.0F, 9.0F, 0.0F, false);
		mask.setTextureOffset(0, 20).addBox(2.0F, -3.65F, 4.925F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(3, 22).addBox(3.0F, -1.65F, 4.925F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(22, 11).addBox(-2.0F, -1.65F, 4.925F, 4.0F, 4.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(15, 21).addBox(3.0F, 2.35F, 4.925F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(0, 0).addBox(4.0F, 2.35F, -3.075F, 1.0F, 1.0F, 9.0F, 0.0F, false);
		mask.setTextureOffset(3, 17).addBox(2.0F, 1.35F, 4.925F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(16, 6).addBox(-3.0F, 1.35F, 4.925F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(11, 21).addBox(-4.0F, 2.35F, 4.925F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(0, 10).addBox(-5.0F, 2.35F, -3.075F, 1.0F, 1.0F, 9.0F, 0.0F, false);
		mask.setTextureOffset(11, 13).addBox(-4.0F, -1.65F, -4.075F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(10, -1).addBox(-1.0F, -1.65F, -5.075F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		mask.setTextureOffset(0, 14).addBox(3.0F, -1.65F, -4.075F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(11, 7).addBox(1.0F, -1.65F, -4.075F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(4, 14).addBox(3.0F, 1.35F, -4.075F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(11, 1).addBox(-5.0F, -1.65F, -3.075F, 1.0F, 1.0F, 9.0F, 0.0F, false);
		mask.setTextureOffset(14, 16).addBox(-3.0F, -3.65F, 4.925F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(4, 20).addBox(-4.0F, -1.65F, 4.925F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(15, 13).addBox(-4.0F, 1.35F, -4.075F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(22, 0).addBox(-3.0F, 1.35F, -4.075F, 6.0F, 3.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(12, 5).addBox(-1.0F, 0.35F, -5.2F, 2.0F, 2.0F, 0.0F, 0.0F, false);
		mask.setTextureOffset(0, 10).addBox(-1.0F, 3.35F, -6.075F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		mask.setTextureOffset(11, 11).addBox(-3.0F, -1.65F, -4.075F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		mask.setTextureOffset(28, 16).addBox(1.0F, -0.65F, -3.6375F, 2.0F, 2.0F, 0.0F, 0.0F, false);
		mask.setTextureOffset(28, 16).addBox(-3.0F, -0.65F, -3.6375F, 2.0F, 2.0F, 0.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(3.5581F, 2.6F, -4.3098F);
		mask.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.2182F, -0.7854F, 0.0F);
		cube_r1.setTextureOffset(16, 3).addBox(-0.375F, -0.25F, -0.75F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		cube_r1.setTextureOffset(0, 4).addBox(-0.875F, -0.75F, -0.25F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r1.setTextureOffset(0, 0).addBox(-1.625F, -1.25F, -1.5F, 3.0F, 3.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		mask.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}