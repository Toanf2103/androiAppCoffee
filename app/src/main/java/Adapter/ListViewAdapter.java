package Adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.firebase.Index;
import com.example.firebase.R;


import java.util.List;

import Model.SinhVien;
import Model.Tree;

public class ListViewAdapter extends BaseAdapter {
    private List<Tree> list;
    private Context context;
    private int layout;
    Swap swap;

    public ListViewAdapter(List<Tree> list, Context context, int layout) {
        this.list = list;
        this.context = context;
        this.layout = layout;
        swap = (Swap) context;
    }

    public ListViewAdapter(List<Tree> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }
    private class ViewHolder{
        TextView tvTen,tvLop;
        ImageView img;
        ImageButton imgDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout,null);
            holder = new ViewHolder();
            holder.tvTen = convertView.findViewById(R.id.tvTen);
            holder.tvLop = convertView.findViewById(R.id.tvName2);
            holder.imgDelete = convertView.findViewById(R.id.imgDelete);
            holder.img=convertView.findViewById(R.id.image);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Tree tree = list.get(position);
        holder.tvTen.setText(tree.getName());
        holder.tvLop.setText(tree.getScienceName());
//        holder.img.setImageResource(tree.getImg());
        Glide.with(context).load(tree.getImg()).into(holder.img);

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swap.delete(list.get(position).getId());
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swap.swap(list.get(position));
            }
        });

        return convertView;
    }


}
