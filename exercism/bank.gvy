class BankAccount 
{
   class ActualBankAccount
   {
      public int balance = 0;
   }
   def account = null;
      
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
      //
   }

   // this should decrement the balance
   // you cannot withdraw into a closed account
   // you cannot withdraw a negative amount 
   synchronized void withdraw(int amount) 
   {
   }

   // returns the current balance
   int getBalance() 
   {
      return 0
   }
}

def bank = new BankAccount();
bank.open();
