package com.nantoni.tp1_morpion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);

        Button btn_pvp = findViewById(R.id.btn_pvp);
        btn_pvp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(GameSelection.this, Game.class);
                i.putExtra("mode", "pvp");
                startActivity(i);
            }
        });

        Button btn_pve = findViewById(R.id.btn_pve);
        btn_pve.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(GameSelection.this, Game.class);
                i.putExtra("mode", "pve");
                startActivity(i);
            }
        });
    }
}
