package mc.garakrral.gmobs.item;

import net.minecraft.world.item.Item;

import mc.garakrral.gmobs.Main;
import mc.garakrral.gmobs.entity.ModEntities;
import mc.garakrral.gmobs.item.feature.SimpleItem;
import mc.garakrral.gmobs.item.feature.TestItem;

import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Main.MODID);

    public static final DeferredItem<Item> MAIN_TAB_ICON_ITEM = ITEMS.register("main_tab_icon_item",
            () -> new SimpleItem(new Item.Properties()));

    public static final DeferredItem<Item> TEST_ITEM = ITEMS.register("test",
            () -> new TestItem(new Item.Properties()));

    public static final DeferredItem<Item> GECKO_SPAWN_EGG = ITEMS.register("gecko_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.GECKO, 0x31afaf, 0xffac00 ,
                    new Item.Properties()));

    public static final DeferredItem<Item> FLY_SPAWN_EGG = ITEMS.register("fly_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.FLY, 0x34adfa, 0xffaa00,
                    new Item.Properties()));
}
