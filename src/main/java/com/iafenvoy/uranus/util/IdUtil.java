package com.iafenvoy.uranus.util;

import net.minecraft.util.Identifier;

public class IdUtil {
    public static String build(String namespace, String path) {
        return new Identifier(namespace, path).toString();
    }
}
