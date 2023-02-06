package eu.malycha.micrometerpoc;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Random;

@Component
public class MeteredService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeteredService.class);

    private final Counter serviceCounter;
    private final Timer serviceTimer;
    private final Gauge serviceGauge;
    private final Random random = new Random();

    private double value;

    public MeteredService(MeterRegistry meterRegistry) {
        this.serviceCounter = meterRegistry.counter("service.counter", "type", "light");
        this.serviceTimer = meterRegistry.timer("service.timer", "type", "light");
        // Non-fluent gauge API is kind of hard to understand. Fluent works better.
        this.serviceGauge = Gauge.builder("service.gauge", this, s -> s.value)
            .tag("type", "light")
            .register(meterRegistry);
    }

    @Scheduled(fixedRate = 100)
    public void perform() {
        serviceCounter.increment();
        serviceTimer.record(Duration.ofMillis(random.nextInt(1000)));
        value = random.nextDouble();
        LOGGER.info("Performing operation");
    }
}
