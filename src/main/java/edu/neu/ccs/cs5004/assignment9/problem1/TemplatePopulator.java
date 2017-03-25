package edu.neu.ccs.cs5004.assignment9.problem1;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeremy on 3/24/17.
 */
class TemplatePopulator {
    public String getTemplateContent(String fileName) throws FileNotFoundException, IOException {
        StringBuilder res = new StringBuilder();
        try (BufferedReader inputFile = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = inputFile.readLine()) != null) {
                if (res.length() != 0)  res.append("\n");
                res.append(line);
            }
        }
        return res.toString();
    }

    public String populateTemplate(String templateContent, Map<String, String> map) {
        StringBuilder res = new StringBuilder(templateContent);
        for (String field : map.keySet()) {
            int index = res.indexOf(field);
            while (index != -1) {
                res.replace(index, index+field.length(), map.get(field));
                index = res.indexOf(field);
            }
        }
        return res.toString();
    }

    public void writeToFile(String content, String dest) throws IOException {
        try (BufferedWriter outputFile = new BufferedWriter(new FileWriter(dest))) {
            outputFile.write(content);
        }
    }

    public void populateAll(String templateContent, List<Map<String, String>> entries, String outDir) throws IOException {
        for (Map<String, String> entry : entries) {
            String populated = populateTemplate(templateContent, entry);
            writeToFile(populated, outDir+"/"+ entry.get("[[first_name]]"));
            // TODO: proper output file name
        }
    }

    public static void main(String[] args) throws Exception {
        String fn = "/Users/Jeremy/Downloads/template.txt";
        TemplatePopulator tp = new TemplatePopulator();
        CSVParser p = new CSVParser("/Users/Jeremy/Downloads/theater-company-members.csv");
        String str = tp.getTemplateContent(fn);
        tp.populateAll(str, p.getEntries(), "out");
    }
}
