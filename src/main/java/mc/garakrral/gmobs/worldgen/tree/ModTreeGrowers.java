package mc.garakrral.gmobs.worldgen.tree;

import java.util.Optional;

import net.minecraft.world.level.block.grower.TreeGrower;

import mc.garakrral.gmobs.Main;
import mc.garakrral.gmobs.worldgen.ModConfiguredFeatures;

public class ModTreeGrowers {
    public static final TreeGrower RED_TREE_GROWER =
            new TreeGrower(Main.MODID + ":red_wood",
                    Optional.empty(), Optional.of(ModConfiguredFeatures.RED_TREE_KEY), Optional.empty());
}
