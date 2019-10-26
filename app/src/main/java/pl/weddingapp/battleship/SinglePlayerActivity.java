package pl.weddingapp.battleship;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SinglePlayerActivity extends Activity {

    Button[][] pole = new Button[11][11];


    static Drawable PUSTY_KWADRAT;
    static Drawable TRAFIONY;
    static Drawable PUDLO;
    static Drawable STATEK;

    static Drawable STATEK_TRAFIONY;
    static Drawable STATEK_NIETRAFIONY;

    BattleshipGame graUser;

    TextView tvWyswietlKomentarz;
    TextView tvTrafioneStrzaly, tvWszystkieStrzaly, tvAktualnyRekord;
    int trafioneStrzaly = 0, wszystkieStrzaly = 0, pozostaloDoTrafienia = 20, rekord=100;

    TextView tvS11,tvS12, tvS13, tvS14, tvS21_1, tvS21_2, tvS22_1, tvS22_2, tvS23_1, tvS23_2, tvS31_1, tvS31_2, tvS31_3, tvS32_1, tvS32_2, tvS32_3, tvS41_1, tvS41_2, tvS41_3, tvS41_4;

    SharedPreferences sharedPreferences;

    int cheatSchowAllShips = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);



        graUser = new BattleshipGame();

        initializeButtons();

        pobierzRekord();

        aktualizujPlanszeDlaGracza();
    }

    private void initializeButtons(){
        PUSTY_KWADRAT = this.getResources().getDrawable(R.drawable.ic_pusty_kwadrat);
        TRAFIONY = this.getResources().getDrawable(R.drawable.wybor_gracza_trafiony);
        PUDLO = this.getResources().getDrawable(R.drawable.wybor_gracza_pudlo);
        STATEK_TRAFIONY = this.getResources().getDrawable(R.drawable.statek_podglad_trafiony);
        STATEK_NIETRAFIONY = this.getResources().getDrawable(R.drawable.statek_podglad_nietrafiony);
        STATEK = this.getResources().getDrawable(R.drawable.statek);


        tvTrafioneStrzaly = findViewById(R.id.tvTrafioneStrzaly);
        tvWszystkieStrzaly = findViewById(R.id.tvWszystkieStrzaly);
        tvAktualnyRekord = findViewById(R.id.tvAktualnyRekord);

        tvWyswietlKomentarz = findViewById(R.id.tvNazwaGlowna);

        tvS11 = findViewById(R.id.tvStatek11);
        tvS12 = findViewById(R.id.tvStatek12);
        tvS13 = findViewById(R.id.tvStatek13);
        tvS14 = findViewById(R.id.tvStatek14);

        tvS21_1 = findViewById(R.id.tvStatek21_1);
        tvS21_2 = findViewById(R.id.tvStatek21_2);

        tvS22_1 = findViewById(R.id.tvStatek22_1);
        tvS22_2 = findViewById(R.id.tvStatek22_2);

        tvS23_1 = findViewById(R.id.tvStatek23_1);
        tvS23_2 = findViewById(R.id.tvStatek23_2);

        tvS31_1 = findViewById(R.id.tvStatek31_1);
        tvS31_2 = findViewById(R.id.tvStatek31_2);
        tvS31_3 = findViewById(R.id.tvStatek31_3);

        tvS32_1 = findViewById(R.id.tvStatek32_1);
        tvS32_2 = findViewById(R.id.tvStatek32_2);
        tvS32_3 = findViewById(R.id.tvStatek32_3);

        tvS41_1 = findViewById(R.id.tvStatek41_1);
        tvS41_2 = findViewById(R.id.tvStatek41_2);
        tvS41_3 = findViewById(R.id.tvStatek41_3);
        tvS41_4 = findViewById(R.id.tvStatek41_4);

        pole[1][1] = findViewById(R.id.tvW1K1);
        pole[1][2] = findViewById(R.id.tvW1K2);
        pole[1][3] = findViewById(R.id.tvW1K3);
        pole[1][4] = findViewById(R.id.tvW1K4);
        pole[1][5] = findViewById(R.id.tvW1K5);
        pole[1][6] = findViewById(R.id.tvW1K6);
        pole[1][7] = findViewById(R.id.tvW1K7);
        pole[1][8] = findViewById(R.id.tvW1K8);
        pole[1][9] = findViewById(R.id.tvW1K9);
        pole[1][10] = findViewById(R.id.tvW1K10);
        pole[2][1] = findViewById(R.id.tvW2K1);
        pole[2][2] = findViewById(R.id.tvW2K2);
        pole[2][3] = findViewById(R.id.tvW2K3);
        pole[2][4] = findViewById(R.id.tvW2K4);
        pole[2][5] = findViewById(R.id.tvW2K5);
        pole[2][6] = findViewById(R.id.tvW2K6);
        pole[2][7] = findViewById(R.id.tvW2K7);
        pole[2][8] = findViewById(R.id.tvW2K8);
        pole[2][9] = findViewById(R.id.tvW2K9);
        pole[2][10] = findViewById(R.id.tvW2K10);
        pole[3][1] = findViewById(R.id.tvW3K1);
        pole[3][2] = findViewById(R.id.tvW3K2);
        pole[3][3] = findViewById(R.id.tvW3K3);
        pole[3][4] = findViewById(R.id.tvW3K4);
        pole[3][5] = findViewById(R.id.tvW3K5);
        pole[3][6] = findViewById(R.id.tvW3K6);
        pole[3][7] = findViewById(R.id.tvW3K7);
        pole[3][8] = findViewById(R.id.tvW3K8);
        pole[3][9] = findViewById(R.id.tvW3K9);
        pole[3][10] = findViewById(R.id.tvW3K10);
        pole[4][1] = findViewById(R.id.tvW4K1);
        pole[4][2] = findViewById(R.id.tvW4K2);
        pole[4][3] = findViewById(R.id.tvW4K3);
        pole[4][4] = findViewById(R.id.tvW4K4);
        pole[4][5] = findViewById(R.id.tvW4K5);
        pole[4][6] = findViewById(R.id.tvW4K6);
        pole[4][7] = findViewById(R.id.tvW4K7);
        pole[4][8] = findViewById(R.id.tvW4K8);
        pole[4][9] = findViewById(R.id.tvW4K9);
        pole[4][10] = findViewById(R.id.tvW4K10);
        pole[5][1] = findViewById(R.id.tvW5K1);
        pole[5][2] = findViewById(R.id.tvW5K2);
        pole[5][3] = findViewById(R.id.tvW5K3);
        pole[5][4] = findViewById(R.id.tvW5K4);
        pole[5][5] = findViewById(R.id.tvW5K5);
        pole[5][6] = findViewById(R.id.tvW5K6);
        pole[5][7] = findViewById(R.id.tvW5K7);
        pole[5][8] = findViewById(R.id.tvW5K8);
        pole[5][9] = findViewById(R.id.tvW5K9);
        pole[5][10] = findViewById(R.id.tvW5K10);
        pole[6][1] = findViewById(R.id.tvW6K1);
        pole[6][2] = findViewById(R.id.tvW6K2);
        pole[6][3] = findViewById(R.id.tvW6K3);
        pole[6][4] = findViewById(R.id.tvW6K4);
        pole[6][5] = findViewById(R.id.tvW6K5);
        pole[6][6] = findViewById(R.id.tvW6K6);
        pole[6][7] = findViewById(R.id.tvW6K7);
        pole[6][8] = findViewById(R.id.tvW6K8);
        pole[6][9] = findViewById(R.id.tvW6K9);
        pole[6][10] = findViewById(R.id.tvW6K10);
        pole[7][1] = findViewById(R.id.tvW7K1);
        pole[7][2] = findViewById(R.id.tvW7K2);
        pole[7][3] = findViewById(R.id.tvW7K3);
        pole[7][4] = findViewById(R.id.tvW7K4);
        pole[7][5] = findViewById(R.id.tvW7K5);
        pole[7][6] = findViewById(R.id.tvW7K6);
        pole[7][7] = findViewById(R.id.tvW7K7);
        pole[7][8] = findViewById(R.id.tvW7K8);
        pole[7][9] = findViewById(R.id.tvW7K9);
        pole[7][10] = findViewById(R.id.tvW7K10);
        pole[8][1] = findViewById(R.id.tvW8K1);
        pole[8][2] = findViewById(R.id.tvW8K2);
        pole[8][3] = findViewById(R.id.tvW8K3);
        pole[8][4] = findViewById(R.id.tvW8K4);
        pole[8][5] = findViewById(R.id.tvW8K5);
        pole[8][6] = findViewById(R.id.tvW8K6);
        pole[8][7] = findViewById(R.id.tvW8K7);
        pole[8][8] = findViewById(R.id.tvW8K8);
        pole[8][9] = findViewById(R.id.tvW8K9);
        pole[8][10] = findViewById(R.id.tvW8K10);
        pole[9][1] = findViewById(R.id.tvW9K1);
        pole[9][2] = findViewById(R.id.tvW9K2);
        pole[9][3] = findViewById(R.id.tvW9K3);
        pole[9][4] = findViewById(R.id.tvW9K4);
        pole[9][5] = findViewById(R.id.tvW9K5);
        pole[9][6] = findViewById(R.id.tvW9K6);
        pole[9][7] = findViewById(R.id.tvW9K7);
        pole[9][8] = findViewById(R.id.tvW9K8);
        pole[9][9] = findViewById(R.id.tvW9K9);
        pole[9][10] = findViewById(R.id.tvW9K10);
        pole[10][1] = findViewById(R.id.tvW10K1);
        pole[10][2] = findViewById(R.id.tvW10K2);
        pole[10][3] = findViewById(R.id.tvW10K3);
        pole[10][4] = findViewById(R.id.tvW10K4);
        pole[10][5] = findViewById(R.id.tvW10K5);
        pole[10][6] = findViewById(R.id.tvW10K6);
        pole[10][7] = findViewById(R.id.tvW10K7);
        pole[10][8] = findViewById(R.id.tvW10K8);
        pole[10][9] = findViewById(R.id.tvW10K9);
        pole[10][10] = findViewById(R.id.tvW10K10);
    }

    public void onClickKwadracik(View view) {

        switch (view.getId()){
            case R.id.tvW1K1:  ruchZawodnika(1,1); break;
            case R.id.tvW1K2:  ruchZawodnika(1,2); break;
            case R.id.tvW1K3:  ruchZawodnika(1,3); break;
            case R.id.tvW1K4:  ruchZawodnika(1,4); break;
            case R.id.tvW1K5:  ruchZawodnika(1,5); break;
            case R.id.tvW1K6:  ruchZawodnika(1,6); break;
            case R.id.tvW1K7:  ruchZawodnika(1,7); break;
            case R.id.tvW1K8:  ruchZawodnika(1,8); break;
            case R.id.tvW1K9:  ruchZawodnika(1,9); break;
            case R.id.tvW1K10:  ruchZawodnika(1,10); break;

            case R.id.tvW2K1:  ruchZawodnika(2,1); break;
            case R.id.tvW2K2:  ruchZawodnika(2,2); break;
            case R.id.tvW2K3:  ruchZawodnika(2,3); break;
            case R.id.tvW2K4:  ruchZawodnika(2,4); break;
            case R.id.tvW2K5:  ruchZawodnika(2,5); break;
            case R.id.tvW2K6:  ruchZawodnika(2,6); break;
            case R.id.tvW2K7:  ruchZawodnika(2,7); break;
            case R.id.tvW2K8:  ruchZawodnika(2,8); break;
            case R.id.tvW2K9:  ruchZawodnika(2,9); break;
            case R.id.tvW2K10:  ruchZawodnika(2,10); break;

            case R.id.tvW3K1:  ruchZawodnika(3,1); break;
            case R.id.tvW3K2:  ruchZawodnika(3,2); break;
            case R.id.tvW3K3:  ruchZawodnika(3,3); break;
            case R.id.tvW3K4:  ruchZawodnika(3,4); break;
            case R.id.tvW3K5:  ruchZawodnika(3,5); break;
            case R.id.tvW3K6:  ruchZawodnika(3,6); break;
            case R.id.tvW3K7:  ruchZawodnika(3,7); break;
            case R.id.tvW3K8:  ruchZawodnika(3,8); break;
            case R.id.tvW3K9:  ruchZawodnika(3,9); break;
            case R.id.tvW3K10:  ruchZawodnika(3,10); break;

            case R.id.tvW4K1:  ruchZawodnika(4,1); break;
            case R.id.tvW4K2:  ruchZawodnika(4,2); break;
            case R.id.tvW4K3:  ruchZawodnika(4,3); break;
            case R.id.tvW4K4:  ruchZawodnika(4,4); break;
            case R.id.tvW4K5:  ruchZawodnika(4,5); break;
            case R.id.tvW4K6:  ruchZawodnika(4,6); break;
            case R.id.tvW4K7:  ruchZawodnika(4,7); break;
            case R.id.tvW4K8:  ruchZawodnika(4,8); break;
            case R.id.tvW4K9:  ruchZawodnika(4,9); break;
            case R.id.tvW4K10:  ruchZawodnika(4,10); break;

            case R.id.tvW5K1:  ruchZawodnika(5,1); break;
            case R.id.tvW5K2:  ruchZawodnika(5,2); break;
            case R.id.tvW5K3:  ruchZawodnika(5,3); break;
            case R.id.tvW5K4:  ruchZawodnika(5,4); break;
            case R.id.tvW5K5:  ruchZawodnika(5,5); break;
            case R.id.tvW5K6:  ruchZawodnika(5,6); break;
            case R.id.tvW5K7:  ruchZawodnika(5,7); break;
            case R.id.tvW5K8:  ruchZawodnika(5,8); break;
            case R.id.tvW5K9:  ruchZawodnika(5,9); break;
            case R.id.tvW5K10:  ruchZawodnika(5,10); break;

            case R.id.tvW6K1:  ruchZawodnika(6,1); break;
            case R.id.tvW6K2:  ruchZawodnika(6,2); break;
            case R.id.tvW6K3:  ruchZawodnika(6,3); break;
            case R.id.tvW6K4:  ruchZawodnika(6,4); break;
            case R.id.tvW6K5:  ruchZawodnika(6,5); break;
            case R.id.tvW6K6:  ruchZawodnika(6,6); break;
            case R.id.tvW6K7:  ruchZawodnika(6,7); break;
            case R.id.tvW6K8:  ruchZawodnika(6,8); break;
            case R.id.tvW6K9:  ruchZawodnika(6,9); break;
            case R.id.tvW6K10:  ruchZawodnika(6,10); break;

            case R.id.tvW7K1:  ruchZawodnika(7,1); break;
            case R.id.tvW7K2:  ruchZawodnika(7,2); break;
            case R.id.tvW7K3:  ruchZawodnika(7,3); break;
            case R.id.tvW7K4:  ruchZawodnika(7,4); break;
            case R.id.tvW7K5:  ruchZawodnika(7,5); break;
            case R.id.tvW7K6:  ruchZawodnika(7,6); break;
            case R.id.tvW7K7:  ruchZawodnika(7,7); break;
            case R.id.tvW7K8:  ruchZawodnika(7,8); break;
            case R.id.tvW7K9:  ruchZawodnika(7,9); break;
            case R.id.tvW7K10:  ruchZawodnika(7,10); break;

            case R.id.tvW8K1:  ruchZawodnika(8,1); break;
            case R.id.tvW8K2:  ruchZawodnika(8,2); break;
            case R.id.tvW8K4:  ruchZawodnika(8,4); break;
            case R.id.tvW8K3:  ruchZawodnika(8,3); break;
            case R.id.tvW8K5:  ruchZawodnika(8,5); break;
            case R.id.tvW8K6:  ruchZawodnika(8,6); break;
            case R.id.tvW8K7:  ruchZawodnika(8,7); break;
            case R.id.tvW8K8:  ruchZawodnika(8,8); break;
            case R.id.tvW8K9:  ruchZawodnika(8,9); break;
            case R.id.tvW8K10:  ruchZawodnika(8,10); break;

            case R.id.tvW9K1:  ruchZawodnika(9,1); break;
            case R.id.tvW9K2:  ruchZawodnika(9,2); break;
            case R.id.tvW9K3:  ruchZawodnika(9,3); break;
            case R.id.tvW9K4:  ruchZawodnika(9,4); break;
            case R.id.tvW9K5:  ruchZawodnika(9,5); break;
            case R.id.tvW9K6:  ruchZawodnika(9,6); break;
            case R.id.tvW9K7:  ruchZawodnika(9,7); break;
            case R.id.tvW9K8:  ruchZawodnika(9,8); break;
            case R.id.tvW9K9:  ruchZawodnika(9,9); break;
            case R.id.tvW9K10:  ruchZawodnika(9,10); break;

            case R.id.tvW10K1:  ruchZawodnika(10,1); break;
            case R.id.tvW10K2:  ruchZawodnika(10,2); break;
            case R.id.tvW10K3:  ruchZawodnika(10,3); break;
            case R.id.tvW10K4:  ruchZawodnika(10,4); break;
            case R.id.tvW10K5:  ruchZawodnika(10,5); break;
            case R.id.tvW10K6:  ruchZawodnika(10,6); break;
            case R.id.tvW10K7:  ruchZawodnika(10,7); break;
            case R.id.tvW10K8:  ruchZawodnika(10,8); break;
            case R.id.tvW10K9:  ruchZawodnika(10,9); break;
            case R.id.tvW10K10:  ruchZawodnika(10,10); break;
        }
    }

    public void ruchZawodnika(int wiersz, int kolumna){

        if(graUser.checkIfFreeField(wiersz,kolumna)){
            int strzal = graUser.shotOfThePlayer(wiersz,kolumna);

            switch (strzal){
                case 1:
                    aktualizujPlanszeDlaGracza();
                    wszystkieStrzaly++;
                    aktualizujWyniki();
                    tvWyswietlKomentarz.setText("Pudło...");
                    break;
                case 2:
                    aktualizujPlanszeDlaGracza();
                    trafioneStrzaly++;
                    wszystkieStrzaly++;
                    pozostaloDoTrafienia--;
                    aktualizujWyniki();
                    tvWyswietlKomentarz.setText("Trafiony!!!");
                    break;
                case 3:
                    aktualizujPlanszeDlaGracza();
                    trafioneStrzaly++;
                    wszystkieStrzaly++;
                    pozostaloDoTrafienia--;
                    aktualizujWyniki();
                    tvWyswietlKomentarz.setText("Trafiony i zatopiony!!!");
                    zamalujZatopionyStatek(wiersz, kolumna);
                    break;
            }


            Log.i("STATKI", "WYNIKI: trafione "+trafioneStrzaly+" , pozostało do trafienia "+pozostaloDoTrafienia);

            if(pozostaloDoTrafienia==0){
                koniecRozgrywki();
            }
        }
    }

    public void zamalujZatopionyStatek(int wiersz, int kolumna){
        int nazwaStatku = graUser.getFieldFromBoardWithShips(wiersz, kolumna);

        switch (nazwaStatku){
            case 11:
                tvS11.setBackground(STATEK_TRAFIONY);
                break;
            case 12:
                tvS12.setBackground(STATEK_TRAFIONY);
                break;
            case 13:
                tvS13.setBackground(STATEK_TRAFIONY);
                break;
            case 14:
                tvS14.setBackground(STATEK_TRAFIONY);
                break;
            case 21:
                tvS21_1.setBackground(STATEK_TRAFIONY);
                tvS21_2.setBackground(STATEK_TRAFIONY);
                break;
            case 22:
                tvS22_1.setBackground(STATEK_TRAFIONY);
                tvS22_2.setBackground(STATEK_TRAFIONY);
                break;
            case 23:
                tvS23_1.setBackground(STATEK_TRAFIONY);
                tvS23_2.setBackground(STATEK_TRAFIONY);
                break;
            case 31:
                tvS31_1.setBackground(STATEK_TRAFIONY);
                tvS31_2.setBackground(STATEK_TRAFIONY);
                tvS31_3.setBackground(STATEK_TRAFIONY);
                break;
            case 32:
                tvS32_1.setBackground(STATEK_TRAFIONY);
                tvS32_2.setBackground(STATEK_TRAFIONY);
                tvS32_3.setBackground(STATEK_TRAFIONY);
                break;
            case 41:
                tvS41_1.setBackground(STATEK_TRAFIONY);
                tvS41_2.setBackground(STATEK_TRAFIONY);
                tvS41_3.setBackground(STATEK_TRAFIONY);
                tvS41_4.setBackground(STATEK_TRAFIONY);
                break;
        }
    }

    public void wyswietlWylosowaneStatki(){
        for (int wiersz = 1; wiersz < pole.length; wiersz++) {
            for (int kolumna = 1; kolumna < pole[wiersz].length; kolumna++) {
                if(graUser.getFieldFromBoardWithShips(wiersz,kolumna)>0){
                    pole[wiersz][kolumna].setBackground(TRAFIONY);
                }
            }
        }
    }

    public void aktualizujPlanszeDlaGracza(){

        for (int wiersz = 1; wiersz < pole.length; wiersz++){
            for (int kolumna=1; kolumna < pole[wiersz].length; kolumna++){
                switch (graUser.getFieldFromBoardForThePlayer(wiersz,kolumna)){
                    case 0:
                        pole[wiersz][kolumna].setBackground(PUSTY_KWADRAT);
                        break;
                    case 1:
                        pole[wiersz][kolumna].setBackground(PUDLO);
                        break;
                    case 2:
                        pole[wiersz][kolumna].setBackground(TRAFIONY);
                        break;
                }


            }
        }
    }

    public void aktualizujWyniki(){
        tvWszystkieStrzaly.setText(String.valueOf(wszystkieStrzaly));
        tvTrafioneStrzaly.setText(String.valueOf(trafioneStrzaly));
    }

    public void koniecRozgrywki(){
        String message;
        if(rekord>wszystkieStrzaly){
            message = "Użyłeś "+wszystkieStrzaly+" strzałów do zestrzelenia wszystkich statków i jest to nowy rekord. Gratulujemy! Chcesz zagrać jeszcze raz?";
            rekord = wszystkieStrzaly;
            zapiszRekord();
        }else{
            message = "Użyłeś "+wszystkieStrzaly+" strzałów do zestrzelenia wszystkich statków, niestety rekord pozostaje bez zmian. Chcesz zagrać jeszcze raz?";
        }

        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        alertdialog.setTitle("Koniec gry!!!");
        alertdialog.setMessage(message);
        alertdialog.setPositiveButton("TAK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetujPlansze();
                aktualizujWyniki();
            }
        });

        alertdialog.show();


    }

    public void pobierzRekord(){
        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        rekord = sharedPreferences.getInt("rekord", 100);

        tvAktualnyRekord.setText(String.valueOf(rekord));
    }

    public void zapiszRekord(){
        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("rekord",rekord);
        editor.apply();

        tvAktualnyRekord.setText(String.valueOf(rekord));
    }

    public void resetujPlansze(){
        graUser = new BattleshipGame();

        trafioneStrzaly=0;
        wszystkieStrzaly=0;
        pozostaloDoTrafienia=20;

        for (int wiersz =1; wiersz<pole.length; wiersz++){
            for (int kolumna = 1; kolumna<pole[wiersz].length; kolumna++){
                pole[wiersz][kolumna].setBackground(PUSTY_KWADRAT);
            }
        }
        tvWyswietlKomentarz.setText("Statki");

        tvS11.setBackground(STATEK_NIETRAFIONY);
        tvS12.setBackground(STATEK_NIETRAFIONY);
        tvS13.setBackground(STATEK_NIETRAFIONY);
        tvS14.setBackground(STATEK_NIETRAFIONY);
        tvS21_1.setBackground(STATEK_NIETRAFIONY);
        tvS21_2.setBackground(STATEK_NIETRAFIONY);
        tvS22_1.setBackground(STATEK_NIETRAFIONY);
        tvS22_2.setBackground(STATEK_NIETRAFIONY);
        tvS23_1.setBackground(STATEK_NIETRAFIONY);
        tvS23_2.setBackground(STATEK_NIETRAFIONY);
        tvS31_1.setBackground(STATEK_NIETRAFIONY);
        tvS31_2.setBackground(STATEK_NIETRAFIONY);
        tvS31_3.setBackground(STATEK_NIETRAFIONY);
        tvS32_1.setBackground(STATEK_NIETRAFIONY);
        tvS32_2.setBackground(STATEK_NIETRAFIONY);
        tvS32_3.setBackground(STATEK_NIETRAFIONY);
        tvS41_1.setBackground(STATEK_NIETRAFIONY);
        tvS41_2.setBackground(STATEK_NIETRAFIONY);
        tvS41_3.setBackground(STATEK_NIETRAFIONY);
        tvS41_4.setBackground(STATEK_NIETRAFIONY);
    }

    public void onClickReset(View view) {
        resetujPlansze();
    }

    public void onClickShowAllShips(View view) {
        if(cheatSchowAllShips==6 || cheatSchowAllShips==7){
            Toast.makeText(this,"Uważaj bo za chwilę pokażę statki",Toast.LENGTH_SHORT).show();
            cheatSchowAllShips++;
        }else if(cheatSchowAllShips==8){
            Toast.makeText(this, "Pokazano wszystkie statki.",Toast.LENGTH_SHORT).show();
            wyswietlWylosowaneStatki();
            cheatSchowAllShips=0;
        }else{
            cheatSchowAllShips++;
        }
    }

}
