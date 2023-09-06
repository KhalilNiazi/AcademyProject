package com.niazi.academy.Adoptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.niazi.academy.MainActivity2;
import com.niazi.academy.Model_class.Std_Detail_Model;
import com.niazi.academy.R;
import com.niazi.academy.databinding.DetaliItemviewBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Std_Detail_Adb extends RecyclerView.Adapter<Std_Detail_Adb.ViewHolder> {

    Context context;

    List<Std_Detail_Model> stdDetailModelList;

    public Std_Detail_Adb(Context context, List<Std_Detail_Model> stdDetailModelList) {
        this.context = context;
        this.stdDetailModelList = stdDetailModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detali_itemview,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Std_Detail_Model stdDetailModel = stdDetailModelList.get(position);

        holder.binding.name.setText(stdDetailModel.getName());
        holder.binding.email.setText(stdDetailModel.getEmail());
        holder.binding.Address.setText(stdDetailModel.getAddress());
        holder.binding.phoneno.setText(stdDetailModel.getPhoneno());


        String imahurl = null;
        imahurl= stdDetailModel.getImage();



        Picasso.get().load(imahurl).into(holder.binding.profileImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(view.getContext(), MainActivity2.class);
               intent.putExtra("name",stdDetailModel.getName());
               intent.putExtra("email",stdDetailModel.getEmail());
               intent.putExtra("address",stdDetailModel.getAddress());
               intent.putExtra("phone",stdDetailModel.getPhoneno());
               intent.putExtra("image",stdDetailModel.getImage());
               view.getContext().startActivity(intent);
       }
        });

        YoYo.with(Techniques.Tada)
                .duration(200)
                .playOn(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return stdDetailModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        DetaliItemviewBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = DetaliItemviewBinding.bind(itemView);
        }

    }
}
