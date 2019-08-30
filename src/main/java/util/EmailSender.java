package util;

import dto.PointSimple;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class EmailSender {
    private List<PointSimple> redMarkers;
    private List<PointSimple> greenMarkers;
    private Message message;

    public EmailSender(List<PointSimple> redMarkers, List<PointSimple> greenMarkers){
        this.redMarkers = redMarkers;
        this.greenMarkers = greenMarkers;
    }
/*
Генерим URL статической карты с точками
 */
    StringBuilder generateURL(){
        StringBuilder url = new StringBuilder();
        String link = "https://maps.googleapis.com/maps/api/staticmap";
//        String center = "?center=60.775243,28.698896";
        String center = "";
        String zoom = "?zoom=15";
//        String zoom = "";
        String size = "&size=600x300";
        String mapType = "&maptype=roadmap";
        String markersRed = "&markers=color:red%7C";
        String markersGreen = "&markers=color:green%7C";
        String keyApi = "&key=AIzaSyC_0hHjpGU0ktipMMfo4CXMcqmeDBLANWI";
        url.append(link);
        url.append(center);
        url.append(zoom);
        url.append(size);
        url.append(mapType);
        for (int i = 0; i < greenMarkers.size(); i++) {
            url.append(markersGreen);
            url.append(greenMarkers.get(i).getLat());
            url.append(",");
            url.append(greenMarkers.get(i).getLng());
        }
        for (int i = 0; i < redMarkers.size(); i++) {
            url.append(markersRed);
            url.append(redMarkers.get(i).getLat());
            url.append(",");
            url.append(redMarkers.get(i).getLng());
        }

        url.append(keyApi);
        return url;
    }
    /*
Вставка URL-а в письмо
 */
    public String generateMessage(Map<String, Object> map){
        PageGenerator pageGenerator = PageGenerator.getInstance();
        StringBuilder url = generateURL();
        map.put("urlMap", url);
        System.out.println(url);
        return pageGenerator.getPage("email.html", map);
    }

    public void send(Map<String, Object> map) {
        final String username = "trashoptimizator@gmail.com";
        final String password = "new111112";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("trashoptimizator@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("new770001@yandex.ru, 770001@rambler.ru, khilkevichigor@gmail.com")
            );
            message.setSubject("Рекомендация по установке мусорных урн");
            String text = generateMessage(map);
            System.out.println(text);
            message.setContent(text, "text/html; charset=UTF-8");
//            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}