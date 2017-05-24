package edu.neu.ccs.cs5004.assignment9.problem1;

import edu.neu.ccs.cs5004.assignment9.problem1.testutils.TestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jeremy on 4/4/17.
 */
public class ArgumentsTest extends TestUtils {
  private static final String IO_DIR = System.getProperty("user.dir") +
          "/src/test/java/edu/neu/ccs/cs5004/assignment9/problem1/io";

  private Arguments args_dup1;
  private Arguments args_dup2;
  private Arguments args_dup3;
  private Arguments args_diff;
  private Arguments args;

  @Before
  public void setUp() throws Exception {
    args_dup1 = new Arguments(new String[]{
            "--letter", "--letter-template", IO_DIR + "/in/template_letter.txt",
            "--output-dir", IO_DIR + "/out", "--csv-file", IO_DIR + "/in/theater-company-members.csv"
    });
    args_dup2 = new Arguments(new String[]{
            "--letter", "--letter-template", IO_DIR + "/in/template_letter.txt",
            "--output-dir", IO_DIR + "/out", "--csv-file", IO_DIR + "/in/theater-company-members.csv"
    });
    args_dup3 = new Arguments(new String[]{
            "--letter", "--letter-template", IO_DIR + "/in/template_letter.txt",
            "--output-dir", IO_DIR + "/out", "--csv-file", IO_DIR + "/in/theater-company-members.csv"
    });
    args_diff = new Arguments(new String[]{
            "--letter", "--letter-template", IO_DIR + "/in/template_email.txt",
            "--output-dir", IO_DIR + "/out", "--csv-file", IO_DIR + "/in/theater-company-members.csv"
    });
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidArguments1() throws Exception {
    args = new Arguments(new String[] {""});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidArguments2() throws Exception {
    args_dup1 = new Arguments(new String[] {"", "", "", "", "", "", "", "", "", ""});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLetterArguments() throws Exception {
    args_dup1 = new Arguments(new String[]{
            "--letter", "--email-template", IO_DIR + "/in/template_letter.txt",
            "--output-dir", IO_DIR + "/out", "--csv-file", IO_DIR + "/in/theater-company-members.csv"});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmailArguments() throws Exception {
    args_dup1 = new Arguments(new String[]{
            "--email", "--letter-template", IO_DIR + "/in/template_email.txt",
            "--output-dir", IO_DIR + "/out", "--csv-file", IO_DIR + "/in/theater-company-members.csv"});
  }

  @Test
  public void testEquals() throws Exception {
    Assert.assertTrue(isEqualsContractValid(args_dup1, args_dup2, args_dup3, args_diff));
    Assert.assertEquals(args_dup1, args_dup2);
    Assert.assertFalse(args_dup1.equals(args_diff));
    Assert.assertFalse(args_dup1.equals(null));
    Assert.assertFalse(args_dup1.equals(""));
  }

  @Test
  public void testHashCode() throws Exception {
    Assert.assertEquals(args_dup1.hashCode(), args_dup2.hashCode());
    Assert.assertFalse(args_dup1.hashCode() == args_diff.hashCode());
  }

}
