import java.util.List;

/**
 * @author Colin Dailey
 * LIFO.java
 *
 * This class implements a last-in, first-out cache scheme
 *
 */

public class LIFO extends CacheScheme{
    public int numCollisions(int cacheSize, String word){
        int numCollisions = 0;
        LIFOCache lc = new LIFOCache(cacheSize);
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
