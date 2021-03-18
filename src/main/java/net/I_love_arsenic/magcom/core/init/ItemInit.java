package net.I_love_arsenic.magcom.core.init;

import net.I_love_arsenic.magcom.MagCom;

import net.I_love_arsenic.magcom.common.items.EtcherItem;
import net.I_love_arsenic.magcom.common.items.GuideItem;
import net.I_love_arsenic.magcom.common.items.SummoningItem;
import net.I_love_arsenic.magcom.common.items.WandItem;
import net.I_love_arsenic.magcom.common.items.melees.RainbowSwordItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MagCom.MOD_ID);

    //Items
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item",
            () -> new Item(new Item.Properties()
                    .group(MagCom.MAGIC_COMBAT_GROUP)
            ));
    public static final RegistryObject<WandItem> WAND_ITEM = ITEMS.register("wand_item",
            () -> new WandItem(new Item.Properties()
                    .group(MagCom.MAGIC_COMBAT_GROUP)
            ));
    public static final RegistryObject<Item> BOORIT_HORN = ITEMS.register("boorit_horn",
            () -> new Item(new Item.Properties()
                    .group(MagCom.MAGIC_COMBAT_GROUP)
            ));
    public static final RegistryObject<SummoningItem> RED_CRYSTAL = ITEMS.register("red_crystal",
            () -> new SummoningItem(new Item.Properties()
                    .group(MagCom.MAGIC_COMBAT_GROUP)
            ));
    public static final RegistryObject<EtcherItem> ETCHER = ITEMS.register("etcher",
            () -> new EtcherItem(new Item.Properties()
                    .group(MagCom.MAGIC_COMBAT_GROUP)
            ));
    public static final RegistryObject<GuideItem> GUIDE_BOOK = ITEMS.register("guide_book",
            () -> new GuideItem(new Item.Properties()
                    .group(MagCom.MAGIC_COMBAT_GROUP)
            ));
    public static final RegistryObject<RainbowSwordItem> DEAD_END_RAINBOW = ITEMS.register("dead_end_rainbow",
            () -> new RainbowSwordItem(new Item.Properties()
                    .group(MagCom.MAGIC_COMBAT_GROUP), -2.4f));


    //Block Items
    public static final RegistryObject<BlockItem> ETCHED_OBSIDIAN = ITEMS.register("etched_obsidian",
            () -> new BlockItem(BlockInit.ETCHED_OBSIDIAN.get(),
                    new Item.Properties()
                            .group(MagCom.MAGIC_COMBAT_GROUP)
            ));
    public static final RegistryObject<BlockItem> MAGE_CRUCIBLE = ITEMS.register("mage_crucible",
            () -> new BlockItem(BlockInit.MAGE_CRUCIBLE.get(),
                    new Item.Properties()
                            .group(MagCom.MAGIC_COMBAT_GROUP)
            ));
}
