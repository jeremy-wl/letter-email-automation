package edu.neu.ccs.cs5004.assignment9.problem1_oo_version;

import java.io.FileNotFoundException;

/**
 * Created by Jeremy on 4/3/17.
 */
class CsvFile extends AbstractTextFile {
  /**
   * Creates a csv file given the file path.
   * The file path given must be valid in the filesystem and not null.
   *
   * @param filePath the path of the file
   * @throws FileNotFoundException the exception that gets thrown when a file
   */
  public CsvFile(String filePath) throws FileNotFoundException {
    super(filePath);
  }

  /**
   * Creates a csv file given the file path.
   * The file path given must be valid in the filesystem and not null.
   * The content also should not be null.
   *
   * @param filePath the path of the file
   * @param content  the content of the file
   * @throws FileNotFoundException the exception that gets thrown when a file
   */
  public CsvFile(String filePath, Text content) throws FileNotFoundException {
    super(filePath, content);
  }
}
