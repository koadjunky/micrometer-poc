package eu.malycha.micrometerpoc;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MeteredService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeteredService.class);

    private final Counter serviceCounter;

    public MeteredService(MeterRegistry meterRegistry) {
        this.serviceCounter = meterRegistry.counter("service.counter", "type", "light");
    }

    @Scheduled(fixedRate = 100)
    public void perform() {
        serviceCounter.increment();
        LOGGER.info("Performing operation");
    }
}
