package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;
import weathertop.weather.instrument.Anemometer;

public class MemberCtrl extends Controller {

    public static void index() {
        Member member = Accounts.getLoggedInMember();
        Logger.info("rendering editmemberdetails.html");
        Logger.info("Member ID: " + member);
        render("editmemberdetails.html", member);
    }


    public static void editDetails(Member member, String firstName, String lastName, String email) {
        member = Accounts.getLoggedInMember();

        Logger.info(member.firstName);
        Logger.info(lastName);
        Logger.info(email);

        if (firstName.equals("") || lastName.equals("") || email.equals("")) {
            if (firstName.equals("")) {
                firstName = member.firstName;
            }
            if (lastName.equals("")) {
                lastName = member.lastName;
            }
            if (email.equals("")) {
                email = member.email;
            }
            Logger.info("All fields not completed");
        }

        member.firstName = firstName;
        member.lastName = lastName;
        member.email = email;
        member.save();
        Logger.info("Editing Member: " + member);
        redirect("/member");


    }

}
