// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


public class ozk_body2 extends EntityModel<Entity> {
	private final ModelRenderer ozk_body;
	private final ModelRenderer ozk_left_sleeve;
	private final ModelRenderer ozk_right_sleeve;

	public ozk_body2() {
		textureWidth = 64;
		textureHeight = 64;

		ozk_body = new ModelRenderer(this);
		ozk_body.setRotationPoint(0.0F, 15.1667F, 1.5F);
		ozk_body.setTextureOffset(0, 13).addBox(-4.0F, 2.8333F, -4.5F, 8.0F, 12.0F, 1.0F, 0.0F, false);
		ozk_body.setTextureOffset(0, 0).addBox(-4.0F, 2.8333F, 0.5F, 8.0F, 12.0F, 1.0F, 0.0F, false);
		ozk_body.setTextureOffset(20, 32).addBox(-4.0F, 2.8333F, 1.5F, 8.0F, 3.0F, 2.0F, 0.0F, false);
		ozk_body.setTextureOffset(38, 26).addBox(-4.0F, 1.8333F, 2.5F, 8.0F, 1.0F, 2.0F, 0.0F, false);
		ozk_body.setTextureOffset(18, 16).addBox(-4.0F, 2.8333F, -3.5F, 1.0F, 12.0F, 4.0F, 0.0F, false);
		ozk_body.setTextureOffset(18, 0).addBox(3.0F, 2.8333F, -3.5F, 1.0F, 12.0F, 4.0F, 0.0F, false);

		ozk_left_sleeve = new ModelRenderer(this);
		ozk_left_sleeve.setRotationPoint(0.0F, 24.0F, 0.0F);
		ozk_left_sleeve.setTextureOffset(38, 13).addBox(4.0F, -6.0F, -3.0F, 4.0F, 12.0F, 1.0F, 0.0F, false);
		ozk_left_sleeve.setTextureOffset(20, 37).addBox(4.0F, -6.0F, 2.0F, 4.0F, 12.0F, 1.0F, 0.0F, false);
		ozk_left_sleeve.setTextureOffset(28, 16).addBox(8.0F, -6.0F, -2.0F, 1.0F, 12.0F, 4.0F, 0.0F, false);
		ozk_left_sleeve.setTextureOffset(10, 28).addBox(3.0F, -6.0F, -2.0F, 1.0F, 12.0F, 4.0F, 0.0F, false);
		ozk_left_sleeve.setTextureOffset(0, 44).addBox(4.0F, -7.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		ozk_left_sleeve.setTextureOffset(40, 39).addBox(4.0F, 6.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);

		ozk_right_sleeve = new ModelRenderer(this);
		ozk_right_sleeve.setRotationPoint(-12.0F, 24.0F, 0.0F);
		ozk_right_sleeve.setTextureOffset(40, 34).addBox(4.0F, -7.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		ozk_right_sleeve.setTextureOffset(38, 29).addBox(4.0F, 6.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		ozk_right_sleeve.setTextureOffset(30, 37).addBox(4.0F, -6.0F, 2.0F, 4.0F, 12.0F, 1.0F, 0.0F, false);
		ozk_right_sleeve.setTextureOffset(0, 26).addBox(3.0F, -6.0F, -2.0F, 1.0F, 12.0F, 4.0F, 0.0F, false);
		ozk_right_sleeve.setTextureOffset(38, 0).addBox(4.0F, -6.0F, -3.0F, 4.0F, 12.0F, 1.0F, 0.0F, false);
		ozk_right_sleeve.setTextureOffset(28, 0).addBox(8.0F, -6.0F, -2.0F, 1.0F, 12.0F, 4.0F, 0.0F, false);
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