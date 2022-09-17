public class griddyMethods {

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

    public static boolean validCoordinate(String coordinate) {
        int x, y;
        if (coordinate.length() > 3 || coordinate.length() < 2) {
            return false;
        }
        x = coordinate.charAt(0)-64;
        if (coordinate.length() == 3) {
            try {
                y = Integer.parseInt(String.valueOf(coordinate.charAt(1)).concat(String.valueOf(coordinate.charAt(2))));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        else {
            try {
                y = Integer.parseInt(String.valueOf(coordinate.charAt(1)));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return x <= 10 && x >= 1 && y <= 10 && y >= 1;
    }

    public static boolean takingCoordinates(String startCoordinate, String endCoordinate, int size, String type, boolean shipPlaced, String[][] battleshipGrid) {
        boolean startValid = griddyMethods.validCoordinate(startCoordinate);
        boolean endValid = griddyMethods.validCoordinate(endCoordinate);
        if (!startValid || !endValid) {
            System.out.println("Your coordinates are invalid for the grid.\n");
            return false;
        }
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
}
