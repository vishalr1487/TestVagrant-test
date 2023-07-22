package com.testvagrant.test;

import java.util.HashMap;

public class Test {

	private static final String SUDEEP = "Sudeep";
	private static final String VISHAL = "Vishal";

	public static void main(String[] args) {

		RecentlyPlayedSongs playedSongs = new RecentlyPlayedSongs(new HashMap<>(), 4);

		playedSongs.addSongToPlaylist(VISHAL, "S1");
		playedSongs.addSongToPlaylist(VISHAL, "S2");
		playedSongs.addSongToPlaylist(VISHAL, "S3");
		playedSongs.addSongToPlaylist(VISHAL, "S4");
		
		
		playedSongs.addSongToPlaylist(SUDEEP, "S10");
		playedSongs.addSongToPlaylist(SUDEEP, "S99");
		playedSongs.addSongToPlaylist(SUDEEP, "S5");
		playedSongs.addSongToPlaylist(SUDEEP, "S88");
		
		playedSongs.displayUserPlaylist(VISHAL);
		playedSongs.displayAllUsersPlaylist();
		
		
	}
	
	
}
