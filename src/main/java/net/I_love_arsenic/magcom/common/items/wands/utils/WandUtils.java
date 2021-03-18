package net.I_love_arsenic.magcom.common.items.wands.utils;

import net.I_love_arsenic.magcom.common.items.wands.AirWand;
import net.I_love_arsenic.magcom.common.items.wands.FireWand;
import net.I_love_arsenic.magcom.common.items.wands.WaterWand;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nullable;
import java.util.*;

public class WandUtils implements WandCall {

    private static final WandCall[] store = {
            new FireWand(),
            new FireWand(),
            new FireWand(),
            new FireWand(),
            new FireWand(),
            new FireWand(),
            new FireWand(),
            new FireWand(),
            new FireWand()
    };

    public static WandCall purchase(int index) {
        return store[index];
    }

    public static int getRandom() {
        return new Random().nextInt(9) - 1;
    }

    private static final WandType[] opposingTypes = {
            WandType.EARTH,
            WandType.FIRE,
            WandType.WATER,
            WandType.AIR,
            WandType.DARK,
            WandType.LIGHT,
            null,
            null,
            null
    };

    public static WandType getOpposing(WandType type) {
        return opposingTypes[type.ordinal()];
    }

    public static WandType getOpposing(int type) {
        return opposingTypes[type];
    }

    public static <T extends LivingEntity> T getClosestEntity(List<? extends T> entities, LivingEntity exception, double x, double y, double z) {
        double d0 = -1.0D;
        T t = null;

        for(T t1 : entities) {
            if (!t1.isEntityEqual(exception)) {
                double d1 = t1.getDistanceSq(x, y, z);
                if (d0 == -1.0D || d1 < d0) {
                    d0 = d1;
                    t = t1;
                }
            }
        }
        return t;
    }

    public static BlockRayTraceResult doRayTrace(World world, PlayerEntity player, RayTraceContext.FluidMode fluidMode, double distance) {
        float f = player.rotationPitch;
        float f1 = player.rotationYaw;
        Vector3d vector3d = player.getEyePosition(1.0F);
        float f2 = MathHelper.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = MathHelper.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -MathHelper.cos(-f * ((float)Math.PI / 180F));
        float f5 = MathHelper.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vector3d vector3d1 = vector3d.add((double)f6 * distance, (double)f5 * distance, (double)f7 * distance);
        return world.rayTraceBlocks(new RayTraceContext(vector3d, vector3d1, RayTraceContext.BlockMode.OUTLINE, fluidMode, player));
    }

    public static double closest(double in, double num1, double num2) {
        return Math.min(num1 - in, num2 - in) + in;
    }

    @Override
    public void useSpell(int mode, World worldIn, PlayerEntity playerIn, Hand handIn) {

    }
}

