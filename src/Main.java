import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String attackCoordinate;
        String nextPlayer;
        boolean correctAttack1, correctAttack2, nextTurn1, nextTurn2, shipHit = false;
        boolean nextGrid = false;
        int player1Score = 0, player2Score = 0;

        Player player1 = new Player();
        Player player2 = new Player();
        player1.displayingBattleshipGrid();

        System.out.println("Player 1, place your ships on the game field\n");
        player1.makingShips();
        while (!nextGrid) {
            System.out.println("\nPress Enter and pass the move to player 2");
            nextPlayer = input.nextLine();
            if (nextPlayer.equals("")) {
                nextGrid = true;
            }
        }

        player2.displayingBattleshipGrid();
        System.out.println("\nPlayer 2, place your ships on the game field\n");
        player2.makingShips();
        nextGrid = false;

        while (!nextGrid) {
            System.out.println("\nPress Enter and pass the move to player 1 to start the game");
            nextPlayer = input.nextLine();
            if (nextPlayer.equals(""))
                nextGrid = true;
        }
        while (player1Score < 17 && player2Score < 17) {
            correctAttack1 = false;
            correctAttack2 = false;
            nextTurn1 = false;
            nextTurn2 = false;
            while (!correctAttack1) {
                player2.displayingPlayerGrid();
                System.out.println("---------------------");
                player1.displayingBattleshipGrid();
                System.out.println("\nPlayer 1, it's your turn:\n");
                attackCoordinate = input.nextLine().toUpperCase();
                correctAttack1 = griddyMethods.validCoordinate(attackCoordinate);
                if (!correctAttack1)
                    System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
                else
                    shipHit = player2.hitDetection(attackCoordinate);
                if (shipHit) {
                    player1Score++;
                }
                if (player1Score >= 17)
                    break;
            }
            while (!nextTurn1) {
                if (player1Score >= 17)
                    break;
                System.out.println("Press Enter and pass the move to player 2");
                nextPlayer = input.nextLine();
                if (nextPlayer.equals(""))
                    nextTurn1 = true;
            }
            while (!correctAttack2) {
                if (player1Score >= 17)
                    break;
                player1.displayingPlayerGrid();
                System.out.println("---------------------");
                player2.displayingBattleshipGrid();
                System.out.println("\nPlayer 2, it's your turn:\n");
                attackCoordinate = input.nextLine().toUpperCase();
                correctAttack2 = griddyMethods.validCoordinate(attackCoordinate);
                if (!correctAttack2)
                    System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
                else
                    shipHit = player1.hitDetection(attackCoordinate);
                if (shipHit) {
                    player2Score++;
                }
                if (player2Score >= 17)
                    break;
            }
            while (!nextTurn2) {
                if (player1Score >= 17 || player2Score >= 17)
                    break;
                System.out.println("Press Enter and pass the move to player 1");
                nextPlayer = input.nextLine();
                if (nextPlayer.equals(""))
                    nextTurn2 = true;
            }
        }
        if (player1Score >= 17)
            System.out.println("\nPlayer 1 sank the last ship. You won. Congratulations!\n");
        else
            System.out.println("\nPlayer 2 sank the last ship. You won. Congratulations!\n");
    }
}
