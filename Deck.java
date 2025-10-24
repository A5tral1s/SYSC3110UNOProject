import java.util.ArrayList;
import java.util.Collections; 

public class Deck {
    private ArrayList<Card> cards;   // cards where you draw from
    private ArrayList<Card> discards;   // cards that have already been played

    public static final int DUPLICATE = 2;
    public static final int MAX_COLOURS = 4;
    public static final int MAX_RANK = 9;

    //Colour type
    public static final int ALL_TYPE = 4;

    //Card type
    public static final int NUMBER_TYPE = 0;
    public static final int SKIP_TYPE = 1;
    public static final int DRAW_ONE_TYPE = 2;
    public static final int REVERSE_TYPE = 3;
    public static final int WILD_TYPE = 4;
    public static final int WILDTWO_TYPE = 5;
    public static final int NO_RANK = -1;

    public Deck(){
        cards = new ArrayList<>();
        discards = new ArrayList<>();
        
        createDeck();
        shuffle();
    }

    //Build the full deck of cards.
    private void createDeck(){

        // for each colour
        for(int colour = 0; colour < MAX_COLOURS; colour++){
            for(int i = 0; i < DUPLICATE; i++){
                // create cards 1 to 9 (number type)
                for(int rank = 1; rank <= MAX_RANK; rank++){
                    cards.add(new Card(colour, NUMBER_TYPE, rank));   
                }

                // add special cards
                cards.add(new Card(colour, SKIP_TYPE, NO_RANK));
                cards.add(new Card(colour, DRAW_ONE_TYPE, NO_RANK));
                cards.add(new Card(colour, REVERSE_TYPE, NO_RANK)); 
            }
        }

        // add wild cards
        for(int i = 0; i < (DUPLICATE*DUPLICATE); i++){
            cards.add(new Card(ALL_TYPE, WILD_TYPE, NO_RANK));
            cards.add(new Card(ALL_TYPE, WILDTWO_TYPE, NO_RANK)); 
        }
    }

    //Shuffle the deck of cards.
    public void shuffle(){
        Collections.shuffle(cards);
    }

    //When the deck of cards runs out, reshuffle discards to refill the deck
    private void reshuffleDiscards(){
        cards.addAll(discards);
        discards.clear();
        shuffle();
    }

    //Draws a card from the deck of cards.
    public Card drawCard(){
        if(cards.isEmpty()){
            reshuffleDiscards();
        }
        return cards.remove(0);

    }

    //Adds a card to the pile of discards
    public void discard(Card card){
        discards.add(card);
    }

}