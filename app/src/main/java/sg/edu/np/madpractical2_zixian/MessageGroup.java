package sg.edu.np.madpractical2_zixian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MessageGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);

        Button grp1btn = findViewById(R.id.grp1btn);
        Button grp2btn = findViewById(R.id.grp2btn);

        grp1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.your_placeholder,new Grp1Fragment()).commit();
            }
        });

        grp2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.your_placeholder, new Grp2Fragment()).commit();
            }
        });
    }
}