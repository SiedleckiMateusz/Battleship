package pl.weddingapp.battleship;

import java.util.Random;

public class BattleshipGame {

    /*
     * Ship designations: Each ship has its name written in the int shipName field. The name contains information: The number of tens is the length of the ship, the number of units is the number of the ship, e.g. the second two-mast is name = 22
     * Markings on the ships board are the names of the ships
     * Mark the field conditions on the player's board. Field marked 0 - Cpu field free of shot, 1- shot missed, 2- shot scored
     * To check if a ship is hit and sunk, we check if all fields with the same name have been hit. If so, then all the boxes are around 1 on the player board, which makes the fields inactive.
     *
     *
     *
     * */

    public int[][] boardForThePlayer = new int[12][12];
    private int[][] boardWithShips = new int[12][12];

    private Random r = new Random();

    BattleshipGame() {
        boardReset(boardForThePlayer);
        boardReset(boardWithShips);
        drawAllOfShips();
    }

    private void drawAllOfShips() {
        drawOfTheShip(4, 41);
        drawOfTheShip(3, 31);
        drawOfTheShip(3, 32);
        drawOfTheShip(2, 21);
        drawOfTheShip(2, 22);
        drawOfTheShip(2, 23);
        drawOfTheShip(1, 11);
        drawOfTheShip(1, 12);
        drawOfTheShip(1, 13);
        drawOfTheShip(1, 14);
        deactivateTheBoxesInTheFrame();
    }

    private void drawOfTheShip(int shipSize, int shipName) {
        boolean vertical = r.nextBoolean();
        int row, column;
        int limit = 12 - shipSize;
        if (vertical) {  //VERTICAL
            //drawing the ship's starting point
            row = r.nextInt(11 - shipSize) + 1;
            column = r.nextInt(10) + 1;
            //checking fields for a vertical layout

            boolean succeeded = checkingWithVerticalPositionChange(row, limit, column, shipSize, shipName);

            if (!succeeded) {
                drawOfTheShip(shipSize, shipName);
            }

        } else { //HORIZONTAL
            //drawing the ship's starting point
            row = r.nextInt(10) + 1;
            column = r.nextInt(11 - shipSize) + 1;
            //checking fields for a horizontal layout

            boolean succeeded = checkingWithHorizontalPositionChange(row, column, limit, shipSize, shipName);

            if (!succeeded) {
                drawOfTheShip(shipSize, shipName);
            }
        }
    }

    private boolean checkingWithVerticalPositionChange(int row, int limit, int column, int shipSize, int shipName) {
        int counter = 1;
        for (; row < limit; row++) {
            for (; column < 11; column++) {
                if (checkForFreeVertically(row, column, shipSize)) {
                    // the ship setting
                    setTheShipVertically(row, column, shipSize, shipName);
                    return true;
                }
                if (column == 10) {
                    column = 1;
                }

                if (counter == limit * 10) {
                    return false;
                } else {
                    counter++;
                }
            }

            if (row == limit - 1) {
                row = 1;
            }
        }

        return false;
    }

    private boolean checkingWithHorizontalPositionChange(int row, int column, int limit, int shipSize, int shipName) {
        int counter = 1;
        for (; column < limit; column++) {
            for (; row < 11; row++) {
                if (checkForFreeHorizontally(row, column, shipSize)) {
                    //the ship settings
                    setTheShipHorizontally(row, column, shipSize, shipName);
                    return true;
                }
                if (row == 10) {
                    row = 1;
                }

                if (counter == limit * 10) {
                    return false;
                } else {
                    counter++;
                }
            }
            if (column == limit - 1) {
                column = 1;
            }
        }
        return false;
    }

    private boolean checkForFreeVertically(int row, int column, int shipSize) {

        for (int i = (row - 1); i <= (row + shipSize); i++) {
            for (int j = (column - 1); j < (column + 2); j++) {
                if (boardWithShips[i][j] > 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean checkForFreeHorizontally(int row, int column, int shipSize) {

        for (int i = column - 1; i <= (column + shipSize); i++) {
            for (int j = (row - 1); j < (row + 2); j++) {
                if (boardWithShips[j][i] > 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private void setTheShipVertically(int row, int column, int shipSize, int shipName) {

        for (int i = 0; i < shipSize; i++) {
            boardWithShips[row][column] = shipName;
            row++;
        }

    }

    private void setTheShipHorizontally(int row, int column, int shipSize, int shipName) {

        for (int i = 0; i < shipSize; i++) {
            boardWithShips[row][column] = shipName;
            column++;
        }

    }

    private void boardReset(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = 0;
            }
        }
    }

    int shotOfThePlayer(int row, int column) {
        // 3- if hit and sink
        // 2- if hit
        // 1-if not hit
        if (boardWithShips[row][column] > 0) {
            boardForThePlayer[row][column] = 2;
            if (hitAndSinkShip(boardWithShips[row][column])) {
                return 3;
            } else {
                return 2;
            }
        } else {
            boardForThePlayer[row][column] = 1;
            return 1;
        }

    }

    private boolean hitAndSinkShip(int field) {
        for (int row = 1; row < boardWithShips.length; row++) {
            for (int column = 1; column < boardWithShips[row].length; column++) {
                if (boardWithShips[row][column] == field) {
                    if (boardForThePlayer[row][column] != 2) {
                        return false;
                    }
                }
            }
        }
        findFieldOfTheSunkenShip(field);
        return true;
    }

    private void findFieldOfTheSunkenShip(int field) {
        for (int row = 1; row < boardWithShips.length; row++) {
            for (int column = 1; column < boardWithShips[row].length; column++) {
                if (boardWithShips[row][column] == field) {
                    changeEmptyFieldAroundShipField(row, column);

                }
            }
        }
    }

    // the function completes the empty fields around the sunken ship as inactive (setting 1 on the fields where it was 0)
    private void changeEmptyFieldAroundShipField(int row, int column) {
        for (int i = row - 1; i < (row + 2); i++) {
            for (int j = column - 1; j < (column + 2); j++) {
                if (boardForThePlayer[i][j] == 0) {
                    boardForThePlayer[i][j] = 1;
                }
            }
        }
    }

    boolean checkIfFreeField(int row, int column) {
        return boardForThePlayer[row][column] != 1 && boardForThePlayer[row][column] != 2;
    }

    public int getFieldFromBoardForThePlayer(int row, int column) {
        return boardForThePlayer[row][column];
    }


    public int getFieldFromBoardWithShips(int row, int column) {
        return boardWithShips[row][column];
    }

    public int[][] getBoardForThePlayer() {
        return boardForThePlayer;
    }

    private void deactivateTheBoxesInTheFrame() {
        //set a safe value that signifies the end of the computer
        for (int row = 0; row < boardForThePlayer.length; row++) {
            for (int column = 0; column < boardForThePlayer[row].length; column++) {
                if (row > 0 && row < boardWithShips.length - 1) {
                    boardForThePlayer[row][0] = 9;
                    boardWithShips[row][0] = 9;
                    boardForThePlayer[row][boardForThePlayer.length - 1] = 9;
                    boardWithShips[row][boardForThePlayer.length - 1] = 9;
                } else {
                    boardWithShips[row][column] = 9;
                    boardForThePlayer[row][column] = 9;
                }
            }
        }
    }


}
