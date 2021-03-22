package com.example.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @Author dongkw
 * @Date 2021/1/26、5:49 下午
 **/
@Slf4j
public class SyncController {

    private final Map<String, Object> responseMap;

    private final Map<String, CyclicBarrier> lockMap;

    public SyncController() {
        responseMap = new ConcurrentHashMap<>();
        lockMap = new ConcurrentHashMap<>();
    }

    public Object waitResponse(String requestId) {
        synchronized (requestId.intern()) {
            if (responseMap.get(requestId) != null) {
                return responseMap.remove(requestId);
            }
            lockMap.put(requestId, new CyclicBarrier(2));
        }
        try {
            lockMap.get(requestId).await(8, TimeUnit.SECONDS);
        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
            e.printStackTrace();
        }
        return responseMap.remove(requestId);
    }

    public void syncResponse(String requestId, Object response) {
        synchronized (requestId.intern()) {
            responseMap.put(requestId, response);
            if (Objects.isNull(lockMap.get(requestId))) {
                return;
            }
        }
        try {
            lockMap.get(requestId).await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        lockMap.remove(requestId);
        log.info("sync response:{},{}", requestId, response);

    }
}
