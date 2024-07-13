package com.iafenvoy.uranus.server.entity;

import com.iafenvoy.uranus.server.tick.modifier.TickRateModifier;

public interface IModifiesTime {
    boolean isTimeModificationValid(TickRateModifier modifier);
}
