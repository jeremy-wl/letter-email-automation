package edu.neu.ccs.cs5004.assignment9.problem1;

import java.io.*;
import java.util.HashMap;
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
        Map<String, String> params = new HashMap<>();
        final String OPT_EMAIL      = "--email";
        final String OPT_LETTER     = "--letter";
        final String OPT_EMAIL_TEMP  = "--email-template";
        final String OPT_LETTER_TEMP = "--letter-template";
        final String OPT_OUTDIR     = "--output-dir";
        final String OPT_CSV        = "--csv-file";

        final String ERR_NO_EMAIL_TEMP = "Error: --email provided but no --email-template was given.";
        final String ERR_NO_LETTER_TEMP = "Error: --letter provided but no --letter-template was given.";
        final String ERR_NO_REQUIRED_OPT = "Error: The command line options --output-dir, csv-file "
                                         + " and either --email or --letter are required.";
        final String ERR_TOO_MANY_OPTS = "Error: Too many options given, "
                                       + "you should provide either emails options or letter options.";

        final String ERR_BODY = "\n\nUsage: \n" +
                "\n" +
                "        --email                  only generate email messages\n" +
                "        --email-template <file>  accepts a filename that holds the email " +
                "template. Required if --email is used\n" +
                "\n" +
                "        --letter                 only generate letters\n" +
                "        --letter-template <file> accepts a filename that holds the email " +
                "template. Required if --letter is used\n" +
                "\n" +
                "        --output-dir <path>      accepts the name of a folder, all output is " +
                "placed in this folder\n" +
                "\n" +
                "        --csv-file <path>        accepts the name of the csv file to process\n" +
                "\n" +
                "Examples: \n" +
                "\n" +
                "       --email --email-template email-template.txt --output-dir emails " +
                "--csv-file customer.csv\n" +
                "       --letter --letter-template letter-template.txt --output-dir letters " +
                "--csv-file customer.csv\n" +
                "\n";

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case (OPT_EMAIL):
                    params.put(OPT_EMAIL, null);
                case (OPT_LETTER):
                    params.put(OPT_LETTER, null);
                case (OPT_EMAIL_TEMP):
                    params.put(OPT_EMAIL_TEMP, args[i+1]);
                case (OPT_LETTER_TEMP):
                    params.put(OPT_LETTER_TEMP, args[i+1]);
                case (OPT_OUTDIR):
                    params.put(OPT_OUTDIR, args[i+1]);
                case (OPT_CSV):
                    params.put(OPT_CSV, args[i+1]);
                default:
                    break;
            }

            if (!params.containsKey(OPT_OUTDIR) || !params.containsKey(OPT_CSV)
            || (!params.containsKey(OPT_EMAIL) && !params.containsKey(OPT_LETTER))) {
                System.out.println(ERR_NO_REQUIRED_OPT + ERR_BODY);
                System.exit(0);
            }
            if (params.containsKey(OPT_EMAIL) && !params.containsKey(OPT_EMAIL_TEMP)) {
                System.out.println(ERR_NO_EMAIL_TEMP + ERR_BODY);
                System.exit(0);
            }
            if (params.containsKey(OPT_LETTER) && !params.containsKey(OPT_LETTER_TEMP)) {
                System.out.println(ERR_NO_LETTER_TEMP + ERR_BODY);
                System.exit(0);
            }
            if (params.size() > 7) {
                System.out.println(ERR_TOO_MANY_OPTS + ERR_BODY);
                System.exit(0);
            }

            String csvFile  = params.get(OPT_CSV);
            String outDir   = params.get(OPT_OUTDIR);
            String template = params.containsKey(OPT_EMAIL) ? params.get(OPT_EMAIL_TEMP)
                                                            : params.get(OPT_LETTER_TEMP);
            TemplatePopulator tp = new TemplatePopulator();
            CSVParser p = new CSVParser(csvFile);
            String templateContent = tp.getTemplateContent(template);
            tp.populateAll(templateContent, p.getEntries(), outDir);
        }
    }
}
