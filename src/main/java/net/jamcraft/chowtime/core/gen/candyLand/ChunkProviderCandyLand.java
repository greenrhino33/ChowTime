package net.jamcraft.chowtime.core.gen.candyLand;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.List;
import java.util.Random;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class ChunkProviderCandyLand implements IChunkProvider{

    private Random rand;

    /** A NoiseGeneratorOctaves used in generating terrain */
    private NoiseGeneratorOctaves noiseGen1;

    /** A NoiseGeneratorOctaves used in generating terrain */
    private NoiseGeneratorOctaves noiseGen2;

    /** A NoiseGeneratorOctaves used in generating terrain */
    private NoiseGeneratorOctaves noiseGen3;

    /** A NoiseGeneratorOctaves used in generating terrain */
    private NoiseGeneratorOctaves noiseGen4;

    /** A NoiseGeneratorOctaves used in generating terrain */
    public NoiseGeneratorOctaves noiseGen5;

    /** A NoiseGeneratorOctaves used in generating terrain */
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise;

    /** Reference to the World object. */
    private World worldObj;

    /** are map structures going to be generated (e.g. strongholds) */
    private final boolean mapFeaturesEnabled;

    /** Holds the overall noise array used in chunk generation */
    private double[] noiseArray;
    private double[] stoneNoise = new double[256];
    private MapGenBase caveGenerator = new MapGenCaves();

    /** Holds Stronghold Generator */
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();

    /** Holds Village Generator */
    private MapGenVillage villageGenerator = new MapGenVillage();

    /** Holds Mineshaft Generator */
    private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
    private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();

    /** Holds ravine generator */
    private MapGenBase ravineGenerator = new MapGenRavine();

    /** The biomes that are used to generate the chunk */
    private BiomeGenBase[] biomesForGeneration;

    /** A double array that hold terrain noise from noiseGen3 */
    double[] noise3;

    /** A double array that hold terrain noise */
    double[] noise1;

    /** A double array that hold terrain noise from noiseGen2 */
    double[] noise2;

    /** A double array that hold terrain noise from noiseGen5 */
    double[] noise5;

    /** A double array that holds terrain noise from noiseGen6 */
    double[] noise6;

    /**
     * Used to store the 5x5 parabolic field that is used during terrain
     * generation.
     */
    float[] parabolicField;
    int[][] field_73219_j = new int[32][32];


    @Override
    public boolean chunkExists(int var1, int var2) {
        return false;
    }

    @Override
    public Chunk provideChunk(int var1, int var2) {
        return null;
    }

    @Override
    public Chunk loadChunk(int var1, int var2) {
        return null;
    }

    @Override
    public void populate(IChunkProvider var1, int var2, int var3) {

    }

    @Override
    public boolean saveChunks(boolean var1, IProgressUpdate var2) {
        return false;
    }

    @Override
    public boolean unloadQueuedChunks() {
        return false;
    }

    @Override
    public boolean canSave() {
        return false;
    }

    @Override
    public String makeString() {
        return null;
    }

    @Override
    public List getPossibleCreatures(EnumCreatureType var1, int var2, int var3, int var4) {
        return null;
    }

    @Override
    public ChunkPosition func_147416_a(World var1, String var2, int var3, int var4, int var5) {
        return null;
    }

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public void recreateStructures(int var1, int var2) {

    }

    @Override
    public void saveExtraData() {

    }

    public ChunkProviderCandyLand(World world, long seed, boolean mapFeaturesEnabled) {
        this.worldObj = world;
        this.mapFeaturesEnabled = mapFeaturesEnabled;
        this.rand = new Random(seed);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);

        NoiseGeneratorOctaves[] noiseGens = { noiseGen1, noiseGen2, noiseGen3, noiseGen4, noiseGen5, noiseGen6, mobSpawnerNoise };
        noiseGens = TerrainGen.getModdedNoiseGenerators(world, this.rand, noiseGens);
        this.noiseGen1 = noiseGens[0];
        this.noiseGen2 = noiseGens[1];
        this.noiseGen3 = noiseGens[2];
        this.noiseGen4 = noiseGens[3];
        this.noiseGen5 = noiseGens[4];
        this.noiseGen6 = noiseGens[5];
        this.mobSpawnerNoise = noiseGens[6];
    }

    public void generateTerrain(int par1, int par2, byte[] par2ArrayOfByte) {
        byte b0 = 4;
        byte b1 = 16;
        byte b2 = 63;
        int k = b0 + 1;
        byte b3 = 17;
        int l = b0 + 1;
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, par1 * 4 - 2, par2 * 4 - 2, k + 5, l + 5);
        this.noiseArray = this.initalizeNoiseField(this.noiseArray, par1 * b0, 0, par2 * b0, k, b3, l);

        for(int i1 = 0; i1 < b0; i1++){
            for (int j1 = 0; j1 < b0; ++j1) {
                for (int k1 = 0; k1 < b1; ++k1) {
                    double d0 = 0.125D;
                    double d1 = this.noiseArray[((i1 + 0) * l + j1 + 0) * b3 + k1 + 0];
                    double d2 = this.noiseArray[((i1 + 0) * l + j1 + 1) * b3 + k1 + 0];
                    double d3 = this.noiseArray[((i1 + 1) * l + j1 + 0) * b3 + k1 + 0];
                    double d4 = this.noiseArray[((i1 + 1) * l + j1 + 1) * b3 + k1 + 0];
                    double d5 = (this.noiseArray[((i1 + 0) * l + j1 + 0) * b3 + k1 + 1] - d1) * d0;
                    double d6 = (this.noiseArray[((i1 + 0) * l + j1 + 1) * b3 + k1 + 1] - d2) * d0;
                    double d7 = (this.noiseArray[((i1 + 1) * l + j1 + 0) * b3 + k1 + 1] - d3) * d0;
                    double d8 = (this.noiseArray[((i1 + 1) * l + j1 + 1) * b3 + k1 + 1] - d4) * d0;

                    for (int l1 = 0; l1 < 8; ++l1) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i2 = 0; i2 < 4; ++i2) {
                            int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + l1;
                            short short1 = 128;
                            j2 -= short1;
                            double d14 = 0.25D;
                            double d15 = (d11 - d10) * d14;
                            double d16 = d10 - d15;

                            for (int k2 = 0; k2 < 4; ++k2) {
                                if ((d16 += d15) > 0.0D) {
                                    par3ArrayOfByte[j2 += short1] = (byte) Blocks.stone;//Block.stone.blockID;
                                } else if (k1 * 8 + l1 < b2) {
                                    par3ArrayOfByte[j2 += short1] = (byte) Blocks.waterStill;
                                } else {
                                    par3ArrayOfByte[j2 += short1] = 0;
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7) {
        ChunkProviderEvent.InitNoiseField event = new ChunkProviderEvent.InitNoiseField(this, par1ArrayOfDouble, par2, par3, par4, par5, par6, par7);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Event.Result.DENY)
            return event.noisefield;

        if (par1ArrayOfDouble == null) {
            par1ArrayOfDouble = new double[par5 * par6 * par7];
        }

        if (this.parabolicField == null) {
            this.parabolicField = new float[25];

            for (int k1 = -2; k1 <= 2; ++k1) {
                for (int l1 = -2; l1 <= 2; ++l1) {
                    float f = 10.0F / MathHelper.sqrt_float((float) (k1 * k1 + l1 * l1) + 0.2F);
                    this.parabolicField[k1 + 2 + (l1 + 2) * 5] = f;
                }
            }
        }

}
