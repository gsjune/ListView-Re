package com.hckim.listviewre.adapterview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hckim.listviewre.R;

import java.util.ArrayList;

public class AdapterViewExamActivity extends AppCompatActivity {

    private static final String TAG = AdapterViewExamActivity.class.getSimpleName(); // D(3) D(2)의 결과

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_view_exam);

        // View
        ListView listView = (ListView) findViewById(R.id.list_view);
//        GridView gridView = (GridView) findViewById(R.id.grid_view); // xml에서 만든 후
//        Spinner spinner = (Spinner) findViewById(R.id.spinner); // xml에서 만든 후

        // data
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

//        gridView.setAdapter(adapter);
//        spinner.setAdapter(adapter);

        // OnItemClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // C(1) new O... Enter
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(AdapterViewExamActivity.this, "" + position, Toast.LENGTH_SHORT).show(); // C(2)
//                People people = data.get(position); // C(3) data를 final로 final ArrayList<People> data...
                People people = (People) parent.getAdapter().getItem(position); // C(3)' 방법 C(3), C(3)', 전역변수화 등
//                Toast.makeText(AdapterViewExamActivity.this, people.toString(), Toast.LENGTH_SHORT).show(); // C(4) Click
                Toast.makeText(AdapterViewExamActivity.this, "그냥 클릭", Toast.LENGTH_SHORT).show(); // C(4)' LongClick 비교
//                Log.d("AdapterViewExamActivity", "onItemClick: " + people.toString()); // D(1) logd Enter debug
                Log.d(TAG, "onItemClick: " + people.toString()); // D(2) TAG Alter Enter

                Intent intent = new Intent(AdapterViewExamActivity.this, DetailAddressActivity.class);
                intent.putExtra("name", people.getName());
                intent.putExtra("phone", people.getPhone());
                intent.putExtra("picture", people.getPicture());
                startActivity(intent);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // E(1)
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AdapterViewExamActivity.this, "롱 클릭" + position, Toast.LENGTH_SHORT).show(); // E(2)
                return true; // E(3) true로 바꿈. 이벤트 소비 제어. 이벤트 소비를 하겠다. 더 이상 이벤트가 흘러가지 않는다.
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_address, menu);
        Log.d(TAG, "onCreateOptionsMenu: 최초 메뉴키 클릭했을 때 메뉴 호출");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: 메뉴 항목 클릭했을 때 메뉴 호출");

        int id = item.getItemId();

        switch(id) {
            case R.id.action_add:
                Intent intent = new Intent(AdapterViewExamActivity.this, DetailAddressActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
