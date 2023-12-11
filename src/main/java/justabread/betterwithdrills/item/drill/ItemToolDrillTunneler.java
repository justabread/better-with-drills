package justabread.betterwithdrills.item.drill;

import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.world.World;

import justabread.betterwithdrills.item.util.ToolUtils;

public class ItemToolDrillTunneler extends ItemToolDrill {
	public ItemToolDrillTunneler(String name, int id, ToolMaterial enumtoolmaterial) {
		super(name, id, enumtoolmaterial);
	}
	@Override
	public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving) {
		Block minedBlock = Block.getBlock(i);
		//Can mine dirt and gravel for more streamlined experience
		if(canHarvestBlock(minedBlock) || (i == 220 || i == 251)) {
			ToolUtils.MineTunnel(j, k, l, entityliving, this);
		}else {
			return super.onBlockDestroyed(itemstack, i, j, k, l, entityliving);
		}

		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		return SwitchDrillType(itemstack, world, entityplayer, true);
	}
}
