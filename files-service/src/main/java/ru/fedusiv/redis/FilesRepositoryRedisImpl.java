package ru.fedusiv.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

// redis-server.exe redis.windows.conf
// redis-cli.exe

/*

    <file type : user id> -> <file name : garbage>

 */

@Repository
public class FilesRepositoryRedisImpl implements FilesRepository {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(String filename, Long id, String name) {
        redisTemplate.opsForHash().put(String.valueOf(id), name, filename);
    }

    public String get(String filename, Long id) {
        return (String) redisTemplate.opsForHash().get(String.valueOf(id), filename);
    }

}
