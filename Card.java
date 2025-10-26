public class Card {
    public enum colortype{
        RED,
        BLUE,
        GREEN,
        YELLOW,
        ALL
    }

    public enum cardtype{
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

    public Card(int color, int type, int rank){
        if(type == 5 || type == 6){ //****NO 6? THIS WOULD CAUSE INDEXOUTOFBOUNDS
            this.type = cardtype.values()[type];
            this.col = colortype.values()[4];
        }
        else{
            this.type = cardtype.values()[type];
            this.col = colortype.values()[color];
            this.rank = rank;
        }
    }

    public colortype getColor() {
        return this.col;
    }

    public cardtype getType(){
        return this.type;
    }

    public int getRank(){
        return this.rank;
    }

    public String getDescription(){
        if(this.getRank()==Deck.NO_RANK){
            return this.getColor() +  " " + this.getType();
        }
        return this.getColor() +  " " + this.getRank();
    }



}
