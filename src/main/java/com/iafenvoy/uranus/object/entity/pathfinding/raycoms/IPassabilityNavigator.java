package com.iafenvoy.uranus.object.entity.pathfinding.raycoms;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public interface IPassabilityNavigator {
    int maxSearchNodes();

    boolean isBlockExplicitlyPassable(BlockState state, BlockPos pos, BlockPos entityPos);

    boolean isBlockExplicitlyNotPassable(BlockState state, BlockPos pos, BlockPos entityPos);
}
