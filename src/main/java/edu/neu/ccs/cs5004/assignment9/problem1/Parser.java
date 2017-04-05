package edu.neu.ccs.cs5004.assignment9.problem1;

import java.util.List;

/**
 * Created by Jeremy on 4/4/17.
 */
public interface Parser<T> {
  /**
   * Extracts all member information from the given csv file.
   * @param inputFile the input file
   * @return a list of members extracted from the file
   */
  List<Member> extractInfo(File<T> inputFile);
}
