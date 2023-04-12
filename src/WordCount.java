import java.util.HashMap;

public class WordCount {
    public static HashMap<String, Integer> countWords(String text) {
        HashMap<String, Integer> wordCounts = new HashMap<>();
        String[] words = text.split("\\s+");
        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }
        return wordCounts;
    }
}
