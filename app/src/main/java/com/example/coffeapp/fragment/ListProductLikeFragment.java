package com.example.coffeapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        apdater = new CafeApdater(getActivity(),R.layout.item_coffee,cafeArrayList,user);
        setListAdapter(apdater);
        getAllSp();

        return view;


    }
    private void anhXa(){

        cafeArrayList = new ArrayList<>();

//        cafeArrayList.add(new Cafe("Cafe-2","20000",3,R.drawable.cafe2,true));
//        cafeArrayList.add(new Cafe("Cafe-2","20000",3,R.drawable.ip11,false));
//        cafeArrayList.add(new Cafe("Cafe-2","20000",3,R.drawable.ip11,true));
//        cafeArrayList.add(new Cafe("Cafe-2","20000",3,R.drawable.ip11,false));

//        cafeArrayList.add(new Cafe("Cafe-3","30000",3,R.drawable.cafe3));
//        cafeArrayList.add(new Cafe("Cafe-4","40000",3,R.drawable.cafe4));
//        cafeArrayList.add(new Cafe("Cafe-5","50000",3,R.drawable.cafe5));
//        cafeArrayList.add(new Cafe("Cafe-6","60000",3,R.drawable.cafe6));
//        cafeArrayList.add(new Cafe("Cafe-7","70000",3,R.drawable.cafe7));

    }
    public void getAllSp(){

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("id_user", user.getId());

        client.post(getString(R.string.link_host)+"getLike.php",params,new JsonHttpResponseHandler(){


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
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
                                    cf.setRate(4);
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