package com.hckim.listviewre.adapterview;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hckim.listviewre.R;

import java.util.ArrayList;

//public class AdapterViewExamActivity extends AppCompatActivity {
public class AdapterViewExamActivity extends AppCompatActivity implements DialogInterface.OnClickListener { // H(3)

    private static final String TAG = AdapterViewExamActivity.class.getSimpleName(); // D(3) D(2)의 결과
    private ArrayList<People> data;
    private ArrayList<People> mPeopleData;
    private PeopleAdapter mAdapter; // F(6)' F(6)의 결과
    private ListView mListView; // F(7)' F(7)의 결과
    private EditText mWeatherEditText; // G(2)'의 결과

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_view_exam);

        // View
//        ListView listView = (ListView) findViewById(R.id.list_view);
        mListView = (ListView) findViewById(R.id.list_view); // F(7)
        GridView gridView = (GridView) findViewById(R.id.grid_view); // xml에서 만든 후
        Spinner spinner = (Spinner) findViewById(R.id.spinner); // xml에서 만든 후

        // data
//        ArrayList<String> data = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            data.add("데이터 " + i);
//        }
//        ArrayList<People> data = new ArrayList<>();
        mPeopleData = new ArrayList<>(); // F(4) F(3)위해 필드로 뺌. data -> Find Action field Enter
        for (int i = 0; i < 100; i++) {
            int picture;
            if (i % 2 == 0) {
                picture = R.drawable.federer;
            } else {
                picture = R.mipmap.ic_launcher;
            }
            People people = new People("아무개 " + i, "전화번호 " + i, picture);
            mPeopleData.add(people);
        }

        // Adapter
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AdapterViewExamActivity.this,
//                android.R.layout.simple_list_item_1, data);
//        ArrayAdapter<People> adapter = new ArrayAdapter<People>(AdapterViewExamActivity.this,
//                android.R.layout.simple_list_item_1, data); // 다시 만들어야

//        PeopleAdapter adapter = new PeopleAdapter(AdapterViewExamActivity.this, mPeopleData); // B(1)
        mAdapter = new PeopleAdapter(AdapterViewExamActivity.this, mPeopleData); // B(1) F(6) F(5)의 업데이트 위해 update를 전역변수로 뺌

        mListView.setAdapter(mAdapter);

        gridView.setAdapter(mAdapter);
        spinner.setAdapter(mAdapter);

        // OnItemClickListener
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // C(1) new O... Enter
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

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // E(1) F(2) F(1) 위해 주석 처리로 막음
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(AdapterViewExamActivity.this, "롱 클릭" + position, Toast.LENGTH_SHORT).show(); // E(2)
//                return true; // E(3) true로 바꿈. 이벤트 소비 제어. 이벤트 소비를 하겠다. 더 이상 이벤트가 흘러가지 않는다.
//            }
//        });

        // Context 메뉴 연결
        registerForContextMenu(mListView); // F(1)

        // SharedPreference 데이터 복원 G(1)
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String weather = settings.getString("weather", "맑음");

//        EditText mWeatherEditText = (EditText) findViewById(R.id.weather_edit);
        mWeatherEditText = (EditText) findViewById(R.id.weather_edit); // G(2)' mWeather 전역변수로
        mWeatherEditText.setText(weather);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo(); // info 롱클릭된 정보가 들어 있음
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo(); // info 롱클릭된 정보가 들어 있음
        switch (item.getItemId()) {
            case R.id.action_item1:
                Toast.makeText(this, "action 1", Toast.LENGTH_SHORT).show();

//                // 물어보자 AlertDialog H(1)
//                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("삭제");
//                builder.setMessage("정말로 삭제하시겠습니까?");
//                // 바깥 부분 클릭했을 때 닫기
//                builder.setCancelable(false);
////                builder.setPositiveButton("삭제", null);
////                builder.setPositiveButton("삭제", null); // H(5) null 지우고 new 해서 하는 방법 추천
//                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() { // H(1) 변화 new On... Enter
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // 삭제
//                        mPeopleData.remove(info.position); // F(3) H(1)' info는 final로. 결과 final AdapterView.AdapterContextMenuInfo...
//                        // 업데이트
//                        mAdapter.notifyDataSetChanged(); // F(5) 베스트
//                    }
//                });
//                builder.setNegativeButton("아니오", this); // H(2) this 빨간 줄 H(3)의 implements... 후 implements method 결과 없어짐
//                builder.setIcon(R.drawable.federer);
//
////                AlertDialog dialog = builder.create();
////                dialog.show();
//
//                builder.create().show();


                showDefaultDialog(info); // I(2) I(1)에서 메소드로 뺀 결과



//                // 삭제
//                mPeopleData.remove(info.position); // F(3) F(3)과 F(5) H(1)'으로 이동
//                // 업데이트
//                mAdapter.notifyDataSetChanged(); // F(5) 베스트

                return true;
            case R.id.action_item2:
                Toast.makeText(this, "action 2", Toast.LENGTH_SHORT).show();

//                AlertDialog.Builder // I(1) builder 이름 겹침. final Alert.Builder builder ~ });까지 메소드로 뺌
                showCustomDialog(); // I(3) Create method

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void showCustomDialog() { // I(3)' I(3)의 결과

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

//        View view = LayoutInflater.from(this).inflate(R.layout.dialog_signin, null, false);
        View view = getLayoutInflater().inflate(R.layout.dialog_signin, null, false);
        builder.setView(view);

       final AlertDialog dialog = builder.create();

//        builder.setPositiveButton("예", null);
//        builder.setNegativeButton("아니오", null);

        view.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() { // new 다음 On... Enter
            @Override
            public void onClick(View v) {
                Toast.makeText(AdapterViewExamActivity.this, "잘 눌림", Toast.LENGTH_SHORT).show();
                // 다이얼로그 닫기
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.negative_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }



    private void showDefaultDialog(final AdapterView.AdapterContextMenuInfo info) { // I(2)' I(1)에서 메소드로 뺀 결과
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("삭제");
        builder.setMessage("정말로 삭제하시겠습니까?");
        // 바깥 부분 클릭했을 때 닫기
        builder.setCancelable(false);
//                builder.setPositiveButton("삭제", null);
//                builder.setPositiveButton("삭제", null); // H(5) null 지우고 new 해서 하는 방법 추천
        builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() { // H(1) 변화 new On... Enter
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 삭제
                mPeopleData.remove(info.position); // F(3) H(1)' info는 final로. 결과 final AdapterView.AdapterContextMenuInfo...
                // 업데이트
                mAdapter.notifyDataSetChanged(); // F(5) 베스트
            }
        });
        builder.setNegativeButton("아니오", this); // H(2) this 빨간 줄 H(3)의 implements... 후 implements method 결과 없어짐
        builder.setIcon(R.drawable.federer);

//                AlertDialog dialog = builder.create();
//                dialog.show();

        builder.create().show();
    }



    @Override
    public void onBackPressed() { // G(2)
        // 저장
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("weather", mWeatherEditText.getText().toString());

        // Commit the edits!
        editor.apply(); // 비동기

        // 뒤로 가기
        super.onBackPressed();
    }

    @Override // 비추천 방법 H(1)이 추천 방법
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                break;
        }
    }
}
