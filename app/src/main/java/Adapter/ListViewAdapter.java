package Adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.firebase.Index;
import com.example.firebase.R;


import java.util.List;

import Model.SinhVien;

public class ListViewAdapter extends BaseAdapter {
    private List<SinhVien> list;
    private Context context;
    private int layout;
    Swap swap;

    public ListViewAdapter(List<SinhVien> list, Context context, int layout) {
        this.list = list;
        this.context = context;
        this.layout = layout;
        swap = (Swap) context;
    }

    public ListViewAdapter(List<SinhVien> list) {
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
        ImageView imgDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout,null);
            holder = new ViewHolder();
            holder.tvTen = convertView.findViewById(R.id.tvTen);
            holder.tvLop = convertView.findViewById(R.id.tvLop);
            holder.imgDelete = convertView.findViewById(R.id.imgDelete);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        SinhVien sv = list.get(position);
        holder.tvTen.setText(sv.getTen());
        holder.tvLop.setText(sv.getLop());


        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swap.delete(position);
            }
        });

        return convertView;
    }


}
