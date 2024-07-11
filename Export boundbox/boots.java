// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


public class CustomModel extends EntityModel<Entity> {
	private final ModelRenderer left_boot;
	private final ModelRenderer right_boot;

	public CustomModel() {
		textureWidth = 64;
		textureHeight = 64;

		left_boot = new ModelRenderer(this);
		left_boot.setRotationPoint(2.0625F, 16.5F, 1.0F);
		left_boot.setTextureOffset(16, 21).addBox(-5.0625F, 5.5F, -4.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		left_boot.setTextureOffset(14, 14).addBox(-1.0625F, 5.5F, -3.0F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		left_boot.setTextureOffset(21, 8).addBox(-1.0625F, 2.5F, -2.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		left_boot.setTextureOffset(6, 20).addBox(-6.0625F, 2.5F, -2.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		left_boot.setTextureOffset(27, 6).addBox(-5.0625F, 3.5F, -3.0F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		left_boot.setTextureOffset(0, 5).addBox(-5.0625F, -5.5F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		left_boot.setTextureOffset(7, 12).addBox(-6.0625F, 5.5F, -3.0F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		left_boot.setTextureOffset(6, 27).addBox(-5.0625F, 5.5F, 2.0F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		left_boot.setTextureOffset(12, 0).addBox(-5.0625F, 2.5F, 2.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		left_boot.setTextureOffset(16, 24).addBox(-6.125F, -5.5F, -0.5F, 0.0F, 11.0F, 1.0F, 0.0F, false);
		left_boot.setTextureOffset(4, 23).addBox(0.0F, -5.5F, -0.5F, 0.0F, 11.0F, 1.0F, 0.0F, false);

		right_boot = new ModelRenderer(this);
		right_boot.setRotationPoint(7.0625F, 16.5F, 1.0F);
		right_boot.setTextureOffset(21, 15).addBox(-5.0625F, 5.5F, -4.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		right_boot.setTextureOffset(11, 5).addBox(-1.0625F, 5.5F, -3.0F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		right_boot.setTextureOffset(18, 0).addBox(-1.0625F, 2.5F, -2.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		right_boot.setTextureOffset(0, 17).addBox(-6.0625F, 2.5F, -2.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		right_boot.setTextureOffset(26, 19).addBox(-5.0625F, 3.5F, -3.0F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		right_boot.setTextureOffset(0, 0).addBox(-5.0625F, -5.5F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		right_boot.setTextureOffset(0, 10).addBox(-6.0625F, 5.5F, -3.0F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		right_boot.setTextureOffset(18, 25).addBox(-5.0625F, 5.5F, 2.0F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		right_boot.setTextureOffset(24, 0).addBox(-5.0625F, 2.5F, 2.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		right_boot.setTextureOffset(2, 23).addBox(-6.125F, -5.5F, -0.5F, 0.0F, 11.0F, 1.0F, 0.0F, false);
		right_boot.setTextureOffset(0, 23).addBox(0.0F, -5.5F, -0.5F, 0.0F, 11.0F, 1.0F, 0.0F, false);
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