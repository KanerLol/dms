package net.dms.mod;

import net.dms.mod.blocks.BlocksDMS;
import net.dms.mod.gui.GuiBuffBar;
import net.dms.mod.handlers.EventHandlerDMS;
import net.dms.mod.handlers.GuiHandler;
import net.dms.mod.items.ItemDec;
import net.dms.mod.items.Items;
import net.dms.mod.packets.PacketPipeline;
import net.dms.mod.proxy.CommonProxy;
import net.dms.mod.tileentity.TileEntityCampFire;
import net.dms.mod.tileentity.TileEntityDMS;
import net.dms.mod.tileentity.TileEntityRock1Block;
import net.dms.mod.tileentity.TileEntityRock2Block;
import net.dms.mod.tileentity.TileEntityRockBlock;
import net.dms.mod.tileentity.TileEntityRocksBlock;
import net.dms.mod.tools.Tools;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


@Mod(modid = dms.modID , version = dms.version , name = dms.modName)
public class dms{
	public static final String modID = "dms";
	public static final String version = "PreAlpha";
	public static final String modName = "Dont MineStarve";
	

	@Instance(modID)
	public static dms instance;
	     
     
	public static CreativeTabs tabDMS = new CreativeTabs("DontMineStarve"){
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem(){
			return Item.getItemById(Item.getIdFromItem(ItemDec.itemCutGrass));
		}
	};
	
	//Materials
	public static ToolMaterial DMSFlintShovel = EnumHelper.addToolMaterial("FlintShovel", 5, 25, 50.0F, 3.0F, 0);
	public static ToolMaterial DMSFlintPickaxe = EnumHelper.addToolMaterial("FlintPickaxe", 5, 33, 50.0F, 3.0F, 0);
	
	
	@SidedProxy(clientSide = "net.dms.mod.proxy.ClientProxy",serverSide = "net.dms.mod.proxy.CommonProxy")
	public static CommonProxy dmsProxy;
	public static final PacketPipeline packetPipeline = new PacketPipeline();
	public static final int guiIdCrockPot=0;
	
		
	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent){
		BlocksDMS.init();
		Tools.init();
		Items.init();
		TileEntityDMS.init();
		
		//Renderer
		dmsProxy.registerRenderThings();
		
	
	}
	@EventHandler
	public void Init(FMLInitializationEvent Event){
		packetPipeline.initialise();
		MinecraftForge.EVENT_BUS.register(new EventHandlerDMS());
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	
	}
	@EventHandler
	public void PostInit(FMLPostInitializationEvent PostEvent){
		packetPipeline.postInitialise();
		MinecraftForge.EVENT_BUS.register(new GuiBuffBar(Minecraft.getMinecraft()));
		
		
	}
	
	
	
		
};
