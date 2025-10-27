import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void getColor() {
        //Testing that Card properly returns the color of wild, and a color card.
        Card wild = new Card(0, 5, 3);
        Card blue = new Card(1, 0, 7);
        Card yellow = new Card(3, 2, 3);
        assertEquals(Card.colortype.ALL, wild.getColor());
        assertEquals(Card.colortype.BLUE, blue.getColor());
        assertEquals(Card.colortype.YELLOW, yellow.getColor());
    }

    @Test
    void getType() {
        //Testing that Card properly returns the type of a wild, number, and draw one card.
        Card wild = new Card(0, 5, 3);
        Card number = new Card(1, 0, 7);
        Card drawone = new Card(3, 2, 3);
        assertEquals(Card.cardtype.WILD, wild.getType());
        assertEquals(Card.cardtype.NUMBER, number.getType());
        assertEquals(Card.cardtype.DRAW_ONE, drawone.getType());
    }

    @Test
    void getRank() {
        //Testing that Card properly returns the rank of a wild card, and two number cards.
        Card wild = new Card(0, 5, 3);
        Card seven = new Card(1, 0, 7);
        Card two = new Card(3, 0, 2);
        assertEquals(0, wild.getRank());
        assertEquals(7, seven.getRank());
        assertEquals(2, two.getRank());
    }

    @Test
    void getDescription() {
        //Testing that the description of each card matches what is expected.
        Card wild = new Card(0, 5, 3);
        Card drawone = new Card(1, 2, 7);
        Card two = new Card(3, 0, 2);
        assertEquals("WILD", wild.getDescription());
        assertEquals("BLUE DRAW_ONE", drawone.getDescription());
        assertEquals("YELLOW 2", two.getDescription());
    }

}

