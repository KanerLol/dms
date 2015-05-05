package net.dms.mod.items;

import net.dms.mod.dms;
import net.dms.mod.entity.ExtendedPlayer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemReg extends ItemFood {

	   public ItemReg(int par1) {
	      super(par1, 0.0F, false);
	      this.setCreativeTab(dms.tabDMS);
	      this.setAlwaysEdible();
	   }

	   @SideOnly(Side.CLIENT)
	   public void registerIcons(IIconRegister par1IconRegister) {
		   this.itemIcon = par1IconRegister.registerIcon(dms.modID + ":" + this.getUnlocalizedName().substring(5)); 
	   }

	   protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer ep) {
		   ExtendedPlayer props = ExtendedPlayer.get(ep);
		   props.setSanity(150);
		   ep.setHealth(150.0f);
	   }

	   @SideOnly(Side.CLIENT)
	   public boolean hasEffect(ItemStack par1ItemStack, int pass) {
	      return true;
	   }
}