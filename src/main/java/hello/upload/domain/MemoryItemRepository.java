package hello.upload.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryItemRepository implements ItemRepository{
    private final Map<Long, Item> store = new HashMap<>();
    private long sequence = 0L;

    @Override
    public void save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
    }

    @Override
    public Item findById(Long id) {
        return store.get(id);
    }
}
