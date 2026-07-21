package net.talisman.talismanjackiechan.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelshendu_Converted<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("talisman_jackiechan", "modelshendu_converted"), "main");
	public final ModelPart all;
	public final ModelPart rightleg;
	public final ModelPart rightpaw;
	public final ModelPart rightshank;
	public final ModelPart rightthigh;
	public final ModelPart righttoe;
	public final ModelPart leftleg;
	public final ModelPart leftpaw;
	public final ModelPart leftshank;
	public final ModelPart leftthigh;
	public final ModelPart lefttoe;
	public final ModelPart body;
	public final ModelPart crotch;
	public final ModelPart neck;
	public final ModelPart neckroot;
	public final ModelPart neckmid;
	public final ModelPart necktop;
	public final ModelPart head;
	public final ModelPart rightarm;
	public final ModelPart rightupperarm;
	public final ModelPart rightforearm;
	public final ModelPart righthand;
	public final ModelPart leftarm;
	public final ModelPart leftupperarm;
	public final ModelPart leftforearm;
	public final ModelPart lefthand;

	public Modelshendu_Converted(ModelPart root) {
		this.all = root.getChild("all");
		this.rightleg = this.all.getChild("rightleg");
		this.rightpaw = this.rightleg.getChild("rightpaw");
		this.rightshank = this.rightleg.getChild("rightshank");
		this.rightthigh = this.rightleg.getChild("rightthigh");
		this.righttoe = this.rightleg.getChild("righttoe");
		this.leftleg = this.all.getChild("leftleg");
		this.leftpaw = this.leftleg.getChild("leftpaw");
		this.leftshank = this.leftleg.getChild("leftshank");
		this.leftthigh = this.leftleg.getChild("leftthigh");
		this.lefttoe = this.leftleg.getChild("lefttoe");
		this.body = this.all.getChild("body");
		this.crotch = this.all.getChild("crotch");
		this.neck = this.all.getChild("neck");
		this.neckroot = this.neck.getChild("neckroot");
		this.neckmid = this.neck.getChild("neckmid");
		this.necktop = this.neck.getChild("necktop");
		this.head = this.all.getChild("head");
		this.rightarm = this.all.getChild("rightarm");
		this.rightupperarm = this.rightarm.getChild("rightupperarm");
		this.rightforearm = this.rightarm.getChild("rightforearm");
		this.righthand = this.rightarm.getChild("righthand");
		this.leftarm = this.all.getChild("leftarm");
		this.leftupperarm = this.leftarm.getChild("leftupperarm");
		this.leftforearm = this.leftarm.getChild("leftforearm");
		this.lefthand = this.leftarm.getChild("lefthand");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));
		PartDefinition rightleg = all.addOrReplaceChild("rightleg", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, 8.0F, -1.25F, 0.0F, 0.1309F, 0.0F));
		PartDefinition rightpaw = rightleg.addOrReplaceChild("rightpaw", CubeListBuilder.create().texOffs(60, 39).addBox(-6.15F, 22.0F, -6.0F, 3.5F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.9572F, -8.0F, 0.6526F));
		PartDefinition cube_r1 = rightpaw.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(86, 54).addBox(-5.25F, 6.9804F, 5.9358F, 1.7F, 1.75F, 0.75F, new CubeDeformation(0.0F)).texOffs(57, 82)
				.addBox(-5.5F, 8.7304F, 5.9358F, 2.2F, 2.75F, 0.75F, new CubeDeformation(0.0F)).texOffs(48, 80).addBox(-5.8F, 11.4804F, 5.9358F, 2.8F, 3.5F, 0.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 6.6014F, -2.4441F, -0.3927F, 0.0F, 0.0F));
		PartDefinition rightshank = rightleg.addOrReplaceChild("rightshank", CubeListBuilder.create(), PartPose.offset(-2.4428F, -8.6976F, 6.1729F));
		PartDefinition cube_r2 = rightshank.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(77, 79).addBox(-5.7F, 7.1804F, 1.6858F, 2.6F, 2.4F, 1.5F, new CubeDeformation(0.0F)).texOffs(77, 23).addBox(-5.6F, 7.5804F, 3.1858F, 2.4F, 2.0F, 2.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(7.4F, 7.299F, -7.9644F, -0.3927F, 0.0F, 0.0F));
		PartDefinition rightthigh = rightleg.addOrReplaceChild("rightthigh", CubeListBuilder.create(), PartPose.offset(4.9572F, -8.0F, 0.6526F));
		PartDefinition cube_r3 = rightthigh.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(55, 58).addBox(-6.0F, -0.7196F, -1.2642F, 3.2F, 6.9F, 3.25F, new CubeDeformation(0.0F)).texOffs(70, 71).addBox(-5.75F, 6.1804F, -1.0642F, 2.7F, 3.9F, 2.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 6.6014F, -2.4441F, -0.3927F, 0.0F, 0.0F));
		PartDefinition righttoe = rightleg.addOrReplaceChild("righttoe", CubeListBuilder.create().texOffs(83, 74).addBox(-5.25F, 14.2F, -8.1F, 0.8F, 0.6F, 2.1F, new CubeDeformation(0.0F)).texOffs(78, 83)
				.addBox(-4.3F, 14.2F, -8.1F, 0.8F, 0.6F, 2.1F, new CubeDeformation(0.0F)).texOffs(71, 83).addBox(-3.35F, 14.2F, -8.1F, 0.8F, 0.6F, 2.1F, new CubeDeformation(0.0F)), PartPose.offset(4.9572F, 0.0F, 0.6526F));
		PartDefinition cube_r4 = righttoe.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(18, 84).addBox(-0.4F, -0.3F, -1.05F, 0.8F, 0.6F, 2.1F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.1F, 14.5F, -6.8F, 0.0F, 0.3927F, 0.0F));
		PartDefinition leftleg = all.addOrReplaceChild("leftleg", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, 8.0F, -1.25F, 0.0F, -0.1745F, 0.0F));
		PartDefinition leftpaw = leftleg.addOrReplaceChild("leftpaw", CubeListBuilder.create().texOffs(0, 61).addBox(2.65F, 22.0F, -6.0F, 3.5F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.924F, -8.0F, 0.8682F));
		PartDefinition cube_r5 = leftpaw.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(47, 86).addBox(3.55F, 6.9804F, 5.9358F, 1.7F, 1.75F, 0.75F, new CubeDeformation(0.0F)).texOffs(35, 82)
				.addBox(3.3F, 8.7304F, 5.9358F, 2.2F, 2.75F, 0.75F, new CubeDeformation(0.0F)).texOffs(9, 81).addBox(3.0F, 11.4804F, 5.9358F, 2.8F, 3.5F, 0.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 6.6014F, -2.4441F, -0.3927F, 0.0F, 0.0F));
		PartDefinition leftshank = leftleg.addOrReplaceChild("leftshank", CubeListBuilder.create(), PartPose.offset(-12.324F, -8.6976F, 6.3885F));
		PartDefinition cube_r6 = leftshank.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(68, 79).addBox(3.1F, 7.1804F, 1.6858F, 2.6F, 2.4F, 1.5F, new CubeDeformation(0.0F)).texOffs(77, 39).addBox(3.2F, 7.5804F, 3.1858F, 2.4F, 2.0F, 2.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(7.4F, 7.299F, -7.9644F, -0.3927F, 0.0F, 0.0F));
		PartDefinition leftthigh = leftleg.addOrReplaceChild("leftthigh", CubeListBuilder.create(), PartPose.offset(-4.924F, -8.0F, 0.8682F));
		PartDefinition cube_r7 = leftthigh.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(42, 58).addBox(-1.6F, -3.45F, -1.625F, 3.2F, 6.9F, 3.25F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.4F, 9.262F, -3.1557F, -0.3927F, 0.0F, 0.0F));
		PartDefinition cube_r8 = leftthigh.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(57, 71).addBox(3.05F, 6.1804F, -1.0642F, 2.7F, 3.9F, 2.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 6.6014F, -2.4441F, -0.3927F, 0.0F, 0.0F));
		PartDefinition lefttoe = leftleg.addOrReplaceChild("lefttoe", CubeListBuilder.create().texOffs(84, 61).addBox(-2.15F, -0.15F, -1.05F, 0.8F, 0.6F, 2.1F, new CubeDeformation(0.0F)).texOffs(25, 84)
				.addBox(-3.1F, -0.15F, -1.05F, 0.8F, 0.6F, 2.1F, new CubeDeformation(0.0F)).texOffs(84, 65).addBox(-1.2F, -0.15F, -1.05F, 0.8F, 0.6F, 2.1F, new CubeDeformation(0.0F)), PartPose.offset(0.726F, 14.35F, -6.1818F));
		PartDefinition cube_r9 = lefttoe.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(85, 83).addBox(-0.4F, -0.3F, -1.05F, 0.8F, 0.6F, 2.1F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.45F, 0.15F, 0.25F, 0.0F, -0.3927F, 0.0F));
		PartDefinition body = all.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(43, 0).addBox(-5.5F, 3.4528F, -3.4367F, 11.0F, 0.5F, 3.45F, new CubeDeformation(0.0F)).texOffs(0, 26).addBox(-6.3F, -11.2972F, -3.6367F, 12.65F, 1.0F, 4.2F, new CubeDeformation(0.0F)).texOffs(0, 18)
						.addBox(-7.6F, -4.0472F, -2.6867F, 15.2F, 3.5F, 3.5F, new CubeDeformation(0.0F)).texOffs(33, 32).addBox(-5.9F, -0.5472F, -2.6867F, 11.7F, 2.5F, 3.25F, new CubeDeformation(0.0F)).texOffs(27, 45)
						.addBox(-5.65F, 1.9528F, -2.6867F, 11.2F, 1.5F, 2.7F, new CubeDeformation(0.0F)).texOffs(0, 38).addBox(-5.55F, -12.5472F, -3.6367F, 11.15F, 0.5F, 3.9F, new CubeDeformation(0.0F)).texOffs(41, 10)
						.addBox(-5.3F, -13.2972F, -3.6367F, 10.65F, 0.75F, 3.7F, new CubeDeformation(0.0F)).texOffs(0, 51).addBox(-4.55F, -14.0472F, -3.6367F, 9.15F, 0.75F, 3.15F, new CubeDeformation(0.0F)).texOffs(0, 83)
						.addBox(-1.05F, -15.7972F, -3.6367F, 2.15F, 0.25F, 1.6F, new CubeDeformation(0.0F)).texOffs(72, 10).addBox(-2.3F, -15.2972F, -3.6367F, 4.65F, 0.5F, 2.35F, new CubeDeformation(0.0F)).texOffs(0, 32)
						.addBox(-5.8F, -12.0472F, -3.6367F, 11.65F, 0.75F, 3.9F, new CubeDeformation(0.0F)).texOffs(0, 56).addBox(-3.55F, -14.7972F, -3.6367F, 7.15F, 0.75F, 2.8F, new CubeDeformation(0.0F)).texOffs(57, 79)
						.addBox(-1.55F, -15.5472F, -3.6367F, 3.15F, 0.25F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-8.15F, -7.5472F, -4.1867F, 16.3F, 3.5F, 5.2F, new CubeDeformation(0.0F)).texOffs(0, 10)
						.addBox(-7.85F, -10.2972F, -3.6367F, 15.75F, 2.75F, 4.45F, new CubeDeformation(0.0F)).texOffs(50, 54).addBox(-5.15F, 1.9528F, -3.4367F, 10.2F, 1.5F, 0.75F, new CubeDeformation(0.0F)).texOffs(25, 51)
						.addBox(-5.4F, -0.5472F, -3.6867F, 10.7F, 2.5F, 1.2F, new CubeDeformation(0.0F)).texOffs(31, 39).addBox(-6.6F, -4.0472F, -3.9367F, 13.2F, 3.5F, 1.25F, new CubeDeformation(0.0F)).texOffs(43, 5)
						.addBox(-5.25F, 3.9528F, -3.3367F, 10.5F, 0.5F, 3.25F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition crotch = all.addOrReplaceChild("crotch", CubeListBuilder.create().texOffs(0, 44).addBox(-5.0F, -2.1486F, -5.8926F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.6014F, 2.5559F));
		PartDefinition neck = all.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition neckroot = neck.addOrReplaceChild("neckroot",
				CubeListBuilder.create().texOffs(21, 59).addBox(-4.05F, -10.7972F, -5.0867F, 8.15F, 0.5F, 1.45F, new CubeDeformation(0.0F)).texOffs(50, 51).addBox(-4.8F, -12.0472F, -5.0867F, 9.65F, 0.5F, 1.45F, new CubeDeformation(0.0F))
						.texOffs(21, 56).addBox(-4.55F, -12.7972F, -5.0867F, 9.15F, 0.75F, 1.45F, new CubeDeformation(0.0F)).texOffs(56, 48).addBox(-4.05F, -13.5472F, -5.0867F, 8.15F, 0.75F, 1.45F, new CubeDeformation(0.0F)).texOffs(72, 0)
						.addBox(-3.05F, -14.2972F, -5.0867F, 6.15F, 0.75F, 1.45F, new CubeDeformation(0.0F)).texOffs(11, 78).addBox(-2.3F, -14.7972F, -5.0867F, 4.65F, 0.5F, 1.45F, new CubeDeformation(0.0F)).texOffs(81, 29)
						.addBox(-1.55F, -15.2972F, -5.0867F, 3.15F, 0.5F, 1.45F, new CubeDeformation(0.0F)).texOffs(41, 16).addBox(-0.8F, -15.5472F, -5.0867F, 1.65F, 0.25F, 1.45F, new CubeDeformation(0.0F)).texOffs(56, 45)
						.addBox(-4.55F, -11.5472F, -5.0867F, 9.15F, 0.75F, 1.45F, new CubeDeformation(0.0F)).texOffs(66, 29).addBox(-3.05F, -10.2972F, -5.0867F, 6.15F, 1.3F, 1.45F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition neckmid = neck.addOrReplaceChild("neckmid",
				CubeListBuilder.create().texOffs(11, 75).addBox(-2.6F, -11.0472F, -6.5367F, 5.15F, 1.15F, 1.45F, new CubeDeformation(0.0F)).texOffs(18, 81).addBox(-0.3F, -14.7972F, -6.5367F, 0.65F, 0.5F, 1.45F, new CubeDeformation(0.0F))
						.texOffs(81, 32).addBox(-1.55F, -14.2972F, -6.5367F, 3.15F, 0.5F, 1.45F, new CubeDeformation(0.0F)).texOffs(77, 58).addBox(-2.3F, -13.7972F, -6.5367F, 4.65F, 0.75F, 1.45F, new CubeDeformation(0.0F)).texOffs(73, 51)
						.addBox(-2.8F, -13.0472F, -6.5367F, 5.65F, 0.75F, 1.45F, new CubeDeformation(0.0F)).texOffs(64, 32).addBox(-3.3F, -12.2972F, -6.5367F, 6.65F, 0.75F, 1.45F, new CubeDeformation(0.0F)).texOffs(72, 3)
						.addBox(-3.05F, -11.5472F, -6.5367F, 6.15F, 0.5F, 1.45F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition necktop = neck.addOrReplaceChild("necktop",
				CubeListBuilder.create().texOffs(73, 54).addBox(-2.05F, -10.7972F, -8.9867F, 4.15F, 0.75F, 2.45F, new CubeDeformation(0.0F)).texOffs(0, 86).addBox(-0.8F, -14.0472F, -7.9867F, 1.65F, 0.5F, 1.45F, new CubeDeformation(0.0F))
						.texOffs(0, 80).addBox(-1.55F, -13.5472F, -7.9867F, 3.15F, 0.75F, 1.45F, new CubeDeformation(0.0F)).texOffs(77, 45).addBox(-2.05F, -12.7972F, -8.3867F, 4.15F, 0.75F, 1.85F, new CubeDeformation(0.0F)).texOffs(64, 35)
						.addBox(-2.55F, -12.0472F, -8.9867F, 5.15F, 0.75F, 2.45F, new CubeDeformation(0.0F)).texOffs(72, 6).addBox(-2.3F, -11.2972F, -8.9867F, 4.65F, 0.5F, 2.45F, new CubeDeformation(0.0F)).texOffs(79, 35)
						.addBox(-1.55F, -10.0472F, -8.9867F, 3.15F, 0.5F, 2.45F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition head = all.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r10 = head.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(87, 14).addBox(-0.525F, -1.375F, -0.375F, 1.0F, 1.75F, 0.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.875F, -6.6602F, -11.0655F, -0.3806F, -0.0992F, -0.2427F));
		PartDefinition cube_r11 = head.addOrReplaceChild("cube_r11",
				CubeListBuilder.create().texOffs(87, 5).addBox(0.75F, 1.75F, -1.35F, 0.75F, 2.0F, 0.75F, new CubeDeformation(0.0F)).texOffs(7, 87).addBox(-1.75F, 1.75F, -1.35F, 0.75F, 2.0F, 0.75F, new CubeDeformation(0.0F)).texOffs(87, 0)
						.addBox(0.75F, -3.25F, -1.35F, 0.75F, 3.0F, 0.75F, new CubeDeformation(0.0F)).texOffs(77, 61).addBox(-1.0F, -3.25F, -1.5F, 1.75F, 7.0F, 1.25F, new CubeDeformation(0.0F)).texOffs(86, 78)
						.addBox(-1.75F, -3.25F, -1.35F, 0.75F, 3.0F, 0.75F, new CubeDeformation(0.0F)).texOffs(48, 69).addBox(-1.25F, -4.5F, -0.25F, 2.5F, 9.0F, 1.25F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -11.5F, -9.5F, -0.3927F, 0.0F, 0.0F));
		PartDefinition cube_r12 = head.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(12, 87).addBox(2.525F, -5.2F, -0.225F, 0.75F, 3.0F, 0.45F, new CubeDeformation(0.0F)).texOffs(54, 86)
				.addBox(-0.375F, -1.5F, -0.225F, 0.75F, 3.0F, 0.45F, new CubeDeformation(0.0F)).texOffs(35, 75).addBox(1.125F, -3.3F, -0.225F, 0.75F, 4.0F, 0.45F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.925F, -10.5447F, -11.1134F, -0.3361F, -0.2071F, -0.532F));
		PartDefinition cube_r13 = head.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(42, 82).addBox(-0.575F, -1.8F, -0.375F, 0.75F, 4.0F, 0.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.625F, -16.2615F, -8.583F, -0.3789F, -0.1057F, -0.259F));
		PartDefinition cube_r14 = head.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(11, 67).addBox(-0.175F, -1.8F, -0.375F, 0.75F, 4.0F, 0.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.625F, -16.2615F, -8.583F, -0.3789F, 0.1057F, 0.259F));
		PartDefinition cube_r15 = head.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(39, 62).addBox(-1.875F, -3.3F, -0.225F, 0.75F, 4.0F, 0.45F, new CubeDeformation(0.0F)).texOffs(32, 84)
				.addBox(-0.375F, -1.5F, -0.225F, 0.75F, 3.0F, 0.45F, new CubeDeformation(0.0F)).texOffs(87, 10).addBox(-3.275F, -5.2F, -0.225F, 0.75F, 3.0F, 0.45F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.925F, -10.5447F, -11.1134F, -0.3361F, 0.2071F, 0.532F));
		PartDefinition cube_r16 = head.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(86, 87).addBox(-0.35F, 0.5F, -1.5F, 1.0F, 1.75F, 0.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.15F, -7.5F, -9.5F, -0.3927F, 0.0F, 0.0F));
		PartDefinition cube_r17 = head.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(82, 87).addBox(-0.475F, -1.375F, -0.375F, 1.0F, 1.75F, 0.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.875F, -6.6602F, -11.0655F, -0.3806F, 0.0992F, 0.2427F));
		PartDefinition rightarm = all.addOrReplaceChild("rightarm", CubeListBuilder.create(), PartPose.offset(-8.0F, -8.3986F, -2.4441F));
		PartDefinition rightupperarm = rightarm.addOrReplaceChild("rightupperarm", CubeListBuilder.create(), PartPose.offset(8.0F, 8.3986F, 2.4441F));
		PartDefinition cube_r18 = rightupperarm.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(37, 18).addBox(-3.75F, -2.25F, -2.25F, 10.75F, 3.25F, 3.5F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-11.2F, -3.0F, -1.4F, 0.0F, 0.0F, -0.7854F));
		PartDefinition rightforearm = rightarm.addOrReplaceChild("rightforearm", CubeListBuilder.create(), PartPose.offset(-5.8F, 9.8523F, -2.5899F));
		PartDefinition cube_r19 = rightforearm.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(17, 62).addBox(-1.75F, -6.5F, 0.0F, 2.5F, 10.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(68, 58)
				.addBox(-2.0F, -7.0F, -0.75F, 3.0F, 10.75F, 0.75F, new CubeDeformation(0.0F)).texOffs(66, 16).addBox(-1.75F, -6.5F, -2.25F, 2.5F, 10.25F, 1.5F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 1.2964F, -0.766F, -0.7854F, 0.0F, 0.0F));
		PartDefinition righthand = rightarm.addOrReplaceChild("righthand", CubeListBuilder.create(), PartPose.offset(8.0F, 8.3986F, 2.4441F));
		PartDefinition cube_r20 = righthand.addOrReplaceChild("cube_r20",
				CubeListBuilder.create().texOffs(64, 83).addBox(-0.75F, 6.0F, -4.3F, 0.5F, 0.75F, 2.3F, new CubeDeformation(0.0F)).texOffs(24, 75).addBox(-0.75F, 3.5F, -2.0F, 0.5F, 4.25F, 3.5F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-13.3F, 2.75F, -5.8F, -0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r21 = righthand.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(62, 87).addBox(-0.25F, 1.35F, 3.625F, 0.5F, 2.3F, 0.75F, new CubeDeformation(0.0F)).texOffs(57, 87)
				.addBox(-0.25F, 1.35F, 1.625F, 0.5F, 2.3F, 0.75F, new CubeDeformation(0.0F)).texOffs(35, 87).addBox(-0.25F, 1.35F, 2.625F, 0.5F, 2.3F, 0.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-13.8F, 5.0304F, -12.5352F, -0.7854F, 0.0F, 0.0F));
		PartDefinition leftarm = all.addOrReplaceChild("leftarm", CubeListBuilder.create(), PartPose.offset(8.0F, -8.3986F, -2.4441F));
		PartDefinition leftupperarm = leftarm.addOrReplaceChild("leftupperarm", CubeListBuilder.create(), PartPose.offset(-8.0F, 8.3986F, 2.4441F));
		PartDefinition cube_r22 = leftupperarm.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(37, 25).addBox(-7.0F, -2.25F, -2.25F, 10.75F, 3.25F, 3.5F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(11.2F, -3.0F, -1.4F, 0.0F, 0.0F, 0.7854F));
		PartDefinition leftforearm = leftarm.addOrReplaceChild("leftforearm", CubeListBuilder.create(), PartPose.offset(-8.0F, 8.3986F, 2.4441F));
		PartDefinition cube_r23 = leftforearm.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(28, 62).addBox(-0.75F, -6.5F, 0.0F, 2.5F, 10.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(39, 69)
				.addBox(-1.0F, -7.0F, -0.75F, 3.0F, 10.75F, 0.75F, new CubeDeformation(0.0F)).texOffs(0, 67).addBox(-0.75F, -6.5F, -2.25F, 2.5F, 10.25F, 1.5F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(13.3F, 2.75F, -5.8F, -0.7854F, 0.0F, 0.0F));
		PartDefinition lefthand = leftarm.addOrReplaceChild("lefthand", CubeListBuilder.create(), PartPose.offset(-8.0F, 8.3986F, 2.4441F));
		PartDefinition cube_r24 = lefthand.addOrReplaceChild("cube_r24",
				CubeListBuilder.create().texOffs(83, 70).addBox(0.25F, 6.0F, -4.3F, 0.5F, 0.75F, 2.3F, new CubeDeformation(0.0F)).texOffs(77, 14).addBox(0.25F, 3.5F, -2.0F, 0.5F, 4.25F, 3.5F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(13.3F, 2.75F, -5.8F, -0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r25 = lefthand.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(77, 87).addBox(-0.25F, 1.35F, 3.625F, 0.5F, 2.3F, 0.75F, new CubeDeformation(0.0F)).texOffs(72, 87)
				.addBox(-0.25F, 1.35F, 1.625F, 0.5F, 2.3F, 0.75F, new CubeDeformation(0.0F)).texOffs(67, 87).addBox(-0.25F, 1.35F, 2.625F, 0.5F, 2.3F, 0.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(13.8F, 5.0304F, -12.5352F, -0.7854F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.rightleg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.rightarm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.leftleg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.leftarm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
	}
}