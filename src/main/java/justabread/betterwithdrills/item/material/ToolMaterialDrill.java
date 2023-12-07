package justabread.betterwithdrills.item.material;

import net.minecraft.core.item.material.ToolMaterial;

public class ToolMaterialDrill extends ToolMaterial {
	private static final int efficiencyMult = 5;

	public static final ToolMaterialDrill iron = (new ToolMaterialDrill(
		ToolMaterial.iron.getDurability() * efficiencyMult,
		ToolMaterial.iron.getEfficiency(false),
		ToolMaterial.iron.getEfficiency(true),
		ToolMaterial.iron.getMiningLevel()
	));
	public static final ToolMaterialDrill steel = (new ToolMaterialDrill(
		ToolMaterial.steel.getDurability() * efficiencyMult,
		ToolMaterial.steel.getEfficiency(false),
		ToolMaterial.steel.getEfficiency(true),
		ToolMaterial.steel.getMiningLevel()
	));

	public static final ToolMaterialDrill diamond = (new ToolMaterialDrill(
		ToolMaterial.diamond.getDurability() * efficiencyMult,
		ToolMaterial.diamond.getEfficiency(false),
		ToolMaterial.diamond.getEfficiency(true),
		ToolMaterial.diamond.getMiningLevel(),
		ToolMaterial.diamond.getDamage(),
		ToolMaterial.diamond.getBlockHitDelay()
	));
	public static final ToolMaterialDrill gold = (new ToolMaterialDrill(
		ToolMaterial.gold.getDurability() * efficiencyMult,
		ToolMaterial.gold.getEfficiency(false),
		ToolMaterial.gold.getEfficiency(true),
		ToolMaterial.gold.getMiningLevel(),
		ToolMaterial.gold.isSilkTouch()
	));
	public ToolMaterialDrill(int durability, float efficiency, float hasteEfficiency, int miningLevel) {
		setDurability(durability);
		setEfficiency(efficiency, hasteEfficiency);
		setMiningLevel(miningLevel);
	}

	public ToolMaterialDrill(int durability, float efficiency, float hasteEfficiency, int miningLevel, boolean silkTouch) {
		setDurability(durability);
		setEfficiency(efficiency, hasteEfficiency);
		setMiningLevel(miningLevel);
		setSilkTouch(silkTouch);
	}

	public ToolMaterialDrill(int durability, float efficiency, float hasteEfficiency, int miningLevel, int damage, int blockHitDelay) {
		setDurability(durability);
		setEfficiency(efficiency, hasteEfficiency);
		setMiningLevel(miningLevel);
		setDamage(damage);
		setBlockHitDelay(blockHitDelay);
	}
}
