// AUTHOR: COLIN DAILEY

import java.util.HashMap;

abstract class HybridDataStructure {

    protected HashMap<Character,Data> map; // Common among all hybrid data structures in this project for O(1) search time
    protected int capacity; // Cache size

    // CONSTRUCTOR //
    public HybridDataStructure(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<Character, Data>();
    }

    // PUBLIC METHODS //
    // Skeleton code for data insertion. returns true when a collision occurs.
    public boolean handleData(Data data) {
        char character = data.getData();

        // Check cache hit first
        if (isCacheHit(character)) {
            handleCacheHit(data);
            return false;
        }

        // if cache hit doesn't occur and the cache is not full, it's a cache miss
        else if (!isCacheFull()) {
            handleCacheMiss(data);
            return false;
        }

        // Last possibility is cache collision
        else {
            handleCacheCollision(data);
            return true;
        }
    }

    //  PROTECTED METHODS //
    // Returns true if data is contained in hashmap, indicating cache hit
    protected boolean isCacheHit(Character key) {
        return map.containsKey(key);
    }

    // Returns true if max size of the cache is reached.
    protected boolean isCacheFull() { // requires coupling with data structure counterpart - only use before or after both are updated.
        return map.size() == capacity;
    }

    // CACHE HANDLING METHODS //
    protected void handleCacheHit(Data data) {
        return;
    }

    protected void handleCacheMiss(Data data) {
        addItem(data);
    }

    protected void handleCacheCollision(Data data) {
        deleteItem();
        addItem(data);
    }

    protected abstract void deleteItem();

    protected abstract void addItem(Data data);

}
