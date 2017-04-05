package edu.neu.ccs.cs5004.assignment9.problem1;

/**
 * Created by Jeremy on 4/2/17.
 */
class Text {
  private String txt;

  /**
   * Creates a text object with its content.
   * The text to be passed in must not be null.
   *
   * @param txt the content of the text
   */
  public Text(String txt) {
    this.txt = txt;
  }

  /**
   * Getter for property 'txt'.
   *
   * @return Value for property 'txt'
   */
  public String getText() {
    return txt;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Text text = (Text) obj;

    return getText().equals(text.getText());
  }

  @Override
  public int hashCode() {
    return getText().hashCode();
  }
}
