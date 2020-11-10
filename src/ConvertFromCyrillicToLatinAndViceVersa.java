import java.io.*;
import java.util.Scanner;

public class ConvertFromCyrillicToLatinAndViceVersa {

    static String[] cyrillicAlphabet = {
            "Ў", "ў", "Ғ", "ғ", "Ё", "Ц", "Ч", "Ш",
            "Я", "Я", "я", "Ю", "ю", "Ш", "ш", "Ч",
            "ч", "Ц", "ц", "Ё", "ё", "А", "а", "Б",
            "б", "В", "в", "Г", "г", "Д", "д", "Е",
            "е", "Ж", "ж", "З", "з", "И", "и", "Й",
            "й", "К", "к", "Л", "л", "М", "м", "Н",
            "н", "О", "о", "П", "п", "Р", "р", "С",
            "с", "Т", "т", "У", "у", "Ф", "ф", "Х",
            "х", "Э", "э", "Қ", "қ", "Ҳ", "ҳ", ".",
            ",", " ", "?", "!"
    };

    static String[] latinAlphabet = {
            "O‘", "o‘", "G‘", "g‘", "Yo", "Ts", "Ch",
            "Sh", "Ya", "YA", "ya", "Yu", "yu", "Sh",
            "sh", "CH", "ch", "TS", "ts", "YO", "yo",
            "А", "a", "B", "b", "V", "v", "G", "g",
            "D", "d", "E", "e", "J", "j", "Z", "z",
            "I", "i", "Y", "y", "K", "k", "L", "l",
            "M", "m", "N", "n", "O", "o", "P", "p",
            "R", "r", "S", "s", "T", "t", "U", "u",
            "F", "f", "H", "h", "E", "e", "Q", "q",
            "X", "x", ".", ",", " ", "?", "!"
    };

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        File file = new File("src/CyrillicText.txt");
        File file1 = new File("src/LatinText.txt");

        System.out.println("1. Cyrillic to Latin\n2. Latin to Cyrillic");
        int n = scanner.nextInt();
        if (n == 1) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(file1))) {
                String c;
                while ((c = reader.readLine()) != null) {
                    String s = convertFromCyrillicToLatin(c);
                    writer.write(s + "\n");
                }
            }

        } else if (n == 2) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file1));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                String c;
                while ((c = reader.readLine()) != null) {
                    String s = convertFromLatinToCyrillic(c);
                    writer.write(s + "\n");
                }
            }
        }

    }

    static String convertFromLatinToCyrillic(String message) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            for (int j = 0; j < latinAlphabet.length; j++) {
                if (i == message.length() - 1) {
                    if (latinAlphabet[j]
                            .equals(String.valueOf(message.charAt(i)))) {
                        str.append(cyrillicAlphabet[j]);
                    }

                } else {
                    if (latinAlphabet[j].equals(String.valueOf(message.charAt(i))
                            .concat(String.valueOf(message.charAt(i + 1))))) {
                        str.append(cyrillicAlphabet[j]);
                        i++;
                        break;
                    }
                    if (latinAlphabet[j].equals(String.valueOf(message.charAt(i)))) {
                        str.append(cyrillicAlphabet[j]);
                        break;
                    }
                }
            }

        }
        return str.toString();

    }

    static String convertFromCyrillicToLatin(String message) {
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < message.length(); j++) {
            for (int k = 0; k < cyrillicAlphabet.length; k++) {
                if (String.valueOf(message.charAt(j))
                        .equals(cyrillicAlphabet[k])) {
                    s.append(latinAlphabet[k]);
                    break;
                }
            }
        }
        return s.toString();
    }
}