import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getName() {
        Player one = new Player("One");
        Player two = new Player("Steve");
        assertEquals("One", one.getName());
        assertEquals("Steve", two.getName());
    }

    @Test
    void getScore() {
        Player one = new Player("One");
        assertEquals(0, one.getScore());
        one.increaseScore(20);
        assertEquals(20, one.getScore());
    }

    @Test
    void addCard() {
        Player one = new Player("one");
        List<Card> e = new ArrayList<Card>();
        assertEquals(e, one.getHand());
        one.addCard(new Card(0, 6, 2));
        e.add(new Card(3, 2, 3));
        assertNotEquals(e, one.getHand());
    }

    @Test
    void removeCard() {
        Player one = new Player("one");
        List<Card> e = new ArrayList<Card>();
        Card toremove = new Card(0, 6,2);
        one.addCard(toremove);
        e.add(one.removeCard(1));
        assertEquals(e.get(0), toremove);
    }

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
}