package com.example.coffeapp.apdater;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.coffeapp.Interface.SwapActivity;
import com.example.coffeapp.Model.Cafe;
import com.example.coffeapp.Model.User;
import com.example.coffeapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CafeApdater extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Cafe> cafeList;
    private User user;


    public CafeApdater(Context context, int layout, List<Cafe> cafeList, User user) {
        this.context = context;
        this.layout = layout;
        this.cafeList = cafeList;
        this.user = user;
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

        if (!cafe.getLike()){
            holder.likeProduct.setImageResource(R.drawable.ic_icon_like);
        } else {

            holder.likeProduct.setImageResource(R.drawable.ic_un_icon_like);
        }


        Glide
                .with(context)
                .load("https://toanf2103.000webhostapp.com/"+cafe.getImg())
                .fitCenter()
                .into(holder.imgHinh);



        holder.likeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link="";
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();

                params.put("id_user", user.getId());
                params.put("id_sp", cafe.getId());

                if(cafe.getLike()){
                    link="/unLikeSp.php";
                }else{
                    link="/addSpLike.php";
                }
                client.post("https://toanf2103.000webhostapp.com/"+link,params,new JsonHttpResponseHandler(){

                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                try {

                                    if(response.getBoolean("trang_thai")){

                                        cafe.setLike(!cafe.getLike());

                                        if (!cafe.getLike()){
                                            holder.likeProduct.setImageResource(R.drawable.ic_icon_like);
                                        } else {

                                            holder.likeProduct.setImageResource(R.drawable.ic_un_icon_like);
                                        }
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            }
                        }
                );




            }
        });


        return convertView;

    }




}
