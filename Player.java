import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private String name;
    private int score;
    private List<Card> hand;

    public Player(String name){
        if(name==null){
            throw new IllegalArgumentException("Player name cannot be empty.");
        }
        this.name = name;
        score = 0;
        hand = new ArrayList<Card>();

    }

    public String getName(){return name;}

    public int getScore(){return score;}

    public int increaseScore(int add){
        if(add < 0){throw new IllegalArgumentException("Score increment must be >= 0");}
        score += add;
        return score;
    }

    public List<Card> getHand(){
        return Collections.unmodifiableList(hand);
    }

    public void addCard(Card card){
        hand.add(card);
    }

    public Card removeCard(int i){
        return hand.remove(i-1);
    }


    public String getHandDescription(){
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("'s cards: ").append("\n");
        if(hand.isEmpty()) return name + "'s hand is empty";
        for(int i=1; i<=hand.size(); i++){
            sb.append(i).append(": ").append(hand.get(i-1).getDescription()).append("\n");
        }
        return sb.toString();
    }

    // DELETE LATER
    /*
    public static void main(String[] args) {
        Deck d = new Deck();
        Player p1 = new Player("me", d);
        System.out.println(p1.getHandDescription());
    }
    */




}
