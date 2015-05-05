package tk.nukeduck.hearts;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import tk.nukeduck.hearts.ModelHeart;
import tk.nukeduck.hearts.TileEntityHeartCrystal;

public class HeartCrystalRenderer extends TileEntitySpecialRenderer {

   private ModelHeart model = new ModelHeart();
   private ResourceLocation texture = new ResourceLocation("hearts", "textures/blocks/heart.png");


   public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
      TileEntityHeartCrystal te = (TileEntityHeartCrystal)tileentity;
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
      GL11.glTranslatef((float)d0 + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef((float)te.rotation, 0.0F, 1.0F, 0.0F);
      this.bindTexture(this.texture);
      this.model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }
}
