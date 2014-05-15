package net.jamcraft.chowtime.core.gen.candyLand;

import net.jamcraft.chowtime.core.CTInits;
import net.jamcraft.chowtime.core.gen.BasicTreeGen;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class BiomeGenCandyLand extends BiomeGenBase {

    protected static final BasicTreeGen BasicTreeGen = new BasicTreeGen(true);

    public BiomeGenCandyLand(int par1) {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = Blocks.grass;
        this.fillerBlock = CTInits.IceCream;
        this.theBiomeDecorator.treesPerChunk = 5;
        this.theBiomeDecorator.deadBushPerChunk = 2;
        this.theBiomeDecorator.reedsPerChunk = 50;
        this.theBiomeDecorator.cactiPerChunk = 10;
        this.spawnableCreatureList.clear();
       // this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityLobster.class, 5, 4, 4));
        this.waterColorMultiplier = 0xFFFF00;
        this.setBiomeName("Candy Land");
    }
}
