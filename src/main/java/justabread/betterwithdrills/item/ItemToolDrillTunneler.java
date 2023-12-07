package justabread.betterwithdrills.item;

import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.world.World;

public class ItemToolDrillTunneler extends ItemToolDrill {
	public ItemToolDrillTunneler(String name, int id, ToolMaterial enumtoolmaterial) {
		super(name, id, enumtoolmaterial);
	}
	@Override
	public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving) {
		Block minedBlock = Block.getBlock(i);
		if(canHarvestBlock(minedBlock)) {
			for(int x = -1; x <= 1; x++) {
				for(int y = -1; y <= 1; y++ ) {
					if(!(x == 0 && y == 0)) {
						switch(mc.objectMouseOver.side) {
							case WEST:
							case EAST:
								TryDestroyBlock(j, k + x, l + y, entityliving);
								break;

							case SOUTH:
							case NORTH:
								TryDestroyBlock(j + x, k + y, l, entityliving);
								break;

							case BOTTOM:
							case TOP:
								TryDestroyBlock(j + x, k, l + y, entityliving);
								break;
						}
					}
				}
			}
		}else {
			super.onBlockDestroyed(itemstack, i, j, k, l, entityliving);
		}

		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		return SwitchDrillType(itemstack, world, entityplayer, true);
	}
}
