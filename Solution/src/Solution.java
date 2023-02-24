import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Знакомство с тегами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Usage: java Solution <tag>");
            return;
        }
        String fileName;
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = console.readLine();
        }

        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        }

        ArrayList<String> resultList = new ArrayList<>();
        String tag = args[0];
        while (builder.indexOf("<" + tag) != -1) {
            int start = builder.indexOf("<" + tag);
            int end = builder.indexOf("</" + tag) + tag.length() + 3;

            int newStart = start + tag.length() + 3;
            try {
                while (builder.substring(newStart, end).contains("<" + tag)) {
                    end = builder.substring(end).indexOf("</" + tag) + tag.length() + 3 + end;
                    newStart = end + 1;
                }
            } catch (StringIndexOutOfBoundsException qQ) {
                resultList.add(builder.substring(start, end));
                builder.delete(start, end);
            }
        }

        for (String resultItem : resultList) { System.out.println(resultItem); }
    }
}