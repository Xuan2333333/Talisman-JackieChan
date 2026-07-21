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
public class Modellpdx<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("talisman_jackiechan", "modellpdx"), "main");
	public final ModelPart all2;
	public final ModelPart leftarm;
	public final ModelPart group52;
	public final ModelPart group51;
	public final ModelPart group50;
	public final ModelPart fgv;
	public final ModelPart group49;
	public final ModelPart group48;
	public final ModelPart group47;
	public final ModelPart group46;
	public final ModelPart group45;
	public final ModelPart efr;
	public final ModelPart group44;
	public final ModelPart group43;
	public final ModelPart group42;
	public final ModelPart group41;
	public final ModelPart group40;
	public final ModelPart group39;
	public final ModelPart group38;
	public final ModelPart group37;
	public final ModelPart group36;
	public final ModelPart group35;
	public final ModelPart group34;
	public final ModelPart group33;
	public final ModelPart group32;
	public final ModelPart group31;
	public final ModelPart pldx2;
	public final ModelPart dsghtrgf;
	public final ModelPart t;
	public final ModelPart trhdfs;
	public final ModelPart wet;
	public final ModelPart wet2;
	public final ModelPart hjgf;
	public final ModelPart sides18;
	public final ModelPart sdhjn;
	public final ModelPart rehj;
	public final ModelPart dcvbngf;
	public final ModelPart sides17;
	public final ModelPart uio;
	public final ModelPart yhg;
	public final ModelPart d;
	public final ModelPart sides16;
	public final ModelPart wqr;
	public final ModelPart f;
	public final ModelPart azgv;
	public final ModelPart ghb;
	public final ModelPart bn;
	public final ModelPart xb;
	public final ModelPart sides15;
	public final ModelPart q;
	public final ModelPart qwe;
	public final ModelPart yuth;
	public final ModelPart sides14;
	public final ModelPart rtyui;
	public final ModelPart io;
	public final ModelPart ertgyh;
	public final ModelPart sdv;
	public final ModelPart dsfgdv;
	public final ModelPart dsg2;
	public final ModelPart dsg;
	public final ModelPart sdgsdg;
	public final ModelPart sides12;
	public final ModelPart zfgh;
	public final ModelPart ghj;
	public final ModelPart bnj;
	public final ModelPart sides11;
	public final ModelPart cb;
	public final ModelPart n;
	public final ModelPart qwfr;
	public final ModelPart sides10;
	public final ModelPart LLeg2;
	public final ModelPart sf;
	public final ModelPart ghjk;
	public final ModelPart wqr2;
	public final ModelPart qw;
	public final ModelPart group30;
	public final ModelPart group29;
	public final ModelPart group28;
	public final ModelPart group27;
	public final ModelPart rightarm;
	public final ModelPart leftleg;
	public final ModelPart rightleg;
	public final ModelPart bb_main;

	public Modellpdx(ModelPart root) {
		this.all2 = root.getChild("all2");
		this.leftarm = this.all2.getChild("leftarm");
		this.group52 = this.all2.getChild("group52");
		this.group51 = this.all2.getChild("group51");
		this.group50 = this.all2.getChild("group50");
		this.fgv = this.all2.getChild("fgv");
		this.group49 = this.fgv.getChild("group49");
		this.group48 = this.fgv.getChild("group48");
		this.group47 = this.fgv.getChild("group47");
		this.group46 = this.fgv.getChild("group46");
		this.group45 = this.fgv.getChild("group45");
		this.efr = this.all2.getChild("efr");
		this.group44 = this.efr.getChild("group44");
		this.group43 = this.efr.getChild("group43");
		this.group42 = this.efr.getChild("group42");
		this.group41 = this.efr.getChild("group41");
		this.group40 = this.efr.getChild("group40");
		this.group39 = this.all2.getChild("group39");
		this.group38 = this.all2.getChild("group38");
		this.group37 = this.all2.getChild("group37");
		this.group36 = this.all2.getChild("group36");
		this.group35 = this.all2.getChild("group35");
		this.group34 = this.all2.getChild("group34");
		this.group33 = this.all2.getChild("group33");
		this.group32 = this.all2.getChild("group32");
		this.group31 = this.all2.getChild("group31");
		this.pldx2 = this.all2.getChild("pldx2");
		this.dsghtrgf = this.pldx2.getChild("dsghtrgf");
		this.t = this.dsghtrgf.getChild("t");
		this.trhdfs = this.t.getChild("trhdfs");
		this.wet = this.trhdfs.getChild("wet");
		this.wet2 = this.wet.getChild("wet2");
		this.hjgf = this.wet.getChild("hjgf");
		this.sides18 = this.wet.getChild("sides18");
		this.sdhjn = this.t.getChild("sdhjn");
		this.rehj = this.sdhjn.getChild("rehj");
		this.dcvbngf = this.sdhjn.getChild("dcvbngf");
		this.sides17 = this.sdhjn.getChild("sides17");
		this.uio = this.t.getChild("uio");
		this.yhg = this.uio.getChild("yhg");
		this.d = this.uio.getChild("d");
		this.sides16 = this.uio.getChild("sides16");
		this.wqr = this.pldx2.getChild("wqr");
		this.f = this.wqr.getChild("f");
		this.azgv = this.f.getChild("azgv");
		this.ghb = this.azgv.getChild("ghb");
		this.bn = this.ghb.getChild("bn");
		this.xb = this.ghb.getChild("xb");
		this.sides15 = this.ghb.getChild("sides15");
		this.q = this.f.getChild("q");
		this.qwe = this.q.getChild("qwe");
		this.yuth = this.q.getChild("yuth");
		this.sides14 = this.q.getChild("sides14");
		this.rtyui = this.f.getChild("rtyui");
		this.io = this.rtyui.getChild("io");
		this.ertgyh = this.rtyui.getChild("ertgyh");
		this.sdv = this.pldx2.getChild("sdv");
		this.dsfgdv = this.sdv.getChild("dsfgdv");
		this.dsg2 = this.dsfgdv.getChild("dsg2");
		this.dsg = this.dsg2.getChild("dsg");
		this.sdgsdg = this.dsg2.getChild("sdgsdg");
		this.sides12 = this.dsg2.getChild("sides12");
		this.zfgh = this.sdv.getChild("zfgh");
		this.ghj = this.zfgh.getChild("ghj");
		this.bnj = this.zfgh.getChild("bnj");
		this.sides11 = this.zfgh.getChild("sides11");
		this.cb = this.sdv.getChild("cb");
		this.n = this.cb.getChild("n");
		this.qwfr = this.cb.getChild("qwfr");
		this.sides10 = this.cb.getChild("sides10");
		this.LLeg2 = this.pldx2.getChild("LLeg2");
		this.sf = this.pldx2.getChild("sf");
		this.ghjk = this.sf.getChild("ghjk");
		this.wqr2 = this.pldx2.getChild("wqr2");
		this.qw = this.wqr2.getChild("qw");
		this.group30 = this.wqr2.getChild("group30");
		this.group29 = this.group30.getChild("group29");
		this.group28 = this.wqr2.getChild("group28");
		this.group27 = this.group28.getChild("group27");
		this.rightarm = this.all2.getChild("rightarm");
		this.leftleg = this.all2.getChild("leftleg");
		this.rightleg = this.all2.getChild("rightleg");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition all2 = partdefinition.addOrReplaceChild("all2",
				CubeListBuilder.create().texOffs(36, 223).addBox(1.25F, -9.0F, -3.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(64, 81).addBox(-1.0F, -5.0F, -5.0F, 2.0F, 9.0F, 0.8F, new CubeDeformation(0.0F)).texOffs(16, 9)
						.addBox(-4.25F, -9.0F, -3.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(16, 68).addBox(-2.0F, -6.75F, -4.6F, 4.0F, 8.0F, 1.25F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r1 = all2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(88, 117).addBox(-1.5F, -0.5F, -0.375F, 3.0F, 1.0F, 0.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.7675F, -7.8446F, -3.825F, 0.0F, 0.0F, -0.3927F));
		PartDefinition cube_r2 = all2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(80, 117).addBox(-2.5F, -1.0F, -0.5F, 3.0F, 1.0F, 0.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, -7.0F, -3.7F, 0.0F, 0.0F, 0.3927F));
		PartDefinition cube_r3 = all2.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(64, 43).addBox(6.0F, -1.0F, 0.45F, 9.0F, 1.0F, 0.55F, new CubeDeformation(0.0F)).texOffs(62, 41).addBox(6.0F, -1.0F, 7.2F, 9.0F, 1.0F, 0.55F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, 5.95F, -4.25F, 0.0F, 0.0F, -0.7854F));
		PartDefinition cube_r4 = all2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(70, 16).addBox(8.0F, -1.0F, 0.45F, 7.0F, 1.0F, 0.55F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.5F, 11.95F, 2.5F, 0.0F, 0.0F, -0.7854F));
		PartDefinition cube_r5 = all2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(50, 16).addBox(-4.5F, -0.5F, -0.275F, 9.0F, 1.0F, 0.55F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0711F, 3.1718F, -3.525F, 0.0F, 0.0F, -0.7854F));
		PartDefinition cube_r6 = all2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(112, 103).addBox(-0.375F, -0.625F, -1.5F, 0.75F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.875F, 6.6587F, -2.4131F, -0.3927F, 0.0F, 0.0F));
		PartDefinition cube_r7 = all2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(112, 94).addBox(-0.5F, -0.625F, -1.0F, 0.75F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.75F, 6.75F, 1.375F, 0.3927F, 0.0F, 0.0F));
		PartDefinition leftarm = all2.addOrReplaceChild("leftarm", CubeListBuilder.create(), PartPose.offset(7.25F, 1.25F, 0.0F));
		PartDefinition cube_r8 = leftarm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(84, 64).mirror().addBox(-1.25F, -1.75F, -1.5F, 2.5F, 3.0F, 2.5F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-0.25F, 7.5F, -7.5F, 0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r9 = leftarm.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-2.76F, -2.0F, -2.75F, 3.5F, 4.0F, 4.75F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.75F, 4.8652F, -2.9207F, 0.3927F, 0.0F, 0.0F));
		PartDefinition cube_r10 = leftarm.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.75F, -3.25F, -2.0F, 3.5F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.75F, 1.75F, -1.0F, -0.3927F, 0.0F, 0.0F));
		PartDefinition cube_r11 = leftarm.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 43).mirror().addBox(-2.25F, -1.5F, -3.0F, 2.5F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.75F, 5.8652F, -2.9207F, 0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r12 = leftarm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(50, 89).mirror().addBox(-1.0F, -2.75F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-0.25F, 7.6332F, -3.3303F, -0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r13 = leftarm.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(58, 92).mirror().addBox(-1.0F, -2.75F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-0.25F, 7.1332F, -3.3303F, -0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r14 = leftarm.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(17, 78).mirror().addBox(-2.0F, -5.0F, -1.5F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.75F, 9.8652F, -2.9207F, -0.3927F, 0.0F, 0.0F));
		PartDefinition cube_r15 = leftarm.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(32, 36).mirror().addBox(-2.75F, -2.25F, -1.0F, 3.5F, 8.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.75F, 0.75F, -3.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition R_r1 = leftarm.addOrReplaceChild("R_r1", CubeListBuilder.create().texOffs(0, 55).mirror().addBox(-1.25F, -1.75F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition group52 = all2.addOrReplaceChild("group52",
				CubeListBuilder.create().texOffs(26, 49).addBox(8.0711F, -6.1498F, 2.802F, 1.0F, 2.0F, 0.55F, new CubeDeformation(0.0F)).texOffs(26, 72).addBox(8.0711F, -6.1498F, -3.198F, 1.0F, 2.0F, 0.55F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r16 = group52.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(34, 86).addBox(-0.5F, -0.725F, -1.2F, 1.0F, 1.0F, 2.4F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8.5711F, -6.2146F, -1.7002F, 0.3927F, 0.0F, 0.0F));
		PartDefinition cube_r17 = group52.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(140, 100).addBox(-0.5F, -0.725F, -1.5F, 1.0F, 1.0F, 2.4F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8.5711F, -6.0998F, 2.077F, -0.3927F, 0.0F, 0.0F));
		PartDefinition group51 = all2.addOrReplaceChild("group51", CubeListBuilder.create().texOffs(40, 99).addBox(-5.75F, 6.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(32, 56)
				.addBox(-5.75F, 5.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 116).addBox(-5.75F, 6.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition group50 = all2.addOrReplaceChild("group50", CubeListBuilder.create().texOffs(56, 115).addBox(7.5F, -7.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(56, 99)
				.addBox(8.0F, -7.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(180, 59).addBox(7.0F, -7.0F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition fgv = all2.addOrReplaceChild("fgv", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition group49 = fgv.addOrReplaceChild("group49", CubeListBuilder.create().texOffs(96, 67).addBox(-1.9F, -12.8F, 9.8F, 3.0F, 1.0F, 1.75F, new CubeDeformation(0.0F)).texOffs(16, 116)
				.addBox(-0.9F, -13.8F, 9.8F, 1.0F, 3.0F, 1.75F, new CubeDeformation(0.0F)).texOffs(112, 107).addBox(-1.4F, -13.3F, 9.8F, 2.0F, 2.0F, 1.75F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 8.0F, -8.0F));
		PartDefinition group48 = fgv.addOrReplaceChild("group48", CubeListBuilder.create().texOffs(175, 100).addBox(-13.26F, -2.5F, 10.55F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(26, 68)
				.addBox(-12.25F, -3.5F, 10.55F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(96, 117).addBox(-12.75F, -3.0F, 10.55F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 8.0F, -8.0F));
		PartDefinition group47 = fgv.addOrReplaceChild("group47", CubeListBuilder.create().texOffs(80, 58).addBox(-10.25F, -5.25F, 10.55F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(12, 69)
				.addBox(-9.25F, -6.25F, 10.55F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(102, 117).addBox(-9.75F, -5.75F, 10.55F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 8.0F, -8.0F));
		PartDefinition group46 = fgv.addOrReplaceChild("group46", CubeListBuilder.create().texOffs(84, 43).addBox(-7.5F, -7.75F, 10.55F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 85)
				.addBox(-6.5F, -8.75F, 10.55F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(118, 0).addBox(-7.0F, -8.25F, 10.55F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 8.0F, -8.0F));
		PartDefinition group45 = fgv.addOrReplaceChild("group45", CubeListBuilder.create().texOffs(114, 58).addBox(-4.5F, -10.5F, 10.55F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(16, 96)
				.addBox(-3.5F, -11.5F, 10.55F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(118, 3).addBox(-4.0F, -11.0F, 10.55F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 8.0F, -8.0F));
		PartDefinition efr = all2.addOrReplaceChild("efr", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition group44 = efr.addOrReplaceChild("group44", CubeListBuilder.create().texOffs(66, 97).addBox(-1.5F, -13.0F, 4.05F, 3.0F, 1.0F, 1.75F, new CubeDeformation(0.0F)).texOffs(22, 116)
				.addBox(-0.5F, -13.98F, 4.05F, 1.0F, 3.0F, 1.75F, new CubeDeformation(0.0F)).texOffs(112, 111).addBox(-1.0F, -13.5F, 4.05F, 2.0F, 2.0F, 1.75F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 8.0F, -8.0F));
		PartDefinition group43 = efr.addOrReplaceChild("group43", CubeListBuilder.create().texOffs(48, 116).addBox(-13.25F, -2.25F, 4.05F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(108, 117)
				.addBox(-12.25F, -3.25F, 4.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(118, 6).addBox(-12.75F, -2.75F, 4.05F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 8.0F, -8.0F));
		PartDefinition group42 = efr.addOrReplaceChild("group42", CubeListBuilder.create().texOffs(64, 116).addBox(-10.25F, -4.25F, 4.05F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(60, 118)
				.addBox(-9.25F, -5.25F, 4.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(118, 9).addBox(-9.75F, -4.75F, 4.05F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 8.0F, -8.0F));
		PartDefinition group41 = efr.addOrReplaceChild("group41", CubeListBuilder.create().texOffs(116, 85).addBox(-7.5F, -7.75F, 4.05F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(64, 118)
				.addBox(-6.5F, -8.75F, 4.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(48, 118).addBox(-7.0F, -8.25F, 4.05F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 8.0F, -8.0F));
		PartDefinition group40 = efr.addOrReplaceChild("group40", CubeListBuilder.create().texOffs(116, 87).addBox(-4.5F, -10.5F, 4.05F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(68, 118)
				.addBox(-3.5F, -11.5F, 4.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(54, 118).addBox(-4.0F, -11.0F, 4.05F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 8.0F, -8.0F));
		PartDefinition group39 = all2.addOrReplaceChild("group39", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition group38 = all2.addOrReplaceChild("group38",
				CubeListBuilder.create().texOffs(34, 47).addBox(-0.35F, 0.05F, -1.1F, 0.9F, 1.0F, 0.45F, new CubeDeformation(0.0F)).texOffs(30, 47).addBox(0.15F, -0.05F, -1.1F, 0.9F, 1.0F, 0.45F, new CubeDeformation(0.0F)).texOffs(30, 39)
						.addBox(-0.1F, 0.0F, -1.1F, 0.9F, 1.0F, 0.45F, new CubeDeformation(0.0F)).texOffs(30, 42).addBox(0.15F, -0.1F, -1.1F, 0.9F, 1.0F, 0.45F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-0.45F, 1.2F, -1.1F, 0.9F, 0.0F, 0.45F, new CubeDeformation(0.0F)).texOffs(30, 43).addBox(-0.6F, 0.4F, -1.1F, 0.9F, 1.0F, 0.45F, new CubeDeformation(0.0F)).texOffs(32, 47)
						.addBox(0.4F, -0.15F, -1.1F, 0.9F, 1.0F, 0.45F, new CubeDeformation(0.0F)),
				PartPose.offset(1.25F, -11.75F, -2.25F));
		PartDefinition group37 = all2.addOrReplaceChild("group37", CubeListBuilder.create().texOffs(8, 162).addBox(6.8F, -15.0F, 4.3F, 2.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 6.0F, -8.0F));
		PartDefinition group36 = all2.addOrReplaceChild("group36", CubeListBuilder.create().texOffs(55, 168).addBox(7.75F, -15.0F, 2.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 5.0F, -5.0F));
		PartDefinition group35 = all2.addOrReplaceChild("group35",
				CubeListBuilder.create().texOffs(30, 44).addBox(-0.55F, 0.05F, -1.1F, 0.9F, 1.0F, 0.45F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-0.8F, 0.85F, -1.1F, 0.9F, 0.0F, 0.45F, new CubeDeformation(0.0F)).texOffs(30, 45)
						.addBox(-1.05F, -0.05F, -1.1F, 0.9F, 1.0F, 0.45F, new CubeDeformation(0.0F)).texOffs(30, 38).addBox(-0.8F, 0.0F, -1.1F, 0.9F, 1.0F, 0.45F, new CubeDeformation(0.0F)).texOffs(30, 40)
						.addBox(-1.05F, -0.1F, -1.1F, 0.9F, 1.0F, 0.45F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-0.45F, 1.2F, -1.1F, 0.9F, 0.0F, 0.45F, new CubeDeformation(0.0F)).texOffs(30, 41)
						.addBox(-0.3F, 0.4F, -1.1F, 0.9F, 1.0F, 0.45F, new CubeDeformation(0.0F)).texOffs(30, 46).addBox(-1.3F, -0.15F, -1.1F, 0.9F, 1.0F, 0.45F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.25F, -11.75F, -2.25F));
		PartDefinition group34 = all2.addOrReplaceChild("group34", CubeListBuilder.create().texOffs(8, 75).addBox(-9.1F, -15.0F, 4.3F, 2.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(58, 81)
				.addBox(-9.0F, -16.0F, 4.3F, 1.0F, 9.0F, 1.5F, new CubeDeformation(0.0F)).texOffs(58, 81).addBox(-3.7F, -16.0F, 4.3F, 1.0F, 9.0F, 1.5F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 6.0F, -8.0F));
		PartDefinition group33 = all2.addOrReplaceChild("group33", CubeListBuilder.create(), PartPose.offset(6.0F, 5.0F, -5.0F));
		PartDefinition group32 = all2.addOrReplaceChild("group32", CubeListBuilder.create().texOffs(46, 44).addBox(-9.7F, -11.0F, 1.2F, 7.4F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 5.0F, -5.0F));
		PartDefinition group31 = all2.addOrReplaceChild("group31", CubeListBuilder.create().texOffs(16, 28).addBox(-9.75F, -15.0F, 2.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 5.0F, -5.0F));
		PartDefinition pldx2 = all2.addOrReplaceChild("pldx2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition dsghtrgf = pldx2.addOrReplaceChild("dsghtrgf", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition t = dsghtrgf.addOrReplaceChild("t", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition trhdfs = t.addOrReplaceChild("trhdfs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition wet = trhdfs.addOrReplaceChild("wet", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition wet2 = wet.addOrReplaceChild("wet2",
				CubeListBuilder.create().texOffs(76, 99).addBox(-5.0F, 4.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 100).addBox(-3.0F, 4.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(8, 100)
						.addBox(-1.0F, 4.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(100, 15).addBox(1.0F, 4.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(16, 100)
						.addBox(3.0F, 4.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition hjgf = wet.addOrReplaceChild("hjgf",
				CubeListBuilder.create().texOffs(24, 100).addBox(-5.0F, 4.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(32, 100).addBox(-3.0F, 4.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(48, 100)
						.addBox(-1.0F, 4.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(64, 100).addBox(1.0F, 4.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(100, 87)
						.addBox(3.0F, 4.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition sides18 = wet.addOrReplaceChild("sides18",
				CubeListBuilder.create().texOffs(84, 101).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(92, 101).addBox(-10.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 4.0F, -1.0F));
		PartDefinition sdhjn = t.addOrReplaceChild("sdhjn", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition rehj = sdhjn.addOrReplaceChild("rehj",
				CubeListBuilder.create().texOffs(8, 116).addBox(-4.75F, 6.0F, 1.0F, 1.75F, 2.0F, 1.6F, new CubeDeformation(0.0F)).texOffs(72, 115).addBox(-3.0F, 6.0F, 1.0F, 2.0F, 2.0F, 1.6F, new CubeDeformation(0.0F)).texOffs(112, 115)
						.addBox(-1.0F, 6.0F, 1.0F, 2.0F, 2.0F, 1.6F, new CubeDeformation(0.0F)).texOffs(0, 116).addBox(1.0F, 6.0F, 1.0F, 2.0F, 2.0F, 1.6F, new CubeDeformation(0.0F)).texOffs(116, 12)
						.addBox(3.0F, 6.0F, 1.0F, 1.75F, 2.0F, 1.6F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition dcvbngf = sdhjn.addOrReplaceChild("dcvbngf",
				CubeListBuilder.create().texOffs(114, 64).addBox(-4.75F, 6.0F, -2.85F, 1.75F, 2.0F, 1.85F, new CubeDeformation(0.0F)).texOffs(112, 69).addBox(-3.0F, 6.0F, -2.85F, 2.0F, 2.0F, 1.85F, new CubeDeformation(0.0F)).texOffs(112, 73)
						.addBox(-1.0F, 6.0F, -2.85F, 2.0F, 2.0F, 1.85F, new CubeDeformation(0.0F)).texOffs(112, 77).addBox(1.0F, 6.0F, -2.85F, 2.0F, 2.0F, 1.85F, new CubeDeformation(0.0F)).texOffs(40, 115)
						.addBox(3.0F, 6.0F, -2.85F, 1.75F, 2.0F, 1.85F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition sides17 = sdhjn.addOrReplaceChild("sides17",
				CubeListBuilder.create().texOffs(112, 81).addBox(-2.0F, 2.0F, 0.0F, 1.75F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(112, 90).addBox(-9.75F, 2.0F, 0.0F, 1.75F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 4.0F, -1.0F));
		PartDefinition uio = t.addOrReplaceChild("uio", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition yhg = uio.addOrReplaceChild("yhg",
				CubeListBuilder.create().texOffs(100, 101).addBox(-5.0F, 8.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(102, 0).addBox(-3.0F, 8.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(102, 4)
						.addBox(-1.0F, 8.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(102, 8).addBox(1.0F, 8.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(40, 103)
						.addBox(3.0F, 8.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition d = uio.addOrReplaceChild("d",
				CubeListBuilder.create().texOffs(56, 103).addBox(-5.0F, 8.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(72, 103).addBox(-3.0F, 8.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 104)
						.addBox(-1.0F, 8.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(8, 104).addBox(1.0F, 8.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(16, 104)
						.addBox(3.0F, 8.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition sides16 = uio.addOrReplaceChild("sides16",
				CubeListBuilder.create().texOffs(104, 19).addBox(-2.0F, 4.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(104, 23).addBox(-10.0F, 4.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 4.0F, -1.0F));
		PartDefinition wqr = pldx2.addOrReplaceChild("wqr", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition f = wqr.addOrReplaceChild("f", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition azgv = f.addOrReplaceChild("azgv", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition ghb = azgv.addOrReplaceChild("ghb", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition bn = ghb.addOrReplaceChild("bn",
				CubeListBuilder.create().texOffs(66, 45).addBox(-9.0F, -2.0F, 1.0F, 6.0F, 2.0F, 1.75F, new CubeDeformation(0.0F)).texOffs(24, 104).addBox(-3.0F, -2.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(104, 27)
						.addBox(-1.0F, -2.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(32, 104).addBox(1.0F, -2.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(104, 36)
						.addBox(3.0F, -2.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(94, 74).addBox(-8.0F, -2.0F, 1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(80, 12)
						.addBox(5.0F, -2.0F, 1.0F, 4.0F, 2.0F, 1.75F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition xb = ghb.addOrReplaceChild("xb",
				CubeListBuilder.create().texOffs(104, 40).addBox(-5.0F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(104, 44).addBox(-3.0F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(48, 104)
						.addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(104, 48).addBox(1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(104, 52)
						.addBox(3.0F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(80, 54).addBox(5.0F, -2.0F, -2.75F, 4.0F, 2.0F, 1.75F, new CubeDeformation(0.0F)).texOffs(82, 39)
						.addBox(-9.0F, -2.0F, -2.75F, 4.0F, 2.0F, 1.75F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition sides15 = ghb.addOrReplaceChild("sides15",
				CubeListBuilder.create().texOffs(94, 78).addBox(1.0F, -6.0F, 0.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(80, 0).addBox(-14.0F, -6.0F, 0.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 4.0F, -1.0F));
		PartDefinition q = f.addOrReplaceChild("q", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition qwe = q.addOrReplaceChild("qwe",
				CubeListBuilder.create().texOffs(80, 113).addBox(-5.0F, 0.0F, 1.0F, 2.0F, 2.0F, 1.75F, new CubeDeformation(0.0F)).texOffs(82, 45).addBox(-9.0F, 0.0F, 1.0F, 4.0F, 2.0F, 1.75F, new CubeDeformation(0.0F)).texOffs(88, 113)
						.addBox(-3.0F, 0.0F, 1.0F, 2.0F, 2.0F, 1.75F, new CubeDeformation(0.0F)).texOffs(96, 113).addBox(-1.0F, 0.0F, 1.0F, 2.0F, 2.0F, 1.75F, new CubeDeformation(0.0F)).texOffs(104, 113)
						.addBox(1.0F, 0.0F, 1.0F, 2.0F, 2.0F, 1.75F, new CubeDeformation(0.0F)).texOffs(114, 28).addBox(3.0F, 0.0F, 1.0F, 2.0F, 2.0F, 1.75F, new CubeDeformation(0.0F)).texOffs(66, 93)
						.addBox(5.0F, 0.0F, 1.0F, 4.0F, 2.0F, 1.5F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition yuth = q.addOrReplaceChild("yuth",
				CubeListBuilder.create().texOffs(104, 61).addBox(-5.0F, 0.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(82, 78).addBox(-9.0F, 0.0F, -2.75F, 4.0F, 2.0F, 1.75F, new CubeDeformation(0.0F)).texOffs(64, 104)
						.addBox(-3.0F, 0.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(104, 70).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(104, 74)
						.addBox(1.0F, 0.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(104, 78).addBox(3.0F, 0.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(74, 60)
						.addBox(4.0F, 0.0F, -2.75F, 5.0F, 2.0F, 1.75F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition sides14 = q.addOrReplaceChild("sides14",
				CubeListBuilder.create().texOffs(80, 4).addBox(0.0F, -4.0F, 0.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(80, 8).addBox(-14.0F, -4.0F, 0.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 4.0F, -1.0F));
		PartDefinition rtyui = f.addOrReplaceChild("rtyui", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition io = rtyui.addOrReplaceChild("io",
				CubeListBuilder.create().texOffs(104, 82).addBox(-5.0F, 2.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(104, 91).addBox(-3.0F, 2.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(104, 95)
						.addBox(-1.0F, 2.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(80, 105).addBox(1.0F, 2.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(88, 105)
						.addBox(3.0F, 2.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition ertgyh = rtyui.addOrReplaceChild("ertgyh",
				CubeListBuilder.create().texOffs(0, 69).addBox(-5.0F, 2.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(96, 105).addBox(-3.0F, 2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(104, 105)
						.addBox(-1.0F, 2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(106, 31).addBox(1.0F, 2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(72, 66)
						.addBox(3.0F, 2.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition sdv = pldx2.addOrReplaceChild("sdv", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition dsfgdv = sdv.addOrReplaceChild("dsfgdv", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition dsg2 = dsfgdv.addOrReplaceChild("dsg2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition dsg = dsg2.addOrReplaceChild("dsg",
				CubeListBuilder.create().texOffs(106, 56).addBox(-5.0F, 10.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(106, 65).addBox(-3.0F, 10.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(40, 107)
						.addBox(-1.0F, 10.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(56, 107).addBox(1.0F, 10.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(72, 107)
						.addBox(3.0F, 10.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition sdgsdg = dsg2.addOrReplaceChild("sdgsdg",
				CubeListBuilder.create().texOffs(0, 108).addBox(-5.0F, 10.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(8, 108).addBox(-3.0F, 10.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(108, 12)
						.addBox(-1.0F, 10.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(16, 108).addBox(1.0F, 10.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(24, 108)
						.addBox(3.0F, 10.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition sides12 = dsg2.addOrReplaceChild("sides12",
				CubeListBuilder.create().texOffs(32, 108).addBox(-2.0F, 6.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(48, 108).addBox(-10.0F, 6.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 4.0F, -1.0F));
		PartDefinition zfgh = sdv.addOrReplaceChild("zfgh", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition ghj = zfgh.addOrReplaceChild("ghj",
				CubeListBuilder.create().texOffs(96, 25).addBox(-5.0F, 12.0F, 1.0F, 2.0F, 2.0F, 2.1F, new CubeDeformation(0.0F)).texOffs(96, 29).addBox(-3.0F, 12.0F, 1.0F, 2.0F, 2.0F, 2.1F, new CubeDeformation(0.0F)).texOffs(48, 96)
						.addBox(-1.0F, 12.0F, 1.0F, 2.0F, 2.0F, 2.1F, new CubeDeformation(0.0F)).texOffs(96, 63).addBox(1.0F, 12.0F, 1.0F, 2.0F, 2.0F, 2.1F, new CubeDeformation(0.0F)).texOffs(96, 94)
						.addBox(3.0F, 12.0F, 1.0F, 2.0F, 2.0F, 2.1F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition bnj = zfgh.addOrReplaceChild("bnj",
				CubeListBuilder.create().texOffs(88, 94).addBox(-5.0F, 12.0F, -3.2F, 2.0F, 2.0F, 2.2F, new CubeDeformation(0.0F)).texOffs(40, 95).addBox(-3.0F, 12.0F, -3.2F, 2.0F, 2.0F, 2.2F, new CubeDeformation(0.0F)).texOffs(0, 96)
						.addBox(-1.0F, 12.0F, -3.2F, 2.0F, 2.0F, 2.2F, new CubeDeformation(0.0F)).texOffs(8, 96).addBox(1.0F, 12.0F, -3.2F, 2.0F, 2.0F, 2.2F, new CubeDeformation(0.0F)).texOffs(96, 21)
						.addBox(3.0F, 12.0F, -3.2F, 2.0F, 2.0F, 2.2F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition sides11 = zfgh.addOrReplaceChild("sides11",
				CubeListBuilder.create().texOffs(64, 108).addBox(-2.0F, 8.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(108, 86).addBox(-10.0F, 8.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 4.0F, -1.0F));
		PartDefinition cb = sdv.addOrReplaceChild("cb", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition n = cb.addOrReplaceChild("n",
				CubeListBuilder.create().texOffs(98, 33).addBox(-5.0F, 13.25F, 1.0F, 2.0F, 2.0F, 2.3F, new CubeDeformation(0.0F)).texOffs(98, 58).addBox(-3.0F, 13.25F, 1.0F, 2.0F, 2.0F, 2.3F, new CubeDeformation(0.0F)).texOffs(30, 29)
						.addBox(-3.0F, 13.25F, -3.0F, 5.0F, 2.0F, 6.3F, new CubeDeformation(0.0F)).texOffs(88, 98).addBox(1.0F, 13.25F, 1.0F, 2.0F, 2.0F, 2.3F, new CubeDeformation(0.0F)).texOffs(96, 98)
						.addBox(3.0F, 13.25F, 1.0F, 2.0F, 2.0F, 2.3F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition qwfr = cb.addOrReplaceChild("qwfr",
				CubeListBuilder.create().texOffs(84, 191).addBox(-5.0F, 13.25F, -3.4F, 2.0F, 2.0F, 2.4F, new CubeDeformation(0.0F)).texOffs(110, 157).addBox(-3.0F, 13.25F, -3.4F, 2.0F, 2.0F, 2.4F, new CubeDeformation(0.0F)).texOffs(78, 31)
						.addBox(-1.0F, 13.25F, -3.4F, 2.0F, 2.0F, 2.4F, new CubeDeformation(0.0F)).texOffs(84, 75).addBox(1.0F, 13.25F, -3.4F, 2.0F, 2.0F, 2.4F, new CubeDeformation(0.0F)).texOffs(92, 55)
						.addBox(3.0F, 13.25F, -3.4F, 2.0F, 2.0F, 2.4F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition sides10 = cb.addOrReplaceChild("sides10",
				CubeListBuilder.create().texOffs(114, 32).addBox(-2.0F, 9.25F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(114, 55).addBox(-10.0F, 9.25F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 4.0F, -1.0F));
		PartDefinition LLeg2 = pldx2.addOrReplaceChild("LLeg2", CubeListBuilder.create().texOffs(26, 75).addBox(2.0F, 13.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition sf = pldx2.addOrReplaceChild("sf",
				CubeListBuilder.create().texOffs(48, 18).addBox(3.0F, -4.0F, -3.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(108, 99).addBox(1.0F, -4.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(80, 109)
						.addBox(-1.0F, -4.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(88, 109).addBox(-3.0F, -4.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(48, 22)
						.addBox(-10.0F, -4.0F, -3.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition ghjk = sf.addOrReplaceChild("ghjk",
				CubeListBuilder.create().texOffs(48, 26).addBox(3.0F, -4.0F, 1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(96, 109).addBox(1.0F, -4.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(104, 109)
						.addBox(-1.0F, -4.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(110, 0).addBox(-3.0F, -4.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(30, 48)
						.addBox(-10.0F, -4.0F, 1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(48, 30).addBox(3.0F, -4.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(48, 48)
						.addBox(-10.0F, -4.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition wqr2 = pldx2.addOrReplaceChild("wqr2",
				CubeListBuilder.create().texOffs(50, 0).addBox(3.0F, -6.0F, -3.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(110, 4).addBox(1.0F, -6.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(110, 8)
						.addBox(-1.0F, -6.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(40, 111).addBox(-3.0F, -6.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(50, 4)
						.addBox(-10.0F, -6.0F, -3.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition qw = wqr2.addOrReplaceChild("qw",
				CubeListBuilder.create().texOffs(50, 8).addBox(3.0F, -6.0F, 1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(56, 111).addBox(1.0F, -6.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(72, 111)
						.addBox(-1.0F, -6.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 112).addBox(-3.0F, -6.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(50, 12)
						.addBox(-10.0F, -6.0F, 1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 51).addBox(3.0F, -6.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(18, 52)
						.addBox(-10.0F, -6.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(8, 112).addBox(-3.0F, -8.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(24, 84)
						.addBox(-3.0F, -8.0F, -3.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(16, 112).addBox(-1.0F, -10.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-1.0F, -10.25F, -3.25F, 0.25F, 0.0F, 0.25F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(0.75F, -10.25F, -3.25F, 0.25F, 0.0F, 0.25F, new CubeDeformation(0.0F)).texOffs(112, 16)
						.addBox(-3.0F, -10.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(112, 20).addBox(-1.0F, -8.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(-1, 86)
						.addBox(-2.0F, -8.0F, -3.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(24, 112).addBox(-1.0F, -9.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(34, 75)
						.addBox(-1.0F, -9.0F, 3.5F, 2.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(54, 67).addBox(0.0F, -13.0F, 3.25F, 2.0F, 9.0F, 1.25F, new CubeDeformation(0.0F)).texOffs(34, 116)
						.addBox(2.0F, -13.0F, 3.05F, 2.0F, 3.0F, 0.95F, new CubeDeformation(0.0F)).texOffs(111, 23).addBox(1.0F, -11.0F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(10, 86)
						.addBox(1.0F, -11.0F, -3.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(36, 52).addBox(1.0F, -14.98F, -3.2F, 2.2F, 4.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(85, 22)
						.addBox(1.0F, -13.0F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(60, 74).addBox(-1.0F, -14.98F, -3.2F, 2.0F, 4.0F, 3.2F, new CubeDeformation(0.0F)).texOffs(86, 28)
						.addBox(-1.0F, -13.0F, 1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(48, 52).addBox(-3.2F, -14.98F, -3.2F, 2.2F, 4.0F, 4.45F, new CubeDeformation(0.0F)).texOffs(82, 87)
						.addBox(-3.0F, -13.0F, 1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(94, 44).addBox(-5.0F, -13.75F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(94, 70)
						.addBox(3.0F, -13.75F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(88, 33).addBox(3.0F, -15.0F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(88, 58)
						.addBox(1.0F, -15.0F, -2.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(70, 88).addBox(1.0F, -15.0F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(68, 8)
						.addBox(-6.0F, -15.25F, -0.35F, 2.0F, 4.0F, 3.85F, new CubeDeformation(0.0F)).texOffs(163, 149).addBox(-2.0F, -21.0F, 0.4F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(58, 139)
						.addBox(-1.0F, -15.0F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(40, 90).addBox(-3.0F, -15.0F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(72, 119)
						.addBox(-5.0F, -15.0F, 0.5F, 2.0F, 2.0F, 0.5F, new CubeDeformation(0.0F)).texOffs(0, 91).addBox(-5.0F, -15.0F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(9, 91)
						.addBox(-2.0F, -11.0F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(92, 0).addBox(-3.0F, -11.0F, -3.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(32, 112)
						.addBox(-3.0F, -11.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(112, 35).addBox(-3.0F, -11.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(112, 39)
						.addBox(-1.0F, -11.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(112, 43).addBox(-1.0F, -11.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(30, 94)
						.addBox(-3.0F, -8.0F, 2.0F, 1.0F, 2.0F, 2.5F, new CubeDeformation(0.0F)).texOffs(94, 38).addBox(-3.0F, -11.0F, 2.0F, 1.0F, 3.0F, 2.5F, new CubeDeformation(0.0F)).texOffs(112, 47)
						.addBox(-1.0F, -10.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(112, 51).addBox(1.0F, -10.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(112, 60)
						.addBox(1.0F, -8.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(92, 5).addBox(1.0F, -8.0F, -3.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(64, 112)
						.addBox(1.0F, -10.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(66, 18).addBox(4.0F, -15.25F, -0.6F, 2.0F, 4.0F, 4.1F, new CubeDeformation(0.0F)).texOffs(74, 49)
						.addBox(-2.0F, -13.0F, 3.25F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(116, 98).addBox(-4.0F, -13.0F, 3.05F, 2.0F, 3.0F, 0.95F, new CubeDeformation(0.0F)).texOffs(165, 68).mirror()
						.addBox(2.0F, -11.0F, 3.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(30, 94).mirror().addBox(2.0F, -8.0F, 2.0F, 1.0F, 2.0F, 2.5F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition group30 = wqr2.addOrReplaceChild("group30", CubeListBuilder.create().texOffs(32, 60).addBox(-2.0F, -2.0F, -1.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(46, 60)
				.addBox(-3.0F, -2.75F, -1.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(60, 52).addBox(-4.0F, -3.5F, -1.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -4.5F, -1.0F));
		PartDefinition group29 = group30.addOrReplaceChild("group29", CubeListBuilder.create(), PartPose.offset(-4.0F, 28.5F, -2.0F));
		PartDefinition group28 = wqr2.addOrReplaceChild("group28", CubeListBuilder.create().texOffs(16, 61).addBox(1.0F, -2.75F, -1.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(62, 34)
				.addBox(2.0F, -3.5F, -1.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(60, 59).addBox(0.0F, -2.0F, -1.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -4.5F, -1.0F));
		PartDefinition group27 = group28.addOrReplaceChild("group27", CubeListBuilder.create(), PartPose.offset(4.0F, 28.5F, -2.0F));
		PartDefinition rightarm = all2.addOrReplaceChild("rightarm", CubeListBuilder.create(), PartPose.offset(-7.25F, 1.5F, 0.0F));
		PartDefinition cube_r18 = rightarm.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 0).addBox(-0.75F, -3.25F, -2.0F, 3.5F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.75F, 1.5F, -1.0F, -0.3927F, 0.0F, 0.0F));
		PartDefinition cube_r19 = rightarm.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(32, 0).addBox(-0.76F, -2.0F, -2.75F, 3.5F, 4.0F, 4.75F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.75F, 4.6152F, -2.9207F, 0.3927F, 0.0F, 0.0F));
		PartDefinition cube_r20 = rightarm.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 43).addBox(-0.25F, -1.5F, -3.0F, 2.5F, 3.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.75F, 5.6152F, -2.9207F, 0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r21 = rightarm.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(58, 92).addBox(-1.0F, -2.75F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.25F, 6.8832F, -3.3303F, -0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r22 = rightarm.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(50, 89).addBox(-1.0F, -2.75F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.25F, 7.3832F, -3.3303F, -0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r23 = rightarm.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(17, 78).addBox(0.0F, -5.0F, -1.5F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.75F, 9.6152F, -2.9207F, -0.3927F, 0.0F, 0.0F));
		PartDefinition cube_r24 = rightarm.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(84, 64).addBox(-1.25F, -1.75F, -1.5F, 2.5F, 3.0F, 2.5F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.25F, 7.25F, -7.5F, 0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r25 = rightarm.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(32, 36).addBox(-0.75F, -2.25F, -1.0F, 3.5F, 8.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.75F, 0.5F, -3.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition R_r2 = rightarm.addOrReplaceChild("R_r2", CubeListBuilder.create().texOffs(0, 55).addBox(-0.75F, -1.75F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -0.25F, 0.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition leftleg = all2.addOrReplaceChild("leftleg",
				CubeListBuilder.create().texOffs(0, 32).addBox(-1.75F, -3.0F, -2.0F, 3.0F, 7.0F, 3.5F, new CubeDeformation(0.0F)).texOffs(82, 82).addBox(-1.5F, 4.0F, -1.5F, 2.5F, 2.0F, 2.75F, new CubeDeformation(0.0F)).texOffs(78, 18)
						.addBox(-1.75F, 6.0F, -1.0F, 3.0F, 2.0F, 2.75F, new CubeDeformation(0.0F)).texOffs(40, 80).addBox(-1.65F, 6.0F, -2.75F, 2.8F, 2.0F, 2.75F, new CubeDeformation(0.0F)).texOffs(40, 75)
						.addBox(-1.4F, 7.0F, -4.5F, 2.3F, 1.0F, 3.75F, new CubeDeformation(0.0F)),
				PartPose.offset(3.25F, 16.0F, 0.0F));
		PartDefinition rightleg = all2.addOrReplaceChild("rightleg",
				CubeListBuilder.create().texOffs(32, 18).addBox(-1.25F, -3.0F, -2.0F, 3.0F, 7.0F, 3.5F, new CubeDeformation(0.0F)).texOffs(80, 49).addBox(-1.15F, 6.0F, -2.75F, 2.8F, 2.0F, 2.75F, new CubeDeformation(0.0F)).texOffs(76, 34)
						.addBox(-0.9F, 7.0F, -4.5F, 2.3F, 1.0F, 3.75F, new CubeDeformation(0.0F)).texOffs(70, 83).addBox(-1.0F, 4.0F, -1.5F, 2.5F, 2.0F, 2.75F, new CubeDeformation(0.0F)).texOffs(70, 78)
						.addBox(-1.25F, 6.0F, -1.0F, 3.0F, 2.0F, 2.75F, new CubeDeformation(0.0F)),
				PartPose.offset(-3.25F, 16.0F, 0.0F));
		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(1.15F, -34.9F, -3.35F, 0.9F, 0.0F, 0.45F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		all2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.rightleg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.rightarm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.leftarm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.leftleg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
	}
}