package com.niazi.academy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.niazi.academy.Adoptor.Std_Detail_Adb;
import com.niazi.academy.Model_class.Std_Detail_Model;
import com.niazi.academy.R;
import com.niazi.academy.databinding.FragmentMainBinding;
import com.niazi.academy.databinding.FragmentUploadDetailBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main_fragment extends Fragment {

    public Main_fragment() {
        // Required empty public constructor
    }
    FragmentMainBinding binding;
    FirebaseDatabase mdatabase;
    DatabaseReference reference;
    FirebaseStorage firebaseStorage;

    Std_Detail_Adb stdDetailAdb;

List<Std_Detail_Model> stdDetailModelList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding= FragmentMainBinding.inflate(inflater,container,false);


        mdatabase = FirebaseDatabase.getInstance();

        reference=mdatabase.getReference().child("Student Detail");
        firebaseStorage= FirebaseStorage.getInstance();

        binding.detailrec.hasFixedSize();

        binding.detailrec.setLayoutManager(new LinearLayoutManager(getContext()));



        stdDetailModelList= new ArrayList<Std_Detail_Model>();

        stdDetailAdb= new Std_Detail_Adb(getContext(),stdDetailModelList);

        binding.detailrec.setAdapter(stdDetailAdb);

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Std_Detail_Model stdDetailModel= snapshot.getValue(Std_Detail_Model.class);
                stdDetailModelList.add(stdDetailModel);
                stdDetailAdb.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


   /*     binding.showbtn.setOnClickListener(view -> {
            FirebaseDatabase.getInstance().getReference().child("Student")
                    .child(binding.Rollno.getText().toString())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Map<String, String> getMap = (Map) snapshot.getValue();
                            if (snapshot.exists()) {
                                String name = (String) getMap.get("name");
                                String classs = (String) getMap.get("email");
                                String subject = (String) getMap.get("address");
                                String phoneno = (String) getMap.get("phoneno");
                                String url = (String) getMap.get("url");

                                binding.editText.setText(name);
                                binding.classed.setText(classs);
                                binding.subject.setText(subject);
                                binding.editTextPhone2.setText(phoneno);


                                Glide.with(getContext()).load(url).into(binding.profileImage);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });



        });
   */

        return binding.getRoot();

    }
}