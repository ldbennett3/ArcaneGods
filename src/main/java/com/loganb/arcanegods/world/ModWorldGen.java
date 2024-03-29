package com.loganb.arcanegods.world;

import java.util.Random;

import com.loganb.arcanegods.init.ModBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class ModWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.getDimension() == 0) {
			generateOverworld(rand, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
	}
	
	private void generateOverworld(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkWriter) {
		
		// Test Crystal Ore generation
		generateOre(ModBlocks.PALE_CRYSTAL_ORE.getDefaultState(), world, rand, chunkX * 16, chunkZ * 16, 16, 64, rand.nextInt(4) + 1, 20);
		generateOre(ModBlocks.FIERY_CRYSTAL_ORE.getDefaultState(), world, rand, chunkX * 16, chunkZ * 16, 16, 64, rand.nextInt(3) + 1, 16);
		generateOre(ModBlocks.LAVENDER_CRYSTAL_ORE.getDefaultState(), world, rand, chunkX * 16, chunkZ * 16, 16, 64, rand.nextInt(2) + 1, 10);
		generateOre(ModBlocks.ICEY_CRYSTAL_ORE.getDefaultState(), world, rand, chunkX * 16, chunkZ * 16, 16, 64, rand.nextInt(3) + 1, 10);
		generateOre(ModBlocks.THUNDER_CRYSTAL_ORE.getDefaultState(), world, rand, chunkX * 16, chunkZ * 16, 16, 64, rand.nextInt(3) + 1, 5);
		
		generateOre(ModBlocks.COPPER_ORE.getDefaultState(), world, rand, chunkX * 16, chunkZ * 16, 16, 100, rand.nextInt(6) + 1, 35);
		generateOre(ModBlocks.SILVER_ORE.getDefaultState(), world, rand, chunkX * 16, chunkZ * 16, 16, 20, rand.nextInt(4) + 1, 5);
		
		generateOre(ModBlocks.MARBLE_BLOCK.getDefaultState(), world, rand, chunkX * 16, chunkZ * 16, 16, 130, rand.nextInt(45) + 10, 20);
		
	}
	
	private void generateOre(IBlockState ore, World world, Random rand, int x, int z, int minY, int maxY, int size, int chances) {
		int deltaY = maxY - minY;
		
		for(int i = 0; i < chances; i++) {
			BlockPos pos = new BlockPos(x + rand.nextInt(16), minY + rand.nextInt(deltaY), z + rand.nextInt(16));
			
			WorldGenMinable generator = new WorldGenMinable(ore, size);
			
			generator.generate(world, rand, pos);
		}
	}
	
}
