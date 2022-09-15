package org.gr8conf

class Foo
{
}

class AuthorAdderASTTransformationTest extends GroovyTestCase 
{
   // Test 1: this class should have a built-in attribute called BAR whose value is "bar"
   void testThatAuthorExists() 
   {
      assert BAR == 'bar';
   }

   // Test 2: instantiating a blank class should show a built-in attribute called BAR
   void testASTTransformationShouldBeDebuggableFromIDE()
   {
      Foo foo = new Foo();
      println "Attribute foo.BAR = ${foo.BAR}";
      assert foo.BAR == "bar";
   }
}
