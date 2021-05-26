package com.example.config;

import com.example.domain.aggregate.InstructionAggr;
import com.example.domain.aggregate.PledgeAggr;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import org.axonframework.commandhandling.distributed.AnnotationRoutingStrategy;
import org.axonframework.commandhandling.distributed.CommandBusConnector;
import org.axonframework.commandhandling.distributed.CommandRouter;
import org.axonframework.commandhandling.distributed.DistributedCommandBus;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.config.Configurer;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.MongoTemplate;
import org.axonframework.extensions.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoFactory;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoSettingsFactory;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.extensions.springcloud.commandhandling.SpringCloudCommandRouter;
import org.axonframework.extensions.springcloud.commandhandling.mode.CapabilityDiscoveryMode;
import org.axonframework.modelling.saga.repository.SagaStore;
import org.axonframework.serialization.Serializer;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @Author dongkw
 * @Date 2021/1/25、1:57 下午
 **/
@Component
public class AxonConfig {

    @Bean
    public EventSourcingRepository<InstructionAggr> instructionAggregateRepository(EventStore eventStore, Cache cache) {
        return EventSourcingRepository.builder(InstructionAggr.class)
                .cache(cache)
                .eventStore(eventStore)
                .build();
    }

    @Bean
    public EventSourcingRepository<PledgeAggr> pledgeRepository(EventStore eventStore, Cache cache) {
        return EventSourcingRepository.builder(PledgeAggr.class)
                .cache(cache)
                .eventStore(eventStore)
                .build();
    }

    @Bean
    public Cache cache() {
        return new WeakReferenceCache();
    }

    @Autowired
    public void config(EventProcessingConfigurer configurer) {
        configurer.registerTrackingEventProcessorConfiguration(
                c -> TrackingEventProcessorConfiguration.forParallelProcessing(2)
        );
//        configurer.usingSubscribingEventProcessors();
    }


//    @Bean
//    public CommandRouter springCloudHttpBackupCommandRouter(
//            DiscoveryClient discoveryClient,
//            RestTemplate restTemplate,
//            Registration localServiceInstance,
//            @Value("${axon.distributed.spring-cloud.fallback-url}")
//                    String messageRoutingInformationEndpoint,
//            @Value("${eureka.instance.metadata-map.instance-type}")
//                    String url) {
//        return SpringCloudHttpBackupCommandRouter.builder()
//                .discoveryClient(discoveryClient)
//                .routingStrategy(new AnnotationRoutingStrategy())
//                .restTemplate(restTemplate)
//                .localServiceInstance(localServiceInstance)
//                .serviceInstanceFilter(t -> {
//                    boolean b = !t.getMetadata().isEmpty() && null != t.getMetadata().get("instance-type")
//                            && t.getMetadata().get("instance-type").equalsIgnoreCase(url);
//                    return b;
//                })
//                .messageRoutingInformationEndpoint(messageRoutingInformationEndpoint)
//                .build();
//
//    }

    @Bean
    @Primary
    public DistributedCommandBus distributedCommandBus(CommandRouter commandRouter,
                                                       CommandBusConnector commandBusConnector) {
        return DistributedCommandBus.builder()
                .commandRouter(commandRouter)
                .connector(commandBusConnector)
                .build();
    }


    @Bean
    public CommandRouter springCloudCommandRouter(DiscoveryClient discoveryClient, Registration localServiceInstance,
                                                  @Qualifier("restCapabilityDiscoveryMode") CapabilityDiscoveryMode capabilityDiscoveryMode,
                                                  @Value("${eureka.instance.metadata-map.instance-type}") String url) {

        return SpringCloudCommandRouter.builder()
                .discoveryClient(discoveryClient)
                .routingStrategy(new AnnotationRoutingStrategy())
                .localServiceInstance(localServiceInstance)
                .capabilityDiscoveryMode(capabilityDiscoveryMode)
                .serviceInstanceFilter(t -> !t.getMetadata().isEmpty() && null != t.getMetadata().get("instance-type")
                        && t.getMetadata().get("instance-type").equalsIgnoreCase(url))
                .consistentHashChangeListener(t -> {
                }).build();
    }

    @Bean
    public MongoClient mongo() {
        MongoFactory mongoFactory = new MongoFactory();
        MongoSettingsFactory mongoSettingsFactory = new MongoSettingsFactory();
        mongoSettingsFactory.setMongoAddresses(Collections.singletonList(new ServerAddress("localhost", 27017)));
        mongoFactory.setMongoClientSettings(mongoSettingsFactory.createMongoClientSettings());

        return mongoFactory.createMongo();
    }

    @Bean
    public MongoTemplate axonMongoTemplate() {
        return DefaultMongoTemplate.builder()
                .mongoDatabase(mongo(), "axon1")
                .build();
    }

    @Autowired
    public void configuration(Configurer configurer, MongoClient client, Serializer serializer) {
        configurer.configureEmbeddedEventStore(t -> storageEngine(client))
                .eventProcessing(conf -> conf.registerTokenStore(t -> tokenStore(serializer)));
    }

    @Bean
    public TokenStore tokenStore(Serializer serializer) {
        return MongoTokenStore.builder()
                .mongoTemplate(axonMongoTemplate())
                .serializer(serializer)
                .build();
    }

    @Bean
    public SagaStore sagaStore(Serializer serializer) {
        return MongoSagaStore.builder()
                .mongoTemplate(axonMongoTemplate())
                .serializer(serializer)
                .build();
    }

    @Bean
    public EventStorageEngine storageEngine(MongoClient client) {
        return MongoEventStorageEngine.builder()
                .mongoTemplate(DefaultMongoTemplate.builder()
                        .mongoDatabase(client)
                        .build())
                .build();
    }

    @Bean
    public EmbeddedEventStore eventStore(EventStorageEngine storageEngine, AxonConfiguration configuration) {
        return EmbeddedEventStore.builder()
                .storageEngine(storageEngine)
                .messageMonitor(configuration.messageMonitor(EventStore.class, "eventStore"))
                .build();
    }

}
