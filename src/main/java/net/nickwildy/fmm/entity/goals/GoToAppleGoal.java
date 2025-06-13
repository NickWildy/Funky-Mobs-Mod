package net.nickwildy.fmm.entity.goals;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.Items;
import net.nickwildy.fmm.entity.custom.BaldiEntity;

import java.util.EnumSet;
import java.util.List;

public class GoToAppleGoal extends Goal {
    private final BaldiEntity entity;
    private ItemEntity targetApple;
    public double speedDouble;

    public GoToAppleGoal(BaldiEntity entity, double speedDouble) {
        this.entity = entity;
        this.speedDouble = speedDouble;
        this.setControls(EnumSet.of(Goal.Control.MOVE));
    }

    @Override
    public boolean canStart() {
        if (entity.hasApple) return false;

        List<ItemEntity> apples = entity.getWorld().getEntitiesByClass(
                ItemEntity.class,
                entity.getBoundingBox().expand(10.0),
                item -> item.getStack().isOf(Items.APPLE) && item.isAlive()
        );

        if (!apples.isEmpty()) {
            targetApple = apples.get(0);
            return true;
        }

        return false;
    }

    @Override
    public boolean shouldContinue() {
        return !entity.hasApple && targetApple != null && targetApple.isAlive() &&
                entity.squaredDistanceTo(targetApple) > 2.0;
    }

    @Override
    public void start() {
        entity.getNavigation().startMovingTo(targetApple, speedDouble);
    }

    @Override
    public void tick() {
        if (targetApple != null) {
            entity.getNavigation().startMovingTo(targetApple, speedDouble);
        }
    }

    @Override
    public void stop() {
        targetApple = null;
    }

    public void resetAppleCollection() {
        entity.hasApple = false;
    }
}
