package org.superpranx.aoc2025.day04;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PrintingDepartmentTest extends TestCase {

  public PrintingDepartmentTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(PrintingDepartmentTest.class);
  }

  public void testLobbyOne() {
    assertEquals(
        1372, PrintingDepartment.printingDepartmentOne(
            PrintingDepartment.extractMovesFromFile("aoc-2025/day04/input.txt")));
  }

  public void testLobbyTwo() {
    assertEquals(
        7922, PrintingDepartment.printingDepartmentTwo(
            PrintingDepartment.extractMovesFromFile("aoc-2025/day04/input.txt")));
  }
}
