package pl.weddingapp.battleship;


import java.util.Random;

public class BootLogic {

    private Random r = new Random();

    int[][] availablePointsToShot;
    int rangeOfAvailablePoints;
    private Point point = new Point();
    Direction direction = Direction.UNKNOW;

    public Point randomPoint(int[][] fieldToShot){
        rangeOfAvailablePoints = howManyPointsToShot(fieldToShot);
        getFreePoints(rangeOfAvailablePoints,fieldToShot);

        int randomlySelectedIndex = r.nextInt(rangeOfAvailablePoints);

        return new Point(availablePointsToShot[0][randomlySelectedIndex], availablePointsToShot[1][randomlySelectedIndex]);
    }

    public Point findNextPoint(Point pointHit, int[][] fieldToShot){
        if(direction == Direction.UNKNOW){
            if(fieldToShot[pointHit.getRow()-1][pointHit.getColumn()]==0){ return new Point(pointHit.getRow()-1,pointHit.getColumn());}
            if(fieldToShot[pointHit.getRow()][pointHit.getColumn()+1]==0){return new Point(pointHit.getRow(),pointHit.getColumn()+1);}
            if(fieldToShot[pointHit.getRow()+1][pointHit.getColumn()]==0){ return new Point(pointHit.getRow()+1,pointHit.getColumn());}
            if(fieldToShot[pointHit.getRow()][pointHit.getColumn()-1]==0){ return new Point(pointHit.getRow(),pointHit.getColumn()-1);}
        }
        if(direction == Direction.VERTICAL_UP){
            if(fieldToShot[pointHit.getRow()-1][pointHit.getColumn()]==0){
                return new Point(pointHit.getRow()-1,pointHit.getColumn());
            }else if(fieldToShot[pointHit.getRow()-1][pointHit.getColumn()]==2){
                pointHit.setRow(pointHit.getRow()-1);
                findNextPoint(pointHit, fieldToShot);
            }else{
                direction = Direction.VERTICAL_DOWN;
                findNextPoint(pointHit, fieldToShot);
            }
        }

        if (direction == Direction.VERTICAL_DOWN){
            if(fieldToShot[pointHit.getRow()+1][pointHit.getColumn()]==0){
                return new Point(pointHit.getRow()+1,pointHit.getColumn());
            }else if(fieldToShot[pointHit.getRow()+1][pointHit.getColumn()]==2){
                pointHit.setRow(pointHit.getRow()+1);
                findNextPoint(pointHit, fieldToShot);
            }else{
                direction = Direction.VERTICAL_UP;
                findNextPoint(pointHit, fieldToShot);
            }
        }

        if (direction == Direction.HORIZONTAL_LEFT){
            if(fieldToShot[pointHit.getRow()][pointHit.getColumn()-1]==0){
                return new Point(pointHit.getRow(),pointHit.getColumn()-1);
            }else if(fieldToShot[pointHit.getRow()][pointHit.getColumn()-1]==2){
                pointHit.setColumn(pointHit.getColumn()-1);
                findNextPoint(pointHit, fieldToShot);
            }else{
                direction = Direction.HORIZONTAL_RIGHT;
                findNextPoint(pointHit, fieldToShot);
            }
        }

        if (direction == Direction.HORIZONTAL_RIGHT){
            if(fieldToShot[pointHit.getRow()][pointHit.getColumn()+1]==0){
                return new Point(pointHit.getRow(),pointHit.getColumn()+1);
            }else if(fieldToShot[pointHit.getRow()][pointHit.getColumn()+1]==2){
                pointHit.setColumn(pointHit.getColumn()+1);
                findNextPoint(pointHit, fieldToShot);
            }else{
                direction = Direction.HORIZONTAL_LEFT;
                findNextPoint(pointHit, fieldToShot);
            }
        }

        return findNextPoint(pointHit,fieldToShot);
    }

    private int howManyPointsToShot(int[][] fieldToShot){
        int numberOfAvailablePoints = 0;
        for (int row = 0; row<fieldToShot.length; row++){
            for (int column = 0; column<fieldToShot[row].length; column++){
                if(fieldToShot[row][column]==0){
                    numberOfAvailablePoints++;
                }
            }
        }

        return numberOfAvailablePoints;
    }

    private int[][] getFreePoints(int tabSize, int[][] fieldToShot){
        availablePointsToShot = new int[2][tabSize];
        int index = 0;
        for (int row = 0; row<fieldToShot.length; row++) {
            for (int column = 0; column < fieldToShot[row].length; column++) {
                if (fieldToShot[row][column] == 0) {
                    availablePointsToShot[0][index] = row;
                    availablePointsToShot[1][index] = column;
                    index++;
                }
            }
        }
        return availablePointsToShot;
    }

}