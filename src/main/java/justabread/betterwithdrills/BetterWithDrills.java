package justabread.betterwithdrills;

import justabread.betterwithdrills.item.chainsaw.ItemToolChainsaw;
import justabread.betterwithdrills.item.drill.ItemToolDrillTunneler;
import justabread.betterwithdrills.item.drill.ItemToolDrillOreMiner;
import justabread.betterwithdrills.item.material.ToolMaterialDrill;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.material.ToolMaterial;
import turniplabs.halplibe.helper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BetterWithDrills implements ModInitializer {
    public static final String MOD_ID = "betterwithdrills";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	//Drills
    public static final Item toolDrillIron = ItemHelper.createItem(MOD_ID, new ItemToolDrillTunneler("tool.drill.iron", 30000, ToolMaterialDrill.iron),"tool.drill.iron", "DrillIron.png");
    public static final Item toolDrillIronOreMiner = ItemHelper.createItem(MOD_ID, new ItemToolDrillOreMiner("tool.drill.oreminer.iron", 30001, ToolMaterialDrill.iron),"tool.drill.oreminer.iron", "DrillIron.png").setNotInCreativeMenu();
    public static final Item toolDrillSteel = ItemHelper.createItem(MOD_ID, new ItemToolDrillTunneler("tool.drill.steel", 30002, ToolMaterialDrill.steel),"tool.drill.steel", "DrillSteel.png");
    public static final Item toolDrillSteelOreMiner = ItemHelper.createItem(MOD_ID, new ItemToolDrillOreMiner("tool.drill.oreminer.steel", 30003, ToolMaterialDrill.steel),"tool.drill.oreminer.steel", "DrillSteel.png").setNotInCreativeMenu();
    public static final Item toolDrillDiamond = ItemHelper.createItem(MOD_ID, new ItemToolDrillTunneler("tool.drill.diamond", 30004, ToolMaterialDrill.diamond),"tool.drill.diamond", "DrillDiamond.png");
    public static final Item toolDrillDiamondOreMiner = ItemHelper.createItem(MOD_ID, new ItemToolDrillOreMiner("tool.drill.oreminer.diamond", 30005, ToolMaterialDrill.diamond),"tool.drill.oreminer.diamond", "DrillDiamond.png").setNotInCreativeMenu();
    public static final Item toolDrillGold = ItemHelper.createItem(MOD_ID, new ItemToolDrillTunneler("tool.drill.gold", 30006, ToolMaterialDrill.gold),"tool.drill.gold", "DrillGold.png");
    public static final Item toolDrillGoldOreMiner = ItemHelper.createItem(MOD_ID, new ItemToolDrillOreMiner("tool.drill.oreminer.gold", 30007, ToolMaterialDrill.gold),"tool.drill.oreminer.gold", "DrillGold.png").setNotInCreativeMenu();

	//Chainsaw
	public static final Item toolChainsaw = ItemHelper.createItem(MOD_ID, new ItemToolChainsaw("tool.drill.chainsaw", 30008, ToolMaterial.iron), "tool.chainsaw", "Chainsaw.png");

    @Override
    public void onInitialize() {
        LOGGER.info("Better With Drills initialized.");

		//Drills
		RecipeHelper.Crafting.createRecipe(BetterWithDrills.toolDrillIron, 1, new Object[]{" E ", "INP", " CP", 'E', Item.repeater, 'I', Item.ingotIron, 'N', Item.nethercoal, 'P', Block.planksOak, 'C', Block.cobbleStone});
        RecipeHelper.Crafting.createRecipe(BetterWithDrills.toolDrillSteel, 1, new Object[]{" E ", "INP", " CP", 'E', Item.repeater, 'I', Item.ingotSteel, 'N', Item.nethercoal, 'P', Block.planksOak, 'C', Block.cobbleStone});
        RecipeHelper.Crafting.createRecipe(BetterWithDrills.toolDrillDiamond, 1, new Object[]{" E ", "INP", " CP", 'E', Item.repeater, 'I', Item.diamond, 'N', Item.nethercoal, 'P', Block.planksOak, 'C', Block.cobbleStone});
        RecipeHelper.Crafting.createRecipe(BetterWithDrills.toolDrillGold, 1, new Object[]{" E ", "INP", " CP", 'E', Item.repeater, 'I', Item.ingotGold, 'N', Item.nethercoal, 'P', Block.planksOak, 'C', Block.cobbleStone});

		//Chainsaw
		RecipeHelper.Crafting.createRecipe(BetterWithDrills.toolChainsaw, 1, new Object[]{"II ", "INS", " RL", 'I', Item.ingotIron, 'N', Item.nethercoal, 'S', Item.ingotSteel, 'R', Item.repeater, 'L', Item.leather});
    }
}
