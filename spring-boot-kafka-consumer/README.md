# Kafka consumer

## 개별 테스트를 할때 

```text

./bin/kafka-console-consumer.sh --bootstrap-server localhost:19092 --topic Kafka_Example --from-beginning

./bin/kafka-console-producer.sh --broker-list localhost:19092 --topic Kafka_Example

```