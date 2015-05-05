package net.dms.mod.plants;

import java.util.Random;

import javax.swing.Icon;

import net.dms.mod.dms;
import net.dms.mod.items.ItemDec;
import net.dms.mod.items.Items;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;




public class GrassTuftBlock extends BlockCrops{
	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;
   public GrassTuftBlock() {
       super();
   }
   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister iconRegister){
	   this.iconArray = new IIcon[1];
	   
	   for (int i = 0;i<this.iconArray.length;i++) {
		   this.iconArray[i] = iconRegister.registerIcon(dms.modID + ":" + this.getUnlocalizedName().substring(5) + (i+1));
	   }
   }
  
   public IIcon getIcon(int side,int metadata){
	   if (metadata <3){
		   if (metadata == 2) {
			   metadata =1;
		   }
		   return this.iconArray[metadata >> 1];
	   }
	   
	   return this.iconArray[0];
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
   
}
 