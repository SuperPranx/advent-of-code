package org.superpranx.aoc2025.day05;

import java.math.BigInteger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CafeteriaTest extends TestCase {

  public CafeteriaTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(CafeteriaTest.class);
  }

  public void testLobbyOne() {
    assertEquals(
        775, Cafeteria.cafeteriaOne(
            Cafeteria.extractRangesFromFile("aoc-2025/day05/input-ranges.txt"),
            Cafeteria.extractIdsFromFile("aoc-2025/day05/input.txt")));
  }

  public void testLobbyTwo() {
    assertEquals(
        new BigInteger("350684792662845"),
        Cafeteria.cafeteriaTwo(Cafeteria.extractRangesFromFile("aoc-2025/day05/input-ranges.txt")));
  }
}
