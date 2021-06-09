package org.quickstart.metrics.micrometer;

import com.github.charithe.kafka.EphemeralKafkaBroker;
import com.github.charithe.kafka.KafkaHelper;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.kafka.KafkaClientMetrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Locale;
import java.util.Properties;

import static java.util.Collections.singletonList;

public class KafkaMetricsSample {
    private static final String TOPIC = "my-example-topic";

    // private static final String brokerList = "localhost:9092";
    private static final String brokerList = "172.16.48.179:9081,172.16.48.180:9081,172.16.48.181:9081";

    private static final long POLL_TIMEOUT = 100;

    public static void main(String[] args) throws Exception {
        EphemeralKafkaBroker broker = EphemeralKafkaBroker.create();
        broker.start();
        KafkaHelper kafkaHelper = KafkaHelper.createFor(broker);
        KafkaConsumer<String, String> consumer = kafkaHelper.createStringConsumer();
        KafkaProducer<String, String> producer = kafkaHelper.createStringProducer();

        // Producer<String, String> producer = createProducer();
        // Consumer<String, String> consumer = createConsumer();

        MeterRegistry registry = new SimpleMeterRegistry();
        new KafkaClientMetrics(consumer).bindTo(registry);
        new KafkaClientMetrics(producer).bindTo(registry);

        consumer.subscribe(singletonList(TOPIC));

        Flux.interval(Duration.ofMillis(10))//
            .doOnEach(n -> producer.send(new ProducerRecord<>(TOPIC, "hello", "world")))//
            .subscribe();

        for (; ; ) {
            consumer.poll(Duration.ofMillis(100));
            consumer.commitAsync();
        }
    }

    public static Producer<String, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "DemoProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);

        // 配置partition选择策略，可选配置
        // props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, SimplePartitioner.class.getName());

        // 2 构建滤器链
        // List<String> interceptors = new ArrayList<>();
        // interceptors.add(SimpleProducerInterceptor.class.getName());
        // props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);

        Producer<String, String> producer = new KafkaProducer<>(props);
        return producer;
    }

    public static Consumer<String, String> createConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "lengfeng.consumer.group");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false"); // 是否自动提交进度
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "3");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, OffsetResetStrategy.EARLIEST.name().toLowerCase(Locale.ROOT));
        // props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, IsolationLevel.READ_COMMITTED.name().toLowerCase(Locale.ROOT));

        // 配置partition分配策略，可选配置
        // props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, Collections.singletonList(SimplePartitionAssignor.class));

        // 2 构建滤器链
        // List<String> interceptors = new ArrayList<>();
        // interceptors.add(SimpleConsumerInterceptor.class.getName());
        // props.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);

        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        return consumer;
    }

}
