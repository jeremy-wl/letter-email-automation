package edu.neu.ccs.cs5004.assignment9.problem1;

import edu.neu.ccs.cs5004.assignment9.problem1.testutils.TestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeremy on 3/26/17.
 */
public class CSVParserTest extends TestUtils {
  private static final String IO_DIR = System.getProperty("user.dir") +
          "/src/test/java/edu/neu/ccs/cs5004/assignment9/problem1/io";
  private CSVParser parser;
  private CSVParser parser_dup;
  private CSVParser parser_dup2;
  private CSVParser parser_diff;
  private List<Map<String, String>> entries;

  @Before
  public void setUp() throws Exception {
    parser = new CSVParser(IO_DIR + "/in/theater-company-members.csv");
    parser_dup = new CSVParser(IO_DIR + "/in/theater-company-members.csv");
    parser_dup2 = new CSVParser(IO_DIR + "/in/theater-company-members.csv");
    parser_diff = new CSVParser(IO_DIR + "/in/diff_csv.csv");

    entries = new ArrayList<Map<String, String>>() {{
      add(new HashMap<String, String>() {{
        put("[[first_name]]", "James");
        put("[[last_name]]", "Butt");
        put("[[company_name]]", "Benton, John B Jr");
        put("[[address]]", "6649 N Blue Gum St");
        put("[[city]]", "New Orleans");
        put("[[county]]", "Orleans");
        put("[[state]]", "LA");
        put("[[zip]]", "70116");
        put("[[phone1]]", "504-621-8927");
        put("[[phone2]]", "504-845-1427");
        put("[[email]]", "jbutt@gmail.com");
        put("[[web]]", "http://www.bentonjohnbjr.com");
      }});
      add(new HashMap<String, String>() {{
        put("[[first_name]]", "Josephine");
        put("[[last_name]]", "Darakjy");
        put("[[company_name]]", "Chanay, Jeffrey A Esq");
        put("[[address]]", "4 B Blue Ridge Blvd");
        put("[[city]]", "Brighton");
        put("[[county]]", "Livingston");
        put("[[state]]", "MI");
        put("[[zip]]", "48116");
        put("[[phone1]]", "810-292-9388");
        put("[[phone2]]", "810-374-9840");
        put("[[email]]", "josephine_darakjy@darakjy.org");
        put("[[web]]", "http://www.chanayjeffreyaesq.com");
      }});
    }};
  }

  @Test
  public void testParse() throws Exception {
    Assert.assertEquals(entries, parser.getEntries());
  }

  @Test(expected = InvalidCSVException.class)
  public void testParseInvalidCSV() throws Exception {
    parser = new CSVParser(IO_DIR + "/in/invalid.csv");
    parser = new CSVParser(IO_DIR + "/in/invalid2.csv");
  }

  @Test
  public void testEquals() throws Exception {
    Assert.assertTrue(isEqualsContractValid(parser, parser_dup, parser_dup2, parser_diff));
    Assert.assertEquals(parser, parser_dup);
    Assert.assertFalse(parser.equals(parser_diff));
  }

  @Test
  public void testHashCode() throws Exception {
    Assert.assertEquals(parser.hashCode(), parser_dup.hashCode());
    Assert.assertFalse(parser.hashCode() == parser_diff.hashCode());
  }
}
