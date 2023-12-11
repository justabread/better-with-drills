package justabread.betterwithdrills.item.util;

import justabread.betterwithdrills.util.Vect3dInt;
import net.minecraft.client.Minecraft;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.tool.ItemTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ToolUtils {
	public static Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
	public static final Logger LOGGER = LoggerFactory.getLogger("betterwithdrills");
	public static boolean TryDestroyBlock(int x, int y, int z, EntityLiving entityLiving, ItemTool tool, Vect3dInt originalBlockCoords) {
		int id = mc.theWorld.getBlockId(x, y, z);
		int meta = mc.theWorld.getBlockMetadata(x, y, z);

		Block block = Block.getBlock(id);
		TileEntity tileEntity = mc.theWorld.getBlockTileEntity(x, y, z);
		ItemStack item = mc.thePlayer.getCurrentEquippedItem();

		if(block != null && tool.canHarvestBlock(block)) {
			boolean removed = mc.theWorld.setBlockWithNotify(x, y, z, 0);
			DamageItem(tool, item, entityLiving, block);
			if (removed && mc.thePlayer.getGamemode().dropBlockOnBreak) {
				Block.blocksList[id].harvestBlock(mc.theWorld, mc.thePlayer, originalBlockCoords.getX(), originalBlockCoords.getY(), originalBlockCoords.getZ(), meta, tileEntity);
			}

			if (item != null && item.stackSize <= 0) {
				mc.thePlayer.destroyCurrentEquippedItem();
			}

			return true;
		}

		return false;
	}

	public static <T> void removeDuplicates(ArrayList<T> list)
	{
        Set<T> set = new LinkedHashSet<>(list);
		list.clear();
		list.addAll(set);

	}

	private static boolean EvaluateRedstone(int blockId, int checkedBlockId) {
		return (blockId >= 390 && blockId <= 393 ) && (checkedBlockId >= 400 && checkedBlockId <= 403)
			|| (checkedBlockId >= 390 && checkedBlockId <= 393 ) && (blockId >= 400 && blockId <= 403) ;
	}

	public static void MineConnectedBlocks(int j, int k, int l, int blockId, EntityLiving entityLiving, ItemTool tool) {
		ArrayList<Vect3dInt> blocksToMine = new ArrayList<>(Collections.singletonList(new Vect3dInt(j, k, l)));

		int iterator = 0;
		boolean finishedStack;

		do {
			for (int x = -1; x <= 1; x++) {
				for (int y = -1; y <= 1; y++) {
					for (int z = -1; z <= 1; z++) {
						if(!(x == 0 && y == 0 && z == 0)){
							Vect3dInt checkedBlockCoords = new Vect3dInt(blocksToMine.get(iterator).getX()  + x, blocksToMine.get(iterator).getY() + y, blocksToMine.get(iterator).getZ() + z);

							int checkedBlockId = mc.theWorld.getBlockId(checkedBlockCoords.getX(), checkedBlockCoords.getY(), checkedBlockCoords.getZ());
							if(checkedBlockId == blockId || EvaluateRedstone(blockId, checkedBlockId)) {
								blocksToMine.add(checkedBlockCoords);
							}
						}
					}
				}
			}
            removeDuplicates(blocksToMine);
            iterator++;
			finishedStack = iterator == blocksToMine.size();
		}while (!finishedStack);

		blocksToMine.remove(0);

		for(Vect3dInt coord : blocksToMine) {
			TryDestroyBlock(coord.getX(), coord.getY(), coord.getZ(), entityLiving, tool, new Vect3dInt(j, k, l));
		}
	}

	public static void MineTunnel(int j, int k, int l, EntityLiving entityLiving, ItemTool tool) {
		for(int x = -1; x <= 1; x++) {
			for(int y = -1; y <= 1; y++ ) {
				if(!(x == 0 && y == 0)) {
					switch(ToolUtils.mc.objectMouseOver.side) {
						case WEST:
						case EAST:
							ToolUtils.TryDestroyBlock(j, k + x, l + y, entityLiving, tool, new Vect3dInt(j, k, l));
							break;

						case SOUTH:
						case NORTH:
							ToolUtils.TryDestroyBlock(j + x, k + y, l, entityLiving, tool, new Vect3dInt(j, k, l));
							break;

						case BOTTOM:
						case TOP:
							ToolUtils.TryDestroyBlock(j + x, k, l + y, entityLiving, tool, new Vect3dInt(j, k, l));
							break;
					}
				}
			}
		}
	}

	public static void DamageItem(ItemTool tool, ItemStack item, EntityLiving entityLiving, Block block) {
		if (item != null && (block.getHardness() > 0.0F || tool.isSilkTouch())) {
			item.damageItem(1, entityLiving);
		}
	}
}
