package cc.hyperium.handlers.handlers.animation;

import cc.hyperium.gui.HyperiumGui;
import cc.hyperium.gui.settings.items.CosmeticSettings;
import cc.hyperium.mixinsimp.renderer.model.IMixinModelBiped;
import cc.hyperium.mixinsimp.renderer.model.IMixinModelPlayer;
import net.minecraft.client.entity.AbstractClientPlayer;

import java.util.Random;

public class FlossDanceHandler extends AbstractPreCopyAnglesAnimationHandler {

    private final Random random = new Random();
    private final float[] randomHeadMovement = new float[3]; // x, y, z values
    private ArmsDirection armsDirection = ArmsDirection.HORIZONTAL;

    public FlossDanceHandler() {
        fillRandomHeadMovementArray();
    }

    @Override
    public float modifyState() {
        float speed = CosmeticSettings.flossDanceSpeed * 2;
        return HyperiumGui.clamp(state + (asc ? speed : -speed), 0.0f, 100.0f);
    }

    @Override
    public void onStartOfState() {
        armsDirection = ArmsDirection.values()[armsDirection.ordinal() + 1 >= ArmsDirection.values().length ? 0 : armsDirection.ordinal() + 1];
        fillRandomHeadMovementArray();
    }

    private void fillRandomHeadMovementArray() {
        randomHeadMovement[0] = (2.0f * random.nextFloat() - 1.0f) * 15.0f;
        randomHeadMovement[1] = (2.0f * random.nextFloat() - 1.0f) * 15.0f;
        randomHeadMovement[2] = (2.0f * random.nextFloat() - 1.0f) * 15.0f;
    }

    @Override
    public void modifyPlayer(AbstractClientPlayer entity, IMixinModelPlayer player, float heldPercent) {
        if (shouldNotModify(entity, player)) {
            return;
        }

        player.getBipedBody().rotateAngleZ = (float) Math.toRadians((right ? 10f : -10f) * heldPercent);
        player.getBipedBodywear().rotateAngleZ = (float) Math.toRadians((right ? 10f : -10f) * heldPercent);

        player.getBipedRightUpperLeg().rotateAngleZ = (float) Math.toRadians((right ? -10f : 10f) * heldPercent);
        player.getBipedRightUpperLegwear().rotateAngleZ = (float) Math.toRadians((right ? -10f : 10f) * heldPercent);
        player.getBipedLeftUpperLeg().rotateAngleZ = (float) Math.toRadians((right ? -10f : 10f) * heldPercent);
        player.getBipedLeftUpperLegwear().rotateAngleZ = (float) Math.toRadians((right ? -10f : 10f) * heldPercent);
        player.getBipedRightUpperLeg().offsetX = (right ? -0.17f : 0.17f) * heldPercent;
        player.getBipedRightUpperLegwear().offsetX = (right ? -0.17f : 0.17f) * heldPercent;
        player.getBipedLeftUpperLeg().offsetX = (right ? -0.17f : 0.17f) * heldPercent;
        player.getBipedLeftUpperLegwear().offsetX = (right ? -0.17f : 0.17f) * heldPercent;

        player.getBipedHead().rotateAngleX = (float) Math.toRadians(randomHeadMovement[0] * heldPercent);
        player.getBipedHeadwear().rotateAngleX = (float) Math.toRadians(randomHeadMovement[0] * heldPercent);
        player.getBipedHead().rotateAngleY = (float) Math.toRadians(randomHeadMovement[1] * heldPercent);
        player.getBipedHeadwear().rotateAngleY = (float) Math.toRadians(randomHeadMovement[1] * heldPercent);
        player.getBipedHead().rotateAngleZ = (float) Math.toRadians(randomHeadMovement[2] * heldPercent);
        player.getBipedHeadwear().rotateAngleZ = (float) Math.toRadians(randomHeadMovement[2] * heldPercent);

        player.getBipedRightUpperArm().rotateAngleZ = (float) Math.toRadians((right ? -50f : 50f) * heldPercent);
        player.getBipedRightUpperArmwear().rotateAngleZ = (float) Math.toRadians((right ? -50f : 50f) * heldPercent);
        player.getBipedRightUpperArm().rotateAngleX = (float) Math.toRadians((armsDirection == ArmsDirection.BACK ? 30.0f : -30.0f) * heldPercent);
        player.getBipedRightUpperArmwear().rotateAngleX = (float) Math.toRadians((armsDirection == ArmsDirection.BACK ? 30.0f : -30.0f) * heldPercent);

        player.getBipedLeftUpperArm().rotateAngleZ = (float) Math.toRadians((right ? -50f : 50f) * heldPercent);
        player.getBipedLeftUpperArmwear().rotateAngleZ = (float) Math.toRadians((right ? -50f : 50f) * heldPercent);
        player.getBipedLeftUpperArm().rotateAngleX = (float) Math.toRadians((armsDirection == ArmsDirection.BACK ? 30.0f : -30.0f) * heldPercent);
        player.getBipedLeftUpperArmwear().rotateAngleX = (float) Math.toRadians((armsDirection == ArmsDirection.BACK ? 30.0f : -30.0f) * heldPercent);
    }

