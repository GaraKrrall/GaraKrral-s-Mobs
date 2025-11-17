package mc.garakrral.gmobs.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;

import mc.garakrral.gmobs.Main;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModDataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent dataEvent) {
        DataGenerator gen = dataEvent.getGenerator();
        PackOutput out = gen.getPackOutput();
        var lookup = dataEvent.getLookupProvider();

        gen.addProvider(dataEvent.includeServer(),
                new ModBiomeModifierProvider(out, lookup));
    }
}
