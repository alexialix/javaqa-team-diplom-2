package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BankTest {

    Bank bank = new Bank();

    @Test
    public void falseIfTransferAmount0FromCreditToCredit() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        CreditAccount userC101 = new CreditAccount(450_000, 10_000, 10);

        assertFalse(bank.transfer(userC100, userC101, 0));
        assertEquals(10_000, userC100.getBalance());
        assertEquals(450_000, userC101.getBalance());
    }

    @Test
    public void falseIfTransferAmount0FromCreditToSaving() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);

        assertFalse(bank.transfer(userC100, userS102, 0));
        assertEquals(10_000, userC100.getBalance());
        assertEquals(50_000, userS102.getBalance());
    }

    @Test
    public void falseIfTransferAmount0FromSavingToCredit() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);

        assertFalse(bank.transfer(userS102, userC100, 0));
        assertEquals(10_000, userC100.getBalance());
        assertEquals(50_000, userS102.getBalance());
    }

    @Test
    public void falseIfTransferFromAmount0CreditToCreditSaving() {
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);
        SavingAccount userS103 = new SavingAccount(10_000, 0, 100_000, 9);

        assertFalse(bank.transfer(userS102, userS103, 0));
        assertEquals(50_000, userS102.getBalance());
        assertEquals(10_000, userS103.getBalance());
    }

    @Test
    public void falseIfTransferAmountLess0FromCreditToCredit() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        CreditAccount userC101 = new CreditAccount(450_000, 10_000, 10);

        assertFalse(bank.transfer(userC100, userC101, -100));
        assertEquals(10_000, userC100.getBalance());
        assertEquals(450_000, userC101.getBalance());
    }

    @Test
    public void falseIfTransferAmountLess0FromCreditToSaving() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);

        assertFalse(bank.transfer(userC100, userS102, -100));
        assertEquals(10_000, userC100.getBalance());
        assertEquals(50_000, userS102.getBalance());
    }

    @Test
    public void falseIfTransferAmountLess0FromSavingToCredit() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);

        assertFalse(bank.transfer(userS102, userC100, -100));
        assertEquals(10_000, userC100.getBalance());
        assertEquals(50_000, userS102.getBalance());
    }

    @Test
    public void falseIfTransferFromAmountLess0CreditToCreditSaving() {
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);
        SavingAccount userS103 = new SavingAccount(10_000, 0, 100_000, 9);

        assertFalse(bank.transfer(userS102, userS103, -100));
        assertEquals(50_000, userS102.getBalance());
        assertEquals(10_000, userS103.getBalance());
    }

    @Test
    public void transferFromCreditToSavingMoreMax() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        SavingAccount userS103 = new SavingAccount(10_000, 0, 100_000, 9);

        assertFalse(bank.transfer(userC100, userS103, 95_000));
        assertEquals(10_000, userC100.getBalance());
        assertEquals(10_000, userS103.getBalance());
    }

    @Test
    public void transferFromSavingToSavingMoreMax() {
        SavingAccount userS102 = new SavingAccount(250_000, 0, 1_000_000, 12);
        SavingAccount userS103 = new SavingAccount(10_000, 0, 100_000, 9);

        assertFalse(bank.transfer(userS102, userS103, 100_000));
        assertEquals(250_000, userS102.getBalance());
        assertEquals(10_000, userS103.getBalance());
    }

    @Test
    public void transferFromCreditLessMinToCredit() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        CreditAccount userC101 = new CreditAccount(450_000, 10_000, 10);

        assertFalse(bank.transfer(userC100, userC101, 120_000));
        assertEquals(10_000, userC100.getBalance());
        assertEquals(450_000, userC101.getBalance());
    }

    @Test
    public void transferFromCreditLessMinToSaving() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);

        assertFalse(bank.transfer(userC100, userS102, 120_000));
        assertEquals(10_000, userC100.getBalance());
        assertEquals(50_000, userS102.getBalance());
    }

    @Test
    public void transferFromSavingLessMinToCredit() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);

        assertFalse(bank.transfer(userS102, userC100, 120_000));
        assertEquals(10_000, userC100.getBalance());
        assertEquals(50_000, userS102.getBalance());
    }

    @Test
    public void transferFromSavingLessMinToSaving() {
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);
        SavingAccount userS103 = new SavingAccount(10_000, 0, 100_000, 9);

        assertFalse(bank.transfer(userS102, userS103, 120_000));
        assertEquals(50_000, userS102.getBalance());
        assertEquals(10_000, userS103.getBalance());
    }

    @Test
    public void transfer4Account() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        CreditAccount userC101 = new CreditAccount(450_000, 10_000, 10);
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);
        SavingAccount userS103 = new SavingAccount(10_000, 0, 100_000, 9);

        Assertions.assertTrue(bank.transfer(userC100, userC101, 100_000));
        assertEquals(-90_000, userC100.getBalance());
        assertEquals(550_000, userC101.getBalance());
        assertEquals(50_000, userS102.getBalance());
        assertEquals(10_000, userS103.getBalance());
    }

    @Test
    public void falseIfTransfer4Account() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        CreditAccount userC101 = new CreditAccount(450_000, 10_000, 10);
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);
        SavingAccount userS103 = new SavingAccount(10_000, 0, 100_000, 9);

        assertFalse(bank.transfer(userS103, userC101, 20_000));
        assertEquals(10_000, userC100.getBalance());
        assertEquals(450_000, userC101.getBalance());
        assertEquals(50_000, userS102.getBalance());
        assertEquals(10_000, userS103.getBalance());
    }

    //валидный перевод
    @Test
    public void TransferFromCreditToSaving() {
        CreditAccount creditAccount = new CreditAccount(100, 500, 10);
        SavingAccount savingAccount = new SavingAccount(50, 0, 200, 5);

        Assertions.assertTrue(bank.transfer(creditAccount, savingAccount, 50));

        assertEquals(50, creditAccount.getBalance());
        assertEquals(100, savingAccount.getBalance());
    }

    @Test
    public void TransferFromCreditToCredit() {
        CreditAccount creditAccount1 = new CreditAccount(200, 500, 15);
        CreditAccount creditAccount2 = new CreditAccount(50, 300, 15);

        Assertions.assertTrue(bank.transfer(creditAccount1, creditAccount2, 100));

        assertEquals(100, creditAccount1.getBalance());
        assertEquals(150, creditAccount2.getBalance());
    }

    @Test
    public void TransferFromSavingToSaving() {
        SavingAccount savingAccount1 = new SavingAccount(150, 0, 500, 10);
        SavingAccount savingAccount2 = new SavingAccount(50, 0, 300, 5);

        Assertions.assertTrue(bank.transfer(savingAccount1, savingAccount2, 50));

        assertEquals(100, savingAccount1.getBalance());
        assertEquals(100, savingAccount2.getBalance());
    }

    @Test
    public void TransferFromSavingToCredit() {
        SavingAccount savingAccount = new SavingAccount(100, 0, 200, 5);
        CreditAccount creditAccount = new CreditAccount(50, 300, 15);

        Assertions.assertTrue(bank.transfer(savingAccount, creditAccount, 50));

        assertEquals(50, savingAccount.getBalance());
        assertEquals(100, creditAccount.getBalance());
    }

    //перевод на невалидный аккаунт
