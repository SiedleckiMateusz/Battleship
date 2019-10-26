package pl.weddingapp.battleship;

public class Point {
    private int wiersz, kolumna;


    //konstruktory

    public Point(){}

    public Point(int wiersz, int kolumna){
        this.wiersz = wiersz;
        this.kolumna = kolumna;
    }

    //metody
    public int ktoraCwiartka(){
        int cwiartka=0;

        if(getRow()>0 && getColumn()>0){
            cwiartka=1;
        }else if(getRow()<0 && getColumn()>0){
            cwiartka=2;
        }else if(getRow()<0 && getColumn()<0) {
            cwiartka=3;
        }else if(getRow()>0 && getColumn()<0) {
            cwiartka=4;
        }

        return cwiartka;
    }

    public Point przesunOWektor(Point punkt1, Point wektor){
        punkt1.setRow(punkt1.getRow()+wektor.getRow());
        punkt1.setColumn(punkt1.getColumn()+wektor.getColumn());

        return  punkt1;
    }

    public static float odlegloscMiedzyPunktami(Point punkt1, Point punkt2){
        float odleglosc=0;
        float odlegloscX = (float)punkt1.getRow()-(float)punkt2.getRow();
        float odlegloscY = (float)punkt1.getColumn()-(float)punkt2.getColumn();

        float odlegloscKwadratX = (float) Math.pow(odlegloscX,2);
        float odlegloscKwadratY = (float) Math.pow(odlegloscY,2);

        odleglosc = (float) Math.sqrt(odlegloscKwadratX+odlegloscKwadratY);

        return odleglosc;
    }


    //metody gettery settery toString itd


    @Override
    public boolean equals(Object obj) {
        Point point;

        if(obj instanceof Point){
            point = (Point) obj;
            return (this.wiersz ==point.wiersz && this.kolumna ==point.kolumna);
        }else{
            return false;
        }

    }

    @Override
    public int hashCode() {
        return kolumna + kolumna;
    }

    @Override
    public String toString() {
        return "(WIERSZ=" + wiersz + ", KOLUMNA=" + kolumna +")";
    }

    public int getRow() {
        return wiersz;
    }

    public void setRow(int wiersz) {
        this.wiersz = wiersz;
    }

    public int getColumn() {
        return kolumna;
    }

    public void setColumn(int kolumna) {
        this.kolumna = kolumna;
    }
}