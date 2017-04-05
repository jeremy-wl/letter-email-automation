package edu.neu.ccs.cs5004.assignment9.problem1;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Jeremy on 4/4/17.
 */
public class Main {
  private static final String IO_DIR = System.getProperty("user.dir") +
      "/src/test/java/edu/neu/ccs/cs5004/assignment9/problem1/io";

  public static void main(String[] args) throws FileNotFoundException {

    String[] argsTest = {"--letter", "--letter-template", IO_DIR + "/in/template_letter.txt",
           "--output-dir", IO_DIR + "/out", "--csv-file", IO_DIR + "/in/theater-company-members.csv"};
    Arguments arguments = new Arguments(argsTest);
    String csvFilePath = arguments.getCsvFilePath();
    String outputDir = arguments.getOutputDir();
    String templateFilePath = arguments.getTemplateFilePath();

    File<Text> csvFile = new CsvFile(csvFilePath);
    Parser<Text> csvParser = new CsvParser();
    List<Member> memberList = csvParser.extractInfo(csvFile);
    new TemplatePopulator().populate(memberList, new PlainTextFile(templateFilePath), outputDir);
  }
}
