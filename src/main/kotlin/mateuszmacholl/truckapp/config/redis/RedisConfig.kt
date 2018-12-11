package mateuszmacholl.truckapp.config.redis

import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.core.RedisTemplate


@Configuration
class RedisConfig(private val redisTemplate: RedisTemplate<String, String>) {
    private final fun clearDb() {
        redisTemplate.execute { connection ->
            connection.flushDb()
        }
    }
    init {
        clearDb()
    }
}