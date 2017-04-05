package edu.neu.ccs.cs5004.assignment9.problem1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeremy on 4/4/17.
 */
public class Arguments {
  private String csvFilePath;
  private String outputDir;
  private String templateFilePath;

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
   * Creates an Arguments object given an array of arguments.
   * Any argument in the array must not be null.
   * @param args an array of arguments passed from the command line
   */
  public Arguments(String[] args) {
    Map<String, String> argsMap = collectArguments(args);
    if (!isArgsValid(argsMap)) {
      throw new IllegalArgumentException("Argument not valid, check previous output message for details.");
    }
      this.csvFilePath = argsMap.get(OPT_CSV);
      this.outputDir = argsMap.get(OPT_OUTDIR);
      this.templateFilePath = argsMap.containsKey(OPT_EMAIL) ? argsMap.get(OPT_EMAIL_TEMP)
                                                             : argsMap.get(OPT_LETTER_TEMP);
  }

  /**
   * Collects all the arguments from the command line input and convert
   * the array of Strings to the corresponding map.
   *
   * @param args a list of arguments passed into the command line
   * @return the corresponding map of the list of Strings
   */
  private Map<String, String> collectArguments(String[] args) {
    Map<String, String> argsMap = new HashMap<>();
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case (OPT_EMAIL):
          argsMap.put(OPT_EMAIL, null);
          break;
        case (OPT_LETTER):
          argsMap.put(OPT_LETTER, null);
          break;
        case (OPT_EMAIL_TEMP):
          argsMap.put(OPT_EMAIL_TEMP, args[i + 1]);
          break;
        case (OPT_LETTER_TEMP):
          argsMap.put(OPT_LETTER_TEMP, args[i + 1]);
          break;
        case (OPT_OUTDIR):
          argsMap.put(OPT_OUTDIR, args[i + 1]);
          break;
        case (OPT_CSV):
          argsMap.put(OPT_CSV, args[i + 1]);
          break;
        default:
          break;
      }
    }
    return argsMap;
  }

  /**
   * Given a map of arguments we get from the command line input,
   * returns true if the arguments combination is valid, false otherwise.
   * @param argsMap the map of arguments
   * @return true if the arguments combination is valid, false otherwise
   */
  private boolean isArgsValid(Map<String, String> argsMap) {
    boolean isArgsValid = true;
    if (!argsMap.containsKey(OPT_OUTDIR) || !argsMap.containsKey(OPT_CSV)
        || (!argsMap.containsKey(OPT_EMAIL) && !argsMap.containsKey(OPT_LETTER))) {
      System.out.println(ERR_NO_REQUIRED_OPT + ERR_BODY);
      isArgsValid = false;
    } else if (argsMap.containsKey(OPT_EMAIL) && !argsMap.containsKey(OPT_EMAIL_TEMP)) {
      System.out.println(ERR_NO_EMAIL_TEMP + ERR_BODY);
      isArgsValid = false;
    } else if (argsMap.containsKey(OPT_LETTER) && !argsMap.containsKey(OPT_LETTER_TEMP)) {
      System.out.println(ERR_NO_LETTER_TEMP + ERR_BODY);
      isArgsValid = false;
    } else if (argsMap.size() > 4) {
      System.out.println(ERR_TOO_MANY_OPTS + ERR_BODY);
      isArgsValid = false;
    }
    return isArgsValid;
  }

  /**
   * Getter for property 'csvFilePath'.
   *
   * @return Value for property 'csvFilePath'
   */
  public String getCsvFilePath() {
    return csvFilePath;
  }

  /**
   * Getter for property 'outputDir'.
   *
   * @return Value for property 'outputDir'
   */
public String getOutputDir() {
    return outputDir;
  }

  /**
   * Getter for property 'templateFilePath'.
   *
   * @return Value for property 'templateFilePath'
   */
public String getTemplateFilePath() {
    return templateFilePath;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    Arguments arguments = (Arguments) obj;

    return getCsvFilePath().equals(arguments.getCsvFilePath())
            && getOutputDir().equals(arguments.getOutputDir())
            && getTemplateFilePath().equals(arguments.getTemplateFilePath());
  }

  @Override
  public int hashCode() {
    int result = getCsvFilePath().hashCode();
    result = 31 * result + getOutputDir().hashCode();
    result = 31 * result + getTemplateFilePath().hashCode();
    return result;
  }
}
