package com.wang.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        //自定义检查方法
      //  return Health.up().build();
        return Health.down().withDetail("msg","服务异常").build();
    }
}
