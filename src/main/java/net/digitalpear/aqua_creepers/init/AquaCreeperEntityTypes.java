package net.digitalpear.aqua_creepers.init;

import net.digitalpear.aqua_creepers.AquaCreepers;
import net.digitalpear.aqua_creepers.common.entities.AquaCreeperEntity;
import net.digitalpear.aqua_creepers.common.entities.UnderwaterTntEntity;
import net.digitalpear.aqua_creepers.common.entities.UnderwaterTntMinecartEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AquaCreeperEntityTypes {
    public static final EntityType<AquaCreeperEntity> AQUA_CREEPER = register(
            "aqua_creeper",
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, AquaCreeperEntity::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 0.7f))
    );

    public static final EntityType<UnderwaterTntEntity> UNDERWATER_TNT = register(
            "underwater_tnt",
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, UnderwaterTntEntity::new)
                    .fireImmune()
                    .dimensions(EntityDimensions.fixed(0.98F, 0.98F))
                    .trackRangeChunks(10)
                    .trackedUpdateRate(10)
    );

    public static final EntityType<UnderwaterTntMinecartEntity> UNDERWATER_TNT_MINECART = register(
            "underwater_tnt_minecart",
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, UnderwaterTntMinecartEntity::new)
            .dimensions(EntityDimensions.fixed(0.98F, 0.7F))
            .trackRangeChunks(8));


    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, AquaCreepers.id(id), type.build());
    }

    public static void init(){
        FabricDefaultAttributeRegistry.register(AQUA_CREEPER, AquaCreeperEntity.createAquaCreeperAttributes());
    }
}
