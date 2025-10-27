import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UnoFlipTest {

    @Test
    void play() {
        //Testing to ensure the game initialization runs properly with base info for
        //number of players, names of players, and some actions.
        String input = "2\nAlice\nBob\n1\n0\n3\n2\n3\n5\n1\n6\n3\n2";
        Scanner testScanner = new Scanner(input);
        UnoFlip testGame = new UnoFlip(testScanner);
        try {
            testGame.play();
        }
        catch(NoSuchElementException e){
        }
    }
}