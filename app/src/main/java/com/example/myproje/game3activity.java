package com.example.myproje;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class game3activity extends AppCompatActivity {

    private static final String TAG = "game3activity";

    private ImageView imageView1, imageView2, imageView3;
    private TextView questionTextView;
    private Button nextButton;

    private final int[] imageResources = {
            R.drawable.araba, R.drawable.tren, R.drawable.domates, R.drawable.sogan,
            R.drawable.gozluk, R.drawable.pizza, R.drawable.gemi, R.drawable.canta,
            R.drawable.hamburger, R.drawable.ucak, R.drawable.bisiklet
    };

    private final String[] imageNames = {
            "araba", "tren", "domates", "sogan", "gozluk", "pizza", "gemi", "canta",
            "hamburger", "ucak", "bisiklet"
    };

    private String correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started");
        setContentView(R.layout.activity_game3activity);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        questionTextView = findViewById(R.id.questionTextView);
        nextButton = findViewById(R.id.nextButton);

        Log.d(TAG, "onCreate: views initialized");

        loadNewQuestion();

        View.OnClickListener imageClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "ImageView clicked: " + v.getId());
                checkAnswer((ImageView) v);
            }
        };

        imageView1.setOnClickListener(imageClickListener);
        imageView2.setOnClickListener(imageClickListener);
        imageView3.setOnClickListener(imageClickListener);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Next button clicked");
                loadNewQuestion();
            }
        });
    }

    private void loadNewQuestion() {
        Log.d(TAG, "loadNewQuestion: started");

        // Resimleri ve isimlerini karışık hale getir
        List<Integer> imageList = new ArrayList<>();
        for (int imageResource : imageResources) {
            imageList.add(imageResource);
        }

        Random random = new Random();
        int correctImageIndex = random.nextInt(imageList.size());
        int correctImageResource = imageList.get(correctImageIndex);

        correctAnswer = imageNames[correctImageIndex];

        // Seçilen resimlerin doğru resmi içerdiğinden emin olun
        List<Integer> selectedImages = new ArrayList<>();
        selectedImages.add(correctImageResource);
        while (selectedImages.size() < 3) {
            int nextImage = imageList.get(random.nextInt(imageList.size()));
            if (!selectedImages.contains(nextImage)) {
                selectedImages.add(nextImage);
            }
        }

        Collections.shuffle(selectedImages); // Resimleri karışık hale getir

        // Seçilen resimleri görüntüle
        imageView1.setImageResource(selectedImages.get(0));
        imageView1.setTag(selectedImages.get(0));
        imageView2.setImageResource(selectedImages.get(1));
        imageView2.setTag(selectedImages.get(1));
        imageView3.setImageResource(selectedImages.get(2));
        imageView3.setTag(selectedImages.get(2));

        // Soruyu ayarla
        String question = getString(R.string.question, correctAnswer);
        questionTextView.setText(question);
        Log.d(TAG, "loadNewQuestion: question set to " + correctAnswer);
    }

    private void checkAnswer(ImageView selectedImageView) {
        int selectedImageResource = (int) selectedImageView.getTag();
        int selectedImageIndex = -1;
        for (int i = 0; i < imageResources.length; i++) {
            if (imageResources[i] == selectedImageResource) {
                selectedImageIndex = i;
                break;
            }
        }

        if (selectedImageIndex == -1) {
            Log.e(TAG, "checkAnswer: selectedImageIndex is -1, possible issue with imageResources");
            Toast.makeText(this, R.string.incorrect, Toast.LENGTH_SHORT).show();
            return;
        }

        String selectedImageName = imageNames[selectedImageIndex];
        Log.d(TAG, "checkAnswer: selected " + selectedImageName);

        if (selectedImageName.equals(correctAnswer)) {
            Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
            loadNewQuestion();
        } else {
            Toast.makeText(this, R.string.incorrect, Toast.LENGTH_SHORT).show();
        }
    }
}
