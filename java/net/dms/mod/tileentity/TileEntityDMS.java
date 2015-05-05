package net.dms.mod.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;


public class TileEntityDMS {


	
	public static void init() {
		registerTE();
	}
	
	public static void registerTE() {
		GameRegistry.registerTileEntity(TileEntityCampFire.class, "TileEntity.CampFire");
		GameRegistry.registerTileEntity(TileEntityRockBlock.class, "TileEntity.RockBlock");
		GameRegistry.registerTileEntity(TileEntityRock1Block.class, "TileEntity.Rock1Block");
		GameRegistry.registerTileEntity(TileEntityRock2Block.class, "TileEntity.Rock2Block");
		GameRegistry.registerTileEntity(TileEntityRocksBlock.class, "TileEntity.RocksBlock");
		GameRegistry.registerTileEntity(TileEntityFlintBlock.class, "TileEntity.FlintBLock");
		GameRegistry.registerTileEntity(TileEntityScMachineBlock.class, "TileEntity.ScienceMachine");
		GameRegistry.registerTileEntity(TileEntityCrockPot.class, "TileEntity.CrockPot");

		 
}
}
