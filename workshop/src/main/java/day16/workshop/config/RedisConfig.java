package day16.workshop.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

// RedisConfig class contains Beans where spring can get from
@Configuration
public class RedisConfig {

    // Annotate the property parameters with @Value and indicate the property name
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private Optional<Integer> redisPort;
    @Value("${spring.redis.username}")
    private String redisUsername;
    @Value("${spring.redis.password}")
    private String redisPassword;

    // To allow autowiring using spring
    @Bean
    @Scope("singleton")
    public RedisTemplate<String, Object> redisTemplate() {

        //3 items needed to set up the redis template(RedisStandaloneConfiguration, JedisConnectionFactory, redisTemplate)
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        config.setPort(redisPort.get());
        config.setUsername(redisUsername);
        config.setPassword(redisPassword);
        config.setDatabase(0);

        // Setting up the Jedis Connection Factory
        final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);

        // Save the properties
        jedisFac.afterPropertiesSet();

        // Create the redis template
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(jedisFac);

        // Keys (Type String) will be serialised
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        // Values (Type Java Object) will be serialised using the JdkSerializer
        RedisSerializer<Object> objSerializer = new JdkSerializationRedisSerializer(getClass().getClassLoader());

        redisTemplate.setValueSerializer(objSerializer);
        redisTemplate.setHashValueSerializer(objSerializer);

        return redisTemplate;


        
    }
}
