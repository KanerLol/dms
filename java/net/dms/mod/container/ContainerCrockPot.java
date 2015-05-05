package net.dms.mod.container;

import net.dms.mod.slot.SlotCrockPot;
import net.dms.mod.tileentity.TileEntityCrockPot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerCrockPot extends Container {

	private TileEntityCrockPot pot;
	private int cookTime;
	private int power;
	private int lastItemBurnTime;
	
	public ContainerCrockPot(InventoryPlayer invPl,TileEntityCrockPot teCp){
		cookTime = 0;
		power = 0;
		lastItemBurnTime=0;
		
		pot = teCp;
		
		this.addSlotToContainer(new Slot(teCp,0,80,5));
		this.addSlotToContainer(new Slot(teCp,1,80,23));
		this.addSlotToContainer(new Slot(teCp,2,80,41));
		this.addSlotToContainer(new Slot(teCp,3,80,59));
		this.addSlotToContainer(new SlotCrockPot(invPl.player,teCp,4,114,32));
		
		//Inventory
				for(int i = 0; i < 3; i++) {
					for(int j = 0; j < 9; j++) {
						this.addSlotToContainer(new Slot(invPl, j + i * 9 + 9, 8 + j * 18, 92 + i * 18));
					}
				}
				
				//ActionBar
				for(int i = 0; i < 9; i++) {
					this.addSlotToContainer(new Slot(invPl, i, 8 + i * 18, 150));
				}
	}
	
	public void addCraftingToCrafters (ICrafting crafting) {
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, this.pot.cookTime);
	}
	
	 @Override
     public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
             ItemStack stack = null;
             Slot slotObject = (Slot) inventorySlots.get(slot);

             //null checks and checks if the item can be stacked (maxStackSize > 1)
             if (slotObject != null && slotObject.getHasStack()) {
                     ItemStack stackInSlot = slotObject.getStack();
                     stack = stackInSlot.copy();

                     //merges the item into player inventory since its in the tileEntity
                     if (slot < pot.getSizeInventory()) {
                             if (!this.mergeItemStack(stackInSlot, pot.getSizeInventory(), 36+pot.getSizeInventory(), true)) {
                                     return null;
                             }
                     }
                     //places it into the tileEntity is possible since its in the player inventory
                     else if (!this.mergeItemStack(stackInSlot, 0, pot.getSizeInventory(), false)) {
                             return null;
                     }

                     if (stackInSlot.stackSize == 0) {
                             slotObject.putStack(null);
                     } else {
                             slotObject.onSlotChanged();
                     }

                     if (stackInSlot.stackSize == stack.stackSize) {
                             return null;
                     }
                     slotObject.onPickupFromSlot(player, stackInSlot);
             }
             return stack;
     }
	
	@Override
	public boolean canInteractWith(EntityPlayer arg0) {
		// TODO Auto-generated method stub
		return pot.isUseableByPlayer(arg0);
	}

}
