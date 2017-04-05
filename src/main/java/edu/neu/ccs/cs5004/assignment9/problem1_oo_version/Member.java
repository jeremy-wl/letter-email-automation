package edu.neu.ccs.cs5004.assignment9.problem1_oo_version;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeremy on 4/4/17.
 */
class Member {
  private Map<String, String> info;

  public Member(List<String> fields, List<String> values) {
    this.info = new HashMap<>();
    for (int i = 0; i < fields.size(); i++)
      this.info.put(fields.get(i), values.get(i));
  }
}
