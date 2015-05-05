package Source;

import java.util.Random;

import net.dms.mod.blocks.BlockDec;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAir extends Block {

	public BlockAir(Material material,float light) {
		super(Material.air);
		setBlockTextureName("testBlock");
		this.setLightLevel(light);
		this.setLightOpacity(0);
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
    public int getRenderType()
    {
        return 8;
    }
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        float f = 0.0000F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, f, 0.0F);
    }
    
    public void updateTick(World world, int x, int y, int z, Random random) {
    	if (!world.isRemote){
    		if (world.getWorldTime() % (20*1) == 0) {
    			if(world.getBlock(x,y,z)!=BlockDec.campFireBlock){
    				world.setBlock(x, y, z, Blocks.air);
    			}
    		}
    	}
	

}

}