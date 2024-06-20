package com.example.myproje;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class game1activity extends AppCompatActivity {

    private GridLayout jumbledWordGridLayout;
    private EditText guessEditText;
    private Button submitButton;

    private String[] words = {"örnek", "araba", "hafıza", "beyin"};
    private String currentWord;
    private String jumbledWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        jumbledWordGridLayout = findViewById(R.id.jumbledWordGridLayout);
        guessEditText = findViewById(R.id.guessEditText);
        submitButton = findViewById(R.id.submitButton);

        setNewWord();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });
    }

    private void setNewWord() {
        currentWord = words[(int) (Math.random() * words.length)];
        jumbledWord = jumbleWord(currentWord);
        displayJumbledWord(jumbledWord);
    }

    private String jumbleWord(String word) {
        List<String> letters = Arrays.asList(word.toUpperCase().split(""));
        Collections.shuffle(letters);
        StringBuilder jumbled = new StringBuilder();
        for (String letter : letters) {
            jumbled.append(letter).append(" ");
        }
        return jumbled.toString().trim();
    }

    private void displayJumbledWord(String jumbledWord) {
        jumbledWordGridLayout.removeAllViews();
        String[] letters = jumbledWord.split(" ");
        for (String letter : letters) {
            TextView letterTextView = new TextView(this);
            letterTextView.setText(letter);
            letterTextView.setTextSize(32);
            letterTextView.setBackgroundResource(R.drawable.letter_background);
            letterTextView.setPadding(16, 16, 16, 16);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(8, 8, 8, 8);
            letterTextView.setLayoutParams(params);
            jumbledWordGridLayout.addView(letterTextView);
        }
    }

    private void checkGuess() {
        String guess = guessEditText.getText().toString().trim();
        if (guess.equalsIgnoreCase(currentWord)) {
            Toast.makeText(this, "Doğru!", Toast.LENGTH_SHORT).show();
            setNewWord();
            guessEditText.setText("");
        } else {
            Toast.makeText(this, "Tekrar deneyin!", Toast.LENGTH_SHORT).show();
        }
    }
}
