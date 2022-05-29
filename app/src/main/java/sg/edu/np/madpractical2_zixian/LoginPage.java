package sg.edu.np.madpractical2_zixian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.ChildEventListener;

public class LoginPage extends AppCompatActivity {

    public FirebaseAuth auth;
    //Get instance of database
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://madprac6-database-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference dbref = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        EditText usernamefield = findViewById(R.id.userinputfield);
        EditText passwordfield = findViewById(R.id.passwordinputfield);
        Button loginbtn = findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernamefield.getText().toString();
                String password = passwordfield.getText().toString();
                Log.i("userin", username);
                Log.i("passin", password);

                //If input is empty make toast
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    Toast.makeText(LoginPage.this, "Missing Input", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Db Reference, under Users > mad > get password and username to match with input
                    dbref.child("Users").child("mad").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String storedpass = snapshot.child("password").getValue(String.class);
                            String storedusername = snapshot.child("username").getValue(String.class);
                            Log.i("getpass", storedpass);
                            Log.i("getuser", storedusername);

                            //If Match, log in
                            if (username.equals(storedusername) && password.equals(storedpass)){
                                startActivity(new Intent(LoginPage.this, ListActivity.class));
                            }
                            //Doesnt match, dont allow
                            else{
                                Toast.makeText(LoginPage.this, "Wrong User/Pass", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    }
}