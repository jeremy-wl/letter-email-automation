package edu.neu.ccs.cs5004.assignment9.problem1_oo_version;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by Jeremy on 4/4/17.
 */
public class FileTest extends edu.neu.ccs.cs5004.assignment9.problem1_oo_version.testutils.TestUtils {
  private static final String IO_DIR = System.getProperty("user.dir") +
          "/src/test/java/edu/neu/ccs/cs5004/assignment9/problem1_oo_version/io/";
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private File<Text> file_dup1;
  private File<Text> file_dup2;
  private File<Text> file_dup3;
  private File<Text> file_diff;

  @Before
  public void setUp() throws Exception {
    file_dup1 = new CsvFile(IO_DIR + "/in/template_letter.txt");
    file_dup2 = new CsvFile(IO_DIR + "/in/template_letter.txt");
    file_dup3 = new CsvFile(IO_DIR + "/in/template_letter.txt");
    file_diff = new CsvFile(IO_DIR + "/in/template_email.txt");
  }

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @After
  public void cleanUpStreams() {
    System.setOut(null);
  }

  @Test
  public void testEquals() throws Exception {
    Assert.assertTrue(isEqualsContractValid(file_dup1, file_dup2, file_dup3, file_diff));
    Assert.assertEquals(file_dup1, file_dup2);
    Assert.assertFalse(file_dup1.equals(file_diff));
    Assert.assertFalse(file_dup1.equals(null));
  }

  @Test
  public void testHashCode() throws Exception {
    Assert.assertEquals(file_dup1.hashCode(), file_dup2.hashCode());
    Assert.assertFalse(file_dup1.hashCode() == file_diff.hashCode());
  }

  @Test
  public void testLoadingANonExistentFile() throws Exception {
    new CsvFile("/dummy/directory/name/" + "file_that_does_not_exist.md");
    Assert.assertEquals(
            "The file was not found : /dummy/directory/name/file_that_does_not_exist.md "
                    + "(No such file or directory)\n", outContent.toString());
  }
}
