package ru.fedusiv.files_server.redis;

public interface FilesRepository {

    void save(String filename, Long id, String name);

}
