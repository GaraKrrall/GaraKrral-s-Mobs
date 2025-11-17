package mc.garakrral.gmobs.datagen;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;

import mc.garakrral.gmobs.Main;
import mc.garakrral.gmobs.worldgen.ModBiomeModifiers;

import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifierProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);

    public ModBiomeModifierProvider(PackOutput out, CompletableFuture<HolderLookup.Provider> r) {
        super(out, r, BUILDER, Set.of(Main.MODID));
    }
}
