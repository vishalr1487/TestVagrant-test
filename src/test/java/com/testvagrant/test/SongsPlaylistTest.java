package com.testvagrant.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SongsPlaylistTest {
    @Test(enabled = true)
    public void testAddSongToPlaylist() {

        RecentlyPlayedSongs recentlyPlayed = new RecentlyPlayedSongs(new HashMap<>(), 3);
        recentlyPlayed.addSongToPlaylist("VISHAL", "S1");
        recentlyPlayed.addSongToPlaylist("SUDEEP", "S2");
        recentlyPlayed.addSongToPlaylist("ANIL", "S3");
        recentlyPlayed.addSongToPlaylist("VISHAL", "S2");
        recentlyPlayed.addSongToPlaylist("VISHAL", "S3");
        recentlyPlayed.addSongToPlaylist("VISHAL", "S4");

        String[] songs = recentlyPlayed.getUserPlaylist("VISHAL");
        Assert.assertEquals(songs, new String[]{"S2", "S3", "S4"});

        songs = recentlyPlayed.getUserPlaylist("SUDEEP");
        Assert.assertEquals(songs, new String[]{"S2"});

        songs = recentlyPlayed.getUserPlaylist("ANIL");
        Assert.assertEquals(songs, new String[]{"S3"});

    }

    @Test(enabled = true)
    public void testAddOneSongToPlaylist() {

        RecentlyPlayedSongs recentlyPlayed = new RecentlyPlayedSongs(new HashMap<>(), 4);
        recentlyPlayed.addSongToPlaylist("VISHAL", "S1");
        recentlyPlayed.addSongToPlaylist("SUDEEP", "S2");
        recentlyPlayed.addSongToPlaylist("ANIL", "S3");


        String[] songs = recentlyPlayed.getUserPlaylist("VISHAL");
        Assert.assertEquals(songs, new String[]{"S1"});

        songs = recentlyPlayed.getUserPlaylist("SUDEEP");
        Assert.assertEquals(songs, new String[]{"S2"});

        songs = recentlyPlayed.getUserPlaylist("ANIL");
        Assert.assertEquals(songs, new String[]{"S3"});

    }

    @Test(enabled = true)
    public void testSameSongToDiffPlaylist() {

        RecentlyPlayedSongs recentlyPlayed = new RecentlyPlayedSongs(new HashMap<>(), 4);
        recentlyPlayed.addSongToPlaylist("VISHAL", "S1");
        recentlyPlayed.addSongToPlaylist("SUDEEP", "S1");
        recentlyPlayed.addSongToPlaylist("ANIL", "S1");


        String[] songs = recentlyPlayed.getUserPlaylist("VISHAL");
        Assert.assertEquals(songs, new String[]{"S1"});

        songs = recentlyPlayed.getUserPlaylist("SUDEEP");
        Assert.assertEquals(songs, new String[]{"S1"});

        songs = recentlyPlayed.getUserPlaylist("ANIL");
        Assert.assertEquals(songs, new String[]{"S1"});

    }

    @Test(enabled = true)
    public void testRemoveSongs() {

        RecentlyPlayedSongs recentlyPlayed = new RecentlyPlayedSongs(new HashMap<String, Queue<String>>(), 4);
        recentlyPlayed.addSongToPlaylist("NAVEEN", "S1");
        boolean removeSong = recentlyPlayed.removeLeastRecentlyPlayedSongFromPlayList("NAVEEN");
        String[] songs = recentlyPlayed.getUserPlaylist("NAVEEN");
        Assert.assertEquals(songs, null);

        recentlyPlayed.addSongToPlaylist("ANIL", "S50");
        recentlyPlayed.addSongToPlaylist("ANIL", "S100");
        boolean removeSong1 = recentlyPlayed.removeLeastRecentlyPlayedSongFromPlayList("ANIL");
        String[] songs1 = recentlyPlayed.getUserPlaylist("ANIL");
        Assert.assertEquals(songs1, new String[]{"S100"});


    }

    @Test(enabled = true)
    public void testRecentlyPlayedSong() {

        RecentlyPlayedSongs recentlyPlayed = new RecentlyPlayedSongs(new HashMap<String, Queue<String>>(), 4);

        recentlyPlayed.addSongToPlaylist("ANIL", "S50");
        recentlyPlayed.addSongToPlaylist("ANIL", "S100");
        String[] songs1 = recentlyPlayed.getUserPlaylist("ANIL");
        Assert.assertEquals(songs1[songs1.length - 1], "S100");


    }

    @Test(enabled = true)
    public void testLeastRecentlyPlayedSong() {

        RecentlyPlayedSongs recentlyPlayed = new RecentlyPlayedSongs(new HashMap<String, Queue<String>>(), 5);

        recentlyPlayed.addSongToPlaylist("VISHAL", "S1");
        recentlyPlayed.addSongToPlaylist("VISHAL", "S2");
        recentlyPlayed.addSongToPlaylist("VISHAL", "S3");
        recentlyPlayed.addSongToPlaylist("VISHAL", "S4");
        recentlyPlayed.addSongToPlaylist("VISHAL", "S5");
        recentlyPlayed.addSongToPlaylist("VISHAL", "S6");
        recentlyPlayed.addSongToPlaylist("VISHAL", "S7");


        String[] songs1 = recentlyPlayed.getUserPlaylist("VISHAL");
        Assert.assertEquals(songs1, new String[]{"S3", "S4", "S5", "S6", "S7"});


    }


    @Test(enabled = true)
    public void testInitialCapacity() {

        RecentlyPlayedSongs recentlyPlayed = new RecentlyPlayedSongs(new HashMap<>(), 4);
        for (String song : new String[]{"S1", "S2", "S3", "S4", "S5"}) {
            recentlyPlayed.addSongToPlaylist("VISHAL", song);
        }
        Assert.assertNotEquals(recentlyPlayed.getUserPlaylist("VISHAL").length, 5);
        Assert.assertEquals(recentlyPlayed.getUserPlaylist("VISHAL").length, 4);
        Assert.assertEquals(recentlyPlayed.getUserPlaylist("VISHAL"), new String[]{"S2", "S3", "S4", "S5"});


    }

    @Test(enabled = true)
    public void testRemoveSongForInvalidUserId() {

        RecentlyPlayedSongs recentlyPlayed = new RecentlyPlayedSongs(new HashMap<String, Queue<String>>(), 4);
        recentlyPlayed.addSongToPlaylist("VISHAL", "S55");
        boolean removeSong = recentlyPlayed.removeLeastRecentlyPlayedSongFromPlayList("NAVEEN");
        Assert.assertFalse(removeSong);

    }

}
