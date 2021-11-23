package com.example.flipcardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_start_game;
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start_game = findViewById(R.id.btn_game_start);
        btn_back = findViewById(R.id.btn_back);

        RadioGroup rg = findViewById(R.id.radio_time);


        btn_start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(rg.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getApplicationContext(), "Time을 선택해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton rb = findViewById(rg.getCheckedRadioButtonId());
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);

                intent.putExtra("time", rb.getText().toString());
                startActivity(intent);
            }
        });
    }
}