# Arguments

The program takes a list of arguments passed into the command line, we therefore can validate them inside this class and capture all information that is needed.

- If the arguments passed into the program is invalid, we throw an exception right away in this class.
- Otherwise, no matter what combination of arguments we get, we basically only need three things
  1. the input csv file path (csvFilePath)
  2. the template file path  (templateFilePath)
  3. the output directory    (outputDir)

The constructor takes the String[] args from the command line, and will either throw an exception, or initialize three instance variables, so that the program can access the information needed (the 3 fields listed above) right from this Arguments instance.

# File

A File is an object that contains its filePath in the filesystem and its content. It has two utility methods readContent() and writeContent(). readContent() returns its content and writeContent() writes the file to the filesystem.

# CSVParser

Given an input CSV file, parse the file line by line and extract all entries to a List of Members, where a Member is a Map containing all key/value information of the entry.

# TemplatePopulator
Given a list of Members, a template File, and the output directory, returns a list of files with all entry information populated to a new file from the template.