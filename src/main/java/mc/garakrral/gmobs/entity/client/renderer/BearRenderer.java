package mc.garakrral.gmobs.entity.client.renderer;

import java.util.Map;

import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import mc.garakrral.gmobs.Main;
import mc.garakrral.gmobs.entity.client.model.BearModel;
import mc.garakrral.gmobs.entity.custom.BearEntity;
import mc.garakrral.gmobs.entity.variant.BearVariant;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import org.jetbrains.annotations.NotNull;

public class BearRenderer extends MobRenderer<BearEntity, BearModel<BearEntity>> {
    private static final Map<BearVariant, ResourceLocation> BEAR_VARIANT_RESOURCE_LOCATION_MAP =
            Util.make(Maps.newEnumMap(BearVariant.class), map -> {
                map.put(BearVariant.NORMAL,
                        ResourceLocation.fromNamespaceAndPath(Main.MODID, "textures/entity/bear/bear.png"));
                map.put(BearVariant.HONEY,
                        ResourceLocation.fromNamespaceAndPath(Main.MODID, "textures/entity/bear/bear_honey.png"));

            });

    public BearRenderer(EntityRendererProvider.Context context) {
        super(context, new BearModel<>(context.bakeLayer(BearModel.LAYER_LOCATION)), 0.25f);
    }

    @NotNull
    @Override
    public ResourceLocation getTextureLocation(BearEntity entity) {
        return BEAR_VARIANT_RESOURCE_LOCATION_MAP.get(entity.getVariant());
    }


    @Override
    public void render(BearEntity bear, float y, float pt, PoseStack pose, MultiBufferSource buf, int light) {

        super.render(bear, y, pt, pose, buf, light);
    }
}
