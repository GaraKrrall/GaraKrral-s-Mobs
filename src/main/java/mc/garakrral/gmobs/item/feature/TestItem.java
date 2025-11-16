package mc.garakrral.gmobs.item.feature;

import java.util.List;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;

public class TestItem extends SimpleItem {
    public TestItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext xContext) {
        Player p = xContext.getPlayer();
        if (p != null){
         p.sendSystemMessage(Component.literal("tick tick tick and tick"));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()){
          tooltipComponents.add(Component.literal("This is a test item."));
        } else {
            tooltipComponents.add(Component.literal("Press shift!"));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
