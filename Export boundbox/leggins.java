// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


public class CustomModel extends EntityModel<Entity> {
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;

	public CustomModel() {
		textureWidth = 64;
		textureHeight = 64;

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(-2.0F, 24.0F, 2.0F);
		left_leg.setTextureOffset(30, 0).addBox(-2.0F, -12.0F, -2.0F, 4.0F, 12.0F, 1.0F, 0.0F, false);
		left_leg.setTextureOffset(0, 16).addBox(2.0F, -12.0F, -1.0F, 1.0F, 12.0F, 4.0F, 0.0F, false);
		left_leg.setTextureOffset(20, 26).addBox(-2.0F, -12.0F, 3.0F, 4.0F, 12.0F, 1.0F, 0.0F, false);
		left_leg.setTextureOffset(10, 16).addBox(-3.0F, -12.0F, -1.0F, 1.0F, 12.0F, 4.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(3.0F, 24.0F, 2.0F);
		right_leg.setTextureOffset(20, 13).addBox(-2.0F, -12.0F, -2.0F, 4.0F, 12.0F, 1.0F, 0.0F, false);
		right_leg.setTextureOffset(10, 0).addBox(2.0F, -12.0F, -1.0F, 1.0F, 12.0F, 4.0F, 0.0F, false);
		right_leg.setTextureOffset(20, 0).addBox(-2.0F, -12.0F, 3.0F, 4.0F, 12.0F, 1.0F, 0.0F, false);
		right_leg.setTextureOffset(0, 0).addBox(-3.0F, -12.0F, -1.0F, 1.0F, 12.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		left_leg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}