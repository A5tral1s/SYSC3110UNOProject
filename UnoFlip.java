import java.util.*;

public class UnoFlip {

    private static final int CARDS_PER_PLAYER = 7;
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;

    private final List<Player> players = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private final Deck deck = new Deck();

    private int turn = 0;                    // current player's index
    private int dir = +1;                    // +1 clockwise, -1 counterclockwise
    private Card.colortype forcedColour;     // active colour after Wild
    private Card topCard;                    // top of discard pile

    public void play() {
        System.out.println("-------------- UNO FLIP --------------");

        // player count
        int n;
        while (true) {
            System.out.print("Enter the number of players (2-4): ");
            String in = sc.nextLine().trim();
            try {
                n = Integer.parseInt(in);
                if (n >= MIN_PLAYERS && n <= MAX_PLAYERS) break; // <-- correct condition
            } catch (NumberFormatException ignored) { }
            System.out.println("Invalid input");
        }

        // player names
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter a name for player " + i + ": ");
            players.add(new Player(sc.nextLine().trim()));
        }

        // Add 7 cards to each player's hand
        for (Player p : players) {
            while (p.getHand().size() < CARDS_PER_PLAYER) {
                p.addCard(deck.drawCard());
            }
        }

        // start discard pile with a NUMBER card
        while (true) {
            Card c = deck.drawCard();
            deck.discard(c);
            topCard = c;
            if (c.getType() == Card.cardtype.NUMBER) break;
        }
        forcedColour = null;

