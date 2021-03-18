package net.I_love_arsenic.magcom.core.event;

import net.I_love_arsenic.magcom.MagCom;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = MagCom.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    //@SubscribeEvent
    //public static void onItemToss(final EntityJoinWorldEvent event) { }
}
