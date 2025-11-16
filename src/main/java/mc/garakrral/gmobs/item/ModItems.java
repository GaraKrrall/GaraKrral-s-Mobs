package mc.garakrral.gmobs.item;

import net.minecraft.world.item.Item;

import mc.garakrral.gmobs.Main;
import mc.garakrral.gmobs.item.feature.SimpleItem;
import mc.garakrral.gmobs.item.feature.TestItem;

import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Main.MODID);

    public static final DeferredItem<Item> MAIN_TAB_ICON_ITEM = ITEMS.register("main_tab_icon_item",
            () -> new SimpleItem(new Item.Properties()));

    public static final DeferredItem<Item> TEST_ITEM = ITEMS.register("test",
            () -> new TestItem(new Item.Properties()));

}
