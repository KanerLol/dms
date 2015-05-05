package net.dms.mod.tileentity;

import net.dms.mod.block.CrockPot;
import net.dms.mod.crafting.CrockPotRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCrockPot extends TileEntity implements ISidedInventory {

	private ItemStack slots[];
	private String customName;
	public int cookTime;
	private final int cookingSpeed=100;
	public boolean Button=false;
	
	public TileEntityCrockPot(){
		slots=new ItemStack[5];
	}
@Override
	public ItemStack decrStackSize(int i, int j) {
		if (slots[i] != null) {
			if (slots[i].stackSize <= j) {
				ItemStack itemstack = slots[i];
				slots[i] = null;
				return itemstack;
			}
			
			ItemStack itemstack1 = slots[i].splitStack(j);
			
			if (slots[i].stackSize == 0) {
				slots[i] = null;
			}
			
			return itemstack1;
		}else{
			return null;
		}
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return "container.crockpot";
	}

	@Override
	public int getInventoryStackLimit() {
	return 1;
	}

	@Override
	public int getSizeInventory() {
		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return slots[i];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (slots[i] != null) {
			ItemStack itemstack = slots[i];
			slots[i] = null;
			return itemstack;
		}else{
			return null;
		}
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return this.customName != null && this.customName.length() > 0;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack is) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		if (worldObj.getTileEntity(xCoord, yCoord, zCoord) != this) {
			return false;
		}else{
			return player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64;
		}
	}

	@Override
	public void openInventory() {}
	@Override
	public void closeInventory() {}
	
	public void readFromNBT (NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList list = nbt.getTagList("Items", 10);
		slots = new ItemStack[getSizeInventory()];
		
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound nbt1 = (NBTTagCompound)list.getCompoundTagAt(i);
			byte b0 = nbt1.getByte("Slot");
			
			if (b0 >= 0 && b0 < slots.length) {
				slots[b0] = ItemStack.loadItemStackFromNBT(nbt1);
			}
		}
		Button = nbt.getBoolean("Button");
		cookTime = nbt.getShort("cookTime");
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("cookTime", (short)cookTime);
		NBTTagList list = new NBTTagList();
		
		for (int i = 0; i < slots.length; i++) {
			if (slots[i] != null) {
				NBTTagCompound nbt1 = new NBTTagCompound();
				nbt1.setByte("Slot", (byte)i);
				slots[i].writeToNBT(nbt1);
				list.appendTag(nbt1);
			}
		}
		
		nbt.setTag("Items", list);
		nbt.setBoolean("Button", Button);
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		slots[i] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
		
	}
	
	private boolean canCook() {
		if(slots[0] == null || slots[1] ==null||slots[2] == null || slots[3] ==null){
			return false;
		}
ItemStack itemstack = CrockPotRecipes.getCookingResult(slots[0].getItem(), slots[1].getItem(),slots[2].getItem(),slots[3].getItem());
		
		if (itemstack == null) {
			return false;
		}
		
		if (slots[4] == null) {
			return true;
		}
		
		if (!slots[4].isItemEqual(itemstack)) {
			return false;
		}
		
		if (slots[4].stackSize < getInventoryStackLimit() && slots[4].stackSize < slots[4].getMaxStackSize()) {
			return true;
		}else{
			return slots[4].stackSize < itemstack.getMaxStackSize();
		}
		
	}
	
	private void cookItem() {
		if (canCook()) {
			ItemStack itemstack = CrockPotRecipes.getCookingResult(slots[0].getItem(), slots[1].getItem(),slots[2].getItem(),slots[3].getItem());
			
			if (slots[4] == null) {
				slots[4] = itemstack.copy();
			}else if (slots[4].isItemEqual(itemstack)) {
				slots[4].stackSize += itemstack.stackSize;
			}
			
			for (int i = 0; i < 4; i++) {
				if (slots[i].stackSize <= 0) {
					slots[i] = new ItemStack(slots[i].getItem().setFull3D());
				}else{
					slots[i].stackSize--;
				}
				
				if (slots[i].stackSize <= 0){
					slots[i] = null;
				}
			}
		}
		
	}
	
	public boolean isCooking() {
		return cookTime>0;
	}
	public boolean ButtonV(){
		if (slots[0] != null && slots[1] !=null&&slots[2] != null && slots[3] !=null&&!isCooking()){
			
		}
		return slots[0] != null && slots[1] !=null&&slots[2] != null && slots[3] !=null&&!isCooking();
	}
	public boolean hasPower(){
		return true;
	}
	
	public void setButton(boolean b){
		this.Button=b;
	}
	
	public boolean getButton(){
		return this.Button;
	}
	
	
	public void invertButton(){
		this.Button=!Button;
	}
	
	public void updateEntity() {
		boolean flag = this.hasPower();
		boolean flag1= false;
		
		
		if(!worldObj.isRemote) {
		
			if (canCook()&&Button) {
				cookTime++;
				
				if (this.cookTime == this.cookingSpeed) {
					this.cookTime = 0;
					this.cookItem();
					Button=false;
					flag1 = true;
				}
			}else{
				cookTime = 0;
			}
			
			if (this.isCooking()) {
				flag1 = true;
				CrockPot.updateBlockState(this.isCooking(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
		
		if (flag1) {
			this.markDirty();
		}
    }
	@Override
	public boolean canExtractItem(int arg0, ItemStack arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean canInsertItem(int arg0, ItemStack arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int[] getAccessibleSlotsFromSide(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
		
	}


