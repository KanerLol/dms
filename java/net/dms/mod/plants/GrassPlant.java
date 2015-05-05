package net.dms.mod.plants;

import java.util.Random;

import javax.swing.Icon;

import net.dms.mod.dms;
import net.dms.mod.blocks.BlockDec;
import net.dms.mod.items.ItemDec;
import net.dms.mod.items.Items;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;




public class GrassPlant extends BlockCrops{
	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;
   public GrassPlant() {
	   super();
	   this.setHardness(30.0F);
	   this.setHarvestLevel("shovel",5);
   }
   
   
   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister iconRegister){
	   this.iconArray = new IIcon[4];
	   for (int i = 0;i<this.iconArray.length;i++) {
		   this.iconArray[i] = iconRegister.registerIcon(dms.modID + ":" + this.getUnlocalizedName().substring(5) + (i+1));
	   }
   }
  
   public IIcon getIcon(int side,int metadata){
	   if (metadata <6){
		   if (metadata == 5) {
			   metadata =4;
		   }
		   {
		   
		   return this.iconArray[metadata >> 1];
		   }
	   }
	   {
		  
	   return this.iconArray[3];
	   }
   }
	   
   
   public int quantityDropped (Random random){
	   return 0;
   }
   
   protected Item func_149866_i()
   {
       return null;
   }
   protected Item func_149865_P()
   {
       return null;
   }
   
 
   public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
   {
	   
	if(Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem()== null&&par1World.getBlockMetadata(par2,par3,par4)>=6){
		if(!par1World.isRemote){
	
	   ItemStack drop = new ItemStack(ItemDec.itemCutGrass);
	   EntityItem dropItem = new EntityItem(par1World,par2,par3,par4,drop);
	   par1World.spawnEntityInWorld(dropItem);
       par1World.setBlock(par2, par3, par4, BlockDec.grassPlant);
     
       return true;
            }
		else if(Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem()== null&&par1World.getBlockMetadata(par2,par3,par4)>=6){
			if(!par1World.isRemote){
				
				   ItemStack drop = new ItemStack(ItemDec.itemCutGrass);
				   EntityItem dropItem = new EntityItem(par1World,par2,par3,par4,drop);
				   par1World.spawnEntityInWorld(dropItem);
			       par1World.setBlock(par2, par3, par4, BlockDec.grassTuftBlock);
			 
			       return true;
			            }
	
		}
	}
	return true;
   }
   public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta){ 
      if(meta>=6&&(!world.isRemote)){
    		   
    		   
    		   ItemStack drop = new ItemStack(ItemDec.itemCutGrass);
    		   EntityItem dropItem = new EntityItem(world,x,y,z,drop);
    		   world.spawnEntityInWorld(dropItem);
    		   
    		   ItemStack drop1 = new ItemStack(ItemDec.itemGrassTuft);
    		   EntityItem drop1Item = new EntityItem(world,x,y,z,drop1);
    		   world.spawnEntityInWorld(drop1Item);
      }
      			   else if(world.getBlockMetadata(x,y,z)<6&&(!world.isRemote)){
      					ItemStack drop1 = new ItemStack(ItemDec.itemGrassTuft);
      					EntityItem drop1Item = new EntityItem(world,x,y,z,drop1);
      					world.spawnEntityInWorld(drop1Item);
		  
      					}	     
   }
   
   @EventHandler
   public void init(FMLInitializationEvent event)
   {
	
   }
}
 