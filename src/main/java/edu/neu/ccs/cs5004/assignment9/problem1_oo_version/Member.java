package edu.neu.ccs.cs5004.assignment9.problem1_oo_version;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeremy on 4/4/17.
 */
class Member {
  private Map<String, String> info;

  /**
   * Creates a Member with its information given a list of fields and a list of values.
   * Both of the arguments must not be null.
   * @param fields a list of fields
   * @param values a list of values
   */
  public Member(List<String> fields, List<String> values) {
    this.info = new HashMap<>();
    for (int i = 0; i < fields.size(); i++)
      this.info.put(fields.get(i), values.get(i));
  }

  public Map<String, String> getInfo() {
    return info;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Member member = (Member) o;

    return getInfo().equals(member.getInfo());
  }

  @Override
  public int hashCode() {
    return getInfo().hashCode();
  }
}