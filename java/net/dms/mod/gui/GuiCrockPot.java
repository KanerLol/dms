package net.dms.mod.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.dms.mod.dms;
import net.dms.mod.container.ContainerCrockPot;
import net.dms.mod.packets.SyncButtonGuiPot;
import net.dms.mod.packets.SyncPlayerPropsPacket;
import net.dms.mod.tileentity.TileEntityCrockPot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class GuiCrockPot extends GuiContainer{

	private ResourceLocation texture = new ResourceLocation(dms.modID+":"+"/textures/gui/CrockPotGui.png");
	private TileEntityCrockPot crockPot;
	public int x,z,y;
	Minecraft mc =Minecraft.getMinecraft();
	
	
	public GuiCrockPot(InventoryPlayer invPl,TileEntityCrockPot tecp) {
		super(new ContainerCrockPot(invPl,tecp));
	    crockPot = tecp;
	    
	    this.xSize = 176;
		this.ySize = 168;
		this.x=crockPot.xCoord;
		this.y=crockPot.yCoord;
		this.z=crockPot.zCoord;
	}

	protected void drawGuiContainerForegroundLayer(int i, int j) {
		String name = this.crockPot.hasCustomInventoryName() ? this.crockPot.getInventoryName() : I18n.format(this.crockPot.getInventoryName());
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 94 + 8, 4210752);
		
	}
	
	
	
	    
	  
	@Override
	protected void drawGuiContainerBackgroundLayer(float arg0, int arg1,
			int arg2) {
		 {
		        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		        GuiButton button =(GuiButton)buttonList.get(0);
		        if (button.id==0&&!crockPot.ButtonV()){
		        	button.visible=false;
		        }
		        if (button.id==0&&crockPot.ButtonV()){
		        	button.visible=true;
		        }
		    }
		
	}
	
	@Override
	public void actionPerformed(GuiButton button)
	{
		
		switch(button.id){
		case 0:{
		
			System.out.println("[Button]:true");
			 if (crockPot.ButtonV()){
			 System.out.println(crockPot.ButtonV());
			 }
			
			 crockPot.invertButton();
			dms.packetPipeline.sendToServer(new SyncButtonGuiPot(crockPot.xCoord,crockPot.yCoord,crockPot.zCoord));
		
		}
		break;
		default:
			break;
		}
	}
public void initGui() {
	super.initGui();
    mc.thePlayer.openContainer = inventorySlots;
    guiLeft = (width - xSize) / 2;
    guiTop = (height - ySize) / 2;
	    this.buttonList.clear();
	    
	    this.buttonList.add(new GuiButton(0,50,50,12,12,"Cook"));

	    

	}

}
