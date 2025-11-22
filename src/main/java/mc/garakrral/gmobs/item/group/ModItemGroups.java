package mc.garakrral.gmobs.item.group;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import mc.garakrral.gmobs.Main;
import mc.garakrral.gmobs.block.ModBlocks;
import mc.garakrral.gmobs.item.ModItems;

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
                        output.accept(ModItems.BEAR_SPAWN_EGG);
                        output.accept(ModBlocks.RED_WOOD);
                        output.accept(ModBlocks.RED_WOOD_LOG);
                        output.accept(ModBlocks.STRIPPED_RED_WOOD_LOG);
                        output.accept(ModBlocks.STRIPPED_RED_WOOD);
                        output.accept(ModBlocks.RED_WOOD_LEAVES);
                        output.accept(ModBlocks.RED_WOOD_PLANKS);
                        output.accept(ModBlocks.RED_WOOD_SAPLING);
                    })
                    .build());
}
