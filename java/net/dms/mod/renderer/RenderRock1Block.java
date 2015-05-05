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

public class RenderRock1Block extends TileEntitySpecialRenderer {

	private static IModelCustom model;
	private static String textureName="Rock1";
	
	private ResourceLocation texture = new ResourceLocation(dms.modID+":textures/model/"+ textureName +".png");
	
	public RenderRock1Block() {
		this.model = AdvancedModelLoader.loadModel(new ResourceLocation(dms.modID+":model/obj/Rock.obj"));;
	}
	
	public static void setTextureName(String name){
		textureName = name;
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y,double z, float f) {
		GL11.glPushMatrix();
		  GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		  GL11.glDisable(GL11.GL_CULL_FACE);
		  GL11.glEnable(3042); // Не трогать
	      GL11.glBlendFunc(770, 771); // Не трогать
		  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		  GL11.glTranslatef((float)x+ 0.5F,(float) y+ 0F,(float) z+ 0.5F);
		  GL11.glRotatef(0, 0F, 0F, 0F);
			
		  Minecraft.getMinecraft().renderEngine.bindTexture(texture); // Текстура.
			
		  GL11.glPushMatrix();
		   this.model.renderAll(); //1/ (count of pixels) texture
		  GL11.glPopMatrix();
		  GL11.glDisable(3042); //Не трогать
		GL11.glPopMatrix();	
	}

}
