package com.example.myproje;

import static com.google.firebase.appcheck.internal.util.Logger.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.FirebaseMessaging;

public class bildirim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bildirim);


        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()){
                    Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                    return;
                }
                String token = task.getResult();

                System.out.println("Your token is: " + token);  //Tokeni logcatte yazdırdık
                Toast.makeText(bildirim.this,"Token",Toast.LENGTH_SHORT).show();
                //f2hVjd5PTyiPTcuz56hr6W:APA91bFJu0RnmCl76lRERVuZVjgiYJZSzKKFOrvv5ORH9DLuJJ7-sukfd5z4MkrxPowv3CJQ3UYeBRp2r9Dji7U25jhDPHUm6fljIDeWJa-BN2AzqiUGLCu3rp4C57EQtxlcBe-Rkrhk


            }
        });
    }
}