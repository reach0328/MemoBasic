package com.android.jh.memobasic;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.android.jh.memobasic.data.DBHelper;
import com.android.jh.memobasic.domain.Memo;
import com.android.jh.memobasic.interfaces.DetailInterface;
import com.android.jh.memobasic.interfaces.ListInterface;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListInterface,DetailInterface {

    ListFragment list;
    DetailFragment detail;
    FrameLayout main;
    FragmentManager manager;

    List<Memo> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = ListFragment.newInstance(1);
        detail = DetailFragment.newInstance();
        main = (FrameLayout)findViewById(R.id.activity_main);
        manager = getSupportFragmentManager();
        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        list.setDatas(datas);
        setList();
    }

    public void loadData() throws SQLException{
        DBHelper dbHelper = OpenHelperManager.getHelper(this,DBHelper.class);
        Dao<Memo,Integer> memoDao = dbHelper.getMemoDao();
        datas = memoDao.queryForAll();
    }

    private void setList() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.activity_main,list);
        transaction.commit();
    }

    @Override
    public void goDetail() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.activity_main,detail);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void goDetail(int position) {

    }

    @Override
    public void backToList() {
        super.onBackPressed();
    }

    @Override
    public void saveToList(Memo memo) throws SQLException {
        DBHelper dbHelper = OpenHelperManager.getHelper(this,DBHelper.class);
        Dao<Memo,Integer> memoDao = dbHelper.getMemoDao();
        memoDao.create(memo);
        loadData();
        list.setDatas(datas);
        super.onBackPressed();
        list.refreshAdapter();
    }

}
