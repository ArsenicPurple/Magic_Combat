package net.I_love_arsenic.magcom.core.capabilities.affinity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AffinityTypeProvider implements ICapabilitySerializable<CompoundNBT> {

    private final DefaultAffinityCapability affinity = new DefaultAffinityCapability();
    private final LazyOptional<IAffinityCapability> affinityOptional = LazyOptional.of(() -> affinity);

    public void invalidate() { affinityOptional.invalidate(); }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return affinityOptional.cast();
    }

    @Override
    public CompoundNBT serializeNBT() {
        if (Affinities.ENTITY_AFFINITY_TYPE == null) {
            return new CompoundNBT();
        } else {
            return (CompoundNBT) Affinities.ENTITY_AFFINITY_TYPE.writeNBT(affinity, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (Affinities.ENTITY_AFFINITY_TYPE != null) {
            Affinities.ENTITY_AFFINITY_TYPE.readNBT(affinity, null, nbt);
        }
    }
}
