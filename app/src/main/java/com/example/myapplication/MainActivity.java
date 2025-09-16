package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button buttonRzuc;
    private Button buttonReset;
    private ImageView[] obrazyKosci;
    private TextView textViewWynikRzutu;
    private TextView textViewWynikSumaryczny;
    private int wynikSumaryczny = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonReset = findViewById(R.id.buttonReset);
        buttonRzuc = findViewById(R.id.buttonRzuc);

        obrazyKosci = new ImageView[5];
        obrazyKosci[0] = findViewById(R.id.imageView);
        obrazyKosci[1] = findViewById(R.id.imageView2);
        obrazyKosci[2] = findViewById(R.id.imageView3);
        obrazyKosci[3] = findViewById(R.id.imageView4);
        obrazyKosci[4] = findViewById(R.id.imageView5);

        textViewWynikRzutu = findViewById(R.id.textView);
        textViewWynikSumaryczny = findViewById(R.id.textView2);

        int[] obrazki = new int[]{
                R.drawable.question,
                R.drawable.k1,
                R.drawable.k2,
                R.drawable.k3,
                R.drawable.k4,
                R.drawable.k5,
                R.drawable.k6
        };

        buttonRzuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] rzuty = rzucWszystkimiKoscmi();

                for (int i = 0; i < obrazyKosci.length; i++){
                    obrazyKosci[i].setImageResource(obrazki[rzuty[i]]);
                }

                int sumaRzutu = sumaKosci(rzuty);
                textViewWynikRzutu.setText("Wynik rzutu: " + sumaRzutu);
                wynikSumaryczny += sumaRzutu;
                textViewWynikSumaryczny.setText("Wynik gry: " + wynikSumaryczny);
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wynikSumaryczny = 0;
                textViewWynikRzutu.setText("Wynik rzutu: ");
                textViewWynikSumaryczny.setText("Wynik gry: ");
                for (int i = 0; i < obrazyKosci.length; i++){
                    obrazyKosci[i].setImageResource(obrazki[0]);
                }
            }
        });
    }
    private int[] rzucWszystkimiKoscmi(){
        int[] rzuty = new int[5];
        Random random = new Random();
        for (int i = 0; i < rzuty.length; i++){
            rzuty[i] = random.nextInt(6)+1; //+1 lub potem przy odczycie grafiki -1
        }
        return rzuty;
    }
    private int sumaKosci(int[] rzuty){
        int s = 0;
        for(int i = 0; i < rzuty.length; i++){
            s += rzuty[i];
        }
        return s;
    }
}