package gg.croxy.module.combat;

import gg.croxy.module.Module;
import gg.croxy.module.Category;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

public class KillAura extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public KillAura() {
        super("KillAura", "Automatically attacks nearby players", Category.COMBAT);
    }

    @Override
    public void onTick() {
        if (!isEnabled() || mc.player == null || mc.world == null) return;

        // Find nearest player within range
        PlayerEntity target = null;
        double closestDistance = 4.5;

        for (PlayerEntity player : mc.world.getPlayers()) {
            if (player != mc.player && !player.isSpectator() && player.isAlive()) {
                double distance = mc.player.distanceTo(player);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    target = player;
                }
            }
        }

        // Attack the target
        if (target != null) {
            mc.interactionManager.attackEntity(mc.player, target);
        }
    }
}