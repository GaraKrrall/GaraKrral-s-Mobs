package mc.garakrral.gmobs.event;

import mc.garakrral.gmobs.Main;
import mc.garakrral.gmobs.entity.ModEntities;
import mc.garakrral.gmobs.entity.client.model.GeckoModel;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GeckoModel.LAYER_LOCATION, GeckoModel::createBodyLayer);
    }

    private static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.GECKO.get());
    }
}
