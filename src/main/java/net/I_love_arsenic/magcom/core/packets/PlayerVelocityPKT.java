package net.I_love_arsenic.magcom.core.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerVelocityPKT {
    private final double x;
    private final double y;
    private final double z;

    public PlayerVelocityPKT(PacketBuffer buf) {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
    }

    public PlayerVelocityPKT(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public PlayerVelocityPKT(Vector3d vec) {
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
    }

    public void encode(PacketBuffer buf) {
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.setVelocity(x, y, z);
        context.get().setPacketHandled(true);
    }
}
