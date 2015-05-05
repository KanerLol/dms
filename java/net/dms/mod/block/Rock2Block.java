package net.dms.mod.block;

import java.util.Random;

import net.dms.mod.dms;
import net.dms.mod.blocks.BlockDec;
import net.dms.mod.items.ItemDec;
import net.dms.mod.tileentity.TileEntityRock1Block;
import net.dms.mod.tileentity.TileEntityRock2Block;
import net.dms.mod.tileentity.TileEntityRockBlock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;




public class Rock2Block extends BlockContainer {

	public Rock2Block(Material material) {
		super(material);
		this.setHarvestLevel("pickaxe", 5);
		this.setLightLevel(0);
		this.setLightOpacity(0);
		this.setHardness(15.0F);
		this.setBlockBounds(0, 0, 0, 1.0F,1.7F, 1.0F);
		this.setCreativeTab(dms.tabDMS);

		
	}
	public int getRenderType() {return -1;}
	public boolean renderAsNormalBlock() {return false;}
	public boolean isOpaqueCube() {return false;}
	
	
	public void onBlockPlacedBy(World world, int x, int y, int z,EntityLivingBase ep,ItemStack is){
		world.setBlock(x, y+1, z, Blocks.anvil);
	
	}
	public int quantityDropped (Random random){
		   return 0;
	   }
	   

	public void onBlockDestroyedByPlayer(World worldObj,int xCoord,int yCoord,int zCoord,int meta){
		if(!worldObj.isRemote){
			   worldObj.spawnEntityInWorld(new EntityItem(worldObj,xCoord,yCoord,zCoord,new ItemStack(ItemDec.itemRocks)));
			   worldObj.spawnEntityInWorld(new EntityItem(worldObj,xCoord,yCoord,zCoord,new ItemStack(ItemDec.itemRocks)));
			   worldObj.spawnEntityInWorld(new EntityItem(worldObj,xCoord,yCoord,zCoord,new ItemStack(ItemDec.itemRocks)));
			   Random random = new Random();
			   int i =random.nextInt(100);
			   if (i<60){
				   
				   worldObj.spawnEntityInWorld(new EntityItem(worldObj,xCoord,yCoord,zCoord,new ItemStack(ItemDec.itemFlint)));
				   worldObj.spawnEntityInWorld(new EntityItem(worldObj,xCoord,yCoord,zCoord,new ItemStack(ItemDec.itemFlint)));
			   } else
				   worldObj.spawnEntityInWorld(new EntityItem(worldObj,xCoord,yCoord,zCoord,new ItemStack(ItemDec.itemFlint)));
		
		       int j=random.nextInt(100);
		       	if (i<25){
				   
				   worldObj.spawnEntityInWorld(new EntityItem(worldObj,xCoord,yCoord,zCoord,new ItemStack(ItemDec.itemNitre)));
				   worldObj.spawnEntityInWorld(new EntityItem(worldObj,xCoord,yCoord,zCoord,new ItemStack(ItemDec.itemNitre)));
			   } else
				   worldObj.spawnEntityInWorld(new EntityItem(worldObj,xCoord,yCoord,zCoord,new ItemStack(ItemDec.itemNitre)));
		}
    		
    	}
	
	
	@Override
	public TileEntity createNewTileEntity(World arg0, int var2) {return new TileEntityRock2Block();}
    
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(dms.modID + ":" + this.getUnlocalizedName().substring(5));
	}
	
}
