package com.shortax.short_utils.blocks.Crafters.Combiner.CombinerScreen;

import com.shortax.short_utils.ShortUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class Combiner_Screen extends HandledScreen<CombinerScreenHandler> {

    private static final Identifier GUI_TEXTURE = Identifier.of(ShortUtils.MOD_ID,"textures/gui/crafter/combiner_gui.png");
    private static final Identifier SUCESS_ARROW_RIGHT = Identifier.of(ShortUtils.MOD_ID,"textures/gui/crafter/combiner_success_arrow.png");
    private static final Identifier SUCESS_ARROW_LEFT = Identifier.of(ShortUtils.MOD_ID,"textures/gui/crafter/combiner_success_arrow_2.png");


    public Combiner_Screen(CombinerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {
        int i = this.x;
        int j = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(RenderLayer::getGuiTextured, GUI_TEXTURE, i, j, 0.0F, 0.0F, this.backgroundWidth, this.backgroundHeight, 256, 256);

        render_success_arrows(context,i,j);
    }

    private void render_success_arrows(DrawContext context, int x, int y)
    {
        if(this.handler.combineSuccess())
        {
            context.drawTexture(RenderLayer::getGuiTextured,SUCESS_ARROW_RIGHT,x+55,y+36,0,0,17,8,17,8);
            context.drawTexture(RenderLayer::getGuiTextured,SUCESS_ARROW_LEFT,x+104,y+40,0,0,17,8,17,8);
        }
    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }


}
