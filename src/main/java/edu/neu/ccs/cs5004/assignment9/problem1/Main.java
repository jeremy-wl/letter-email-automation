package edu.neu.ccs.cs5004.assignment9.problem1;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Jeremy on 4/4/17.
 */
public class Main {
  /**
   * Given a list of arguments from the command line input,
   * extracts all entries from the csv file specified,
   * populates them to the template specified,
   * and write the generated files to the filesystem.
   *
   * @param args a list of arguments from the command line input
   *
   * @throws FileNotFoundException the exception that gets thrown when a file
   *                               with the specified file path does not exist
   */
  public static void main(String[] args) throws FileNotFoundException {

    // Collecting arguments from command line
    Arguments arguments = new Arguments(args);
    String csvFilePath = arguments.getCsvFilePath();
    String outputDir = arguments.getOutputDir();
    String templateFilePath = arguments.getTemplateFilePath();

    // extracting member list from csv file
    File<Text> csvFile = new CsvFile(csvFilePath);
    Parser<Text> csvParser = new CsvParser();
    List<Member> memberList = csvParser.extractInfo(csvFile);

    // populate member info to files
    TemplatePopulator populator = new TemplatePopulator();
    List<File> files = populator.populate(memberList, new PlainTextFile(templateFilePath),
                                             outputDir);
    for (File file : files) {
      file.writeContent();
    }
  }
}
