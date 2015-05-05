package net.dms.mod.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.dms.mod.dms;
import net.dms.mod.blocks.BlockDec;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlocks extends Item{
	
	public ItemBlocks(){
		this.setCreativeTab(dms.tabDMS);
	}


	
	public boolean onItemUse(ItemStack parItemStack, EntityPlayer parPlayer, 
	          World worldObj, int xCoord, int yCoord, int zCoord, int par7, float par8, 
	          float par9, float par10)
	          
	    {
			yCoord++;
	       if (parPlayer.canPlayerEdit(xCoord, yCoord+1,zCoord,par7, parItemStack))
	        {
	            if (worldObj.getBlock(xCoord, yCoord-1, zCoord) instanceof Block&&
	            	worldObj.getBlock(xCoord, yCoord+1, zCoord)==Blocks.air&&
	            	worldObj.getBlock(xCoord-2, yCoord+1, zCoord+2)==Blocks.air&&
	            	worldObj.getBlock(xCoord+2, yCoord+1, zCoord-2)==Blocks.air&&
	            	worldObj.getBlock(xCoord+2, yCoord+1, zCoord+2)==Blocks.air&&
	            	worldObj.getBlock(xCoord-2, yCoord+1, zCoord-2)==Blocks.air&&
	            			worldObj.getBlock(xCoord-1, yCoord+1, zCoord+1)==Blocks.air&&
	            			worldObj.getBlock(xCoord+1, yCoord+1, zCoord-1)==Blocks.air&&
	            			worldObj.getBlock(xCoord-1, yCoord+1, zCoord-1)==Blocks.air&&
	            			worldObj.getBlock(xCoord+1, yCoord+1, zCoord+1)==Blocks.air){
	            		
	            	
	      			
	            
	           
	                worldObj.setBlock(xCoord, yCoord,zCoord, BlockDec.campFireBlock);
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
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(dms.modID + ":" + this.getUnlocalizedName().substring(5));
	}
	 

}
