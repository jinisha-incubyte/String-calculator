package com.incubyte.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCalculator {

  public int add(String input) {
    if (checkEmptyString(input)) {
      return 0;
    } else {
      List<String> numbers = getSeparators(input);

      return getAddition(numbers);
    }
  }

  private static boolean checkEmptyString(String input) {
    return input.equals("");
  }

  private static String refineInput(String input) {
    if (input.startsWith("//")) {
      return input.substring(input.indexOf("\n") + 1);
    }
    return input;
  }

  private List<String> checkSeparator(String input, String separators) {
    input = refineInput(input);
    List<String> numbers = new ArrayList<>();
    if (separators.length() != 0) {
      return Arrays.asList(getDifferentNumbers(input, separators));
    } else {
      numbers.add(input);
    }
    return numbers;
  }

  private static int getAddition(List<String> numbers) {
    int sum = 0;
    for (String number : numbers) {
      int value = Integer.parseInt(number);
      if (value < 0) {
        throw new RuntimeException("Negative numbers not allowed");
      }
      if (value > 1000) {
        continue;
      }
      sum += value;
    }
    return sum;
  }

  private List<String> getSeparators(String input) {
    String separatorString;
    StringBuilder separators = new StringBuilder();
    List<String> separatorsList = getSeparatorsList(input);
    if (separatorsList.isEmpty()) {
      separatorsList.add(input);
      return separatorsList;
    }
    for (String separator : separatorsList) {
      separators.append(separator).append("|");
    }
    separators = new StringBuilder(separators.substring(0, separators.length() - 1));
    separatorString = separators.toString();
    separatorString=separatorString.replace("*","\\*");
    return checkSeparator(input, separatorString);
  }

  List<String> getSeparatorsList(String input) {
    List<String> separator = new ArrayList<>();
    if (input.startsWith("//[")) {
      separator.addAll(getArbitraryLengthSeparator(input));
    } else if (input.startsWith("//")) {
      separator = getCustomSeparator(input);
    }
    separator.addAll(getDefaultSeparator(input));
    return separator;
  }

  private static List<String> getArbitraryLengthSeparator(String input) {
    List<String> seperator = new ArrayList<>();
    StringBuilder temp = new StringBuilder();
    for (int ip = 0; ip < input.length(); ip++) {

      if (input.charAt(ip) == '[') {
        temp = new StringBuilder();
        continue;
      }
      if (input.charAt(ip) == ']') {
        seperator.add(temp.toString());
        temp = new StringBuilder();
        continue;
      }
      temp.append(input.charAt(ip));

    }
    return seperator;
  }

  private static List<String> getCustomSeparator(String input) {
    List<String> separators = new ArrayList<>();
    separators.add(input.charAt(2) + "");
    return separators;
  }

  private static List<String> getDefaultSeparator(String input) {
    List<String> separators = new ArrayList<>();
    if (input.contains(",")) {
      separators.add(",");
    }
    if (input.contains("\n")) {
      separators.add("\n");
    }
    return separators;
  }

  public String[] getDifferentNumbers(String input, String separators) {
    String[] numbers;
    numbers = input.split(separators);
    return numbers;
  }
}
