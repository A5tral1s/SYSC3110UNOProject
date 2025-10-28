import org.junit.jupiter.api.Test;

import java.util.Scanner;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the functionality of the UnoFlip class.
 *
 * @author Emma Wong
 * @version 1
 */
class UnoFlipTest {

    /**
     * Test that game starts properly with valid input
     */
    @Test
    public void testGameInitialization() {
        String input = "2\nAlice\nBob\n";
        Scanner testScanner = new Scanner(input);
        UnoFlip testGame = new UnoFlip(testScanner);

        assertNotNull(testGame, "Game should be created successfully");
    }

    /**
     * Test parseColour method parses user input correctly
     *
     * @throws Exception
     */
    @Test
    void parseColour_validInputs() throws Exception {
        UnoFlip game = new UnoFlip(new Scanner(""));

        Method parse = UnoFlip.class.getDeclaredMethod("parseColour", String.class);
        parse.setAccessible(true);

        assertEquals(Card.colortype.RED, parse.invoke(game, "red"));
        assertEquals(Card.colortype.GREEN, parse.invoke(game, "  Green  "));
        assertEquals(Card.colortype.YELLOW, parse.invoke(game, "yElLoW"));
    }

    /**
     * Test parseColour method parses invalid user input correctly
     *
     * @throws Exception
     */
    @Test
    void parseColour_invalidInputs() throws Exception {
        UnoFlip game = new UnoFlip(new Scanner(""));

        Method parse = UnoFlip.class.getDeclaredMethod("parseColour", String.class);
        parse.setAccessible(true);

        assertNull(parse.invoke(game, "purple"));
        assertNull(parse.invoke(game, ""));
        assertNull(parse.invoke(game, "ALL"));
        assertNull(parse.invoke(game, "123"));
    }

    /**
     * Tests points method to return the correct amount of points for a given card
     */
    @Test
    void points_returnsExpectedForSpecialCard() throws Exception {
        UnoFlip game = new UnoFlip(new Scanner(""));

        Method points = UnoFlip.class.getDeclaredMethod("points", Card.class);
        points.setAccessible(true);

        // create one card of each type
        Card numberCard = new Card(Card.colortype.RED.ordinal(), Card.cardtype.NUMBER.ordinal(), 5);
        Card skipCard   = new Card(Card.colortype.BLUE.ordinal(), Card.cardtype.SKIP.ordinal(), 0);
        Card reverseCard= new Card(Card.colortype.GREEN.ordinal(), Card.cardtype.REVERSE.ordinal(), 0);
        Card drawOneCard= new Card(Card.colortype.YELLOW.ordinal(), Card.cardtype.DRAW_ONE.ordinal(), 0);
        Card wildCard   = new Card(Card.colortype.ALL.ordinal(), Card.cardtype.WILD.ordinal(), 0);
        Card wildTwoCard= new Card(Card.colortype.ALL.ordinal(), Card.cardtype.WILDTWO.ordinal(), 0);

        // invoke UnoFlip.points() for each and check expected values
        assertEquals(5,  points.invoke(game, numberCard), "NUMBER card should return its rank value (5)");
        assertEquals(20, points.invoke(game, skipCard),   "SKIP card should return 20 points");
        assertEquals(20, points.invoke(game, reverseCard),"REVERSE card should return 20 points");
        assertEquals(10, points.invoke(game, drawOneCard),"DRAW_ONE card should return 10 points");
        assertEquals(40, points.invoke(game, wildCard),   "WILD card should return 40 points");
        assertEquals(50, points.invoke(game, wildTwoCard),"WILDTWO card should return 50 points");
    }

    /**
     * Tests isLegal method for a simple colour matching case
     * Top card: BLUE 3, Played card: BLUE SKIP  is legal
     */
    @Test
    void isLegal_ColourMatch() throws Exception {
        UnoFlip game = new UnoFlip(new Scanner(""));

        Method isLegal = UnoFlip.class.getDeclaredMethod("isLegal", Card.class, Card.colortype.class, Card.class);
        isLegal.setAccessible(true);

        Card top = new Card(Card.colortype.BLUE.ordinal(), Card.cardtype.NUMBER.ordinal(), 3);

        Card toPlay = new Card(Card.colortype.BLUE.ordinal(), Card.cardtype.SKIP.ordinal(), 0);

        boolean ok = (boolean) isLegal.invoke(game, toPlay, null, top);
        assertTrue(ok, "Colour match should be legal");
    }

}