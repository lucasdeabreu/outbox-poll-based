package dev.lucasdeabreu.outbox.pollbased.changelog;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
class ChangelogSynchronizer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ChangelogEntityRepository changelogEntityRepository;
    private final int limit;
    private final String topic;

    public ChangelogSynchronizer(final KafkaTemplate<String, String> kafkaTemplate,
                                 final ChangelogEntityRepository changelogEntityRepository,
                                 @Value("${changelog.synchronizer.limit}") final int limit,
                                 @Value("${changelog.synchronizer.topic}") final String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.changelogEntityRepository = changelogEntityRepository;
        this.limit = limit;
        this.topic = topic;
    }

    @Transactional
    public void sync() {
        log.atInfo().log("Starting to synchronizing the changelog");
        changelogEntityRepository.updateToDoneAndReturnWithLimit(limit).forEach(this::sync);
    }

    public void sync(final ChangelogEntity changelog) {
        log.atInfo().log("Synchronizing {}", changelog);
        final ProducerRecord<String, String> record = new ProducerRecord<>(topic, changelog.getData());
        record.headers().add("TABLE", changelog.getTableName().getBytes());
        record.headers().add("ACTION", changelog.getEventType().getBytes());
        kafkaTemplate.send(record);
    }

}
