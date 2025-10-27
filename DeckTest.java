import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class DeckTest {

    @Test
    void shuffle() throws CloneNotSupportedException {
        //Test to make sure that after a shuffle occurs (which happens upon making the deck)
        Deck deckOne = new Deck();
        ArrayList<Card> firstcards = new ArrayList<>();
        ArrayList<Card> secondcards = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            firstcards.add(deckOne.drawCard());
        }

        Deck deckTwo = new Deck();
        for(int j = 0; j < 10; j++){
            secondcards.add(deckTwo.drawCard());
        }
        assertNotEquals(firstcards, secondcards);
    }

    @Test
    void drawCard() {
        //Test to make sure drawing cards works properly.
        Deck deck = new Deck();
        Card first = deck.drawCard();
        Card second = deck.drawCard();
        assertNotEquals(null, first);
        if(first.getType() == second.getType() & first.getRank() == second.getRank() & first.getColor() == second.getColor()) {
            assertEquals(first.getDescription(), second.getDescription());
        } else{
            assertNotEquals(first.getDescription(), second.getDescription());
        }
    }

    @Test
    void topCard() {
        //Test to make sure when discarding a card, it gets put in the discard pile properly.
        Deck deck = new Deck();
        assertNull(deck.topCard());
        Card drawn = deck.drawCard();
        deck.discard(drawn);
        assertEquals(drawn.getDescription(), deck.topCard().getDescription());
    }

}
