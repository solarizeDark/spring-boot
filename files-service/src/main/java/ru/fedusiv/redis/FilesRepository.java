package ru.fedusiv.redis;

public interface FilesRepository {

    void save(String filename, Long id, String name);
    String get(String filename, Long id);

}
