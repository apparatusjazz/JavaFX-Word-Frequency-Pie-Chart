package sample;

import java.util.*;

public class HistogramLetters {

    HashMap<Character, Integer> letters_frequencies;
    HashMap<Character, Double> letters_probabilities, letters_angles;
    ArrayList<Character> nSorted;
    double char_count;


    public HistogramLetters(String file) {
        setLetters_frequencies(file);
        this.letters_probabilities = null;
        this.letters_angles = null;
        this.nSorted = null;
    }

    public HashMap getLetter_frequencies () {
        return letters_frequencies;
    }
    public HashMap getLetter_probabilites () {
        return letters_probabilities;
    }
    public HashMap getLetters_angles () {
        return letters_angles;
    }
    public ArrayList getNSorted () {
        return nSorted;
    }

    public void setLetters_frequencies(String file) {

        ReadTextFile.openFile(file);
        String temp = ReadTextFile.readFile();
        String all_letters = temp.replaceAll("[^a-zA-Z]", "").toLowerCase();

        HashMap<Character, Integer> letter_frequencies = new HashMap<Character, Integer>();

        for (int i = 0; i < all_letters.length(); i++) {
            char c = all_letters.charAt(i);
            var val = letter_frequencies.get(c);
            if (val != null) {                      //If letter already exist, increment value
                letter_frequencies.put(c, val + 1);
            }
            else {
                letter_frequencies.put(c, 1);
            }
            char_count = i;
        }
        this.letters_frequencies = letter_frequencies;
    }


    public void setLetters_probabilities(HashMap<Character, Integer> letters_frequencies) {
        HashMap<Character, Double> letters_probabilities = new HashMap<Character, Double>();
        letters_frequencies.forEach((k, v) -> letters_probabilities.put(k, v / char_count));
        this.letters_probabilities = letters_probabilities;
    }

    public void setLetters_angles(HashMap<Character, Double> letters_probabilities) {
        HashMap<Character, Double> letters_angles = new HashMap<Character, Double>();
        letters_probabilities.forEach((k, v) -> letters_angles.put(k, v * 360));
        this.letters_angles = letters_angles;
    }

    //Creates an arraylist that stores the characters in the order of most frequent to the least frequent

    public void setNSorted(HashMap<Character, Integer> m) {
        List<Map.Entry<Character, Integer>> list =
                new LinkedList<Map.Entry<Character, Integer>>(m.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1,
                               Map.Entry<Character, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        int i = 0;
        ArrayList<Character> temp = new ArrayList<Character>();
        for (Map.Entry<Character, Integer> aa : list) {
            temp.add(i, aa.getKey());
        }
        this.nSorted = temp;

    }
}
