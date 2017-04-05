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
public class MemberTest extends TestUtils {
  private Member member_dup1;
  private Member member_dup2;
  private Member member_dup3;
  private Member member_diff;
  private List<String> fields;
  private List<String> values;
  private List<String> values_diff;


  @Before
  public void setUp() throws Exception {
    fields = new ArrayList<String>() {{
      add("title1");
      add("title2");
    }};
    values = new ArrayList<String>() {{
      add("value1");
      add("value2");
    }};
    values_diff = new ArrayList<String>() {{
      add("value0");
      add("value0");
    }};

    member_dup1 = new Member(fields, values);
    member_dup2 = new Member(fields, values);
    member_dup3 = new Member(fields, values);
    member_diff = new Member(fields, values_diff);
  }

  @Test
  public void testEquals() throws Exception {
    Assert.assertTrue(isEqualsContractValid(member_dup1, member_dup2, member_dup3, member_diff));
    Assert.assertEquals(member_dup1, member_dup2);
    Assert.assertFalse(member_dup1.equals(member_diff));
    Assert.assertFalse(member_dup1.equals(null));
    Assert.assertFalse(member_dup1.equals(""));
  }

  @Test
  public void testHashCode() throws Exception {
    Assert.assertEquals(member_dup1.hashCode(), member_dup2.hashCode());
    Assert.assertFalse(member_dup1.hashCode() == member_diff.hashCode());
  }
}
