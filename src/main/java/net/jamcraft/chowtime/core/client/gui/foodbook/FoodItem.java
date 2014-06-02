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

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

/**
 * Created by James Hollowell on 6/1/2014.
 */
public class FoodItem
{
    private ItemFood wrappedFood;
    private String descUnloc="";

    public FoodItem(ItemFood food)
    {
        wrappedFood=food;
        descUnloc=food.getUnlocalizedName().substring(food.getUnlocalizedName().length()-5)+".desc";
    }

    public String getLocalizedDescription()
    {
        return StatCollector.translateToLocal(descUnloc);
    }

    public String getLocalizedName()
    {
        return StatCollector.translateToLocal(wrappedFood.getUnlocalizedName());
    }

    public boolean hasDescription()
    {
        return StatCollector.canTranslate(descUnloc);
    }

    public Item getItem()
    {
        return wrappedFood;
    }

    public int getHaunches()
    {
        return wrappedFood.func_150905_g(new ItemStack(wrappedFood));
    }
}
