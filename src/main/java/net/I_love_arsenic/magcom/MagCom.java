package net.I_love_arsenic.magcom;

import com.mojang.brigadier.CommandDispatcher;
import net.I_love_arsenic.magcom.client.render.entity.BooritRenderer;
import net.I_love_arsenic.magcom.common.entities.BooritEntity;
import net.I_love_arsenic.magcom.core.capabilities.affinity.Affinities;
import net.I_love_arsenic.magcom.core.capabilities.affinity.AffinityEventHandler;
import net.I_love_arsenic.magcom.core.commands.GetAffinityCommand;
import net.I_love_arsenic.magcom.core.init.BlockInit;
import net.I_love_arsenic.magcom.core.init.EntityTypeInit;
import net.I_love_arsenic.magcom.core.init.ItemInit;
import net.I_love_arsenic.magcom.core.packets.PlayerVelocityPKT;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

/*
TODO : Notes are written with no relevance to order
 1. Add multiple textures to wand
 2. Create Boorit Mob Entity
 3. Create more spells
 */

// The value here should match an entry in the META-INF/mods.toml file
@Mod("magic_combat")
public class MagCom
{
    public static final String MOD_ID = "magic_combat";
    public static final ItemGroup MAGIC_COMBAT_GROUP = new MagComGroup("magic_combat.tab");
    public static final Logger LOGGER = LogManager.getLogger();
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL_INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public MagCom() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetup);

        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        EntityTypeInit.ENTITIES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        final CommandDispatcher<CommandSource> dispatcher = new CommandDispatcher<>();
        GetAffinityCommand.register(dispatcher);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        CHANNEL_INSTANCE.registerMessage(0, PlayerVelocityPKT.class, PlayerVelocityPKT::encode, PlayerVelocityPKT::new, PlayerVelocityPKT::handle);

        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(EntityTypeInit.BOORIT_ENTITY.get(), BooritEntity.setCustomAttributes().create());
        });

        Affinities.register();
        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, AffinityEventHandler::onAttachCapabilitiesEvent);
    }

    public void clientSetup(final FMLClientSetupEvent event) {
        registerItemModels(event.getMinecraftSupplier());

        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.BOORIT_ENTITY.get(), BooritRenderer::new);
    }

    @OnlyIn(Dist.CLIENT)
    private void registerItemModels(Supplier<Minecraft> minecraft) {
        ItemRenderer renderer = minecraft.get().getItemRenderer();

        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.MAGIC_PROJECTILE.get(), (renderManager) -> new SpriteRenderer<>(renderManager, renderer));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.MAGIC_DEFENSE_PROJECTILE.get(), (renderManager) -> new SpriteRenderer<>(renderManager, renderer));
    }

    public static class MagComGroup extends ItemGroup {

        public MagComGroup(String label) {
            super(label);
        }

        @Override
        public ItemStack createIcon() {
            return ItemInit.WAND_ITEM.get().getDefaultInstance();
        }


    }
}
