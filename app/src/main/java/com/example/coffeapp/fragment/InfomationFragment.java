package com.example.coffeapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.coffeapp.Model.User;
import com.example.coffeapp.R;
import com.example.coffeapp.Interface.SwapActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfomationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfomationFragment extends Fragment {

    private User user;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView tvEditUser,tvEmailUser;
    private Button btnLogout;
    private LinearLayout lnEditUser;
    private  LinearLayout lnCart;
    private ImageView imgAvatar;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    SwapActivity swapActivityn ;

    public InfomationFragment() {
        // Required empty public constructor
    }
    public InfomationFragment(User user) {
        // Required empty public constructor
        this.user =user;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfomationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfomationFragment newInstance(String param1, String param2) {
        InfomationFragment fragment = new InfomationFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_infomation, container, false);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapActivityn = (SwapActivity) getActivity();


                swapActivityn.logout();
            }
        });

        tvEditUser = view.findViewById(R.id.tvEditUser);
        tvEmailUser = view.findViewById(R.id.tvEmailUser);
        lnEditUser = view.findViewById(R.id.lnEditUser);
        lnCart = view.findViewById(R.id.cart);
        imgAvatar=view.findViewById(R.id.imgHinh);

        Glide
                .with(view)
                .load("https://timanhdep.com/wp-content/uploads/2022/07/hinh-anh-dai-dien-avatar-dep-cho-facebook-01.jpg")
                .fitCenter()
                .into(imgAvatar);

        tvEditUser.setText(user.getName());
        tvEmailUser.setText(user.getEmail());


        lnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapActivityn = (SwapActivity) getActivity();
                swapActivityn.editUser();
            }
        });
        lnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapActivityn = (SwapActivity) getActivity();
                swapActivityn.cart(2);
            }
        });

        return view;
    }
}