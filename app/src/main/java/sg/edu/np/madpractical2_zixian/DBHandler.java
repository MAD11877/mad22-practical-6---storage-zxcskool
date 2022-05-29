package sg.edu.np.madpractical2_zixian;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context context){
        super(context, "UserDatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createsql = "CREATE TABLE User(Name TEXT, Description TEXT, Id INT, Followed INT)";
        sqLiteDatabase.execSQL(createsql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS User");
        onCreate(sqLiteDatabase);
    }

    public void insertUser(User user){
        SQLiteDatabase writetouserdb = this.getWritableDatabase();
        int followedint;

        if (user.followed){
            followedint = 1;
        }
        else{
            followedint = 0;
        }

        writetouserdb.execSQL(
                "INSERT INTO User VALUES(\"" + user.name + "\", \"" + user.description + "\", " +
                        "\"" + user.id + "\", \"" + followedint + "\")");
        writetouserdb.close();
    }

    public ArrayList<User> getUsers(){
        SQLiteDatabase getalluser = this.getReadableDatabase();
        ArrayList<User> usersFromDB = new ArrayList<>();

        String getallquery = "SELECT * FROM User";

        Cursor cursor = getalluser.rawQuery(getallquery, null);
        while (cursor.moveToNext()){
            User user = new User();
            user.name = cursor.getString(0);
            user.description = cursor.getString(1);
            user.id = cursor.getInt(2);
            int followednum = cursor.getInt(3);

            if (followednum == 1){
                user.followed = true;
            }
            else{
                user.followed = false;
            }

            usersFromDB.add(user);
        }

        getalluser.close();
        return usersFromDB;
    }

    public void updateUser(User user){
        SQLiteDatabase updateuserinfo = this.getWritableDatabase();

        //If user followed (value = 1), on click calls this function and set to 0.

        if (user.followed){
            String getquery = "UPDATE User SET Followed = 0 WHERE Id = \"" + user.id  +"\"";
            updateuserinfo.execSQL(getquery);
        }
        else{
            String getquery = "UPDATE User SET Followed = 1 WHERE Id = \"" + user.id + "\"";
            updateuserinfo.execSQL(getquery);
        }
        updateuserinfo.close();
    }
}
