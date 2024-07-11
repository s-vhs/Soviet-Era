// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


public class CustomModel extends EntityModel<Entity> {
	private final ModelRenderer ozk_body;
	private final ModelRenderer ozk_left_sleeve;
	private final ModelRenderer ozk_right_sleeve;

	public CustomModel() {
		textureWidth = 64;
		textureHeight = 64;

		ozk_body = new ModelRenderer(this);
		ozk_body.setRotationPoint(9.0F, 23.5F, -8.0F);
		ozk_body.setTextureOffset(0, 13).addBox(-12.0F, -23.5F, 5.0F, 8.0F, 12.0F, 1.0F, 0.0F, false);
		ozk_body.setTextureOffset(0, 0).addBox(-12.0F, -23.5F, 10.0F, 8.0F, 12.0F, 1.0F, 0.0F, false);
		ozk_body.setTextureOffset(18, 16).addBox(-12.0F, -23.5F, 11.0F, 8.0F, 3.0F, 2.0F, 0.0F, false);
		ozk_body.setTextureOffset(24, 0).addBox(-12.0F, -24.5F, 12.0F, 8.0F, 1.0F, 2.0F, 0.0F, false);

		ozk_left_sleeve = new ModelRenderer(this);
		ozk_left_sleeve.setRotationPoint(0.0F, 24.0F, 0.0F);
		ozk_left_sleeve.setTextureOffset(18, 21).addBox(5.0F, -24.0F, -3.0F, 4.0F, 12.0F, 1.0F, 0.0F, false);
		ozk_left_sleeve.setTextureOffset(18, 21).addBox(-7.0F, -24.0F, -3.0F, 4.0F, 12.0F, 1.0F, 0.0F, false);
		ozk_left_sleeve.setTextureOffset(18, 21).addBox(-7.0F, -24.0F, 2.0F, 4.0F, 12.0F, 1.0F, 0.0F, false);
		ozk_left_sleeve.setTextureOffset(18, 21).addBox(5.0F, -24.0F, 2.0F, 4.0F, 12.0F, 1.0F, 0.0F, false);
		ozk_left_sleeve.setTextureOffset(18, 0).addBox(9.0F, -24.0F, -2.0F, 1.0F, 12.0F, 4.0F, 0.0F, false);
		ozk_left_sleeve.setTextureOffset(18, 0).addBox(4.0F, -24.0F, -2.0F, 1.0F, 12.0F, 4.0F, 0.0F, false);
		ozk_left_sleeve.setTextureOffset(18, 0).addBox(-3.0F, -24.0F, -2.0F, 1.0F, 12.0F, 4.0F, 0.0F, false);
		ozk_left_sleeve.setTextureOffset(18, 0).addBox(-8.0F, -24.0F, -2.0F, 1.0F, 12.0F, 4.0F, 0.0F, false);
		ozk_left_sleeve.setTextureOffset(28, 21).addBox(5.0F, -25.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		ozk_left_sleeve.setTextureOffset(28, 8).addBox(5.0F, -12.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);

		ozk_right_sleeve = new ModelRenderer(this);
		ozk_right_sleeve.setRotationPoint(-12.0F, 24.0F, 0.0F);
		ozk_right_sleeve.setTextureOffset(28, 3).addBox(5.0F, -25.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		ozk_right_sleeve.setTextureOffset(0, 26).addBox(5.0F, -12.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		ozk_body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		ozk_left_sleeve.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		ozk_right_sleeve.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}