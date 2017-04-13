package firebase.hoangduchuu.net.appchat.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;

/**
 * Created by hoang on 27/03/2017.
 */
public class ChatItem {
    public String uID;
    public String chatContent;
    public String chatAuthor;

    public ChatItem() {
    }

    public ChatItem(String uID, String chatContent, String chatAuthor) {
        this.uID = uID;
        this.chatContent = chatContent;
        this.chatAuthor = chatAuthor;
    }


    public HashMap<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uID", uID);
        result.put("chatAuthor", chatAuthor);
        result.put("chatContent", chatContent);
        return result;
    }
}
