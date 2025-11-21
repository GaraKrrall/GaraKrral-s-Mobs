package mc.garakrral.gmobsreforged.item;

import net.minecraft.world.item.Item;

import mc.garakrral.gmobsreforged.Main;
import mc.garakrral.gmobsreforged.entity.ModEntities;
import mc.garakrral.gmobsreforged.item.feature.SimpleItem;
import mc.garakrral.gmobsreforged.item.feature.TestItem;

import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    public static final RegistryObject<Item> MAIN_TAB_ICON_ITEM = ITEMS.register("main_tab_icon_item",
            () -> new SimpleItem(new Item.Properties()));

    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test",
            () -> new TestItem(new Item.Properties()));

    public static final RegistryObject<Item> GECKO_SPAWN_EGG = ITEMS.register("gecko_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.GECKO, 0x31afaf, 0xffac00,
                    new Item.Properties()));

    public static final RegistryObject<Item> FLY_SPAWN_EGG = ITEMS.register("fly_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.FLY, 0x34adfa, 0xffaa00,
                    new Item.Properties()));

    public static final RegistryObject<Item> BEAR_SPAWN_EGG = ITEMS.register("bear_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BEAR, 0X45FDFa, 0x55adfa,
                    new Item.Properties()));
}
