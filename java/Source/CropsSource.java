package Source;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.dms.mod.dms;
import net.dms.mod.blocks.BlockDec;
import net.dms.mod.items.SimpleItem;
import net.dms.mod.tileentity.TileEntityCampFire;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class CropsSource extends Item implements IPlantable
{
    private final Block theBlockPlant;
    /**
     * Block ID of the soil this seed food should be planted on.
     */
    private final Block soilId;
    private boolean isFuel;
	public String fuelType;

    public CropsSource( 
          Block parBlockPlant, Block parSoilBlock,boolean isFuel,String TypeFuel)
    {
        theBlockPlant = parBlockPlant;
        soilId = parSoilBlock;
        this.isFuel=isFuel;
		fuelType=TypeFuel;
        this.setCreativeTab(dms.tabDMS);
    }

    public boolean onItemUse(ItemStack parItemStack, EntityPlayer parPlayer, 
          World parWorld, int parX, int parY, int parZ, int par7, float par8, 
          float par9, float par10)
    {
     // not sure what this parameter does, copied it from potato
    	if (par7 != 1)
        {
            return false;
        }
    	else 
        if (parPlayer.canPlayerEdit(parX, parY+1, parZ, par7, parItemStack))
        {
            // check that the soil block can sustain the plant
        	
        	
            // and that block above is air so there is room for plant to grow
            if (parWorld.getBlock(parX, parY, parZ)==soilId && parWorld.getBlock(parX, parY+1, parZ)==Blocks.air)
            {
             // place the plant block
                parWorld.setBlock(parX, parY+1, parZ, theBlockPlant);
                // decrement the stack of seed items
                --parItemStack.stackSize;
                return true;
            }
            else
            	if (isFuel){
    	            if (parWorld.getBlock(parX, parY, parZ)==BlockDec.campFireBlock)
    	            {
    	             // place the plant block
    	            	TileEntityCampFire t = (TileEntityCampFire) parWorld.getTileEntity(parX, parY, parZ);
    	                   t.setBurnTime(t.getBurnTime()+SimpleItem.countFuel(fuelType));
    	                   parWorld.playSoundEffect(parX, parY, parZ,dms.modID+":addFuel", 1, 1);
    	                --parItemStack.stackSize;
    	                return true;
    	            }
    	            else
    	            {
    	                return false;
    	            }
    	        }
            {
                return false;
            }
        }
        else return false;
        
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
        return EnumPlantType.Crop;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z)
    {
        return theBlockPlant;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
    {
        return 0;
    }

    public Block getSoilId() 
    {
        return soilId;
    }
    
    @SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(dms.modID + ":" + this.getUnlocalizedName().substring(5));
	}

    
    
}