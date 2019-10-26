package pl.weddingapp.battleship;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerVsCpuActivity extends Activity {

    Button[][] poleCpu = new Button[11][11];
    Button[][] poleGracza = new Button[11][11];


    static Drawable PUSTY_KWADRAT;
    static Drawable TRAFIONY;
    static Drawable PUDLO;
    static Drawable STATEK;

    static Drawable STATEK_TRAFIONY;
    static Drawable STATEK_NIETRAFIONY;

    BattleshipGame graGracz, graCpu;
    BootLogic bootLogic = new BootLogic();

    TextView tvWyswietlKomentarz, tvCzyjaKolej;
    TextView tvTrafioneStrzalyGracza, tvTrafioneStrzalyCpu, tvImieGracza;
    int trafioneStrzalyGracza = 0, trafioneStrzalyCpu = 0;

    String imieGracza;

    LinearLayout llPlnaszaGracza, llMenuPrzedRzogrywka;

    TextView tvS11,tvS12, tvS13, tvS14, tvS21_1, tvS21_2, tvS22_1, tvS22_2, tvS23_1, tvS23_2, tvS31_1, tvS31_2, tvS31_3, tvS32_1, tvS32_2, tvS32_3, tvS41_1, tvS41_2, tvS41_3, tvS41_4;
    TextView tvGraczS11,tvGraczS12, tvGraczS13, tvGraczS14, tvGraczS21_1, tvGraczS21_2, tvGraczS22_1, tvGraczS22_2, tvGraczS23_1, tvGraczS23_2, tvGraczS31_1, tvGraczS31_2, tvGraczS31_3, tvGraczS32_1, tvGraczS32_2, tvGraczS32_3, tvGraczS41_1, tvGraczS41_2, tvGraczS41_3, tvGraczS41_4;

    SharedPreferences sharedPreferences;

    private int czyTrafiony = 0;
    private Point punktTrafiony;


    enum KtoTeraz{ CPU, GRACZ}

    private KtoTeraz ktoTeraz = KtoTeraz.GRACZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_vs_cpu);


        initializeButtons();

        pobierzImieGracza();

        przygotowanieNowejRozgrywki();

        poleCpu[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initializeButtons(){
        PUSTY_KWADRAT = this.getResources().getDrawable(R.drawable.ic_pusty_kwadrat);
        TRAFIONY = this.getResources().getDrawable(R.drawable.wybor_gracza_trafiony);
        PUDLO = this.getResources().getDrawable(R.drawable.wybor_gracza_pudlo);
        STATEK_TRAFIONY = this.getResources().getDrawable(R.drawable.statek_podglad_trafiony);
        STATEK_NIETRAFIONY = this.getResources().getDrawable(R.drawable.statek_podglad_nietrafiony);
        STATEK = this.getResources().getDrawable(R.drawable.statek);

        tvWyswietlKomentarz = findViewById(R.id.tvKomentarz);
        tvCzyjaKolej = findViewById(R.id.tvCzyjruch);

        llPlnaszaGracza = findViewById(R.id.llPlanszaDoStrzelania);
        llMenuPrzedRzogrywka = findViewById(R.id.llMenuPrzedRozgrywka);


        tvTrafioneStrzalyGracza = findViewById(R.id.tvPunktyZawodnika);
        tvImieGracza = findViewById(R.id.tvNazwaZawodnika);

        tvS11 = findViewById(R.id.tvStatekCpu11);
        tvS12 = findViewById(R.id.tvStatekCpu12);
        tvS13 = findViewById(R.id.tvStatekCpu13);
        tvS14 = findViewById(R.id.tvStatekCpu14);

        tvS21_1 = findViewById(R.id.tvStatekCpu21_1);
        tvS21_2 = findViewById(R.id.tvStatekCpu21_2);

        tvS22_1 = findViewById(R.id.tvStatekCpu22_1);
        tvS22_2 = findViewById(R.id.tvStatekCpu22_2);

        tvS23_1 = findViewById(R.id.tvStatekCpu23_1);
        tvS23_2 = findViewById(R.id.tvStatekCpu23_2);

        tvS31_1 = findViewById(R.id.tvStatekCpu31_1);
        tvS31_2 = findViewById(R.id.tvStatekCpu31_2);
        tvS31_3 = findViewById(R.id.tvStatekCpu31_3);

        tvS32_1 = findViewById(R.id.tvStatekCpu32_1);
        tvS32_2 = findViewById(R.id.tvStatekCpu32_2);
        tvS32_3 = findViewById(R.id.tvStatekCpu32_3);

        tvS41_1 = findViewById(R.id.tvStatekCpu41_1);
        tvS41_2 = findViewById(R.id.tvStatekCpu41_2);
        tvS41_3 = findViewById(R.id.tvStatekCpu41_3);
        tvS41_4 = findViewById(R.id.tvStatekCpu41_4);

        poleCpu[1][1] = findViewById(R.id.tvW1K1);
        poleCpu[1][2] = findViewById(R.id.tvW1K2);
        poleCpu[1][3] = findViewById(R.id.tvW1K3);
        poleCpu[1][4] = findViewById(R.id.tvW1K4);
        poleCpu[1][5] = findViewById(R.id.tvW1K5);
        poleCpu[1][6] = findViewById(R.id.tvW1K6);
        poleCpu[1][7] = findViewById(R.id.tvW1K7);
        poleCpu[1][8] = findViewById(R.id.tvW1K8);
        poleCpu[1][9] = findViewById(R.id.tvW1K9);
        poleCpu[1][10] = findViewById(R.id.tvW1K10);
        poleCpu[2][1] = findViewById(R.id.tvW2K1);
        poleCpu[2][2] = findViewById(R.id.tvW2K2);
        poleCpu[2][3] = findViewById(R.id.tvW2K3);
        poleCpu[2][4] = findViewById(R.id.tvW2K4);
        poleCpu[2][5] = findViewById(R.id.tvW2K5);
        poleCpu[2][6] = findViewById(R.id.tvW2K6);
        poleCpu[2][7] = findViewById(R.id.tvW2K7);
        poleCpu[2][8] = findViewById(R.id.tvW2K8);
        poleCpu[2][9] = findViewById(R.id.tvW2K9);
        poleCpu[2][10] = findViewById(R.id.tvW2K10);
        poleCpu[3][1] = findViewById(R.id.tvW3K1);
        poleCpu[3][2] = findViewById(R.id.tvW3K2);
        poleCpu[3][3] = findViewById(R.id.tvW3K3);
        poleCpu[3][4] = findViewById(R.id.tvW3K4);
        poleCpu[3][5] = findViewById(R.id.tvW3K5);
        poleCpu[3][6] = findViewById(R.id.tvW3K6);
        poleCpu[3][7] = findViewById(R.id.tvW3K7);
        poleCpu[3][8] = findViewById(R.id.tvW3K8);
        poleCpu[3][9] = findViewById(R.id.tvW3K9);
        poleCpu[3][10] = findViewById(R.id.tvW3K10);
        poleCpu[4][1] = findViewById(R.id.tvW4K1);
        poleCpu[4][2] = findViewById(R.id.tvW4K2);
        poleCpu[4][3] = findViewById(R.id.tvW4K3);
        poleCpu[4][4] = findViewById(R.id.tvW4K4);
        poleCpu[4][5] = findViewById(R.id.tvW4K5);
        poleCpu[4][6] = findViewById(R.id.tvW4K6);
        poleCpu[4][7] = findViewById(R.id.tvW4K7);
        poleCpu[4][8] = findViewById(R.id.tvW4K8);
        poleCpu[4][9] = findViewById(R.id.tvW4K9);
        poleCpu[4][10] = findViewById(R.id.tvW4K10);
        poleCpu[5][1] = findViewById(R.id.tvW5K1);
        poleCpu[5][2] = findViewById(R.id.tvW5K2);
        poleCpu[5][3] = findViewById(R.id.tvW5K3);
        poleCpu[5][4] = findViewById(R.id.tvW5K4);
        poleCpu[5][5] = findViewById(R.id.tvW5K5);
        poleCpu[5][6] = findViewById(R.id.tvW5K6);
        poleCpu[5][7] = findViewById(R.id.tvW5K7);
        poleCpu[5][8] = findViewById(R.id.tvW5K8);
        poleCpu[5][9] = findViewById(R.id.tvW5K9);
        poleCpu[5][10] = findViewById(R.id.tvW5K10);
        poleCpu[6][1] = findViewById(R.id.tvW6K1);
        poleCpu[6][2] = findViewById(R.id.tvW6K2);
        poleCpu[6][3] = findViewById(R.id.tvW6K3);
        poleCpu[6][4] = findViewById(R.id.tvW6K4);
        poleCpu[6][5] = findViewById(R.id.tvW6K5);
        poleCpu[6][6] = findViewById(R.id.tvW6K6);
        poleCpu[6][7] = findViewById(R.id.tvW6K7);
        poleCpu[6][8] = findViewById(R.id.tvW6K8);
        poleCpu[6][9] = findViewById(R.id.tvW6K9);
        poleCpu[6][10] = findViewById(R.id.tvW6K10);
        poleCpu[7][1] = findViewById(R.id.tvW7K1);
        poleCpu[7][2] = findViewById(R.id.tvW7K2);
        poleCpu[7][3] = findViewById(R.id.tvW7K3);
        poleCpu[7][4] = findViewById(R.id.tvW7K4);
        poleCpu[7][5] = findViewById(R.id.tvW7K5);
        poleCpu[7][6] = findViewById(R.id.tvW7K6);
        poleCpu[7][7] = findViewById(R.id.tvW7K7);
        poleCpu[7][8] = findViewById(R.id.tvW7K8);
        poleCpu[7][9] = findViewById(R.id.tvW7K9);
        poleCpu[7][10] = findViewById(R.id.tvW7K10);
        poleCpu[8][1] = findViewById(R.id.tvW8K1);
        poleCpu[8][2] = findViewById(R.id.tvW8K2);
        poleCpu[8][3] = findViewById(R.id.tvW8K3);
        poleCpu[8][4] = findViewById(R.id.tvW8K4);
        poleCpu[8][5] = findViewById(R.id.tvW8K5);
        poleCpu[8][6] = findViewById(R.id.tvW8K6);
        poleCpu[8][7] = findViewById(R.id.tvW8K7);
        poleCpu[8][8] = findViewById(R.id.tvW8K8);
        poleCpu[8][9] = findViewById(R.id.tvW8K9);
        poleCpu[8][10] = findViewById(R.id.tvW8K10);
        poleCpu[9][1] = findViewById(R.id.tvW9K1);
        poleCpu[9][2] = findViewById(R.id.tvW9K2);
        poleCpu[9][3] = findViewById(R.id.tvW9K3);
        poleCpu[9][4] = findViewById(R.id.tvW9K4);
        poleCpu[9][5] = findViewById(R.id.tvW9K5);
        poleCpu[9][6] = findViewById(R.id.tvW9K6);
        poleCpu[9][7] = findViewById(R.id.tvW9K7);
        poleCpu[9][8] = findViewById(R.id.tvW9K8);
        poleCpu[9][9] = findViewById(R.id.tvW9K9);
        poleCpu[9][10] = findViewById(R.id.tvW9K10);
        poleCpu[10][1] = findViewById(R.id.tvW10K1);
        poleCpu[10][2] = findViewById(R.id.tvW10K2);
        poleCpu[10][3] = findViewById(R.id.tvW10K3);
        poleCpu[10][4] = findViewById(R.id.tvW10K4);
        poleCpu[10][5] = findViewById(R.id.tvW10K5);
        poleCpu[10][6] = findViewById(R.id.tvW10K6);
        poleCpu[10][7] = findViewById(R.id.tvW10K7);
        poleCpu[10][8] = findViewById(R.id.tvW10K8);
        poleCpu[10][9] = findViewById(R.id.tvW10K9);
        poleCpu[10][10] = findViewById(R.id.tvW10K10);




        tvTrafioneStrzalyCpu = findViewById(R.id.tvPunktyCpu);

        tvGraczS11 = findViewById(R.id.tvStatekGracza11);
        tvGraczS12 = findViewById(R.id.tvStatekGracza12);
        tvGraczS13 = findViewById(R.id.tvStatekGracza13);
        tvGraczS14 = findViewById(R.id.tvStatekGracza14);

        tvGraczS21_1 = findViewById(R.id.tvStatekGracza21_1);
        tvGraczS21_2 = findViewById(R.id.tvStatekGracza21_2);

        tvGraczS22_1 = findViewById(R.id.tvStatekGracza22_1);
        tvGraczS22_2 = findViewById(R.id.tvStatekGracza22_2);

        tvGraczS23_1 = findViewById(R.id.tvStatekGracza23_1);
        tvGraczS23_2 = findViewById(R.id.tvStatekGracza23_2);

        tvGraczS31_1 = findViewById(R.id.tvStatekGracza31_1);
        tvGraczS31_2 = findViewById(R.id.tvStatekGracza31_2);
        tvGraczS31_3 = findViewById(R.id.tvStatekGracza31_3);

        tvGraczS32_1 = findViewById(R.id.tvStatekGracza32_1);
        tvGraczS32_2 = findViewById(R.id.tvStatekGracza32_2);
        tvGraczS32_3 = findViewById(R.id.tvStatekGracza32_3);

        tvGraczS41_1 = findViewById(R.id.tvStatekGracza41_1);
        tvGraczS41_2 = findViewById(R.id.tvStatekGracza41_2);
        tvGraczS41_3 = findViewById(R.id.tvStatekGracza41_3);
        tvGraczS41_4 = findViewById(R.id.tvStatekGracza41_4);

        poleGracza[1][1] = findViewById(R.id.tvPodgladW1K1);
        poleGracza[1][2] = findViewById(R.id.tvPodgladW1K2);
        poleGracza[1][3] = findViewById(R.id.tvPodgladW1K3);
        poleGracza[1][4] = findViewById(R.id.tvPodgladW1K4);
        poleGracza[1][5] = findViewById(R.id.tvPodgladW1K5);
        poleGracza[1][6] = findViewById(R.id.tvPodgladW1K6);
        poleGracza[1][7] = findViewById(R.id.tvPodgladW1K7);
        poleGracza[1][8] = findViewById(R.id.tvPodgladW1K8);
        poleGracza[1][9] = findViewById(R.id.tvPodgladW1K9);
        poleGracza[1][10] = findViewById(R.id.tvPodgladW1K10);
        poleGracza[2][1] = findViewById(R.id.tvPodgladW2K1);
        poleGracza[2][2] = findViewById(R.id.tvPodgladW2K2);
        poleGracza[2][3] = findViewById(R.id.tvPodgladW2K3);
        poleGracza[2][4] = findViewById(R.id.tvPodgladW2K4);
        poleGracza[2][5] = findViewById(R.id.tvPodgladW2K5);
        poleGracza[2][6] = findViewById(R.id.tvPodgladW2K6);
        poleGracza[2][7] = findViewById(R.id.tvPodgladW2K7);
        poleGracza[2][8] = findViewById(R.id.tvPodgladW2K8);
        poleGracza[2][9] = findViewById(R.id.tvPodgladW2K9);
        poleGracza[2][10] = findViewById(R.id.tvPodgladW2K10);
        poleGracza[3][1] = findViewById(R.id.tvPodgladW3K1);
        poleGracza[3][2] = findViewById(R.id.tvPodgladW3K2);
        poleGracza[3][3] = findViewById(R.id.tvPodgladW3K3);
        poleGracza[3][4] = findViewById(R.id.tvPodgladW3K4);
        poleGracza[3][5] = findViewById(R.id.tvPodgladW3K5);
        poleGracza[3][6] = findViewById(R.id.tvPodgladW3K6);
        poleGracza[3][7] = findViewById(R.id.tvPodgladW3K7);
        poleGracza[3][8] = findViewById(R.id.tvPodgladW3K8);
        poleGracza[3][9] = findViewById(R.id.tvPodgladW3K9);
        poleGracza[3][10] = findViewById(R.id.tvPodgladW3K10);
        poleGracza[4][1] = findViewById(R.id.tvPodgladW4K1);
        poleGracza[4][2] = findViewById(R.id.tvPodgladW4K2);
        poleGracza[4][3] = findViewById(R.id.tvPodgladW4K3);
        poleGracza[4][4] = findViewById(R.id.tvPodgladW4K4);
        poleGracza[4][5] = findViewById(R.id.tvPodgladW4K5);
        poleGracza[4][6] = findViewById(R.id.tvPodgladW4K6);
        poleGracza[4][7] = findViewById(R.id.tvPodgladW4K7);
        poleGracza[4][8] = findViewById(R.id.tvPodgladW4K8);
        poleGracza[4][9] = findViewById(R.id.tvPodgladW4K9);
        poleGracza[4][10] = findViewById(R.id.tvPodgladW4K10);
        poleGracza[5][1] = findViewById(R.id.tvPodgladW5K1);
        poleGracza[5][2] = findViewById(R.id.tvPodgladW5K2);
        poleGracza[5][3] = findViewById(R.id.tvPodgladW5K3);
        poleGracza[5][4] = findViewById(R.id.tvPodgladW5K4);
        poleGracza[5][5] = findViewById(R.id.tvPodgladW5K5);
        poleGracza[5][6] = findViewById(R.id.tvPodgladW5K6);
        poleGracza[5][7] = findViewById(R.id.tvPodgladW5K7);
        poleGracza[5][8] = findViewById(R.id.tvPodgladW5K8);
        poleGracza[5][9] = findViewById(R.id.tvPodgladW5K9);
        poleGracza[5][10] = findViewById(R.id.tvPodgladW5K10);
        poleGracza[6][1] = findViewById(R.id.tvPodgladW6K1);
        poleGracza[6][2] = findViewById(R.id.tvPodgladW6K2);
        poleGracza[6][3] = findViewById(R.id.tvPodgladW6K3);
        poleGracza[6][4] = findViewById(R.id.tvPodgladW6K4);
        poleGracza[6][5] = findViewById(R.id.tvPodgladW6K5);
        poleGracza[6][6] = findViewById(R.id.tvPodgladW6K6);
        poleGracza[6][7] = findViewById(R.id.tvPodgladW6K7);
        poleGracza[6][8] = findViewById(R.id.tvPodgladW6K8);
        poleGracza[6][9] = findViewById(R.id.tvPodgladW6K9);
        poleGracza[6][10] = findViewById(R.id.tvPodgladW6K10);
        poleGracza[7][1] = findViewById(R.id.tvPodgladW7K1);
        poleGracza[7][2] = findViewById(R.id.tvPodgladW7K2);
        poleGracza[7][3] = findViewById(R.id.tvPodgladW7K3);
        poleGracza[7][4] = findViewById(R.id.tvPodgladW7K4);
        poleGracza[7][5] = findViewById(R.id.tvPodgladW7K5);
        poleGracza[7][6] = findViewById(R.id.tvPodgladW7K6);
        poleGracza[7][7] = findViewById(R.id.tvPodgladW7K7);
        poleGracza[7][8] = findViewById(R.id.tvPodgladW7K8);
        poleGracza[7][9] = findViewById(R.id.tvPodgladW7K9);
        poleGracza[7][10] = findViewById(R.id.tvPodgladW7K10);
        poleGracza[8][1] = findViewById(R.id.tvPodgladW8K1);
        poleGracza[8][2] = findViewById(R.id.tvPodgladW8K2);
        poleGracza[8][3] = findViewById(R.id.tvPodgladW8K3);
        poleGracza[8][4] = findViewById(R.id.tvPodgladW8K4);
        poleGracza[8][5] = findViewById(R.id.tvPodgladW8K5);
        poleGracza[8][6] = findViewById(R.id.tvPodgladW8K6);
        poleGracza[8][7] = findViewById(R.id.tvPodgladW8K7);
        poleGracza[8][8] = findViewById(R.id.tvPodgladW8K8);
        poleGracza[8][9] = findViewById(R.id.tvPodgladW8K9);
        poleGracza[8][10] = findViewById(R.id.tvPodgladW8K10);
        poleGracza[9][1] = findViewById(R.id.tvPodgladW9K1);
        poleGracza[9][2] = findViewById(R.id.tvPodgladW9K2);
        poleGracza[9][3] = findViewById(R.id.tvPodgladW9K3);
        poleGracza[9][4] = findViewById(R.id.tvPodgladW9K4);
        poleGracza[9][5] = findViewById(R.id.tvPodgladW9K5);
        poleGracza[9][6] = findViewById(R.id.tvPodgladW9K6);
        poleGracza[9][7] = findViewById(R.id.tvPodgladW9K7);
        poleGracza[9][8] = findViewById(R.id.tvPodgladW9K8);
        poleGracza[9][9] = findViewById(R.id.tvPodgladW9K9);
        poleGracza[9][10] = findViewById(R.id.tvPodgladW9K10);
        poleGracza[10][1] = findViewById(R.id.tvPodgladW10K1);
        poleGracza[10][2] = findViewById(R.id.tvPodgladW10K2);
        poleGracza[10][3] = findViewById(R.id.tvPodgladW10K3);
        poleGracza[10][4] = findViewById(R.id.tvPodgladW10K4);
        poleGracza[10][5] = findViewById(R.id.tvPodgladW10K5);
        poleGracza[10][6] = findViewById(R.id.tvPodgladW10K6);
        poleGracza[10][7] = findViewById(R.id.tvPodgladW10K7);
        poleGracza[10][8] = findViewById(R.id.tvPodgladW10K8);
        poleGracza[10][9] = findViewById(R.id.tvPodgladW10K9);
        poleGracza[10][10] = findViewById(R.id.tvPodgladW10K10);
    }

    private void pobierzImieGracza(){
        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        imieGracza = sharedPreferences.getString("imieGracza","You");

        tvImieGracza.setText(imieGracza);
    }

    private void zapiszImieGracza(String imie){
        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("imieGracza", imie);
        editor.apply();


    }

    public void onClickLosujStatki(View view) {
        graGracz = new BattleshipGame();
        aktualizujPlansze(poleGracza, graGracz, true);
    }

    public void onClickZmienImie(View view) {

        androidx.appcompat.app.AlertDialog.Builder alert = new androidx.appcompat.app.AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);

        alert.setTitle("ZMIEŃ IMIĘ");
        alert.setView(edittext);
        edittext.setHint("Podaj swoje imię");


        alert.setPositiveButton("Zmień", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String imie = edittext.getText().toString();
                if(imie.equals("")){
                    imie = "You";
                }else{
                    tvImieGracza.setText(imie);
                    zapiszImieGracza(imie);
                }

            }
        });

        alert.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.show();


    }

    public void onClickRozpocznijGre(View view) {
        //UKRYJ/POKAŻ ELEMENTY
        llPlnaszaGracza.setVisibility(View.VISIBLE);
        llMenuPrzedRzogrywka.setVisibility(View.GONE);
    }

    public void zamalujZatopionyStatekGracza(int wiersz, int kolumna){
        int nazwaStatku = graGracz.getFieldFromBoardWithShips(wiersz, kolumna);

        switch (nazwaStatku){
            case 11:
                tvGraczS11.setBackground(STATEK_TRAFIONY);
                break;
            case 12:
                tvGraczS12.setBackground(STATEK_TRAFIONY);
                break;
            case 13:
                tvGraczS13.setBackground(STATEK_TRAFIONY);
                break;
            case 14:
                tvGraczS14.setBackground(STATEK_TRAFIONY);
                break;
            case 21:
                tvGraczS21_1.setBackground(STATEK_TRAFIONY);
                tvGraczS21_2.setBackground(STATEK_TRAFIONY);
                break;
            case 22:
                tvGraczS22_1.setBackground(STATEK_TRAFIONY);
                tvGraczS22_2.setBackground(STATEK_TRAFIONY);
                break;
            case 23:
                tvGraczS23_1.setBackground(STATEK_TRAFIONY);
                tvGraczS23_2.setBackground(STATEK_TRAFIONY);
                break;
            case 31:
                tvGraczS31_1.setBackground(STATEK_TRAFIONY);
                tvGraczS31_2.setBackground(STATEK_TRAFIONY);
                tvGraczS31_3.setBackground(STATEK_TRAFIONY);
                break;
            case 32:
                tvGraczS32_1.setBackground(STATEK_TRAFIONY);
                tvGraczS32_2.setBackground(STATEK_TRAFIONY);
                tvGraczS32_3.setBackground(STATEK_TRAFIONY);
                break;
            case 41:
                tvGraczS41_1.setBackground(STATEK_TRAFIONY);
                tvGraczS41_2.setBackground(STATEK_TRAFIONY);
                tvGraczS41_3.setBackground(STATEK_TRAFIONY);
                tvGraczS41_4.setBackground(STATEK_TRAFIONY);
                break;
        }
    }

    public void zamalujZatopionyStatekCpu(int wiersz, int kolumna){
        int nazwaStatku = graCpu.getFieldFromBoardWithShips(wiersz, kolumna);

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

    public void aktualizujPlansze(Button[][] pole, BattleshipGame gra, boolean planszaPodglad){

        for (int wiersz = 1; wiersz < pole.length; wiersz++){
            for (int kolumna=1; kolumna < pole[wiersz].length; kolumna++){

                switch (gra.getFieldFromBoardForThePlayer(wiersz,kolumna)){
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

                if(planszaPodglad && gra.getFieldFromBoardWithShips(wiersz,kolumna)>10 && gra.getFieldFromBoardForThePlayer(wiersz,kolumna)==0){
                    pole[wiersz][kolumna].setBackground(STATEK);
                }

            }
        }
    }

    private void wyczyscPodgladFlotyIPlansze(){
        for (int wiersz = 1; wiersz< poleCpu.length; wiersz++){
            for (int kolumna = 1; kolumna< poleCpu[wiersz].length; kolumna++){
                poleCpu[wiersz][kolumna].setBackground(PUSTY_KWADRAT);
                poleGracza[wiersz][kolumna].setBackground(PUSTY_KWADRAT);
            }
        }


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

        tvGraczS11.setBackground(STATEK_NIETRAFIONY);
        tvGraczS12.setBackground(STATEK_NIETRAFIONY);
        tvGraczS13.setBackground(STATEK_NIETRAFIONY);
        tvGraczS14.setBackground(STATEK_NIETRAFIONY);
        tvGraczS21_1.setBackground(STATEK_NIETRAFIONY);
        tvGraczS21_2.setBackground(STATEK_NIETRAFIONY);
        tvGraczS22_1.setBackground(STATEK_NIETRAFIONY);
        tvGraczS22_2.setBackground(STATEK_NIETRAFIONY);
        tvGraczS23_1.setBackground(STATEK_NIETRAFIONY);
        tvGraczS23_2.setBackground(STATEK_NIETRAFIONY);
        tvGraczS31_1.setBackground(STATEK_NIETRAFIONY);
        tvGraczS31_2.setBackground(STATEK_NIETRAFIONY);
        tvGraczS31_3.setBackground(STATEK_NIETRAFIONY);
        tvGraczS32_1.setBackground(STATEK_NIETRAFIONY);
        tvGraczS32_2.setBackground(STATEK_NIETRAFIONY);
        tvGraczS32_3.setBackground(STATEK_NIETRAFIONY);
        tvGraczS41_1.setBackground(STATEK_NIETRAFIONY);
        tvGraczS41_2.setBackground(STATEK_NIETRAFIONY);
        tvGraczS41_3.setBackground(STATEK_NIETRAFIONY);
        tvGraczS41_4.setBackground(STATEK_NIETRAFIONY);


    }

    private void przygotowanieNowejRozgrywki(){
        //UKRYJ/POKAŻ ELEMENTY
        llPlnaszaGracza.setVisibility(View.GONE);
        llMenuPrzedRzogrywka.setVisibility(View.VISIBLE);

        trafioneStrzalyGracza = 0;
        trafioneStrzalyCpu = 0;

        wyczyscPodgladFlotyIPlansze();

        graCpu = new BattleshipGame();
        graGracz = new BattleshipGame();


        aktualizujPlansze(poleGracza, graGracz, true);
    }

    public void aktualizujInfo(String czyjaKolej, String komentarz){
        tvCzyjaKolej.setText(String.valueOf(czyjaKolej));
        tvWyswietlKomentarz.setText(String.valueOf(komentarz));
        tvTrafioneStrzalyCpu.setText(String.valueOf(trafioneStrzalyCpu));
        tvTrafioneStrzalyGracza.setText(String.valueOf(trafioneStrzalyGracza));
    }

    public void koniecRozgrywki(){
        String message;
        if(trafioneStrzalyGracza>trafioneStrzalyCpu){
            message = "Wygrałeś rozgrywkę "+trafioneStrzalyGracza+":"+trafioneStrzalyCpu+". Gratulujemy! Chcesz zagrać jeszcze raz?";
        }else{
            message = "Mr. Always Ready wygrał rozgrywkę "+trafioneStrzalyGracza+":"+trafioneStrzalyCpu+". Chcesz zagrać jeszcze raz?";
        }

        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        alertdialog.setTitle("Koniec gry!!!");
        alertdialog.setMessage(message);
        alertdialog.setPositiveButton("TAK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                przygotowanieNowejRozgrywki();
            }
        });
        alertdialog.setNegativeButton("NIE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertdialog.show();
    }

    private void ruchCpu(){
        Point aktualnyPunkt;


        if(czyTrafiony!=0){
            aktualnyPunkt = bootLogic.findNextPoint(punktTrafiony, graGracz.boardForThePlayer);
        }else{
            aktualnyPunkt = bootLogic.randomPoint(graGracz.boardForThePlayer);
        }

        if(graGracz.checkIfFreeField(aktualnyPunkt.getRow(),aktualnyPunkt.getColumn())){
            int strzal = graGracz.shotOfThePlayer(aktualnyPunkt.getRow(), aktualnyPunkt.getColumn());

            aktualizujPlansze(poleGracza, graGracz, true);

            switch (strzal){
                case 1:
                    aktualizujInfo("Twój ruch", "Pudło...");
                    ktoTeraz = KtoTeraz.GRACZ;
                    break;
                case 2:
                    trafioneStrzalyCpu++;
                    aktualizujInfo("Ruch Mr. Always Ready", "Trafiony!!!");
                    czyTrafiony++;

                    if(czyTrafiony==2){
                        if(punktTrafiony.getColumn()> aktualnyPunkt.getColumn()){ bootLogic.direction = Direction.HORIZONTAL_LEFT; }
                        if(punktTrafiony.getColumn()< aktualnyPunkt.getColumn()){ bootLogic.direction = Direction.HORIZONTAL_RIGHT; }
                        if(punktTrafiony.getRow()< aktualnyPunkt.getRow()){ bootLogic.direction = Direction.VERTICAL_DOWN; }
                        if(punktTrafiony.getRow()> aktualnyPunkt.getRow()){ bootLogic.direction = Direction.VERTICAL_UP; }
                    }

                    punktTrafiony = new Point(aktualnyPunkt.getRow(), aktualnyPunkt.getColumn());
                    ruchCpu();
                    break;
                case 3:
                    trafioneStrzalyCpu++;
                    aktualizujInfo("Ruch Mr. Always Ready", "Trafiony i zatopiony!!!");
                    czyTrafiony=0;
                    punktTrafiony = null;
                    bootLogic.direction = Direction.UNKNOW;
                    zamalujZatopionyStatekGracza(aktualnyPunkt.getRow(),aktualnyPunkt.getColumn());

                    ruchCpu();
                    break;
            }
        }
    }

    public void ruchZawodnika(int wiersz, int kolumna){
        if(ktoTeraz == KtoTeraz.GRACZ){
            if(graCpu.checkIfFreeField(wiersz,kolumna)){
                int strzal = graCpu.shotOfThePlayer(wiersz,kolumna);

                switch (strzal){
                    case 1:
                        aktualizujPlansze(poleCpu, graCpu, false);
                        aktualizujInfo("Ruch Mr. Always Ready", "Pudło...");
                        ktoTeraz = KtoTeraz.CPU;
                        ruchCpu();
                        break;
                    case 2:
                        aktualizujPlansze(poleCpu, graCpu, false);
                        trafioneStrzalyGracza++;
                        aktualizujInfo("Twój ruch", "Trafiony!!!");
                        break;
                    case 3:
                        aktualizujPlansze(poleCpu, graCpu, false);
                        trafioneStrzalyGracza++;
                        aktualizujInfo("Twój ruch", "Trafiony i zatopiony!!!");
                        zamalujZatopionyStatekCpu(wiersz, kolumna);
                        break;
                }


                if(trafioneStrzalyCpu==20 ||trafioneStrzalyGracza==20){
                    koniecRozgrywki();
                }
            }
        }else{
            Toast.makeText(this, "Teraz ruch Mr Always Ready",Toast.LENGTH_SHORT).show();
        }


    }

    public void onClickRuchGracza(View view) {
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

}
