package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.management.relation.RelationServiceNotRegisteredException;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void shouldAdd3Amount() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        account.add(3_000);
        account.add(2_000);
        account.add(1_000);

        Assertions.assertEquals(8_000, account.getBalance());
    }


    @Test
    public void shouldBeTrueAddBalance() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        Assertions.assertTrue(account.add(3_000));
    }

    @Test
    public void shouldBeFalseAddGreaterThanMaxBalance() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        Assertions.assertFalse(account.add(9_000));
    }

    @Test
    public void balanceNotChangedIfAddGreaterThanMax() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        account.add(9_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldBeFalseAddLessThan0() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        Assertions.assertFalse(account.add(-1_000));
    }

    @Test
    public void balanceNotChangedIfAddLessThan0() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        account.add(-1_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldIfReteLessThan0() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(2_000, 1_000, 10_000, -5));
    }

    @Test
    public void shouldPayLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(7_000, 1_000, 10_000, 5);

        account.pay(3_000);

        Assertions.assertEquals(7_000 - 3_000, account.getBalance());
    }

    @Test
    public void shouldAddEndPay() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        account.add(3_000);
        account.pay(2_000);
        account.pay(1_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldBeTruePayBalance() {
        SavingAccount account = new SavingAccount(7_000, 1_000, 10_000, 5);

        Assertions.assertTrue(account.pay(3_000));
    }

    @Test
    public void shouldBeFalsePayGreaterThanMinBalance() {
        SavingAccount account = new SavingAccount(7_000, 1_000, 10_000, 5);

        Assertions.assertFalse(account.pay(9_000));
    }

    @Test
    public void balanceNotChangedIfPayGreaterThanMin() {
        SavingAccount account = new SavingAccount(7_000, 1_000, 10_000, 5);

        account.pay(9_000);

        Assertions.assertEquals(7_000, account.getBalance());
    }

    @Test
    public void balanceNotChangedIfMinAndInitialBalanceEqualsBeforePay() {
        SavingAccount account = new SavingAccount(1_000, 1_000, 10_000, 5);

        account.pay(900);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldFalseIfMinAndInitialBalanceEqualsBeforePay() {
        SavingAccount account = new SavingAccount(1_000, 1_000, 10_000, 5);

        Assertions.assertFalse(account.pay(900));
    }

    @Test
    public void shouldBeFalsePayLessThan0() {
        SavingAccount account = new SavingAccount(7_000, 1_000, 10_000, 5);

        Assertions.assertFalse(account.pay(-1_000));
    }

    @Test
    public void balanceNotChangedIfPayLessThan0() {
        SavingAccount account = new SavingAccount(7_000, 1_000, 10_000, 5);

        account.pay(-1_000);

        Assertions.assertEquals(7_000, account.getBalance());
    }

    @Test
    public void yearChangeTest() {
        SavingAccount account = new SavingAccount(200, 0, 10_000, 15);

        Assertions.assertEquals(30, account.yearChange());

    }

    @Test
    public void fInitialBalance0() {
        SavingAccount account = new SavingAccount(0, 0, 10_000, 15);

        Assertions.assertEquals(0, account.yearChange());

    }

    @Test
    public void ifInitialBalanceLessThan0() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(-200, 0, 10_000, 15));
    }

    @Test
    public void ifMinBalanceLessThan0() {
        SavingAccount account;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(200, -1_000, 10_000, 15));
    }

    @Test
    public void ifMaxBalanceLessThan0() {
        SavingAccount account;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(200, 1_000, -10_000, 15));
    }

    @Test
    public void ifInitialBalanceLessThanMinBalance() {
        SavingAccount account;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(200, 1_000, 10_000, 15));
    }

    @Test
    public void ifInitialBalanceGreaterThanMaxBalance() {
        SavingAccount account;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(12_000, 1_000, 10_000, 15));
    }

    @Test
    public void shouldBeFalseAddBalance0() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        Assertions.assertFalse(account.add(0));
    }

    @Test
    public void balanceNotChangedIfAdd0() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        account.add(0);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldBeFalseIfPay0() {
        SavingAccount account = new SavingAccount(7_000, 1_000, 10_000, 5);

        Assertions.assertFalse(account.pay(0));
    }

    @Test
    public void balanceNotChangedIfPay0() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        account.pay(0);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void yearChangeIfRate0() {
        SavingAccount account = new SavingAccount(200, 0, 10_000, 0);

        Assertions.assertEquals(0, account.yearChange());

    }
}