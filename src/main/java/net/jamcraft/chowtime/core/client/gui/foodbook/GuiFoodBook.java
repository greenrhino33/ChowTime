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

import cpw.mods.fml.common.registry.GameData;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemFood;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by James Hollowell on 5/31/2014.
 */
public class GuiFoodBook extends GuiScreen
{
    private static final ResourceLocation texture = new ResourceLocation("minecraft:textures/gui/book.png");

    private static final int BOOK_BTN_NEXT = 0;
    private static final int BOOK_BTN_PREV = 1;

    public static List<ItemFood> foods = new ArrayList<ItemFood>();
    public static List<BookPage> pages = new ArrayList<BookPage>();

    private GuiButton next;
    private GuiButton prev;

    private int pageIndex = 0;

    public GuiFoodBook()
    {
        super();
        findFoods();
        makePages();
    }

    private void findFoods()
    {
        Iterator iter = GameData.getItemRegistry().iterator();
        while (iter.hasNext())
        {
            Object item = iter.next();
            if (item instanceof ItemFood)
            {
                foods.add((ItemFood) item);
            }
        }
    }

    private void makePages()
    {
        int foodCurrent = 0;
        do
        {
            BookPage newBook = new BookPage(this, foodCurrent);
            foodCurrent = newBook.calculateEndIndex();
            pages.add(newBook);
        }
        while (foodCurrent < foods.size());
    }

    @Override
    public void initGui()
    {
        super.initGui();
        @SuppressWarnings("unchecked")
        List<GuiButton> buttons = buttonList;

        int bookXBegin = (width - 192) / 2;

        buttons.add(next = new GuiButtonPageChange(BOOK_BTN_NEXT, bookXBegin + 120, 2 + 154, false));
        buttons.add(prev = new GuiButtonPageChange(BOOK_BTN_PREV, bookXBegin + 38, 2 + 154, true));
        updateButtonState();
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        switch (button.id)
        {
            case BOOK_BTN_NEXT:
                pageIndex++;
                break;
            case BOOK_BTN_PREV:
                --pageIndex;
                break;
        }
        updateButtonState();
    }

    private void updateButtonState()
    {
        next.visible = pageIndex < pages.size() - 1;
        prev.visible = pageIndex > 0;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float renderPartials)
    {
        drawBackground();
        drawForeground();

        super.drawScreen(mouseX, mouseY, renderPartials);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    @Override
    protected void keyTyped(char c, int key)
    {
        char lowerCase = Character.toLowerCase(c);
        if (key == Keyboard.KEY_ESCAPE)
        {
            mc.displayGuiScreen(null);
        }
        else if (Character.getType(lowerCase) == Character.LOWERCASE_LETTER)
        {
            //            for (int i = 0, len = pages.size(); i < len; ++i)
            //            {
            //                OreBookPage page = pages.get(i);
            //                if (Character.toLowerCase(page.getDisplayName().charAt(0)) == c)
            //                {
            //                    pageIndex = i;
            //                    updateButtonState();
            //                    break;
            //                }
            //            }
        }
    }

    protected void drawBackground()
    {
        int bookXStart = (width - 192) / 2;
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(bookXStart, 2, 0, 0, 192, 192);
    }

    protected void drawForeground()
    {
        if (pageIndex==0)
            drawStartScreen();
        else
            drawFoodPage();
    }

    protected void drawStartScreen()
    {
        fontRendererObj.drawString()
    }

    protected void drawFoodPage()
    {
        BookPage pg=pages.get(pageIndex-1);
        pg.RenderPage();
    }
}
