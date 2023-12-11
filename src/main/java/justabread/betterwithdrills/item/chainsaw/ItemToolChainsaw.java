package justabread.betterwithdrills.item.chainsaw;

import justabread.betterwithdrills.item.util.ToolUtils;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tool.ItemToolAxe;

public class ItemToolChainsaw extends ItemToolAxe {
	public ItemToolChainsaw(String name, int id, ToolMaterial enumtoolmaterial) {
		super(name, id, enumtoolmaterial);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving) {
		Block choppedBlock = Block.getBlock(i);
		if(canHarvestBlock(choppedBlock) && choppedBlock.getKey().contains("log")) {
			ToolUtils.MineConnectedBlocks(j, k, l, i, entityliving, this);
		} else {
			return super.onBlockDestroyed(itemstack, i, j, k, l, entityliving);
		}

		return true;
	}
}
