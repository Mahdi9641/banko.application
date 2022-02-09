package com.company.banko.domain;

import java.util.Date;

public class AccountTransactionDTO {

   private String accountNumber;

   private long deposit;

   private long amount;


   public String getAccountNumber() {
      return accountNumber;
   }

   public void setAccountNumber(String accountNumber) {
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
