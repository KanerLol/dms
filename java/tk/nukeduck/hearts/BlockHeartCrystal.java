package tk.nukeduck.hearts;

import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tk.nukeduck.hearts.ClientProxy;
import tk.nukeduck.hearts.Hearts;
import tk.nukeduck.hearts.TileEntityHeartCrystal;

public class BlockHeartCrystal extends BlockContainer {

   public BlockHeartCrystal(Material par2Material) {
      super(par2Material);
      this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.9375F, 0.875F);
   }

   public TileEntity createNewTileEntity(World world, int i) {
      return new TileEntityHeartCrystal();
   }

   public Item getItemDropped(int i, Random rand, int i2) {
      return Hearts.heartCrystalItem;
   }

   public void registerBlockIcons(IIconRegister icon) {
      super.blockIcon = icon.registerIcon("hearts:heartIcon");
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int getRenderType() {
      return ClientProxy.renderId;
   }
}
