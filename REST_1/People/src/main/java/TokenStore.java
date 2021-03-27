import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

//a singleton class
public class TokenStore {
    private static TokenStore instance = new TokenStore();
    private Map<String, TokenData> tokenDataMap = new HashMap<String, TokenData>();

    private TokenStore(){   }

    public static TokenStore getInstance(){
        return instance;
    }

    public String putToken(String username) {
        String token  = UUID.randomUUID().toString();
        tokenDataMap.put(token, new TokenData(username) );
        return token;
    }

    public String getUsername(String token) {
        if (tokenDataMap.containsKey(token) ){
            if (tokenDataMap.get(token).expiryTime > System.currentTimeMillis() ) {
                return tokenDataMap.get(token).username;
            }
            else {
                tokenDataMap.remove(token);
            }
        }
        return  null;
    }

    public static class TokenData {
         String username;
        long expiryTime;

        public TokenData(String username){
            this.username = username;
            this.expiryTime = System.currentTimeMillis() + 5  * 60 * 1000;
        }
    }
}
