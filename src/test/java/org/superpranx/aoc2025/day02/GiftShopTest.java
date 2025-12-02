package org.superpranx.aoc2025.day02;

import java.math.BigInteger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GiftShopTest extends TestCase {

  public GiftShopTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(GiftShopTest.class);
  }

  public void testNumberOfDoubles() {
    assertEquals(
        new BigInteger("19574776074"), GiftShop.giftShopOne(
            GiftShop.extractMovesFromFile("aoc-2025/day02/input.txt")));
  }

  public void testNumberOfZeroPasses() {
    assertEquals(
        new BigInteger("25912654282"), GiftShop.giftShopTwo(
            GiftShop.extractMovesFromFile("aoc-2025/day02/input.txt")));
  }
}
