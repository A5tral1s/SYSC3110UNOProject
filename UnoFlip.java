import java.util.*;

public class UnoFlip {
    private final List<Player> players = new ArrayList<>();
    private final Deque<Card> discard = new ArrayDeque<>();// cards that were already played 
    private Deck draw;

    private int turn = 0;   // index into players
    private int dir  = +1;  // +1 clockwise, -1 counter

    public UnoFlip(List<Player> playerList, Deck deck) {
        if (playerList == null || playerList.size() < 2 || playerList.size() > 4) {
            throw new IllegalArgumentException("Need 2-4 players");
        }
        players.addAll(playerList);
        draw = deck;
    }

    //Deal and flip the first discard card.
    public void start(int cardsPerPlayer) {
        for (int k = 0; k < cardsPerPlayer; k++) {
            for (Player p : players) {
                p.addToHand(safelyDraw());
            }
        }
        discard.push(safelyDraw());
    }

    public Player current() { return players.get(turn); }
    public Card top() { return discard.peek(); }
    public List<Card> hand() { return current().getHand(); }

   
    private Card safelyDraw() {
        if (draw.isEmpty()) {
            Card topCard = discard.pop();
            draw.addCards(new ArrayList<>(discard));
            discard.clear();
            discard.push(topCard);
            draw.shuffle();
        }
        return draw.drawCard();
    }
}
