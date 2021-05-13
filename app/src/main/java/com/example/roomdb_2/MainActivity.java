package com.example.roomdb_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtT;
    Button btnAdd,btnRemove;
    ListView listView;
    CustomAdapter adapter;
    List<User> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtT = findViewById(R.id.edtName);
        btnAdd=findViewById(R.id.btnAdd);
        btnRemove = findViewById(R.id.btnRemove);
        listView = findViewById(R.id.lvName);
        users = new ArrayList<>();
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "user").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();


//        users.add(new User("MInh"));
//        users.add(new User("Dao"));
        users = userDao.getAll();
        adapter = new CustomAdapter(users,R.layout.customitem,this);
        Log.d("lisuser",users+"");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDao.insertUser(new User(edtT.getText().toString()));
                //userDao.deleteAll();
                recreate();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userDao.deleteAll();
                recreate();
            }
        });
//        User u = new User("An");
        //userDao.insertUser(u);

        listView.setAdapter(adapter);
        //listView.invalidateViews();

    }
}