package mc.garakrral.gmobs.event;

import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;

import mc.garakrral.gmobs.Main;
import mc.garakrral.gmobs.entity.ModEntities;
import mc.garakrral.gmobs.entity.client.model.BearModel;
import mc.garakrral.gmobs.entity.client.model.FlyModel;
import mc.garakrral.gmobs.entity.client.model.GeckoModel;
import mc.garakrral.gmobs.entity.custom.BearEntity;
import mc.garakrral.gmobs.entity.custom.FlyEntity;
import mc.garakrral.gmobs.entity.custom.GeckoEntity;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {
        e.registerLayerDefinition(GeckoModel.LAYER_LOCATION, GeckoModel::createBodyLayer);
        e.registerLayerDefinition(FlyModel.LAYER_LOCATION, FlyModel::createBodyLayer);
        e.registerLayerDefinition(BearModel.LAYER_LOCATION, BearModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent e) {
        e.put(ModEntities.GECKO.get(), GeckoEntity.createAttributes().build());
        e.put(ModEntities.FLY.get(), FlyEntity.createAttribute().build());
        e.put(ModEntities.BEAR.get(), BearEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent e) {
        e.register(ModEntities.GECKO.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        e.register(ModEntities.FLY.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.WORLD_SURFACE,
                FlyEntity::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        e.register(ModEntities.BEAR.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
