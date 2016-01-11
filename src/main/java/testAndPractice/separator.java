package testAndPractice;


///code 
//PART 1:
//Write a method to split a string based on a separator string e.g. 
//
//split(“HelloFooWorldFoo", "Foo") should return [“Hello”, “World”]
//
//split(“Hello World", " ") should return [“Hello”, “World”]
//
//PART 2:
//Write test cases for the above methods. 
//
//PART 3:
//How would u use this if you had to run it on in interactive/real time manner on a large set of strings e.g. split a column in a table with million of rows.
//
public class separator
{
   public static void main (String [] args)
   {

   String data = "HelloFooWorldFoo";
   String separator = "Foo";

   String [] strArray = data.split(separator);
   for(int i=0; i<strArray.length;i++) 
       {
        System.out.println(strArray[i]);
       }
   }
}
