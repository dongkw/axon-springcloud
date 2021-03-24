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
import org.axonframework.extensions.springcloud.commandhandling.SpringCloudHttpBackupCommandRouter;
import org.axonframework.extensions.springcloud.commandhandling.mode.CapabilityDiscoveryMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.function.Predicate;

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

    @Bean
    public CommandRouter springCloudHttpBackupCommandRouter(
            DiscoveryClient discoveryClient,
            RestTemplate restTemplate,
            Registration localServiceInstance,
            @Value("${axon.distributed.spring-cloud.fallback-url}")
                    String messageRoutingInformationEndpoint) {
        return SpringCloudHttpBackupCommandRouter.builder()
                .discoveryClient(discoveryClient)
                .routingStrategy(new AnnotationRoutingStrategy())
                .restTemplate(restTemplate)
                .localServiceInstance(localServiceInstance)
                .messageRoutingInformationEndpoint(messageRoutingInformationEndpoint)
                .build();

    }
    @Bean
    @Primary
    public DistributedCommandBus distributedCommandBus(CommandRouter commandRouter,
                                                       CommandBusConnector commandBusConnector) {
        return DistributedCommandBus.builder()
                .commandRouter(commandRouter)
                .connector(commandBusConnector)
                .build();
    }
////
//    @Bean
//    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
//        return new RestTemplate(factory);
//    }
//
//    @Bean
//    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
//        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//        factory.setReadTimeout(5000);//ms
//        factory.setConnectTimeout(15000);//ms
//        return factory;
//    }
//    @Bean
//    public CommandRouter springCloudCommandRouter(DiscoveryClient discoveryClient, Registration localServiceInstance,
//                                                  CapabilityDiscoveryMode capabilityDiscoveryMode) {
//        return SpringCloudCommandRouter.builder().discoveryClient(discoveryClient)
//                .routingStrategy(new AnnotationRoutingStrategy()).localServiceInstance(localServiceInstance)
//                .capabilityDiscoveryMode(capabilityDiscoveryMode)
//                .serviceInstanceFilter(new Predicate<ServiceInstance>() {
//
//                    @Override
//                    public boolean test(ServiceInstance t) {
//                        return !t.getMetadata().isEmpty() && null != t.getMetadata().get("instance-type")
//                                && t.getMetadata().get("instance-type").equalsIgnoreCase("eda");
//                    }
//                }).build();
//    }

}
