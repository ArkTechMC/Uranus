package com.iafenvoy.uranus.client.model.tabula;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gegy1000
 * @since 1.0.0
 */
public class TabulaCubeContainer {
    private final String name;
    private final String identifier;
    private final String parentIdentifier;
    private final boolean txMirror;
    private final boolean hidden;
    private final List<TabulaCubeContainer> children = new ArrayList<>();
    private final int[] dimensions;
    private final double[] position;
    private final double[] offset;
    private final double[] rotation;
    private final double[] scale;
    private final int[] txOffset;
    private final double mcScale;
    private final double opacity;

    public TabulaCubeContainer(String name, String identifier, String parentIdentifier, int[] dimensions, double[] position, double[] offset, double[] rotation, double[] scale, int[] textureOffset, boolean textureMirror, double opacity, double mcScale, boolean hidden) {
        this.name = name;
        this.identifier = identifier;
        this.parentIdentifier = parentIdentifier;
        this.dimensions = dimensions;
        this.position = position;
        this.offset = offset;
        this.rotation = rotation;
        this.scale = scale;
        this.txOffset = textureOffset;
        this.txMirror = textureMirror;
        this.opacity = opacity;
        this.mcScale = mcScale;
        this.hidden = hidden;
    }

    public String getName() {
        return this.name;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getParentIdentifier() {
        return this.parentIdentifier;
    }

    public int[] getDimensions() {
        return this.dimensions;
    }

    public double[] getPosition() {
        return this.position;
    }

    public double[] getOffset() {
        return this.offset;
    }

    public double[] getRotation() {
        return this.rotation;
    }

    public double[] getScale() {
        return this.scale;
    }

    public int[] getTextureOffset() {
        return this.txOffset;
    }

    public boolean isTextureMirrorEnabled() {
        return this.txMirror;
    }

    public double getMCScale() {
        return this.mcScale;
    }

    public double getOpacity() {
        return this.opacity;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public List<TabulaCubeContainer> getChildren() {
        return this.children;
    }
}