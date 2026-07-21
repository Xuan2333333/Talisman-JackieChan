// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Model西木<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "西木"),
			"main");
	private final ModelPart all;
	private final ModelPart body;
	private final ModelPart group7;
	private final ModelPart group6;
	private final ModelPart left_wing;
	private final ModelPart group21;
	private final ModelPart right_wing;
	private final ModelPart group2;
	private final ModelPart right_arm;
	private final ModelPart arm;
	private final ModelPart hand;
	private final ModelPart left_arm;
	private final ModelPart arm2;
	private final ModelPart hand2;
	private final ModelPart right_leg;
	private final ModelPart group10;
	private final ModelPart group11;
	private final ModelPart group12;
	private final ModelPart left_leg;
	private final ModelPart group3;
	private final ModelPart group4;
	private final ModelPart group5;
	private final ModelPart head;
	private final ModelPart right_ear;
	private final ModelPart left_ear;
	private final ModelPart tail;

	public Model西木(ModelPart root) {
		this.all = root.getChild("all");
		this.body = this.all.getChild("body");
		this.group7 = this.body.getChild("group7");
		this.group6 = this.body.getChild("group6");
		this.left_wing = this.all.getChild("left_wing");
		this.group21 = this.left_wing.getChild("group21");
		this.right_wing = this.all.getChild("right_wing");
		this.group2 = this.right_wing.getChild("group2");
		this.right_arm = this.all.getChild("right_arm");
		this.arm = this.right_arm.getChild("arm");
		this.hand = this.right_arm.getChild("hand");
		this.left_arm = this.all.getChild("left_arm");
		this.arm2 = this.left_arm.getChild("arm2");
		this.hand2 = this.left_arm.getChild("hand2");
		this.right_leg = this.all.getChild("right_leg");
		this.group10 = this.right_leg.getChild("group10");
		this.group11 = this.group10.getChild("group11");
		this.group12 = this.group10.getChild("group12");
		this.left_leg = this.all.getChild("left_leg");
		this.group3 = this.left_leg.getChild("group3");
		this.group4 = this.group3.getChild("group4");
		this.group5 = this.group3.getChild("group5");
		this.head = this.all.getChild("head");
		this.right_ear = this.head.getChild("right_ear");
		this.left_ear = this.head.getChild("left_ear");
		this.tail = this.all.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = all.addOrReplaceChild("body", CubeListBuilder.create(),
				PartPose.offset(-1.0F, -20.0F, 0.75F));

		PartDefinition group7 = body.addOrReplaceChild("group7",
				CubeListBuilder.create().texOffs(36, 48)
						.addBox(-1.0F, 0.2365F, 0.4079F, 2.0F, 0.5F, 2.0F, new CubeDeformation(0.0F)).texOffs(48, 45)
						.addBox(-1.0F, 0.8365F, 0.4079F, 2.0F, 0.4F, 2.0F, new CubeDeformation(0.0F)).texOffs(50, 36)
						.addBox(-0.9F, 0.7365F, 0.5079F, 1.8F, 0.1F, 1.8F, new CubeDeformation(0.0F)).texOffs(36, 51)
						.addBox(-0.75F, -2.4635F, 0.6579F, 1.5F, 2.75F, 1.5F, new CubeDeformation(0.0F)),
				PartPose.offset(1.0F, 7.7135F, -1.4079F));

		PartDefinition cube_r1 = group7.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(58, 13).addBox(-0.75F, -0.7365F, -0.125F, 1.5F, 1.5F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.1115F, -0.475F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r2 = group7.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(32, 54).addBox(-0.75F, 0.6345F, -0.3864F, 1.5F, 1.25F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.1115F, -0.475F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r3 = group7.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(52, 30).addBox(-0.75F, -1.8845F, -0.3864F, 1.5F, 1.25F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.1115F, -0.475F, -0.6545F, 0.0F, 0.0F));

		PartDefinition group6 = body.addOrReplaceChild("group6",
				CubeListBuilder.create().texOffs(8, 47)
						.addBox(0.25F, -1.0F, -1.0F, 1.5F, 2.25F, 2.0F, new CubeDeformation(0.0F)).texOffs(50, 4)
						.addBox(2.25F, 2.5F, -1.0F, 0.95F, 2.45F, 2.0F, new CubeDeformation(0.0F)).texOffs(8, 51)
						.addBox(-1.2F, 2.5F, -1.0F, 0.95F, 2.45F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r4 = group6.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(50, 31)
						.addBox(0.75F, -0.85F, -1.25F, 0.5F, 1.85F, 2.5F, new CubeDeformation(0.0F)).texOffs(8, 41)
						.addBox(-1.75F, -1.0F, -1.25F, 2.5F, 3.0F, 2.5F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r5 = group6.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(48, 47)
						.addBox(-1.25F, -0.85F, -1.25F, 0.5F, 1.85F, 2.5F, new CubeDeformation(0.0F)).texOffs(40, 13)
						.addBox(-0.75F, -1.0F, -1.25F, 2.5F, 3.0F, 2.5F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r6 = group6
				.addOrReplaceChild("cube_r6",
						CubeListBuilder.create().texOffs(44, 38).addBox(-0.85F, -0.7F, -1.0F, 2.25F, 2.7F, 2.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r7 = group6
				.addOrReplaceChild("cube_r7",
						CubeListBuilder.create().texOffs(52, 8).addBox(-1.7F, -0.65F, -1.0F, 0.9F, 1.8F, 2.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.1F, 4.8F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r8 = group6
				.addOrReplaceChild("cube_r8",
						CubeListBuilder.create().texOffs(52, 0).addBox(0.8F, -0.65F, -1.0F, 0.9F, 1.8F, 2.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(1.9F, 4.8F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r9 = group6
				.addOrReplaceChild("cube_r9",
						CubeListBuilder.create().texOffs(36, 43).addBox(-1.4F, -0.7F, -1.0F, 2.25F, 2.7F, 2.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(2.0F, 4.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition left_wing = all.addOrReplaceChild("left_wing",
				CubeListBuilder.create().texOffs(20, 37).mirror()
						.addBox(13.8819F, 3.6647F, 0.0153F, 6.3F, 5.9F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(1.7567F, -16.5296F, 1.525F, 0.0F, -0.5672F, 0.0F));

		PartDefinition cube_r10 = left_wing.addOrReplaceChild("cube_r10",
				CubeListBuilder.create().texOffs(16, 10).mirror()
						.addBox(-7.5F, 0.5F, 0.5F, 8.25F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(10.1185F, -6.7704F, -0.3847F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r11 = left_wing.addOrReplaceChild("cube_r11",
				CubeListBuilder.create().texOffs(52, 21).mirror()
						.addBox(-0.4F, -1.1F, 0.0F, 3.0F, 2.6F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(14.7045F, 9.2026F, 0.1153F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r12 = left_wing.addOrReplaceChild("cube_r12",
				CubeListBuilder.create().texOffs(0, 14).mirror()
						.addBox(-1.5F, 0.5F, 0.5F, 8.4F, 14.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(14.1185F, -5.2704F, -0.3847F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r13 = left_wing.addOrReplaceChild("cube_r13",
				CubeListBuilder.create().texOffs(34, 20).mirror()
						.addBox(-4.15F, -4.5F, 0.4F, 3.9F, 12.1F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(21.1185F, 0.9296F, -0.3847F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r14 = left_wing.addOrReplaceChild("cube_r14",
				CubeListBuilder.create().texOffs(40, 8).mirror()
						.addBox(-6.625F, -2.25F, 0.0F, 6.0F, 4.75F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(6.9906F, 2.6747F, 0.1153F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r15 = left_wing.addOrReplaceChild("cube_r15",
				CubeListBuilder.create().texOffs(10, 30).mirror()
						.addBox(-1.5F, 0.5F, 0.5F, 5.0F, 10.75F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
						.texOffs(56, 50).mirror().addBox(-4.5F, 0.5F, 0.5F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
						.mirror(false),
				PartPose.offsetAndRotation(3.8185F, -2.3704F, -0.3847F, 0.0F, 0.0F, -0.3927F));

		PartDefinition group21 = left_wing.addOrReplaceChild("group21", CubeListBuilder.create(),
				PartPose.offset(3.8185F, -2.3704F, 0.4153F));

		PartDefinition cube_r16 = group21.addOrReplaceChild("cube_r16",
				CubeListBuilder.create().texOffs(32, 6).mirror()
						.addBox(-4.5F, -0.5F, -0.5F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 0.0F, -0.3F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r17 = group21.addOrReplaceChild("cube_r17",
				CubeListBuilder.create().texOffs(40, 2).mirror()
						.addBox(-4.5F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(6.3F, -4.4F, -0.3F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r18 = group21.addOrReplaceChild("cube_r18",
				CubeListBuilder.create().texOffs(0, 0).mirror()
						.addBox(-0.25F, 0.0F, -0.15F, 0.3F, 1.0F, 0.3F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(7.3916F, -6.3708F, -0.3F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r19 = group21.addOrReplaceChild("cube_r19",
				CubeListBuilder.create().texOffs(58, 15).mirror()
						.addBox(0.0F, 2.5F, -0.5F, 0.5F, 2.0F, 0.5F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(7.4F, -8.2F, -0.05F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r20 = group21.addOrReplaceChild("cube_r20",
				CubeListBuilder.create().texOffs(16, 2).mirror()
						.addBox(-4.5F, -0.5F, -0.5F, 10.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(10.3F, -2.9F, -0.3F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r21 = group21.addOrReplaceChild("cube_r21",
				CubeListBuilder.create().texOffs(4, 39).mirror()
						.addBox(-0.4F, -4.5F, -0.4F, 0.9F, 12.1F, 0.8F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(17.3F, 3.3F, -0.3F, 0.0F, 0.0F, -0.3927F));

		PartDefinition right_wing = all.addOrReplaceChild("right_wing",
				CubeListBuilder.create().texOffs(20, 37).addBox(-20.1819F, 3.6647F, 0.0153F, 6.3F, 5.9F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.7567F, -16.5296F, 1.525F, 0.0F, 0.5672F, 0.0F));

		PartDefinition cube_r22 = right_wing.addOrReplaceChild("cube_r22",
				CubeListBuilder.create().texOffs(16, 10).addBox(-0.75F, 0.5F, 0.5F, 8.25F, 6.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-10.1185F, -6.7704F, -0.3847F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r23 = right_wing.addOrReplaceChild("cube_r23",
				CubeListBuilder.create().texOffs(52, 21).addBox(-2.6F, -1.1F, 0.0F, 3.0F, 2.6F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-14.7045F, 9.2026F, 0.1153F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r24 = right_wing.addOrReplaceChild("cube_r24",
				CubeListBuilder.create().texOffs(0, 14).addBox(-6.9F, 0.5F, 0.5F, 8.4F, 14.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-14.1185F, -5.2704F, -0.3847F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r25 = right_wing.addOrReplaceChild("cube_r25",
				CubeListBuilder.create().texOffs(34, 20).addBox(0.25F, -4.5F, 0.4F, 3.9F, 12.1F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-21.1185F, 0.9296F, -0.3847F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r26 = right_wing.addOrReplaceChild("cube_r26",
				CubeListBuilder.create().texOffs(40, 8).addBox(0.625F, -2.25F, 0.0F, 6.0F, 4.75F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.9906F, 2.6747F, 0.1153F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r27 = right_wing.addOrReplaceChild("cube_r27",
				CubeListBuilder.create().texOffs(10, 30)
						.addBox(-3.5F, 0.5F, 0.5F, 5.0F, 10.75F, 0.0F, new CubeDeformation(0.0F)).texOffs(56, 50)
						.addBox(2.5F, 0.5F, 0.5F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.8185F, -2.3704F, -0.3847F, 0.0F, 0.0F, 0.3927F));

		PartDefinition group2 = right_wing.addOrReplaceChild("group2", CubeListBuilder.create(),
				PartPose.offset(-3.8185F, -2.3704F, 0.4153F));

		PartDefinition cube_r28 = group2
				.addOrReplaceChild("cube_r28",
						CubeListBuilder.create().texOffs(32, 6).addBox(-3.5F, -0.5F, -0.5F, 8.0F, 1.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, -0.3F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r29 = group2.addOrReplaceChild("cube_r29",
				CubeListBuilder.create().texOffs(40, 2).addBox(-0.5F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.3F, -4.4F, -0.3F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r30 = group2.addOrReplaceChild("cube_r30",
				CubeListBuilder.create().texOffs(0, 0).addBox(-0.05F, 0.0F, -0.15F, 0.3F, 1.0F, 0.3F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-7.3916F, -6.3708F, -0.3F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r31 = group2.addOrReplaceChild("cube_r31",
				CubeListBuilder.create().texOffs(58, 15).addBox(-0.5F, 2.5F, -0.5F, 0.5F, 2.0F, 0.5F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-7.4F, -8.2F, -0.05F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r32 = group2.addOrReplaceChild("cube_r32",
				CubeListBuilder.create().texOffs(16, 2).addBox(-6.1F, -0.5F, -0.5F, 10.6F, 1.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-10.3F, -2.9F, -0.3F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r33 = group2.addOrReplaceChild("cube_r33",
				CubeListBuilder.create().texOffs(4, 39).addBox(-0.5F, -4.5F, -0.4F, 0.9F, 12.1F, 0.8F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-17.3F, 3.3F, -0.3F, 0.0F, 0.0F, 0.3927F));

		PartDefinition right_arm = all.addOrReplaceChild("right_arm", CubeListBuilder.create(),
				PartPose.offset(-2.825F, -17.325F, 1.0F));

		PartDefinition arm = right_arm.addOrReplaceChild("arm", CubeListBuilder.create(),
				PartPose.offset(-0.475F, 0.425F, -0.3F));

		PartDefinition cube_r34 = arm.addOrReplaceChild("cube_r34",
				CubeListBuilder.create().texOffs(44, 43).addBox(-3.0F, -0.5F, -0.5F, 4.25F, 1.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2359F, -0.422F, -0.7346F));

		PartDefinition cube_r35 = arm.addOrReplaceChild("cube_r35",
				CubeListBuilder.create().texOffs(18, 20).addBox(-0.25F, -0.625F, -3.0F, 1.0F, 1.25F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5981F, 3.4389F, -1.5201F, 0.8795F, 0.8995F, -0.1378F));

		PartDefinition hand = right_arm.addOrReplaceChild("hand", CubeListBuilder.create(),
				PartPose.offsetAndRotation(-4.2436F, 6.6199F, -3.7347F, 0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r36 = hand.addOrReplaceChild("cube_r36",
				CubeListBuilder.create().texOffs(52, 61)
						.addBox(2.3877F, -0.2071F, 0.1692F, 0.3F, 0.25F, 1.0F, new CubeDeformation(0.0F)).texOffs(58, 9)
						.addBox(0.1988F, -0.4571F, 1.7113F, 1.0F, 0.75F, 0.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.2497F, 0.6214F, -2.2272F, 0.1462F, 0.0534F, 0.8322F));

		PartDefinition cube_r37 = hand.addOrReplaceChild("cube_r37",
				CubeListBuilder.create().texOffs(54, 41).addBox(1.7939F, -0.4571F, -1.12F, 0.75F, 0.75F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.2497F, 0.6214F, -2.2272F, 0.195F, -0.7219F, 0.6946F));

		PartDefinition cube_r38 = hand.addOrReplaceChild("cube_r38",
				CubeListBuilder.create().texOffs(52, 62)
						.addBox(0.78F, -1.9351F, -1.685F, 1.0F, 0.25F, 0.3F, new CubeDeformation(0.0F)).texOffs(52, 62)
						.addBox(0.78F, 0.6649F, -1.685F, 1.0F, 0.25F, 0.3F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.2497F, 0.6214F, -2.2272F, 0.2988F, 1.1157F, 0.2871F));

		PartDefinition cube_r39 = hand.addOrReplaceChild("cube_r39",
				CubeListBuilder.create().texOffs(53, 62).addBox(0.6951F, -0.5351F, -1.758F, 1.0F, 0.25F, 0.3F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.2497F, 0.6214F, -2.2272F, 0.175F, 0.733F, 0.135F));

		PartDefinition cube_r40 = hand.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(54, 52)
				.addBox(-0.994F, 0.4149F, -1.5545F, 0.75F, 0.75F, 2.0F, new CubeDeformation(0.0F)).texOffs(48, 52)
				.addBox(-0.994F, -2.2351F, -1.5545F, 0.75F, 0.75F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.2497F, 0.6214F, -2.2272F, 0.1299F, -0.0444F, 0.0114F));

		PartDefinition cube_r41 = hand.addOrReplaceChild("cube_r41",
				CubeListBuilder.create().texOffs(6, 55).addBox(-1.1591F, -0.8351F, -1.5583F, 0.75F, 0.75F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.2497F, 0.6214F, -2.2272F, 0.1431F, -0.4335F, -0.0433F));

		PartDefinition cube_r42 = hand.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(56, 47)
				.addBox(-1.1347F, -0.8916F, -0.5778F, 0.75F, 0.75F, 1.75F, new CubeDeformation(0.0F)).texOffs(21, 52)
				.addBox(-0.9847F, -1.5351F, 1.1508F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.2497F, 0.6214F, -2.2272F, 0.1379F, 0.3449F, 0.0641F));

		PartDefinition cube_r43 = hand.addOrReplaceChild("cube_r43",
				CubeListBuilder.create().texOffs(56, 55).addBox(0.2329F, 0.2356F, -2.3873F, 0.75F, 0.75F, 1.75F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.8497F, -0.3786F, -0.0272F, 0.5306F, 0.3449F, 0.0641F));

		PartDefinition cube_r44 = hand.addOrReplaceChild("cube_r44",
				CubeListBuilder.create().texOffs(56, 44).addBox(-1.1347F, -2.0812F, -0.7188F, 0.75F, 0.75F, 1.75F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.2497F, 0.6214F, -2.2272F, -0.2548F, 0.3449F, 0.0641F));

		PartDefinition left_arm = all.addOrReplaceChild("left_arm", CubeListBuilder.create(),
				PartPose.offset(2.825F, -17.325F, 1.0F));

		PartDefinition arm2 = left_arm.addOrReplaceChild("arm2", CubeListBuilder.create(),
				PartPose.offset(0.475F, 0.425F, -0.3F));

		PartDefinition cube_r45 = arm2.addOrReplaceChild("cube_r45",
				CubeListBuilder.create().texOffs(44, 43).mirror()
						.addBox(-1.25F, -0.5F, -0.5F, 4.25F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2359F, 0.422F, 0.7346F));

		PartDefinition cube_r46 = arm2.addOrReplaceChild("cube_r46",
				CubeListBuilder.create().texOffs(18, 20).mirror()
						.addBox(-0.75F, -0.625F, -3.0F, 1.0F, 1.25F, 6.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(2.5981F, 3.4389F, -1.5201F, 0.8795F, -0.8995F, 0.1378F));

		PartDefinition hand2 = left_arm.addOrReplaceChild("hand2", CubeListBuilder.create(),
				PartPose.offsetAndRotation(4.2436F, 6.6199F, -3.7347F, 0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r47 = hand2.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(52, 61).mirror()
				.addBox(-2.6877F, -0.2071F, 0.1692F, 0.3F, 0.25F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(58, 9).mirror()
				.addBox(-1.1988F, -0.4571F, 1.7113F, 1.0F, 0.75F, 0.75F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.2497F, 0.6214F, -2.2272F, 0.1462F, -0.0534F, -0.8322F));

		PartDefinition cube_r48 = hand2.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(54, 41).mirror()
				.addBox(-2.5439F, -0.4571F, -1.12F, 0.75F, 0.75F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.2497F, 0.6214F, -2.2272F, 0.195F, 0.7219F, -0.6946F));

		PartDefinition cube_r49 = hand2.addOrReplaceChild("cube_r49",
				CubeListBuilder.create().texOffs(52, 62).mirror()
						.addBox(-1.78F, -1.9351F, -1.685F, 1.0F, 0.25F, 0.3F, new CubeDeformation(0.0F)).mirror(false)
						.texOffs(52, 62).mirror()
						.addBox(-1.78F, 0.6649F, -1.685F, 1.0F, 0.25F, 0.3F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.2497F, 0.6214F, -2.2272F, 0.2988F, -1.1157F, -0.2871F));

		PartDefinition cube_r50 = hand2.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(53, 62).mirror()
				.addBox(-1.6951F, -0.5351F, -1.758F, 1.0F, 0.25F, 0.3F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.2497F, 0.6214F, -2.2272F, 0.175F, -0.733F, -0.135F));

		PartDefinition cube_r51 = hand2.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(54, 52).mirror()
				.addBox(0.244F, 0.4149F, -1.5545F, 0.75F, 0.75F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(48, 52).mirror()
				.addBox(0.244F, -2.2351F, -1.5545F, 0.75F, 0.75F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.2497F, 0.6214F, -2.2272F, 0.1299F, 0.0444F, -0.0114F));

		PartDefinition cube_r52 = hand2.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(6, 55).mirror()
				.addBox(0.4091F, -0.8351F, -1.5583F, 0.75F, 0.75F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.2497F, 0.6214F, -2.2272F, 0.1431F, 0.4335F, 0.0433F));

		PartDefinition cube_r53 = hand2.addOrReplaceChild("cube_r53",
				CubeListBuilder.create().texOffs(56, 47).mirror()
						.addBox(0.3847F, -0.8916F, -0.5778F, 0.75F, 0.75F, 1.75F, new CubeDeformation(0.0F))
						.mirror(false).texOffs(21, 52).mirror()
						.addBox(-0.0153F, -1.5351F, 1.1508F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.2497F, 0.6214F, -2.2272F, 0.1379F, -0.3449F, -0.0641F));

		PartDefinition cube_r54 = hand2.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(56, 55).mirror()
				.addBox(-0.9829F, 0.2356F, -2.3873F, 0.75F, 0.75F, 1.75F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.8497F, -0.3786F, -0.0272F, 0.5306F, -0.3449F, -0.0641F));

		PartDefinition cube_r55 = hand2.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(56, 44).mirror()
				.addBox(0.3847F, -2.0812F, -0.7188F, 0.75F, 0.75F, 1.75F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.2497F, 0.6214F, -2.2272F, -0.2548F, -0.3449F, -0.0641F));

		PartDefinition right_leg = all.addOrReplaceChild("right_leg", CubeListBuilder.create(),
				PartPose.offset(-1.0F, -10.75F, 0.0F));

		PartDefinition group10 = right_leg.addOrReplaceChild("group10", CubeListBuilder.create(),
				PartPose.offsetAndRotation(-2.108F, 8.9676F, -2.5035F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r56 = group10.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(52, 18)
				.addBox(-0.6285F, -0.2693F, -3.6037F, 1.75F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(44, 51)
				.addBox(-0.3785F, -3.7193F, -3.6037F, 1.25F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0838F, 3.5999F, 4.5485F, -0.6528F, 0.0436F, 0.0756F));

		PartDefinition cube_r57 = group10.addOrReplaceChild("cube_r57",
				CubeListBuilder.create().texOffs(46, 31).addBox(-1.0235F, -6.9526F, 1.8361F, 1.25F, 5.25F, 1.25F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.367F, 2.0653F, 3.8742F, 1.0459F, -0.0334F, 0.0807F));

		PartDefinition cube_r58 = group10.addOrReplaceChild("cube_r58",
				CubeListBuilder.create().texOffs(28, 43).addBox(-0.75F, -2.25F, -0.75F, 1.5F, 7.15F, 1.5F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.608F, -7.4676F, -0.6465F, 0.0F, 0.0F, 0.3927F));

		PartDefinition group11 = group10.addOrReplaceChild("group11", CubeListBuilder.create(),
				PartPose.offset(5.883F, 0.5324F, -4.2215F));

		PartDefinition cube_r59 = group11.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(24, 55)
				.addBox(0.5715F, -2.8436F, -4.0412F, 0.75F, 0.75F, 1.8F, new CubeDeformation(0.0F)).texOffs(22, 58)
				.addBox(0.7715F, -2.1725F, -5.1993F, 0.35F, 0.5F, 0.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.9668F, 3.0674F, 8.77F, 0.5252F, 0.0436F, 0.0756F));

		PartDefinition cube_r60 = group11.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(58, 0)
				.addBox(0.5715F, 0.8916F, -4.8868F, 0.75F, 1.5F, 0.75F, new CubeDeformation(0.0F)).texOffs(52, 61)
				.addBox(0.7715F, 2.0654F, -5.2695F, 0.35F, 0.75F, 0.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.9668F, 3.0674F, 8.77F, -0.2602F, 0.0436F, 0.0756F));

		PartDefinition cube_r61 = group11.addOrReplaceChild("cube_r61",
				CubeListBuilder.create().texOffs(53, 62).addBox(0.7715F, 0.5435F, -5.867F, 0.35F, 0.75F, 0.5F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.9668F, 3.0674F, 8.77F, 0.1325F, 0.0436F, 0.0756F));

		PartDefinition group12 = group10.addOrReplaceChild("group12", CubeListBuilder.create(),
				PartPose.offset(7.283F, 0.8324F, -6.2715F));

		PartDefinition cube_r62 = group12.addOrReplaceChild("cube_r62",
				CubeListBuilder.create().texOffs(53, 62).addBox(-0.6285F, 0.5435F, -5.867F, 0.35F, 0.75F, 0.5F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.3668F, 2.7674F, 10.82F, 0.1325F, 0.0436F, 0.0756F));

		PartDefinition cube_r63 = group12.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(52, 61)
				.addBox(-0.6285F, 2.0654F, -5.2695F, 0.35F, 0.75F, 0.75F, new CubeDeformation(0.0F)).texOffs(6, 58)
				.addBox(-0.8285F, 0.8916F, -4.8868F, 0.75F, 1.5F, 0.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.3668F, 2.7674F, 10.82F, -0.2602F, 0.0436F, 0.0756F));

		PartDefinition cube_r64 = group12.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(20, 58)
				.addBox(-0.6285F, -2.1725F, -5.1993F, 0.35F, 0.5F, 0.75F, new CubeDeformation(0.0F)).texOffs(30, 55)
				.addBox(-0.8285F, -2.8436F, -4.0412F, 0.75F, 0.75F, 1.8F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.3668F, 2.7674F, 10.82F, 0.5252F, 0.0436F, 0.0756F));

		PartDefinition left_leg = all.addOrReplaceChild("left_leg", CubeListBuilder.create(),
				PartPose.offset(1.0F, -10.75F, 0.0F));

		PartDefinition group3 = left_leg.addOrReplaceChild("group3", CubeListBuilder.create(),
				PartPose.offsetAndRotation(2.108F, 8.9676F, -2.5035F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r65 = group3.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(52, 18).mirror()
				.addBox(-1.1215F, -0.2693F, -3.6037F, 1.75F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(44, 51).mirror()
				.addBox(-0.8715F, -3.7193F, -3.6037F, 1.25F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(1.0838F, 3.5999F, 4.5485F, -0.6528F, -0.0436F, -0.0756F));

		PartDefinition cube_r66 = group3.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(46, 31).mirror()
				.addBox(-0.2265F, -6.9526F, 1.8361F, 1.25F, 5.25F, 1.25F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.367F, 2.0653F, 3.8742F, 1.0459F, 0.0334F, -0.0807F));

		PartDefinition cube_r67 = group3.addOrReplaceChild("cube_r67",
				CubeListBuilder.create().texOffs(28, 43).mirror()
						.addBox(-0.75F, -2.25F, -0.75F, 1.5F, 7.15F, 1.5F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-1.608F, -7.4676F, -0.6465F, 0.0F, 0.0F, -0.3927F));

		PartDefinition group4 = group3.addOrReplaceChild("group4", CubeListBuilder.create(),
				PartPose.offset(-5.883F, 0.5324F, -4.2215F));

		PartDefinition cube_r68 = group4.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(24, 55).mirror()
				.addBox(-1.3215F, -2.8436F, -4.0412F, 0.75F, 0.75F, 1.8F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(22, 58).mirror()
				.addBox(-1.1215F, -2.1725F, -5.1993F, 0.35F, 0.5F, 0.75F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(6.9668F, 3.0674F, 8.77F, 0.5252F, -0.0436F, -0.0756F));

		PartDefinition cube_r69 = group4.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(58, 0).mirror()
				.addBox(-1.3215F, 0.8916F, -4.8868F, 0.75F, 1.5F, 0.75F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(52, 61).mirror()
				.addBox(-1.1215F, 2.0654F, -5.2695F, 0.35F, 0.75F, 0.75F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(6.9668F, 3.0674F, 8.77F, -0.2602F, -0.0436F, -0.0756F));

		PartDefinition cube_r70 = group4.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(53, 62).mirror()
				.addBox(-1.1215F, 0.5435F, -5.867F, 0.35F, 0.75F, 0.5F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(6.9668F, 3.0674F, 8.77F, 0.1325F, -0.0436F, -0.0756F));

		PartDefinition group5 = group3.addOrReplaceChild("group5", CubeListBuilder.create(),
				PartPose.offset(-7.283F, 0.8324F, -6.2715F));

		PartDefinition cube_r71 = group5.addOrReplaceChild("cube_r71",
				CubeListBuilder.create().texOffs(53, 62).mirror()
						.addBox(0.2785F, 0.5435F, -5.867F, 0.35F, 0.75F, 0.5F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(8.3668F, 2.7674F, 10.82F, 0.1325F, -0.0436F, -0.0756F));

		PartDefinition cube_r72 = group5.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(52, 61).mirror()
				.addBox(0.2785F, 2.0654F, -5.2695F, 0.35F, 0.75F, 0.75F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(6, 58).mirror()
				.addBox(0.0785F, 0.8916F, -4.8868F, 0.75F, 1.5F, 0.75F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(8.3668F, 2.7674F, 10.82F, -0.2602F, -0.0436F, -0.0756F));

		PartDefinition cube_r73 = group5.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(20, 58).mirror()
				.addBox(0.2785F, -2.1725F, -5.1993F, 0.35F, 0.5F, 0.75F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(30, 55).mirror()
				.addBox(0.0785F, -2.8436F, -4.0412F, 0.75F, 0.75F, 1.8F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(8.3668F, 2.7674F, 10.82F, 0.5252F, -0.0436F, -0.0756F));

		PartDefinition head = all.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(6, 52)
						.addBox(-1.25F, -12.0F, 0.6F, 0.5F, 3.0F, 0.3F, new CubeDeformation(0.0F)).texOffs(4, 50)
						.addBox(-1.25F, -8.3F, -0.5F, 0.5F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(32, 20)
						.addBox(-1.1F, -12.75F, 0.5F, 0.2F, 5.0F, 0.5F, new CubeDeformation(0.0F)).texOffs(20, 30)
						.addBox(-3.0F, -8.0F, -1.0F, 4.0F, 3.5F, 3.0F, new CubeDeformation(0.0F)).texOffs(44, 55)
						.addBox(-2.25F, -8.25F, -0.5F, 0.65F, 0.25F, 2.0F, new CubeDeformation(0.0F)).texOffs(50, 55)
						.addBox(-0.4F, -8.25F, -0.5F, 0.65F, 0.25F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.0F, -17.0F, 0.0F));

		PartDefinition cube_r74 = head.addOrReplaceChild("cube_r74",
				CubeListBuilder.create().texOffs(42, 24).mirror()
						.addBox(-1.2F, -0.25F, -1.498F, 2.2F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-0.2F, -4.75F, 0.5F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r75 = head.addOrReplaceChild("cube_r75",
				CubeListBuilder.create().texOffs(42, 24).addBox(-1.0F, -0.25F, -1.499F, 2.2F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.8F, -4.75F, 0.5F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r76 = head.addOrReplaceChild("cube_r76",
				CubeListBuilder.create().texOffs(0, 0).addBox(-0.05F, -0.25F, -0.05F, 0.2F, 0.75F, 0.1F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.1F, -3.45F, -0.95F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r77 = head.addOrReplaceChild("cube_r77",
				CubeListBuilder.create().texOffs(0, 0).addBox(-0.1F, -3.25F, 0.0F, 0.2F, 4.25F, 0.25F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -8.75F, 0.75F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r78 = head.addOrReplaceChild("cube_r78",
				CubeListBuilder.create().texOffs(0, 0).addBox(-0.1F, -3.25F, 0.0F, 0.2F, 4.25F, 0.25F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -8.75F, 0.75F, 0.0F, 0.0F, -0.3927F));

		PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(0, 0).mirror()
				.addBox(-3.5512F, -6.778F, 0.01F, 1.2F, 0.1F, 0.1F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 52).mirror().addBox(-3.4012F, -6.678F, 0.1F, 1.05F, 0.2F, 0.0F, new CubeDeformation(0.0F))
				.mirror(false).texOffs(1, 58).mirror()
				.addBox(-0.375F, -5.25F, 0.1F, 1.5F, 1.55F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(2, 55).mirror().addBox(-0.475F, -5.5F, 0.1F, 0.85F, 0.3F, 0.0F, new CubeDeformation(0.0F))
				.mirror(false), PartPose.offset(-4.125F, -2.1F, 0.0F));

		PartDefinition cube_r79 = right_ear.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(0, 51)
				.mirror().addBox(1.9799F, 0.3012F, 0.025F, 0.95F, 0.55F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 54).mirror().addBox(-1.9986F, -0.8049F, 0.025F, 1.2F, 0.7F, 0.0F, new CubeDeformation(0.0F))
				.mirror(false).texOffs(0, 0).mirror()
				.addBox(-1.6236F, -0.9799F, -0.065F, 1.0F, 0.2F, 0.1F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).mirror().addBox(-0.6236F, -0.9799F, -0.065F, 1.0F, 0.3F, 0.1F, new CubeDeformation(0.0F))
				.mirror(false).texOffs(0, 0).mirror()
				.addBox(0.3764F, -0.9799F, -0.065F, 2.0F, 0.4F, 0.1F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-1.2451F, -5.2562F, 0.075F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r80 = right_ear.addOrReplaceChild("cube_r80",
				CubeListBuilder.create().texOffs(1, 56).mirror()
						.addBox(-2.7986F, -0.4829F, 0.025F, 0.85F, 0.25F, 0.0F, new CubeDeformation(0.0F))
						.mirror(false),
				PartPose.offsetAndRotation(-1.1451F, -5.2562F, 0.075F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r81 = right_ear.addOrReplaceChild("cube_r81",
				CubeListBuilder.create().texOffs(1, 55).mirror()
						.addBox(-1.0617F, -0.6146F, 0.025F, 0.6F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
						.texOffs(0, 55).mirror()
						.addBox(-0.4617F, -0.8646F, 0.025F, 2.2F, 1.35F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-1.2451F, -5.2562F, 0.075F, 0.0F, 0.0F, 0.7854F));

		PartDefinition left_ear = head.addOrReplaceChild("left_ear",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(2.3512F, -6.778F, 0.01F, 1.2F, 0.1F, 0.1F, new CubeDeformation(0.0F)).texOffs(0, 52)
						.addBox(2.3512F, -6.678F, 0.1F, 1.05F, 0.2F, 0.0F, new CubeDeformation(0.0F)).texOffs(1, 58)
						.addBox(-1.125F, -5.25F, 0.1F, 1.5F, 1.55F, 0.0F, new CubeDeformation(0.0F)).texOffs(2, 55)
						.addBox(-0.375F, -5.5F, 0.1F, 0.85F, 0.3F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(2.125F, -2.1F, 0.0F));

		PartDefinition cube_r82 = left_ear.addOrReplaceChild("cube_r82",
				CubeListBuilder.create().texOffs(0, 51)
						.addBox(-2.9299F, 0.3012F, 0.025F, 0.95F, 0.55F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 54)
						.addBox(0.7986F, -0.8049F, 0.025F, 1.2F, 0.7F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(0.6236F, -0.9799F, -0.065F, 1.0F, 0.2F, 0.1F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-0.3764F, -0.9799F, -0.065F, 1.0F, 0.3F, 0.1F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-2.3764F, -0.9799F, -0.065F, 2.0F, 0.4F, 0.1F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.2451F, -5.2562F, 0.075F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r83 = left_ear.addOrReplaceChild("cube_r83",
				CubeListBuilder.create().texOffs(1, 56).addBox(1.9486F, -0.4829F, 0.025F, 0.85F, 0.25F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.1451F, -5.2562F, 0.075F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r84 = left_ear.addOrReplaceChild("cube_r84",
				CubeListBuilder.create().texOffs(1, 55)
						.addBox(0.4617F, -0.6146F, 0.025F, 0.6F, 1.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 55)
						.addBox(-1.7383F, -0.8646F, 0.025F, 2.2F, 1.35F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.2451F, -5.2562F, 0.075F, 0.0F, 0.0F, -0.7854F));

		PartDefinition tail = all.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(42, 57).addBox(-0.3F,
				1.325F, -2.05F, 0.6F, 0.75F, 1.5F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -13.5102F, 4.9728F));

		PartDefinition cube_r85 = tail
				.addOrReplaceChild("cube_r85",
						CubeListBuilder.create().texOffs(29, 52).addBox(-0.2F, -0.375F, -0.75F, 0.4F, 0.55F, 1.25F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r86 = tail.addOrReplaceChild("cube_r86",
				CubeListBuilder.create().texOffs(12, 56).addBox(-0.2F, -0.075F, -0.525F, 0.4F, 0.35F, 1.15F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 1.8598F, 0.8228F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r87 = tail.addOrReplaceChild("cube_r87",
				CubeListBuilder.create().texOffs(52, 12).addBox(-0.55F, -0.5F, -1.3F, 1.1F, 1.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.0102F, -2.4728F, 0.3927F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.right_arm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.right_wing.yRot = (Mth.sin(ageInTicks * 0.6F) * 0.6F);
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.left_arm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.right_leg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.left_wing.yRot = (Mth.sin(ageInTicks * 0.6F + 3) * 0.6F);
		this.left_leg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
	}
}