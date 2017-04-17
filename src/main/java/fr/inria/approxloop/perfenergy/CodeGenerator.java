package fr.inria.approxloop.perfenergy;

import fr.inria.approxloop.loopstodb.TakeLoopsToDb;
import fr.inria.approxloop.orm.Loop;
import fr.inria.approxloops.sqlite.SQLiteConnector;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.sql.ResultSet;
import java.util.HashMap;

import static fr.inria.approxloop.EmailSender.send;

/**
 * Created by elmarce on 24/03/17.
 */
public class CodeGenerator {

    protected Configuration templateConf;

    /**
     * Initializes the benchmark generator
     *
     * @throws java.io.IOException
     */
    protected void initialize(File templatePath) throws IOException {
        //Initialize the configuration only once
        if (templateConf != null) return;

        // Create your Configuration instance, and specify if up to what FreeMarker
        // version (here 2.3.22) do you want to apply the fixes that are not 100%
        // backward-compatible. See the Configuration JavaDoc for details.
        templateConf = new Configuration(Configuration.VERSION_2_3_22);

        // Specify the source where the template files come from. Here I set a
        // plain directory for it, but non-file-system sources are possible too:
        templateConf.setDirectoryForTemplateLoading(templatePath);

        // Set the preferred charset template files are stored in. UTF-8 is
        // a good choice in most applications:
        templateConf.setDefaultEncoding("UTF-8");

        // Sets how errors will appear.
        // During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
        templateConf.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        //templateConf.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    protected HashMap<String, Loop> getLoops(String project, int strategy) throws Exception {
        SQLiteConnector connector = new SQLiteConnector(TakeLoopsToDb.dbPath, "loop_versions", "uid", "code", "strategy");
        connector.connect();
        ResultSet resultSet = connector.getRows("uid LIKE '%" + project + "%' AND strategy = " + strategy);
        HashMap<String, Loop> result = new HashMap<>();
        while (resultSet.next()) {
            String uid = resultSet.getString(1);
            result.put(uid.replace(".", "_"), new Loop(uid, resultSet.getString(2),
                    resultSet.getInt(3)));
        }
        connector.close();
        return result;
    }

    protected void executeCommand(String... command) {
        int r;
        try {
            // Use a ProcessBuilder
            ProcessBuilder pb = new ProcessBuilder(command);
            Process p = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line = null;
            while ((line = br.readLine()) != null)
                System.out.println(line);
            while ((line = err.readLine()) != null)
                System.err.println(line);
            r = p.waitFor(); // Let the process finish.
        } catch (Exception e) {
            e.printStackTrace();
            //send("marcelino.rguez.cancio@gmail.com", "1qaz2wsx3edc*-", "Problems!", "The program was unable to run.");
            r = -1;
        }
        if (r != 0)
            throw new RuntimeException("The execution of the microbenchmark crashed");
    }

    protected void generate(HashMap<String, Object> input, String templateName, String outputPath) {
        generate(input, templateName, outputPath, false);
    }

    protected void generate(HashMap<String, Object> input, String templateName, String outputPath, boolean fullPath) {
        PrintWriter out = null;
        try {
            Template mainTmpl = templateConf.getTemplate(templateName + ".ftl");
            StringWriter writer = new StringWriter();
            mainTmpl.process(input, writer);
            String output = writer.getBuffer().toString();
            if ( fullPath )
                out = new PrintWriter(outputPath);
            else
                out = new PrintWriter(outputPath + "/" + templateName + ".java");
            out.println(output);
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null) out.close();
        }
    }
}
