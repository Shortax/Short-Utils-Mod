package com.shortax.short_utils.blockentities.BuildProjector.ProjectorScreen;

import com.shortax.short_utils.ShortUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ProjectorScreen extends HandledScreen<ProjectorScreenHandler> {

    private static final Identifier GUI_TEXTURE = Identifier.of(ShortUtils.MOD_ID,"textures/gui/utilities/projector_gui.png");
    private static final Text TITLE = Text.of("Projector");
    private static final int COLOR_TITLE_INT = RGB_TO_INT(127,127,127);
    private static final int GREEN_COL_INT = RGB_TO_INT(20,200,30);

    private boolean applied;

    public ProjectorScreen(ProjectorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, TITLE);
        title.copy();
    }

    @Override
    protected void init() {
        this.backgroundWidth = 176;
        this.backgroundHeight = 86;
        super.init();

        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;

        this.applied = false;

        add_Buttons();
    }

    @Override
    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {
        int i = this.x;
        int j = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(RenderLayer::getGuiTextured, GUI_TEXTURE, i, j, 0.0F, 0.0F, this.backgroundWidth, this.backgroundHeight, 256, 256);

    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        context.drawText(this.textRenderer, this.title, this.titleX, this.titleY, COLOR_TITLE_INT, false);
        draw_applied_text(context);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    private void draw_applied_text(DrawContext context)
    {
        if(this.applied) {
            String s = "Applied!";
            context.drawText(this.textRenderer, Text.of(s), textRenderer.getWidth(s)/2, -10, GREEN_COL_INT, false);
        }
    }

    private void add_Buttons()
    {
        ButtonWidget.Builder apply_buttonBuilder = new ButtonWidget.Builder(Text.of("Apply"),button -> {
            //this.handler.updateSettings();
            this.applied = true;
        });

        int bwidth = this.backgroundWidth/5;
        int bheight = 9 + this.backgroundHeight/8;

        int bX = this.x+this.backgroundWidth/2-bwidth/2;
        int bY = (this.height - this.backgroundHeight) / 2 + this.backgroundHeight-10-bheight/2;

        ButtonWidget applyButton = apply_buttonBuilder.dimensions(bX,bY,bwidth,bheight)
                .tooltip(Tooltip.of(Text.of("Applies settings"))).build();

        addDrawableChild(applyButton);
    }

    private static int RGB_TO_INT(int R, int G, int B)
    {
        return ((R << 16) | (G << 8) | B);
    }
}
