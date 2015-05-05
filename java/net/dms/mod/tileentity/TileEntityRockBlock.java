package net.dms.mod.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRockBlock extends TileEntity {

    private static int Stage = 0;

	void setStage(int stage){
    	this.Stage=stage;
    }
    
    public int getStage(){
    	return this.Stage;
    }
    
    
    @Override
    public void readFromNBT(NBTTagCompound nbt)
{
    super.readFromNBT(nbt);
    this.Stage = nbt.getInteger("Stage");
}

@Override
public void writeToNBT(NBTTagCompound nbt)
{
    super.writeToNBT(nbt);

    nbt.setInteger("Stage",Stage);
}	
	
	
}
