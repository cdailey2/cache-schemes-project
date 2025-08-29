// AUTHOR: COLIN DAILEY

import java.util.PriorityQueue;

public class LFUCache extends HybridDataStructure {
    private PriorityQueue<Data> pq;
    int time;

    // CONSTRUCTOR //
    public LFUCache(int capacity) {
        super(capacity);
        this.pq = new PriorityQueue<Data>();
        this.time = 0;
    }

    // PROTECTED METHODS //

    @Override
    protected void handleCacheHit(Data data) {
        char key = data.getData();
        Data storedData = map.get(key);
        storedData.addFrequency();
        pq.remove(storedData);
        pq.add(storedData);
    }

    @Override
    protected void deleteItem() {
        Data data = pq.poll();
        char key = data.getData();
        map.remove(key);
    }

    @Override
    protected void addItem(Data data) {
        char character = data.getData();
        incrementTime(data);
        map.put(character, data);
        pq.add(data);
    }

    // PRIVATE METHODS //

    private void incrementTime(Data data) {
        time = time + 1;
        data.setTime(time);
    }


}
