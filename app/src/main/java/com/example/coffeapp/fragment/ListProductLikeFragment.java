package com.example.coffeapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.coffeapp.Cafe;
import com.example.coffeapp.CafeApdater;
import com.example.coffeapp.ListProduct;
import com.example.coffeapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListProductLikeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListProductLikeFragment extends Fragment {
    CafeApdater apdater;
    List<Cafe> cafeArrayList;

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
//        anhXa();
//        // Inflate the layout for this fragment
//        apdater = new CafeApdater(getActivity(),R.layout.item_coffee,cafeArrayList);
//        setListAdapter(apdater);

        return inflater.inflate(R.layout.fragment_list_product_like, container, false);
    }
    private void anhXa(){

        cafeArrayList = new ArrayList<>();

        cafeArrayList.add(new Cafe("Cafe-2","20000",3,R.drawable.cafe2,true));
        cafeArrayList.add(new Cafe("Cafe-2","20000",3,R.drawable.ip11,false));
        cafeArrayList.add(new Cafe("Cafe-2","20000",3,R.drawable.ip11,true));
        cafeArrayList.add(new Cafe("Cafe-2","20000",3,R.drawable.ip11,false));
//        cafeArrayList.add(new Cafe("Cafe-3","30000",3,R.drawable.cafe3));
//        cafeArrayList.add(new Cafe("Cafe-4","40000",3,R.drawable.cafe4));
//        cafeArrayList.add(new Cafe("Cafe-5","50000",3,R.drawable.cafe5));
//        cafeArrayList.add(new Cafe("Cafe-6","60000",3,R.drawable.cafe6));
//        cafeArrayList.add(new Cafe("Cafe-7","70000",3,R.drawable.cafe7));

    }
}