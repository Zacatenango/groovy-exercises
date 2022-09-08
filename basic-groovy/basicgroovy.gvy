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

      // Groovy has dynamic typing
      def skvrn = "prvých";
      println "skvrn = \"" + skvrn + "\"";

      // Groovy has multiline strings
      def povrch = """
      povrch
      raer
      tufriqana
      """
      println "povrch = \"" + povrch + "\""

      // Primitive data types: Groovy counts lists and hash tables as primitives
      // Lists and maps, however, must be declared with def
      def lista = [ "skvrn", "prvych", "povrch" ];
      def mapa = [ "skvrn":10, "prvych":20, "povrch":30 ];
      // Groovy can print 
   }
}

Foo foo = new Foo();
foo.FOO_FOO();
