package com.example.config;

import com.example.aggregate.InstructionAggr;
import org.axonframework.commandhandling.distributed.AnnotationRoutingStrategy;
import org.axonframework.commandhandling.distributed.CommandBusConnector;
import org.axonframework.commandhandling.distributed.CommandRouter;
import org.axonframework.commandhandling.distributed.DistributedCommandBus;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.extensions.springcloud.commandhandling.SpringCloudCommandRouter;
import org.axonframework.extensions.springcloud.commandhandling.mode.CapabilityDiscoveryMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

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


//    @Bean
//    public CommandGateway commandGatewayWithRetry(CommandBus commandBus) {
//
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
//        RetryScheduler rs = IntervalRetryScheduler
//                .builder()
//                .retryExecutor(scheduledExecutorService)
//                .maxRetryCount(5)
//                .retryInterval(1000)
//                .build();
//        CommandGateway commandGateway = DefaultCommandGateway
//                .builder()
//                .commandBus(commandBus)
//                .retryScheduler(rs)
//                .build();
//        return commandGateway;
//    }

}
