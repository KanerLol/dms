package Source;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.dms.mod.dms;
import net.dms.mod.blocks.BlockDec;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class ManureSource extends Item
{
	private Block theBlockPlant;
    private Block soilId;
   




    public ManureSource(Block parBlockPlant, Block parSoilBlock)
      {
          theBlockPlant = parBlockPlant;
          soilId = parSoilBlock;
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
        // check if player has capability to edit
        else if (parPlayer.canPlayerEdit(parX, parY+1, parZ, par7, parItemStack))
        {
            // check that the soil block can sustain the plant
        	
        	
            // and that block above is air so there is room for plant to grow
            if (parWorld.getBlock(parX, parY, parZ)==soilId)
            {
             // place the plant block
                parWorld.setBlock(parX, parY, parZ, theBlockPlant);
                // decrement the stack of seed items
                --parItemStack.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
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