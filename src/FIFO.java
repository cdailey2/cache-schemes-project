import java.util.ArrayList;
import java.util.List;

/**
 * @author Colin Dailey
 *
 * FIFO.java
 *
 * This class implements a first-in, first-out cache scheme.
 *
 */

public class FIFO extends CacheScheme {

    public int numCollisions(int cacheSize, String word) {
        int numCollisions = 0;
        FIFOCache fc = new FIFOCache(cacheSize);
        List<Character> charList = parseString(word);

        for (Character c : charList) {
            Data data = new Data(c);
            if(fc.handleData(data)) {
                numCollisions += 1;
            }
        }
        return numCollisions;
    }
}
