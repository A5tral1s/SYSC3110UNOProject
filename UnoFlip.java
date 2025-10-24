/*
SOME NOTES
In Card class:
- Fix enumeration (type==6 doesnt make sense)
- Include compare/match method to check if card can be place on top

In Deck Class:
- Should deck and discard be ArrayDequeue instead of ArrayList?
    - Has push and peek functions
- Should we ensure Discard starts with non-wild card? We should intialize discard to start with one card.
- Should also create function that returns top of the discard/ what card is currently played

In UnoFlip class:
- Work in progress. Need to handle all inputs and flow of game.
- Initalize players
- Call Deck's method to create first discard
- Display state (top card on discard pile and current players pile)
- Allow user to pick card (index based) or draw card
- Check if picked card is valid play
- Handle special cases/wild cards (rotate, pick up cards, wildcard colour change)
- Need to handle points
 */

import java.util.*;
import java.util.Scanner;

public class UnoFlip {
    private final List<Player> players;
    private Deck deck;
    private static final int CARDS_PER_PLAYER  = 7;
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;
    private final Scanner sc = new Scanner(System.in);
    private int turn = 0;   // index into players
    private int dir  = +1;  // +1 clockwise, -1 counter

    public UnoFlip() {
        deck = new Deck();
        players = new ArrayList<Player>();
    }

    public void play(){
        System.out.println("-------------- UNO FLIP --------------");
        initPlayers();

        while(true){
            Player currentPlayer = players.get(turn);
            Card top = deck.
        }

    }

    private void initPlayers() {
        // Creating number of players
        int n;
        while (true) {
            System.out.println("Enter the number of players (2-4):");
            String player_input = sc.nextLine().trim();
            try {
                n = Integer.parseInt(player_input);
                if (n >= MIN_PLAYERS && n <= MAX_PLAYERS) break;
            } catch (NumberFormatException ignored) {
            }
            System.out.print("Invalid. ");
        }

        // Initialize players
        for (int i = 1; i <= n; i++) {
            System.out.println("Enter player " + i + "'s name: ");
            String name = sc.nextLine().trim();
            players.add(new Player(name));
        }

        // Deal hand to each player
        for (Player p : players) {
            for (int i = 0; i < CARDS_PER_PLAYER; i++) {
                p.draw(deck.drawCard());
            }
        }
    }





    //Deal and flip the first discard card.
    public void start() {
        for (int k = 0; k < CARDS_PER_PLAYER; k++) {
            for (Player p : players) {
                p.draw(safelyDraw());
            }
        }
        discard.push(safelyDraw());
    }

    public Player current() { return players.get(turn); }
    public Card top() { return discard.peek(); }
    public List<Card> hand() { return current().getHand(); }

   
    private Card safelyDraw() {
        /*
        Should this be handled in Deck?
        KEEP DISCARDS IN DECK, NOT UNOFLIP
        if (draw.isEmpty()) {
            Card topCard = discard.pop();
            draw.addCards(new ArrayList<>(discard));
            discard.clear();
            discard.push(topCard);
            draw.shuffle();
        }
        *
         */
        return draw.drawCard();
    }

    public static void main(String[] args) {

        UnoFlip game =  new UnoFlip();
        game.play();
    }
}
