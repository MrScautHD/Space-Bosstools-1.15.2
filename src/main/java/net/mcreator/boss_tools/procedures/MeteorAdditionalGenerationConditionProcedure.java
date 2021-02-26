package net.mcreator.boss_tools.procedures;

import net.mcreator.boss_tools.BossToolsModVariables;
import net.mcreator.boss_tools.BossToolsModElements;

import java.util.Map;

@BossToolsModElements.ModElement.Tag
public class MeteorAdditionalGenerationConditionProcedure extends BossToolsModElements.ModElement {
	public MeteorAdditionalGenerationConditionProcedure(BossToolsModElements instance) {
		super(instance, 526);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		return ((BossToolsModVariables.ConfigMeteor) == 1);
	}
}
