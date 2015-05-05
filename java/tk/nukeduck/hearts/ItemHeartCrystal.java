package tk.nukeduck.hearts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHeartCrystal extends ItemFood {

   public ItemHeartCrystal(int par1) {
      super(par1, 0.0F, false);
      this.setCreativeTab(CreativeTabs.tabMisc);
      this.setAlwaysEdible();
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IIconRegister par1IconRegister) {
      super.itemIcon = par1IconRegister.registerIcon("hearts:heartIcon");
   }

   protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      double valueToSet = 2.0D;

      try {
         valueToSet = par3EntityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth).getModifier(par3EntityPlayer.getPersistentID()).getAmount() + 2.0D;
      } catch (Exception var8) {
         ;
      }

      AttributeModifier moreHealth = new AttributeModifier(par3EntityPlayer.getPersistentID(), "Heart Crystal Health Boost", valueToSet, 0);
      IAttributeInstance attributeinstance = par3EntityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
      attributeinstance.removeModifier(moreHealth);
      attributeinstance.applyModifier(moreHealth);
   }

   @SideOnly(Side.CLIENT)
   public boolean hasEffect(ItemStack par1ItemStack, int pass) {
      return true;
   }
}
