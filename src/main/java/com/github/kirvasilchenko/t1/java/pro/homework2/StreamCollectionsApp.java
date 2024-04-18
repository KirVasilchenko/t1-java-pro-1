package com.github.kirvasilchenko.t1.java.pro.homework2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class StreamCollectionsApp {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 4, 5, 5);
        System.out.println(removeDuplicates(numbers)); // [1, 2, 3, 4, 5]
        System.out.println(getThirdMaxNumber(numbers)); // 4
        System.out.println(getThirdMaxUniqueNumber(numbers)); // 3

        List<Employee> employees = List.of(
                new Employee("Alice", 35, "Manager"),
                new Employee("Bob", 20, "Designer"),
                new Employee("Carl", 25, "Engineer"),
                new Employee("Diane", 30, "Developer"),
                new Employee("Eugene", 34, "Engineer"),
                new Employee("Frank", 19, "Designer"),
                new Employee("Grace", 26, "Engineer"),
                new Employee("Henry", 31, "Developer"),
                new Employee("Ivan", 33, "Engineer"),
                new Employee("Jack", 20, "Designer"),
                new Employee("Kate", 27, "Engineer"),
                new Employee("Linda", 32, "Engineer")
        );
        System.out.println(getTopThreeEldestEngineersOrderByAgeDesc(employees)); // 34, 33, 32
        System.out.println(getGetAverageEngineersAge(employees)); // 29.5

        List<String> wordList = List.of("one", "two", "three", "four", "five", "six", "seven", "thirteen");
        System.out.println(findLongestWord(wordList)); // thirteen

        String words = "one two two three three three four four four four";
        System.out.println(collectDictionaryWords(words)); // one=1, two=2, three=3, four=4

        printSortedByWordsLengthAscAndAlphabet(wordList);

        String[] wordsArray = new String[]{
                "one two three four",
                "five six seven eight nine ten eleven",
                "twelve thirteen fourteen",
                "fifteen sixteen seventeen",
                "eighteen nineteen twenty"
        };

        System.out.println(getLongestWordFromArray(wordsArray)); // seventeen
    }

    private static String findLongestWord(List<String> words) {
        return words.stream().filter(Objects::nonNull).max(Comparator.comparingInt(String::length)).orElseThrow(() -> new RuntimeException("List is empty"));
    }

    public static <T> List<T> removeDuplicates(List<T> list) {
        return list.stream().distinct().toList();
    }

    public static Integer getThirdMaxNumber(List<Integer> list) {
        return list.stream().sorted().toList().get(list.size() - 3);
    }

    public static Integer getThirdMaxUniqueNumber(List<Integer> list) {
        return list.stream().distinct().sorted((e1, e2) -> Integer.compare(e2, e1)).skip(2L).findFirst().get();
    }

    public static List<Employee> getTopThreeEldestEngineersOrderByAgeDesc(List<Employee> list) {
        return list.stream().filter(e -> e.position().equals("Engineer")).sorted((e1, e2) -> Integer.compare(e2.age(), e1.age())).limit(3).collect(Collectors.toList());
    }

    public static Double getGetAverageEngineersAge(List<Employee> list) {
        return list.stream().filter(e -> e.position().equals("Engineer")).mapToInt(Employee::age).average().getAsDouble();
    }

    private static Map<String, Integer> collectDictionaryWords(String words) {
        return Arrays.stream(words.split(" ")).collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));
    }

    private static void printSortedByWordsLengthAscAndAlphabet(List<String> wordList) {
        wordList.stream().sorted(Comparator.comparingInt(String::length).thenComparing(String::compareTo)).forEach(System.out::println);
    }

    private static String getLongestWordFromArray(String[] wordsArray) {
        return findLongestWord(Arrays.stream(wordsArray).flatMap(s -> Arrays.stream(s.split(" "))).toList());
    }

    public record Employee(String name, int age, String position) {
    }
}
