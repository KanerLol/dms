package tk.nukeduck.hearts;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import tk.nukeduck.hearts.Hearts;

public class WorldGeneratorHearts implements IWorldGenerator {

   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
      switch(world.provider.dimensionId) {
      case -1:
         this.generateNether(world, random, chunkX * 16, chunkZ * 16);
         break;
      case 0:
         this.generateSurface(world, random, chunkX * 16, chunkZ * 16);
         break;
      case 1:
         this.generateEnd(world, random, chunkX * 16, chunkZ * 16);
      }

   }

   private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
      int x = 0;

      for(int k = 0; k < 10; ++k) {
         int firstBlockXCoord = chunkX + rand.nextInt(16);
         int firstBlockYCoord = rand.nextInt(20);
         int firstBlockZCoord = chunkZ + rand.nextInt(16);
         if(rand.nextInt(2) == 0 && world.getBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord) == Blocks.stone && world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == Blocks.air) {
            world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, Hearts.heartCrystal);
            ++x;
         }
      }

   }

   private void generateNether(World world, Random rand, int chunkX, int chunkZ) {}

   private void generateEnd(World world, Random rand, int chunkX, int chunkZ) {}
}
