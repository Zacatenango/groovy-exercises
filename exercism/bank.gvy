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
   // Failure to meet any of such conditions should throw an exception
   synchronized void deposit(int amount) 
   {
      if (account != null && account.balance >= 0 && amount >= 0)
      {
         account.balance += amount;
      }
      else
      {
         throw new Exception("ERROR: Deposit failed")
      }
   }

   // this should decrement the balance
   // you cannot withdraw into a closed account
   // you cannot withdraw a negative amount 
   // Attempting to overdraw must throw an exception
   synchronized void withdraw(int amount) 
   {
      if (account != null && amount <= account.balance && amount >= 0)
      {
         account.balance -= amount;
      }
      else
      {
         throw new Exception("ERROR: Withdrawal failed");
      }
   }

   // returns the current balance
   // Attempting to get balance of closed account should throw exception
   int getBalance() 
   {
      if (account != null)
      {
         return account.balance;
      }
      else
      {
         throw new Exception("ERROR: Balance retreival failed");
      }
   }
}


// This function is used to spawn 1000 threads at a time that will attempt to deposit and withdraw concurrently
void adjustBalanceConcurrently(BankAccount bankAccount) 
{
   Random random = new Random();
   List<Thread> threads = new ArrayList<Thread>();
   (1..1000).each 
   {
      threads.add(new Thread(
      {
         try 
         {
            bankAccount.deposit(5)
            Thread.sleep(random.nextInt(10))
            bankAccount.withdraw(5)
         } 
         catch (InterruptedException ignored) {} 
         catch (Exception e) 
         {
            print("Exception should not be thrown: ${e.getMessage()}")
         }
      }))
   }
   threads.each { it.start() }
   threads.each { it.join() }
}

// With this function, we run our test
// We create our bank, open an account, deposit 1000, and have 1000 threads deposit and withdraw 5 concurrently 10 times
// If mutual exclusion has been implemented correctly, our bank account should have the same initial balance of 1000
BankAccount bankAccount = new BankAccount();
bankAccount.open();
bankAccount.deposit(1000);
for (int i = 0; i < 10; i++) 
{
   adjustBalanceConcurrently(bankAccount);
}
println "Final balance should be 1000, is actually ${bankAccount.getBalance()}";
