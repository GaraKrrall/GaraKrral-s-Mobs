package mc.garakrral.gmobsreforged.item.feature;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;

public class TestItem extends SimpleItem {
    public TestItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext xContext) {
        Player p = xContext.getPlayer();
        if (p != null) {
            p.sendSystemMessage(Component.literal("tick tick tick and tick"));
        }
        return InteractionResult.SUCCESS;
    }

}
