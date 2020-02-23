package de.edgelord.mai.main;

import de.edgelord.mai.art.ArtPiece;
import de.edgelord.mai.art.Canvas;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.GameConfig;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.utils.ColorUtil;

public class Main extends Game {

    public static void main(String[] args) {
        init(GameConfig.config(1920, 1080, "Mr. ArtInvestor", 5));
        start(30);

        setDrawFPS(false);
        getHost().setBackgroundColor(ColorUtil.blend(ColorUtil.ROYAL_BLUE, ColorUtil.MIDNIGHT_BLUE, .75f));

        SceneManager.getCurrentScene().addGameObject(new Canvas(0, 0, new ArtPiece()));
    }
}
