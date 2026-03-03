package com.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final int MAX_RETRY = 1;

    private static final ConcurrentHashMap<String, AtomicInteger> retryMap =
            new ConcurrentHashMap<>();

    @Override
    public boolean retry(ITestResult result) {

        String testName = result.getMethod().getMethodName();
        retryMap.putIfAbsent(testName, new AtomicInteger(0));

        int retryCount = retryMap.get(testName).incrementAndGet();

        return retryCount <= MAX_RETRY;
    }

    public static ConcurrentHashMap<String, AtomicInteger> getRetryStats() {
        return retryMap;
    }
}
