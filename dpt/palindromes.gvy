#! /usr/bin/groovy

class Palindromes
{
   boolean ispalindrome(String string_to_evaluate)
   {
      // Remove all spaces from the string
      String comparisonstring = string_to_evaluate.replaceAll(" ", "");
      // Reverse the string into a new variable
      String comparisonstring_reversed = comparisonstring.reverse();
      // Compare -- if both are the same, it's palindrome
      return comparisonstring_reversed.equals(comparisonstring);
   }
}

String string_to_evaluate = "anita lava la tina";
Palindromes palindromizer = new Palindromes();
println "Is '$string_to_evaluate' palindrome? ${palindromizer.ispalindrome(string_to_evaluate)? "Yes, it is": "No, it's not"}"
