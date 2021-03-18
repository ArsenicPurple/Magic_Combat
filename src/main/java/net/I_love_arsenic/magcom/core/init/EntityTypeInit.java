package net.I_love_arsenic.magcom.core.init;

import net.I_love_arsenic.magcom.MagCom;
import net.I_love_arsenic.magcom.common.entities.BooritEntity;
import net.I_love_arsenic.magcom.common.entities.MagicEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypeInit {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MagCom.MOD_ID);

    public static final RegistryObject<EntityType<MagicEntity>> MAGIC_PROJECTILE = ENTITIES.register("magic_projectile",
            () -> EntityType.Builder.<MagicEntity>create(MagicEntity::new, EntityClassification.MISC)
                    .size(0.25F, 0.25F)
                    .build("magic_projectile")
    );

    public static final RegistryObject<EntityType<MagicEntity>> MAGIC_DEFENSE_PROJECTILE = ENTITIES.register("magic_defense_projectile",
            () -> EntityType.Builder.<MagicEntity>create(MagicEntity::new, EntityClassification.MISC)
                    .size(3F, 3F)
                    .build("magic_defense_projectile")
    );

    public static final RegistryObject<EntityType<BooritEntity>> BOORIT_ENTITY = ENTITIES.register("boorit_entity",
            () -> EntityType.Builder.<BooritEntity>create(BooritEntity::new, EntityClassification.MONSTER)
                    .size(1.1f, 2.15f)
                    .build(new ResourceLocation(MagCom.MOD_ID, "boorit_entity").toString())
    );
}
