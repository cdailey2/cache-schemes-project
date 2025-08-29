import java.util.List;

/**
 * @author Colin Dailey
 *
 * FIF.java
 *
 *
 * This class implements the Cache Scheme Furthest in Future
 *
 * It is not a realistic cache scheme but rather an optimal scheme
 * for benchmarking.  It "cheats" by looking into the future!
 *
 * You do NOT have to use the Data class for this, nor do you have to worry about
 * run time.
 */


public class FIF extends CacheScheme {

    public int numCollisions(int cacheSize, String word){
        int numCollisions = 0;
        FIFCache fc = new FIFCache(cacheSize, word);
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
