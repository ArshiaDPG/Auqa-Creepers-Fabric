package net.digitalpear.aqua_creepers.common.entities.entity_goals;

import net.minecraft.entity.ai.goal.SwimAroundGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;
import org.joml.Vector3f;

public class WaterGoals extends SwimAroundGoal {
    public WaterGoals(FishEntity fish) {
        super(fish, fish.getAttributeBaseValue(EntityAttributes.GENERIC_MOVEMENT_SPEED), 40);
    }
}
