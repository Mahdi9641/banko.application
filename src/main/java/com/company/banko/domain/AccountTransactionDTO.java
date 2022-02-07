package com.company.banko.domain;

import java.util.Date;

public class AccountTransactionDTO {

   private long accountNumber;

   private long deposit;

   private long amount;


   public long getAccountNumber() {
      return accountNumber;
   }

   public void setAccountNumber(long accountNumber) {
      this.accountNumber = accountNumber;
   }

   public long getDeposit() {
      return deposit;
   }


   public void setDeposit(long deposit) {
      this.deposit = deposit;
   }

   public long getAmount() {
      return amount;
   }

   public void setAmount(long amount) {
      this.amount = amount;
   }

}
