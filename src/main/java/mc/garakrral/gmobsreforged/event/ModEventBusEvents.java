package mc.garakrral.gmobsreforged.event;


import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;

import mc.garakrral.gmobsreforged.Main;
import mc.garakrral.gmobsreforged.entity.ModEntities;
import mc.garakrral.gmobsreforged.entity.client.model.BearModel;
import mc.garakrral.gmobsreforged.entity.client.model.FlyModel;
import mc.garakrral.gmobsreforged.entity.client.model.GeckoModel;
import mc.garakrral.gmobsreforged.entity.custom.BearEntity;
import mc.garakrral.gmobsreforged.entity.custom.FlyEntity;
import mc.garakrral.gmobsreforged.entity.custom.GeckoEntity;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
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
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent e) {
        e.register(ModEntities.GECKO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        e.register(ModEntities.FLY.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.WORLD_SURFACE,
                FlyEntity::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        e.register(ModEntities.BEAR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
