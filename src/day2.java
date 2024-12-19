import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class day2 {
    public static void main(String[] args) throws IOException {
        System.out.println(part1("inputs/day2.txt"));
        System.out.println(part2("inputs/day2.txt"));
    }

    public static int part1(String file) throws IOException {
        ArrayList<ArrayList<Integer>> lists = makeList(file);
        int counter = 0;
        for (ArrayList<Integer> list : lists) {
            if (isSafe(list)) {
                counter++;
            }
        }
        return counter;
    }

    public static int part2(String file) throws IOException {
        ArrayList<ArrayList<Integer>> lists = makeList(file);
        int counter = 0;
        boolean safe;
        for (ArrayList<Integer> list : lists) {
            safe = false;
            for (int i = -1; i < list.size(); i++) {
                if (i == -1) {
                    if (isSafe(list)) safe = true;
                } else {
                    ArrayList<Integer> listCopy = (ArrayList<Integer>) list.clone();
                    listCopy.remove(i);
                    if (isSafe(listCopy)) safe = true;
                }
            }
            if (safe) counter++;
        }
        return counter;
    }

    public static boolean isSafe(ArrayList<Integer> list) {
        boolean incr, curr;
        for (int j = 1; j < list.size() - 1; j++) {
            incr = list.get(j - 1) < list.get(j);
            curr = list.get(j) < list.get(j + 1);
            if (curr != incr) {
                return false;
            }
            if ((Math.abs(list.get(j) - list.get(j + 1)) > 3) || (Math.abs(list.get(j) - list.get(j + 1)) < 1)) {
                return false;
            }
            if (j == 1) {
                if ((Math.abs(list.get(0) - list.get(1)) > 3) || (Math.abs(list.get(0) - list.get(1)) < 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static ArrayList<ArrayList<Integer>> makeList(String file) throws IOException {
        String data = readFile(file);
        String[] vals = data.split("\n");
        String[] split;
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (String val : vals) {
            split = val.split(" ");
            list.clear();

            for (String s : split) {
                list.add(Integer.parseInt(s));
            }
            ArrayList<Integer> listCopy = (ArrayList<Integer>) list.clone();

            lists.add(listCopy);

        }
        return lists;
    }
}
