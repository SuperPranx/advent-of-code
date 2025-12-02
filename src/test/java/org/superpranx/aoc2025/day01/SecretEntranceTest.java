package org.superpranx.aoc2025.day01;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SecretEntranceTest extends TestCase {

  public SecretEntranceTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(SecretEntranceTest.class);
  }

  public void testNumberOfZeroResults() {
    assertEquals(
        989L, SecretEntrance.secretEntranceOne(
            SecretEntrance.extractMovesFromFile("aoc-2025/day01/input.txt")));
  }

  public void testNumberOfZeroPasses() {
    assertEquals(
        5941L, SecretEntrance.secretEntranceTwo(
            SecretEntrance.extractMovesFromFile("aoc-2025/day01/input.txt")));
  }
}
