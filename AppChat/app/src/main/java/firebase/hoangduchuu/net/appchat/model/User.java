package firebase.hoangduchuu.net.appchat.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by hoang on 27/03/2017.
 */

@IgnoreExtraProperties
public class User {

    public String username;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(String email) {
        this.email = email;
    }


    @Exclude
    public Map<String, Object> toMaps() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userName", username);
        result.put("email", email);
        return result;
    }

}
