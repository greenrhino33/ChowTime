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
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
            int foodHeight = 20;
            if (food.hasDescription())
            {
                List l = Minecraft.getMinecraft().fontRenderer.listFormattedStringToWidth(food.getLocalizedDescription(), parent.width - 20);
                foodHeight += Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * l.size();
            }
            if (height + foodHeight > parent.height - 5)
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
        int height = 0;
        for (int i = startNDX; i < parent.foods.size(); i++)
        {
            FoodItem food = new FoodItem(parent.foods.get(i));
            RenderItem(itemRender, 10, height, food.getItem());
            height += 20;
            if (food.hasDescription())
            {
                List l = Minecraft.getMinecraft().fontRenderer.listFormattedStringToWidth(food.getLocalizedDescription(), parent.width - 20);
                height += Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * l.size();
            }
        }
    }

    protected void RenderItem(RenderItem itemRenderer, int x, int y, Item food)
    {
        itemRenderer.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, new ItemStack(food), x, y, false);
    }

    public void RenderHaunches()
    {
        //GuinIngam L705

        int i1 = 5;

        for (int i = 0; i < 10; ++i)
        {
            //            int k5 = i2;
            int i4 = 16;
            byte b4 = 0;

            //            if (this.mc.thePlayer.isPotionActive(Potion.hunger))
            //            {
            //                i4 += 36;
            //                b4 = 13;
            //            }

            //            if (this.mc.thePlayer.getFoodStats().getSaturationLevel() <= 0.0F && this.updateCounter % (i1 * 3 + 1) == 0)
            //            {
            //                k5 = i2 + (this.rand.nextInt(3) - 1);
            //            }

            //            if (flag1)
            //            {
            //                b4 = 1;
            //            }
            //
            //            k4 = l1 - i * 8 - 9;
            //            this.drawTexturedModalRect(k4, k5, 16 + b4 * 9, 27, 9, 9);
            //
            //            if (flag1)
            //            {
            //                if (i * 2 + 1 < j1)
            //                {
            //                    this.drawTexturedModalRect(k4, k5, i4 + 54, 27, 9, 9);
            //                }
            //
            //                if (i * 2 + 1 == j1)
            //                {
            //                    this.drawTexturedModalRect(k4, k5, i4 + 63, 27, 9, 9);
            //                }
            //            }
        }
    }
}
