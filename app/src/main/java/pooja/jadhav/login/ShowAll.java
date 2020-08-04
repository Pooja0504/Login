package pooja.jadhav.login;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

import static pooja.jadhav.login.Signup.db;

public class ShowAll extends AppCompatActivity {
    int img_src[] = {R.drawable.child3, R.drawable.englisone,
            R.drawable.englishtwo, R.drawable.img2, R.drawable.img3,
            R.drawable.img4, R.drawable.mathone, R.drawable.mathtwo
            , R.drawable.oldmansay};
    Cursor cursor;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        db = openOrCreateDatabase("pooja", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS dbpooja(name VARCHAR,roll_no VARCHAR,email VARCHAR,user_phone VARCHAR,user_location VARCHAR,password VARCHAR);");
        final ArrayList<CustomClass> items = new ArrayList<>();
        Random random= new Random();
        int random_number=random.nextInt(9);
        cursor = db.rawQuery("SELECT * FROM dbpooja ", null);
        if (cursor.getCount() == 0) {
//                            showMessage("Error", "No Records Found");
            return;
        } else {

            while (cursor.moveToNext()) {
                random_number=random.nextInt(9);
                items.add(new CustomClass(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),img_src[random_number]));
               // random_number=random.nextInt(9);
            }
        }
//
        customAdapter = new CustomAdapter(ShowAll.this,items);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(customAdapter);
    }

    }

