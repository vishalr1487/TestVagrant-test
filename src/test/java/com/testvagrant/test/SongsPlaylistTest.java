package com.testvagrant.test;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SongsPlaylistTest {
	public SongsPlaylistTest givenSongs(HashMap<String, String> songsMap) {
		return this;
	}

	@Test(enabled = true)
	public void testAddSongToPlaylist() {

		RecentlyPlayedSongs recentlyPlayed = new RecentlyPlayedSongs(new HashMap<>(), 3);
		recentlyPlayed.addSongToPlaylist("VISHAL", "S1");
		recentlyPlayed.addSongToPlaylist("SUDEEP", "S2");
		recentlyPlayed.addSongToPlaylist("ANIL", "S3");

		String[] songs = recentlyPlayed.getUserPlaylist("VISHAL");
		Assert.assertEquals(songs, new String[] { "S1" });

		songs = recentlyPlayed.getUserPlaylist("SUDEEP");
		Assert.assertEquals(songs, new String[] { "S2" });

		songs = recentlyPlayed.getUserPlaylist("ANIL");
		Assert.assertEquals(songs, new String[] { "S3" });

	}

	@Test(enabled = true)
	public void testRemoveSongs() {

		RecentlyPlayedSongs recentlyPlayed = new RecentlyPlayedSongs(new HashMap<>(), 4);
		recentlyPlayed.addSongToPlaylist("NAVEEN", "S1");
		boolean removeSong = recentlyPlayed.removeLeastRecentlyPlayedSongFromPlayList("NAVEEN");
		String[] songs = recentlyPlayed.getUserPlaylist("NAVEEN");
		Assert.assertEquals(songs, null);

		recentlyPlayed.addSongToPlaylist("ANIL", "S50");
		recentlyPlayed.addSongToPlaylist("ANIL", "S100");
		boolean removeSong1 = recentlyPlayed.removeLeastRecentlyPlayedSongFromPlayList("ANIL");
		String[] songs1 = recentlyPlayed.getUserPlaylist("ANIL");
		Assert.assertEquals(songs1, new String[] { "S100" });
		System.out.println(songs1[0]);

	}

	@Test(enabled = true)
	public void testRemoveSongForInvalidUserId() {

		RecentlyPlayedSongs recentlyPlayed = new RecentlyPlayedSongs(new HashMap<>(), 4);
		recentlyPlayed.addSongToPlaylist("VISHAL", "S55");
		boolean removeSong = recentlyPlayed.removeLeastRecentlyPlayedSongFromPlayList("NAVEEN");
		Assert.assertFalse(removeSong);

	}

}
