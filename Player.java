import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private int score;
    private List<Card> hand;

    public Player(String name, Deck deck){
        this.name = name;
        score = 0;
        hand = new ArrayList<Card>();
        for(int i=0; i < 7; i++){
            hand.add(deck.drawCard());
        }
    }

    public String getName(){return name;}

    public int getScore(){return score;}

    public int increaseScore(int add){
        score += add;
        return score;
    }


    public void printHand(){
        System.out.println(name +" cards:");
        for(int i=1; i<=7; i++){
            System.out.println(i + ": " + hand.get(i-1).getDescription());
        }
    }

    public static void main(String[] args) {
        Deck d = new Deck();
        Player p1 = new Player("me", d);
        p1.printHand();
    }


}
