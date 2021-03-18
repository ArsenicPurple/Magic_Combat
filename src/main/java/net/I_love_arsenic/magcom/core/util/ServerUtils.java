package net.I_love_arsenic.magcom.core.util;

import net.I_love_arsenic.magcom.MagCom;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.Timer;

@OnlyIn(Dist.DEDICATED_SERVER)
public class ServerUtils {

    public static Timer timer = new Timer();

    public static void spawnParticles(ServerWorld world, BasicParticleType particle, double x, double y, double z, int count) {
        world.spawnParticle(particle, x, y, z, count,0,0,0, 0.1);
    }

    public static void spawnParticles(ServerWorld world, BasicParticleType particle, double x, double y, double z, int count, double speed) {
        world.spawnParticle(particle, x, y, z, count,0,0,0, speed);
    }

    public static void sendToNearby(World world, BlockPos pos, Object toSend) {
        if (world instanceof ServerWorld) {
            ServerWorld ws = (ServerWorld) world;

            ws.getChunkProvider().chunkManager.getTrackingPlayers(new ChunkPos(pos), false)
                    .filter(p -> p.getDistanceSq(pos.getX(), pos.getY(), pos.getZ()) < 64 * 64)
                    .forEach(p -> MagCom.CHANNEL_INSTANCE.send(PacketDistributor.PLAYER.with(() -> p), toSend));
        }
    }

    public static void sendToNearby(World world, Entity e, Object toSend) {
        sendToNearby(world, e.getPosition(), toSend);
    }

    public static void sendTo(ServerPlayerEntity playerMP, Object toSend) {
        MagCom.CHANNEL_INSTANCE.sendTo(toSend, playerMP.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendNonLocal(ServerPlayerEntity playerMP, Object toSend) {
        if (playerMP.server.isDedicatedServer() || !playerMP.getGameProfile().getName().equals(playerMP.server.getServerOwner())) {
            sendTo(playerMP, toSend);
        }
    }
}
