# Theater Company Letter/Email Automation

The program was an assignment of the [Object Oriented Design and Analysis Course](http://www.ccs.neu.edu/home/skotthe/classes/cs5004/Spring/2017/syllabus.html)
Course, a more detailed problem description and specifications can be found
[here](http://www.ccs.neu.edu/home/skotthe/classes/cs5004/Spring/2017/module6.html#btab-a8).

## Problem Description
Suppose we have a CSV file containing all member information

```csv
"first_name" , "last_name" , "company_name" , "email"
"James"      , "Butt"      , "Benton"       , "504-845-1427"
"Josephine"  , "R, Darakjy", "Chanay"       , "810-374-9840"
"Art"        , "Venere"    , "Chemel"       , "856-264-4130"
```

, a letter and email template containing placeholders, where placeholders are enclosed in **[[ ]]**, below shows an example of a letter template

```
[[company_name]].
[[first_name]] [[last_name]]
[[address]], [[city]],
[[county]], [[state]], [[zip]]
([[email]])

Dear [[first_name]] [[last_name]],

    Please find enclosed your complementary tickets to "A Streetcar
    Named Desire" directed by John Jarmush and Susan Mae. We look
    forward to seeing you at one of our showings at our New York
    theater between March 1st and April 10th.

Sincerely,

```

Given the CSV file and letter/email templates the company would like you to create a program that they can run on the command line that would take these files as inputs, and generate files from templates that will contain the email messages and letters to be sent to their members.

The list of options to be specified as the command line arguments are as follows

```
--email                  only generate email messages
--email-template <file>  accepts a filename that holds the email template

--letter                 only generate letters
--letter-template <file> accepts a filename that holds the email template

--output-dir <path>      accepts the name of a folder, all output is placed in this folder
--csv-file <path>        accepts the name of the csv file to process
```

## Running the program

To run the program correctly, the command line arguments need to be valid, the same applies to the input files.
There are some samples ready in the test folder, you may run the program (Main class) with
 ```
 --email --email-template "src/test/java/edu/neu/ccs/cs5004/assignment9/problem1/io/in/template_email.txt" --output-dir "src/test/java/edu/neu/ccs/cs5004/assignment9/problem1/io/out" --csv-file "src/test/java/edu/neu/ccs/cs5004/assignment9/problem1/io/in/theater-company-members.csv"
 ```
 as arguments and check the generated output at "src/test/java/edu/neu/ccs/cs5004/assignment9/problem1/io/out"
