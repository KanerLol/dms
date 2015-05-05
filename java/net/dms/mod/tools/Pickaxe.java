package net.dms.mod.tools;

import net.dms.mod.dms;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;

public class Pickaxe extends ItemPickaxe{

	public Pickaxe(ToolMaterial material) {
		super(material);
		this.setCreativeTab(dms.tabDMS);
	}



@Override
public void registerIcons(IIconRegister par1IconRegister)
{
    this.itemIcon = par1IconRegister.registerIcon(dms.modID +":"+ getUnlocalizedName().substring(5));
}
}