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

    Arguments arguments = new Arguments(args);
    String csvFilePath = arguments.getCsvFilePath();
    String outputDir = arguments.getOutputDir();
    String templateFilePath = arguments.getTemplateFilePath();

    File<Text> csvFile = new CsvFile(csvFilePath);
    Parser<Text> csvParser = new CsvParser();
    List<Member> memberList = csvParser.extractInfo(csvFile);
    new TemplatePopulator().populate(memberList, new PlainTextFile(templateFilePath), outputDir);
  }
}
