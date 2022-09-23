package org.gr8conf

class Foo
{
   public static final int FOO = 5;
   Foo(int foo)
   {
      println "Constructor: foo = $foo";
   }
}

class AuthorAdderASTTransformationTest extends GroovyTestCase 
{
   // Test 1: this class should have a built-in attribute called BAR whose value is "bar"
   void testThatAuthorExists() 
   {
      assert BAR == 'bar';
   }

   // Test 2: instantiating any class should show a built-in attribute called BAR
   void testASTTransformationShouldBeDebuggableFromIDE()
   {
      Foo foo = new Foo(Foo.FOO);
      println "Attribute foo.BAR = ${foo.BAR}";
      assert foo.BAR == "bar";
   }
}
