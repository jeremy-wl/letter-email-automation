package edu.neu.ccs.cs5004.assignment9.problem1;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Jeremy on 4/2/17.
 */
abstract class AbstractTextFile extends File<Text> {

  /**
   * Creates a text file given the file path.
   * The file path given must be valid in the filesystem and not null.
   *
   * @param filePath the path of the file
   * @throws FileNotFoundException the exception that gets thrown when a file
   *                               with the specified file path does not exist
   */
  protected AbstractTextFile(String filePath) throws FileNotFoundException {
    super(filePath);
    this.content = readContent();
  }

  /**
   * Creates a text file given the file path.
   * The file path given must be valid in the filesystem and not null.
   * The content also should not be null.
   *
   * @param filePath the path of the file
   * @param content  the content of the file
   * @throws FileNotFoundException the exception that gets thrown when a file
   *                               with the specified file path does not exist
   */
  protected AbstractTextFile(String filePath, Text content) throws FileNotFoundException {
    super(filePath);
    this.content = content;
  }

  /**
   * Getter for property 'content'.
   *
   * @return Value for property 'content'
   */
  @Override
  protected Text getContent() {
    return content;
  }

  @Override
  protected Text readContent() {
    StringBuilder res = new StringBuilder();
    try (BufferedReader inputFile = new BufferedReader(new InputStreamReader(
            new FileInputStream(filePath), "UTF-8"))) {
      String line;
      while ((line = inputFile.readLine()) != null) {
        if (res.length() != 0) {
          res.append("\n");
        }
        res.append(line);
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println("The file was not found : " + fnfe.getMessage());
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
    }
    return new Text(res.toString());
  }

  @Override
  protected void writeContent() {
    try (BufferedWriter outputFile = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(filePath), "UTF-8"))) {
      outputFile.write(content.getText());
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
    }
  }
}
