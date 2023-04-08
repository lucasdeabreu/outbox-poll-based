package dev.lucasdeabreu.outbox.pollbased.changelog;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Component
class ChangelogSynchronizerBackgroundJob {

    private final ChangelogSynchronizer changelogSynchronizer;

    @Transactional
    @Scheduled(initialDelay = 60, fixedDelay = 60, timeUnit = TimeUnit.SECONDS)
    public void start() {
        log.atInfo().log("Running background job");
        changelogSynchronizer.sync();
    }

}
