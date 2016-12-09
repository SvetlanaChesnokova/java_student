package ru.stqa.pft.mantis.model;

/**
 * Created by chesnokova.sa on 09.12.2016.
 */
public class MailMessage {

    public String to;
    public String text;

    public MailMessage(String to, String text) {
        //кому пришло письмо
         this.to = to;
        //текст письма
         this.text = text;
     }

}
