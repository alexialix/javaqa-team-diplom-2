package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    // Тесты для конструктора
    @Test
    public void shouldCreateValidAccount() {
        CreditAccount account = new CreditAccount(0, 5000, 15);

        Assertions.assertEquals(0, account.getBalance());
        Assertions.assertEquals(5000, account.getCreditLimit());
        Assertions.assertEquals(15, account.getRate());
    }

    @Test
    public void shouldHandleWithZeroRate() {
        CreditAccount account = new CreditAccount(0, 5000, 0);
        Assertions.assertEquals(0, account.getBalance());
        Assertions.assertEquals(5000, account.getCreditLimit());
        Assertions.assertEquals(0, account.getRate());
    }

    @Test
    public void shouldHandleWithZeroCreditLimit() {
        CreditAccount account = new CreditAccount(0, 0, 10);
        Assertions.assertEquals(0, account.getBalance());
        Assertions.assertEquals(0, account.getCreditLimit());
        Assertions.assertEquals(10, account.getRate());
    }

    @Test
    public void shouldThrowForNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new CreditAccount(0, 5000, -15));
    }


    @Test
    public void shouldThrowForNegativeCreditLimit() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new CreditAccount(0, -5000, 15));
    }

    @Test
    public void shouldThrowForNegativeBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new CreditAccount(-100, 5000, 15));
    }

    // Тесты для pay
    @Test
    public void shouldNotPayNegativeAmount() {
        CreditAccount account = new CreditAccount(0, 5000, 15);

        boolean result = account.pay(-100);

        Assertions.assertFalse(result);
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldPayAtLimitWithZeroBalance() {
        CreditAccount account = new CreditAccount(0, 5000, 15);

        boolean result = account.pay(5000);

        Assertions.assertTrue(result);
        Assertions.assertEquals(-5000, account.getBalance());
    }

    @Test
    public void shouldPayWithinLimitWithPositiveBalance() {
        CreditAccount account = new CreditAccount(1000, 5000, 15);

        boolean result = account.pay(1000);

        Assertions.assertTrue(result);
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldPayWithinLimitWithNegativeBalance() {
        CreditAccount account = new CreditAccount(1000, 5000, 15);

        boolean result = account.pay(2000);
        boolean result2 = account.pay(1000);
        Assertions.assertTrue(result);
        Assertions.assertTrue(result2);
        Assertions.assertEquals(-2000, account.getBalance());
    }

    @Test
    public void shouldNotPayExceedingLimitWithZeroBalance() {
        CreditAccount account = new CreditAccount(0, 5000, 15);

        boolean result = account.pay(6000);

        Assertions.assertFalse(result);
        Assertions.assertEquals(0, account.getBalance());
    }


    @Test
    public void shouldNotPayExceedingLimitWithNegativeBalance() {
        CreditAccount account = new CreditAccount(1000, 5000, 15);

        boolean result = account.pay(2000);
        boolean result2 = account.pay(5000);
        Assertions.assertTrue(result);
        Assertions.assertFalse(result2);
        Assertions.assertEquals(-1000, account.getBalance());
    }

    @Test
    public void shouldNotPayExceedingLimitWithPositiveBalance() {
        CreditAccount account = new CreditAccount(1000, 5000, 15);

        boolean result = account.pay(7000);

        Assertions.assertFalse(result);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void shouldHandleMultiplePayments() {
        CreditAccount account = new CreditAccount(1000, 5000, 15);

        boolean firstPayment = account.pay(800);
        boolean secondPayment = account.pay(1000);

        Assertions.assertTrue(firstPayment);
        Assertions.assertTrue(secondPayment);
        Assertions.assertEquals(-800, account.getBalance());
    }


    // Тесты для add
    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(100, 500, 15);

        boolean result = account.add(200);

        Assertions.assertTrue(result);
        Assertions.assertEquals(300, account.getBalance());
    }

    @Test
    public void shouldAddToZeroBalance() {
        CreditAccount account = new CreditAccount(0, 5000, 15);

        boolean result = account.add(3000);

        Assertions.assertTrue(result);
        Assertions.assertEquals(3000, account.getBalance());
    }

    @Test
    public void shouldAddToNegativeBalance() {
        CreditAccount account = new CreditAccount(1000, 5000, 15);

        boolean result = account.pay(5000);
        boolean result2 = account.add(3000);

        Assertions.assertTrue(result);
        Assertions.assertTrue(result2);
        Assertions.assertEquals(-1000, account.getBalance());
    }

    @Test
    public void shouldNotAddZero() {
        CreditAccount account = new CreditAccount(0, 5000, 15);

        boolean result = account.add(0);

        Assertions.assertFalse(result);
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldAddThreeAmounts() {
        CreditAccount account = new CreditAccount(1000, 5000, 15);

        boolean firstAdd = account.add(500);
        boolean secondAdd = account.add(700);
        boolean thirdAdd = account.add(300);

        Assertions.assertTrue(firstAdd);
        Assertions.assertTrue(secondAdd);
        Assertions.assertTrue(thirdAdd);

        Assertions.assertEquals(2500, account.getBalance());
    }

    // Тесты для yearChange

    @Test
    public void shouldReturnZeroForPositiveBalance() {
        CreditAccount account = new CreditAccount(200, 5000, 15);

        int result = account.yearChange();

        Assertions.assertEquals(0, result);
    }

    @Test
    public void shouldReturnNegativeYearChange() {
        CreditAccount account = new CreditAccount(1000, 5000, 20);

        boolean result = account.pay(5000);
        int yearChange = account.yearChange();

        Assertions.assertEquals(-800, yearChange);
    }

    @Test
    public void shouldReturnZeroForZeroBalance() {
        CreditAccount account = new CreditAccount(0, 5000, 15);

        int yearChange = account.yearChange();

        Assertions.assertEquals(0, yearChange);
    }
}