package org.superpranx.aoc2025.day05;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.superpranx.util.FileReaderUtil;

public class Cafeteria {

  public static int cafeteriaOne(List<Range> ranges, List<BigInteger> ids) {
    final List<Range> optimizedRanges = optimizeRanges(ranges);
    return ids.stream().mapToInt(id -> idValidityQualifier(optimizedRanges, id)).reduce(0, Integer::sum);
  }

  private static List<Range> optimizeRanges(List<Range> ranges) {
    List<Range> sorted = new ArrayList<>(ranges.stream()
        .sorted(Comparator.comparing((Range a) -> a.low).thenComparing(a -> a.high))
        .toList());
    List<Range> optimized = new ArrayList<>();

    optimized.add(sorted.get(0));
    sorted.remove(0);
    while (!sorted.isEmpty()) {
      int lastOptimizedIndex = optimized.size() - 1;
      Range lastOptimized = optimized.get(lastOptimizedIndex);
      if (lastOptimized.low.compareTo(sorted.get(0).low) < 1 && lastOptimized.high.compareTo(sorted.get(0).low) > -1) {
        optimized.remove(lastOptimizedIndex);
        optimized.add(new Range(lastOptimized.low, lastOptimized.high.max(sorted.get(0).high)));
      } else {
        optimized.add(sorted.get(0));
      }
      sorted.remove(0);
    }

    return optimized;
  }

  private static int idValidityQualifier(List<Range> ranges, BigInteger id) {
    return ranges.parallelStream().anyMatch(range -> range.low.compareTo(id) < 1 && range.high.compareTo(id) > -1)
        ? 1
        : 0;
  }

  public static BigInteger cafeteriaTwo(List<Range> ranges) {
    final List<Range> optimizedRanges = optimizeRanges(ranges);
    return optimizedRanges.stream()
        .map(range -> range.high.subtract(range.low).add(BigInteger.ONE))
        .reduce(BigInteger.ZERO, BigInteger::add);
  }

  public static List<BigInteger> extractIdsFromFile(String inputFilePath) {
    return FileReaderUtil.getListOfLines(inputFilePath).stream().map(BigInteger::new).toList();
  }

  public static List<Range> extractRangesFromFile(String inputFilePath) {
    return FileReaderUtil.getListOfLines(inputFilePath)
        .stream()
        .map(rangeString -> rangeString.split("-"))
        .map(ranges -> new Range(new BigInteger(ranges[0]), new BigInteger(ranges[1])))
        .toList();
  }

  public record Range(BigInteger low, BigInteger high) {

  }
}
