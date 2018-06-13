package com.chriniko.example.benchmark.domain;

import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@State(value = Scope.Thread)
public class ExecutionPlanPerThread {

    @Param(value = {"500000", "1000000"})
    private int datasetSize;

    @Param(value = {"111134", "468000", "10"})
    public String key;

    public List<StupidHolder> dataset;

    public boolean sorted;

    @Setup(value = Level.Invocation)
    public void setup() {
        dataset = IntStream.rangeClosed(1, datasetSize)
                .boxed()
                .map(idx -> new StupidHolder(idx.toString(), "name_" + idx))
                .collect(Collectors.toList());

    }
}
