package mc.garakrral.gmobs.entity.client.renderer;

import java.util.Map;

import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import mc.garakrral.gmobs.Main;
import mc.garakrral.gmobs.entity.client.model.GeckoModel;
import mc.garakrral.gmobs.entity.custom.GeckoEntity;
import mc.garakrral.gmobs.entity.variant.GeckoVariant;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import org.jetbrains.annotations.NotNull;

public class GeckoRenderer extends MobRenderer<GeckoEntity, GeckoModel<GeckoEntity>> {
    private static final Map<GeckoVariant, ResourceLocation> GECKO_VARIANT_RESOURCE_LOCATION_MAP =
            Util.make(Maps.newEnumMap(GeckoVariant.class), map -> {
                map.put(GeckoVariant.BLUE,
                        ResourceLocation.fromNamespaceAndPath(Main.MODID, "textures/entity/gecko/gecko_blue.png"));
                map.put(GeckoVariant.PINK,
                        ResourceLocation.fromNamespaceAndPath(Main.MODID, "textures/entity/gecko/gecko_pink.png"));
                map.put(GeckoVariant.BROWN,
                        ResourceLocation.fromNamespaceAndPath(Main.MODID, "textures/entity/gecko/gecko_brown.png"));
                map.put(GeckoVariant.GREEN,
                        ResourceLocation.fromNamespaceAndPath(Main.MODID, "textures/entity/gecko/gecko_green.png"));
                map.put(GeckoVariant.RED,
                        ResourceLocation.fromNamespaceAndPath(Main.MODID, "textures/entity/gecko/gecko_red.png"));
                    });

    public GeckoRenderer(EntityRendererProvider.Context context) {
        super(context, new GeckoModel<>(context.bakeLayer(GeckoModel.LAYER_LOCATION)), 0.25f);
    }

    @NotNull
    @Override
    public ResourceLocation getTextureLocation(GeckoEntity entity) {
        return GECKO_VARIANT_RESOURCE_LOCATION_MAP.get(entity.getVariant());
    }

    @Override
    public void render(GeckoEntity gecko, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (gecko.isBaby()) {
            poseStack.scale(0.50f, 0.50f, 0.50f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }
        super.render(gecko, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
