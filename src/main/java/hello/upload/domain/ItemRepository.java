package hello.upload.domain;

public interface ItemRepository {

    void save(Item item);

    Item findById(Long id);
}
