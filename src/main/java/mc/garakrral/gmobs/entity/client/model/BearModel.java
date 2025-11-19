package mc.garakrral.gmobs.entity.client.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

import mc.garakrral.gmobs.Main;
import mc.garakrral.gmobs.entity.client.animation.BearAnimations;
import mc.garakrral.gmobs.entity.custom.BearEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class BearModel<T extends BearEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Main.MODID, "bearmodel"), "main");
    private final ModelPart root;
    private final ModelPart legs;
    private final ModelPart one;
    private final ModelPart two;
    private final ModelPart three;
    private final ModelPart four;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart sweet_nose;
    private final ModelPart ear;

    public BearModel(ModelPart root) {
        this.root = root.getChild("root");
        this.legs = this.root.getChild("legs");
        this.one = this.legs.getChild("1");
        this.two = this.legs.getChild("2");
        this.three = this.legs.getChild("3");
        this.four = this.legs.getChild("4");
        this.body = this.root.getChild("body");
        this.head = this.root.getChild("head");
        this.sweet_nose = this.head.getChild("sweet_nose");
        this.ear = this.head.getChild("ear");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition legs = root.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition
        one = legs.addOrReplaceChild("1", CubeListBuilder.create().texOffs(-6, -4).addBox(0.0F, 0.0F, 0.0F, 5.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -8.0F, -6.0F));

        PartDefinition
        two = legs.addOrReplaceChild("2", CubeListBuilder.create().texOffs(-8, -5).addBox(-5.0F, 0.0F, 0.0F, 5.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -8.0F, 8.0F));

        PartDefinition
        three = legs.addOrReplaceChild("3", CubeListBuilder.create().texOffs(-7, -5).addBox(0.0F, 0.0F, -7.0F, 5.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -8.0F, 15.0F));

        PartDefinition
        four = legs.addOrReplaceChild("4", CubeListBuilder.create().texOffs(-7, -4).addBox(-5.0F, 0.0F, -5.0F, 5.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -8.0F, -1.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(-16, -14).addBox(-10.0F, 0.0F, -16.0F, 20.0F, 12.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(-10, -9).addBox(-9.0F, 1.0F, -27.0F, 18.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 19.0F));

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(-9, -5).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, -15.0F));

        PartDefinition sweet_nose = head.addOrReplaceChild("sweet_nose", CubeListBuilder.create().texOffs(-3, -1).addBox(-3.0F, -13.0F, -18.0F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 15.0F));

        PartDefinition ear = head.addOrReplaceChild("ear", CubeListBuilder.create().texOffs(1, 1).addBox(3.0F, -20.0F, -12.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(1, 1).addBox(-5.0F, -20.0F, -12.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(1, 1).addBox(5.0F, -20.0F, -12.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(1, 1).addBox(-6.0F, -20.0F, -12.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 15.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void setupAnim(BearEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.animate(entity.walkAnimationState, BearAnimations.WALK, ageInTicks, 1f);
      this.animate(entity.attackAnimationState, BearAnimations.ATTACK, ageInTicks, 1f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        this.root.render(poseStack, buffer, packedLight,packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}
