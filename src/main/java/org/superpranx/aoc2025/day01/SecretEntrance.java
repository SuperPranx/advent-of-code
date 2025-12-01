package org.superpranx.aoc2025.day01;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.superpranx.util.FileReaderUtil;

public class SecretEntrance {

  public static int secretEntranceOne(List<Integer> moves) {
    List<Integer> normalizedMoves = moves.stream().map(move -> move % 100).toList();
    int result = 0;
    int state = 50;

    for (Integer normalizedMove : normalizedMoves) {
      int newState = state + normalizedMove;

      if (newState < 0) {
        state = newState + 100;
      } else if (newState > 99) {
        state = newState - 100;
      } else {
        state = newState;
      }

      if (state == 0) {
        result++;
      }
    }

    return result;
  }

  public static int secretEntranceTwo(List<Integer> moves) {
    int state = 50;
    int result = 0;

    for (int move : moves) {
      int newPos = state + move;

      if (move < 0) {
        if (newPos < 0) {
          result += Math.abs(newPos) / 100;
          if (state != 0 && newPos % 100 != 0) {
            result++;
          }
        }
      } else {
        result += newPos / 100;
        if (newPos % 100 == 0) {
          result--;
        }
      }

      state = (newPos % 100 + 100) % 100;

      if (state == 0) {
        result++;
      }
    }

    return result;
  }

  public static List<Integer> extractMovesFromFile(String inputFilePath) {
    return FileReaderUtil.getListOfLines(inputFilePath)
        .stream()
        .filter(StringUtils::isNotBlank)
        .map(move -> move.charAt(0) == 'R'
            ? Integer.parseInt(move.substring(1))
            : -Integer.parseInt(move.substring(1)))
        .toList();
  }
}
