package com.example.coffeapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.coffeapp.Interface.SwapActivity;
import com.example.coffeapp.Model.Cafe;
import com.example.coffeapp.Model.User;
import com.example.coffeapp.apdater.CafeApdater;
import com.example.coffeapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListProductLikeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListProductLikeFragment extends ListFragment {
    private CafeApdater apdater;
    private List<Cafe> cafeArrayList;
    private User user;
    private SwapActivity swapActivityn ;
    private ImageButton searchBtn;
    private ImageView cartBtn;
    private EditText dataSearch;
    private String timKiem="";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListProductLikeFragment() {
        // Required empty public constructor
    }
    public ListProductLikeFragment(User user) {
        this.user =user;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListProductLikeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListProductLikeFragment newInstance(String param1, String param2) {
        ListProductLikeFragment fragment = new ListProductLikeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        swapActivityn.infoProduct(cafeArrayList.get(position));
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_product_like, container, false);
        swapActivityn = (SwapActivity) getActivity();
        anhXa();
        searchBtn=view.findViewById(R.id.searchBtn);
        cartBtn=view.findViewById(R.id.cartBtn);
        dataSearch=view.findViewById(R.id.dataSearch);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapActivityn = (SwapActivity) getActivity();
                swapActivityn.cart(0);
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timKiem=dataSearch.getText().toString();

                getAllSp();
            }
        });


        apdater = new CafeApdater(getActivity(),R.layout.item_coffee,cafeArrayList,user);
        setListAdapter(apdater);
        getAllSp();

        return view;


    }
    private void anhXa(){

        cafeArrayList = new ArrayList<>();



    }
    public void getAllSp(){

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("id_user", user.getId());
        if(!timKiem.isEmpty()){
            params.put("tim_kiem",timKiem);
        }

        client.post(getString(R.string.link_host)+"getLike.php",params,new JsonHttpResponseHandler(){


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            cafeArrayList.removeAll(cafeArrayList);
                            if(response.getBoolean("trang_thai")){
                                JSONArray data = response.getJSONArray("data");
                                for (int i=0;i<data.length();i++){
                                    Cafe cf = new Cafe();
                                    JSONObject obj = data.getJSONObject(i);
                                    cf.setId(obj.getInt("id"));
                                    cf.setName(obj.getString("ten_sp"));

                                    cf.setPrice(String.valueOf(obj.getDouble("gia")));

                                    cf.setImg(obj.getString("hinh_anh"));
                                    cf.setLike(obj.getBoolean("like"));
                                    cf.setRate(obj.getInt("rate"));

                                    cafeArrayList.add(cf);
                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        apdater.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    }
                }
        );

    }
}