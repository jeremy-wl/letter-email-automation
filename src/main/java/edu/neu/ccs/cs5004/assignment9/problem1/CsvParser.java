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

/**
 * Represents a CSV parser.
 */
class CsvParser {
  private List<Map<String, String>> entries;

  /**
   * Creates the CSV parser and parse the given csv into a list of entries.
   *
   * @param fileName the name of a CSV file
   */
  CsvParser(String fileName) {
    entries = new ArrayList<>();
    parse(fileName);
  }

  /**
   * Given the name of a CSV file, parse the given csv file into a list of hash map,
   * with the key being the name of the field, and the value the value of the field.
   *
   * @param fileName the name of a CSV file
   */
  private void parse(String fileName) {
    List<String> fields = null;
    List<List<String>> entries = null;
    try (BufferedReader inputFile = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = inputFile.readLine()) != null) {
        List<String> strings = parseLine(line);
        if (fields == null) {
          fields = new ArrayList<>(strings);
        } else {
          if (entries == null) {
            entries = new ArrayList<>();
          }
          entries.add(strings);
        }
      }
      for (List<String> entry : entries) {
        if (entry.size() == 0) {
          continue;
        }
        if (entry.size() != fields.size()) {
          throw new InvalidCsvException("Invalid CSV format");
        }
        Map<String, String> map = new HashMap<>();
        for (int index = 0; index < fields.size(); index++) {
          map.put("[[" + fields.get(index) + "]]", entry.get(index));
        }
        this.entries.add(map);
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println("*** OUPS! A file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    }
  }

  /**
   * Given a string from a line read from a csv file.
   *
   * @param line a line read from a csv file
   *
   * @return a list of values parsed from the csv file
   */
  private List<String> parseLine(String line) {
    List<String> res = new ArrayList<>();

    Pattern regex = Pattern.compile("\"(.*?)\"|,([^\"]+?),");

    Matcher matcher = regex.matcher(line);

    while (matcher.find()) {
      String match = matcher.group();
      res.add(match.substring(1, match.length() - 1));
    }
    return res;
  }

  /**
   * Getter for the property 'entries'.
   *
   * @return value for the property 'entries'
   */
  public List<Map<String, String>> getEntries() {
    return entries;
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

    List<Map<String, String>> thisEntry = this.getEntries();
    List<Map<String, String>> thatEntry = csvParser.getEntries();

    if (thisEntry.size() != thatEntry.size()) {
      return false;
    }

    int index = 0;
    while (index < thisEntry.size()) {
      if (!thisEntry.get(index).equals(thatEntry.get(index))) {
        return false;
      }
      index++;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return getEntries() != null ? getEntries().hashCode() : 0;
  }
}