package net.I_love_arsenic.magcom.common.items.wands.utils;

import net.minecraft.util.math.vector.Vector3d;
import org.lwjgl.system.CallbackI;

public class BlockUtils {
    public static final Vector3d[] WaterUtilityL4 = {

            //East Wall
            new Vector3d(3, 0, 0),
            new Vector3d(3, 0, 1),
            new Vector3d(3, 0, -1),
            new Vector3d(3, 1, 0),
            new Vector3d(3, 1, 1),
            new Vector3d(3, 1, -1),
            new Vector3d(3, 2, 0),
            new Vector3d(3, 2, 1),
            new Vector3d(3, 2, -1),
            new Vector3d(3, 3, 1),
            new Vector3d(3, 3, -1),

            //West Wall
            new Vector3d(-3, 0, 0),
            new Vector3d(-3, 0, 1),
            new Vector3d(-3, 0, -1),
            new Vector3d(-3, 1, 0),
            new Vector3d(-3, 1, 1),
            new Vector3d(-3, 1, -1),
            new Vector3d(-3, 2, 0),
            new Vector3d(-3, 2, 1),
            new Vector3d(-3, 2, -1),
            new Vector3d(-3, 3, 1),
            new Vector3d(-3, 3, -1),

            //North Wall
            new Vector3d(0, 0, 3),
            new Vector3d(1, 0, 3),
            new Vector3d(-1, 0, 3),
            new Vector3d(0, 1, 3),
            new Vector3d(1, 1, 3),
            new Vector3d(-1, 1, 3),
            new Vector3d(0, 2, 3),
            new Vector3d(1, 2, 3),
            new Vector3d(-1, 2, 3),
            new Vector3d(1, 3, 3),
            new Vector3d(-1, 3, 3),

            //South Wall
            new Vector3d(0, 0, -3),
            new Vector3d(1, 0, -3),
            new Vector3d(-1, 0, -3),
            new Vector3d(0, 1, -3),
            new Vector3d(1, 1, -3),
            new Vector3d(-1, 1, -3),
            new Vector3d(0, 2, -3),
            new Vector3d(1, 2, -3),
            new Vector3d(-1, 2, -3),
            new Vector3d(1, 3, -3),
            new Vector3d(-1, 3, -3),

            //NE Pillar
            new Vector3d(2, 0, 2),
            new Vector3d(2, 1, 2),
            new Vector3d(2, 2, 2),

            //NW Pillar
            new Vector3d(-2, 0, 2),
            new Vector3d(-2, 1, 2),
            new Vector3d(-2, 2, 2),

            //SE Pillar
            new Vector3d(2, 0, -2),
            new Vector3d(2, 1, -2),
            new Vector3d(2, 2, -2),

            //SW Pillar
            new Vector3d(-2, 0, -2),
            new Vector3d(-2, 1, -2),
            new Vector3d(-2, 2, -2)
    };
}
