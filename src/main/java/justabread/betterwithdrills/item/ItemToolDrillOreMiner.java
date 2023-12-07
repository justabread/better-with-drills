package justabread.betterwithdrills.item;

import justabread.betterwithdrills.util.Vect3dInt;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.world.World;

import java.util.Arrays;
import java.util.HashSet;

public class ItemToolDrillOreMiner extends ItemToolDrill {

    public ItemToolDrillOreMiner(String name, int id, ToolMaterial enumtoolmaterial) {
        super(name, id, enumtoolmaterial);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving) {
        Block minedBlock = Block.getBlock(i);
        HashSet<Vect3dInt> blocksToMine = new HashSet<>();

        if(minedBlock.getKey().contains("ore")) {
//            for(Vect3dInt coord : blocksToMine) {
//                for (int x = -1; x <= 1; x++) {
//                    for (int y = -1; y <= 1; y++) {
//                        for (int z = -1; z <= 1; z++) {
//                            if(!(x == 0 && y == 0 && z == 0)){
//                                Vect3dInt checkedBlockCoords = new Vect3dInt(j + x, k + y, l + z);
//                                int checkedBlockId = mc.theWorld.getBlockId(checkedBlockCoords.getX(), checkedBlockCoords.getY(), checkedBlockCoords.getZ());
//                                if(checkedBlockId == i) {
//                                    blocksToMine.add(checkedBlockCoords);
//                                }
//                            }
//                        }
//                    }
//                }
//            }

            LOGGER.info(String.valueOf(blocksToMine.size()));
//            int blockDestroyedCounter = 0;
//
//            for(int x = -1; x <= 1; x++) {
//                for(int y = -1; y <= 1; y++ ) {
//                    if(!(x == 0 && y == 0)) {
//                        switch(mc.objectMouseOver.side) {
//                            case WEST:
//                            case EAST:
//                                TryDestroyBlock(j, k + x, l + y);
//                                break;
//
//                            case SOUTH:
//                            case NORTH:
//                                TryDestroyBlock(j + x, k + y, l);
//                                break;
//
//                            case BOTTOM:
//                            case TOP:
//                                TryDestroyBlock(j + x, k, l + y);
//                                break;
//                        }
//
//                        blockDestroyedCounter++;
//                    }
//                }
//            }
//
//            Block block = Block.getBlock(i);
//            if (block != null && (block.getHardness() > 0.0F || this.isSilkTouch())) {
//                for(int count = 0; count < blockDestroyedCounter; count++) {
//                    itemstack.damageItem(1, entityliving);
//                }
//            }
        }

        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        return SwitchDrillType(itemstack, world, entityplayer, false);
    }
}
