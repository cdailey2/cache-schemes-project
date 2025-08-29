import java.util.List;

/**
 * @author Colin Dailey
 *
 * LFU.java
 *
 * This class implements a least-frequently-used cache scheme
 *
 */

public class LFU extends CacheScheme {
    public int numCollisions(int cacheSize, String word){
        int numCollisions = 0;
        LFUCache lc = new LFUCache(cacheSize);
        List<Character> charList = parseString(word);

        for (Character c : charList) {
            Data data = new Data(c);
            if(lc.handleData(data)) {
                numCollisions += 1;
            }
        }
        return numCollisions;
    }

}

