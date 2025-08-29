// AUTHOR: COLIN DAILEY

import java.util.LinkedList;
import java.util.Queue;

public class FIFOCache extends HybridDataStructure {
    private Queue<Data> queue; // Maintains insertion order (FIFO)

    // CONSTRUCTOR //
    public FIFOCache(int capacity) {
        super(capacity);
        this.queue = new LinkedList<>();
    }

    // PROTECTED METHODS //

    // deletes 1st item of queue then the corresponding map key-value pair
    // Implement in subclasses because order determines deletion of map key-value pair
    @Override
    protected void deleteItem() {
        Data data = queue.poll(); // deletes oldest item in queue and identifies "key" to delete in map
        char key = data.getData();
        map.remove(key);
    }

    @Override
    protected void addItem(Data data) {
        char character = data.getData();

        map.put(character, data);
        queue.add(data);
    }
}
