class RotationalCipher 
{
   private int key;

   RotationalCipher(int key) 
   {
      this.key = key;
   }

   String rotate(String cipherText) 
   {
      // Trivial cases: rotating by 0 or 26 should do nothing
      if (key == 0 || key == 26) return cipherText;

      // Relying on a LUT is gonna be a bit of a pickle, because punctuation must be respected
      // So instead I'm going to rely on the ASCII value of the character compared to "A" or "a"
      // depending on whether it's upper or lowercase
      // So, we start out converting our plaintext to a char array
      def ciphertext_chars = cipherText.getChars();

      // Then we shift each character
      for (int X=0; X<ciphertext_chars.size(); X++)
      {
         // Exercise statement says numbers and punctuation should not be shifted.
         // Therefore, we only shift the ones that are letters
         if (ciphertext_chars[X].isLetter())
         {
            // We calculate our offset from A or a depending on our character's case
            int offset = ciphertext_chars[X].isUpperCase()? 
               ciphertext_chars[X] - (char)'A': 
               ciphertext_chars[X] - (char)'a';
            
            // Then we shift our character modulo 26
            int cipher_offset = (offset + key) % 26;

            // Then we convert our cipher offset back into a character
            // taking A or a as base depending on our source character
            char cipher_char = ciphertext_chars[X].isUpperCase()? (char)'A' + cipher_offset: (char)'a' + cipher_offset;

            // Then we save our cipher character into the array
            ciphertext_chars[X] = cipher_char;
         }
      }

      // Then we output our ciphertext
      return new String(ciphertext_chars);
   }
}

println "Ciphertext is ${new RotationalCipher(3).rotate("skvrn")}";
