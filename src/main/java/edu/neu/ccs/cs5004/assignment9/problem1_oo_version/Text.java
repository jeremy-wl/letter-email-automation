package edu.neu.ccs.cs5004.assignment9.problem1_oo_version;

/**
 * Created by Jeremy on 4/2/17.
 */
class Text {
  private String text;

  /**
   * Creates a text with its text.
   * The text to be passed in must not be null.
   *
   * @param text the text of the text
   */
  public Text(String text) {
    this.text = text;
  }

  /**
   * Getter for property 'text'.
   *
   * @return Value for property 'text'
   */
  public String getText() {
    return text;
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