    @Override
    public void modifyPlayer(AbstractClientPlayer entity, IMixinModelBiped player, float heldPercent) {
        if (shouldNotModify(entity, player)) {
            return;
        }

        player.getBipedBody().rotateAngleZ = (float) Math.toRadians((right ? 10f : -10f) * heldPercent);

        player.getBipedRightUpperLeg().rotateAngleZ = (float) Math.toRadians((right ? -10f : 10f) * heldPercent);
        player.getBipedLeftUpperLeg().rotateAngleZ = (float) Math.toRadians((right ? -10f : 10f) * heldPercent);
        player.getBipedRightUpperLeg().offsetX = (right ? -0.17f : 0.17f) * heldPercent;
        player.getBipedLeftUpperLeg().offsetX = (right ? -0.17f : 0.17f) * heldPercent;

        player.getBipedHead().rotateAngleX = (float) Math.toRadians(randomHeadMovement[0] * heldPercent);
        player.getBipedHeadwear().rotateAngleX = (float) Math.toRadians(randomHeadMovement[0] * heldPercent);
        player.getBipedHead().rotateAngleY = (float) Math.toRadians(randomHeadMovement[1] * heldPercent);
        player.getBipedHeadwear().rotateAngleY = (float) Math.toRadians(randomHeadMovement[1] * heldPercent);
        player.getBipedHead().rotateAngleZ = (float) Math.toRadians(randomHeadMovement[2] * heldPercent);
        player.getBipedHeadwear().rotateAngleZ = (float) Math.toRadians(randomHeadMovement[2] * heldPercent);

        player.getBipedRightUpperArm().rotateAngleZ = (float) Math.toRadians((right ? -50f : 50f) * heldPercent);
        player.getBipedRightUpperArm().rotateAngleX = (float) Math.toRadians((armsDirection == ArmsDirection.BACK ? 30.0f : -30.0f) * heldPercent);

        player.getBipedLeftUpperArm().rotateAngleZ = (float) Math.toRadians((right ? -50f : 50f) * heldPercent);
        player.getBipedLeftUpperArm().rotateAngleX = (float) Math.toRadians((armsDirection == ArmsDirection.BACK ? 30.0f : -30.0f) * heldPercent);
    }

    private boolean shouldNotModify(AbstractClientPlayer entity, IMixinModelBiped player) {
        AnimationState animationState = get(entity.getUniqueID());
        int ticks = animationState.getFrames();

        if (ticks <= 2) {
            if (animationState.shouldReset()) {
                resetAnimation(player);
            }

            return true;
        }

        return false;
    }

    enum ArmsDirection {
        HORIZONTAL, BACK, FRONT
    }
}
