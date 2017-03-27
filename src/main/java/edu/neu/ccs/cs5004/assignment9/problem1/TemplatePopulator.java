package edu.neu.ccs.cs5004.assignment9.problem1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeremy on 3/24/17.
 */
class TemplatePopulator {
  private static final String OPT_EMAIL = "--email";
  private static final String OPT_LETTER = "--letter";
  private static final String OPT_EMAIL_TEMP = "--email-template";
  private static final String OPT_LETTER_TEMP = "--letter-template";
  private static final String OPT_OUTDIR = "--output-dir";
  private static final String OPT_CSV = "--csv-file";

  private static final String ERR_NO_EMAIL_TEMP = "\n\nError: --email provided but no "
           + "--email-template was given.";
  private static final String ERR_NO_LETTER_TEMP = "\n\nError: --letter provided but no "
           + "--letter-template was given.";
  private static final String ERR_NO_REQUIRED_OPT = "\n\nError: The command line options "
           + "--output-dir, csv-file "
           + "and either --email or --letter are required.";
  private static final String ERR_TOO_MANY_OPTS = "\n\nError: Too many options given, "
           + "you should provide either emails options or letter options.";

  private static final String ERR_BODY = "\n\nUsage: \n"
           + "\n"
           + "        --email                  only generate email messages\n"
           + "        --email-template <file>  accepts a filename that holds the email "
           + "template. Required if --email is used\n"
           + "\n"
           + "        --letter                 only generate letters\n"
           + "        --letter-template <file> accepts a filename that holds the email "
           + "template. Required if --letter is used\n"
           + "\n"
           + "        --output-dir <path>      accepts the name of a folder, all output is "
           + "placed in this folder\n"
           + "\n"
           + "        --csv-file <path>        accepts the name of the csv file to process\n"
           + "\n"
           + "Examples: \n"
           + "\n"
           + "       --email --email-template email-template.txt --output-dir emails "
           + "--csv-file customer.csv\n"
           + "       --letter --letter-template letter-template.txt --output-dir letters "
           + "--csv-file customer.csv\n"
           + "\n";

  /**
   * Gets the file content from the given file.
   *
   * @param fileName the name of the file
   *
   * @return the content of the given file
   */
  private static String getFileContent(String fileName) {
    StringBuilder res = new StringBuilder();
    try (BufferedReader inputFile = new BufferedReader(new InputStreamReader(
                                      new FileInputStream(fileName), "UTF-8"))) {
      String line;
      while ((line = inputFile.readLine()) != null) {
        if (res.length() != 0) {
          res.append("\n");
        }
        res.append(line);
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println("*** OUPS! A file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    }
    return res.toString();
  }

  /**
   * Populate the template with the given template and the entry parsed from csv file.
   *
   * @param templateContent the content of the template
   * @param map             map of an entry parsed from csv file
   *
   * @return a string with the template populated with the given entry
   */
  private static String populateTemplate(String templateContent, Map<String, String> map) {
    StringBuilder res = new StringBuilder(templateContent);
    for (String field : map.keySet()) {
      int index = res.indexOf(field);
      while (index != -1) {
        res.replace(index, index + field.length(), map.get(field));
        index = res.indexOf(field);
      }
    }
    return res.toString();
  }

  /**
   * Writes the content to the file specified.
   *
   * @param content the content of a file to be written
   * @param dest    the destination file
   */
  private static void writeToFile(String content, String dest) {
    try (BufferedWriter outputFile = new BufferedWriter(new OutputStreamWriter(
                                          new FileOutputStream(dest), "UTF-8"))) {
      outputFile.write(content);
    } catch (FileNotFoundException fnfe) {
      System.out.println("*** OUPS! A file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    }
  }

  /**
   * Populate the template with the given template and entries,
   * and creates files corresponding to each entry.
   *
   * @param templateContent the content of the template
   * @param entries         a list of entries parsed from the csv
   * @param outDir          the output directory
   */
  private static void populateAll(String templateContent, List<Map<String, String>> entries,
                                  String outDir) {
    int num = 1;
    for (Map<String, String> entry : entries) {
      String populated = populateTemplate(templateContent, entry);
      writeToFile(populated, outDir + "/" + "output" + num++);
    }
  }

  /**
   * Accepts certain arguments at the command line and populate different template to files
   * given different options
   *
   * {@code --email                 } only generate email messages
   * {@code --email-template <file> } accepts a filename that holds the email template
   *
   * {@code --letter                } only generate letters
   * {@code --letter-template <file>} accepts a filename that holds the email template
   *
   *
   * {@code --output-dir <path>}  accepts the name of a folder, all output is placed in this folder
   * {@code --csv-file <path>}    accepts the name of the csv file to process
   *
   * <p>When an illegal combination of inputs is provided by the user
   *    the program will exit with a helpful error message and a short explanation of
   *    how to use the program along with examples.
   *
   * @param args the arguments passed at the command line
   */
  public static void main(String[] args) {
    boolean isArgsValid = true;
    Map<String, String> params = new HashMap<>();
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case (OPT_EMAIL):
          params.put(OPT_EMAIL, null);
          break;
        case (OPT_LETTER):
          params.put(OPT_LETTER, null);
          break;
        case (OPT_EMAIL_TEMP):
          params.put(OPT_EMAIL_TEMP, args[i + 1]);
          break;
        case (OPT_LETTER_TEMP):
          params.put(OPT_LETTER_TEMP, args[i + 1]);
          break;
        case (OPT_OUTDIR):
          params.put(OPT_OUTDIR, args[i + 1]);
          break;
        case (OPT_CSV):
          params.put(OPT_CSV, args[i + 1]);
          break;
        default:
          break;
      }
    }
    if (!params.containsKey(OPT_OUTDIR) || !params.containsKey(OPT_CSV)
            || (!params.containsKey(OPT_EMAIL) && !params.containsKey(OPT_LETTER))) {
      System.out.println(ERR_NO_REQUIRED_OPT + ERR_BODY);
      isArgsValid = false;
    } else if (params.containsKey(OPT_EMAIL) && !params.containsKey(OPT_EMAIL_TEMP)) {
      System.out.println(ERR_NO_EMAIL_TEMP + ERR_BODY);
      isArgsValid = false;
    } else if (params.containsKey(OPT_LETTER) && !params.containsKey(OPT_LETTER_TEMP)) {
      System.out.println(ERR_NO_LETTER_TEMP + ERR_BODY);
      isArgsValid = false;
    } else if (params.size() > 4) {
      System.out.println(ERR_TOO_MANY_OPTS + ERR_BODY);
      isArgsValid = false;
    }

    if (isArgsValid) {
      String csvFile = params.get(OPT_CSV);
      String outDir = params.get(OPT_OUTDIR);
      String template = params.containsKey(OPT_EMAIL) ? params.get(OPT_EMAIL_TEMP)
              : params.get(OPT_LETTER_TEMP);
      TemplatePopulator populator = new TemplatePopulator();
      CsvParser parser = new CsvParser(csvFile);
      String templateContent = populator.getFileContent(template);
      populator.populateAll(templateContent, parser.getEntries(), outDir);
    }
  }
}
