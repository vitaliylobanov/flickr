package testAndPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class fetchBracketsAndCompare
{

   public static void main(String[] args)
   {
      
      String one = "( )";
      String two = "{ }";
      String three = "[ ]";
      String foo = "(1+2)*[6, 7, 9](){}";
      Pattern regex = Pattern.compile("[^A-Za-z0-9+*' ,]");
      Matcher matcher = regex.matcher(foo);
      List<String> specialChar = new ArrayList<String>();
      while (matcher.find())
      {
         System.out.println("Found a " + matcher.group());
         specialChar.add(matcher.group());
      }

      String joinedString = StringUtils.join(new Object[]{specialChar});
      String nEwjoinedString = joinedString.replaceAll(",", "");
      System.out.println(nEwjoinedString);
      
      if(nEwjoinedString.contains(two) || nEwjoinedString.contains(one) 
            || nEwjoinedString.contains(three)){
        System.out.println("cool");
      }
   }
}


