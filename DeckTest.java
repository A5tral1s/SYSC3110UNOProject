import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests the functionality of the Deck class.
 * This test suite makes sure all methods in the Deck Class
 * perform the correct function.
 *
 * @author Eshal Kashif
 * @version 1
 */
class DeckTest {

    /**
     * Test shuffle(). The likelihood of 2 shuffled decks having the same first 10
     * cards is extremely low (although not impossible), this method checks that.
     */
    @Test
    void shuffle() {
        // Compare VALUE (descriptions) from two freshly shuffled decks
        Deck d1 = new Deck();
        Deck d2 = new Deck();

        List<String> first10_d1 = new ArrayList<>();
        List<String> first10_d2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            first10_d1.add(d1.drawCard().getDescription());
            first10_d2.add(d2.drawCard().getDescription());
        }

        // Not guaranteed, but overwhelmingly likely to differ
        assertNotEquals(first10_d1, first10_d2,
                "Two independently shuffled decks should almost surely yield different first-10 sequences.");
    }

    /**
     * Ensure drawCard() draws a non-null card
     */
    @Test
    void drawCard() {
        Deck deck = new Deck();
        Card c1 = deck.drawCard();
        Card c2 = deck.drawCard();
        assertNotNull(c1, "First draw should return a card.");
        assertNotNull(c2, "Second draw should return a card.");
    }

    /**
     * Ensure deck starts with no discards and creates discard after card is drawn
     */
    @Test
    void discardAndTopCard() {
        Deck deck = new Deck();
        assertNull(deck.topCard(), "No discards yet, topCard() should be null.");

        Card drawn = deck.drawCard();
        deck.discard(drawn);
        assertSame(drawn, deck.topCard(),
                "topCard() should be the exact object most recently discarded.");
    }

    /**
     * LIFO behavior of discards: last discarded should be the top.
     */
    @Test
    void discardIsLifo() {
        Deck deck = new Deck();
        Card a = deck.drawCard();
        Card b = deck.drawCard();

        deck.discard(a);
        assertSame(a, deck.topCard(), "First discard should be on top");

        deck.discard(b);
        assertSame(b, deck.topCard(), "Most recent discard should now be on top");
    }
}
