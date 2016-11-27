package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ClientData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chesnokova.sa on 27.11.2016.
 */
public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;

    //генерация тестовых данных
    //настройки, кол-во и путь можно указать в Edit Configuration
    //пример заполнения -f src/test/resources/contakts.xml -c 4 -d xml
    //путь до E:\data\java_prodgekt\2016\student Test\java_student\addressbook-web-tests

    public static void main (String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
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
        List<ClientData> groups = geberateGroups(count);
        if (format.equals("csv")){
            saveAsCsv(groups, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(groups, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(groups, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsJson(List<ClientData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsCsv(List<ClientData> groups, File file) throws IOException {
        //вывожу для себя путь
        System.out.println("**************************");
        System.out.println(new File(".").getAbsolutePath());
        System.out.println("**************************");
        try (Writer writer = new FileWriter(file)) {
            for (ClientData group : groups) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s\n", group.getP_lastname(), group.getP_firstnam(), group.getP_address(),
                        group.getP_email(), group.getP_phones(), group.getGroup()));
            }
        }
    }

    private void saveAsXml(List<ClientData> groups, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ClientData.class);
        String xml = xstream.toXML(groups);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private List<ClientData> geberateGroups(int count) {
        List<ClientData> groups = new ArrayList<ClientData>();
        for (int i = 0; i < count; i++){
            groups.add(new ClientData().withP_lastname(String.format("Ivanov %s", i))
                    .withP_firstnam(String.format("Vasj %s", i)).withP_address(String.format("address %s", i))
                    .withP_email("email@ngs.ru").withP_phones(String.format("9-546-54%s", i))
                    .withGroup(String.format("test %s", i)));
        }
        return groups;
    }
}
