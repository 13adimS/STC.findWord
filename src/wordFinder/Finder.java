package wordFinder;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class Finder extends Thread {

    @Override
    public void run() {
        synchronized (this) {
            saveFile(getOccurencies());
        }
    }

    private String[] sources;
    private String[] words;

    private static final Pattern END_OF_SENTENCE = Pattern.compile("[\\.!\\?][\\s\"]");

    private final Map<String, List> dict = new HashMap<>();

    public Finder(String[] sources, String[] words) {
        this.sources = sources;
        this.words = words;
    }

    private Map<String, List> getOccurencies() {
        for (String source : sources) {
            for (String word : words) {
                if (dict.get(word) == null) {
                    dict.put(word, getSentence(source, word));
                }
                else {
                    dict.get(word).addAll(getSentence(source, word));
                }
            }
        }
        return dict;
    }

    private List<String> getSentence(String source, String word) {
        List<String> sentences = new ArrayList<>();
        final String lowCaseWord = word.toLowerCase();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source))) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                for (String sentence : END_OF_SENTENCE.split(line)) {
                    if (sentence.toLowerCase().contains(lowCaseWord)) {
                        sentences.add(sentence);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sentences;
    }

    private void saveFile (Map<String, List> content) {
        try {
            File file = new File("res.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            for (Map.Entry<String, List> entry : content.entrySet()) {
                fileOutputStream.write((entry.getKey() + ": " + entry.getValue().toString() + "\r\n").getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
