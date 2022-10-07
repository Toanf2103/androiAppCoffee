package com.example.coffeapp.apdater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffeapp.Model.Cafe;
import com.example.coffeapp.R;

import java.util.List;

public class CafeApdater extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Cafe> cafeList;

    public CafeApdater(Context context, int layout, List<Cafe> cafeList) {
        this.context = context;
        this.layout = layout;
        this.cafeList = cafeList;
    }

    @Override
    public int getCount() {
        return cafeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView txtName;
        TextView txtPrice;
        ImageView imgHinh;
        ImageView likeProduct;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =inflater.inflate(layout,null);
            holder = new ViewHolder();
             holder.txtName= (TextView) convertView.findViewById(R.id.tvName);
            holder.txtPrice= (TextView) convertView.findViewById(R.id.tvPrice);
            holder.imgHinh = (ImageView) convertView.findViewById(R.id.imgHinh);
            holder.likeProduct = (ImageView) convertView.findViewById(R.id.likeProduct);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)  convertView.getTag();
        }
        Cafe cafe = cafeList.get(position);
        holder.txtName.setText(cafe.getName());
        holder.txtPrice.setText(cafe.getPrice());

        if (cafe.getLike()){
            holder.likeProduct.setImageResource(R.drawable.ic_icon_like);
        } else {

            holder.likeProduct.setImageResource(R.drawable.ic_un_icon_like);
        }


        holder.imgHinh.setImageResource(cafe.getImg());
        holder.likeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cafe.setLike(!cafe.getLike());


                if (cafe.getLike()){
                    holder.likeProduct.setImageResource(R.drawable.ic_icon_like);
                } else {

                    holder.likeProduct.setImageResource(R.drawable.ic_un_icon_like);
                }
            }
        });
        return convertView;

    }


}
