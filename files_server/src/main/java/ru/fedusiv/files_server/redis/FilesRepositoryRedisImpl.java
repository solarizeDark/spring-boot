package ru.fedusiv.files_server.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/*

    <user id> -> <file name : uuid tag + file name>

 */

@Repository
public class FilesRepositoryRedisImpl implements FilesRepository {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(String filename, Long id, String name) {
        redisTemplate.opsForHash().put(String.valueOf(id), name, filename);
    }



}
