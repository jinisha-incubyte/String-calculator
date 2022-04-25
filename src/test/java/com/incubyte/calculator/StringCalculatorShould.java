package com.incubyte.calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class StringCalculatorShould {

  private StringCalculator stringCalculator;

  @BeforeEach
  public void init() {
    stringCalculator = new StringCalculator();
  }

  @Test
  public void return_0_for_empty_string() {
    Assertions.assertEquals(stringCalculator.add(""), 0);
  }

  @Test
  public void return_number_for_single_number() {
    Assertions.assertEquals(stringCalculator.add("4"), 4);
  }

  @Test
  public void add_numbers_for_more_than_two_digits() {
    Assertions.assertEquals(stringCalculator.add("1,2,3"), 6);
  }

  @Test
  public void add_numbers_for_multiple() {
    Assertions.assertEquals(stringCalculator.add("1,2,3"), 6);
  }

  @Test
  public void add_numbers_for_newline_separator() {
    Assertions.assertEquals(stringCalculator.add("1\n2"), 3);
  }

  @Test
  public void add_numbers_for_custom_seperator() {
    Assertions.assertEquals(stringCalculator.add("//:\n1\n2"), 3);
  }

  @Test
  void throw_exception_if_negative_number_passed() {
    RuntimeException runtimeException =
        assertThrows(RuntimeException.class, () -> stringCalculator.add("//:\n1\n-22"));
    assertThat(runtimeException.getMessage()).isEqualTo("Negative numbers not allowed");
  }

  @Test
  void ignore_numbers_grater_than_1000() {
    Assertions.assertEquals(stringCalculator.add("1\n20000"), 1);
  }

  @Test
  void support_arbitrary_length_separator() {
    Assertions.assertEquals(stringCalculator.add("//[;;;]\n1;;;2"), 3);
  }

  @Test
  void support_arbitrary_length_separator_for_multiple() {
    Assertions.assertEquals(stringCalculator.add("//[;;;][***]\n1;;;2***4"), 7);
  }
}
