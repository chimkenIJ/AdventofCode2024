import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class day4 {
    public static void main(String[] args) throws IOException {
        System.out.println(part1("inputs/day4.txt"));
        System.out.println(part2("inputs/day4.txt"));
    }

    public static int part1(String file) throws IOException {
        int counter = 0;
        String data = readFile(file);
        String[][] box = makeBox(file);
        String[] vals = data.split("\n");

        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[i].length; j++) {
                if (checkDiagonal(box, "XMAS", "SAMX", -1, i, j)) {
                    counter++;
                }
                if (checkDiagonal(box, "XMAS", "SAMX", 1, i, j)) {
                    counter++;
                }
            }
        }

        int diagCounter = counter;
        System.out.println("Diagonal: " + diagCounter);

        //horizontal
        for (int i = 0; i < vals.length; i++) {
            counter += check(vals[i], "XMAS", "SAMX");
        }

        int horizCounter = counter - diagCounter;
        System.out.println("Horizontal: " + horizCounter);

        //vertical
        for (int j = 0; j < box[0].length; j++) {
            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < box.length; i++) {
                ans.append(box[i][j]);
            }
            counter += check(String.valueOf(ans), "XMAS", "SAMX");
        }

        int vertCounter = counter - horizCounter - diagCounter;
        System.out.println("Vertical: " + vertCounter);
        System.out.println("Total: " + (diagCounter + horizCounter + vertCounter));

        return counter;
    }

    public static int part2(String file) throws IOException {
        String[][] box = makeBox(file);
        int counter = 0;
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[i].length - 2; j++) {
                if (checkDiagonal(box, "MAS", "SAM", 1, i, j)) {
                    if (checkDiagonal(box, "MAS", "SAM", -1, i, j + 2)) {
                        counter++;
                    }
                }
            }
        }

        return counter;
    }

    public static boolean checkDiagonal(String[][] box, String check1, String check2, int dir, int i, int j) {
        StringBuilder diag = new StringBuilder();
        if (box[i][j].equals(check1.substring(0, 1)) || box[i][j].equals(check2.substring(0, 1))) {
            diag = new StringBuilder(box[i][j]);

            for (int k = 1; k < check1.length(); k++) {
                if (dir > 0) {
                    if ((j <= box[i].length - check1.length()) && (i <= box.length - check1.length())) {
                        diag.append(box[i + k][j + k * dir]);

                    }
                } else {
                    if ((j >= check1.length() - 1) && (i <= box.length - check1.length())) {
                        diag.append(box[i + k][j + k * dir]);
                    }
                }
            }
        }
        return check(diag.toString(), check1, check2) > 0;

    }


    public static String[][] makeBox(String file) throws IOException {
        String data = readFile(file);
        String[] vals = data.split("\n");
        String[][] box = new String[vals.length][vals[0].length()];

        for (int i = 0; i < vals.length; i++) {
            for (int j = 0; j < vals[0].length(); j++) {
                box[i][j] = vals[i].substring(j, j + 1);
            }
        }
        return box;
    }

    public static int check(String line, String check1, String check2) {
        int counter = 0;
        for (int i = 0; i <= line.length() - check1.length(); i++) {
            if ((line.substring(i, i + check1.length()).equals(check1)) || (line.substring(i, i + check2.length()).equals(check2)))
                counter++;
        }
        return counter;

    }

    public static String readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
