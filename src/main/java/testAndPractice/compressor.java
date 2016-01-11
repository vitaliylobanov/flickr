package testAndPractice;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Given a string, write a compression algorithm as following: 1. If a
 * character, ch, occurs n(>1) times in a row, then it will be represented by
 * {ch}{n}, where {x} is the value of x. For example, if the substring is a
 * sequence of 4 'a' ("aaaa"), it will be represented as "a4". 2. If a
 * character, ch, occurs exactly one time in a row, then it will be simply
 * represented as {ch}. For example, if the substring is "a", then it will be
 * represented as "a".
 * 
 * Example: abcaaabbb -> abca3b3 abcd -> abcd aabbccddaa -> a2b2c2d2a2
 * 
 * Take 1: You will be graded for correctness. Take 2: Don't forget to write a
 * jUnit test.
 */
 
public class compressor {
   public String compress(String str){
       int count = 1;
       StringBuilder builder = new StringBuilder();

       for(int i = 1; i<str.length(); i++){

           if(str.charAt(i) == str.charAt(i-1) && i < str.length()-1){
               count++;
           }
           // case when the last letter is in the sequence preceding it. Add that sequence to
           // the compressed string
           else if(i == str.length()-1 && str.charAt(i) == str.charAt(i-1)){
               count++;
               builder.append(str.charAt(i));
               builder.append(count);
           }

           // case where the last letter is NOT in the sequence preceding it. Add it to string.
           else if(i == str.length()-1 && str.charAt(i) != str.charAt(i-1)){
               builder.append(str.charAt(i-1));
               builder.append(count);
               count = 1;
               builder.append(str.charAt(i));
               builder.append(count);
           }
           else{
               // appending the character and THEN appending the count works.
               builder.append(str.charAt(i-1));
               builder.append(count);
               count = 1;
           }

       }

       str = builder.toString();
       System.out.println(str);

       return str;
   }

   @Test
   public void test_a5() {
       assertEquals("a5", compress("aaaaa"));
   }
   
   @Test
   public void test_aabcccccaaa() {
       assertEquals("a2b1c5a3", compress("aabcccccaaa"));
   }
   
   public static void main(String[] args){
       compressor test = new compressor();

       test.compress("aabcccccaaa");
       test.compress("aaaaa");
       test.compress("aaaabbb");
       test.compress("aaabbbccc");
       test.compress("abc");
       test.compress("a");
       test.compress("");
   }
}

