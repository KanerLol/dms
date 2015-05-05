package net.dms.mod.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.dms.mod.dms;
import net.dms.mod.model.CampFireModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderCampFire extends TileEntitySpecialRenderer {

		
	private static CampFireModel model;
	private ResourceLocation texture = new ResourceLocation(dms.modID+":textures/model/CampFire.png");
	
	public RenderCampFire() {
		this.model = new CampFireModel();
	}
	
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y,double z, float f) {
		GL11.glPushMatrix();
		 GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		  GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		  GL11.glDisable(GL11.GL_CULL_FACE);
		  GL11.glDisable(GL11.GL_LIGHTING);
		  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		  GL11.glTranslatef((float)x+ 0.5F,(float) y+ 1.5F,(float) z+ 0.5F);
		  GL11.glRotatef(180, 0F, 0F, 0F);
			
		  this.bindTexture(texture);
			
		  GL11.glPushMatrix();
		   this.model.renderModel(0.0625F); //1/ (count of pixels) texture
		  GL11.glPopMatrix();
		 GL11.glPopAttrib();
		GL11.glPopMatrix();	
	}
	
	

	

}
