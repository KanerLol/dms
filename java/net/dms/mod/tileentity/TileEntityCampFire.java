package net.dms.mod.tileentity;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.dms.mod.block.CampFireBlock;
import net.dms.mod.blocks.BlockDec;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class TileEntityCampFire extends TileEntity {
	
	public TileEntityCampFire(){
		setBurnTime(135);
	}
	
private int BurnTime;
private int StageBurn;
private final int max=300;
private boolean part1,part2,part3,part4,part5;

public void updateEntity() {
	 
      if (!worldObj.isRemote){
          if (BurnTime>0){
        	  if (worldObj.getWorldTime() % (20*1) == 0) {
        		  setBurnTime(getBurnTime()-1);
        		  if (BurnTime>220&&BurnTime<=max&&StageBurn!=5){
        			  StageBurn=5;
        			  ClearBlock();
        			 part5=true;
        			 part1=part2=part3=part4=false;
          			worldObj.setBlock(xCoord, yCoord+1, zCoord, BlockDec.Light1);
          			worldObj.setBlock(xCoord-2, yCoord+1, zCoord+2, BlockDec.Light1);
          			worldObj.setBlock(xCoord+2, yCoord+1, zCoord-2, BlockDec.Light1);
          			worldObj.setBlock(xCoord+2, yCoord+1, zCoord+2, BlockDec.Light1);
          			worldObj.setBlock(xCoord-2, yCoord+1, zCoord-2, BlockDec.Light1);
	       				}
		        if (BurnTime>180&&BurnTime<=220&&StageBurn!=4){
		        	StageBurn=4;
		        	ClearBlock();
		        	 part4=true;
        			 part2=part3=part1=part5=false;
		        	worldObj.setBlock(xCoord, yCoord+1, zCoord, BlockDec.Light1);
		        	worldObj.setBlock(xCoord-1, yCoord+1, zCoord+1, BlockDec.Light1);
		  			worldObj.setBlock(xCoord+1, yCoord+1, zCoord-1, BlockDec.Light1);
		  			worldObj.setBlock(xCoord+1, yCoord+1, zCoord+1, BlockDec.Light1);
		  			worldObj.setBlock(xCoord-1, yCoord+1, zCoord-1, BlockDec.Light1);
		        	
				       	}
		        if (BurnTime>120&&BurnTime<=180&&StageBurn!=3){
		        	StageBurn=3;
		        	 part3=true;
        			 part2=part1=part4=part5=false;
		        	ClearBlock();
		        	worldObj.setBlock(xCoord, yCoord+1, zCoord, BlockDec.Light1);
		        	
				  	}
				if (BurnTime>70&&BurnTime<=120&&StageBurn!=2){
			    	StageBurn=2;
			    	 part2=true;
        			 part1=part3=part4=part5=false;
			    	ClearBlock();
			    	worldObj.setBlock(xCoord, yCoord+1, zCoord, BlockDec.Light2);
			    	
				  	}
			    if (BurnTime>0&&BurnTime<=70&&StageBurn!=1){
			    	StageBurn=1;
			    	 part1=true;
        			 part2=part3=part4=part5=false;
			    	ClearBlock();
			    	worldObj.setBlock(xCoord, yCoord+1, zCoord, BlockDec.Light3);
			    	
				}
        		  }
        	    	
        		 }
        	  
        	  
        	  
          if (BurnTime==0){
        	 worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
        	 worldObj.removeTileEntity(xCoord, yCoord, zCoord);
          }
        	 if (BurnTime>max){
            	 BurnTime=max;
        	 }
      }
      
      else if (worldObj.isRemote){
    	  if (worldObj.getWorldTime() % (10*1) == 0) {
    		  
    			  worldObj.spawnParticle("smoke", (double)(xCoord+0.5), (double)(yCoord+0.3), (double)(zCoord+0.5), 0D, 0D, 0D);
    	          worldObj.spawnParticle("flame", (double)(xCoord+0.5), (double)(yCoord+0.3), (double)(zCoord+0.5), 0D, 0D, 0D);
    	          worldObj.spawnParticle("smoke", (double)(xCoord+0.3), (double)(yCoord+0.4), (double)(zCoord+0.7), 0D, 0D, 0D);
    	          worldObj.spawnParticle("flame", (double)(xCoord+0.3), (double)(yCoord+0.4), (double)(zCoord+0.7), 0D, 0D, 0D);
    	          worldObj.spawnParticle("smoke", (double)(xCoord+0.7), (double)(yCoord+0.3), (double)(zCoord+0.4), 0D, 0D, 0D);
    	          worldObj.spawnParticle("flame", (double)(xCoord+0.7), (double)(yCoord+0.3), (double)(zCoord+0.4), 0D, 0D, 0D);
    	          worldObj.spawnParticle("largesmoke", (double)(xCoord+0.2), (double)(yCoord+0.4), (double)(zCoord+0.8), 0D, 0D, 0D);
    	          worldObj.spawnParticle("largesmoke", (double)(xCoord+0.5), (double)(yCoord+0.3), (double)(zCoord+0.5), 0D, 0D, 0D);
    		  
    	  }
    	  
    	  }
      
 }		     

		      	     

    public void ClearBlock(){
    	if(!worldObj.isRemote){
    		worldObj.setBlock(xCoord, yCoord+1, zCoord, Blocks.air);
  			worldObj.setBlock(xCoord-2, yCoord+1, zCoord+2, Blocks.air);
  			worldObj.setBlock(xCoord+2, yCoord+1, zCoord-2, Blocks.air);
  			worldObj.setBlock(xCoord+2, yCoord+1, zCoord+2, Blocks.air);
  			worldObj.setBlock(xCoord-2, yCoord+1, zCoord-2, Blocks.air);
  			worldObj.setBlock(xCoord-1, yCoord+1, zCoord+1, Blocks.air);
  			worldObj.setBlock(xCoord+1, yCoord+1, zCoord-1, Blocks.air);
  			worldObj.setBlock(xCoord+1, yCoord+1, zCoord+1, Blocks.air);
  			worldObj.setBlock(xCoord-1, yCoord+1, zCoord-1, Blocks.air);
    		
    	}
    }
    
    
    public void setBurnTime(int time){
    	this.BurnTime=time;
    }
    
    public int getBurnTime(){
    	return this.BurnTime;
    }
    
    public void setStageBurn(int stage){
    	this.StageBurn=stage;
    }
    
    public int getStageBurn(){
    	return this.StageBurn;
    }
    
    
    @Override
    public void readFromNBT(NBTTagCompound nbt)
{
    super.readFromNBT(nbt);
    this.BurnTime = nbt.getInteger("BurnTime");
    this.StageBurn = nbt.getInteger("StageBurn");
}

@Override
public void writeToNBT(NBTTagCompound nbt)
{
    super.writeToNBT(nbt);
    nbt.setInteger("BurnTime", BurnTime);
    nbt.setInteger("StageBurn",StageBurn);
}

}
