import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Nim {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String nameP1 = "";
        String nameP2 = "";
        String pile = "";
        String player = "";
        String loser = "";
        String winner = "";
        Map<String, Integer> piles = new HashMap<String, Integer>();{{
            piles.put("A", 3);
            piles.put("B", 4);
            piles.put("C", 5);
        }};
        int numberTaken;
        int turn = 1;

        //Require each player's name
        System.out.printf("Player 1, enter your name: ");
        nameP1 = keyboard.nextLine();
        while (nameP1.isEmpty()){
            System.out.printf("You must enter a name: ");
            nameP1 = keyboard.nextLine();
        }

        System.out.printf("Player 2, enter your name: ");
        nameP2 = keyboard.nextLine();
        while (nameP2.isEmpty()){
            System.out.printf("You must enter a name: ");
            nameP2 = keyboard.nextLine();
        }

        System.out.printf("A: %d  B: %d  C: %d \n", piles.get("A"), piles.get("B"), piles.get("C"));

        //Determine which player's turn it is (odd turns P1, even turns P2)
        while (piles.get("A") + piles.get("B") + piles.get("C") > 1) {
            if (turn % 2 != 0) {
                player = nameP1;
            } else {
                player = nameP2;
            }

            System.out.printf("%s, choose a pile: ", player);
            pile = keyboard.next().toUpperCase();

            // Disallow picking from empty pile
            while (pile.equals("A") && piles.get("A") == 0 ||
                   pile.equals("B") && piles.get("B") == 0 ||
                   pile.equals("C") && piles.get("C") == 0) {
                System.out.printf(
                        "Nice try, %s, that pile is empty. Chose again: ",
                        player);
                pile = keyboard.next().toUpperCase();
            }
            // Disallow picking invalid pile
            while (!pile.equals("A") &&
                   !pile.equals("B") &&
                   !pile.equals("C")){
                System.out.printf("That is not a valid choise. Choose A, B or C: ");
                pile = keyboard.next().toUpperCase();
            }

            System.out.printf("How many to remove from pile %s: ", pile);
            numberTaken = keyboard.nextInt();

            while (numberTaken < 1) {
                System.out.printf("You must take at least one. How many?: ");
                numberTaken = keyboard.nextInt();
            }
            // Disallow taking more counters that a given pile contains
            while (pile.equals("A") && numberTaken > piles.get("A") ||
                   pile.equals("B") && numberTaken > piles.get("B") ||
                   pile.equals("C") && numberTaken > piles.get("C")) {
                System.out.printf(
                        "You cannot take that many from pile %s. Try again: ",
                        pile);
                numberTaken = keyboard.nextInt();
            }

            turn += 1;
            
            //Subtract number taken from a pile
            if (pile.equals("A")) {
                piles.put("A", (piles.get("A")-numberTaken));
            } else if (pile.equals("B")) {
                piles.put("B", (piles.get("B")-numberTaken));
            } else {
                piles.put("C", (piles.get("C")-numberTaken));
            }

            System.out.printf("A: %d  B: %d  C: %d \n", piles.get("A"), piles.get("B"), piles.get("C"));
        }

        // This determines which player wins and loses based on the turn that
        // ends the game
        // TODO (rachael) handle the case where the player on the last turn picks
        // up the final counter to lose intentionally
        if (turn % 2 != 0) {
            loser = nameP1;
            winner = nameP2;
        } else {
            loser = nameP2;
            winner = nameP1;
        }

        System.out.printf("%s you must take the last remaining counter, so you "
                         + "lose. %s wins!", loser, winner);
        keyboard.close();
    }
}
