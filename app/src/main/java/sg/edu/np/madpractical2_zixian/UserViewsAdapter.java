package sg.edu.np.madpractical2_zixian;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserViewsAdapter extends RecyclerView.Adapter<UserViewHolder>{

    ArrayList<User> userList;

    public UserViewsAdapter(ArrayList<User> userList){
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_view, parent, false);
        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User data = userList.get(position);
        holder.name.setText(data.name);
        holder.description.setText(data.description);
        holder.pfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(view.getContext());
                ad.setTitle("Profile").setMessage(holder.name.getText());
                ad.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                ad.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(view.getContext(), MainActivity.class);
                        intent.putExtra("indexposition", holder.getAdapterPosition());
                        view.getContext().startActivity(intent);
                    }
                });
                ad.show();
            }
        });
        if (data.name.endsWith("7")){
            holder.bigpic.setVisibility(View.VISIBLE);
        }
        else{
            holder.bigpic.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

}
