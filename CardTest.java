import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
* Tests the functionality of the Card class.
* This test suite makes sure all public methods in the Card Class
 * return the proper data.
*
*/
class CardTest {

    /**
    * Verifies that the getColor method correctly returns the card's color.
    */
    @Test
    public void getColor() {
        Card wild = new Card(0, 5, 3);
        Card blue = new Card(1, 0, 7);
        Card yellow = new Card(3, 2, 3);
        assertEquals(Card.colortype.ALL, wild.getColor());
        assertEquals(Card.colortype.BLUE, blue.getColor());
        assertEquals(Card.colortype.YELLOW, yellow.getColor());
    }

    /**
    * Verifies that the getType method correctly returns the card's type.
    */
    @Test
    public void getType() {
        Card wild = new Card(0, 5, 3);
        Card number = new Card(1, 0, 7);
        Card drawone = new Card(3, 2, 3);
        assertEquals(Card.cardtype.WILD, wild.getType());
        assertEquals(Card.cardtype.NUMBER, number.getType());
        assertEquals(Card.cardtype.DRAW_ONE, drawone.getType());
    }

    /**
    * Verifies that the getRank method correctly returns the card's rank.
    */
    @Test
    public void getRank() {
        Card wild = new Card(0, 5, 3);
        Card seven = new Card(1, 0, 7);
        Card two = new Card(3, 0, 2);
        assertEquals(0, wild.getRank());
        assertEquals(7, seven.getRank());
        assertEquals(2, two.getRank());
    }

    /**
    * Verifies that the getDescription method correctly returns a description of the card.
    */
    @Test
    public void getDescription() {
        Card wild = new Card(0, 5, 3);
        Card drawone = new Card(1, 2, 7);
        Card two = new Card(3, 0, 2);
        assertEquals("WILD", wild.getDescription());
        assertEquals("BLUE DRAW_ONE", drawone.getDescription());
        assertEquals("YELLOW 2", two.getDescription());
    }

}


