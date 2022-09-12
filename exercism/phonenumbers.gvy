class PhoneNumber 
{
   static String clean(String input) 
   {
      // The purpose of this exercise is to practice Groovy regexps by using them to cleanup phone numbers
      return "skvrn";
   }
}

PhoneNumber phonecleaner = new PhoneNumber();
println "Cleaning up [2234567890] (trivial case: 10 straight digits) should give [2234567890], gives [${phonecleaner.clean("2234567890")}]";
println "Cleaning up [(223) 456-7890] (10 digits with standard format) should give [2234567890], gives [${phonecleaner.clean("(223) 456-7890")}]";
println "Cleaning up [223.456.7890] (10 digits with dots) should give [2234567890], gives [${phonecleaner.clean("223.456.7890")}]";
println "Cleaning up [223 456   7890   ] (10 digits with many spaces) should give [2234567890], gives [${phonecleaner.clean("223 456   7890   ")}]";
println "Cleaning up [12234567890] (11 digits with leading 1) should give [2234567890], gives [${phonecleaner.clean("12234567890")}]";
println "Cleaning up [+1 (223) 456-7890] (11 digits with punctuation and leading 1) should give [2234567890], gives [${phonecleaner.clean("+1 (223) 456-7890")}]";
println "Attempting to clean up [3456789] (8- digits) should give exception, gives [${phonecleaner.clean("3456789")}]";
println "Attempting to clean up [123456789] (9 digits) should give exception, gives [${phonecleaner.clean("123456789")}]";
println "Attempting to clean up [22234567890] (11 digits without leading 1) should give exception, gives [${phonecleaner.clean("22234567890")}]";
println "Attempting to clean up [321234567890] (over 11 digits) should give exception, gives [${phonecleaner.clean("321234567890")}]";
println "Attempting to clean up [1-800-Banamex] (number has letters) should give exception, gives [${phonecleaner.clean("1-800-Banamex")}]";
println "Attempting to clean up [123-@:!-7890] (punctuation other than ()+-.) should give exception, gives [${phonecleaner.clean("123-@:!-7890")}]";
println "Attempting to clean up [(023) 456-7890] (area code starts with 0) should give exception, gives [${phonecleaner.clean("(023) 456-7890")}]";
println "Attempting to clean up [(123) 456-7890] (area code starts with 1) should give exception, gives [${phonecleaner.clean("(123) 456-7890")}]";
println "Attempting to clean up [(223) 056-7890] (local number starts with 0) should give exception, gives [${phonecleaner.clean("(223) 056-7890")}]";
println "Attempting to clean up [(223) 156-7890] (local number starts with 1) should give exception, gives [${phonecleaner.clean("(223) 156-7890")}]";
println "Attempting to clean up [1 (023) 456-7890] (11 digits, valid, area code starts with 0) should give exception, gives [${phonecleaner.clean("1 (023) 456-7890")}]";
println "Attempting to clean up [+1 (223) 056-7890] (11 digits, valid, local number starts with 0) should give exception, gives [${phonecleaner.clean("+1 (223) 056-7890")}]";
println "Attempting to clean up [1.223.156.7890] (11 digits, valid, local number starts with 1) should give exception, gives [${phonecleaner.clean("1.223.156.7890")}]";
