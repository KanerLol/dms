package net.dms.mod.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.dms.mod.dms;
import net.dms.mod.blocks.BlockDec;
import net.dms.mod.tileentity.TileEntityCampFire;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SimpleItem extends Item {
	
	public SimpleItem(boolean isFuel,String TypeFuel) {
		this.setCreativeTab(dms.tabDMS);
		this.isFuel=isFuel;
		fuelType=TypeFuel;
	}
	private boolean isFuel;
	public String fuelType;
	public boolean onItemUse(ItemStack parItemStack, EntityPlayer parPlayer, 
	          World parWorld, int parX, int parY, int parZ, int par7, float par8, 
	          float par9, float par10)
	    {
	        if (par7 != 1)
	        {
	            return false;
	        }
	        
	        else if (parPlayer.canPlayerEdit(parX, parY, parZ, par7, parItemStack))
	        {
	        	
	        	
	        	if (isFuel){
	            if (parWorld.getBlock(parX, parY, parZ)==BlockDec.campFireBlock)
	            {
	            	TileEntityCampFire t = (TileEntityCampFire) parWorld.getTileEntity(parX, parY, parZ);
	                   t.setBurnTime(t.getBurnTime()+countFuel(fuelType));
	                   parWorld.playSoundEffect(parX, parY, parZ,dms.modID+":addFuel", 1, 1);
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
	        else
	        {
	            return false;
	        }
	    }
	
	
	public static int countFuel(String fuelType){
		int count = 0;
		if (fuelType.equals("Flower")) {count=7;}
		if (fuelType.equals("Materials")) {count=15;}
		if (fuelType.equals("BurningItems")) {count=45;}
		if (fuelType.equals("Saplings")) {count=180;}
		return count;
	}
	
	       
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(dms.modID + ":" + this.getUnlocalizedName().substring(5));
	}
}
