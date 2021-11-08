package com.example.recyclerviewtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText wordInput, meanInput;
    Button addBtn;
    List<String> wordList = new ArrayList<>();
    List<String> meanList = new ArrayList<>();
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordInput = findViewById(R.id.word_input);
        meanInput = findViewById(R.id.mean_input);
        addBtn = findViewById(R.id.add_list_btn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = wordInput.getText().toString();
                String mean = meanInput.getText().toString();
                wordInput.setText("");
                meanInput.setText("");
                wordList.add(word);
                meanList.add(mean);
                Data data = new Data();
                data.setWord(word);
                data.setMean(mean);
                adapter.addItem(data);
                adapter.notifyDataSetChanged();
            }
        });

        init();
        getData();
    }

    private void init(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData(){

        for(int i=0; i<3; i++) {
            wordList.add("Word"+i);
            meanList.add("Mean"+i);
        }

        for (int i = 0; i < wordList.size(); i++) {
            Data data = new Data();
            data.setWord(wordList.get(i));
            data.setMean(meanList.get(i));
            adapter.addItem(data);
        }

        adapter.notifyDataSetChanged();
    }
}