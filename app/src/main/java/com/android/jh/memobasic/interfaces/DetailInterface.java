package com.android.jh.memobasic.interfaces;

import com.android.jh.memobasic.domain.Memo;

import java.sql.SQLException;

/**
 * Created by JH on 2017-02-14.
 */

public interface DetailInterface {
    public void backToList();
    public void saveToList(Memo memo) throws SQLException;
}
