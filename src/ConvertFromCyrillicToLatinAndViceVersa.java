import java.io.*;
import java.util.Scanner;

public class ConvertFromCyrillicToLatinAndViceVersa {

    static String[] cyrillicAlphabet = {
            "Ў", "ў", "Ғ", "ғ", "Ё", "Ц", "Ч", "Ш",
            "Я", "Ғ", "ғ", "Ў", "ў", "Я", "я", "Ю",
            "ю", "Ш", "ш", "Ч", "ч", "Ц", "ц", "Ё",
            "ё", "А", "а", "Б", "б", "В", "в", "Г",
            "г", "Д", "д", "Е", "е", "Ж", "ж", "З",
            "з", "И", "и", "Й", "й", "К", "к", "Л",
            "л", "М", "м", "Н", "н", "О", "о", "П",
            "п", "Р", "р", "С", "с", "Т", "т", "У",
            "у", "Ф", "ф", "Х", "х", "Э", "э", "Қ",
            "қ", "Ҳ", "ҳ", ".", ",", " ", "?", "!"
    };

    static String[] latinAlphabet = {
            "O‘", "o‘", "G‘", "g‘", "Yo", "Ts", "Ch",
            "Sh", "Ya", "G'", "g'", "O'", "o'", "YA",
            "ya", "Yu", "yu", "SH", "sh", "CH", "ch",
            "TS", "ts", "YO", "yo", "А", "a", "B", "b",
            "V", "v", "G", "g", "D", "d", "E", "e", "J",
            "j", "Z", "z", "I", "i", "Y", "y", "K", "k",
            "L", "l", "M", "m", "N", "n", "O", "o", "P",
            "p", "R", "r", "S", "s", "T", "t", "U", "u",
            "F", "f", "H", "h", "E", "e", "Q", "q", "X",
            "x", ".", ",", " ", "?", "!"
    };
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        File file = new File("src/main/java/extra_Task/Kiril.txt");
        File file1 = new File("src/main/java/extra_Task/Latin.txt");

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

    public static String convertFromCyrillicToLatin(String message) {
        scanner = new Scanner(System.in);
        int counter = 0;
        for (int i = 0; i < message.length() - 1; i++) {
            for (String s : latinAlphabet) {
                if (!(i == message.length() - 2 || i == message.length() - 1)) {
                    if (s.equals(String.valueOf(message.charAt(i)).concat(String.valueOf(message.charAt(i + 1))))) {
                        i++;
                        counter++;
                    }
                }
            }
        }

        int[] temp = new int[message.length() + counter];
        int tempCounter = 0;

        for (int i = 0; i < message.length(); i++) {

            int idKir = 0;
            for (String s : cyrillicAlphabet) {
                if (i == message.length() - 1) {
                    if (s.equals(String.valueOf(message.charAt(i)))) {
                        temp[tempCounter] = idKir;
                        tempCounter++;
                        break;
                    }
                    idKir++;
                } else {
                    if (s.equals(String.valueOf(message.charAt(i)).concat(String.valueOf(message.charAt(i + 1))))) {
                        temp[tempCounter] = idKir;
                        tempCounter++;
                        i++;
                        break;
                    } else if (s.equals(String.valueOf(message.charAt(i)))) {
                        temp[tempCounter] = idKir;
                        tempCounter++;
                        break;
                    }
                    idKir++;
                }
            }
        }
        StringBuilder retValue = new StringBuilder();
        for (int i = 0; i < temp.length + counter; i++) {
            for (int j = 0; j < cyrillicAlphabet.length; j++) {
                if (temp[i] == j) {
                    retValue.append(latinAlphabet[j]);
                }
            }
        }
        return retValue.toString();
    }

    public static String convertFromLatinToCyrillic(String message) {
        int counter = 0;
        for (int i = 0; i < message.length() - 1; i++) {
            for (String s : latinAlphabet) {
                if (!(i == message.length() - 2 || i == message.length() - 1)) {
                    if (s.equals(String.valueOf(message.charAt(i)).concat(String.valueOf(message.charAt(i + 1))))) {
                        i++;
                        counter++;
                    }
                }
            }
        }


        int[] temp = new int[message.length()];
        int tempCounter = 0;

        for (int i = 0; i < message.length(); i++) {

            int idLat = 0;
            for (String s : latinAlphabet) {
                if (i == message.length() - 1) {
                    if (s.equals(String.valueOf(message.charAt(i)))) {
                        temp[tempCounter] = idLat;
                        tempCounter++;
                        break;
                    }
                    idLat++;
                } else {
                    if (s.equals(String.valueOf(message.charAt(i)).concat(String.valueOf(message.charAt(i + 1))))) {
                        temp[tempCounter] = idLat;
                        tempCounter++;
                        i++;
                        break;
                    } else if (s.equals(String.valueOf(message.charAt(i)))) {
                        temp[tempCounter] = idLat;
                        tempCounter++;
                        break;
                    }
                    idLat++;
                }

            }
        }

        StringBuilder retValue = new StringBuilder();
        for (int i = 0; i < temp.length - counter; i++) {
            for (int j = 0; j < cyrillicAlphabet.length; j++) {
                if (temp[i] == j) {
                    retValue.append(cyrillicAlphabet[j]);
                }
            }
        }
        return retValue.toString();
    }
}