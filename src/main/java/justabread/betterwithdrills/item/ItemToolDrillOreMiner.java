package justabread.betterwithdrills.item;

import justabread.betterwithdrills.util.Vect3dInt;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.world.World;

import java.util.*;

public class ItemToolDrillOreMiner extends ItemToolDrill {

    public ItemToolDrillOreMiner(String name, int id, ToolMaterial enumtoolmaterial) {
        super(name, id, enumtoolmaterial);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving) {
        if(Block.getBlock(i).getKey().contains("ore")) {
            HashSet<Vect3dInt> blocksToMine = new HashSet<>(Collections.singletonList(new Vect3dInt(j, k, l)));
            int iterator = 0;
            boolean finishedStack;

            do {
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        for (int z = -1; z <= 1; z++) {
                            if(!(x == 0 && y == 0 && z == 0)){
                                List<Vect3dInt> blocksToMineList = new ArrayList<>(blocksToMine);
                                Vect3dInt checkedBlockCoords = new Vect3dInt(blocksToMineList.get(iterator).getX()  + x, blocksToMineList.get(iterator).getY() + y, blocksToMineList.get(iterator).getZ() + z);

                                int checkedBlockId = mc.theWorld.getBlockId(checkedBlockCoords.getX(), checkedBlockCoords.getY(), checkedBlockCoords.getZ());
                                if(checkedBlockId == i) {
                                    blocksToMine.add(checkedBlockCoords);
                                }
                            }
                        }
                    }
                }

                iterator++;
                finishedStack = iterator == blocksToMine.size();
            }while (!finishedStack);

            for(Vect3dInt coord : blocksToMine) {
                TryDestroyBlock(coord.getX(), coord.getY(), coord.getZ(), entityliving);
            }
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
