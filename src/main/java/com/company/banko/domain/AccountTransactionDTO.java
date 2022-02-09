package com.company.banko.domain;

import java.util.Date;

public class AccountTransactionDTO {

   private String accountNumber;

   private String description;

   private Date transactionDate;

   private long deposit;

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Date getTransactionDate() {
      return transactionDate;
   }

   public void setTransactionDate(Date transactionDate) {
      this.transactionDate = transactionDate;
   }

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

}
