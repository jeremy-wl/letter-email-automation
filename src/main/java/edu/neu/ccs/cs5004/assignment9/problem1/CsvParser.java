package edu.neu.ccs.cs5004.assignment9.problem1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jeremy on 4/4/17.
 */
public class CsvParser implements Parser<Text> {
  private List<String> fields;
  private List<Member> entries;

  /**
   * Creates a csv parser with empty fields.
   */
  public CsvParser() {
    this.fields = new ArrayList<>();
    this.entries = new ArrayList<>();
  }

  @Override
  public List<Member> extractInfo(File<Text> inputFile) {
    Text fileContent = inputFile.readContent();
    try (BufferedReader bufferedReader = new BufferedReader(new StringReader(fileContent
        .getText()))) {
      String line;

      while ((line = bufferedReader.readLine()) != null) {
        List<String> parsedLine = parseLine(line);
        if (this.fields.isEmpty())  {
          this.fields = parsedLine;
        } else if (parsedLine == null)  {
          continue;
        } else if (parsedLine.size() != this.fields.size()) {
          throw new InvalidCsvException("The input csv format is invalid.");
        } else {
          this.entries.add(new Member(this.fields, parsedLine));
        }
      }
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
    }

    return this.entries;
  }

  /**
   * Given a line from the csv file, returns a list of column values from this line.
   * @param line a line from the csv file
   * @return a list of column values from this line
   */
  private List<String> parseLine(String line) {
    if (line.trim().equals(""))  {
      return null;  // using null to denote an empty line
    }

    List<String> res = new ArrayList<>();
    Pattern regex = Pattern.compile("\"(.*?)\"|,([^\"]+?),");
    Matcher matcher = regex.matcher(line);

    while (matcher.find()) {
      String match = matcher.group();
      res.add(match.substring(1, match.length() - 1));
    }
    return res;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    CsvParser csvParser = (CsvParser) obj;

    return fields.equals(csvParser.fields) && entries.equals(csvParser.entries);
  }

  @Override
  public int hashCode() {
    int result = fields.hashCode();
    result = 31 * result + entries.hashCode();
    return result;
  }
}
