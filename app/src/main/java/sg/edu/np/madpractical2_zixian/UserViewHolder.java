package sg.edu.np.madpractical2_zixian;

import android.app.ListActivity;
import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView description;
    ImageView pfp;
    ImageView bigpic;

    public UserViewHolder(View view){
        super(view);
        name = view.findViewById(R.id.nameplaceholder);
        description = view.findViewById(R.id.descplaceholder);
        pfp = view.findViewById(R.id.profilepic);
        bigpic = view.findViewById(R.id.if7showpic);
    }
}
