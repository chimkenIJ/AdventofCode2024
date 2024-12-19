import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

public class day1 {
    public static void main(String[] args) throws IOException {
        System.out.println(part1("inputs/day1.txt"));
        System.out.println(part2("inputs/day1.txt"));
    }

    public static int part1(String file) throws IOException {
        int[][] lists = makeList(file);
        int[] list1 = lists[0];
        int[] list2 = lists[1];
        Arrays.sort(list1);
        Arrays.sort(list2);
        int totalDifference = 0;
        for (int i = 0; i < list1.length; i++) {
            totalDifference += Math.abs(list1[i] - list2[i]);
        }
        return totalDifference;
    }

    public static int part2(String file) throws IOException {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[][] lists = makeList(file);
        int[] list1 = lists[0];
        int[] list2 = lists[1];
        for (int num : list1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int repeats = 0;
        for (int num : list2) {
            if (map.containsKey(num)) {
                repeats += (num * map.get(num));
            }
        }
        return repeats;
    }

    public static int[][] makeList(String file) throws IOException {
        String data = readFile(file);
        String[] vals = data.split("\n");
        int[] list1 = new int[vals.length];
        int[] list2 = new int[vals.length];
        String[] split;
        for (int i = 0; i < vals.length; i++) {
            split = vals[i].split(" {3}");
            list1[i] = Integer.parseInt(split[0]);
            list2[i] = Integer.parseInt(split[1]);
        }
        int[][] lists = new int[2][list1.length];
        lists[0] = list1;
        lists[1] = list2;
        return lists;
    }

    public static String readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }


}
