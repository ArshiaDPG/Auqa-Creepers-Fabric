package net.digitalpear.aqua_creepers.init;

import net.digitalpear.aqua_creepers.AquaCreepers;
import net.digitalpear.aqua_creepers.common.entities.AquaCreeperEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AquaCreeperEntityTypes {
    public static final EntityType<AquaCreeperEntity> AQUA_CREEPER = Registry.register(
            Registries.ENTITY_TYPE,
            AquaCreepers.id("aqua_creeper"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, AquaCreeperEntity::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 0.7f))
                    .build());

    public static void init(){
        FabricDefaultAttributeRegistry.register(AQUA_CREEPER, AquaCreeperEntity.createAquaCreeperAttributes());
    }
}
