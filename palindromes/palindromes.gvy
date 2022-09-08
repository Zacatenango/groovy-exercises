class Foo
{
   int FOO = 5;
   void FOO_FOO()
   {
      // From a Java-like call to a shorthand call, all this is valid Groovy:
      System.out.println("kk = " + FOO);
      println("kk = " + FOO);
      println "kk = " + FOO;
      // Semicolons are optional as well, BASH-style.
      // I'll use them anyway
      println "kk = " + FOO
   }
}

Foo foo = new Foo();
foo.FOO_FOO();
