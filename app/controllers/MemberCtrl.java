package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;
import weathertop.weather.instrument.Anemometer;

public class MemberCtrl extends Controller{

    public static void index()
    {
        Member member = Accounts.getLoggedInMember();
        Logger.info("rendering editmemberdetails.html");
        Logger.info("Member ID: "+ member);
        render("editmemberdetails.html",member);
    }


    public static void editDetails(Member member, String firstName, String lastName, String email) {
        member = Accounts.getLoggedInMember();
        member.firstName = firstName;
        member.lastName = lastName;
        member.email = email;
        member.save();
        Logger.info("Editing Member: "+ member);
        redirect("/member");
    }

}
