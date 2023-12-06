package justabread.betterwithdrills.item;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tool.ItemToolPickaxe;
import net.minecraft.client.Minecraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemToolDrill extends ItemToolPickaxe {
	public static final Logger LOGGER = LoggerFactory.getLogger("betterwithdrills");
	public ItemToolDrill (String name, int id, ToolMaterial enumtoolmaterial) {
		super(name, id, enumtoolmaterial);
	}
	Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
	public boolean TryDestroyNeighborBlock(int x, int y, int z) {
		int id = mc.theWorld.getBlockId(x, y, z);
		int meta = mc.theWorld.getBlockMetadata(x, y, z);

		TileEntity tileEntity = mc.theWorld.getBlockTileEntity(x, y, z);

		if(Block.getBlock(id) != null && canHarvestBlock(Block.getBlock(id))) {
			boolean removed = mc.theWorld.setBlockWithNotify(x, y, z, 0);
			ItemStack item = mc.thePlayer.getCurrentEquippedItem();

			if (removed && mc.thePlayer.getGamemode().dropBlockOnBreak) {
				Block.blocksList[id].harvestBlock(mc.theWorld, mc.thePlayer, x, y, z, meta, tileEntity);
			}

			if (item != null && item.stackSize <= 0) {
				mc.thePlayer.destroyCurrentEquippedItem();
			}

			return true;
		}

		return false;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving) {
		Block minedBlock = Block.getBlock(i);
		if(canHarvestBlock(minedBlock)) {
			int blockDestroyedCounter = 0;

			for(int x = -1; x <= 1; x++) {
				for(int y = -1; y <= 1; y++ ) {
					if(!(x == 0 && y == 0)) {
						switch(mc.objectMouseOver.side) {
							case WEST:
							case EAST:
								TryDestroyNeighborBlock(j, k + x, l + y);
								break;

							case SOUTH:
							case NORTH:
								TryDestroyNeighborBlock(j + x, k + y, l);
								break;

							case BOTTOM:
							case TOP:
								TryDestroyNeighborBlock(j + x, k, l + y);
								break;
						}

						blockDestroyedCounter++;
					}
				}
			}

			Block block = Block.getBlock(i);
			if (block != null && (block.getHardness() > 0.0F || this.isSilkTouch())) {
				for(int count = 0; count < blockDestroyedCounter; count++) {
					itemstack.damageItem(1, entityliving);
				}
			}
		}

		return true;
	}
}
