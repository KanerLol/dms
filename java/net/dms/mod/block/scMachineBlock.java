package net.dms.mod.block;

import java.util.Random;

import net.dms.mod.dms;
import net.dms.mod.blocks.BlockDec;
import net.dms.mod.items.ItemDec;
import net.dms.mod.tileentity.TileEntityFlintBlock;
import net.dms.mod.tileentity.TileEntityRock1Block;
import net.dms.mod.tileentity.TileEntityRock2Block;
import net.dms.mod.tileentity.TileEntityRockBlock;
import net.dms.mod.tileentity.TileEntityRocksBlock;
import net.dms.mod.tileentity.TileEntityScMachineBlock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;




public class scMachineBlock extends BlockContainer {

	public scMachineBlock(Material material) {
		super(material);
		this.setLightLevel(0);
		this.setLightOpacity(0);
		this.setHardness(30.0F);
		this.setBlockBounds(0F,0f,0f,1f,1.6f,1f);
		this.setCreativeTab(dms.tabDMS);
		

		
	}
	public int getRenderType() {return -1;}
	public boolean renderAsNormalBlock() {return false;}
	public boolean isOpaqueCube() {return false;}
	

	
	public int quantityDropped (Random random){
		   return 0;
	   }
	
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta){   
	   }

	
	@Override
	public TileEntity createNewTileEntity(World arg0, int var2) {return new TileEntityScMachineBlock();}
    
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(dms.modID + ":" + this.getUnlocalizedName().substring(5));
	}
	
}
