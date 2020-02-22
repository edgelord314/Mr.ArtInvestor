package de.edgelord.mai.art;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.effect.image.SaltyBufferedImage;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.gameobject.EmptyGameObject;

public class Canvas extends EmptyGameObject {

    public static final String TAG = "canvas";

    private ArtPiece artPiece;
    private SaltyImage image = new SaltyBufferedImage(ArtPiece.WIDTH, ArtPiece.HEIGHT);

    public Canvas(float xPos, float yPos, ArtPiece artPiece) {
        super(xPos, yPos, ArtPiece.WIDTH, ArtPiece.HEIGHT, TAG);

        setX(Game.getHost().getHorizontalCentrePosition(this));
        setY(150);

        this.artPiece = artPiece;
        image.drawTo(artPiece);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.drawImage(image, this);
    }
}
