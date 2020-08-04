package pooja.jadhav.login;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {
    TextView tvfirstname, tvlastname, tvloc, tvemail, tvcontact;
    EditText edfirstname, edlastname, edloc, esemail, edcontact, edpass, edconfpass;
    static String s_name, s_roll_no, s_loc, s_email, s_phone, s_pass, s_cnf_pass;
    static SQLiteDatabase db;
    Button b1;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        tvfirstname = findViewById(R.id.textViewfn);
        tvlastname = findViewById(R.id.textViewln);
        tvemail = findViewById(R.id.textViewemailid);
        tvcontact = findViewById(R.id.textViewcon);
        tvloc = findViewById(R.id.textViewloc);

        edfirstname = findViewById(R.id.edFisrtPersonName);
        edlastname = findViewById(R.id.edLastTextPersonName);
        edloc = findViewById(R.id.edLocationPerson);
        esemail = findViewById(R.id.edEmailIDPerson);
        edcontact = findViewById(R.id.edPhone);
        edpass = findViewById(R.id.edPassword);
        edconfpass = findViewById(R.id.edConfPassword);
        b1 = findViewById(R.id.signupbutton);

        db = openOrCreateDatabase("pooja", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS dbpooja(name VARCHAR,roll_no VARCHAR,email VARCHAR,user_phone VARCHAR,user_location VARCHAR,password VARCHAR);");    }

    public void registeruser(View view) {
        s_name = edfirstname.getText().toString().toUpperCase().trim();
        s_roll_no = edlastname.getText().toString().toUpperCase().trim();
        s_loc = edloc.getText().toString().toUpperCase().trim();
        s_email = esemail.getText().toString().toLowerCase().trim();
        s_phone = edcontact.getText().toString().trim();
        s_pass = edpass.getText().toString().trim();
        s_cnf_pass = edconfpass.getText().toString().trim();

        if (s_name.isEmpty() || s_roll_no.isEmpty() || s_loc.isEmpty() || s_email.isEmpty() || s_phone.isEmpty()) {
            show_Message("Registration", "Invalid Input");
        } else if (s_pass.isEmpty() || s_cnf_pass.isEmpty()) {
            show_Message("Registration", "Invalid Input");
        } else if (s_pass.equals(s_cnf_pass)) {
            flag=1;
            show_Message("Registration", "Valid Input");

        } else {
            show_Message("Registration", "Password Not Valid ");
        }
    }

    private void show_Message(String title, String input)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(input);
        builder.setIcon(R.mipmap.login_icon_round);
        builder.setCancelable(false);

        builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if( flag==1)
                {
                    db.execSQL("INSERT INTO dbpooja VALUES('"+s_name+"','"+s_roll_no+"','"+s_email+"','"+s_phone+"','"+s_loc+"','"+s_pass+"');");
                    //show_Message_Confirmation("Success","Registration Done");
                    show_Message("Success","Record Added");
                    Intent intent_my1 = new Intent(Signup.this, Login.class);
                    startActivity(intent_my1);
                    builder.setCancelable(false);
                }
                else
                    {
                    show_Message("Failed","Registration failed");
                }
            }
        });
        builder.setNegativeButton("RETRY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent_my = new Intent(Signup.this, Signup.class);
                startActivity(intent_my);
                builder.setCancelable(false);
            }
        });
        builder.show();
    }



    }
