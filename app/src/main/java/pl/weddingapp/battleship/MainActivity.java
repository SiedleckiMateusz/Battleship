package pl.weddingapp.battleship;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickSinglePlayer(View view) {
        Intent intent = new Intent(this, SinglePlayerActivity.class);
        startActivity(intent);
    }

    public void onClickPlayerVsCpu(View view) {
        Intent intent = new Intent(this, PlayerVsCpuActivity.class);
        startActivity(intent);
    }

    public void onClickGameRules(View view) {
    }
}
