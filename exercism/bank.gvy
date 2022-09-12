class BankAccount 
{
   class ActualBankAccount
   {
      public int balance = 0;
   }
   ActualBankAccount account = null;
      
   // You cannot do any operations before you open the account.
   // An account opens with a balance of 0
   // You can reopen an account
   void open() 
   {
      account = new ActualBankAccount();
   }

   // you cannot do any operations after you close the account
   // To do that, we delete the account
   void close() 
   {
      account = null;
   }

   // this should increment the balance
   // you cannot deposit into a closed account
   // you cannot deposit a negative amount 
   synchronized void deposit(int amount) 
   {
      if (account != null && account.balance >= 0)
      {
         account.balance += amount;
      }
   }

   // this should decrement the balance
   // you cannot withdraw into a closed account
   // you cannot withdraw a negative amount 
   synchronized void withdraw(int amount) 
   {
      if (account != null && amount < account.balance)
      {
         account.balance -= amount;
      }
   }

   // returns the current balance
   int getBalance() 
   {
      return account.balance;
   }
}

BankAccount bankAccount = new BankAccount();
bankAccount.open()
bankAccount.deposit(23)
bankAccount.withdraw(10)
bankAccount.withdraw(13)
println "Final balance is ${bankAccount.getBalance()}";
