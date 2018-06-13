package com.chriniko.example.benchmark;

import com.chriniko.example.benchmark.test.FirstTestApproach;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, RunnerException {

        // naive approach
        FirstTestApproach.commonSearchTechniques();

        // best approach
        /*

        Throughput results:

        # Run complete. Total time: 00:06:17

        Benchmark                                        (datasetSize)   Mode  Cnt       Score        Error  Units
        SecondTestApproach.linearSearch                        1000000  thrpt    3   36695.571 ± 259960.726  ops/s
        SecondTestApproach.linearSearch                        1500000  thrpt    3   45870.824 ±  67999.910  ops/s
        SecondTestApproach.mergesortAndBinarySearch            1000000  thrpt    3      23.485 ±    212.076  ops/s
        SecondTestApproach.mergesortAndBinarySearch            1500000  thrpt    3       2.242 ±      2.684  ops/s
        SecondTestApproach.mergesortOnceAndBinarySearch        1000000  thrpt    3  154720.349 ± 615134.520  ops/s
        SecondTestApproach.mergesortOnceAndBinarySearch        1500000  thrpt    3  139871.668 ± 256226.261  ops/s

         */
        org.openjdk.jmh.Main.main(args);

    }

}
