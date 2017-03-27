package edu.neu.ccs.cs5004.assignment9.problem1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeremy on 3/26/17.
 */
public class TemplatePopulatorTest {
    private CSVParser parser;
    private List<Map<String, String>> entries;
    private String templateContent;
    private TemplatePopulator tp;

    @Before
    public void setUp() throws Exception {
        parser = new CSVParser("io/in/theater-company-members.csv");
        tp = new TemplatePopulator();
        templateContent = "To:[[email]]\n" +
                "Subject:Information on this years members only show!\n" +
                "\n" +
                "Dear [[first_name]] [[last_name]], \n" +
                "\n" +
                "   This year's members only theater show will showcase \"A Streetcar\n" +
                "   Named Desire\" directed by John Jarmush and Susan Mae at our New\n" +
                "   York location between March 1st and April 10th.  Your complementary\n" +
                "   tickets for the show are on their way through mail and should\n" +
                "   reach you within the next couple of days.\n" +
                "\n" +
                "   Sincerely, \n";
        entries = new ArrayList<Map<String, String>>() {{
            add(new HashMap<String, String>() {{
                put("[[first_name]]", "James");
                put("[[last_name]]", "Butt");
                put("[[company_name]]", "Benton, John B Jr");
                put("[[address]]", "6649 N Blue Gum St");
                put("[[city]]", "New Orleans");
                put("[[county]]", "Orleans");
                put("[[state]]", "LA");
                put("[[zip]]", "70116");
                put("[[phone1]]", "504-621-8927");
                put("[[phone2]]", "504-845-1427");
                put("[[email]]", "jbutt@gmail.com");
                put("[[web]]", "http://www.bentonjohnbjr.com");
            }});
            add(new HashMap<String, String>() {{
                put("[[first_name]]", "Josephine");
                put("[[last_name]]", "Darakjy");
                put("[[company_name]]", "Chanay, Jeffrey A Esq");
                put("[[address]]", "4 B Blue Ridge Blvd");
                put("[[city]]", "Brighton");
                put("[[county]]", "Livingston");
                put("[[state]]", "MI");
                put("[[zip]]", "48116");
                put("[[phone1]]", "810-292-9388");
                put("[[phone2]]", "810-374-9840");
                put("[[email]]", "josephine_darakjy@darakjy.org");
                put("[[web]]", "http://www.chanayjeffreyaesq.com");
            }});
        }};
    }

    @Test
    public void testGetTemplateContent() throws Exception {
        Method method = tp.getClass().getDeclaredMethod("getFileContent", String.class);
        method.setAccessible(true);
        Assert.assertEquals(templateContent, method.invoke(tp, "io/in/template_email.txt"));
    }

    @Test
    public void testPopulateAll() throws Exception {
        Method method = tp.getClass().getDeclaredMethod("populateAll", String.class, List.class, String.class);
        method.setAccessible(true);
        method.invoke(tp, templateContent, parser.getEntries(), "io/out");
    }

    @Test
    public void testMainValidEmail() throws Exception {
        String[] argsValid = {"--email", "--email-template", "io/in/template_email.txt", "--output-dir",

                "io/out", "--csv-file", "io/in/theater-company-members.csv"};
        String outContent = "To:jbutt@gmail.com\n" +
                "Subject:Information on this years members only show!\n" +
                "\n" +
                "Dear James Butt, \n" +
                "\n" +
                "   This year's members only theater show will showcase \"A Streetcar\n" +
                "   Named Desire\" directed by John Jarmush and Susan Mae at our New\n" +
                "   York location between March 1st and April 10th.  Your complementary\n" +
                "   tickets for the show are on their way through mail and should\n" +
                "   reach you within the next couple of days.\n" +
                "\n" +
                "   Sincerely, ";
        Method method = tp.getClass().getDeclaredMethod("getFileContent", String.class);
        method.setAccessible(true);

        TemplatePopulator.main(argsValid);
        Assert.assertEquals(outContent, method.invoke(tp, "io/out/output1"));
    }

    @Test
    public void testMainValidLetter() throws Exception {
        String[] argsValid = {"--letter", "--letter-template", "io/in/template_letter.txt",
                              "--output-dir", "io/out", "--csv-file", "io/in/theater-company-members.csv"};
        String outContent = "Benton, John B Jr.\n" +
                "James Butt\n" +
                "6649 N Blue Gum St, New Orleans,\n" +
                "Orleans, LA, 70116\n" +
                "(jbutt@gmail.com)\n" +
                "\n" +
                "Dear James Butt, \n" +
                "\n" +
                "    Please find enclosed your complementary tickets to \"A Streetcar\n" +
                "    Named Desire\" directed by John Jarmush and Susan Mae. We look\n" +
                "    forward to seeing you at one of our showings at our New York\n" +
                "    theater between March 1st and April 10th.\n" +
                "\n" +
                "Sincerely, ";
        Method method = tp.getClass().getDeclaredMethod("getFileContent", String.class);
        method.setAccessible(true);

        TemplatePopulator.main(argsValid);
        Assert.assertEquals(outContent,  method.invoke(tp, "io/out/output1"));
    }

    @Test
    public void testMainInvalid() throws Exception {
        String[] argsinInvalid1 = {"--letter", "io/in/template_letter.txt",
                "--output-dir", "io/out", "--csv-file", "io/in/theater-company-members.csv"};
        String[] argsinInvalid2 = {"--email", "io/in/template_letter.txt",
                "--output-dir", "io/out", "--csv-file", "io/in/theater-company-members.csv"};
        String[] argsinInvalid3 = {"--letter", "--letter-template", "io/in/template_letter.txt",
                "--output-dir", "io/out", "aaa", "bbb", "ccc", "ddd"};
        TemplatePopulator.main(argsinInvalid1);
        TemplatePopulator.main(argsinInvalid2);
        TemplatePopulator.main(argsinInvalid3);
    }
}
