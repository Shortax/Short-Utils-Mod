package com.shortax.short_utils.blocks.blockentities.Projector.ProjectorScreen;

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

import static com.shortax.short_utils.helpers.mcMath.RGB_TO_INT;

@Environment(EnvType.CLIENT)
public class ProjectorScreen extends HandledScreen<ProjectorScreenHandler> {

    private static final Text TITLE = Text.translatable("block.short_utils.projector_block");
    private static final Identifier GUI_TEXTURE = Identifier.of(ShortUtils.MOD_ID,"textures/gui/utilities/projector_gui.png");
    public static final int TITLE_COLOR_INT = RGB_TO_INT(127, 127, 127);
    public static final int GREEN_COLOR_INT= RGB_TO_INT(20,200,30);
    public static final int RED_COLOR_INT = RGB_TO_INT(155,10,10);
    private static String[] cached_translation_text;


    public int APPLY_IDX = 0;
    public int DEFAULT_IDX = 1;
    private static final String default_translate_path = "ui.short_utils.";
    private static final String[] Buttons = {"apply", "default"};
    private int bWidth;

    private int pack = 0;

    private boolean applied;
    private boolean reset;

    public ProjectorScreen(ProjectorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, TITLE);

        this.backgroundWidth = 176;
        this.backgroundHeight = 86;
        title.copy();

        cached_translation_text = new String[]{
                translate(announce_path(APPLY_IDX)).getString(),
                translate(announce_path(DEFAULT_IDX)).getString()
        };
    }

    @Override
    protected void init() {
        super.init();

        titleX = (backgroundWidth - textRenderer.getWidth(TITLE)) / 2;

        this.applied = false;
        this.reset = false;

        int prev = 0;
        for(String t : Buttons)
        {
            WidthCalc calc_width = (String txt) -> Math.min(backgroundWidth / 4, textRenderer.getWidth(txt) + 6);
            bWidth = Math.max(calc_width.process(t),prev);
            prev = bWidth;
        }
        bWidth+=1;

        addDrawableChild(get_Button(this.x,APPLY_IDX,pressAction -> {
            //this.handler.updateSettings();
            this.applied = true;
            this.reset = false;
        }));

        addDrawableChild(get_Button(this.x,DEFAULT_IDX,pressAction -> {
            //this.handler.resetSettings();
            this.applied = false;
            this.reset = true;
        }));
    }

    @Override
    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {
        int i = this.x;
        int j = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(RenderLayer::getGuiTextured, GUI_TEXTURE, i, j, 0.0F, 0.0F, this.backgroundWidth, this.backgroundHeight, 256, 256);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        context.drawText(this.textRenderer, this.title, this.titleX, this.titleY, TITLE_COLOR_INT, false);

        announce_text(context,this.applied,APPLY_IDX, GREEN_COLOR_INT);
        announce_text(context,this.reset,DEFAULT_IDX, RED_COLOR_INT);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    public ButtonWidget get_Button(int x, int idx, ButtonWidget.PressAction pressAction)
    {

        int space_between_buttons = 3;

        int bheight = Math.min(backgroundHeight /6, 16);
        int x_CENTER = x+backgroundWidth/2;
        int y_NORTH = (height - backgroundHeight) / 2;
        int y_SOUTH = y_NORTH + backgroundHeight;
        int bottom_Y = y_SOUTH - 10 - bheight / 2;

        //int bX = x_CENTER - bWidth - space_between_buttons;
        int bX = (int) (x_CENTER + (pack - (Buttons.length) / 2.0) * (bWidth+space_between_buttons));
        //int default_bX = apply_bX + bWidth + space_between_buttons;

        ButtonWidget.Builder buttonBuilder = new ButtonWidget.Builder(translate(translate_path(idx)),pressAction);

        pack++;
        return buttonBuilder.dimensions(bX, bottom_Y, bWidth,bheight)
                .tooltip(Tooltip.of(translate(tooltip_path(idx)))).build();
    }

    public void announce_text(DrawContext context, boolean test, int idx, int color)
    {
        if(test) {
            context.drawText(textRenderer, Text.of(cached_translation_text[idx]), 5, -10, color, true);
        }
    }

    private static Text translate(String path)
    {
        return Text.translatable(path);
    }

    private static String tooltip_path(int key)
    {
        return translate_path(key)+"_text";
    }

    private static String announce_path(int key)
    {
        return translate_path(key)+"_announce";
    }

    private static String translate_path(int key)
    {
        return default_translate_path +Buttons[key];
    }

    @FunctionalInterface
    interface WidthCalc {
        int process(String text);
    }
}
