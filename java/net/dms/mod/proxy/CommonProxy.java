package net.dms.mod.proxy;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;

public class CommonProxy {
	
	private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

      public void registerRenderThings() {
    	  
      }
      
      public void initialize() {
    	  
      }
      
      public void registerTileEntitySpecialRenderer() {
    	  
      }

      public static NBTTagCompound getEntityData(String name)
      {
      return extendedEntityData.remove(name);
      }
}
