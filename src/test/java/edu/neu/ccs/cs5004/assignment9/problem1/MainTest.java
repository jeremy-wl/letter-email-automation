package edu.neu.ccs.cs5004.assignment9.problem1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jeremy on 4/4/17.
 */
public class MainTest {
  private static final String IO_DIR = System.getProperty("user.dir") +
          "/src/test/java/edu/neu/ccs/cs5004/assignment9/problem1/io/";

  String[] args_valid1;
  String[] args_valid2;

  File<Text> csvFileOutput1;
  File<Text> csvFileOutput2;
  File<Text> csvFileOutput3;
  File<Text> csvFileOutput4;

  Text expectedOutput1;
  Text expectedOutput2;
  Text expectedOutput3;
  Text expectedOutput4;

  @Before
  public void setUp() throws Exception {
    args_valid1 = new String[] {"--letter", "--letter-template", IO_DIR + "/in/template_letter.txt",
         "--output-dir", IO_DIR + "/out", "--csv-file", IO_DIR + "/in/theater-company-members.csv"};
    args_valid2 = new String[] {"--email", "--email-template", IO_DIR + "/in/template_email.txt",
         "--output-dir", IO_DIR + "/out", "--csv-file", IO_DIR + "/in/theater-company-members.csv"};
    expectedOutput1 = new PlainTextFile(IO_DIR + "/out/expected/expected1.txt").getContent();
    expectedOutput2 = new PlainTextFile(IO_DIR + "/out/expected/expected2.txt").getContent();
    expectedOutput3 = new PlainTextFile(IO_DIR + "/out/expected/expected3.txt").getContent();
    expectedOutput4 = new PlainTextFile(IO_DIR + "/out/expected/expected4.txt").getContent();

  }

  @Test
  public void testMainLetter() throws Exception {
    Main.main(args_valid1);

    csvFileOutput1 = new PlainTextFile(IO_DIR + "/out/generated1.txt");
    csvFileOutput2 = new PlainTextFile(IO_DIR + "/out/generated2.txt");

    Assert.assertEquals(expectedOutput1, csvFileOutput1.getContent());
    Assert.assertEquals(expectedOutput2, csvFileOutput2.getContent());
  }
  @Test
  public void testMainEmail() throws Exception {
    Main.main(args_valid2);

    csvFileOutput3 = new PlainTextFile(IO_DIR + "/out/generated1.txt");
    csvFileOutput4 = new PlainTextFile(IO_DIR + "/out/generated2.txt");

    Assert.assertEquals(expectedOutput3, csvFileOutput3.getContent());
    Assert.assertEquals(expectedOutput4, csvFileOutput4.getContent());
  }
}
