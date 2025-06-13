package net.nickwildy.fmm.entity.goals;

import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.nickwildy.fmm.entity.custom.BaldiEntity;

public class AppleAttackGoal extends MeleeAttackGoal {
    private final BaldiEntity mob;

    public AppleAttackGoal(BaldiEntity mob, double speed, boolean pauseWhenIdle) {
        super(mob, speed, pauseWhenIdle);
        this.mob = mob;
    }

    @Override
    public boolean canStart() {
        if (mob instanceof BaldiEntity baldi && baldi.hasCollectedApple()) {
            return false;
        }
        return super.canStart();
    }

    @Override
    public boolean shouldContinue() {
        if (mob instanceof BaldiEntity baldi && baldi.hasCollectedApple()) {
            return false;
        }
        return super.shouldContinue();
    }
}