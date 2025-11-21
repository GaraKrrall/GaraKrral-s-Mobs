package mc.garakrral.gmobsreforged.entity;

import java.util.function.Supplier;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import mc.garakrral.gmobsreforged.Main;
import mc.garakrral.gmobsreforged.entity.custom.BearEntity;
import mc.garakrral.gmobsreforged.entity.custom.FlyEntity;
import mc.garakrral.gmobsreforged.entity.custom.GeckoEntity;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Main.MODID);

    public static final Supplier<EntityType<GeckoEntity>> GECKO =
            ENTITY_TYPES.register("gecko", () -> EntityType.Builder.of(GeckoEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.35f).build("gecko"));

    public static final Supplier<EntityType<FlyEntity>> FLY =
            ENTITY_TYPES.register("fly", () -> EntityType.Builder.of(FlyEntity::new, MobCategory.CREATURE)
                    .sized(0.30f, 0.30f).build("fly"));

    public static final Supplier<EntityType<BearEntity>> BEAR =
            ENTITY_TYPES.register("bear", () -> EntityType.Builder.of(BearEntity::new, MobCategory.CREATURE)
                    .sized(2.0f, 1.0f).build("bear"));
}
