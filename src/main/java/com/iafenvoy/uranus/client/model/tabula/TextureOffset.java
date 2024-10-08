package com.iafenvoy.uranus.client.model.tabula;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class TextureOffset {
    public final int textureOffsetX;
    public final int textureOffsetY;

    public TextureOffset(int textureOffsetXIn, int textureOffsetYIn) {
        this.textureOffsetX = textureOffsetXIn;
        this.textureOffsetY = textureOffsetYIn;
    }
}