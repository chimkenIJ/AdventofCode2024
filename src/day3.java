import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class day3 {
    public static void main(String[] args) throws IOException {
        System.out.println(part1("inputs/day3.txt"));
        System.out.println(part2("inputs/day3.txt"));
    }

    public static int part1(String file) throws IOException {
        int counter = 0;
        String data = readFile(file);
        for (int i = 0; i < data.length() - 4; i++) {
            counter += checkIfTrue(data, i);
        }
        return counter;
    }

    public static int part2(String file) throws IOException {
        boolean on = true;
        int counter = 0;
        String data = readFile(file);
        for (int i = 0; i < data.length() - 4; i++) {
            if (data.startsWith("don't()",i)) {
                on = false;
            } else if (data.startsWith("do()",i)) {
                on = true;
            }
            if(on) {
                counter += checkIfTrue(data, i);
            }
        }
        return counter;
    }

    public static int checkIfTrue(String data, int i) {
        int counter = 0;
        if (data.substring(i, i + 4).equals("mul(")) {
            if (Character.isDigit(data.charAt(i + 4))) {
                StringBuilder first = new StringBuilder(data.substring(i + 4, i + 5));
                int j = i + 5;
                while (Character.isDigit(data.charAt(j))) {
                    first.append(data.charAt(j));
                    j++;
                }
                if (data.charAt(j) == (',') && Character.isDigit(data.charAt(j + 1))) {
                    StringBuilder second = new StringBuilder(data.substring(j + 1, j + 2));

                    int k = j + 2;
                    while (Character.isDigit(data.charAt(k))) {
                        second.append(data.charAt(k));
                        k++;
                    }
                    if (data.charAt(k) == (')')) {
                        counter += Integer.parseInt(first.toString()) * Integer.parseInt(second.toString());
                    }
                }
            }
        }
        return counter;
    }

    public static String readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
