package com.chriniko.example.benchmark.test;

import com.chriniko.example.benchmark.domain.ExecutionPlan;
import com.chriniko.example.benchmark.domain.ExecutionPlanPerThread;
import com.chriniko.example.benchmark.domain.StupidHolder;
import org.openjdk.jmh.annotations.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class SecondTestApproach {

    /*
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(time = 3, timeUnit = TimeUnit.SECONDS, iterations = 3, batchSize = 1)
    @Measurement(iterations = 2, time = 10, timeUnit = TimeUnit.SECONDS, batchSize = 1)
    public void dummy() {

    }
    */

    @Fork(value = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1, time = 3, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 3, time = 3, timeUnit = TimeUnit.SECONDS)
    @Threads(value = Threads.MAX)
    public Optional<StupidHolder> linearSearch(ExecutionPlan executionPlan) {

        return executionPlan.dataset.stream()
                .filter(stupidHolder -> stupidHolder.getId().equals("768"))
                .findAny();
    }

    @Fork(value = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1, time = 3, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 3, time = 3, timeUnit = TimeUnit.SECONDS)
    @Threads(value = Threads.MAX)
    public StupidHolder mergesortAndBinarySearch(ExecutionPlanPerThread executionPlan) {

        executionPlan.dataset.sort(Comparator.comparing(StupidHolder::getId));

        int result = Collections.binarySearch(
                executionPlan.dataset,
                new StupidHolder(executionPlan.key),
                Comparator.comparing(StupidHolder::getId)
        );

        return result > 0 ? executionPlan.dataset.get(result) : null;
    }

    @Fork(value = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1, time = 3, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 3, time = 3, timeUnit = TimeUnit.SECONDS)
    @Threads(value = Threads.MAX)
    public StupidHolder mergesortOnceAndBinarySearch(ExecutionPlanPerThread executionPlan) {

        if (!executionPlan.sorted) {
            executionPlan.dataset.sort(Comparator.comparing(StupidHolder::getId));
            executionPlan.sorted = true;
        }
        int result = Collections.binarySearch(
                executionPlan.dataset,
                new StupidHolder(executionPlan.key),
                Comparator.comparing(StupidHolder::getId)
        );

        return result > 0 ? executionPlan.dataset.get(result) : null;


    }
}
