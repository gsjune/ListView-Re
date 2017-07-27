package com.hckim.listviewre.adapterview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hckim.listviewre.R;

import java.util.List;

/**
 * Created by K on 2017-07-28.
 */

public class PeopleAdapter extends BaseAdapter { // (1)
    private final List<People> mData; // (5) (4)의 결과
    private final Context mContext; // (9)''' (9)''의 결과

    public PeopleAdapter(Context context, List<People> data) { // (3) Constructor (9)' (9) 전에 Context
        mContext = context; // (9)''
        mData = data; // (4) Create field 'mData'
    }

    @Override // (2)
    public int getCount() {
//        return 0;
        return mData.size(); // (6)
    }

    // position 번쨰 어떤 데이터가(아이템이) 있는지 알려 줘야 함
    @Override
    public Object getItem(int position) { // public Object getItem(int i) {
//        return null;
        return mData.get(position); // (7)
    }

    //
    @Override
    public long getItemId(int position) { // public long getItemId(int i)
//        return 0;
        return position; // (8)
    }

    // position 번째 레이아웃 완성해서 알려 줘야 함
    // convertView는 position 번째 레이아웃의 레퍼런스
    // parent는 어댑터가 붙을 부모의 레퍼런스(ListView 또는 GridView)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // public View getView(int i, View view, ViewGroup viewGroup) {
        if (convertView == null) {
            // 최초

            // 레이아웃 가져오기
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_exam, parent, false); // (9)
            ImageView imageView = (ImageView) convertView.findViewById(R.id.image_view);
            TextView nameTextView = (TextView) convertView.findViewById(R.id.name_text);
            TextView phoneTextView = (TextView) convertView.findViewById(R.id.phone_text);

//        } else {
//            // 재사용

            // Data
            People people = (People) getItem(position); // (People) mData.get(position);

            // 뿌리기
            imageView.setImageResource(people.getPicture());
            nameTextView.setText(people.getName());
            phoneTextView.setText(people.getPhone());
        }

//        return null;
        return convertView;
    }
}
