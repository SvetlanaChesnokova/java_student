package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chesnokova.sa on 26.11.2016.
 */
public class GroupDataGenerator {

    @Parameter (names = "-c", description = "Group count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;

    //генерация тестовых данных
    //настройки, кол-во и путь можно указать в Edit Configuration
    //пример заполнения -f src/test/resources/groups.csv - c 10 -d xml
    //путь до E:\data\java_prodgekt\2016\student Test\java_student\addressbook-web-tests

    public static void main (String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        //generator- заполнено опцией, args-передано в командной строке
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<GroupData> groups = geberateGroups(count);
        if (format.equals("csv")){
            saveAsCsv(groups, new File (file));
        } else if (format.equals("xml")) {
            saveAsXml(groups, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsCsv(List<GroupData> groups, File file) throws IOException {
        //вывожу для себя путь
        System.out.println("**************************");
        System.out.println(new File(".").getAbsolutePath());
        System.out.println("**************************");

        Writer writer =  new FileWriter(file);
        for (GroupData group : groups){
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(),group.getFooter()));
        }
        writer.close();

    }

    private void saveAsXml(List<GroupData> groups, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        String xml = xstream.toXML(groups);

       Writer writer =  new FileWriter(file);
       writer.write(xml);
       writer.close();
    }

    private List<GroupData> geberateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++){
            groups.add(new GroupData().withName(String.format("test %s", i))
                    .withHeader(String.format("header %s", i)).withFooter(String.format("footer %s", i)));
        }
        return groups;
    }
}
