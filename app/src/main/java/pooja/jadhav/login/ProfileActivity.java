package pooja.jadhav.login;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static pooja.jadhav.login.Login.edt_username;
import static pooja.jadhav.login.Signup.db;

public class ProfileActivity extends AppCompatActivity {
    TextView editTextname, editTextrollno, editTextemail, editTextphone, editTextloc;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        db = openOrCreateDatabase("pooja", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS dbpooja(name VARCHAR,roll_no VARCHAR,email VARCHAR,user_phone VARCHAR,user_location VARCHAR,password VARCHAR);");
        editTextname = findViewById(R.id.editName);
        editTextrollno = findViewById(R.id.edRollNo);
        editTextemail = findViewById(R.id.edEmailID);
        editTextphone = findViewById(R.id.edPhone);
        editTextloc = findViewById(R.id.edLocation);

        cursor = db.rawQuery("SELECT * FROM dbpooja WHERE email='" + edt_username+ " ", null);
        if (cursor.moveToFirst()) {
            editTextname.setText(cursor.getString(1));
            editTextrollno.setText(cursor.getString(2));
            editTextemail.setText(cursor.getString(3));
            editTextphone.setText(cursor.getString(4));
            editTextloc.setText(cursor.getString(5));
        }
    }
    public void logout(View view) {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);

    }
}