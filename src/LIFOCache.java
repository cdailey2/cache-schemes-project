// AUTHOR: COLIN DAILEY

import java.util.Stack;

public class LIFOCache extends HybridDataStructure {
    private Stack<Data> stack; // Maintains order (LIFO)

    public LIFOCache(int capacity) {
        super(capacity);
        this.stack = new Stack<Data>();
    }

    // PROTECTED METHODS //
    @Override
    protected void deleteItem() {
        Data data = stack.pop(); // deletes newest item in stack and identifies "key" to delete in map
        char key = data.getData();
        map.remove(key);
    }

    @Override
    protected void addItem(Data data) {
        char character = data.getData();

        map.put(character, data);
        stack.push(data);
    }

}
