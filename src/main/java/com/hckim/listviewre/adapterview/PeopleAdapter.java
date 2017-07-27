package com.hckim.listviewre.adapterview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by K on 2017-07-28.
 */

public class PeopleAdapter extends BaseAdapter { // (1)
    private final List<People> mData; // (5) (4)의 결과

    public PeopleAdapter(List<People> data) { // (3) Constructor
        mData = data; // (4) Create field 'mData'
    }

    @Override // (2)
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
