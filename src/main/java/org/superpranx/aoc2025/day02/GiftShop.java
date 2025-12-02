package org.superpranx.aoc2025.day02;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.superpranx.util.FileReaderUtil;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TEN;
import static java.math.BigInteger.ZERO;

public class GiftShop {

  private static final BigInteger MINUS_ONE = ONE.negate();

  public static BigInteger giftShopOne(List<ProductCodeRange> codeRanges) {
    return codeRanges.stream()
        .map(GiftShop::calculateDoubles)
        .filter(doubles -> !MINUS_ONE.equals(doubles))
        .reduce(ZERO, BigInteger::add);
  }

  private static BigInteger calculateDoubles(ProductCodeRange codeRange) {
    if (codeRange.isValidRange()) {
      String validLower = codeRange.firstValidLower();
      String validUpper = codeRange.firstValidUpper();

      BigInteger start = new BigInteger(validLower.substring(0, validLower.length() / 2));
      BigInteger end = new BigInteger(validUpper.substring(0, validUpper.length() / 2));

      BigInteger doubles = ZERO;
      for (BigInteger i = start; i.compareTo(end) <= 0; i = i.add(ONE)) {
        int length = i.toString().length();
        BigInteger fullNumber = i.multiply(TEN.pow(length)).add(i);
        doubles = doubles.add(fullNumber);
      }

      return ProductCodeRange.lowerAdjustment(validLower)
          .add(doubles)
          .add(ProductCodeRange.upperAdjustment(validUpper));
    } else {
      return ONE.negate();
    }
  }

  public static BigInteger giftShopTwo(List<ProductCodeRange> codeRanges) {
    return codeRanges.stream()
        .map(GiftShop::calculateMultiples)
        .reduce(ZERO, BigInteger::add)
        .add(giftShopOne(codeRanges));
  }

  private static BigInteger calculateMultiples(ProductCodeRange codeRange) {
    BigInteger start = new BigInteger(codeRange.lower);
    BigInteger end = new BigInteger(codeRange.upper);

    BigInteger multiples = ZERO;
    for (BigInteger i = start; i.compareTo(end) <= 0; i = i.add(ONE)) {
      multiples = multiples.add(getLargestMultipleFor(i));
    }

    return multiples;
  }

  private static BigInteger getLargestMultipleFor(BigInteger number) {
    for (int parts = 2; parts <= number.toString().length(); parts++) {
      int numLength = number.toString().length();
      if (numLength % parts == 0) {
        String code = number.toString();
        boolean isValid = Strings.CI.equals(code, StringUtils.repeat(code.substring(0, code.length() / parts), parts));
        if (isValid && parts == 2) {
          return ZERO;
        } else if (isValid) {
          return number;
        }
      }
    }
    return ZERO;
  }

  public static List<ProductCodeRange> extractMovesFromFile(String inputFilePath) {
    return FileReaderUtil.getListOfLines(inputFilePath)
        .stream()
        .filter(StringUtils::isNotBlank)
        .map(codeRange -> new ProductCodeRange(codeRange.split("-")[0], codeRange.split("-")[1]))
        .toList();
  }

  public record ProductCodeRange(String lower, String upper) {

    String firstValidLower() {
      if (lower.length() % 2 == 0) {
        return lower;
      } else if ((lower.length() + 1) <= upper.length()) {
        return TEN.pow(lower.length()).toString();
      } else {
        return ZERO.toString();
      }
    }

    String firstValidUpper() {
      if (upper.length() % 2 == 0) {
        return upper;
      } else if ((upper.length() - 1) >= lower.length()) {
        return TEN.pow(upper.length() - 1).subtract(ONE).toString();
      } else {
        return ZERO.toString();
      }
    }

    static BigInteger lowerAdjustment(String code) {
      String firstPart = code.substring(0, code.length() / 2);
      String secondPart = code.substring(code.length() / 2);
      BigInteger firstNumber = new BigInteger(firstPart);
      BigInteger secondNumber = new BigInteger(secondPart);
      return secondNumber.compareTo(firstNumber) <= 0
          ? ZERO
          : MINUS_ONE.multiply(new BigInteger(firstPart + firstPart));
    }

    static BigInteger upperAdjustment(String code) {
      String firstPart = code.substring(0, code.length() / 2);
      String secondPart = code.substring(code.length() / 2);
      BigInteger firstNumber = new BigInteger(firstPart);
      BigInteger secondNumber = new BigInteger(secondPart);
      return secondNumber.compareTo(firstNumber) < 0
          ? MINUS_ONE.multiply(new BigInteger(firstPart + firstPart))
          : ZERO;
    }

    boolean isValidRange() {
      return !(Strings.CI.equals(this.firstValidLower(), "0") || Strings.CI.equals(this.firstValidUpper(), "0"));
    }
  }
}
