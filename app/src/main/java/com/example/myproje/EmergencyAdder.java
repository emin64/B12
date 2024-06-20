package com.example.myproje;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.myproje.databinding.ActivityAddPhotoBinding;
import com.example.myproje.databinding.ActivityEmergencyAdderBinding;
import com.example.myproje.model.Post;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class EmergencyAdder extends AppCompatActivity {

    private FirebaseStorage firebaseStorage;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;
    private ActivityEmergencyAdderBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_adder);
        binding= ActivityEmergencyAdderBinding.inflate(getLayoutInflater());
        View view =binding.getRoot();
        setContentView(view);
        firebaseStorage=FirebaseStorage.getInstance();
        auth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        storageReference=firebaseStorage.getReference();


        binding.emergencyAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=binding.emergencyAddName.getText().toString();
                String number=binding.emergencyAddNumber.getText().toString();
                FirebaseUser user = auth.getCurrentUser();
                String email= user.getEmail();
                HashMap<String, Object> EmergencyData = new HashMap<>();
                EmergencyData.put("useremail",email);
                EmergencyData.put("Emergencyname",name);
                EmergencyData.put("Emergencynumber",number);
                EmergencyData.put("date", FieldValue.serverTimestamp());

                firebaseFirestore.collection("Emergency").add(EmergencyData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Intent intent=new Intent(EmergencyAdder.this,EmergencyActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EmergencyAdder.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }




}
