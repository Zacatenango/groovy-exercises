class PhoneNumber 
{
   static String clean(String input) 
   {
      // The purpose of this exercise is to practice Groovy regexps by using them to cleanup phone numbers
      boolean phone_is_syntactically_valid = false;
      String decoded_phone = "";
      
      // Part 1: syntax validation
      // Test 1: trivial case: 10 consecutive digits
      if (input ==~ /^[0-9]{10}$/) 
      {
         phone_is_syntactically_valid = true;
         decoded_phone = input;
      }

      // Test 2: 10 digits with standard format
      // Groovy feature: slash strings -- the slash is also a string delimiter. Backslashes in slash strings don't need to be escaped. It's meant for regexps.
      else if (input ==~ /\([0-9]{3}\) [0-9]{3}-[0-9]{4}/)
      {
         // To run a regexp against my text, I use the =~ operator with the regexp on right side
         // To match groups, I match against (?<groupname>regexp). This will return me a java.util.regex.Matcher object.
         // To retrieve my matches, first I run the regexp with matcher.matches(), then I use matcher.group("groupname")
         def decoded_phone_items = input =~ /\((?<areacode>[0-9]{3})\) (?<localcode1>[0-9]{3})-(?<localcode2>[0-9]{4})/;
         decoded_phone_items.matches();
         decoded_phone = decoded_phone_items.group("areacode") + decoded_phone_items.group("localcode1") + decoded_phone_items.group("localcode2");
         phone_is_syntactically_valid = true;
      }

      // Test 3: 10 digits separated by dots
      else if (input ==~ /[0-9]{3}\.[0-9]{3}\.[0-9]{4}/)
      {
         def decoded_phone_items = input =~ /(?<areacode>[0-9]{3})\.(?<localcode1>[0-9]{3})\.(?<localcode2>[0-9]{4})/;
         decoded_phone_items.matches();
         decoded_phone = decoded_phone_items.group("areacode") + decoded_phone_items.group("localcode1") + decoded_phone_items.group("localcode2");
         phone_is_syntactically_valid = true;
      }

      // Test 4 - 10 digits separated by spaces
      else if (input ==~ /^ *[0-9]{3} *[0-9]{3} *[0-9]{4} *$/)
      {
         def decoded_phone_items = input =~ /^ *(?<areacode>[0-9]{3}) *(?<localcode1>[0-9]{3}) *(?<localcode2>[0-9]{4}) *$/;
         decoded_phone_items.matches();
         decoded_phone = decoded_phone_items.group("areacode") + decoded_phone_items.group("localcode1") + decoded_phone_items.group("localcode2");
         phone_is_syntactically_valid = true;
      }

      // Test 5: 11 consecutive digits with leading 1
      else if (input ==~ /^1[0-9]{10}$/)
      {
         def decoded_phone_items = input =~ /1(?<nationalphone>[0-9]{10})/;
         decoded_phone_items.matches();
         decoded_phone = decoded_phone_items.group("nationalphone");
         phone_is_syntactically_valid = true;
      }

      // Test 6: 11 digits with standard punctuation and leading +1
      else if (input ==~ /^\+1 \([0-9]{3}\) [0-9]{3}-[0-9]{4}$/)
      {
         def decoded_phone_items = input =~ /^\+1 \((?<areacode>[0-9]{3})\) (?<localcode1>[0-9]{3})-(?<localcode2>[0-9]{4})$/;
         decoded_phone_items.matches();
         decoded_phone = decoded_phone_items.group("areacode") + decoded_phone_items.group("localcode1") + decoded_phone_items.group("localcode2");
         phone_is_syntactically_valid = true;
      }

      // Part 2: content validation
      if (phone_is_syntactically_valid)
      {
         // Test 1: area code does not start with 0 or 1?
         // Test 2: local code does not start with 0 or 1?
         boolean phone_content_is_valid = false;
         if (decoded_phone[0] != '0' && decoded_phone[0] != '1' &&
             decoded_phone[3] != '0' && decoded_phone[3] != '1')
         {
            phone_content_is_valid = true;
         }

         // Only return the phone after all these validations have succeeded.
         // Otherwise, throw an exception
         if (phone_content_is_valid)
         {
            return decoded_phone;
         }
         else
         {
            //throw new Exception("Semantically invalid phone");
            return "Exception";
         }
      }
      else
      {
         //throw new Exception("Syntactically invalid phone");
         return "Exception";
      }
   }
}

PhoneNumber phonecleaner = new PhoneNumber();

println "";
println "Cleaning up [2234567890] (trivial case: 10 straight digits) should give [2234567890], gives [${phonecleaner.clean("2234567890")}]";
println "Cleaning up [(223) 456-7890] (10 digits with standard format) should give [2234567890], gives [${phonecleaner.clean("(223) 456-7890")}]";
println "Cleaning up [223.456.7890] (10 digits with dots) should give [2234567890], gives [${phonecleaner.clean("223.456.7890")}]";
println "Cleaning up [223 456   7890   ] (10 digits with many spaces) should give [2234567890], gives [${phonecleaner.clean("223 456   7890   ")}]";
println "Cleaning up [12234567890] (11 digits with leading 1) should give [2234567890], gives [${phonecleaner.clean("12234567890")}]";
println "Cleaning up [+1 (223) 456-7890] (11 digits with standard punctuation and leading 1) should give [2234567890], gives [${phonecleaner.clean("+1 (223) 456-7890")}]";

println "";
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
