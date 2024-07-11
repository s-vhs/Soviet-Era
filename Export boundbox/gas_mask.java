// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class gas_mask<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "gas_mask"), "main");
	private final ModelPart mask;
	private final ModelPart cube_r1;

	public gas_mask(ModelPart root) {
		this.mask = root.getChild("mask");
		this.cube_r1 = root.getChild("cube_r1");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition mask = partdefinition.addOrReplaceChild("mask", CubeListBuilder.create().texOffs(11, 21).addBox(-3.0F, -4.65F, -3.0771F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(22, 6).addBox(-3.0F, -3.65F, -4.0771F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 4).addBox(-3.0F, -2.65F, -4.0771F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 8).addBox(2.0F, -3.65F, -4.0771F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(2.0F, -4.65F, -3.0771F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(11, 11).addBox(4.0F, -1.65F, -3.0771F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(2.0F, -3.65F, 4.9229F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(3, 22).addBox(3.0F, -1.65F, 4.9229F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 11).addBox(-2.0F, -1.65F, 4.9229F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(15, 21).addBox(3.0F, 2.35F, 4.9229F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.0F, 2.35F, -3.0771F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(3, 17).addBox(2.0F, 1.35F, 4.9229F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 6).addBox(-3.0F, 1.35F, 4.9229F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(11, 21).addBox(-4.0F, 2.35F, 4.9229F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-5.0F, 2.35F, -3.0771F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(11, 13).addBox(-4.0F, -1.65F, -4.0771F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(10, -1).addBox(-1.0F, -1.65F, -5.0771F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 14).addBox(3.0F, -1.65F, -4.0771F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(11, 7).addBox(1.0F, -1.65F, -4.0771F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 14).addBox(3.0F, 1.35F, -4.0771F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(11, 1).addBox(-5.0F, -1.65F, -3.0771F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(14, 16).addBox(-3.0F, -3.65F, 4.9229F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 20).addBox(-4.0F, -1.65F, 4.9229F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(15, 13).addBox(-4.0F, 1.35F, -4.0771F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(-3.0F, 1.35F, -4.0771F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 5).addBox(-1.0F, 0.35F, -5.1396F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-1.0F, 3.35F, -6.0771F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(11, 11).addBox(-3.0F, -1.65F, -4.0771F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 16).addBox(1.0F, -0.65F, -3.6396F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(28, 16).addBox(-3.0F, -0.65F, -3.6396F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.35F, -0.9229F));

		PartDefinition cube_r1 = mask.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 3).addBox(-0.375F, -0.25F, -0.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(-0.875F, -0.75F, -0.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.625F, -1.25F, -1.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5581F, 2.6F, -4.3119F, 0.2182F, -0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		mask.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}