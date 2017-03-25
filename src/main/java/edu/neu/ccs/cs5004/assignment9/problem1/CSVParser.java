package edu.neu.ccs.cs5004.assignment9.problem1;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jeremy on 3/23/17.
 */
class CSVParser {
    private List<Map<String, String>> entries;

    public CSVParser(String fileName) throws IOException {
        entries = new ArrayList<>();
        parse(fileName);
    }

    // given a csv file name, returns a list of hashmap with the field being key
    private void parse(String fileName) throws FileNotFoundException, IOException {
        List<String> fields = null;
        List<List<String>> entries = null;
        try (BufferedReader inputFile = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = inputFile.readLine()) != null) {
                List<String> strings = parseLine(line);
                if (fields == null)   fields = new ArrayList<>(strings);
                else {
                    if (entries == null)   entries = new ArrayList<>();
                    entries.add(strings);
                }
            }
        }
        for (List<String> entry : entries) {
            if (entry.size() != fields.size())  continue; // TODO: throw exception here
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < fields.size(); i++) {
                map.put("[[" + fields.get(i) + "]]", entry.get(i));
            }
            this.entries.add(map);
        }
    }

    // add all strings in the line to a los
    // check corner cases
    private List<String> parseLine(String line) {
        List<String> res = new ArrayList<>();
        // "([^"]*"|[^,]*)(,|$)
        Pattern regex = Pattern.compile("\"(.*?)\"|,([^\"]+?),");
        // should match quoted  OR non-quoted str
        Matcher matcher = regex.matcher(line);

        while (matcher.find()) {
            String match = matcher.group();
            res.add(match.substring(1, match.length()-1));
        }
        return res;
    }

    public List<Map<String, String>> getEntries() {
        return entries;
    }

    public static void main(String[] args) throws Exception {
        CSVParser p = new CSVParser("/Users/Jeremy/Downloads/theater-company-members.csv");
        System.out.println(p);
    }

//    private boolean


//    public List<String> getFields() {
//        return fields;
//    }
//
//    public List<List<String>> getEntries() {
//        return entries;
//    }

//    public String getField(int index) {
//        return getFields().get(index);
//    }
//
//    public List<String> getEntry(int index) {
//        return getEntries().get(index);
//    }
}
