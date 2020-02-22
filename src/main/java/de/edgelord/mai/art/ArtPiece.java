package de.edgelord.mai.art;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.GeneralUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ArtPiece implements Drawable {

    public static final List<Color> PALETTE = new ArrayList<>();
    public static final List<Integer> PIXEL_SIZES = new ArrayList<>();

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 750;

    private int pixelSize;

    public ArtPiece() {
        pixelSize = (int) GeneralUtil.randomObjectFromList(PIXEL_SIZES);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        float x = 0;
        float y = 0;
        float width = pixelSize;
        float height = pixelSize;

        Color lastColor = null;
        Color nextColor = null;

        while (y <= HEIGHT) {

            nextColor = (Color) GeneralUtil.randomObjectFromList(PALETTE);

            for (int i = 0; i < 6 && nextColor != lastColor; i++) {
                nextColor = (Color) GeneralUtil.randomObjectFromList(PALETTE);
            }

            saltyGraphics.setColor(nextColor);
            saltyGraphics.drawRect(x, y, width, height);

            if (x + width >= WIDTH) {
                y += pixelSize;
                x = 0;
            } else {
                x += width;
            }

            lastColor = nextColor;
        }
    }

    static {
        PIXEL_SIZES.add(10);
        PIXEL_SIZES.add(20);
        PIXEL_SIZES.add(50);
        PIXEL_SIZES.add(125);
        PIXEL_SIZES.add(250);
    }

    static {
        PALETTE.add(ColorUtil.MAROON_RED_COLOR);
        PALETTE.add(ColorUtil.HEART_RED);
        PALETTE.add(ColorUtil.DARK_SALMON);

        PALETTE.add(ColorUtil.ORANGE_RED);
        PALETTE.add(ColorUtil.ORANGE);
        PALETTE.add(ColorUtil.DARK_ORANGE);

        PALETTE.add(ColorUtil.FOREST_GREEN);
        PALETTE.add(ColorUtil.MEDIUM_SEA_GREEN);
        PALETTE.add(ColorUtil.OLIVE_DRAB_GREEN);

        PALETTE.add(ColorUtil.MINT_CREAM);
        PALETTE.add(ColorUtil.PEACH_PUFF);
        PALETTE.add(ColorUtil.LAVENDER_BUSH);

        PALETTE.add(ColorUtil.TEAL_BLUE_COLOR);
        PALETTE.add(ColorUtil.NAVY_BLUE_COLOR);
        PALETTE.add(ColorUtil.CORN_FLOWER_BLUE);

        PALETTE.add(ColorUtil.GOLD);
        PALETTE.add(ColorUtil.GOLD_ROD);
        PALETTE.add(ColorUtil.DARK_GOLD_ROD);

        PALETTE.add(ColorUtil.SADDLE_BROWN);
        PALETTE.add(ColorUtil.CHOCOLATE_BROWN);

        PALETTE.add(ColorUtil.BLUE_VIOLET);
        PALETTE.add(ColorUtil.INDIGO);
        PALETTE.add(ColorUtil.DARK_MAGENTA);

        PALETTE.add(ColorUtil.SLATE_GRAY);
        PALETTE.add(ColorUtil.SAD_GRAY);

        PALETTE.add(ColorUtil.YELLOW);
        PALETTE.add(ColorUtil.PLAIN_YELLOW);
    }
}
