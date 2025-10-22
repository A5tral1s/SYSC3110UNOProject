import java.util.ArrayList;
import java.util.Collections; 

public class Deck {
    private ArrayList<Card> cards;   // cards where you draw from
    private ArrayList<Card> discards;   // cards that have already been played

    public static final int MAX_COLOURS = 4;
    public static final int MAX_RANK = 9;

    public static final int ALL_TYPE = 4;
    public static final int NUMBER_TYPE = 0;
    public static final int SKIP_TYPE = 1;
    public static final int ONE_TYPE = 2;
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

        //EDIT: adjust range, right now just one of each

        // for each colour
        for(int colour = 0; colour < MAX_COLOURS; colour++){
            
            // create cards 0 to 9 (number type)
            for(int rank = 0; rank <= MAX_RANK; rank++){
                cards.add(new Card(colour, NUMBER_TYPE, rank));   
            }

            // add special cards
            cards.add(new Card(colour, SKIP_TYPE, NO_RANK));
            cards.add(new Card(colour, ONE_TYPE, NO_RANK));
            cards.add(new Card(colour, REVERSE_TYPE, NO_RANK)); 
        }

        // add wild cards
        cards.add(new Card(ALL_TYPE, WILD_TYPE, NO_RANK));
        cards.add(new Card(ALL_TYPE, WILDTWO_TYPE, NO_RANK)); 
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
        } else {
            return cards.remove(0);
        }
    }

    //Adds a card to the pile of discards
    public void discard(Card card){
        discards.add(card);
    }

}