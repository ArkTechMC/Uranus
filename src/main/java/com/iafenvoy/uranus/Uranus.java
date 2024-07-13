package com.iafenvoy.uranus;

import com.iafenvoy.uranus.util.Timeout;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Uranus implements ModInitializer {
    public static final String MOD_ID = "uranus";
    public static final Logger LOGGER = LogManager.getLogger("uranus");
    public static final GameRules.Key<GameRules.BooleanRule> showModLightening = GameRuleRegistry.register("showModLightening", GameRules.Category.UPDATES, GameRuleFactory.createBooleanRule(true));

    @Override
    public void onInitialize() {
        Timeout.startTimeout();
    }
}
