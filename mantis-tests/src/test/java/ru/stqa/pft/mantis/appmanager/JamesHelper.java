package ru.stqa.pft.mantis.appmanager;


import org.apache.commons.net.telnet.TelnetClient;
import ru.stqa.pft.mantis.model.MailMessage;


import javax.mail.*;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by chesnokova.sa on 12.12.2016.
 */
public class JamesHelper {
    private ApplicationManager app;

    private TelnetClient telnet;
    private InputStream in;
    private PrintStream out;

    private Session mailSession;
    private Store store;
    private String mailserver;

    public  JamesHelper (ApplicationManager app) {
        this.app = app;
        telnet = new TelnetClient();
        mailSession = Session.getDefaultInstance(System.getProperties());
    }

    public boolean doesUserExist(String name) {
        //проверка существования пользователя
        initTelnetSession();
        write("verify " + name);
        String result = readUntil("exist");
        closeTelnetSession();
        return result.trim().equals("User " + name + " exist");
    }

    private void initTelnetSession() {
        mailserver = app.getProperty("mailserver.host");
        int port = Integer.parseInt(app.getProperty("mailserver.port"));
        String login = app.getProperty("mailserver.adminlogin");
        String password = app.getProperty("mailserver.adminpassword");

        try {
            //соединение с почтовым сервером
            telnet.connect(mailserver, port);
            //входной поток, читаем
            in = telnet.getInputStream();
            // пишем
            out = new PrintStream(telnet.getOutputStream());

        }   catch (Exception e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }

        //пишем
        readUntil("Login id:");
        write("");
        readUntil("Password:");
        write("");

        readUntil("Login id:");
        write(login);
        readUntil("Password:");
        write(password);

        readUntil ("Welcome " + login+ ". HELP for a list of commands");
    }

    private String readUntil (String pattern) {
        //посимвольно читается  письмо и сравнивается с шаблоном,
        // как только находим соответствие - считаем, что письмо прочитано
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            char ch = (char) in.read();
            while (true) {
                System.out.print(ch);
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                        return  sb.toString();
                    }
                }
                ch = (char) in.read();
            }
        }    catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void write(String value) {
        try {
            out.println(value);
            out.flush();
            System.out.println(value);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeTelnetSession() {
        write ("quit");
    }

    private void drainEmail(String username, String password) throws javax.mail.MessagingException {
        //позволяет удалить все письма полученне определеным пользователем.
        //письма помечаются флагом делите, при закрытии будут удалены.
        Folder inbox = openInbox(username, password);
        for (Message message : inbox.getMessages()) {
            message.setFlag(Flags.Flag.DELETED, true);
        }
        closeFolder(inbox);
    }

    private void closeFolder (Folder folder) throws javax.mail.MessagingException {
        //поментка всех писем на удаление
        folder.close(true);
        store.close();
    }

    public void createUser (String name, String passwd) {
        //создаем пользователя
        initTelnetSession();
        write("adduser " + name + " " + passwd);
        String result = readUntil("User " + name + " added");
        closeTelnetSession();
    }


    public void deleteUser (String name) {
        initTelnetSession();
        write("deluser " + name);
        String result = readUntil("User " + name + "deleted");
        closeTelnetSession();
    }

    public List<MailMessage> waitForMail(String username, String password, long timeout) throws javax.mail.MessagingException {
        //узнаем текущее время
        long now = System.currentTimeMillis();
        //проверяем, сколько времени прошло
        while (System.currentTimeMillis() < now + timeout) {
            List<MailMessage> allMail = getAllMail (username, password);
            if (allMail.size() > 0) {
                return allMail;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new Error ("No mail :(");
    }


    public List<MailMessage> getAllMail(String username, String password) throws javax.mail.MessagingException {
       //открытие
        Folder inbox = openInbox(username, password);
        List<MailMessage> messages = Arrays.asList(inbox.getMessages()).stream().map((m) ->
                toModelMail(m)).collect(Collectors.toList());
        closeFolder( inbox);
        // закрытие почтового ящика
        return messages;
    }

    private Folder openInbox(String username, String password) throws javax.mail.MessagingException {
      //протокол для доступа к почте
        store = mailSession.getStore("pop3");
        //вводим логин и пароль
        store.connect(mailserver,username, password);
        //доступ к  папки  INBOX
        Folder folder = store.getDefaultFolder().getFolder("INBOX");
        // открытие на запись и чтение
        folder.open(Folder.READ_WRITE);
        return folder;
    }


    public static MailMessage toModelMail (Message m)  {
        //берем первое письмо, читаем его и по полученому письму строем объект
        try {
            return new  MailMessage (m.getAllRecipients()[0].toString(),(String) m.getContent());
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }  catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
