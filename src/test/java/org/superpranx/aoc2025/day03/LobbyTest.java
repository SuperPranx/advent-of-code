package org.superpranx.aoc2025.day03;

import java.math.BigInteger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class LobbyTest extends TestCase {

  public LobbyTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(LobbyTest.class);
  }

  public void testLobbyOne() {
    assertEquals(
        17100, Lobby.lobbyOne(
            Lobby.extractMovesFromFile("aoc-2025/day03/input.txt")));
  }

  public void testLobbyTwo() {
    assertEquals(
        new BigInteger("170418192256861"), Lobby.lobbyTwo(
            Lobby.extractMovesFromFile("aoc-2025/day03/input.txt")));
  }
}
