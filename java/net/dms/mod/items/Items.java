package net.dms.mod.items;

import static net.dms.mod.items.ItemDec.*;
import Source.CropsSource;
import Source.ManureSource;
import net.dms.mod.blocks.BlockDec;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;

public class Items {



	public static void init() {
		registerItems();
	}
	
	public static void registerItems(){
		itemCutGrass = registerItem(new SimpleItem(true,"Materials").setUnlocalizedName("CutGrass").setMaxStackSize(30));
		itemGrassTuft = registerItem(new CropsSource(BlockDec.grassTuftBlock,Blocks.grass,true,"Saplings").setUnlocalizedName("GrassTuft").setMaxStackSize(30));
		itemTwigs = registerItem(new SimpleItem(true,"Materials").setUnlocalizedName("Twigs"));
		itemFlint = registerItem(new SimpleItem(false,"").setUnlocalizedName("Flint"));
		itemRocks = registerItem(new SimpleItem(false,"").setUnlocalizedName("Rocks"));
		itemNitre = registerItem(new SimpleItem(false,"").setUnlocalizedName("Nitre"));
		
		
		
		
		itemManure = registerItem(new ManureSource(BlockDec.grassPlant,BlockDec.grassTuftBlock).setUnlocalizedName("Manure"));
	    itemCampFireBlock = registerItem(new ItemBlocks().setUnlocalizedName("CampFireItem"));
	
	
	
	
	   itemReg =registerItem(new ItemReg(0).setUnlocalizedName("CreativeItem"));
	}
	
	public static Item registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
		
		return item;
	}
}
