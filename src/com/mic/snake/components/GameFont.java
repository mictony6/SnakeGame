package com.mic.snake.components;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
/**
 * Class for creating and deriving game-specific fonts.
 * @author Michael Anthony Bitoon
 */
public class GameFont {



    private Font smallFont;
    private Font largeFont;
    private Font zeldaFont;

    public GameFont()
    {
        InputStream zelda = getClass().getResourceAsStream("/ZeldaOracles.ttf");
        try {
            assert zelda != null;
            zeldaFont = Font.createFont(Font.PLAIN, zelda);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        largeFont = zeldaFont.deriveFont(Font.BOLD, 48);

        smallFont = zeldaFont.deriveFont(Font.PLAIN, 20);
    }

    /**
     * Returns header font.
     */
    public Font getLargeFont() {

        return largeFont;
    }

    /**
     * Returns sub-header font.
     */
    public Font getSmallFont() {

        return smallFont;
    }

    public Font getZeldaFont() {
        return zeldaFont;
    }
}
