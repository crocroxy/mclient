package gg.croxy.module.combat;

import gg.croxy.module.Module;
import gg.croxy.module.Category;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class AutoTotem extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public AutoTotem() {
        super("AutoTotem", "Automatically places totems in offhand", Category.COMBAT);
    }

    @Override
    public void onTick() {
        if (!isEnabled() || mc.player == null) return;

        // Check if offhand is empty or not holding totem
        if (mc.player.getOffHandStack().isEmpty() || mc.player.getOffHandStack().getItem() != Items.TOTEM_OF_UNDYING) {
            // Search for totem in inventory
            int totemSlot = -1;
            for (int i = 0; i < 36; i++) {
                if (mc.player.getInventory().getStack(i).getItem() == Items.TOTEM_OF_UNDYING) {
                    totemSlot = i;
                    break;
                }
            }

            // Move totem to offhand
            if (totemSlot != -1) {
                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, totemSlot, 0, SlotActionType.PICKUP, mc.player);
                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
            }
        }
    }
}