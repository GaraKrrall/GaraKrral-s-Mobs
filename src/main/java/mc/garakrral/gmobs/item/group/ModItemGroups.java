package mc.garakrral.gmobs.item.group;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import mc.garakrral.gmobs.Main;
import mc.garakrral.gmobs.item.ModItems;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItemGroups {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Main.MODID);

    public static final Supplier<CreativeModeTab> MAIN_TAB = TABS.register("main",
            () -> CreativeModeTab.builder().icon(
                    () -> new ItemStack(ModItems.MAIN_TAB_ICON_ITEM.get()))
                    .title(Component.literal("GaraKrral's Mobs"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.TEST_ITEM);
                        output.accept(ModItems.GECKO_SPAWN_EGG);
                        output.accept(ModItems.FLY_SPAWN_EGG);
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}
