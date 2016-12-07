package ru.stqa.pft.mantis.appmanager;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;
import org.apache.tools.mail.MailMessage;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chesnokova.sa on 08.12.2016.
 */
public class MailHelper {
    private ApplicationManager app;
    private final Wiser wiser;

    public MailHelper(ApplicationManager app){
        this.app=app;
        //почтовый сервер
        wiser = new Wiser();
    }

    public List<MailMessage> waitForMail (int count, long timeout) throws MessagingException,IOException{
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + timeout) {
            if (wiser.getMessages().size() >= count) {
                return wiser.getMessages().stream().map((m)-> toModelMail(m)).collect(Collectors.toList());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new Error ("No mail :(");
    }

    private static MailMessage toModelMail(WiserMessage m) {
        try{
            MimeMessage mm = m.getMimeMessage();
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
        }   catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }   catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
    }

    public void  start() {
        wiser.start();
    }

    public void  stop() {
        wiser.stop();
    }

}
