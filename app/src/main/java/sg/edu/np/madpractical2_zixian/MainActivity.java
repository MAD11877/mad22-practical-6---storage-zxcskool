package sg.edu.np.madpractical2_zixian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*User newuser = new User("World", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
            "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua", 1, false);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView greet = findViewById(R.id.greeting);
        TextView description = findViewById(R.id.description);
        Button followbtn = findViewById(R.id.followbtn);
        /*greet.setText("Hello " + newuser.name + "!");
        description.setText(newuser.description);
        followbtn.setText("Follow");*/

        DBHandler db = new DBHandler(this);

        Intent receive = getIntent();
        int index = receive.getIntExtra("indexposition", 1);
        User selecteduser = ListActivity.userList.get(index);

        greet.setText(selecteduser.name);
        description.setText(selecteduser.description);

        //Default check of followed/not
        if (selecteduser.followed){
            followbtn.setText("Followed");
        }
        else{
            followbtn.setText("Follow");
        }

        followbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecteduser.followed){
                    followbtn.setText("Followed");
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                    selecteduser.followed = true;
                    db.updateUser(selecteduser);
                }
                else if (selecteduser.followed){
                    followbtn.setText("Follow");
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
                    selecteduser.followed = false;
                    db.updateUser(selecteduser);
                }
            }
        });

        Button msgbtn = findViewById(R.id.messagebtn);
        msgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startmsg = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(startmsg);
            }
        });

    }
}