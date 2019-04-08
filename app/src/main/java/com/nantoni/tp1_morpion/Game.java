package com.nantoni.tp1_morpion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game extends AppCompatActivity {

    List<ImageButton> buttonGrid = new ArrayList<ImageButton>();
    int stateGrid[] = new int[9];

    private boolean circleTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        buttonGrid.add((ImageButton) findViewById(R.id.imageButton_0_0));
        buttonGrid.add((ImageButton) findViewById(R.id.imageButton_0_1));
        buttonGrid.add((ImageButton) findViewById(R.id.imageButton_0_2));
        buttonGrid.add((ImageButton) findViewById(R.id.imageButton_1_0));
        buttonGrid.add((ImageButton) findViewById(R.id.imageButton_1_1));
        buttonGrid.add((ImageButton) findViewById(R.id.imageButton_1_2));
        buttonGrid.add((ImageButton) findViewById(R.id.imageButton_2_0));
        buttonGrid.add((ImageButton) findViewById(R.id.imageButton_2_1));
        buttonGrid.add((ImageButton) findViewById(R.id.imageButton_2_2));

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
                            }else {
                                stateGrid[index] = 2;
                                ((ImageButton)v).setImageResource(R.drawable.cross);
                                circleTurn = true;
                            }
                        }
                    }

                }
            });
        }
    }
}
