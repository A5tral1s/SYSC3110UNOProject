import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void shuffle() throws CloneNotSupportedException {
        Deck deckOne = new Deck();
        Deck deckTwo = new Deck();
        assertNotEquals(deckOne.drawCard(), deckTwo.drawCard());
    }

    @Test
    void drawCard() {
        Deck deck = new Deck();
        Card first = deck.drawCard();
        Card second = deck.drawCard();
        assertNotEquals(null, first);
        if(first.getType() == second.getType() & first.getRank() == second.getRank()) {
            assertEquals(first.getDescription(), second.getDescription());
        } else{
            assertNotEquals(first.getDescription(), second.getDescription());
        }
    }

    @Test
    void topCard() {
        Deck deck = new Deck();
        assertNull(deck.topCard());
        Card drawn = deck.drawCard();
        deck.discard(drawn);
        assertEquals(drawn.getDescription(), deck.topCard().getDescription());
    }
}