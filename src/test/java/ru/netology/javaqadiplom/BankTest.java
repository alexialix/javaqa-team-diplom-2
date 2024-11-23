package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BankTest {

    @Test
    public void falseIfTransferAmount0FromCreditToCredit() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        CreditAccount userC101 = new CreditAccount(450_000, 10_000, 10);

        Bank transfer = new Bank();

        Assertions.assertFalse(transfer.transfer(userC100, userC101, 0));
        Assertions.assertEquals(10_000, userC100.getBalance());
        Assertions.assertEquals(450_000, userC101.getBalance());
    }

    @Test
    public void falseIfTransferAmount0FromCreditToSaving() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);

        Bank transfer = new Bank();

        Assertions.assertFalse(transfer.transfer(userC100, userS102, 0));
        Assertions.assertEquals(10_000, userC100.getBalance());
        Assertions.assertEquals(50_000, userS102.getBalance());
    }

    @Test
    public void falseIfTransferAmount0FromSavingToCredit() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);

        Bank transfer = new Bank();

        Assertions.assertFalse(transfer.transfer(userS102, userC100, 0));
        Assertions.assertEquals(10_000, userC100.getBalance());
        Assertions.assertEquals(50_000, userS102.getBalance());
    }

    @Test
    public void falseIfTransferFromAmount0CreditToCreditSaving() {
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);
        SavingAccount userS103 = new SavingAccount(10_000, 0, 100_000, 9);

        Bank transfer = new Bank();

        Assertions.assertFalse(transfer.transfer(userS102, userS103, 0));
        Assertions.assertEquals(50_000, userS102.getBalance());
        Assertions.assertEquals(10_000, userS103.getBalance());
    }
    @Test
    public void falseIfTransferAmountLess0FromCreditToCredit() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        CreditAccount userC101 = new CreditAccount(450_000, 10_000, 10);

        Bank transfer = new Bank();

        Assertions.assertFalse(transfer.transfer(userC100, userC101, -100));
        Assertions.assertEquals(10_000, userC100.getBalance());
        Assertions.assertEquals(450_000, userC101.getBalance());
    }

    @Test
    public void falseIfTransferAmountLess0FromCreditToSaving() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);

        Bank transfer = new Bank();

        Assertions.assertFalse(transfer.transfer(userC100, userS102, -100));
        Assertions.assertEquals(10_000, userC100.getBalance());
        Assertions.assertEquals(50_000, userS102.getBalance());
    }

    @Test
    public void falseIfTransferAmountLess0FromSavingToCredit() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);

        Bank transfer = new Bank();

        Assertions.assertFalse(transfer.transfer(userS102, userC100, -100));
        Assertions.assertEquals(10_000, userC100.getBalance());
        Assertions.assertEquals(50_000, userS102.getBalance());
    }

    @Test
    public void falseIfTransferFromAmountLess0CreditToCreditSaving() {
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);
        SavingAccount userS103 = new SavingAccount(10_000, 0, 100_000, 9);

        Bank transfer = new Bank();

        Assertions.assertFalse(transfer.transfer(userS102, userS103, -100));
        Assertions.assertEquals(50_000, userS102.getBalance());
        Assertions.assertEquals(10_000, userS103.getBalance());
    }

    @Test
    public void transferFromCreditToSavingMoreMax() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        SavingAccount userS103 = new SavingAccount(10_000, 0, 100_000, 9);

        Bank transfer = new Bank();

        Assertions.assertFalse(transfer.transfer(userC100, userS103, 95_000));
        Assertions.assertEquals(10_000, userC100.getBalance());
        Assertions.assertEquals(10_000, userS103.getBalance());
    }

    @Test
    public void transferFromSavingToSavingMoreMax() {
        SavingAccount userS102 = new SavingAccount(250_000, 0, 1_000_000, 12);
        SavingAccount userS103 = new SavingAccount(10_000, 0, 100_000, 9);

        Bank transfer = new Bank();

        Assertions.assertFalse(transfer.transfer(userS102, userS103, 100_000));
        Assertions.assertEquals(250_000, userS102.getBalance());
        Assertions.assertEquals(10_000, userS103.getBalance());
    }

    @Test
    public void transferFromCreditLessMinToCredit() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        CreditAccount userC101 = new CreditAccount(450_000, 10_000, 10);

        Bank transfer = new Bank();

        Assertions.assertFalse(transfer.transfer(userC100, userC101, 120_000));
        Assertions.assertEquals(10_000, userC100.getBalance());
        Assertions.assertEquals(450_000, userC101.getBalance());
    }

    @Test
    public void transferFromCreditLessMinToSaving() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);

        Bank transfer = new Bank();

        Assertions.assertFalse(transfer.transfer(userC100, userS102, 120_000));
        Assertions.assertEquals(10_000, userC100.getBalance());
        Assertions.assertEquals(50_000, userS102.getBalance());
    }

    @Test
    public void transferFromSavingLessMinToCredit() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);

        Bank transfer = new Bank();

        Assertions.assertFalse(transfer.transfer(userS102, userC100, 120_000));
        Assertions.assertEquals(10_000, userC100.getBalance());
        Assertions.assertEquals(50_000, userS102.getBalance());
    }

    @Test
    public void transferFromSavingLessMinToSaving() {
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);
        SavingAccount userS103 = new SavingAccount(10_000, 0, 100_000, 9);

        Bank transfer = new Bank();

        Assertions.assertFalse(transfer.transfer(userS102, userS103, 120_000));
        Assertions.assertEquals(50_000, userS102.getBalance());
        Assertions.assertEquals(10_000, userS103.getBalance());
    }

    @Test
    public void transfer4Account() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        CreditAccount userC101 = new CreditAccount(450_000, 10_000, 10);
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);
        SavingAccount userS103 = new SavingAccount(10_000, 0, 100_000, 9);


        Bank transfer = new Bank();

        Assertions.assertTrue(transfer.transfer(userC100, userC101, 100_000));
        Assertions.assertEquals(-90_000, userC100.getBalance());
        Assertions.assertEquals(550_000, userC101.getBalance());
        Assertions.assertEquals(50_000, userS102.getBalance());
        Assertions.assertEquals(10_000, userS103.getBalance());
    }

    @Test
    public void falseIfTransfer4Account() {
        CreditAccount userC100 = new CreditAccount(10_000, 100_000, 15);
        CreditAccount userC101 = new CreditAccount(450_000, 10_000, 10);
        SavingAccount userS102 = new SavingAccount(50_000, 0, 1_000_000, 12);
        SavingAccount userS103 = new SavingAccount(10_000, 0, 100_000, 9);


        Bank transfer = new Bank();

        Assertions.assertFalse(transfer.transfer(userS103, userC101, 20_000));
        Assertions.assertEquals(-90_000, userC100.getBalance());
        Assertions.assertEquals(550_000, userC101.getBalance());
        Assertions.assertEquals(50_000, userS102.getBalance());
        Assertions.assertEquals(10_000, userS103.getBalance());
    }

}