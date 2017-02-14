package com.android.jh.memobasic;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.jh.memobasic.domain.Memo;
import com.android.jh.memobasic.interfaces.ListInterface;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment implements View.OnClickListener{

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private List<Memo> datas = new ArrayList<>();
    RecyclerView recyclerView;
    MemoAdapter adapter;
    Context context = null;
    View view = null;
    Button btn_plus;
    ListInterface listInterface;

    public ListFragment() {

    }
    public static ListFragment newInstance(int columnCount) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       if(view != null)
           return view;
        view = inflater.inflate(R.layout.fragment_memo_list, container, false);

        // Set the adapter
        Context context = getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        adapter = new MemoAdapter(context,datas);

        recyclerView.setAdapter(adapter);
        btn_plus = (Button) view.findViewById(R.id.btn_plus);
        btn_plus.setOnClickListener(this);
        return view;
    }

    private void refreshList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listInterface = (ListInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    public void setDatas(List<Memo> datas) {
        this.datas = datas;
    }
    public void refreshAdapter() {
        adapter = new MemoAdapter(context, datas);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_plus :
                listInterface.goDetail();
                break;
        }
    }
}
