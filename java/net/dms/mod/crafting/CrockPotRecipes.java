package net.dms.mod.crafting;

import net.dms.mod.items.ItemDec;
import net.dms.mod.items.SimpleItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class CrockPotRecipes {

	public CrockPotRecipes() {
		
	}
	
	public static ItemStack getCookingResult(Item i1, Item i2,Item i3,Item i4) {
		return getOutput(i1,i2,i3,i4);
	}
	
	public static ItemStack getOutput(Item i1, Item i2, Item i3, Item i4) {
		//Recipe One
		if (i1 == ItemDec.itemFlint && i2 == Items.iron_ingot|| i1 == Items.iron_ingot && i2 == ItemDec.itemFlint) {
			return new ItemStack(ItemDec.itemRocks, 1);
		}
		return null;
	}
	
}
