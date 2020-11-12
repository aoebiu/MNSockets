package xyz.mengnan.MNSockets.config;

import io.enoa.toolkit.id.snowflake.SnowflakeKit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnowflakeConfig {
    @Bean
    public void setup() {
        SnowflakeKit.init(1, 1);
    }
}
