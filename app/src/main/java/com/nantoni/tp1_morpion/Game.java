package com.nantoni.tp1_morpion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Game extends AppCompatActivity {

    private List<ImageButton> buttonGrid = new ArrayList<ImageButton>();
    private int stateGrid[] = new int[9];

    private int winCountPlayerCircle = 0;
    private int winCountPlayerCross = 0;

    private boolean circleTurn = true;
    private boolean ia = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        String mode = getIntent().getExtras().getString("mode");
        if(mode.contains( "pve" ) ){
            ia = true;
        }

        buttonGrid.add((ImageButton) findViewById(R.id.imageButton_0_0));
        buttonGrid.add((ImageButton) findViewById(R.id.imageButton_0_1));
        buttonGrid.add((ImageButton) findViewById(R.id.imageButton_0_2));
        buttonGrid.add((ImageButton) findViewById(R.id.imageButton_1_0));
        buttonGrid.add((ImageButton) findViewById(R.id.imageButton_1_1));
        buttonGrid.add((ImageButton) findViewById(R.id.imageButton_1_2));
        buttonGrid.add((ImageButton) findViewById(R.id.imageButton_2_0));
        buttonGrid.add((ImageButton) findViewById(R.id.imageButton_2_1));
        buttonGrid.add((ImageButton) findViewById(R.id.imageButton_2_2));

        Button btn_pvp = findViewById(R.id.button_replay);
        btn_pvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
            }
        });

        for (ImageButton img_btn : buttonGrid)
        {
            img_btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int index = -1;

                    try {
                        index = Integer.parseInt( ((ImageButton)v).getTag().toString() );
                    }catch (NumberFormatException e){
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    if(index > -1){
                        if( stateGrid[index] == 0){
                            if(circleTurn){
                                ((ImageButton)v).setImageResource(R.drawable.circle);
                                stateGrid[index] = 1;
                                circleTurn = false;
                                ((TextView) findViewById(R.id.textView_playerCircle_Win)).setTextColor(Color.BLACK);
                                ((TextView) findViewById(R.id.textView_playerCross_Win)).setTextColor(Color.RED);
                            }else {
                                stateGrid[index] = 2;
                                ((ImageButton)v).setImageResource(R.drawable.cross);
                                circleTurn = true;
                                ((TextView) findViewById(R.id.textView_playerCircle_Win)).setTextColor(Color.RED);
                                ((TextView) findViewById(R.id.textView_playerCross_Win)).setTextColor(Color.BLACK);
                            }
                            checkWin();
                            if(ia){
                                iaTurn();
                            }
                        }
                    }
                }
            });
        }
    }

    private void iaTurn(){
        int unsetCount = 0;
        for(int i = 0; i < stateGrid.length; i++){
            if(stateGrid[i] == 0){
                unsetCount++;
            }
        }

        int random = new Random().nextInt(unsetCount);

        for(int i = 0; i < stateGrid.length; i++){
            if(stateGrid[i] == 0){
                if(random-- == 0){
                    buttonGrid.get(random).setImageResource(R.drawable.cross);
                    stateGrid[random] = 2;
                    circleTurn = true;
                }
            }
        }
        checkWin();
    }

    // Resets the board's buttons to dot images
    private void resetBoard(){
        for (int i = 0; i < buttonGrid.size(); i++){
            if(stateGrid[i] != 0){
                buttonGrid.get(i).setImageResource(R.drawable.dot);
                stateGrid[i] = 0;
            }
        }
        lockButtons(true);
    }

    // Check if a player has won
    private String winner;
    private void checkWin(){
        winner = "";
              if ((stateGrid[0] == stateGrid[1] && stateGrid[1]== stateGrid[2]) ||
                  (stateGrid[0] == stateGrid[3] && stateGrid[3]== stateGrid[6])){
                    if(stateGrid[0] == 1){
                        winner = "Circle Player";
                        ((TextView) findViewById(R.id.textView_playerCircle_Win)).setText(Integer.toString(++winCountPlayerCircle));
                    } else if (stateGrid[0] == 2){
                        winner = "Cross Player";
                        ((TextView) findViewById(R.id.textView_playerCross_Win)).setText(Integer.toString(++winCountPlayerCross));
                    }
              }
              if ((stateGrid[6] == stateGrid[7] && stateGrid[7]== stateGrid[8]) ||
                  (stateGrid[2] == stateGrid[5] && stateGrid[5]== stateGrid[8])){
                  if(stateGrid[8] == 1){
                      winner = "Circle Player";
                      ((TextView) findViewById(R.id.textView_playerCircle_Win)).setText(Integer.toString(++winCountPlayerCircle));
                  } else if (stateGrid[8] == 2) {
                      winner = "Cross Player";
                      ((TextView) findViewById(R.id.textView_playerCross_Win)).setText(Integer.toString(++winCountPlayerCross));
                  }
              }
              if(
                  (stateGrid[0] == stateGrid[4] && stateGrid[4]== stateGrid[8]) ||
                  (stateGrid[3] == stateGrid[4] && stateGrid[4]== stateGrid[5]) ||
                  (stateGrid[1] == stateGrid[4] && stateGrid[4]== stateGrid[7]) ||
                  (stateGrid[2] == stateGrid[4] && stateGrid[4]== stateGrid[6])) {
                  if(stateGrid[4] == 1){
                      winner = "Circle Player";
                      ((TextView) findViewById(R.id.textView_playerCircle_Win)).setText(Integer.toString(++winCountPlayerCircle));
                  } else if (stateGrid[4] == 2) {
                      winner = "Cross Player";
                      ((TextView) findViewById(R.id.textView_playerCross_Win)).setText(Integer.toString(++winCountPlayerCross));
                  }
              }

              if(!winner.isEmpty()){
                  Toast.makeText(getApplicationContext(), winner + " WON!",Toast.LENGTH_SHORT).show();
                  lockButtons(false);
              }else {
                  int setCount = 0;
                  for(int i = 0; i < stateGrid.length; i++){
                      if(stateGrid[i] != 0){
                          setCount++;
                      }
                  }
                  if(setCount == 9){
                      Toast.makeText(getApplicationContext(), "DRAW!",Toast.LENGTH_SHORT).show();
                      lockButtons(false);
                  }
              }
    }

    private void lockButtons(boolean block){
        for (ImageButton img_btn : buttonGrid)
        {
            img_btn.setClickable(block);
            img_btn.setEnabled(block);
        }
    }
}
