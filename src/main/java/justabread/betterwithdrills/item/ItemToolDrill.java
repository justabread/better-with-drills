package justabread.betterwithdrills.item;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tool.ItemToolPickaxe;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.client.Minecraft;

public class ItemToolDrill extends ItemToolPickaxe {
	public ItemToolDrill (String name, int id, ToolMaterial enumtoolmaterial) {
		super(name, id, enumtoolmaterial);
	}
	Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
	public void DestroyNeighborBlock(int x, int y, int z) {
		int id = mc.theWorld.getBlockId(x, y, z);
		int meta = mc.theWorld.getBlockMetadata(x, y, z);
		TileEntity tileEntity = mc.theWorld.getBlockTileEntity(x, y, z);
		boolean removed = mc.theWorld.setBlockWithNotify(x, y, z, 0);
		ItemStack item = mc.thePlayer.getCurrentEquippedItem();

		if (removed && mc.thePlayer.getGamemode().dropBlockOnBreak) {
			Block.blocksList[id].harvestBlock(mc.theWorld, mc.thePlayer, x, y, z, meta, tileEntity);
		}

		if (item != null && item.stackSize <= 0) {
			mc.thePlayer.destroyCurrentEquippedItem();
		}
	}

	@Override
	public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving) {
		int blockDestroyedCounter = 0;
		EntityPlayer player = (EntityPlayer) entityliving;

		for(int x = -1; x <= 1; x++) {
			for(int y = -1; y <= 1; y++ ) {
				if(!(x == 0 && y == 0)) {
					switch(Direction.getDirection(player)) {
						case WEST:
						case EAST:
							DestroyNeighborBlock(j, k + x, l + y);

							break;

						case SOUTH:
						case NORTH:
							DestroyNeighborBlock(j + x, k + y, l);
							break;

						case DOWN:
						case UP:
							DestroyNeighborBlock(j + x, k, l + y);
							break;
					}

					blockDestroyedCounter++;
				}
			}
		}

		Block block = Block.blocksList[i];
		if (block != null && (block.getHardness() > 0.0F || this.isSilkTouch())) {
			for(int count = 0; count < blockDestroyedCounter; count++) {
				itemstack.damageItem(1, entityliving);
			}
		}

		return true;
	}
}
