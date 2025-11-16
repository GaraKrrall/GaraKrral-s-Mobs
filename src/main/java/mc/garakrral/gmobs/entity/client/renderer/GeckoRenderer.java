package mc.garakrral.gmobs.entity.client.renderer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import mc.garakrral.gmobs.Main;
import mc.garakrral.gmobs.entity.client.model.GeckoModel;
import mc.garakrral.gmobs.entity.custom.GeckoEntity;

import com.mojang.blaze3d.vertex.PoseStack;

public class GeckoRenderer extends MobRenderer<GeckoEntity, GeckoModel<GeckoEntity>> {
    public GeckoRenderer(EntityRendererProvider.Context context, GeckoModel<GeckoEntity> model, float shadowRadius) {
        super(context, new GeckoModel<>(context.bakeLayer(GeckoModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(GeckoEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Main.MODID, "textures/entity/gecko/gecko_blue.png");
    }

    @Override
    public void render(GeckoEntity gecko, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (gecko.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }
        super.render(gecko, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
