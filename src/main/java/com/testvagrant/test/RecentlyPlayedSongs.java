package com.testvagrant.test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

public class RecentlyPlayedSongs {

    private Map<String, Queue<String>> userSongsMap;
    private int initialCapacity = 3;

    public RecentlyPlayedSongs(int initialCapacity) {
        this.userSongsMap = new HashMap<String, Queue<String>>();
        this.initialCapacity = initialCapacity;
    }

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

    public boolean removeLeastRecentlyPlayedSongFromPlayList(String user) {

        if (userSongsMap.isEmpty()) {
            System.err.println("Data store is empty");
            return false;
        }

        if (user == null || !userSongsMap.containsKey(user)) {
            System.err.println(user + "Invalid User Name");
            return false;
        }

        userSongsMap.get(user).remove();
        return true;
    }

    public boolean addSongToPlaylist(String user, String songName) {

        if (user == null) {
            System.err.println("Invalid User Name");
            return false;
        }

        userSongsMap.putIfAbsent(user, new LinkedList<String>());

        if (userSongsMap.get(user).size() == initialCapacity) {
            boolean flag = removeLeastRecentlyPlayedSongFromPlayList(user);
            if (!flag) {
                System.err.println("Song not added, capacity is full");
                return false;
            }
        }

        userSongsMap.get(user).add(songName);

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
            System.err.println(userName + " User not found");
            return null;
        }

        if (userSongsMap.get(userName).isEmpty()) {
            System.err.println(userName + " Playlist is empty");
            return null;
        }

        return userSongsMap.get(userName).toArray(new String[userSongsMap.get(userName).size()]);

    }

}
