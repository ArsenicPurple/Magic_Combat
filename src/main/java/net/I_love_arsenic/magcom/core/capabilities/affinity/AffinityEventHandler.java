package net.I_love_arsenic.magcom.core.capabilities.affinity;

import net.I_love_arsenic.magcom.MagCom;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class AffinityEventHandler {

    public static void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PlayerEntity) {
            AffinityTypeProvider provider = new AffinityTypeProvider();
            event.addCapability(new ResourceLocation(MagCom.MOD_ID, "affinity"), provider);
            event.addListener(provider::invalidate);
        }
    }
}
