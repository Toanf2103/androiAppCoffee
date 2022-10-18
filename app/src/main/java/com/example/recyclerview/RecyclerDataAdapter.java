package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerDataAdapter extends RecyclerView.Adapter<RecyclerDataAdapter.DataViewHolder> {

    private List<Person> people;
    private Context context;


    @NonNull

    public RecyclerDataAdapter(Context context,List<Person> people){
        this.people=people;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

        switch  (viewType){
            case 1:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_name,parent,false);
                break;
            case 2:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_name_female,parent,false);
                break;
            case 3:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_name,parent,false);
                break;
        }

        return new DataViewHolder(itemView);
    }



    @Override
    public int getItemCount() {
        return people==null?0:people.size();
    }
    @Override
    public int getItemViewType(int position){
        if(people.get(position).isMale()){
            return 1;
        }
        return 2;
    }

    @Override
    public void onBindViewHolder(RecyclerDataAdapter.DataViewHolder holder,int position){
        String name =people.get(position).getName();
        holder.tvName.setText(name);

    }

    public static class DataViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        public DataViewHolder(View itemView){
            super(itemView);
            tvName =(TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
