package org.superpranx.aoc2024.day02;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class RedNosedReactorAnalysisTest extends TestCase {

  public RedNosedReactorAnalysisTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(RedNosedReactorAnalysisTest.class);
  }

  public void testReportSafety() {
    assertEquals(
        334, RedNosedReactorAnalysis.checkReportsForSafety(
            RedNosedReactorAnalysis.extractListsFromInputFile(
                "aoc-2024/day02/input.txt")));
  }

  public void testReportSafetyWithTolerance() {
    assertEquals(
        400, RedNosedReactorAnalysis.checkReportsForSafetyWithTolerance(
            RedNosedReactorAnalysis.extractListsFromInputFile(
                "aoc-2024/day02/input.txt")));
  }
}
