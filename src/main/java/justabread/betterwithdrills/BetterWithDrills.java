package justabread.betterwithdrills;

import justabread.betterwithdrills.item.ItemToolDrill;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.material.ToolMaterial;
import turniplabs.halplibe.helper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BetterWithDrills implements ModInitializer {
    public static final String MOD_ID = "betterwithdrills";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Item toolDrillIron = ItemHelper.createItem(MOD_ID, new ItemToolDrill("tool.drill.irondrill", 30000, ToolMaterial.iron),"tool.drill.iron", "PickHolystone.png");

    @Override
    public void onInitialize() {
        LOGGER.info("Better With Drills initialized.");
    }
}