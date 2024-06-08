package Repository;

import DatabaseManager.DatabaseManager;
import java.util.List;

public interface RepositoryInterface<T> {
    void create(T toAdd, DatabaseManager db);
    T findById(int id, DatabaseManager db);
    List<T> findAll(DatabaseManager db);
    void update(T toAdd, DatabaseManager db);
    void delete(int id, DatabaseManager db);
}
