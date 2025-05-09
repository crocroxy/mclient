package gg.croxy.gui;

import gg.croxy.gui.theme.Theme;
import gg.croxy.module.Category;
import gg.croxy.module.Module;
import gg.croxy.module.ModuleManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import java.awt.Color;

public class ClickGUI extends Screen {
    private static final int PANEL_WIDTH = 120;
    private static final int PANEL_HEIGHT = 300;
    private static final int HEADER_HEIGHT = 30;
    private static final int MODULE_HEIGHT = 25;
    private static final int PADDING = 5;
    
    private int dragX, dragY;
    private boolean dragging = false;
    private Category selectedCategory = Category.COMBAT;

    public ClickGUI() {
        super(Text.literal("Croxy Client"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Draw main panel background
        Theme.drawRoundedRect(context, 10, 10, PANEL_WIDTH, PANEL_HEIGHT, 8, Theme.BACKGROUND);
        
        // Draw category tabs
        int tabX = 15;
        int tabY = 15;
        
        for (Category category : Category.values()) {
            boolean isSelected = category == selectedCategory;
            Color tabColor = isSelected ? Theme.PRIMARY_GREEN : Theme.SECONDARY_BACKGROUND;
            Theme.drawRoundedRect(context, tabX, tabY, PANEL_WIDTH - 10, HEADER_HEIGHT, 5, tabColor);
            
            // Draw category name
            context.drawTextWithShadow(textRenderer, category.name(), 
                tabX + 5, tabY + (HEADER_HEIGHT - textRenderer.fontHeight) / 2, 
                isSelected ? 0xFFFFFFFF : 0xFFAAAAAA);
            
            tabY += HEADER_HEIGHT + PADDING;
        }
        
        // Draw modules for selected category
        int moduleY = tabY;
        for (Module module : ModuleManager.getInstance().getModules()) {
            if (module.getCategory() == selectedCategory) {
                Color moduleColor = module.isEnabled() ? Theme.SECONDARY_GREEN : Theme.SECONDARY_BACKGROUND;
                Theme.drawRoundedRect(context, 15, moduleY, PANEL_WIDTH - 10, MODULE_HEIGHT, 5, moduleColor);
                
                // Draw module name
                context.drawTextWithShadow(textRenderer, module.getName(),
                    20, moduleY + (MODULE_HEIGHT - textRenderer.fontHeight) / 2,
                    module.isEnabled() ? 0xFFFFFFFF : 0xFFAAAAAA);
                
                moduleY += MODULE_HEIGHT + PADDING;
            }
        }
        
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Handle category selection
        int tabY = 15;
        for (Category category : Category.values()) {
            if (isHovered(15, tabY, PANEL_WIDTH - 10, HEADER_HEIGHT, mouseX, mouseY)) {
                selectedCategory = category;
                return true;
            }
            tabY += HEADER_HEIGHT + PADDING;
        }
        
        // Handle module toggling
        int moduleY = tabY;
        for (Module module : ModuleManager.getInstance().getModules()) {
            if (module.getCategory() == selectedCategory) {
                if (isHovered(15, moduleY, PANEL_WIDTH - 10, MODULE_HEIGHT, mouseX, mouseY)) {
                    module.toggle();
                    return true;
                }
                moduleY += MODULE_HEIGHT + PADDING;
            }
        }
        
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean isHovered(int x, int y, int width, int height, double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}