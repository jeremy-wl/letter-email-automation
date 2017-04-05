package edu.neu.ccs.cs5004.assignment9.problem1_oo_version;

import java.util.List;

/**
 * Created by Jeremy on 4/4/17.
 */
public interface Parser<T> {
  List<Member> extractInfo(File<T> inputFile);
}
