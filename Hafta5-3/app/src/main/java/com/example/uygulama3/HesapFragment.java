package com.example.uygulama3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HesapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HesapFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Hesap hesap;

    public HesapFragment(Hesap hesap) {
        this.hesap=hesap;
    }

    public HesapFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HesapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HesapFragment newInstance(String param1, String param2) {
        HesapFragment fragment = new HesapFragment();
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

        View myView= inflater.inflate(R.layout.fragment_hesap, container, false);
        TextView txt_hesapsahibi=myView.findViewById(R.id.txt_hesapsahibi);
        TextView txt_hesaptürü=myView.findViewById(R.id.txt_hesaptürü);
        TextView txt_hesapbakiyesi=myView.findViewById(R.id.txt_bakiye);
        txt_hesapsahibi.setText(this.hesap.getHesapsahibi());
        txt_hesaptürü.setText(this.hesap.getHesaptürü());
        txt_hesapbakiyesi.setText(this.hesap.getHesapbakiyesi()+"");
        return myView;
    }
}