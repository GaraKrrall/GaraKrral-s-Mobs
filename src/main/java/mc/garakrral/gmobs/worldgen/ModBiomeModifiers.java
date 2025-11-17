package mc.garakrral.gmobs.worldgen;

import java.util.List;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;

import mc.garakrral.gmobs.Main;
import mc.garakrral.gmobs.entity.ModEntities;

import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> GECKO_SPAWN = registerKey("gecko");

    public static void bootstrap(BootstrapContext<BiomeModifier> c) {
        var placedFeatures = c.lookup(Registries.PLACED_FEATURE);
        var biomes = c.lookup(Registries.BIOME);

        c.register(GECKO_SPAWN, new BiomeModifiers.AddSpawnsBiomeModifier(
           HolderSet.direct(biomes.getOrThrow(Biomes.SWAMP), biomes.getOrThrow(Biomes.PLAINS)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.GECKO.get(), 20, 2, 4))
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(Main.MODID, name));
    }
}
