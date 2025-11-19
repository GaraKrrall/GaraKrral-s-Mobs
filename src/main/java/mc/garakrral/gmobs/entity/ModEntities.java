package mc.garakrral.gmobs.entity;

import java.util.function.Supplier;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import mc.garakrral.gmobs.Main;
import mc.garakrral.gmobs.entity.custom.BearEntity;
import mc.garakrral.gmobs.entity.custom.FlyEntity;
import mc.garakrral.gmobs.entity.custom.GeckoEntity;

import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Main.MODID);

    public static final Supplier<EntityType<GeckoEntity>> GECKO =
            ENTITY_TYPES.register("gecko", () -> EntityType.Builder.of(GeckoEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.35f).build("gecko"));

    public static final Supplier<EntityType<FlyEntity>> FLY =
            ENTITY_TYPES.register("fly", () -> EntityType.Builder.of(FlyEntity::new, MobCategory.CREATURE)
                    .sized(0.30f, 0.30f).build("fly"));

    public static final Supplier<EntityType<BearEntity>> BEAR =
            ENTITY_TYPES.register("bear" , () -> EntityType.Builder.of(BearEntity::new, MobCategory.CREATURE)
                    .sized(2f, 1.5f).build("bear"));
}
