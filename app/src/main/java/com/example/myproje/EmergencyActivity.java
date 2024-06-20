package com.example.myproje;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myproje.adapter.EmergencyAdapter;
import com.example.myproje.adapter.PostAdapter;
import com.example.myproje.databinding.ActivityEmergencyBinding;
import com.example.myproje.databinding.ActivityGalleryBinding;
import com.example.myproje.model.Emergency;
import com.example.myproje.model.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Map;

public class EmergencyActivity extends AppCompatActivity {

    private FirebaseStorage firebaseStorage;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;
    private ActivityEmergencyBinding binding;

    ArrayList<Emergency> emergencyArrayList;
    EmergencyAdapter emergencyAdapter;

    Button emergencyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        binding= ActivityEmergencyBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        firebaseStorage=FirebaseStorage.getInstance();
        auth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        storageReference=firebaseStorage.getReference();
        emergencyArrayList=new ArrayList<>();

        binding.recyclerViewEmergency.setLayoutManager(new LinearLayoutManager(this));
        emergencyAdapter=new EmergencyAdapter(emergencyArrayList);
        binding.recyclerViewEmergency.setAdapter(emergencyAdapter);
        getEmergencyData();

        Button emergencyBtn=findViewById(R.id.emergencyButton);
        emergencyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmergencyActivity.this, EmergencyAdder.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void getEmergencyData(){

        firebaseFirestore.collection("Emergency").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Toast.makeText(EmergencyActivity.this,error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
                if (value!=null){
                    for (DocumentSnapshot snapshot:value.getDocuments()){

                        Map<String, Object> data =snapshot.getData();

                        String number=(String) data.get("Emergencynumber");
                        String name=(String) data.get("Emergencyname");


                        Emergency emergency=new Emergency(name,number);
                        emergencyArrayList.add(emergency);

                    }
                    emergencyAdapter.notifyDataSetChanged();
                }
            }
        });



    }



}