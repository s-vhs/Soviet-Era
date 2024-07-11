// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


public class boot2s extends EntityModel<Entity> {
	private final ModelRenderer left_boot;
	private final ModelRenderer right_boot;

	public boot2s() {
		textureWidth = 64;
		textureHeight = 64;

		left_boot = new ModelRenderer(this);
		left_boot.setRotationPoint(2.0625F, 16.5F, 1.0F);
		left_boot.setTextureOffset(22, 22).addBox(-2.0625F, 10.0F, -4.5F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		left_boot.setTextureOffset(8, 10).addBox(1.9375F, 10.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
		left_boot.setTextureOffset(16, 22).addBox(1.9375F, 7.0F, -2.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		left_boot.setTextureOffset(18, 15).addBox(-3.5625F, 7.0F, -2.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		left_boot.setTextureOffset(28, 19).addBox(-2.0625F, 8.0F, -3.5F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		left_boot.setTextureOffset(16, 10).addBox(-2.0625F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		left_boot.setTextureOffset(8, 2).addBox(-3.0625F, 10.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
		left_boot.setTextureOffset(28, 9).addBox(-2.0625F, 10.0F, 3.0F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		left_boot.setTextureOffset(0, 23).addBox(-2.0625F, 7.0F, 3.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		left_boot.setTextureOffset(0, 26).addBox(-3.25F, -1.0F, -0.5F, 0.0F, 11.0F, 1.0F, 0.0F, false);
		left_boot.setTextureOffset(14, 24).addBox(3.0F, -1.0F, -0.5F, 0.0F, 11.0F, 1.0F, 0.0F, false);

		right_boot = new ModelRenderer(this);
		right_boot.setRotationPoint(7.0625F, 16.5F, 1.0F);
		right_boot.setTextureOffset(22, 5).addBox(-2.0625F, 10.0F, -4.5F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		right_boot.setTextureOffset(0, 0).addBox(1.9375F, 10.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
		right_boot.setTextureOffset(10, 18).addBox(2.4375F, 7.0F, -2.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		right_boot.setTextureOffset(0, 16).addBox(-3.0625F, 7.0F, -2.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		right_boot.setTextureOffset(28, 0).addBox(-2.0625F, 8.0F, -3.5F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		right_boot.setTextureOffset(16, 0).addBox(-2.0625F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		right_boot.setTextureOffset(0, 8).addBox(-3.0625F, 10.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
		right_boot.setTextureOffset(26, 26).addBox(-2.0625F, 10.0F, 3.0F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		right_boot.setTextureOffset(24, 15).addBox(-2.0625F, 7.0F, 3.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		right_boot.setTextureOffset(12, 24).addBox(-3.125F, -1.0F, -0.5F, 0.0F, 11.0F, 1.0F, 0.0F, false);
		right_boot.setTextureOffset(10, 24).addBox(3.125F, -1.0F, -0.5F, 0.0F, 11.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		left_boot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		right_boot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}