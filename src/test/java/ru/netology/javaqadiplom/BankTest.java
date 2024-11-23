package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    private final Bank bank = new Bank();  // Использование внешнего класса Bank

    //валидный перевод
    @Test
    public void TransferFromCreditToSaving() {
        CreditAccount creditAccount = new CreditAccount(100, 500, 10);
        SavingAccount savingAccount = new SavingAccount(50, 0, 200, 5);

        assertTrue(bank.transfer(creditAccount, savingAccount, 50));

        assertEquals(50, creditAccount.getBalance());
        assertEquals(100, savingAccount.getBalance());
    }

    @Test
    public void TransferFromCreditToCredit() {
        CreditAccount creditAccount1 = new CreditAccount(200, 500, 15);
        CreditAccount creditAccount2 = new CreditAccount(50, 300, 15);

        assertTrue(bank.transfer(creditAccount1, creditAccount2, 100));

        assertEquals(100, creditAccount1.getBalance());
        assertEquals(150, creditAccount2.getBalance());
    }

    @Test
    public void TransferFromSavingToSaving() {
        SavingAccount savingAccount1 = new SavingAccount(150, 0, 500, 10);
        SavingAccount savingAccount2 = new SavingAccount(50, 0, 300, 5);

        assertTrue(bank.transfer(savingAccount1, savingAccount2, 50));

        assertEquals(100, savingAccount1.getBalance());
        assertEquals(100, savingAccount2.getBalance());
    }

    @Test
    public void TransferFromSavingToCredit() {
        SavingAccount savingAccount = new SavingAccount(100, 0, 200, 5);
        CreditAccount creditAccount = new CreditAccount(50, 300, 15);

        assertTrue(bank.transfer(savingAccount, creditAccount, 50));

        assertEquals(50, savingAccount.getBalance());
        assertEquals(100, creditAccount.getBalance());
    }

    //невалидный перевод с null
    @Test
    void shouldNotTransferToAccountWithInvalidParameters() {
        CreditAccount creditAccount = new CreditAccount(500, 1000, 10);

        assertFalse(bank.transfer(creditAccount, null, 100));
        assertEquals(500, creditAccount.getBalance());
    }

    @Test
    void shouldNotTransferFromAccountWithInvalidParameters() {
        SavingAccount savingAccount = new SavingAccount(500, 0, 1000, 5);

        assertFalse(bank.transfer(null, savingAccount, 100));
        assertEquals(500, savingAccount.getBalance());
    }

    //тройной перевод
    @Test
    void shouldTransferFromCreditToSavingThreeTimes() {
        CreditAccount creditAccount = new CreditAccount(500, 1000, 10);
        SavingAccount savingAccount = new SavingAccount(500, 0, 1000, 5);

        assertTrue(bank.transfer(creditAccount, savingAccount, 100));
        assertTrue(bank.transfer(creditAccount, savingAccount, 50));
        assertTrue(bank.transfer(creditAccount, savingAccount, 75));

        assertEquals(275, creditAccount.getBalance());
        assertEquals(725, savingAccount.getBalance());
    }

    @Test
    void shouldTransferFromSavingToCreditThreeTimes() {
        SavingAccount savingAccount = new SavingAccount(500, 0, 1000, 5);
        CreditAccount creditAccount = new CreditAccount(500, 1000, 10);

        assertTrue(bank.transfer(savingAccount, creditAccount, 100));
        assertTrue(bank.transfer(savingAccount, creditAccount, 50));
        assertTrue(bank.transfer(savingAccount, creditAccount, 75));

        assertEquals(275, savingAccount.getBalance());
        assertEquals(725, creditAccount.getBalance());
    }

    @Test
    void shouldTransferFromCreditToCreditThreeTimes() {
        CreditAccount creditAccount1 = new CreditAccount(500, 1000, 10);
        CreditAccount creditAccount2 = new CreditAccount(1000, 1000, 10);

        assertTrue(bank.transfer(creditAccount1, creditAccount2, 100));
        assertTrue(bank.transfer(creditAccount1, creditAccount2, 50));
        assertTrue(bank.transfer(creditAccount1, creditAccount2, 75));

        assertEquals(275, creditAccount1.getBalance());
        assertEquals(1225, creditAccount2.getBalance());
    }

    @Test
    void shouldTransferFromSavingToSavingThreeTimes() {
        SavingAccount savingAccount1 = new SavingAccount(500, 0, 1000, 5);
        SavingAccount savingAccount2 = new SavingAccount(1000, 0, 1000, 5);

        assertTrue(bank.transfer(savingAccount1, savingAccount2, 100));
        assertTrue(bank.transfer(savingAccount1, savingAccount2, 50));
        assertTrue(bank.transfer(savingAccount1, savingAccount2, 75));

        assertEquals(275, savingAccount1.getBalance());
        assertEquals(1275, savingAccount2.getBalance());
    }
}
