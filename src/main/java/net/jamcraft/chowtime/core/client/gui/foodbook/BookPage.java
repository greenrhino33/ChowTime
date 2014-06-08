/*
 * ChowTime - Dynamically updating food mod for Minecraft
 *     Copyright (C) 2014  Team JamCraft
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.jamcraft.chowtime.core.client.gui.foodbook;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James Hollowell on 6/1/2014.
 */
public class BookPage
{
    private int startNDX = 0;
    private int endNDX = 0;
    private GuiFoodBook parent;
    private List<FoodItem> foodItems = new ArrayList<FoodItem>();

    public BookPage(GuiFoodBook parent, int startIndex)
    {
        this.parent = parent;
        this.startNDX = startIndex;
    }

    public int calculateEndIndex()
    {
        int height = 0;
        for (int i = startNDX; i < parent.foods.size(); i++)
        {
            FoodItem food = new FoodItem(parent.foods.get(i));
            int foodHeight = 24;
            if (food.hasDescription())
            {
                List l = Minecraft.getMinecraft().fontRenderer.listFormattedStringToWidth(food.getLocalizedDescription(), 110);
                foodHeight += Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * l.size();
            }
            if (height + foodHeight > 125)
            {
                return endNDX;
            }
            else
            {
                height += foodHeight;
                endNDX = i;
                foodItems.add(food);
            }
        }
        return endNDX;
    }

    public void RenderPage(RenderItem itemRender)
    {
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        int height = 16;
        for (int i = 0; i < foodItems.size(); i++)
        {
            height+=3;
            FoodItem food = foodItems.get(i);
            RenderItem(itemRender, parent.bookXStart + 35, height, food.getItem());
            fontRenderer.drawString(food.getLocalizedName(), parent.bookXStart + 55, height + 1, 0x000000);
            RenderHaunches(food.getHaunches(), parent.bookXStart + 55, height + fontRenderer.FONT_HEIGHT+1);
            height += 21;
            if (food.hasDescription())
            {
                fontRenderer.drawSplitString(food.getLocalizedDescription(), parent.bookXStart + 45, height, 110, 0x000000);
                List l = Minecraft.getMinecraft().fontRenderer.listFormattedStringToWidth(food.getLocalizedDescription(), 110);
                height += (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 2) * l.size();
            }
        }
    }

    protected void RenderItem(RenderItem itemRenderer, int x, int y, Item food)
    {
        itemRenderer.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, new ItemStack(food), x, y, false);

    }

    public void RenderHaunches(int haunches, int x, int y)
    {
        //GuinIngam L705

        Minecraft.getMinecraft().renderEngine.bindTexture(Gui.icons);
        final int fullX = 52;
        final int halfX = 61;
        final int bgX = 16;
        final int dim = 8;
        final int hungerY = 27;

        int endHaunch = haunches / 2;

        GL11.glPushMatrix();

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glColor4f(1, 1, 1, 1);

        for (int i = 0; i < endHaunch; i++)
        {
            parent.drawTexturedModalRect(x, y, bgX, hungerY, dim, dim);
            parent.drawTexturedModalRect(x, y, fullX, hungerY, dim, dim);
            x += dim + 1;
        }

        if (haunches % 2 == 1)
        {
            parent.drawTexturedModalRect(x, y, bgX, hungerY, dim, dim);
            parent.drawTexturedModalRect(x, y, halfX, hungerY, dim, dim);
        }

        GL11.glEnable(GL11.GL_LIGHTING);

        GL11.glPopMatrix();
    }
}
