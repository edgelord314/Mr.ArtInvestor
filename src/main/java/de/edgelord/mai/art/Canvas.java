package de.edgelord.mai.art;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.animation.KeyframeAnimation;
import de.edgelord.saltyengine.core.animation.LinearKeyframeAnimation;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.effect.image.SaltyBufferedImage;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.gameobject.EmptyGameObject;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.input.MouseInputHandler;
import de.edgelord.saltyengine.scene.SceneManager;

import java.awt.event.MouseEvent;

public class Canvas extends EmptyGameObject {

    public static final String TAG = "canvas";

    public static float nextSmallViewX = 50;
    private static boolean rowFull = false;

    private boolean mainCanvas = true;

    private ArtPiece artPiece;
    private SaltyImage image = new SaltyBufferedImage(ArtPiece.WIDTH, ArtPiece.HEIGHT);
    private KeyframeAnimation smallViewAnimationX = new LinearKeyframeAnimation();
    private KeyframeAnimation smallViewAnimationY = new LinearKeyframeAnimation();
    private KeyframeAnimation smallViewAnimationWidth = new LinearKeyframeAnimation();
    private KeyframeAnimation smallViewAnimationHeight = new LinearKeyframeAnimation();

    private KeyframeAnimation rowFullXAnimation = new LinearKeyframeAnimation();

    public Canvas(float xPos, float yPos, ArtPiece artPiece) {
        super(xPos, yPos, ArtPiece.WIDTH, ArtPiece.HEIGHT, TAG);

        setX(Game.getHost().getHorizontalCentrePosition(this));
        setY(200);

        this.artPiece = artPiece;
        image.drawTo(artPiece);

        smallViewAnimationY.add(150, 25 - getY());
        smallViewAnimationX.add(150, (nextSmallViewX >= 1800 ? 1625 : nextSmallViewX) - getX());
        smallViewAnimationWidth.add(150, 200 - getWidth());
        smallViewAnimationHeight.add(150, 120 - getHeight());

        if (nextSmallViewX >= 1800) {
            rowFull = true;
        } else if (!rowFull) {
            nextSmallViewX = nextSmallViewX + 225;
        }

        Input.addMouseInputHandler(new MouseInputHandler() {
            @Override
            public void mouseMoved(MouseEvent e) {

            }

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {

                if (!getTransform().isVisible()) {
                    Input.getMouseHandlers().remove(this);
                    removeFromCurrentScene();
                }
                if (mainCanvas) {
                    smallViewAnimationX.calculateAnimation();
                    smallViewAnimationY.calculateAnimation();
                    smallViewAnimationWidth.calculateAnimation();
                    smallViewAnimationHeight.calculateAnimation();
                    SceneManager.getCurrentScene().addGameObject(new Canvas(getX(), getY(), new ArtPiece()));
                    mainCanvas = false;
                } else if (rowFull) {
                    rowFullXAnimation = new LinearKeyframeAnimation();
                    rowFullXAnimation.add(150, -225);
                    rowFullXAnimation.calculateAnimation();
                }
            }

            @Override
            public void mouseExitedScreen(MouseEvent e) {

            }

            @Override
            public void mouseEnteredScreen(MouseEvent e) {

            }

            @Override
            public void mouseWheelMoved(MouseEvent e) {

            }
        });
    }

    @Override
    public void onFixedTick() {
        if (!smallViewAnimationX.animationEnded()) {
            setX(getX() + smallViewAnimationX.nextDelta());
            setY(getY() + smallViewAnimationY.nextDelta());
            setWidth(getWidth() + smallViewAnimationWidth.nextDelta());
            setHeight(getHeight() + smallViewAnimationHeight.nextDelta());
        }

        if (!rowFullXAnimation.animationEnded()) {
            setX(getX() + rowFullXAnimation.nextDelta());
        }
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.drawImage(image, this);
    }
}