        // game loop
        while (true) {
            Player cur = players.get(turn);

            System.out.println("\n------- " + cur.getName() + "'s turn -------");
            System.out.println("Top card: " + topCard.getDescription());
            List<Card> hand = cur.getHand();
            /*
            System.out.println("Your cards:");
            List<Card> hand = cur.getHand();
            for (int i = 0; i < hand.size(); i++) {
                System.out.println((i + 1) + ". " + hand.get(i).getDescription());
            }

            */
            System.out.println(cur.getHandDescription());
            System.out.print("Enter a card index to play a card, or 0 to draw a card: ");

            int choice;
            while (true) {
                String s = sc.nextLine().trim();
                try {
                    choice = Integer.parseInt(s);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    System.out.print("Enter a card index to play a card, or 0 to draw a card: ");
                }
            }

            // draw a card
            if (choice == 0) {
                Card d = deck.drawCard();
                cur.addCard(d);
                System.out.println(cur.getName() + " drew a card: " + d.getDescription());
                forcedColour = null;
                advance(1);

                // resultant state
                //printResultantState();
                continue;

            }

            // invalid index
            if (choice < 1 || choice > hand.size()) {
                System.out.println("Invalid input. Please enter a number between 1 and " + hand.size());
                continue;
            }

            Card toPlay = hand.get(choice - 1);
            Card.colortype chosen = null;

            // wild / wild-draw-two colour
            if (toPlay.getType() == Card.cardtype.WILD || toPlay.getType() == Card.cardtype.WILDTWO) {
                System.out.print("Choose colour (RED/BLUE/GREEN/YELLOW): ");
                chosen = parseColour(sc.nextLine().trim());
                if (chosen == null || chosen == Card.colortype.ALL) {
                    System.out.println("Invalid input. Please enter a valid colour");
                    continue;
                }
            }

            // validate placement
            if (!isLegal(toPlay, chosen)) {
                System.out.println("Placing that card is not a valid move. Try again.");
                continue;
            }

            // play the card
            cur.removeCard(choice);
            deck.discard(toPlay);
            topCard = toPlay;
            System.out.println(cur.getName() + " played a card: " + toPlay.getDescription());
            forcedColour = (toPlay.getType() == Card.cardtype.WILD || toPlay.getType() == Card.cardtype.WILDTWO)
                    ? chosen : null;

            // scoring
            int gained = points(toPlay);
            cur.increaseScore(gained);
            System.out.println(cur.getName() + " gains " + gained + " point(s).  Total: " + cur.getScore());


            // win condition
            if (cur.getHand().isEmpty()) {
                System.out.println(cur.getName() + " wins the game.");
                break;
            }

            // handle special cards
            Card.cardtype t = toPlay.getType();
            if (t == Card.cardtype.SKIP) {
                int skipped = peekNextIndex(1);
                System.out.println(players.get(skipped).getName() + " misses their turn due to SKIP.");
                advance(2);
            } else if (t == Card.cardtype.REVERSE) {
                if (players.size() == 2) {
                    int skipped = peekNextIndex(1);
                    System.out.println(players.get(skipped).getName() + " misses their turn due to REVERSE.");
                    advance(2);
                } else {
                    System.out.println(cur.getName() + " has placed a REVERSE card. The direction of play changes.");
                    dir = -dir;
                    advance(1);
                }
            } else if (t == Card.cardtype.DRAW_ONE) {
                int victim = peekNextIndex(1);
                Card d = deck.drawCard();
                players.get(victim).addCard(d);
                System.out.println(players.get(victim).getName()
                        + " has to draw a card due to DRAW ONE: " + d.getDescription());
                advance(2);
            } else if (t == Card.cardtype.WILDTWO) {
                int victim = peekNextIndex(1);
                Card d1 = deck.drawCard();
                Card d2 = deck.drawCard();
                players.get(victim).addCard(d1);
                players.get(victim).addCard(d2);
                System.out.println(players.get(victim).getName()
                        + " draws two cards due to WILD DRAW TWO: "
                        + d1.getDescription() + ", " + d2.getDescription());
                advance(2);
            } else {
                advance(1);
            }
            //printResultantState();
        } // end while(true)
        //print resultant state


    } // end play()

    // ---- helpers ----

    // Print top discard + next player's cards (ADD THIS)
    private void printResultantState() {
        Player next = players.get(turn);
        System.out.println("Resultant state — Top card: " + topCard.getDescription());
        System.out.println("Next player: " + next.getName() + " — cards:");
        List<Card> nh = next.getHand();
        for (int i = 0; i < nh.size(); i++) {
            System.out.println((i + 1) + ". " + nh.get(i).getDescription());
        }
    }

    // Simple scoring for M1 (ADD THIS)
    private int points(Card c) {
        switch (c.getType()) {
            case NUMBER:    return Math.max(0, c.getRank()); // rank value
            case SKIP:
            case REVERSE:
            case DRAW_ONE:
                return 20; // if your enum is DRAW_ONE
            case WILD:
            case WILDTWO:
                return 50;
            default:
                return 0;
        }
    }

    private boolean isLegal(Card c, Card.colortype chosenIfWild) {
        if (c.getType() == Card.cardtype.WILD || c.getType() == Card.cardtype.WILDTWO)
            return chosenIfWild != null && chosenIfWild != Card.colortype.ALL;

        Card.colortype active = (forcedColour != null ? forcedColour : topCard.getColor());
        boolean colourMatch = (c.getColor() == active);
        boolean numberMatch = (c.getType() == Card.cardtype.NUMBER &&
                topCard.getType() == Card.cardtype.NUMBER &&
                c.getRank() == topCard.getRank());
        boolean actionMatch = (c.getType() != Card.cardtype.NUMBER &&
                c.getType() == topCard.getType());
        return colourMatch || numberMatch || actionMatch;
    }

    // move turn pointer
    private void advance(int steps) {
        int n = players.size();
        turn = ((turn + steps * dir) % n + n) % n;
    }

    // look ahead without moving turn
    private int peekNextIndex(int steps) {
        int n = players.size();
        return ((turn + steps * dir) % n + n) % n;
    }

    // parse colour text
    private Card.colortype parseColour(String s) {
        s = s.toUpperCase(Locale.ROOT).trim();
        switch (s) {
            case "RED":    return Card.colortype.RED;
            case "BLUE":   return Card.colortype.BLUE;
            case "GREEN":  return Card.colortype.GREEN;
            case "YELLOW": return Card.colortype.YELLOW;
            default:       return null;
        }
    }

    public static void main(String[] args) {
        new UnoFlip().play();
    }
}
