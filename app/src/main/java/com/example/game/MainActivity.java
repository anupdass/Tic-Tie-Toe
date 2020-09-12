package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0: is yellow and 1: is red 2: empty

    int activeplayer = 0;
    int []gmstate = {2,2,2,2,2,2,2,2,2};
    int [][] winnerposition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,7},{0,4,8},{2,4,6}};

    boolean gameWinner = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropImage(View view) {

        ImageView counter = (ImageView) view;

        int tapcount = Integer.parseInt(counter.getTag().toString());

        if(gmstate[tapcount]==2 && gameWinner) {
            gmstate[tapcount] = activeplayer;

            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.image2);
                activeplayer = 0;
            }
            for (int[] winnerposition : winnerposition) {
                String winner = "";
                if (gmstate[winnerposition[0]] == gmstate[winnerposition[1]] && gmstate[winnerposition[1]] == gmstate[winnerposition[2]]
                        && gmstate[winnerposition[0]] != 2) {
                    gameWinner = false;
                    if (activeplayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    TextView textView = findViewById(R.id.winnerText);
                    Button btn = findViewById(R.id.retry);
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("Winner: "+winner);
                    btn.setVisibility(View.VISIBLE);
                }

            }
        }
    }

    public void retry(View view) {
        startActivity(new Intent(MainActivity.this,MainActivity.class));
    }
}