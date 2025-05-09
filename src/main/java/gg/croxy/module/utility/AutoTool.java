package gg.croxy.module.utility;

import gg.croxy.module.Module;
import gg.croxy.module.Category;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;

public class AutoTool extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public AutoTool() {
        super("AutoTool", "Automatically selects the best tool", Category.UTILITY);
    }

    @Override
    public void onTick() {
        if (!isEnabled() || mc.player == null) return;

        // Check if player is breaking a block
        if (mc.options.attackKey.isPressed()) {
            int bestSlot = -1;
            float bestSpeed = 1.0f;

            // Find the best tool in hotbar
            for (int i = 0; i < 9; i++) {
                Item item = mc.player.getInventory().getStack(i).getItem();
                if (item instanceof MiningToolItem) {
                    float speed = ((MiningToolItem) item).getMiningSpeedMultiplier(
                        mc.player.getInventory().getStack(i),
                        mc.world.getBlockState(mc.crosshairTarget.getBlockPos())
                    );
                    if (speed > bestSpeed) {
                        bestSpeed = speed;
                        bestSlot = i;
                    }
                }
            }

            // Switch to best tool
            if (bestSlot != -1) {
                mc.player.getInventory().selectedSlot = bestSlot;
            }
        }
    }
}