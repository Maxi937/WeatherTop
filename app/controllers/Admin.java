package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Song;
import play.Logger;
import play.mvc.Controller;

public class Admin extends Controller
{
    public static void index()
    {
        Logger.info("Rendering Admin");

        List<Song> song = Song.findAll();
        render ("Admin.html",song);
    }
}