package com.niazi.academy.fragment;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.niazi.academy.R;
import com.niazi.academy.databinding.FragmentUploadDetailBinding;

import java.util.HashMap;
import java.util.Map;

public class Upload_Detail extends Fragment {


    private int Gallery_Code;

    public Upload_Detail() {
        // Required empty public constructor
    }
    FragmentUploadDetailBinding binding;
    int rollid = 0;

    Uri uri;
    String ImageUrl;

    ProgressDialog progressDialog;


    FirebaseDatabase mdatabase;
    DatabaseReference reference;
    FirebaseStorage firebaseStorage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding= FragmentUploadDetailBinding.inflate(inflater,container,false);
        FirebaseDatabase.getInstance().getReference().child("Student Detail").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                rollid = (int) snapshot.getChildrenCount();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mdatabase = FirebaseDatabase.getInstance();
        reference=mdatabase.getReference().child("Student Detail");
        firebaseStorage= FirebaseStorage.getInstance();


        binding.uploadphotob.setOnClickListener(view -> {
            Intent intent =  new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, Gallery_Code);
        });
        progressDialog = new ProgressDialog(getContext());

            //                             .child(String.valueOf(rollid+1)).on                                                          .putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                             /*   @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();

                                    while (!uriTask.isCanceled());

                                            imageUrl = uriTask.getResult().toString();

                                    HashMap<String, String> map = new HashMap<>();
                                    map.put("url", imageUrl);
                                    map.put("name", binding.editTextname.getText().toString());
                                    map.put("class", binding.classed.getText().toString());
                                    map.put("subject", binding.subject.getText().toString());
                                    map.put("phoneno", binding.editTextPhone2.getText().toString());

                                    FirebaseDatabase.getInstance().getReference().child("Student Details")
                                            .child(String.valueOf(rollid+1)).setValue(map);


                                }

                            });


        });
*/


        return binding.getRoot();


    }
 @Override
        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

     super.onActivityResult(requestCode, resultCode, data);

     if (requestCode == Gallery_Code && resultCode == RESULT_OK) {

         uri = data.getData();
         binding.profileImage.setImageURI(uri);
     }
     binding.uploadbn.setOnClickListener(view -> {


         AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
         alert.setTitle("Attention Please");
         alert.setMessage("Are You Sure");
         alert.setInverseBackgroundForced(true);
         alert.setCancelable(false);
         alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
              /*   progressDialog.setTitle("Uploading...");
                 progressDialog.show();


*/
                 String editTextnam = binding.editTextname.getText().toString().trim();
                 String classe =  binding.classed.getText().toString().trim();
                 String subjec = binding.subject.getText().toString().trim();
                 String editTextPhoe2 = binding.editTextPhone2.getText().toString().trim();


                 if (!(editTextnam.isEmpty() && classe.isEmpty() && subjec.isEmpty() && editTextPhoe2.isEmpty() && uri != null)) {
                     progressDialog.setTitle("Uploading...");
                     progressDialog.show();

                     StorageReference filepath = firebaseStorage.getReference().child("Student Profile")
                             .child(uri.getLastPathSegment());
                     filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                         @Override
                         public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                             Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Uri> task) {


                                     String imageUrl = task.getResult().toString();

                                     DatabaseReference map = reference.push();
                                     map.child("image").setValue(task.getResult().toString());
                                     map.child("name").setValue(editTextnam);
                                     map.child("email").setValue(classe);
                                     map.child("address").setValue(subjec);
                                     map.child("phoneno").setValue(editTextPhoe2);

                                     progressDialog.dismiss();
                                /*     FirebaseDatabase.getInstance().getReference().child("Student Details")
                                             .child(String.valueOf(rollid + 1)).setValue(map);
                                */ }
                             });
                         }

                     });
                 }
             }
         }).setNegativeButton("No", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                 Toast.makeText(getContext(), "Cancel", Toast.LENGTH_SHORT).show();
             }
         });
         alert.show();
/*
                 FirebaseStorage.getInstance().getReference().child("Student").child(String.valueOf(rollid + 1)).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                     @Override
                     public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                         Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();

                         while (!uriTask.isComplete());
                             Uri ImageUri = uriTask.getResult();
                             ImageUrl= ImageUri.toString();


                         HashMap<String, String> map = new HashMap<>();
                         map.put("url", ImageUrl);
                         map.put("name", binding.editTextname.getText().toString());
                         map.put("email", binding.classed.getText().toString());
                         map.put("address", binding.subject.getText().toString());
                         map.put("phoneno", binding.editTextPhone2.getText().toString());

                         FirebaseDatabase.getInstance().getReference().child("Student")
                                 .child(String.valueOf(rollid + 1)).setValue(map);

                         progressDialog.dismiss();


                     }
                 });
             }
             }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                     Toast.makeText(getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                 }
             });
         alert.show();


         });*/


     });


 }
}