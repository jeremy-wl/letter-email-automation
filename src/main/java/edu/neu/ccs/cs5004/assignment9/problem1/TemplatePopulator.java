package edu.neu.ccs.cs5004.assignment9.problem1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeremy on 4/4/17.
 */
public class TemplatePopulator {
  private static final int PLACEHOLDER_LEFT_OFFSET  = 2;
  private static final int PLACEHOLDER_RIGHT_OFFSET = 2;

  /**
   * Given a member info, a template file and the output file path,
   * returns a new file with its content populated with the given member info.
   * @param member a member info
   * @param templateFile a template file
   * @param filePath the output file path
   * @return a new file with its content populated with the given member info
   * @throws FileNotFoundException the exception that gets thrown when a file
   *                               with the specified file path does not exist
   */
  private File populate(Member member, File<Text> templateFile, String filePath)
          throws FileNotFoundException {
    Map<String, String> memberInfo = member.getInfo();
    StringBuilder populatedText = new StringBuilder(templateFile.getContent().getText());

    for (String field : memberInfo.keySet()) {
      int index = populatedText.indexOf(field);
      while (index != -1) {
        populatedText.replace(index - PLACEHOLDER_LEFT_OFFSET,
                              index + field.length() + PLACEHOLDER_RIGHT_OFFSET,
                              memberInfo.get(field));
        index = populatedText.indexOf(field);
      }
    }
    return new PlainTextFile(filePath, new Text(populatedText.toString()));
  }

  /**
   * Given a list of member info, a template file and the output file path,
   * returns a new file with its content populated with the given member info.
   * @param memberList a list of member info
   * @param templateFile a template file
   * @param filePath the output file path
   * @return a list of files with its content populated with the given member info
   * @throws FileNotFoundException the exception that gets thrown when a file
   *                               with the specified file path does not exist
   */
  public List<File> populate(List<Member> memberList, File<Text> templateFile, String filePath)
         throws FileNotFoundException {
    List<File> files = new ArrayList<>();
    for (int i = 0; i < memberList.size(); i++) {
      int fileNumbering = i + 1;
      File generatedFile = populate(memberList.get(i), templateFile,
                                        filePath + "/generated" + fileNumbering + ".txt");
      files.add(generatedFile);
    }
    return files;
  }
}
