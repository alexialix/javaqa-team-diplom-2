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
    public void shouldPayWhenBalanceIsPositive() {
        CreditAccount account = new CreditAccount(1000, 5000, 15);

        boolean result = account.pay(3000);

        Assertions.assertTrue(result);
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
    public void shouldPayAtLimitWithPositiveBalance() {
        CreditAccount account = new CreditAccount(1000, 5000, 15);

        boolean result = account.pay(6000);

        Assertions.assertTrue(result);
        Assertions.assertEquals(-5000, account.getBalance());
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

    @Test
    public void shouldPayWhenBalanceIsNegative() {
        CreditAccount account = new CreditAccount(-2000, 5000, 15);

        boolean result = account.pay(1000);

        Assertions.assertTrue(result);
        Assertions.assertEquals(-3000, account.getBalance());
    }

    @Test
    public void shouldNotPayWhenExceedingLimitWithNegativeBalance() {
        CreditAccount account = new CreditAccount(-4000, 5000, 15);

        boolean result = account.pay(2000);

        Assertions.assertFalse(result);
        Assertions.assertEquals(-4000, account.getBalance());
    }

    // Тесты для add
    @Test
    public void shouldAddToZeroBalance() {
        CreditAccount account = new CreditAccount(0, 5000, 15);

        boolean result = account.add(3000);

        Assertions.assertTrue(result);
        Assertions.assertEquals(3000, account.getBalance());
    }

    @Test
    public void shouldAddToNegativeBalance() {
        CreditAccount account = new CreditAccount(-2000, 5000, 15);

        boolean result = account.add(3000);

        Assertions.assertTrue(result);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void shouldNotAddZero() {
        CreditAccount account = new CreditAccount(0, 5000, 15);

        boolean result = account.add(0);

        Assertions.assertFalse(result);
        Assertions.assertEquals(0, account.getBalance());
    }

    // Тесты для yearChange
    @Test
    public void shouldReturnNegativeYearChange() {
        CreditAccount account = new CreditAccount(-5000, 5000, 20);

        int yearChange = account.yearChange();

        Assertions.assertEquals(-1000, yearChange);
    }

    @Test
    public void shouldReturnZeroYearChange() {
        CreditAccount account = new CreditAccount(0, 5000, 15);

        int yearChange = account.yearChange();

        Assertions.assertEquals(0, yearChange);
    }

    @Test
    public void shouldHandleZeroRateInYearChange() {
        CreditAccount account = new CreditAccount(-2000, 5000, 0);

        int yearChange = account.yearChange();

        Assertions.assertEquals(0, yearChange);
    }
}
