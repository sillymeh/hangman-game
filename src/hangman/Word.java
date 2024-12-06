package hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Word {
    private String word;
    private char[] guessedWord;
    private static List<Character> guessedLetters = new ArrayList<>();

    private static final String[] dictionary;

    static {
        try {
            dictionary = Files.readAllLines(Paths.get("src/hangman/words.txt")).toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getWord() { //рандомное слово из файла
        Random random = new Random();
        int randomIndex = random.nextInt(dictionary.length - 1);
        return dictionary[randomIndex];
    }

    public Word() {
        this.word = getWord();
        this.guessedWord = new char[word.length()];
        Arrays.fill(guessedWord, '-');
    }

    public boolean guessLetter(char letter) { //угадываем букву
        if (!guessedLetters.contains(letter)) {
            guessedLetters.add(letter);
        }
        boolean found = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                guessedWord[i] = letter;
                found = true;
            }
        }
        return found;

    }
    public String getCurrentWord() { //слово, отгаданное сейчас
        return new String(guessedWord);
    }
    public boolean isGuessed() { //проверка угадали ли слово
        return word.equals(getCurrentWord());
    }
    public String getGuessedWord(){
        return new String(word);
    }
    public static String getGuessedLetters() {
        StringBuilder guesses = new StringBuilder();
        for (int i = 0; i < guessedLetters.size(); i++) {
            guesses.append(guessedLetters.get(i));
            if (i < guessedLetters.size() - 1) {
                guesses.append(", ");
            }
        }
        return guesses.toString();
    }
}
