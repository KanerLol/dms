package net.dms.mod.tools;

import net.dms.mod.dms;
import net.dms.mod.blocks.BlockDec;
import net.dms.mod.items.SimpleItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import static net.dms.mod.tools.ToolsDec.*;

public class Tools {



	public static void init() {
		registerItems();
	}
	
	public static void registerItems(){
		toolsFlintShovel = registerItem(new Shovels(dms.DMSFlintShovel).setUnlocalizedName("FlintShovel"));
		toolsFlintPickaxe = registerItem(new Pickaxe(dms.DMSFlintPickaxe).setUnlocalizedName("FlintPickaxe"));
	}
	
	public static Item registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
		
		return item;
	}
}
