package pooja.jadhav.login;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static pooja.jadhav.login.Signup.db;

public class Admin extends AppCompatActivity implements View.OnClickListener {
    EditText editRollno, editName, editEmail,editLoc,editPassword,editPhone;
    Button btnAdd, btnDelete, btnModify, btnView, btnViewAll, btnShowInfo;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        editRollno = findViewById(R.id.editRollNo);
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editemail);
        editPhone = findViewById(R.id.editphone);
        editLoc = findViewById(R.id.editLoc);
        editPassword = findViewById(R.id.edipass);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnModify = findViewById(R.id.btnModify);
        btnView = findViewById(R.id.btnView);
        btnViewAll = findViewById(R.id.btnViewAll);
        btnShowInfo = findViewById(R.id.btnShowInfo);

        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        btnShowInfo.setOnClickListener(this);
        db = openOrCreateDatabase("pooja", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS dbpooja(name VARCHAR,roll_no VARCHAR,email VARCHAR,user_phone VARCHAR,user_location VARCHAR,password VARCHAR);");    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                if (editName.getText().toString().trim().length() == 0 ||
                        editRollno.getText().toString().trim().length() == 0 ||
                        editEmail.getText().toString().trim().length() == 0 ||
                        editPhone.getText().toString().trim().length() == 0 ||
                        editLoc.getText().toString().trim().length() == 0 ||
                        editPassword.getText().toString().trim().length() == 0
                ) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    showMessage("Error", "Please enter all values");
                    return;
                }
                db.execSQL("INSERT INTO dbpooja VALUES('" + editName.getText() + "','" + editRollno.getText() + "','" + editEmail.getText() + "','" + editPhone.getText() + "','" + editLoc.getText() + "','" + editPassword.getText() + "' );");
                showMessage("Success", "Record added");
                clearText();
                break;
            case R.id.btnDelete:
                if (editRollno.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                cursor = db.rawQuery("SELECT * FROM dbpooja WHERE rollno='" + editRollno.getText() + "'", null);
                if (cursor.moveToFirst()) {
                    db.execSQL("DELETE FROM student WHERE rollno='" + editRollno.getText() + "'");
                    showMessage("Success", "Record Deleted");
                }
                break;
            case R.id.btnModify:
                if (editRollno.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                cursor = db.rawQuery("SELECT * FROM dbpooja WHERE rollno='" + editRollno.getText() + "'", null);
                if (cursor.moveToFirst()) {
                    db.execSQL("UPDATE student SET name='" + editName.getText() + "' roll_no='" + editRollno.getText() + "'email='" + editEmail.getText() + "'user_phone='" + editPhone.getText() + "'user_Loc'" + editLoc.getText() + "'password='" + editPassword.getText() + "' WHERE rollno='" + editRollno.getText() + "'");
                    showMessage("Success", "Record Modified");
                }
                break;
            case R.id.btnView:
                if (editRollno.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                cursor = db.rawQuery("SELECT * FROM dbpooja WHERE rollno='" + editRollno.getText() + "'", null);
                if (cursor.moveToFirst()) {
                    editName.setText(cursor.getString(1));
                    editEmail.setText(cursor.getString(2));
                } else {
                    showMessage("Error", "Invalid Rollno");
                    clearText();
                }
                break;

         case R.id.btnViewAll:
               /*    cursor = db.rawQuery("SELECT * FROM dbpooja ", null);
                if (cursor.getCount() == 0) {
                    showMessage("Error", "No Records Found");
                    return;
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (cursor.moveToNext()) {
                        buffer.append("Name   : " + cursor.getString(0) + "\n");
                        buffer.append("Rollno : " + cursor.getString(1) + "\n");
                        buffer.append("Email  : " + cursor.getString(2) + "\n");
                        buffer.append("Phone no  : " + cursor.getString(3) + "\n");
                        buffer.append("Location  : " + cursor.getString(4) + "\n");
                    }
                    showMessage("All Records !!", buffer.toString());
                }

                    break;
*/
            Intent intent=new Intent(Admin.this, ShowAll.class);
            startActivity(intent);
            break;

            case R.id.btnShowInfo:
                        showMessage("Student Record Application", "Developed By Pooja Jadhav");
                        break;


        }   }
        private void showMessage (String title, String msg){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(title);
            builder.setMessage(msg);
            builder.setIcon(R.mipmap.login_icon_round);

            builder.setCancelable(true);
            builder.show();
        }
        private void clearText () {
            editRollno.setText("");
            editName.setText("");
            editEmail.setText("");
            editRollno.requestFocus();
        }

    public void show(View view) {
        Intent intent=new Intent(Admin.this, ShowAll.class);
        startActivity(intent);
    }
}