//    @Test
//    void shouldNotTransferFromAccountWithInvalidParameters() {
//        SavingAccount savingAccount = new SavingAccount(500, 0, 1000, 5);
//        CreditAccount invalid = new CreditAccount(0, 1000, -5);
//        assertFalse(bank.transfer(invalid, savingAccount, 100));
//        assertEquals(500, savingAccount.getBalance());
//    }

//    @Test
//    void shouldNotTransferToAccountWithInvalidParameters() {
//        SavingAccount savingAccount = new SavingAccount(500, 0, 1000, 5);
//        CreditAccount invalid = new CreditAccount(0, 1000, -5);
//        assertFalse(bank.transfer(savingAccount, invalid, 100));
//        assertEquals(500, savingAccount.getBalance());
//    }

    //перевод с нулевого баланса
    @Test
    public void falseFromCreditWithZeroInitialBalance() {
        CreditAccount creditAccount = new CreditAccount(0, 10, 15);
        CreditAccount user = new CreditAccount(450_000, 10_000, 10);

        assertFalse(bank.transfer(creditAccount, user, 100));
        assertEquals(0, creditAccount.getBalance());
        assertEquals(450_000, user.getBalance());
    }

    @Test
    public void falseFromSavingWithZeroInitialBalance() {
        SavingAccount savingAccount = new SavingAccount(0, 0, 1000, 5);
        CreditAccount user = new CreditAccount(450_000, 10_000, 10);

        assertFalse(bank.transfer(savingAccount, user, 100));
        assertEquals(0, savingAccount.getBalance());
        assertEquals(450_000, user.getBalance());
    }

    //тройной перевод
    @Test
    void shouldTransferFromCreditToSavingThreeTimes() {
        CreditAccount creditAccount = new CreditAccount(500, 1000, 10);
        SavingAccount savingAccount = new SavingAccount(500, 0, 1000, 5);

        Assertions.assertTrue(bank.transfer(creditAccount, savingAccount, 100));
        Assertions.assertTrue(bank.transfer(creditAccount, savingAccount, 50));
        Assertions.assertTrue(bank.transfer(creditAccount, savingAccount, 75));

        assertEquals(275, creditAccount.getBalance());
        assertEquals(725, savingAccount.getBalance());
    }

    @Test
    void shouldTransferFromSavingToCreditThreeTimes() {
        SavingAccount savingAccount = new SavingAccount(500, 0, 1000, 5);
        CreditAccount creditAccount = new CreditAccount(500, 1000, 10);

        Assertions.assertTrue(bank.transfer(savingAccount, creditAccount, 100));
        Assertions.assertTrue(bank.transfer(savingAccount, creditAccount, 50));
        Assertions.assertTrue(bank.transfer(savingAccount, creditAccount, 75));

        assertEquals(275, savingAccount.getBalance());
        assertEquals(725, creditAccount.getBalance());
    }

    @Test
    void shouldTransferFromCreditToCreditThreeTimes() {
        CreditAccount creditAccount1 = new CreditAccount(500, 1000, 10);
        CreditAccount creditAccount2 = new CreditAccount(1000, 1000, 10);

        Assertions.assertTrue(bank.transfer(creditAccount1, creditAccount2, 100));
        Assertions.assertTrue(bank.transfer(creditAccount1, creditAccount2, 50));
        Assertions.assertTrue(bank.transfer(creditAccount1, creditAccount2, 75));

        assertEquals(275, creditAccount1.getBalance());
        assertEquals(1225, creditAccount2.getBalance());
    }

    @Test
    void shouldTransferFromSavingToSavingThreeTimes() {
        SavingAccount savingAccount1 = new SavingAccount(500, 0, 1000, 5);
        SavingAccount savingAccount2 = new SavingAccount(1000, 0, 2000, 5);

        Assertions.assertTrue(bank.transfer(savingAccount1, savingAccount2, 100));
        Assertions.assertTrue(bank.transfer(savingAccount1, savingAccount2, 50));
        Assertions.assertTrue(bank.transfer(savingAccount1, savingAccount2, 75));

        assertEquals(275, savingAccount1.getBalance());
        assertEquals(1225, savingAccount2.getBalance());
    }
}