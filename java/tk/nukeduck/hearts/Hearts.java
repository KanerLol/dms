package tk.nukeduck.hearts;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import tk.nukeduck.hearts.BlockHeartCrystal;
import tk.nukeduck.hearts.ClientProxy;
import tk.nukeduck.hearts.CommonProxy;
import tk.nukeduck.hearts.ItemHeartCrystal;
import tk.nukeduck.hearts.TileEntityHeartCrystal;
import tk.nukeduck.hearts.WorldGeneratorHearts;

@Mod(
   modid = "hearts",
   name = "Heart Crystal Mod",
   version = "1.0"
)
public class Hearts {

   public static Block heartCrystal;
   public static Item heartCrystalItem;
   @SidedProxy(
      clientSide = "tk.nukeduck.hearts.ClientProxy",
      serverSide = "tk.nukeduck.hearts.CommonProxy"
   )
   public static CommonProxy proxy;
   public static ClientProxy clientProxy = new ClientProxy();


   @SubscribeEvent
   public void onDrop(LivingDropsEvent event) {
      try {
         event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(heartCrystalItem, (int)event.entityLiving.getEntityAttribute(SharedMonsterAttributes.maxHealth).getModifier(event.entityLiving.getPersistentID()).getAmount() / 2)));
      } catch (Exception var3) {
         ;
      }

   }

   @EventHandler
   public static void init(FMLInitializationEvent event) {
      GameRegistry.registerWorldGenerator(new WorldGeneratorHearts(), 1);
      heartCrystal = (new BlockHeartCrystal(Material.glass)).setBlockName("heartcrystal").setHardness(1.5F);
      heartCrystal.setHarvestLevel("pickaxe", 2);
      GameRegistry.registerBlock(heartCrystal, "heartcrystal");
      heartCrystalItem = (new ItemHeartCrystal(0)).setUnlocalizedName("heartcrystalitem");
      GameRegistry.registerItem(heartCrystalItem, "heartcrystalitem");
      GameRegistry.registerTileEntity(TileEntityHeartCrystal.class, "HeartCrystal");
      MinecraftForge.EVENT_BUS.register(new Hearts());
      clientProxy.registerRenderers();
   }

}
