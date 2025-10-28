import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the functionality of the Player class.
 * This test suite makes sure all public methods in the Player Class
 * return the proper data.
 *
 * @author Eshal Kashif
 * @version 1
 */
class PlayerTest {

    /**
     * Verifies that the getName() method returns the correct name
     */
    @Test
    void getName() {
        Player one = new Player("One");
        Player two = new Player("Steve");
        assertEquals("One", one.getName());
        assertEquals("Steve", two.getName());
    }

    /**
     * Verifies that the getScore() method returns the correct score after incrementing
     */
    @Test
    void getScore() {
        Player one = new Player("One");
        assertEquals(0, one.getScore());
        one.increaseScore(20);
        assertEquals(20, one.getScore());
    }

    /**
     * Verifies that the addCard() method correctly adds a card to the player's hand
     */
    @Test
    void addCard() {
        Player p = new Player("one");
        assertEquals(0, p.getHand().size());
        Card c = new Card(0, Deck.NUMBER_TYPE, 7);
        p.addCard(c);
        assertEquals(1, p.getHand().size());
        assertSame(c, p.getHand().get(0)); // identity check; Card has no equals()
    }

    /**
     * Verifies that a card is correctly removed from a player's hand
     */
    @Test
    void removeCard() {
        Player p = new Player("one");
        Card c = new Card(0, Deck.WILDTWO_TYPE, Deck.NO_RANK); // any card is fine
        p.addCard(c);
        assertEquals(1, p.getHand().size());

        Card removed = p.removeCard(1); // 1-based index per your API
        assertSame(c, removed);
        assertEquals(0, p.getHand().size());
    }

    /**
     * Verifies a correct description is returned of the player's hand
     */
    @Test
    void getHandDescription() {
        Player one = new Player("one");
        String a = one.getName() + "'s hand is empty";
        assertEquals(a, one.getHandDescription());
        StringBuilder sb = new StringBuilder();
        Card first = new Card(0, 0, 1);
        Card second = new Card(3, 3,2);
        one.addCard(first);
        one.addCard(second);
        sb.append(one.getName()).append("'s cards: ").append("\n");
        sb.append("1: ").append(first.getDescription()).append("\n");
        sb.append("2: ").append(second.getDescription()).append("\n");
        assertEquals(sb.toString(), one.getHandDescription());
    }

    /**
     * Ensures you can't increase score by negative value
     */
    @Test
    void increaseScoreRejectsNegative() {
        Player p = new Player("one");
        assertThrows(IllegalArgumentException.class, () -> p.increaseScore(-1));
    }

    /**
     * Ensures null names cannot be passed in Player constructor
     */
    @Test
    void constructorRejectsNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Player(null));
    }

    /**
     * Ensure an invalid index cannot be used to remove a card
     */
    @Test
    void removeCardOutOfBounds() {
        Player p = new Player("one");
        assertThrows(IndexOutOfBoundsException.class, () -> p.removeCard(1)); // empty hand
    }
}