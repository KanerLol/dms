package net.dms.mod.proxy;

import net.dms.mod.renderer.RenderCampFire;
import net.dms.mod.renderer.RenderFlintBlock;
import net.dms.mod.renderer.RenderRock1Block;
import net.dms.mod.renderer.RenderRock2Block;
import net.dms.mod.renderer.RenderRockBlock;
import net.dms.mod.renderer.RenderRocksBlock;
import net.dms.mod.renderer.RenderScMachineBlock;
import net.dms.mod.tileentity.TileEntityCampFire;
import net.dms.mod.tileentity.TileEntityFlintBlock;
import net.dms.mod.tileentity.TileEntityRock1Block;
import net.dms.mod.tileentity.TileEntityRock2Block;
import net.dms.mod.tileentity.TileEntityRockBlock;
import net.dms.mod.tileentity.TileEntityRocksBlock;
import net.dms.mod.tileentity.TileEntityScMachineBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy {

	
      public void registerRenderThings() {
    	  
    	  //CampFire
    	  TileEntitySpecialRenderer renderCampFire = new RenderCampFire();
    	  ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCampFire.class, renderCampFire ); 
    	  //RockBlock
    	  TileEntitySpecialRenderer renderRockBlock = new RenderRockBlock();
    	  ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRockBlock.class, renderRockBlock );
    	  TileEntitySpecialRenderer renderRock1Block = new RenderRock1Block();
    	  ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRock1Block.class, renderRock1Block );
    	  TileEntitySpecialRenderer renderRock2Block = new RenderRock2Block();
    	  ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRock2Block.class, renderRock2Block );
    	  //Rocks
    	  TileEntitySpecialRenderer renderRocksBlock = new RenderRocksBlock();
    	  ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRocksBlock.class, renderRocksBlock ); 
    	  //Flint
    	//Rocks
    	  TileEntitySpecialRenderer renderFlintBlock = new RenderFlintBlock();
    	  ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFlintBlock.class, renderFlintBlock ); 
    	  
    	  
    	  //Machine
    	    //ScienceMachine
    	  TileEntitySpecialRenderer renderScMachineBlock = new RenderScMachineBlock();
    	  ClientRegistry.bindTileEntitySpecialRenderer(TileEntityScMachineBlock.class, renderScMachineBlock ); 
    	  
    	  

      }
      
      public void registerTileEntitySpecialRenderer() {
    	  
      }
}
