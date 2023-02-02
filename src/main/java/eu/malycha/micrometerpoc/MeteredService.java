package eu.malycha.micrometerpoc;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Random;

@Component
public class MeteredService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeteredService.class);

    private final Counter serviceCounter;
    private final Timer serviceTimer;
    private final Random random = new Random();

    public MeteredService(MeterRegistry meterRegistry) {
        this.serviceCounter = meterRegistry.counter("service.counter", "type", "light");
        this.serviceTimer = meterRegistry.timer("service.timer", "type", "light");
    }

    @Scheduled(fixedRate = 100)
    public void perform() {
        serviceCounter.increment();
        serviceTimer.record(Duration.ofMillis(random.nextInt(1000)));
        LOGGER.info("Performing operation");
    }
}
