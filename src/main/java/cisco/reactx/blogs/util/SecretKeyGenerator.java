package cisco.reactx.blogs.util;

import java.security.Key;
import java.util.logging.Logger;

import javax.crypto.spec.SecretKeySpec;

public class SecretKeyGenerator implements KeyGenerator {

    private Logger logger = Logger.getLogger(getClass().getName());

    
    public Key generateKey() {
        String keyString = "cmad-blog-project";
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
        logger.info("#### generate a key : " + key);
        return key;
    }

}
