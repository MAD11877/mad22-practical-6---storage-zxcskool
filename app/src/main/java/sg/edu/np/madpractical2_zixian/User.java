package sg.edu.np.madpractical2_zixian;

public class User {
    public String name;
    public String description;
    public int id;
    public boolean followed;

    public User(String n, String d, int i, boolean f){
        this.name = n;
        this.description = d;
        this.id = i;
        this.followed = f;
    }

    public User() {
    }
}
