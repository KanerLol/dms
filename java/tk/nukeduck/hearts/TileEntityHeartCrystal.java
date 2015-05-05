package tk.nukeduck.hearts;

import net.minecraft.tileentity.TileEntity;

public class TileEntityHeartCrystal extends TileEntity {

   public int rotation = 0;


   public void updateEntity() {
      this.rotation += 2;
      if(this.rotation > 360) {
         this.rotation = 0;
      }

   }
}
