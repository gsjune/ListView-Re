package com.hckim.listviewre.adapterview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.hckim.listviewre.R;

import java.util.ArrayList;

public class AdapterViewExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_view_exam);

        // View
        ListView listView = (ListView) findViewById(R.id.list_view);

        // Data
//        ArrayList<String> data = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            data.add("데이터 " + i);
//        }
        ArrayList<People> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int picture;
            if (i % 2 == 0) {
                picture = R.drawable.federer;
            } else {
                picture = R.mipmap.ic_launcher;
            }
            People people = new People("아무개 " + i, "전화번호 " + i, picture);
            data.add(people);
        }

        // Adapter
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AdapterViewExamActivity.this,
//                android.R.layout.simple_list_item_1, data);
//        ArrayAdapter<People> adapter = new ArrayAdapter<People>(AdapterViewExamActivity.this,
//                android.R.layout.simple_list_item_1, data); // 다시 만들어야

        PeopleAdapter adapter = new PeopleAdapter(AdapterViewExamActivity.this, data); // B(1)

        listView.setAdapter(adapter);
    }
}
