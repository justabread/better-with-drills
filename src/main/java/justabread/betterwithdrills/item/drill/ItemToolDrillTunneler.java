package justabread.betterwithdrills.item.drill;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
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
		if(canHarvestBlock(minedBlock)) {
			ToolUtils.MineTunnel(j, k, l, entityliving, this);
		}else {
			return super.onBlockDestroyed(itemstack, i, j, k, l, entityliving);
		}

		return true;
	}

	//Can mine dirt and gravel for more streamlined experience
	@Override
	public boolean canHarvestBlock(Block block) {
		Integer miningLevel = miningLevels.get(block);
		if(block.getKey().contains("dirt") || block.getKey().contains("gravel") || block.getKey().contains("grass")) {
			return true;
		}else if (miningLevel != null) {
			return this.material.getMiningLevel() >= miningLevel;
		} else {
			return block.hasTag(BlockTags.MINEABLE_BY_PICKAXE);
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		return SwitchDrillType(itemstack, world, entityplayer, true);
	}
}
