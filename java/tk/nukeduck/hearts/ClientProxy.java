package tk.nukeduck.hearts;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tk.nukeduck.hearts.CommonProxy;
import tk.nukeduck.hearts.HeartCrystalRenderer;
import tk.nukeduck.hearts.TileEntityHeartCrystal;

public class ClientProxy extends CommonProxy {

   public static int renderId;


   @SideOnly(Side.CLIENT)
   public void registerRenderers() {
      renderId = RenderingRegistry.getNextAvailableRenderId();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHeartCrystal.class, new HeartCrystalRenderer());
   }
}
