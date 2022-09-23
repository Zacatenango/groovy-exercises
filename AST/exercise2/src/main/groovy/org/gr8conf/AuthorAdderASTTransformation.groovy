package org.gr8conf

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

/* How to code AST transformations in Groovy 

   Because the devs soon realized that coding and compiling a separate AST transformation in Java
   was going to be quite tedious, time-consuming and would cost Big Tech a lot of money, they worked
   on making it possible to code an AST transformation in Groovy.

   How to code a minimal AST in Groovy:

   1.- Import the following packages:
       import org.codehaus.groovy.ast.*;
       import org.codehaus.groovy.ast.builder.*;
       import org.codehaus.groovy.ast.expr.*;
       import org.codehaus.groovy.control.*;
       import org.codehaus.groovy.transform.*;
       (No need to import java.util.List, because it is implicit in Groovy)
   
   2.- Declare a class that extends AbstractASTTransformation
   
   3.- Annotate class with @GroovyASTTransformation(phase=CompilePhase.CONVERSION)

   4.- Code a function that overrides public void visit(), with same parameters as in Java

   5.- Now that we are in Groovy, some things will change:
       - We don't need to fetch all the classes of the program first thing, this can be
         done by iterating with sourcecode.AST.classes.each { <clojure> }
       - Iterating with .each requires a clojure
       - And instead of directly adding our field, we will instead use org.codehaus.groovy.ast.builder.AstBuilder
         This class takes a clojure that specifies what will be transformed and how, and
         returns whatever the AST compiles into, in this case an object representing a class field
       - Then we add our field with oneclass.addField(field[0])
    
   6.- Make sure our ASTT's class is specified on src/main/resources/META-INF/services/org.codehaus.groovy.transform.ASTTransformation */

@GroovyASTTransformation(phase=CompilePhase.CONVERSION)
class AuthorAdderASTTransformation extends AbstractASTTransformation
{
   @Override public void visit(final ASTNode[] nodes, final SourceUnit sourcecode)
   {
      sourcecode.AST.classes.each \
      {
         oneclass ->
            def field = new AstBuilder().buildFromSpec \
            {
               fieldNode "BAR", ACC_PUBLIC | ACC_STATIC | ACC_FINAL, String, this.class, { constant "bar" };
            };
            oneclass.addField(field[0]);
      }
   }
}
