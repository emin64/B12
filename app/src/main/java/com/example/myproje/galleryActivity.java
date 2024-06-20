package com.example.myproje;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myproje.adapter.PostAdapter;
import com.example.myproje.databinding.ActivityGalleryBinding;
import com.example.myproje.model.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class galleryActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<Post> postArrayList;
    private ActivityGalleryBinding binding;

    PostAdapter postAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityGalleryBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);


        firebaseFirestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        postArrayList=new ArrayList<>();
        getData();

        binding.recyclerViewGallery.setLayoutManager(new LinearLayoutManager(this));
        postAdapter=new PostAdapter(postArrayList);
        binding.recyclerViewGallery.setAdapter(postAdapter);



        Button addBtn=findViewById(R.id.buttonaddphoto);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(galleryActivity.this, AddPhotoActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private void getData(){
        firebaseFirestore.collection("Photos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Toast.makeText(galleryActivity.this,error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
                if (value!=null){
                    for (DocumentSnapshot snapshot:value.getDocuments()){

                        Map<String, Object> data =snapshot.getData();

                        String userEmail=(String) data.get("useremail");
                        String comment=(String) data.get("comment");
                        String downloadUrl=(String) data.get("downloadurl");
                        String imageName=(String) data.get("imagename");

                        Post post=new Post(userEmail,comment,imageName,downloadUrl);
                        postArrayList.add(post);

                    }
                    postAdapter.notifyDataSetChanged();
                }
            }
        });

    }
}