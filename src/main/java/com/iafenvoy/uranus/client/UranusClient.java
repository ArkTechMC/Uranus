package com.iafenvoy.uranus.client;

import com.iafenvoy.uranus.StaticVariables;
import com.iafenvoy.uranus.animation.IAnimatedEntity;
import com.iafenvoy.uranus.client.tick.ClientTickRateTracker;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class UranusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(StaticVariables.ANIMATION, (client, handler, buf, responseSender) -> {
            int entityID = buf.readInt();
            int index = buf.readInt();
            if (client.world != null) {
                IAnimatedEntity entity = (IAnimatedEntity) client.world.getEntityById(entityID);
                if (entity != null) {
                    if (index == -1) entity.setAnimation(IAnimatedEntity.NO_ANIMATION);
                    else entity.setAnimation(entity.getAnimations()[index]);
                    entity.setAnimationTick(0);
                }
            }
        });
        ClientPlayNetworking.registerGlobalReceiver(StaticVariables.SYNC_CLIENT_TICK, (client, handler, buf, responseSender) -> ClientTickRateTracker.getForClient(client).syncFromServer(buf.readNbt()));
    }
}
