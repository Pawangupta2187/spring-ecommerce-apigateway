package com.apigateway.apigateway.filters;



import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
@Component
public class FirstPreLastPostGlobalFilter
        implements GlobalFilter, Ordered {

//    final Logger logger =
//            LoggerFactory.getLogger(FirstPreLastPostGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {
//        Logger logger;
//        logger.info("First Pre Global Filter");
        long start = System.currentTimeMillis();
        System.out.println("Filter Called  "+start);
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    //logger.info("Last Post Global Filter");
                    long end = System.currentTimeMillis();
                    //logger.info("Global Post Filter executed");
                    long elapsedTime = System.currentTimeMillis() - start;
                    System.out.println("Post Filter>> "+end+" Total Time Taken "+elapsedTime+" ms");
                }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}