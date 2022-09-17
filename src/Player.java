import java.util.Scanner;

public class Player {
    private String[][] battleshipGrid;
    private String[][] playerGrid;
    private String[][] shipCoordinates;
    private int score;

    public Player() {
        this.battleshipGrid = griddyMethods.makingGrid();
        this.playerGrid = griddyMethods.makingGrid();
        this.shipCoordinates = new String[5][6];
        this.score = 0;
    }

    public void displayingBattleshipGrid() {
        for (String[] row : battleshipGrid) {
            for (String column : row) {
                System.out.print(column);
            }
            System.out.println();
        }
    }

    public void displayingPlayerGrid() {
        for (String[] row : playerGrid) {
            for (String column : row) {
                System.out.print(column);
            }
            System.out.println();
        }
    }


    public void makingShips() {
        Scanner input = new Scanner(System.in);
        boolean carrierPlaced=false, battleshipPlaced=false, submarinePlaced=false, cruiserPlaced=false, destroyerPlaced=false;
        String startCoordinate, endCoordinate;

        while (!carrierPlaced) {
            System.out.println("Enter the starting coordinate of the Aircraft Carrier (5 cells)");
            startCoordinate = input.nextLine().toUpperCase();
            System.out.println("Enter the ending coordinate of the Aircraft Carrier (5 cells)");
            endCoordinate = input.nextLine().toUpperCase();
            carrierPlaced = griddyMethods.takingCoordinates(startCoordinate, endCoordinate, 5, "Aircraft Carrier", false, battleshipGrid);
            if (carrierPlaced) {
                updateGrid(startCoordinate, endCoordinate, 0);
            }
        }
        displayingBattleshipGrid();
        while (!battleshipPlaced) {
            System.out.println("Enter the starting coordinate of the Battleship (4 cells)");
            startCoordinate = input.nextLine().toUpperCase();
            System.out.println("Enter the ending coordinate of the Battleship (4 cells)");
            endCoordinate = input.nextLine().toUpperCase();
            battleshipPlaced = griddyMethods.takingCoordinates(startCoordinate, endCoordinate, 4, "Battleship", false, battleshipGrid);
            if (battleshipPlaced) {
                updateGrid(startCoordinate, endCoordinate, 1);
            }
        }
        displayingBattleshipGrid();
        while (!submarinePlaced) {
            System.out.println("Enter the starting coordinate of the Submarine (3 cells)");
            startCoordinate = input.nextLine().toUpperCase();
            System.out.println("Enter the ending coordinate of the Submarine (3 cells)");
            endCoordinate = input.nextLine().toUpperCase();
            submarinePlaced = griddyMethods.takingCoordinates(startCoordinate, endCoordinate, 3, "Submarine", false, battleshipGrid);
            if (submarinePlaced) {
                updateGrid(startCoordinate, endCoordinate, 2);
            }
        }
        displayingBattleshipGrid();
        while (!cruiserPlaced) {
            System.out.println("Enter the starting coordinate of the Cruiser (3 cells)");
            startCoordinate = input.nextLine().toUpperCase();
            System.out.println("Enter the ending coordinate of the Cruiser (3 cells)");
            endCoordinate = input.nextLine().toUpperCase();
            cruiserPlaced = griddyMethods.takingCoordinates(startCoordinate, endCoordinate, 3, "Cruiser", false, battleshipGrid);
            if (cruiserPlaced) {
                updateGrid(startCoordinate, endCoordinate, 3);
            }
        }
        displayingBattleshipGrid();
        while (!destroyerPlaced) {
            System.out.println("Enter the starting coordinate of the Destroyer (2 cells)");
            startCoordinate = input.nextLine().toUpperCase();
            System.out.println("Enter the ending coordinate of the Destroyer (2 cells)");
            endCoordinate = input.nextLine().toUpperCase();
            destroyerPlaced = griddyMethods.takingCoordinates(startCoordinate, endCoordinate, 2, "Destroyer", false, battleshipGrid);
            if (destroyerPlaced) {
                updateGrid(startCoordinate, endCoordinate, 4);
            }
        }
        displayingBattleshipGrid();
        for (int x=0; x<5; x++) {
            for (int y=0; y<5; y++) {
                if (shipCoordinates[x][y] == null) {
                    shipCoordinates[x][y] = "X";
                }
            }
            shipCoordinates[x][5] = "O";
        }
    }

    public void updateGrid(String startCoordinate, String endCoordinate, int shipType) {
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

    public boolean hitDetection(String attackCoordinate) {
        boolean shipSunk = false;
        int attackX, attackY;
        attackX = attackCoordinate.charAt(0)-64;
        if (attackCoordinate.length() == 3)
            attackY = Integer.parseInt(String.valueOf(attackCoordinate.charAt(1)).concat(String.valueOf(attackCoordinate.charAt(2))));
        else
            attackY = Integer.parseInt(String.valueOf(attackCoordinate.charAt(1)));

        if (battleshipGrid[attackX][attackY].equals(" O")) {
            battleshipGrid[attackX][attackY] = " X";
            playerGrid[attackX][attackY] = " X";
            if (score == 16) {
                displayingPlayerGrid();
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
            displayingPlayerGrid();
            if (shipSunk)
                System.out.println("\nYou sank a ship!\n");
            else
                System.out.println("\nYou hit a ship!\n");
            score++;
            return true;
        }
        else if (battleshipGrid[attackX][attackY].equals(" ~")){
            battleshipGrid[attackX][attackY] = " M";
            playerGrid[attackX][attackY] = " M";
            displayingPlayerGrid();
            System.out.println("\nYou missed!\n");
            return false;
        }
        else {
            displayingPlayerGrid();
            System.out.println("\nYou've already hit that location.\n");
            return false;
        }
    }
}
