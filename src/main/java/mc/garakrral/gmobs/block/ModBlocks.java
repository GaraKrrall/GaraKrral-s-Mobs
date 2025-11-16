package mc.garakrral.gmobs.block;

import java.util.function.Supplier;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import mc.garakrral.gmobs.Main;
import mc.garakrral.gmobs.item.ModItems;

import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Main.MODID);


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
      DeferredBlock<T> toReturn = BLOCKS.register(name, block);
      registerBlockItem(name, toReturn);
      return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name,  () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
