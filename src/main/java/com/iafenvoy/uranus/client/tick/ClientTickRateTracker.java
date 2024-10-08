package com.iafenvoy.uranus.client.tick;

import com.iafenvoy.uranus.server.tick.TickRateTracker;
import com.iafenvoy.uranus.server.tick.modifier.TickRateModifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ClientTickRateTracker extends TickRateTracker {
    public static final Logger LOGGER = LogManager.getLogger("uranus-client-tick");
    private static final Map<MinecraftClient, ClientTickRateTracker> dataMap = new HashMap<>();
    private static final float MS_PER_TICK = 50f;
    public final MinecraftClient client;

    public ClientTickRateTracker(MinecraftClient client) {
        this.client = client;
    }

    public static ClientTickRateTracker getForClient(MinecraftClient minecraft) {
        if (!dataMap.containsKey(minecraft)) {
            ClientTickRateTracker tracker = new ClientTickRateTracker(minecraft);
            dataMap.put(minecraft, tracker);
            return tracker;
        }
        return dataMap.get(minecraft);
    }

    public void syncFromServer(NbtCompound tag) {
        this.tickRateModifierList.clear();
        this.fromTag(tag);
    }

    public void masterTick() {
        super.masterTick();
        this.client.renderTickCounter.tickTime = this.getClientTickRate();
    }

    public float getClientTickRate() {
        float f = MS_PER_TICK;
        for (TickRateModifier modifier : this.tickRateModifierList) {
            assert MinecraftClient.getInstance().player != null;
            if (modifier.appliesTo(MinecraftClient.getInstance().world, MinecraftClient.getInstance().player.getX(), MinecraftClient.getInstance().player.getY(), MinecraftClient.getInstance().player.getZ()))
                f *= modifier.getTickRateMultiplier();
        }
        return Math.max(1F, f * this.getEntityTickLengthModifier(MinecraftClient.getInstance().player));
    }

    public float modifySoundPitch(SoundInstance soundInstance) {
        float f = 1.0F;
        for (TickRateModifier modifier : this.tickRateModifierList) {
            assert MinecraftClient.getInstance().player != null;
            if (modifier.appliesTo(MinecraftClient.getInstance().world, MinecraftClient.getInstance().player.getX(), MinecraftClient.getInstance().player.getY(), MinecraftClient.getInstance().player.getZ()))
                f /= modifier.getTickRateMultiplier();
        }
        return Math.max(1F, f * this.getEntityTickLengthModifier(MinecraftClient.getInstance().player));
    }

    @Override
    public void tickEntityAtCustomRate(Entity entity) {
        if (entity.getWorld().isClient && entity.getWorld() instanceof ClientWorld clientWorld)
            clientWorld.tickEntity(entity);
    }
}
