package com.android.jh.memobasic;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.jh.memobasic.domain.Memo;
import com.android.jh.memobasic.interfaces.DetailInterface;

import java.sql.SQLException;
import java.util.Date;

public class DetailFragment extends Fragment implements View.OnClickListener {

    int position = -1;
    Context context = null;
    View view = null;
    Button btn_save, btn_cancel;
    EditText et_Memo;
    DetailInterface detailInterface;

    public DetailFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view != null)
            return view;
        view =inflater.inflate(R.layout.fragment_detail, container, false);

        btn_save = (Button)view.findViewById(R.id.btn_save);
        btn_cancel = (Button)view.findViewById(R.id.btn_cancle);
        et_Memo = (EditText)view.findViewById(R.id.et_content);

        btn_save.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.detailInterface = (DetailInterface) context;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_save :
                Memo memo = makeMemo();
                try {
                    detailInterface.saveToList(memo);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_cancle :
                break;
        }
    }
    private Memo makeMemo() {
        Memo memo = new Memo();
        memo.setMemo(et_Memo.getText().toString());
        memo.setDate(new Date(System.currentTimeMillis()));
        return memo;
    }
}
