package org.superpranx.aoc2025.day03;

import java.math.BigInteger;
import java.util.List;

import org.superpranx.util.FileReaderUtil;

public class Lobby {

  public static int lobbyOne(List<String> banks) {
    return banks.stream().mapToInt(Lobby::sumOfBank).reduce(0, Integer::sum);
  }

  private static int sumOfBank(String bank) {
    int tensIndex = -1;
    int maxTens = -1, maxOnes = -1;

    for (int i = 0; i < bank.length() - 1; i++) {
      int currentNumber = bank.charAt(i) - 48;
      if (currentNumber > maxTens) {
        maxTens = currentNumber;
        tensIndex = i;
      }
    }

    for (int j = bank.length() - 1; j > tensIndex; j--) {
      int currentNumber = bank.charAt(j) - 48;
      if (currentNumber > maxOnes) {
        maxOnes = currentNumber;
      }
    }

    return 10 * maxTens + maxOnes;
  }

  public static BigInteger lobbyTwo(List<String> banks) {
    return banks.parallelStream()
        .map(bank -> Lobby.findLargestBatteryForSize(bank, 12))
        .reduce(BigInteger.ZERO, BigInteger::add);
  }

  private static BigInteger findLargestBatteryForSize(String bank, int size) {
    int remainingBatteries = size;
    int startingIndex = 0;
    BigInteger batteryJoltage = BigInteger.ZERO;

    for (; remainingBatteries > 0; remainingBatteries--) {
      Battery nextBattery = findNextLargestBattery(bank, startingIndex, remainingBatteries);
      batteryJoltage = batteryJoltage.add(BigInteger.TEN.pow(remainingBatteries - 1)
          .multiply(BigInteger.valueOf(nextBattery.capacity)));
      startingIndex = nextBattery.position + 1;
    }

    return batteryJoltage;
  }

  private static Battery findNextLargestBattery(String bank, int startingIndex, int remaining) {
    if (remaining != 1) {
      // search forwards
      int max = -1, pos = -1;
      for (int i = startingIndex; i <= bank.length() - remaining; i++) {
        int currentNumber = bank.charAt(i) - 48;
        if (currentNumber > max) {
          max = currentNumber;
          pos = i;
        }
      }
      return new Battery(max, pos);
    } else {
      // search backwards
      int max = -1, pos = -1;
      for (int i = bank.length() - remaining; i >= startingIndex; i--) {
        int currentNumber = bank.charAt(i) - 48;
        if (currentNumber > max) {
          max = currentNumber;
          pos = i;
        }
      }
      return new Battery(max, pos);
    }
  }

  public static List<String> extractMovesFromFile(String inputFilePath) {
    return FileReaderUtil.getListOfLines(inputFilePath);
  }

  public record Battery(int capacity, int position) {

  }
}
