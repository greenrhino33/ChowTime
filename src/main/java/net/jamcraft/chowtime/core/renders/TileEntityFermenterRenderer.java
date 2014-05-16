package net.jamcraft.chowtime.core.renders;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityFermenterRenderer extends TileEntitySpecialRenderer
{    
	@Override
	public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float var8) 
	{
	    ResourceLocation field_147526_d = new ResourceLocation("/textures/blocks/dirt.png");
        Tessellator tess = Tessellator.instance;
	}

}
