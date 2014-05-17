package net.jamcraft.chowtime.core.effects;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.jamcraft.chowtime.core.CTInits;
import net.jamcraft.chowtime.core.items.CTPotions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import java.util.HashMap;

/**
 * Created by Kayla Marie on 5/17/14.
 */
public class CTPotionEffectHandler {

    public static HashMap<Entity, Long> sugarPotionHit = new HashMap<Entity, Long>();

    @SubscribeEvent
    public void onLivingHurt(LivingAttackEvent event) {
        if(event.source.getSourceOfDamage() instanceof EntityPlayer){
            EntityPlayer p = (EntityPlayer) event.source.getSourceOfDamage();
            if(p.isPotionActive(CTPotions.fruitPunch) && !p.worldObj.isRemote){
                sugarPotionHit.put(event.entity, event.entity.worldObj.getTotalWorldTime());
            }
        }
    }
}
