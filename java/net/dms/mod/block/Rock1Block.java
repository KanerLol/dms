package net.dms.mod.block;

import java.util.Random;

import net.dms.mod.dms;
import net.dms.mod.blocks.BlockDec;
import net.dms.mod.tileentity.TileEntityRock1Block;
import net.dms.mod.tileentity.TileEntityRock2Block;
import net.dms.mod.tileentity.TileEntityRockBlock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;




public class Rock1Block extends BlockContainer {

	public Rock1Block(Material material) {
		super(material);
		this.setHarvestLevel("pickaxe", 5);
		this.setLightLevel(0);
		this.setLightOpacity(0);
		this.setHardness(15.0F);
		this.setBlockBounds(0, 0, 0, 1.0F, 1.7F, 1.0F);
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
    		worldObj.setBlock(xCoord, yCoord, zCoord, BlockDec.Rock2Block);
    		
    	}
	}
	
	@Override
	public TileEntity createNewTileEntity(World arg0, int var2) {return new TileEntityRock1Block();}
    
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(dms.modID + ":" + this.getUnlocalizedName().substring(5));
	}
	
}
