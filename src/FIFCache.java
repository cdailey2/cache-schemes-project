// AUTHOR: COLIN DAILEY //

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class FIFCache extends HybridDataStructure {
    int time;
    PriorityQueue<Data> pq;
    DataLinkedList dataLinkedList;
    ArrayList<Data> cacheArrayList;
    ArrayList<Data> oracleArrayList;


    // CONSTRUCTOR //
    public FIFCache(int capacity, String word) {
        super(capacity);
        this.time = 0;
        this.cacheArrayList = new ArrayList<>(); // used for looping through cache
        this.oracleArrayList = loadOracleArrayList(word); // used to see into the future of the word
    }

    // PROTECTED METHODS //

    @Override
    protected void handleCacheHit(Data data) {
        oracleArrayList.remove(0);
    }

    @Override
    protected void handleCacheMiss(Data data) {
        super.handleCacheMiss(data);
        oracleArrayList.remove(0);
    }

    @Override
    protected void handleCacheCollision(Data data) {
        super.handleCacheCollision(data);
        oracleArrayList.remove(0);
    }

    @Override
    protected void addItem(Data data) { // edit
        char character = data.getData();
        incrementTime(data);
        map.put(character, data);
        cacheArrayList.add(data);
    }

    @Override
    protected void deleteItem() { // Don't think this is right... need to find FIF first
        Data data = determineFIF();
        char key = data.getData();
        map.remove(key);
        cacheArrayList.remove(getLetterIndex(data));
    }


    // PRIVATE METHODS //

    private int getLetterIndex(Data data) {
        for (int i = 0; i < cacheArrayList.size(); i++) {
            if (cacheArrayList.get(i).getData() == data.getData()) {
                return i;
            }
        }
        return 0;
    }

    // Loads the oracle array with letters of word
    private ArrayList<Data> loadOracleArrayList(String word) {
        ArrayList<Data> oracleArrayList = new ArrayList<>();
        for (char c : word.toCharArray()) {
            oracleArrayList.add(new Data(c));
        }
        return oracleArrayList;
    }

    private void incrementTime(Data data) {
        time = time + 1;
        data.setTime(time);
    }

    // Uses priority queue to return oldest among all FIF candidates
    private Data determineFIF() {
        PriorityQueue<Data> FIFCandidates = getFIFCandidates();
        Data fif = FIFCandidates.poll();
        return fif;
    }

    // Finds the farthest in future character(s) within the total word
    // Make this create a subset of FIF...
    private PriorityQueue<Data> getFIFCandidates() {
        PriorityQueue<Data> FIFCandidates = generateCachedLettersNotRepeated(); // will initialize either an empty pq or a pq with non-repeated letters if they exist
        if (!FIFCandidates.isEmpty()) { // if non-repeated letters exists in the cache these are considered FIF
            return FIFCandidates;
        }
        else { // Will return a pq with single FIF candidate
            FIFCandidates = findFIFLetter();
            return FIFCandidates;
        }
    }

    // Generates the letters in cache which do not repeat for the rest of the word (1st order candidates for FIF)
    private PriorityQueue<Data> generateCachedLettersNotRepeated() { // Problem
        PriorityQueue<Data> cachedLettersNotRepeated = new PriorityQueue<>();

        for (int i = 0; i < cacheArrayList.size(); i++) {
            Data currElement = cacheArrayList.get(i);
            boolean dataExists = false;
            for (int j = 0; j < oracleArrayList.size(); j++) {
                if (currElement.getData() == oracleArrayList.get(j).getData()) {
                    dataExists = true;
                }
            }
            if (!dataExists) {
                cachedLettersNotRepeated.add(currElement);
            }
        }
        return cachedLettersNotRepeated;
    }

    // returns the letter existing in the cache with the largest index of first appearance.
    // returns as a pq of length 1 -> no ties can possibly exist
    private PriorityQueue<Data> findFIFLetter() {

        PriorityQueue<Data> FIFLetter = new PriorityQueue<>();
        int largestFirstIndex = 0;
        for (int i = 0; i < cacheArrayList.size(); i++) {
            boolean letterFoundInOracle = false;
            for(int j = 0; j < oracleArrayList.size(); j++) {
                if ((!letterFoundInOracle) && (cacheArrayList.get(i).getData() == (oracleArrayList.get(j).getData()))) {
                    letterFoundInOracle = true;
                    if (j > largestFirstIndex) {
                        largestFirstIndex = j;
                    }
                }
            }
        }
        FIFLetter.add(oracleArrayList.get(largestFirstIndex));
        return FIFLetter;
    }

}
