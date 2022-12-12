package com.example.coffeapp.apdater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeapp.Interface.DeleteCart;
import com.example.coffeapp.Model.Cafe;
import com.example.coffeapp.R;

import java.text.DecimalFormat;
import java.util.List;

public class CartApdater extends RecyclerView.Adapter<CartApdater.CartViewHolder>{

    private Context context;
    private List<Cafe> cafeList;
    DeleteCart deleteCart;

    public CartApdater(Context context) {
        this.context = context;
        deleteCart = (DeleteCart) context;
    }

    public void setData(List<Cafe> list){
        this.cafeList=list;
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cafe cf = cafeList.get(position);


        if(cf==null){
            return;
        }
        holder.tvName.setText(cf.getName());

//        holder.tvGia.setText(String.valueOf(formatter.format(cf.getPrice()))+" VNĐ");
        holder.tvGia.setText(cf.getStringPrice());
        Glide
                .with(context)
                .load("https://toanf2103.000webhostapp.com/"+cf.getImg())
                .fitCenter()
                .into(holder.imgSp);
        holder.tvSoLuong.setText("SỐ LƯỢNG: "+cf.getSo_luong());
        holder.deleteSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, String.valueOf(cf.getId()), Toast.LENGTH_SHORT).show();
                deleteCart.delete(cf.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        if (cafeList!=null){
            return cafeList.size();
        }
        return 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgSp;
        private TextView tvName,tvGia,tvSoLuong;
        private ImageButton deleteSp;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            imgSp=itemView.findViewById(R.id.imgHinh);
            tvSoLuong=itemView.findViewById(R.id.soLuong);

            tvName=itemView.findViewById(R.id.tvName);

            tvGia=itemView.findViewById(R.id.tvPrice);
            deleteSp=itemView.findViewById(R.id.deleteBtn);



        }
    }
}
