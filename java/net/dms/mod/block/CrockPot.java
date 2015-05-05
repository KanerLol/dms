package net.dms.mod.block;

import java.util.Random;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.dms.mod.dms;
import net.dms.mod.blocks.BlockDec;
import net.dms.mod.packets.SyncButtonGuiPot;
import net.dms.mod.packets.SyncButtonGuiPotOnOpen;
import net.dms.mod.tileentity.TileEntityCampFire;
import net.dms.mod.tileentity.TileEntityCrockPot;
import net.dms.mod.tileentity.TileEntityRock1Block;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CrockPot extends BlockContainer {

	private Random rand;
	private final boolean isActive;
	private static boolean keepInventory =false;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	
	
	public CrockPot(boolean state) {
		super(Material.iron);
		rand= new Random();
		isActive=state;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ireg){
		this.blockIcon = ireg.registerIcon(dms.modID+":"+ (this.isActive ? "CrockPotSideOn" : "CrockPotSideOff"));
		this.iconFront = ireg.registerIcon(dms.modID+":"+ (this.isActive ? "CrockPotFrontOn" : "CrockPotFrontOff"));
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side,int metadata){
		return metadata == 0 && side==3 ? this.iconFront :(side==metadata ? this.iconFront:this.blockIcon);
	}
	
	public void onBlockAdded(World world,int x,int y,int z){
		super.onBlockAdded(world,x,y,z);
		this.setDefaultDirection(world,x,y,z);
	}
	
	private void setDefaultDirection(World world, int x, int y, int z) {
		if(!world.isRemote){
			Block block1 = world.getBlock(x, y, z - 1);
			Block block2 = world.getBlock(x, y, z + 1);
			Block block3 = world.getBlock(x - 1, y, z);
			Block block4 = world.getBlock(x + 1, y, z);
			
			byte b0 = 3;
			
			if(block1.func_149730_j() && !block2.func_149730_j()){
				b0 = 3;
			}
			
			if(block2.func_149730_j() && !block1.func_149730_j()){
				b0 = 2;
			}
			
			if(block3.func_149730_j() && !block4.func_149730_j()){
				b0 = 5;
			}
			
			if(block4.func_149730_j() && !block3.func_149730_j()){
				b0 = 4;
			}
	
			world.setBlockMetadataWithNotify(x, y, x, b0, 2);
		}
	}
		
		public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityPlayer, ItemStack itemstack) {
	    	int i = MathHelper.floor_double((double)(entityPlayer.rotationYaw * 4.0F / 360F) + 0.5D) & 3;
	    	
	    	if (i == 0) {
	    		world.setBlockMetadataWithNotify(x, y, z, 2, 2);
	    	}
	    	
	    	if (i == 1) {
	    		world.setBlockMetadataWithNotify(x, y, z, 5, 2);
	    	}
	    	
	    	if (i == 2) {
	    		world.setBlockMetadataWithNotify(x, y, z, 3, 2);
	    	}
	    	
	    	if (i == 3) {
	    		world.setBlockMetadataWithNotify(x, y, z, 4, 2);
	    	}
	    	
	    	if(itemstack.hasDisplayName()) {
	    		//((TileEntityCrockPot)world.getTileEntity(x, y, z)).setCustomName(itemstack.getDisplayName());
	    	}
	    }
		
		 public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
			if (!world.isRemote&&!player.isSneaking()){
				TileEntityCrockPot entity = (TileEntityCrockPot) world.getTileEntity(x, y, z);

				dms.packetPipeline.sendTo(new SyncButtonGuiPotOnOpen(x,y,z,entity.Button),(EntityPlayerMP) player);
				
			} 
			 if (player.isSneaking()){
				 TileEntityCrockPot entity = (TileEntityCrockPot) world.getTileEntity(x, y, z);
				 System.out.println("[BUTTON]:"+entity.Button);
				 System.out.println("[BUTTONV]:"+entity.ButtonV());
			 }
		    	if (world.isRemote) {
		    		return true;
		    	}else if (!player.isSneaking()) {
		    		TileEntityCrockPot entity = (TileEntityCrockPot) world.getTileEntity(x, y, z);
		    		if (entity != null) {
		    			FMLNetworkHandler.openGui(player, dms.instance, dms.guiIdCrockPot, world, x, y, z);
		    		}
		    		return true;
		    	}else{
		    		return false;
		    	}
		    }

		@Override
		public TileEntity createNewTileEntity(World arg0, int var2) {return new TileEntityCrockPot();}
		
		public static void updateBlockState(boolean isCooking, World world, int xCoord, int yCoord, int zCoord) {
			
			int i = world.getBlockMetadata(xCoord, yCoord, zCoord);
			TileEntityCrockPot entity = (TileEntityCrockPot)world.getTileEntity(xCoord, yCoord, zCoord);
			keepInventory = true;
			
			if (isCooking) {
				world.setBlock(xCoord, yCoord, zCoord, BlockDec.CrockPotActive);
			}else{
				world.setBlock(xCoord, yCoord, zCoord, BlockDec.CrockPotIdle);
			}
			
			keepInventory = false;
			world.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);
			
			if (entity != null) {
				entity.validate();
				world.setTileEntity(xCoord, yCoord, zCoord, entity);
			}
		}
	}


