package gg.croxy.gui.theme;

import net.minecraft.client.gui.DrawContext;
import java.awt.Color;

public class Theme {
    // Main colors
    public static final Color PRIMARY_GREEN = new Color(0, 255, 136);
    public static final Color SECONDARY_GREEN = new Color(0, 204, 109);
    public static final Color BACKGROUND = new Color(17, 17, 17, 230);
    public static final Color SECONDARY_BACKGROUND = new Color(24, 24, 24, 230);
    public static final Color BORDER = new Color(0, 255, 136, 100);
    
    public static void drawRoundedRect(DrawContext context, int x, int y, int width, int height, int radius, Color color) {
        // Draw modern rounded rectangle
        context.fill(x + radius, y, x + width - radius, y + height, color.getRGB());
        context.fill(x, y + radius, x + width, y + height - radius, color.getRGB());
        
        // Draw corners
        drawCircle(context, x + radius, y + radius, radius, color);
        drawCircle(context, x + width - radius, y + radius, radius, color);
        drawCircle(context, x + radius, y + height - radius, radius, color);
        drawCircle(context, x + width - radius, y + height - radius, radius, color);
    }
    
    private static void drawCircle(DrawContext context, int x, int y, int radius, Color color) {
        for (int i = 0; i <= radius; i++) {
            for (int j = 0; j <= radius; j++) {
                if ((i * i) + (j * j) <= (radius * radius)) {
                    context.fill(x - i, y - j, x - i + 1, y - j + 1, color.getRGB());
                    context.fill(x + i, y - j, x + i + 1, y - j + 1, color.getRGB());
                    context.fill(x - i, y + j, x - i + 1, y + j + 1, color.getRGB());
                    context.fill(x + i, y + j, x + i + 1, y + j + 1, color.getRGB());
                }
            }
        }
    }
}