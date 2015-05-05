package net.dms.mod.blocks;

import static net.dms.mod.blocks.BlockDec.*;
import Source.BlockLightSource;
import cpw.mods.fml.common.registry.GameRegistry;
import net.dms.mod.dms;
import net.dms.mod.block.CampFireBlock;
import net.dms.mod.block.CrockPot;
import net.dms.mod.block.FlintBlock;
import net.dms.mod.block.Rock1Block;
import net.dms.mod.block.Rock2Block;
import net.dms.mod.block.RockBlock;
import net.dms.mod.block.RocksBlock;
import net.dms.mod.block.scMachineBlock;
import net.dms.mod.plants.GrassPlant;
import net.dms.mod.plants.GrassTuftBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlocksDMS {
	
	public static void init() {
		registerBlocks();
	}
	
	public static void registerBlocks() {
		  grassPlant = registerBlock(new GrassPlant().setBlockName("GrassPlant"));
		  grassTuftBlock = registerBlock(new GrassTuftBlock().setBlockName("GrassTuftBlock"));
		  campFireBlock = registerBlock(new CampFireBlock(Material.wood).setBlockName("CampFire"));
		  RocksBlock = registerBlock(new RocksBlock(Material.rock).setBlockName("RocksTile"));
		  RocksBlock = registerBlock(new FlintBlock(Material.rock).setBlockName("FlintTile"));
		  
		  
		  //Rocks
		  RockBlock = registerBlock(new RockBlock(Material.rock).setBlockName("RockStage_1"));
		  Rock1Block = registerBlock(new Rock1Block(Material.rock).setBlockName("RockStage_2"));
		  Rock2Block = registerBlock(new Rock2Block(Material.rock).setBlockName("RockStage_3"));
		  
		  //Machine
		  scMachine = registerBlock(new scMachineBlock(Material.rock).setBlockName("ScienceMachine"));
		  CrockPotIdle = registerBlock(new CrockPot(false).setBlockName("CrockPot").setCreativeTab(dms.tabDMS).setHardness(3.5F));
		  CrockPotActive = registerBlock(new CrockPot(true).setBlockName("CrockPotActive").setHardness(3.5F));
		  
		  
		  //LightBlock
		  Light1 = registerBlock(new BlockLightSource(Material.air, 1.0F).setBlockName("Light1"));
		  Light2 = registerBlock(new BlockLightSource(Material.air, 0.8F).setBlockName("Light2"));
		  Light3 = registerBlock(new BlockLightSource(Material.air, 0.5F).setBlockName("Light3"));
	}
	
	
	public static Block registerBlock(Block block) {
		GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
		
		
		return block;
	}
}
