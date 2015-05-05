package net.dms.mod.block;

import ibxm.Player;

import java.util.Random;

import net.dms.mod.dms;
import net.dms.mod.entity.ExtendedPlayer;
import net.dms.mod.tileentity.TileEntityCampFire;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CampFireBlock extends BlockContainer  {

	

	public CampFireBlock(Material material) {
		super(material);
		
		this.setHarvestLevel("pickaxe", 5);
		this.setLightLevel(0);
		this.setLightOpacity(0);
		this.setHardness(15.0F);
		this.setBlockBounds(0, 0, 0, 1, 0.3F, 1);
		

		
		
	}
Minecraft mc = Minecraft.getMinecraft();
	public int getRenderType() {return -1;}
	public boolean renderAsNormalBlock() {return false;}
	public boolean isOpaqueCube() {return false;}
	
	public TileEntity createNewTileEntity(World world) {	
		
	        try
	        {
	            return new TileEntityCampFire();
	        }
	        catch (Exception var3)
	        {
	            throw new RuntimeException(var3);
	        }
	    }
	
	      

                

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(dms.modID + ":" + this.getUnlocalizedName().substring(5));
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int var2) {return new TileEntityCampFire();}
    
	
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer ep) {
		ExtendedPlayer props = ExtendedPlayer.get(ep);
		if(!world.isRemote&&mc.thePlayer.isSneaking()){
			TileEntityCampFire t =(TileEntityCampFire) world.getTileEntity(x,y,z);
			 world.playSoundEffect(x, y, z,dms.modID+":addFuel", 1, 1);
			ep.addChatMessage(new ChatComponentText("Burn Time: "+Integer.toString(t.getBurnTime())));
			props.consumeSanity(2);
			System.out.println("[TEST SERVER Sanity]"+props.getSanity());
		}else{
System.out.println("[TEST CLIENT Sanity]"+props.getSanity());
			}
		
	}
			
			
	
			
			
		
		
	
	public void onBlockDestroyedByPlayer(World worldObj,int xCoord,int yCoord,int zCoord,int meta){
		if(!worldObj.isRemote){
    		worldObj.setBlock(xCoord, yCoord+1, zCoord, Blocks.air);
  			worldObj.setBlock(xCoord-2, yCoord+1, zCoord+2, Blocks.air);
  			worldObj.setBlock(xCoord+2, yCoord+1, zCoord-2, Blocks.air);
  			worldObj.setBlock(xCoord+2, yCoord+1, zCoord+2, Blocks.air);
  			worldObj.setBlock(xCoord-2, yCoord+1, zCoord-2, Blocks.air);
  			worldObj.setBlock(xCoord-1, yCoord+1, zCoord+1, Blocks.air);
  			worldObj.setBlock(xCoord+1, yCoord+1, zCoord-1, Blocks.air);
  			worldObj.setBlock(xCoord+1, yCoord+1, zCoord+1, Blocks.air);
  			worldObj.setBlock(xCoord-1, yCoord+1, zCoord-1, Blocks.air);
    		
    	}
	}
	
	public void onBlockHarvested(World world,int x,int y,int z,int meta,EntityPlayer ep){
		TileEntityCampFire t = (TileEntityCampFire) world.getTileEntity(x, y, z);
        t.ClearBlock();
	
        	
    
}
	}
