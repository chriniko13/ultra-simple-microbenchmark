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

        # Run complete. Total time: 00:04:40

        Benchmark                                        (datasetSize)  (key)   Mode  Cnt       Score         Error  Units
        SecondTestApproach.linearSearch                         500000    134  thrpt    3   65657.904 ±  890054.705  ops/s
        SecondTestApproach.linearSearch                         500000    768  thrpt    3   76553.557 ± 1421337.341  ops/s
        SecondTestApproach.linearSearch                        1000000    134  thrpt    3   34745.095 ±  238010.857  ops/s
        SecondTestApproach.linearSearch                        1000000    768  thrpt    3   35292.404 ±  203400.985  ops/s
        SecondTestApproach.mergesortAndBinarySearch             500000    134  thrpt    3      89.454 ±     300.818  ops/s
        SecondTestApproach.mergesortAndBinarySearch             500000    768  thrpt    3      54.220 ±     319.437  ops/s
        SecondTestApproach.mergesortAndBinarySearch            1000000    134  thrpt    3      44.413 ±     157.998  ops/s
        SecondTestApproach.mergesortAndBinarySearch            1000000    768  thrpt    3      39.992 ±     199.542  ops/s
        SecondTestApproach.mergesortOnceAndBinarySearch         500000    134  thrpt    3  201803.306 ±  358776.710  ops/s
        SecondTestApproach.mergesortOnceAndBinarySearch         500000    768  thrpt    3  265699.696 ±  629093.875  ops/s
        SecondTestApproach.mergesortOnceAndBinarySearch        1000000    134  thrpt    3  169991.087 ±  697320.433  ops/s
        SecondTestApproach.mergesortOnceAndBinarySearch        1000000    768  thrpt    3  158705.845 ±  309665.407  ops/s

         */
        org.openjdk.jmh.Main.main(args);

    }

}
