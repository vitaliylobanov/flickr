package testAndPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrayExample
{

      public static void main(String[] args) {
         List<String> input = new ArrayList<String>();
         input.add("123-45-6789");
         input.add("9876-5-4321");
         input.add("987-65-4321 (attack)");
         input.add("987-65-4321 ");
         input.add("192-83-7465");


         for (String ssn : input) {
            if (ssn.matches("^(\\d{3}-?\\d{2}-?\\d{4})$")) {
               System.out.println("Found good SSN: " + ssn);
            }
         }
      }
      
      public static void addToarray() {
         String input = "I have a cat, but I like my dog better.";

         Pattern p = Pattern.compile("(mouse|cat|dog|wolf|bear|human)");
         Matcher m = p.matcher(input);

         List<String> animals = new ArrayList<String>();
         while (m.find()) {
            System.out.println("Found a " + m.group() + ".");
            animals.add(m.group());
         }
      }
   }



