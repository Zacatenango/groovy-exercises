/*
 * Copyright 2003-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gr8conf;

// Exercise 1 from https://melix.github.io/ast-workshop/
// Exercise statement:
// 1.- Create a global AST transform that adds a public static final String $AUTHOR to all classes
// 2.- Initialize the field with your name
// 3.- Update the unit test so that it passes
// Question: Can you use the AST transformation in the same source tree as the one where it's defined?

/* One tool to generate the ASTs we want is the Groovy console (groovyConsole)
   To do this, we first type in the console's code box something similar to what we want, such as:

   public class Foo
   {
      public static final String $FOO = "foo";
   }
   
   We open Script menu -> Inspect Ast, and select "At end of Phase: Semantic Analysis",
   then in the left hand tree we open ClassNode - Foo -> Fields -> FieldNode - $FOO,
   and then we browse the stuff on the right hand table.  */

/* Now, how do we code a minimal AST?

   1.- We import the following packages:
       import org.codehaus.groovy.ast.ASTNode;
       import org.codehaus.groovy.ast.ClassHelper;
       import org.codehaus.groovy.ast.ClassNode;
       import org.codehaus.groovy.ast.expr.ConstantExpression;
       import org.codehaus.groovy.control.CompilePhase;
       import org.codehaus.groovy.control.SourceUnit;
       import org.codehaus.groovy.transform.AbstractASTTransformation;
       import org.codehaus.groovy.transform.GroovyASTTransformation;

   2.- We declare a class that extends AbstractASTTransformation 

   3.- We annotate the class with @GroovyASTTransformation(phase=CompilePhase.CONVERSION)
       This is the compilation phase where the abstract syntax tree is created from the 
       token trees that result from parsing the code. Groovy's gimmick is that the programmer
       can tinker with this phase of compilation. As the name says, AST transformation
       run at the conversion phase, after the AST has been created.

       Quick theory refresher: in programming language theory, the AST is a tree representation
       of the abstract syntactic structure of the source code. It is abstract in the sense that
       it only represents meaningful details, e.g. it views "blocks of code" instead of "start
       brace" and "end brace", or it views an if-else construct as a parent "if" node with three
       children: the condition, the if's body, and the else's body, without storing any literal
       "if" or a literal expression in the tree.

       In this sense, an AST transformation refers to an operation performed on the AST where
       we manually tamper with the program's code before the AST undergoes the semantic analysis
       step (the step where variable names are tested for validity and classes are resolved).
       An example of a simple AST transformation would be adding an author field to all the
       classes of the program: instead of manually hunting and pecking all of them, we run the
       initial AST through an AST transformation that adds that field automatically. An AST,
       therefore, can be seen as a way of dynamically rewriting a program's source code, or
       as a way to implement the class/function annotation feature of many programming
       languages.

       NOTE: We might later end up seeing more compilation phases; for now we stick
       to the conversion phase of compilation
       Classes can be seen in the official org.codehaus.groovy.control.CompilePhase enumeration
       https://github.com/groovy/groovy-core/blob/master/src/main/org/codehaus/groovy/control/CompilePhase.java
   
   4.- Then we code a function that overrides the function public void visit() of class
       org.codehaus.groovy.transform.AbstractASTTransformation
       This is the function that actually performs the AST

   5.- To get all the classes of the program, we use source.getAST().getClasses(). This will
       return us a List of org.codehaus.groovy.ast.ClassNode(s).
   
   6.- To add a field to our classes, we use 
       classnode.addField(String fieldname, int <access modifiers bitmask>, ClassHelper.<datatype constant>, new ConstantExpression("initial value")) */

/* But we can't use our AST just like that -- because ASTs are written in Java, not Groovy, 
   and because ASTs have to be precompiled, we can't use the AST on the same source tree where
   it's defined! Therefore, we have to use it on the test source tree, which is written in 
   Groovy and is in src/test/groovy
   
   To make the Groovy compiler pick up our AST, we must add a file on src/main/resources/META-INF/services 
   called org.codehaus.groovy.transform.ASTTransformation that contains:
   org.gr8conf.AuthorAdderASTTransformation
   Doing so will tell Groovy to pick up an AST transformation defined in class org.gr8conf.AuthorAdderASTTransformation
   (which is this class) and apply it as a compilation hook */

import org.codehaus.groovy.ast.*;
import org.codehaus.groovy.ast.expr.*;
import org.codehaus.groovy.control.*;
import org.codehaus.groovy.transform.*;
import java.util.List;

@GroovyASTTransformation(phase=CompilePhase.CONVERSION)
public class AuthorAdderASTTransformation extends AbstractASTTransformation
{
   @Override 
   public void visit(final ASTNode[] nodes, final SourceUnit sourcecode)
   {
      List<ClassNode> program_classes = sourcecode.getAST().getClasses();
      for (ClassNode oneclass: program_classes)
      {
         oneclass.addField("BAR", ACC_PUBLIC | ACC_STATIC | ACC_FINAL, ClassHelper.STRING_TYPE, new ConstantExpression("bar"));
      }
   }
}
