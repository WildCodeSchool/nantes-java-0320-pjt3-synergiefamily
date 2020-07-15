package com.wildcodeschool.synergieFamily.service;

import com.wildcodeschool.synergieFamily.entity.ActivityLeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendNewUserEmail(String email, String password) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Inscription à Synergie Family");
        msg.setText("Bonjour,\n Votre mot de passe est : " + password);

        javaMailSender.send(msg);

    }

    public void sendNewActivityLeader(String url, ActivityLeader activityLeader, String token){

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(activityLeader.getEmail());

        msg.setSubject("Valider votre fiche sur Synergie Family");
        msg.setText("Bonjour,\n Merci de remplir vos informations sur le lien suivant :\n" +
                url + "/activity-leader-edit/" + token);

        javaMailSender.send(msg);

    }
    public void sendInformationActivityLeader(ActivityLeader activityLeader,
                                              String subject, String content){

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(activityLeader.getEmail());
        msg.setSubject(subject);
        msg.setText(content);

        javaMailSender.send(msg);

    }

    public void sendActivityLeaderByFilter(ActivityLeader activityLeader){

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(activityLeader.getEmail());
        msg.setSubject("Information");
        msg.setText("Bonjour,\n Voici les informations pour votre prochaine mission : ");

        javaMailSender.send(msg);

    }
}
