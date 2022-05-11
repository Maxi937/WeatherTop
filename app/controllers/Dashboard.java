package controllers;

import models.Playlist;
import models.Song;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{
  public static void index() {

    Song s1 = new Song("Piano Sonata No. 3", "Beethoven", 4.30);
    Song s2 = new Song("Piano Sonata No. 7", "Beethoven", 3.15);
    Song s3 = new Song("Piano Sonata No. 10", "Beethoven", 3.50);
    Playlist playlist = new Playlist("Beethoven Sonatas");
    playlist.songs.add(s1);
    playlist.songs.add (s2);
    playlist.songs.add (s3);



    Logger.info("Rendering Dashboard");
    render ("dashboard.html", playlist);
  }
}
