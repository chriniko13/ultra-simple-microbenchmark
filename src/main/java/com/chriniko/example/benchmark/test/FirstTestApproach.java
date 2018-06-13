package com.chriniko.example.benchmark.test;

import com.chriniko.example.benchmark.domain.StupidHolder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FirstTestApproach {

    private static final int DATASET_SIZE = 1_000_000;
    private static final String KEY_TO_SEARCH_FOR = "568";
    private static int SHUFFLE_ROUNDS = 3;
    private static int EXEC_ROUNDS = 10;

    public static void commonSearchTechniques() {

        // print info phase
        System.out.println("* Dataset size: " + DATASET_SIZE);
        System.out.println("* Key to search for: " + KEY_TO_SEARCH_FOR);
        System.out.println("* Shuffling rounds: " + SHUFFLE_ROUNDS);
        System.out.println("* Execution rounds: " + EXEC_ROUNDS);

        System.out.println("========================================\n");


        // prepare phase
        final long startPrepareTime = System.nanoTime();

        List<StupidHolder> stupidHolders = IntStream.rangeClosed(1, DATASET_SIZE)
                .boxed()
                .map(idx -> new StupidHolder(idx.toString(), "name_" + idx))
                .collect(Collectors.toList());

        IntStream.rangeClosed(1, SHUFFLE_ROUNDS)
                .forEach(idx -> Collections.shuffle(stupidHolders));

        long totalPrepareTime = System.nanoTime() - startPrepareTime;
        long totalPrepareTimeInMs = TimeUnit.MILLISECONDS.convert(totalPrepareTime, TimeUnit.NANOSECONDS);
        System.out.println("* Total prepare time in ms: " + totalPrepareTimeInMs + " (" + totalPrepareTime + " ns)");
        System.out.println("========================================\n");


        // 1st approach - actual phase
        System.out.println("Linear[O(n)] search approach:");
        for (int i = 0; i < EXEC_ROUNDS; i++) {

            final long startTime = System.nanoTime();
            stupidHolders.stream()
                    .filter(stupidHolder -> stupidHolder.getId().equals(KEY_TO_SEARCH_FOR))
                    .findAny()
                    .ifPresent(result -> {

                        long totalTime = System.nanoTime() - startTime;
                        long totalTimeInMs = TimeUnit.MILLISECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
                        System.out.println("* Total time in ms: " + totalTimeInMs + " (" + totalTime + " ns)");


                    });
        }
        System.out.println("========================================\n");


        // 2nd approach - actual phase
        System.out.println("Sort[O(nlogn)] and BinarySearch[O(logn)] approach:");
        for (int i = 0; i < EXEC_ROUNDS; i++) {

            final long startTime = System.nanoTime();

            stupidHolders.sort(Comparator.comparing(StupidHolder::getId));
            int result = Collections.binarySearch(
                    stupidHolders,
                    new StupidHolder(KEY_TO_SEARCH_FOR),
                    Comparator.comparing(StupidHolder::getId)
            );
            if (result > 0) {

                long totalTime = System.nanoTime() - startTime;
                long totalTimeInMs = TimeUnit.MILLISECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
                System.out.println("* Total time in ms: " + totalTimeInMs + " (" + totalTime + " ns)");
            }
        }
        System.out.println("========================================\n");

        // Important Note: We should reshuffle here because our dataset is sorted.
        IntStream.rangeClosed(1, SHUFFLE_ROUNDS)
                .forEach(idx -> Collections.shuffle(stupidHolders));


        // 3rd approach - actual phase
        System.out.println("Sort[O(nlogn)] Just Once and BinarySearch[O(logn)] approach:");
        for (int i = 0; i < EXEC_ROUNDS; i++) {

            final long startTime = System.nanoTime();

            if (i == 0) {
                stupidHolders.sort(Comparator.comparing(StupidHolder::getId));
            }

            int result = Collections.binarySearch(
                    stupidHolders,
                    new StupidHolder(KEY_TO_SEARCH_FOR),
                    Comparator.comparing(StupidHolder::getId)
            );
            if (result > 0) {

                long totalTime = System.nanoTime() - startTime;
                long totalTimeInMs = TimeUnit.MILLISECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
                System.out.println("* Total time in ms: " + totalTimeInMs + " (" + totalTime + " ns)");
            }
        }
        System.out.println("========================================\n");

    }

}
