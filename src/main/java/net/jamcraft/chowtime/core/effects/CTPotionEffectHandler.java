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

package net.jamcraft.chowtime.core.effects;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.jamcraft.chowtime.core.items.CTPotions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import java.util.HashMap;

/**
 * Created by Kayla Marie on 5/17/14.
 */
public class CTPotionEffectHandler
{

    public static HashMap<Entity, Long> sugarPotionHit = new HashMap<Entity, Long>();

    @SubscribeEvent
    public void onLivingHurt(LivingAttackEvent event)
    {
        if (event.source.getSourceOfDamage() instanceof EntityPlayer)
        {
            EntityPlayer p = (EntityPlayer) event.source.getSourceOfDamage();
            if (p.isPotionActive(CTPotions.fruitPunch) && !p.worldObj.isRemote)
            {
                sugarPotionHit.put(event.entity, event.entity.worldObj.getTotalWorldTime());
            }
        }
    }
}
