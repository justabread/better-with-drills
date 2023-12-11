package justabread.betterwithdrills.item.drill;

import justabread.betterwithdrills.item.util.ToolUtils;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.world.World;

public class ItemToolDrillOreMiner extends ItemToolDrill {

    public ItemToolDrillOreMiner(String name, int id, ToolMaterial enumtoolmaterial) {
        super(name, id, enumtoolmaterial);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving) {
        if(Block.getBlock(i).getKey().contains("ore")) {
			ToolUtils.MineConnectedBlocks(j, k, l, i, entityliving, this);
        }else {
            super.onBlockDestroyed(itemstack, i, j, k, l, entityliving);
        }

        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        return SwitchDrillType(itemstack, world, entityplayer, false);
    }
}
