import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String attackCoordinate;
        String nextPlayer;
        boolean correctAttack1, correctAttack2, shipHit=false;
        int player1Hits = 0, player2Hits = 0;
        String[][] shipCoordinates1 = new String[5][6];
        String[][] shipCoordinates2 = new String[5][6];

        String[][] battleshipGrid1 = makingGrid();
        String[][] player1Grid = makingGrid();
        String[][] battleshipGrid2 = makingGrid();
        String[][] player2Grid = makingGrid();

        displayingGrid(battleshipGrid1);
        System.out.println("Player 1, place your ships on the game field\n");
        makingShips(battleshipGrid1, shipCoordinates1);
        System.out.println("Press Enter and pass the move to another player");
        nextPlayer = input.nextLine();
        if (nextPlayer.equals("")) {
            displayingGrid(battleshipGrid1);
            System.out.println("\nPlayer 2, place your ships on the game field\n");
            makingShips(battleshipGrid2, shipCoordinates2);
        }


        System.out.println("Press Enter and pass the move to another player");
        nextPlayer = input.nextLine();
        if (nextPlayer.equals("")) {
            while (player1Hits < 17 && player2Hits < 17) {
                correctAttack1 = false;
                correctAttack2 = false;
                boolean nextTurn1 = false;
                boolean nextTurn2 = false;
                while (!correctAttack1) {
                    displayingGrid(player2Grid);
                    System.out.println("---------------------");
                    displayingGrid(battleshipGrid1);
                    System.out.println("\nPlayer 1, it's your turn:\n");
                    attackCoordinate = input.nextLine().toUpperCase();
                    correctAttack1 = validCoordinate(attackCoordinate);
                    if (!correctAttack1)
                        System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
                    else
                        shipHit = hitDetection(attackCoordinate, battleshipGrid2, player2Grid, shipCoordinates2, player1Hits);
                    player1Hits = shipHit ? player1Hits + 1 : player1Hits;
                    if (player1Hits >= 17)
                        break;
                }
                while (!nextTurn1) {
                    System.out.println("Press Enter and pass the move to another player");
                    nextPlayer = input.nextLine();
                    if (nextPlayer.equals(""))
                        nextTurn1 = true;
                }
                while (!correctAttack2) {
                    if (player1Hits >= 17) {
                        break;
                    }
                    displayingGrid(player1Grid);
                    System.out.println("---------------------");
                    displayingGrid(battleshipGrid2);
                    System.out.println("\nPlayer 2, it's your turn:\n");
                    attackCoordinate = input.nextLine().toUpperCase();
                    correctAttack2 = validCoordinate(attackCoordinate);
                    if (!correctAttack2)
                        System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
                    else
                        shipHit = hitDetection(attackCoordinate, battleshipGrid1, player1Grid, shipCoordinates1, player2Hits);
                    player2Hits = shipHit ? player2Hits + 1 : player2Hits;
                    if (player2Hits >= 17)
                        break;
                }
                while (!nextTurn2) {
                    System.out.println("Press Enter and pass the move to another player");
                    nextPlayer = input.nextLine();
                    if (nextPlayer.equals(""))
                        nextTurn2 = true;
                }
            }
        }
    }

    public static String[][] makingGrid() {
        String[][] battleshipGrid = new String[11][11];
        for (int row=0; row<11; row++) {
            for (int column=0; column<11; column++) {
                if (row==0 && column==0)
                    battleshipGrid[row][column] = "  ";

                else if (row == 0)
                    battleshipGrid[row][column] = column+" ";

                else if (column == 0)
                    battleshipGrid[row][column] = Character.toString(row+64);

                else
                    battleshipGrid[row][column] = " ~";
            }
        }
        return battleshipGrid;
    }

    public static void displayingGrid(String[][] battleshipGrid) {
        for (String[] row : battleshipGrid) {
            for (String column : row) {
                System.out.print(column);
            }
            System.out.println();
        }
    }

    public static void makingShips(String[][] battleshipGrid, String[][] shipCoordinates) {
        Scanner input = new Scanner(System.in);
        boolean carrierPlaced=false, battleshipPlaced=false, submarinePlaced=false, cruiserPlaced=false, destroyerPlaced=false;
        String startCoordinate, endCoordinate;

        while (!carrierPlaced) {
            System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells)");
            startCoordinate = input.next().toUpperCase();
            endCoordinate = input.next().toUpperCase();
            carrierPlaced = takingCoordinates(startCoordinate, endCoordinate, 5, "Aircraft Carrier", false, battleshipGrid);
            if (carrierPlaced) {
                updateGrid(startCoordinate, endCoordinate, battleshipGrid, shipCoordinates, 0);
            }
        }
        displayingGrid(battleshipGrid);
        while (!battleshipPlaced) {
            System.out.println("Enter the coordinates of the Battleship (4 cells)");
            startCoordinate = input.next().toUpperCase();
            endCoordinate = input.next().toUpperCase();
            battleshipPlaced = takingCoordinates(startCoordinate, endCoordinate, 4, "Battleship", false, battleshipGrid);
            if (battleshipPlaced) {
                updateGrid(startCoordinate, endCoordinate, battleshipGrid, shipCoordinates, 1);
            }
        }
        displayingGrid(battleshipGrid);
        while (!submarinePlaced) {
            System.out.println("Enter the coordinates of the Submarine (3 cells)");
            startCoordinate = input.next().toUpperCase();
            endCoordinate = input.next().toUpperCase();
            submarinePlaced = takingCoordinates(startCoordinate, endCoordinate, 3, "Submarine", false, battleshipGrid);
            if (submarinePlaced) {
                updateGrid(startCoordinate, endCoordinate, battleshipGrid, shipCoordinates, 2);
            }
        }
        displayingGrid(battleshipGrid);
        while (!cruiserPlaced) {
            System.out.println("Enter the coordinates of the Cruiser (3 cells)");
            startCoordinate = input.next().toUpperCase();
            endCoordinate = input.next().toUpperCase();
            cruiserPlaced = takingCoordinates(startCoordinate, endCoordinate, 3, "Cruiser", false, battleshipGrid);
            if (cruiserPlaced) {
                updateGrid(startCoordinate, endCoordinate, battleshipGrid, shipCoordinates, 3);
            }
        }
        displayingGrid(battleshipGrid);
        while (!destroyerPlaced) {
            System.out.println("Enter the coordinates of the Destroyer (2 cells)");
            startCoordinate = input.next().toUpperCase();
            endCoordinate = input.next().toUpperCase();
            destroyerPlaced = takingCoordinates(startCoordinate, endCoordinate, 2, "Destroyer", false, battleshipGrid);
            if (destroyerPlaced) {
                updateGrid(startCoordinate, endCoordinate, battleshipGrid, shipCoordinates, 4);
            }
        }
        displayingGrid(battleshipGrid);
        for (int x=0; x<5; x++) {
            for (int y=0; y<5; y++) {
                if (shipCoordinates[x][y] == null) {
                    shipCoordinates[x][y] = "X";
                }
            }
            shipCoordinates[x][5] = "O";
        }

    }

    public static boolean takingCoordinates(String startCoordinate, String endCoordinate, int size, String type, boolean shipPlaced, String[][] battleshipGrid) {
        int startingX, startingY, endingX, endingY;
        startingX = startCoordinate.charAt(0)-64;
        if (startCoordinate.length() == 3)
            startingY = Integer.parseInt(String.valueOf(startCoordinate.charAt(1)).concat(String.valueOf(startCoordinate.charAt(2))));
        else
            startingY = Integer.parseInt(String.valueOf(startCoordinate.charAt(1)));
        endingX = endCoordinate.charAt(0)-64;
        if (endCoordinate.length() == 3)
        endingY = Integer.parseInt(String.valueOf(endCoordinate.charAt(1)).concat(String.valueOf(endCoordinate.charAt(2))));
        else
            endingY = Integer.parseInt(String.valueOf(endCoordinate.charAt(1)));
        if (startingX == endingX) {
            if (startingY-endingY==size-1 || endingY-startingY==size-1) {
                shipPlaced = true;
                if (startingY<endingY) {
                    for (int y=startingY; y<=endingY; y++) {
                        if (battleshipGrid[startingX][y].equals(" O")
                                || battleshipGrid[startingX - 1][y].equals(" O")
                                || battleshipGrid[startingX][y - 1].equals(" O")) {
                            shipPlaced = false;
                            break;
                        }
                        if (startingX + 1 < 11) {
                            if (battleshipGrid[startingX + 1][y].equals(" O")) {
                                shipPlaced = false;
                                break;
                            }
                        }
                        if (y + 1 < 11) {
                            if (battleshipGrid[startingX][y + 1].equals(" O")) {
                                shipPlaced = false;
                                break;
                            }
                        }
                    }
                }
                else {
                    for (int y=endingY; y<=startingY; y++) {
                        if (battleshipGrid[startingX][y].equals(" O")
                                || battleshipGrid[startingX - 1][y].equals(" O")
                                || battleshipGrid[startingX][y - 1].equals(" O")) {
                            shipPlaced = false;
                            break;
                        }
                        if (startingX + 1 < 11) {
                            if (battleshipGrid[startingX + 1][y].equals(" O")) {
                                shipPlaced = false;
                                break;
                            }
                        }
                        if (y + 1 < 11) {
                            if (battleshipGrid[startingX][y + 1].equals(" O")) {
                                shipPlaced = false;
                                break;
                            }
                        }
                    }
                }
                if (!shipPlaced) {
                    System.out.println("Error! You placed it too close to another one. Try again:\n");
                }
            }
            else
                System.out.printf("Error! Wrong length of the %s! Try again:\n\n", type);
        }
        else if (startingY == endingY) {
            if (startingX-endingX==size-1 || endingX-startingX==size-1) {
                shipPlaced = true;
                if (startingX<endingX) {
                    for (int x=startingX; x<=endingX; x++) {
                        if (battleshipGrid[x][startingY].equals(" O")
                                || battleshipGrid[x][startingY - 1].equals(" O")
                                || battleshipGrid[x - 1][startingY].equals(" O")) {
                            shipPlaced = false;
                            break;
                        }
                        if (startingY + 1 < 11) {
                            if (battleshipGrid[x][startingY + 1].equals(" O")) {
                                shipPlaced = false;
                                break;
                            }
                        }
                        if (x + 1 < 11) {
                            if (battleshipGrid[x + 1][startingY].equals(" O")) {
                                shipPlaced = false;
                                break;
                            }
                        }
                    }
                }
                else {
                    for (int x=endingX; x<=startingX; x++) {
                        if (battleshipGrid[x][startingY].equals(" O")
                                || battleshipGrid[x][startingY - 1].equals(" O")
                                || battleshipGrid[x - 1][startingY].equals(" O")) {
                            shipPlaced = false;
                            break;
                        }
                        if (startingY + 1 < 11) {
                            if (battleshipGrid[x][startingY + 1].equals(" O")) {
                                shipPlaced = false;
                                break;
                            }
                        }
                        if (x + 1 < 11) {
                            if (battleshipGrid[x + 1][startingY].equals(" O")) {
                                shipPlaced = false;
                                break;
                            }
                        }
                    }
                }
                if (!shipPlaced) {
                    System.out.println("Error! You placed it too close to another one. Try again:\n");
                }
            }
            else
                System.out.printf("Error! Wrong length of the %s! Try again:\n\n", type);
        }
        else
            System.out.println("Error! Wrong ship location! Try again:\n");
        return shipPlaced;
    }

    public static void updateGrid(String startCoordinate, String endCoordinate, String[][] battleshipGrid, String[][] shipCoordinates, int shipType) {
        int startingX, startingY, endingX, endingY;
        int shipNum = 0;
        String shipCoordinate;
        startingX = startCoordinate.charAt(0)-64;
        if (startCoordinate.length() == 3)
            startingY = Integer.parseInt(String.valueOf(startCoordinate.charAt(1)).concat(String.valueOf(startCoordinate.charAt(2))));
        else
            startingY = Integer.parseInt(String.valueOf(startCoordinate.charAt(1)));
        endingX = endCoordinate.charAt(0)-64;
        if (endCoordinate.length() == 3)
            endingY = Integer.parseInt(String.valueOf(endCoordinate.charAt(1)).concat(String.valueOf(endCoordinate.charAt(2))));
        else
            endingY = Integer.parseInt(String.valueOf(endCoordinate.charAt(1)));

        if (startingY<endingY) {
            for (int y=startingY; y<=endingY; y++) {
                battleshipGrid[startingX][y] = " O";
                shipCoordinate = String.valueOf((char) (startingX+64)).concat(String.valueOf(y));
                shipCoordinates[shipType][shipNum] = shipCoordinate;
                shipNum++;
            }
        }
        else if (startingY>endingY) {
            for (int y=endingY; y<=startingY; y++) {
                battleshipGrid[startingX][y] = " O";
                shipCoordinate = String.valueOf((char) (startingX+64)).concat(String.valueOf(y));
                shipCoordinates[shipType][shipNum] = shipCoordinate;
                shipNum++;
            }
        }
        else if (startingX<endingX) {
            for (int x=startingX; x<=endingX; x++) {
                battleshipGrid[x][startingY] = " O";
                shipCoordinate = String.valueOf((char) (x+64)).concat(String.valueOf(startingY));
                shipCoordinates[shipType][shipNum] = shipCoordinate;
                shipNum++;
            }
        }
        else if (startingX>endingX) {
            for (int x=endingX; x<=startingX; x++) {
                battleshipGrid[x][startingY] = " O";
                shipCoordinate = String.valueOf((char) (x+64)).concat(String.valueOf(startingY));
                shipCoordinates[shipType][shipNum] = shipCoordinate;
                shipNum++;
            }
        }
    }

    public static boolean validCoordinate(String coordinate) {
        int x, y;
        if (coordinate.length() > 3 || coordinate.length() < 2) {
            return false;
        }
        x = coordinate.charAt(0)-64;
        if (coordinate.length() == 3) {
            y = Integer.parseInt(String.valueOf(coordinate.charAt(1)).concat(String.valueOf(coordinate.charAt(2))));
        }
        else {
            y = Integer.parseInt(String.valueOf(coordinate.charAt(1)));
        }
        return x <= 10 && x >= 1 && y <= 10 && y >= 1;
    }

    public static boolean hitDetection(String attackCoordinate, String[][] battleshipGrid, String[][] emptyGrid, String[][] shipCoordinates, int score) {
        boolean shipSunk = false;
        int attackX, attackY;
        attackX = attackCoordinate.charAt(0)-64;
        if (attackCoordinate.length() == 3)
            attackY = Integer.parseInt(String.valueOf(attackCoordinate.charAt(1)).concat(String.valueOf(attackCoordinate.charAt(2))));
        else
            attackY = Integer.parseInt(String.valueOf(attackCoordinate.charAt(1)));

        if (battleshipGrid[attackX][attackY].equals(" O")) {
            battleshipGrid[attackX][attackY] = " X";
            emptyGrid[attackX][attackY] = " X";
            if (score == 16) {
                displayingGrid(emptyGrid);
                System.out.println("\nYou sank the last ship. You won. Congratulations!\n");
                return true;
            }
            for (int x=0; x<5; x++) {
                for (int y=0; y<5; y++) {
                    if (shipCoordinates[x][y].equals(attackCoordinate)) {
                        shipCoordinates[x][y] = "X";
                    }
                }
                if (shipCoordinates[x][0].equals("X") && shipCoordinates[x][1].equals("X") && shipCoordinates[x][2].equals("X")
                        && shipCoordinates[x][3].equals("X") && shipCoordinates[x][4].equals("X") && shipCoordinates[x][5].equals("O")) {
                    shipSunk = true;
                    shipCoordinates[x][5] = "X";
                }
            }
            // displayingGrid(battleshipGrid);
            displayingGrid(emptyGrid);
            if (shipSunk)
                System.out.println("\nYou sank a ship!\n");
            else
                System.out.println("\nYou hit a ship!\n");
            return true;
        }
        else if (battleshipGrid[attackX][attackY].equals(" ~")){
            battleshipGrid[attackX][attackY] = " M";
            emptyGrid[attackX][attackY] = " M";
            // displayingGrid(battleshipGrid);
            displayingGrid(emptyGrid);
            System.out.println("\nYou missed!\n");
            return false;
        }
        else {
            displayingGrid(emptyGrid);
            System.out.println("\nYou hit a ship!\n");
            return false;
        }
    }
}
