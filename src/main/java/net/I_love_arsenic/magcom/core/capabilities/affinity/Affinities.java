package net.I_love_arsenic.magcom.core.capabilities.affinity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class Affinities {

    @CapabilityInject(IAffinityCapability.class)
    public static Capability<IAffinityCapability> ENTITY_AFFINITY_TYPE = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IAffinityCapability.class, new Storage(), DefaultAffinityCapability::new);
    }

    public static class Storage implements Capability.IStorage<IAffinityCapability> {

        @Nullable
        @Override
        public INBT writeNBT(Capability<IAffinityCapability> capability, IAffinityCapability instance, Direction side) {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt("type", instance.getAffinity());
            return nbt;
        }

        @Override
        public void readNBT(Capability<IAffinityCapability> capability, IAffinityCapability instance, Direction side, INBT nbt) {
            int type = ((CompoundNBT) nbt).getInt("type");
            instance.setAffinity(type);
        }
    }
}
