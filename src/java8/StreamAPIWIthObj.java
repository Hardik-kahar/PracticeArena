package java8;

import java.util.*;
import java.util.stream.Collectors;

public class StreamAPIWIthObj {

    public static void main(String[] args) {



//      1.  How to convert a List of objects into a Map by considering duplicated keys and store them in sorted order?
//      2   How to check if list is empty in Java 8 using Optional, if not null iterate through the list and print the object?
        List<Notes> noteLst = new ArrayList<>();
        noteLst.add(new Notes(1L, "note1", 11));
        noteLst.add(new Notes(2L, "note2", 22));
        noteLst.add(new Notes(3L, "note3", 33));
        noteLst.add(new Notes(4L, "note4", 44));
        noteLst.add(new Notes(5L, "note5", 55));
        noteLst.add(new Notes(6L, "note4", 66));


//      1. How to convert a List of objects into a Map by considering duplicated keys and store them in sorted order?
        Map<String, Long> notesRecords = noteLst.stream()
                .sorted(Comparator
                        .comparingLong(Notes::getId)
                        .reversed()) // Sorting in descending order by ID
                .collect(Collectors.toMap(
                        Notes::getName,               // Key: name
                        Notes::getId,                 // Value: ID
                        (oldValue, newValue) -> oldValue, // Merge function: keep the first (higher ID)
                        LinkedHashMap::new           // Maintain insertion order
                ));

        System.out.println("Notes : " + notesRecords);

//      2   How to check if list is empty in Java 8 using Optional, if not null iterate through the list and print the object?
        Optional.ofNullable(noteLst)
                .orElseGet(Collections::emptyList) // creates empty immutable list: [] in case noteLst is null
                .stream().filter(Objects::nonNull) //loop throgh each object and consider non null objects
                .map(Notes::getName) // method reference, consider only tag name
                .forEach(System.out::println);


    }
//
//    ✅ What is Collectors.mapping()?
//Collectors.mapping(Function<? super T, ? extends U> mapper, Collector<? super U, A, R>)
//    It lets you transform elements in a stream before collecting them with another collector.
//
//            📌 Think of it as:
//            ➡️ map() + collect(...) together inside a collector pipeline
//
//✅ Why use mapping()?
//    You use it inside another collector like groupingBy, partitioningBy, or toMap.
//
//    It helps you extract or transform values before collecting them.
//
//    Keeps things cleaner and more functional than pre-processing with .map().
//
//            🚀 Top 5 Use Cases of Collectors.mapping()
//1. ✅ Extracting names from a list of users grouped by department
//    java
//            Copy
//    Edit
//    Map<String, List<String>> departmentToUsernames = employees.stream()
//            .collect(Collectors.groupingBy(
//                    Employee::getDepartment,
//                    Collectors.mapping(Employee::getName, Collectors.toList())
//            ));
//📌 mapping(Employee::getName, toList()) → extracts only names into a list per department.
//
//            2. ✅ Extracting only email domains from a list
//            java
//    Copy
//            Edit
//    List<String> domains = users.stream()
//            .collect(Collectors.mapping(
//                    user -> user.getEmail().split("@")[1],
//                    Collectors.toList()
//            ));
//3. ✅ Using inside partitioningBy to extract keys (as we did earlier):
//    java
//            Copy
//    Edit
//    Map<Boolean, List<Integer>> result = map.entrySet().stream()
//            .collect(Collectors.partitioningBy(
//                    e -> e.getValue() > 1,
//                    Collectors.mapping(Map.Entry::getKey, Collectors.toList())
//            ));
//🎯 Without mapping, you'd get a List<Map.Entry<...>> which is bulky.
//
//            4. ✅ Combining with joining()
//    java
//            Copy
//    Edit
//    Map<String, String> departmentToNames = employees.stream()
//            .collect(Collectors.groupingBy(
//                    Employee::getDepartment,
//                    Collectors.mapping(Employee::getName, Collectors.joining(", "))
//            ));
//✨ You get comma-separated names in a string per department.
//
//5. ✅ Combining with toSet() or summarizingInt()
//    java
//            Copy
//    Edit
//    Map<String, Set<String>> skillsByDepartment = employees.stream()
//            .collect(Collectors.groupingBy(
//                    Employee::getDepartment,
//                    Collectors.mapping(Employee::getSkill, Collectors.toSet())
//            ));
//🧠 Summary Table:
//
//    Use With	What mapping() Does
//    groupingBy()	Extract specific fields or transform before collecting
//    partitioningBy()	Extract keys or values from Map.Entry
//    joining()	Create comma-separated values
//    toSet() / toList()	Create collections with only transformed values
}

//Collector in Java Streams — this is a super important distinction and comes in handy in interviews too!
//
//        ✅ Collectors vs Collector — What's the Difference?
//
//Term	What is it?	Where it's used
//Collectors	A utility class with static methods	You call its methods to create collectors
//Collector	An interface that represents a collector	The object returned by Collectors.xyz()
//✅ 1. Where Do We Use Collectors?
//        📌 You use Collectors when you want to collect the elements of a Stream into some structure like:
//
//
//Usage	Code Example
//Convert to List	Collectors.toList()
//Convert to Set	Collectors.toSet()
//Join strings	Collectors.joining(", ")
//Count items	Collectors.counting()
//Group by a classifier	Collectors.groupingBy(...)
//Partition by a condition	Collectors.partitioningBy(...)
//Transform values while collecting	Collectors.mapping(..., ...)
//Flatten nested structure	Collectors.flatMapping(..., ...)
//🧠 These return a Collector object which you pass to .collect(...)
//
//✅ 2. Where Do We Use Collector?
//        📌 Collector<T, A, R> is an interface. You use it:
//
//When you write your own custom collector
//
//When you want to pass an existing collector from Collectors
//
//When you are writing a method that accepts a generic collector
//
//🧪 Examples:
//        ✅ Using Collectors to create a collector:
//java
//        Copy
//Edit
//List<String> names = people.stream()
//        .map(Person::getName)
//        .collect(Collectors.toList()); // Using Collectors.toList()
//✅ Storing a Collector in a variable:
//java
//        Copy
//Edit
//Collector<Person, ?, List<String>> nameCollector =
//        Collectors.mapping(Person::getName, Collectors.toList());
//
//List<String> names = people.stream().collect(nameCollector);
//✅ Writing a method that accepts a Collector:
//java
//        Copy
//Edit
//public static <T, R> R collectSomething(Stream<T> stream, Collector<T, ?, R> collector) {
//    return stream.collect(collector);
//}
//✅ Custom Collector Example:
//java
//        Copy
//Edit
//Collector<String, ?, String> reverseConcat = Collector.of(
//        StringBuilder::new,
//        (sb, str) -> sb.insert(0, str),
//        StringBuilder::append,
//        StringBuilder::toString
//);
//🧠 TL;DR Summary:
//
//You Want To...	Use
//Get predefined collector	Collectors.xyz()
//Store/Pass a collector	Collector<...>
//Create your own collector	Implement Collector or use Collector.of()
//Use inside .collect(...)	Collector instance (usually from Collectors)