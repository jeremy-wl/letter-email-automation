package edu.neu.ccs.cs5004.assignment9.problem1_oo_version;

import edu.neu.ccs.cs5004.assignment9.problem1.testutils.TestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Jeremy on 4/4/17.
 */
public class CsvParserTest extends TestUtils {
  private static final String IO_DIR = System.getProperty("user.dir") +
          "/src/test/java/edu/neu/ccs/cs5004/assignment9/problem1_oo_version/io/";

  private CsvParser cp_dup1;
  private CsvParser cp_dup2;
  private CsvParser cp_dup3;
  private CsvParser cp_diff;

  private File<Text> csvFile1;
  private File<Text> csvFile2;

  @Before
  public void setUp() throws Exception {
    csvFile1 = new CsvFile(IO_DIR + "/in/theater-company-members.csv");
    csvFile2 = new CsvFile(IO_DIR + "/in/diff_csv.csv");

    cp_dup1 = new CsvParser();
    cp_dup2 = new CsvParser();
    cp_dup3 = new CsvParser();
    cp_diff = new CsvParser();

    cp_dup1.extractInfo(csvFile1);
    cp_dup2.extractInfo(csvFile1);
    cp_dup3.extractInfo(csvFile1);
    cp_diff.extractInfo(csvFile2);
  }

  @Test
  public void extractInfo() throws Exception {
  }

  @Test
  public void testEquals() throws Exception {
    Assert.assertTrue(isEqualsContractValid(cp_dup1, cp_dup2, cp_dup3, cp_diff));
    Assert.assertEquals(cp_dup1, cp_dup2);
    Assert.assertFalse(cp_dup1.equals(cp_diff));
    Assert.assertFalse(cp_dup1.equals(null));
    Assert.assertFalse(cp_dup1.equals(""));
  }

  @Test
  public void testHashCode() throws Exception {
    Assert.assertEquals(cp_dup1.hashCode(), cp_dup2.hashCode());
    Assert.assertFalse(cp_dup1.hashCode() == cp_diff.hashCode());
  }
}
