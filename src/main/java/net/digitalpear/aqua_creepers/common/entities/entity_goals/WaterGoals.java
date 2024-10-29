package net.digitalpear.aqua_creepers.common.entities.entity_goals;

import net.minecraft.entity.ai.goal.SwimAroundGoal;
import net.minecraft.entity.passive.FishEntity;

public class WaterGoals extends SwimAroundGoal {

    //This code is mainly used to make them swim around
    private final FishEntity fish;
    public boolean hasSelfControl() {
        return true;
    }

    public WaterGoals(FishEntity fish) {
        super(fish, 1.0, 40);
        this.fish = fish;
    }

    @Override
    public boolean canStart() {
        return hasSelfControl() && super.canStart();
    }
}
