package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by chesnokova.sa on 07.12.2016.
 */
public class FtpHelper {
    //Помошник для подмены конфигурационного файла сайта.
    //В нашем случае, для отключения кода проверки, при регистрации нового сайта на сайте (картинки).


    private final ApplicationManager app;
    private FTPClient ftp;

    public FtpHelper(ApplicationManager app) {
        this.app = app;
        ftp = new FTPClient();
    }

    public void upload(File file, String target, String backup) throws IOException {
        //метод подменяет файл конфигурации на новый, а старый переименовывает, ещё создает и удаляет резервную копию

        //создается соединение с удаленой машиной
        ftp.connect(app.getProperty("ftp.host"));
        //осуществляется вход на удаленный сервер
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        //удаляет резервную копию
        ftp.deleteFile(backup);
        //переименовываем - делаем резервную копию
        ftp.rename(target, backup);
        ftp.enterLocalPassiveMode();
        //передается локальный файл, на удаленую машину
        ftp.storeFile(target, new FileInputStream(file));
        ftp.disconnect();
    }

    public void restore (String backup, String target) throws IOException {
        //метод востанавливает старый файл, конфигурации
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(target);
        ftp.rename(backup, target);
        ftp.disconnect();
    }
}
