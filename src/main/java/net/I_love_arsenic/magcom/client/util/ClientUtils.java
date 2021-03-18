package net.I_love_arsenic.magcom.client.util;

import net.I_love_arsenic.magcom.MagCom;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientUtils {
    public static void spawnParticles(World world, BasicParticleType particle, Vector3d pos, int count) {
        for(int j = 0; j < count; ++j) {
            world.addParticle(particle, pos.x, pos.y, pos.z, 0, 0, 0);
        }
    }

    public static void spawnParticles(World world, BasicParticleType particle, double x, double y, double z, int count) {
        for(int j = 0; j < count; ++j) {
            world.addParticle(particle, x, y, z, 0, 0, 0);
        }
    }

    public static void spawnParticles(World world, BasicParticleType particle, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int count) {
        for(int j = 0; j < count; ++j) {
            world.addParticle(particle, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }

    public static void sendToServer(Object msg) {
        MagCom.CHANNEL_INSTANCE.sendToServer(msg);
    }
}
