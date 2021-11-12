package com.example.firebasetest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

//  https://wooastory.tistory.com/119
//  https://choi-dev.tistory.com/69 FireStore 데이터 가져오기
public class MainActivity_Firestore extends AppCompatActivity {

    EditText et_word, et_mean;
    Button btn_save;
    private FirebaseFirestore mDatabase;
    int dataIdx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_word = findViewById(R.id.et_word);
        et_mean = findViewById(R.id.et_mean);
        btn_save = findViewById(R.id.btn_save);

        mDatabase = FirebaseFirestore.getInstance();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getWord = et_word.getText().toString();
                String getMean = et_mean.getText().toString();

                HashMap word = new HashMap<>();
                word.put(getWord, getMean);

                mDatabase.collection("word")
                        .add(word)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.w("FireStore", "Added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("FireStore", "Error", e);
                            }
                        });
            }
        });


//        mDatabase.collection("user")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful()) {
//                            for (QueryDocumentSnapshot doc : task.getResult()) {
//                                Log.w("FireStore", doc.getId() + " : " + doc.getData());
//                            }
//                        } else {
//                            Log.w("FireStore", "Error", task.getException());
//                        }
//                    }
//                });
    }
}
