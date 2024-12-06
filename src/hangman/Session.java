package hangman;

import java.util.Scanner;

public class Session {
    private int attemptsLeft;
    private final Word word;
    public Session () {
        this.word = new Word();
        this.attemptsLeft = 10;
    }
    public void play() {
        Scanner input = new Scanner(System.in); //сканнер для ввода данных
        while (attemptsLeft > 0) {
            System.out.println("Слово: " + word.getCurrentWord());
            System.out.println("Осталось неверных попыток: " + attemptsLeft + ".");
            System.out.println("Отгадайте букву или сдайтесь, введя команду 'сдаюсь': ");
            String guess = input.nextLine();
            if (guess.equals("сдаюсь")) {
                System.out.println("Вы сдались! Загаданное слово было " + word.getGuessedWord() + ".");
                break;
            }
            if (guess.length() == 1) {
                char letter = guess.charAt(0);
                if (!word.guessLetter(letter)) {
                    attemptsLeft--;
                }
                if (word.isGuessed()) {
                    System.out.println("Вы угадали слово! Загаданное слово было " + word.getGuessedWord() + ".");
                    break;
                }
            }
            if (attemptsLeft != 0) {
                System.out.println("Уже введенные ранее буквы: " + word.getGuessedLetters() + ".");
            }
        }
        if (attemptsLeft == 0) {
            System.out.println("Извините, вы проиграли! Загаданное слово было " + word.getGuessedWord() + ".");
        }
    }
}
