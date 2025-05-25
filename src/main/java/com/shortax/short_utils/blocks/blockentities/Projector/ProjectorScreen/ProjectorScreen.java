/*
 *
 *  * This file is part of Short Utils mod
 *  *
 *  * Copyright (C) 2025 Shortax
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU Lesser General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  * GNU Lesser General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU Lesser General Public License
 *  * along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package com.shortax.short_utils.blocks.blockentities.Projector.ProjectorScreen;

import com.shortax.short_utils.ShortUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerEntity;
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
    public static final int WHITE_COLOR_INT = RGB_TO_INT(255,255,255);
    private static String[] cached_translation_text;


    public int APPLY_IDX = 0;
    public int DEFAULT_IDX = 1;
    private static final String default_translate_path = "ui.short_utils.";
    private static final String[] Buttons = {"apply", "default"};
    private int bWidth;

    private int server_radius;
    private int server_thickness;
    private int server_transparency;

    private int client_radius;
    private int client_thickness;
    private int client_transparency;

    private int pack;

    private boolean applied;
    private boolean reset;
    private float show_text = 0f;

    private mySlider radius_slider;
    private mySlider thickness_slider;
    private mySlider transparency_slider;

    private final PlayerEntity player;

    private float totalDeltaTime = 0f;

    public ProjectorScreen(ProjectorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, TITLE);

        player = inventory.player;

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
        ShortUtils.LOGGER.info("{} Screen initialized",this.title.getString());

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

        pack = 0;

        addDrawableChild(get_Button(this.x,APPLY_IDX,pressAction -> {

            this.applied = true;
            this.reset = false;

            this.show_text = 4000f;

            this.handler.onButtonClick(player,0);
        }));

        addDrawableChild(get_Button(this.x,DEFAULT_IDX,pressAction -> {
            this.applied = false;
            this.reset = true;

            this.show_text = 4000f;

            this.reset_sliders();

            this.handler.onButtonClick(player,1);
        }));

        this.client_radius = this.handler.get_default_radius();
        this.server_thickness = this.handler.get_default_thickness();
        this.client_transparency = this.handler.get_default_transparency();

        updateClientValues(updateServerTrackedValues());
        add_settings_sliders();
        updateSliders();
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

        draw_sliderText(context);

        announce_text(context,this.applied,APPLY_IDX, GREEN_COLOR_INT);
        announce_text(context,this.reset,DEFAULT_IDX, RED_COLOR_INT);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);

        totalDeltaTime += delta*50f; //to milliseconhds

        if(totalDeltaTime >= 16f) //after 16ms update sliders
        {
            boolean changed = updateServerTrackedValues();
            if(changed) {
                updateClientValues(true);
                updateSliders();
            }

            advance_text_fade();
            totalDeltaTime = 0f;
        }
    }

    private boolean updateServerTrackedValues() {
        boolean changed = false;
        if(this.server_radius != this.handler.get_radius()) {
            this.server_radius = this.handler.get_radius();
            changed = true;
        }
        if(this.server_thickness != this.handler.get_thickness()) {
            this.server_thickness = this.handler.get_thickness();
            changed = true;
        }
        if(this.server_transparency != this.handler.get_transparency()) {
            this.server_transparency= this.handler.get_transparency();
            changed = true;
        }

        return changed;
    }

    private void updateClientValues(boolean changed)
    {
        if(changed) {
            if (server_radius != client_radius)
                client_radius = server_radius;
            if (server_thickness != client_thickness)
                client_thickness = server_thickness;
            if (server_transparency != client_transparency)
                client_transparency = server_transparency;
        }
    }

    private void reset_sliders()
    {
        this.radius_slider.reset();
        this.thickness_slider.reset();
        this.transparency_slider.reset();
    }

    private void updateSliders()
    {
        this.updateSliders(this.client_radius,this.client_thickness,this.client_transparency);
    }

    private void updateSliders(int r, int th, int tr)
    {
        this.radius_slider.set(r);
        this.thickness_slider.set(th);
        this.transparency_slider.set(tr);
    }

    private void add_settings_sliders()
    {
        //half GUI * 80%
        int sliderWidth = backgroundWidth/2 / 5*4;
        int sliderHeight = backgroundHeight/8;
        int spacing = sliderHeight/2;

        int y_NORTH = (height - backgroundHeight) / 2 + sliderHeight*2;

        int sliderX = this.x + textRenderer.getWidth("Transparency:") + textRenderer.getWidth("\t") + spacing * 3;

        int i = 0;
        this.radius_slider = new mySlider(sliderX, get_slider_y(y_NORTH, i, sliderHeight, spacing), sliderWidth, sliderHeight,
                this.client_radius, this.handler.get_min_radius() , this.handler.get_max_radius(),this.handler.get_default_radius()  ,
                updateValue -> this.client_radius = updateValue);
        this.radius_slider.set_unit("bl");
        this.radius_slider.updateMessage();

        i = 1;
        this.thickness_slider = new mySlider(sliderX, get_slider_y(y_NORTH, i, sliderHeight, spacing), sliderWidth, sliderHeight,
                this.client_thickness, this.handler.get_min_thickness(), this.handler.get_max_thickness(), this.handler.get_default_thickness(),
                updateValue -> this.client_thickness = updateValue);
        this.thickness_slider.set_unit("bl");
        this.thickness_slider.updateMessage();

        i = 2;
        this.transparency_slider = new mySlider(sliderX, get_slider_y(y_NORTH, i, sliderHeight, spacing), sliderWidth, sliderHeight,
                this.client_transparency, this.handler.get_min_transparency() , this.handler.get_max_transparency() , this.handler.get_default_transparency(),
                updateValue -> this.client_transparency = updateValue);
        this.transparency_slider.set_unit("%");
        this.transparency_slider.updateMessage();

        addDrawableChild(this.radius_slider);
        addDrawableChild(this.thickness_slider);
        addDrawableChild(this.transparency_slider);
    }

    public void draw_sliderText(DrawContext context){
        int tex_X = 6;
        int texY = 22;
        context.drawText(this.textRenderer, Text.of("Radius: "),
                tex_X, texY, WHITE_COLOR_INT, true);

        context.drawText(this.textRenderer, Text.of("Thickness: "),
                tex_X, texY+15,
                WHITE_COLOR_INT, true);

        context.drawText(this.textRenderer, Text.of("Transparency: "),
                tex_X, texY+30,
                WHITE_COLOR_INT, true);
    }

    private int get_slider_y(int origin, int i, int height, int spacing)
    {
        return origin + height * i + spacing * i;
    }

    private ButtonWidget get_Button(int x, int idx, ButtonWidget.PressAction pressAction)
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

    private void advance_text_fade()
    {
        this.show_text = Math.max(this.show_text-this.totalDeltaTime,0f);
        if(this.show_text <= 0)
        {
            this.applied = false;
            this.reset = false;
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

    static class mySlider extends SliderWidget{

        private int setting;
        private final int min;
        private final int max;
        private final int default_value;
        private String unit = "";
        private final SettingsUpdate updateFunc;

        public mySlider(int x, int y, int width, int height, int setting,int min, int max , int default_val, SettingsUpdate updateFunction) {
            super(x, y, width, height, Text.of(setting+""), setting_to_slider_pos(setting,min,max));
            this.setting = setting;
            updateFunc = updateFunction;

            this.min = min;
            this.max = max;
            this.default_value = default_val;
        }

        @Override
        protected void updateMessage() {
            this.setMessage(Text.of(this.setting+ " " + this.unit));
        }

        @Override
        protected void applyValue() {
            if(this.setting != sliderpos_to_setting(this.value)) {
                this.setting = sliderpos_to_setting(this.value);
                updateFunc.update(this.setting);
            }
        }

        private int sliderpos_to_setting(double val)
        {
            return (int) ((val * (this.max-this.min)) + this.min);
        }

        public static double setting_to_slider_pos(int set, int minimum, int maximum)
        {
            return (double) (set - minimum) / (maximum - minimum);
        }

        public void set_unit(String unitString)
        {
            this.unit = unitString;
        }

        public void reset()
        {
            this.set(this.default_value);
        }

        public void set(int val)
        {
            if(this.setting != val) {
                this.setting = val;
                this.value = setting_to_slider_pos(this.setting, this.min, this.max);
                this.applyValue();
                this.updateMessage();
            }
        }

        @Environment(EnvType.CLIENT)
        public interface SettingsUpdate{
            void update(int updateValue);
        }
    }
}
