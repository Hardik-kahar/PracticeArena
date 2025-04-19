package java8;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamAPI {

    /*
        https://blog.devgenius.io/java-8-coding-and-programming-interview-questions-and-answers-62512c44f062

        https://medium.com/@mehar.chand.cloud/java-stream-coding-interview-questions-part-1-dc39e3575727

        https://medium.com/@mehar.chand.cloud/java-stream-coding-interview-questions-part-2-9f3aad0025f3

        https://medium.com/@mehar.chand.cloud/java-stream-hard-interview-questions-54ea0de40acc
     */
    public static void main(String[] args) {

        int[] arr = {10, 15, 8, 49, 25, 98, 98, 32, 15, 1, 1, 85, 6, 2, 3, 65, 6, 45, 45, 5662, 2582, 2, 2, 266, 666, 656};
        //1.    Given a list of integers, find out all the even numbers that exist in the list using Stream functions?
        //2.    Given a list of integers, find out all the numbers starting with 1 using Stream functions?
        //3.    How to find duplicate elements in a given integers list in java using Stream functions?
        //4.    Given the list of integers, find the first element of the list using Stream functions?
        //5.    Given a list of integers, find the total number of elements present in the list using Stream functions?
        //6.    Given a list of integers, find the maximum value element present in it using Stream functions?
        //7.    Given a String, find the first non-repeated number in it using Stream functions?


//      ================================ String questions ================================
        String str = "JavaarticlesareAwesome";
        //1.    Given a String, find the first non-repeated character in it using Stream functions?


//      find not-repeating character

//      ================================================================================================================
//      ============================================== Solution ========================================================

//        1.Given a list of integers, find out all the even numbers that exist in the list using Stream functions?
        Arrays.stream(arr)
                .filter(num -> num%2 ==0)
                .boxed()
                .forEach(num -> System.out.print(num +", "));
        
        Map<Boolean, List<Integer>> evenNum1 = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.partitioningBy(num -> num%2 ==0));

        evenNum1.forEach((key, value) -> {
            String label = key ? "Even numbers" : "Odd numbers";
            System.out.println(label + ": " + value);
        });
//        ============================================================================================================

//        2. Given a list of integers, find out all the numbers starting with 1 using Stream functions?
        List<String> findNumberStartFrom1 = Arrays.stream(arr)
                .boxed()
                .map(num -> num+"" )
                .filter(s -> s.startsWith("1"))
                .toList();
        System.out.println("findNumberStartFrom1: "+ findNumberStartFrom1);
//        ============================================================================================================

        //3.    How to find duplicate elements in a given integers list in java using Stream functions?
//      find only duplicate
        HashSet<Integer> set = new HashSet<>();
        int duplicate = Arrays.stream(arr)
//                .boxed()
                .filter(num -> !set.add(num))
//                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("sum of duplicate: "+ duplicate);

        List<Integer> list = Arrays.stream(arr)
                .boxed()
                .distinct()
                .toList();
        System.out.println("distinct : "+ list);

//        =========== counting element ===============
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : arr){
            map.put(num, map.getOrDefault(num,0)+1);
        }
        map.entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .forEach( i ->{
                    System.out.println("key: "+ i.getKey()+ " count: "+ i.getValue());
                });

//        map.forEach((key, count)->{
//            System.out.print("key: "+ key+ " count: "+ count);
//            System.out.println();
//        });

//      =========== counting element with pure stream api ===============
        List<Integer> result = Arrays.stream(arr)
                .boxed()
                .collect(  Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println("result: "+ result);
//        ============================================================================================================
//        4. Given the list of integers, find the first element of the list using Stream functions?

        int i = Arrays.stream(arr)
//                .boxed()
                .findFirst()
                .orElse(0);
        System.out.println("fist numeric value: "+ i);
//      ============================================================================================================
//      5. Given a list of integers, find the total number of elements present in the list using Stream functions?
        long sumOfNums = Arrays.stream(arr).boxed().count();
//      ============================================================================================================
//      6. Given a list of integers, find the maximum value element present in it using Stream functions?
        int maxNumValue = Arrays.stream(arr)
                .max()
                .getAsInt();
        System.out.println("Maximum value: "+ maxNumValue);

        // get in sorted form with distinct element
        List<Integer> sortedNums = Arrays.stream(arr)
                .boxed()
//                .distinct()
                .sorted()
//                .sorted(Comparator.reverseOrder())
//                .skip(1)
//                .findFirst()
//                .stream()
//                .mapToInt(Integer::intValue)
//                .findFirst().orElse(0);
                .toList();

        System.out.println("sortedNums: "+ sortedNums);

//      ============================================================================================================
//      7. Given a String, find the first non-repeated character in it using Stream functions?
        int findFirstNonRepeative = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue()==1)
                .map(Map.Entry::getKey)
                .peek(System.out::println)
                .findFirst()
                .orElse(0);
        System.out.println("findFirstNonRepeative: "+ findFirstNonRepeative);

//      ================================================= String questions =================================================
//      ====================================================================================================================
//      ====================================================================================================================

//      find repeating character
        String uniqueCharacter = Arrays.stream(str.split(""))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,  Collectors.counting()))
                .entrySet()
                .stream()
//                .peek(e -> System.out.println("e: "+ e))
                .filter(e -> e.getValue()==1)
                .map(Map.Entry::getKey)
//                .peek(System.out::println)
//                .findFirst()
//                .orElse(null);
                .collect(Collectors.joining(", "));
        System.out.println("repeating: "+ uniqueCharacter);

        Set<Character> seenCharacters = new HashSet<>();

        Character ch = str.chars().mapToObj( c -> (char)c)
                .filter(c -> !seenCharacters.add(c))
                .findFirst()
                .orElse(null);
        System.out.println("not repetitive "+ ch);

//      1. Given a String, find the first non-repeated character in it using Stream functions?





    }
}
