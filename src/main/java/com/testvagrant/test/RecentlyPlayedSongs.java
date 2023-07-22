package com.testvagrant.test;

import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

public class RecentlyPlayedSongs {

	private Map<String, Queue<String>> userSongsMap;
	private int initialCapacity = 3;

	public RecentlyPlayedSongs(Map<String, Queue<String>> userSongsMap, int initialCapacity) {
		this.userSongsMap = userSongsMap;
		this.initialCapacity = initialCapacity;
	}

	public Map<String, Queue<String>> getUserSongsMap() {
		return userSongsMap;
	}

	public void setUserSongsMap(Map<String, Queue<String>> userSongsMap) {
		this.userSongsMap = userSongsMap;
	}

	public int getInitialCapacity() { 
		return initialCapacity;
	}

	public boolean removeLeastRecentlyPlayedSongFromPlayList(String userId) {

		if (userSongsMap.isEmpty()) {
			System.err.println("Data store is empty");
			return false;
		}

		if (userId == null || !userSongsMap.containsKey(userId)) {
			System.err.println("Invalid User Name");
			return false;
		}

		userSongsMap.get(userId).remove();
		return true;
	}

	public boolean addSongToPlaylist(String userId, String songName) {

		if (userId == null) {
			System.err.println("Invalid User Name");
			return false;
		}

		userSongsMap.putIfAbsent(userId, new LinkedList<>());

		if (userSongsMap.get(userId).size() == initialCapacity) {
			boolean flag = removeLeastRecentlyPlayedSongFromPlayList(userId);
			if (!flag) {
				System.err.println("Song not added, capacity is full");
				return false;
			}
		}

		userSongsMap.get(userId).add(songName);

		return true;
	}

	public void displayUserPlaylist(String userName) {

		if (userName == null || !userSongsMap.containsKey(userName)) {
			System.err.println("User not found");
			return;
		}

		for (String song : userSongsMap.get(userName)) {
			System.out.println(song);
		}
	}

	public void displayAllUsersPlaylist() {

		for (Entry<String, Queue<String>> entry : userSongsMap.entrySet()) {
			System.out.println(entry.getKey());
			for (String song : entry.getValue()) {
				System.out.println(song);
			}
		}
	}

	public String[] getUserPlaylist(String userName) {

		if (userName == null || !userSongsMap.containsKey(userName)) {
			System.err.println("User not found");
			return null;
		}
		
		if(userSongsMap.get(userName).isEmpty()) {
			System.err.println("Playlist is empty");
			return null;
		}
		
		return userSongsMap.get(userName).toArray(new String[userSongsMap.get(userName).size()]);
		
	}

}
