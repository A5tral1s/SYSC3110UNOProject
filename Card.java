/**
 * Class Card - one of the Cards in the UnoFlip Deck.
 *
 * This class is part of the UnoFlip game application.
 *
 * A "Card" represents a single card in the game. The game keeps
 * track of a total of 116 as per the guidelines, each with a color,
 * type, and rank.
 *
 * @author Matthew Sanii
 * @version 1
 */

public class Card {
    public enum colortype{ //The four main card colors, plus ALL to represent the WILD cards
        RED,
        BLUE,
        GREEN,
        YELLOW,
        ALL
    }

    public enum cardtype{ //All the different card types in UnoFlip
        NUMBER,
        SKIP,
        DRAW_ONE,
        REVERSE,
        FLIP,
        WILD,
        WILDTWO
    }

    private colortype col;
    private cardtype type;
    private int rank;

    /**
    * Create a Card with a color, type, and rank.
    * @param color The Color of the card
    * @param type The type of card it is (wild, draw, skip)
    * @param rank The numerical rank of the card.
    */
    public Card(int color, int type, int rank){
        if(type == 5 || type == 6){ //If card type is one of the two Wild cards, set 'color' to ALL
            this.type = cardtype.values()[type];
            this.col = colortype.values()[4];
        }
        else{
            this.type = cardtype.values()[type];
            this.col = colortype.values()[color];
            this.rank = rank;
        }
    }

    /**
    * Get the card's color
    * @return The card's color
    */
    public colortype getColor() {
        return this.col;
    }

    /**
    * Get the card's type
    * @return The card's type
    */
    public cardtype getType(){
        return this.type;
    }

    /**
    * Get the card's rank
    * @return The card's rank
    */
    public int getRank(){
        return this.rank;
    }

    /**
    * Return a description of the card, stating color, type, and rank where applicable.
    * @return The description of the card as a String
    */
    public String getDescription(){
        if(this.getRank()==Deck.NO_RANK){
            return this.getColor() +  " " + this.getType();
        }
        return this.getColor() +  " " + this.getRank();
    }



}
