package org.superpranx.aoc2025.day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.superpranx.util.FileReaderUtil;

public class PrintingDepartment {

  private static final List<Coordinates> removableBoxes = new ArrayList<>();

  public static int printingDepartmentOne(List<String> grid) {
    int reachableRolls = 0;
    for (int i = 0; i < grid.size(); i++) {
      String currentRow = grid.get(i);
      for (int j = 0; j < currentRow.length(); j++) {
        if (grid.get(i).charAt(j) != '@') {
          continue;
        }
        reachableRolls += rollQualifier(grid, i, j);
      }
    }
    return reachableRolls;
  }

  private static int rollQualifier(List<String> grid, int i, int j) {
    int rollsAround = Arrays.stream(Directions.values()).mapToInt(direction -> {
      int i1 = i + direction.di;
      int j1 = j + direction.dj;
      if (i1 < 0 || i1 >= grid.size() || j1 < 0 || j1 >= grid.get(i1).length()) {
        return 0;
      } else {
        return grid.get(i1).charAt(j1) == '@' ? 1 : 0;
      }
    }).reduce(0, Integer::sum);
    if (rollsAround < 4) {
      removableBoxes.add(new Coordinates(i, j));
      return 1;
    } else {
      return 0;
    }
  }

  public static int printingDepartmentTwo(List<String> grid) {
    List<String> modifiableGrid = new ArrayList<>(grid);
    removableBoxes.clear();
    int newRemovals = -1;
    int removedUntil = 0;
    while (newRemovals != 0) {
      newRemovals = printingDepartmentOne(modifiableGrid);
      int newMaxIndex = removedUntil + newRemovals;
      for (; removedUntil < newMaxIndex; removedUntil++) {
        Coordinates toBeRemoved = removableBoxes.get(removedUntil);
        char[] update = modifiableGrid.get(toBeRemoved.i).toCharArray();
        update[toBeRemoved.j] = 'x';
        modifiableGrid.set(toBeRemoved.i, new String(update));
      }
    }
    return removableBoxes.size();
  }

  public static List<String> extractMovesFromFile(String inputFilePath) {
    return FileReaderUtil.getListOfLines(inputFilePath);
  }

  private enum Directions {
    NW(-1, -1),
    N(-1, 0),
    NE(-1, 1),
    E(0, 1),
    SE(1, 1),
    S(1, 0),
    SW(1, -1),
    W(0, -1);

    final int di, dj;

    Directions(int di, int dj) {
      this.di = di;
      this.dj = dj;
    }
  }

  private record Coordinates(int i, int j) {

  }
}
