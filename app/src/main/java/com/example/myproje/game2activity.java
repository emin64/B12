package com.example.myproje;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class game2activity extends AppCompatActivity {

    private GridLayout gridLayout;
    private int[] imageResources = {
            R.drawable.mor, R.drawable.kivi, R.drawable.ahududu, R.drawable.karpuz,
            R.drawable.mor, R.drawable.kivi, R.drawable.ahududu, R.drawable.karpuz
    };
    private ImageView firstSelectedImageView;
    private ImageView secondSelectedImageView;
    private boolean isBusy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2activity);

        gridLayout = findViewById(R.id.gridLayout);

        List<Integer> imagesList = Arrays.asList(
                R.drawable.mor, R.drawable.kivi, R.drawable.ahududu, R.drawable.karpuz,
                R.drawable.mor, R.drawable.kivi, R.drawable.ahududu, R.drawable.karpuz
        );
        Collections.shuffle(imagesList);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setTag(imagesList.get(i));
            imageView.setImageResource(R.drawable.hidden_image);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isBusy) return;
                    handleImageClick((ImageView) v);
                }
            });
        }
    }

    private void handleImageClick(final ImageView imageView) {
        if (firstSelectedImageView == null) {
            firstSelectedImageView = imageView;
            firstSelectedImageView.setImageResource((int) firstSelectedImageView.getTag());
        } else if (secondSelectedImageView == null) {
            secondSelectedImageView = imageView;
            secondSelectedImageView.setImageResource((int) secondSelectedImageView.getTag());

            isBusy = true;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkMatch();
                }
            }, 1000);
        }
    }

    private void checkMatch() {
        if (firstSelectedImageView.getTag().equals(secondSelectedImageView.getTag())) {
            firstSelectedImageView.setClickable(false);
            secondSelectedImageView.setClickable(false);
            checkGameEnd();
        } else {
            firstSelectedImageView.setImageResource(R.drawable.hidden_image);
            secondSelectedImageView.setImageResource(R.drawable.hidden_image);
        }

        firstSelectedImageView = null;
        secondSelectedImageView = null;
        isBusy = false;
    }

    private void checkGameEnd() {
        boolean gameEnded = true;
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            if (gridLayout.getChildAt(i).isClickable()) {
                gameEnded = false;
                break;
            }
        }
        if (gameEnded) {
            Toast.makeText(this, "Oyun bitti! Tebrikler!", Toast.LENGTH_SHORT).show();
        }
    }
}
