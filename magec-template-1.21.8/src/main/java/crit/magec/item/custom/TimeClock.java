package crit.magec.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.tick.TickManager;

public class TimeClock extends Item {
    public TimeClock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) return ActionResult.FAIL;
        TickManager tickManager = world.getTickManager();
        if (tickManager.isFrozen()){
            tickManager.setFrozen(false);
        } else {
            tickManager.setFrozen(true);
        }
        return  ActionResult.SUCCESS;
    }
}
