package sg.edu.np.madpractical2_zixian;

import static java.lang.Math.random;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    public static ArrayList<User> userList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DBHandler userdb = new DBHandler(this);

        Random random = new Random();

        //If list is empty, database is empty, initialize database. Else, no need initialize and send to recycler
        if (userdb.getUsers().isEmpty()){
            //Initializing users in database
            for (int i = 0; i < 20; i++){
                User newuser = new User();
                int randnameno = random.nextInt(1000000000 + 1000000000) - 1000000000;
                int descnumber = random.nextInt(1000000000 + 1000000000) - 1000000000;

                newuser.name = "Name " + randnameno;
                newuser.description = "Description " + descnumber;
                newuser.id = i + 1;

                if ((random.nextInt(2)) == 1){
                    newuser.followed = true;
                }
                else{
                    newuser.followed = false;
                }

                //userList.add(newuser);
                userdb.insertUser(newuser);
            }
        }
        userList = userdb.getUsers();

        RecyclerView rv = findViewById(R.id.recyclerview);
        UserViewsAdapter adapter = new UserViewsAdapter(userList);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);

        /*ImageView iv = findViewById(R.id.profilepic);       //How to get the right pfp for the user?
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(ListActivity.this);
                ad.setTitle("Profile").setMessage("help").setCancelable(true)
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("View", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int randint = random.nextInt(1000000000-1) + 1;
                                Intent sendint = new Intent(ListActivity.this, MainActivity.class);
                                sendint.putExtra("randomint", randint);
                                startActivity(sendint);
                            }
                        });
                ad.show();
            }
        });*/
    }
}