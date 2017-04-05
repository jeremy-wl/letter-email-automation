package edu.neu.ccs.cs5004.assignment9.problem1;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeremy on 4/4/17.
 */
public class TemplatePopulator {
  private static final int PLACEHOLDER_LEFT_OFFSET  = 2;
  private static final int PLACEHOLDER_RIGHT_OFFSET = 2;

  private File generateFile(Member member, File<Text> templateFile, String filePath)
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

  public void populate(List<Member> memberList, File<Text> templateFile, String filePath)
         throws FileNotFoundException {
    for (int i = 0; i < memberList.size(); i++) {
      int fileNumbering = i + 1;
      File generatedFile = generateFile(memberList.get(i), templateFile,
                                        filePath + "/generated" + fileNumbering + ".txt");
      generatedFile.writeContent();
    }
  }
}
