package com.example.mediaplayerapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.List;

public class PlaylistManager {

    private final ObservableList<File> playlist = FXCollections.observableArrayList();
    private int currentIndex = -1;

    public ObservableList<File> getPlaylist() { return playlist; }
    public int getCurrentIndex() { return currentIndex; }
    public void setCurrentIndex(int index) { this.currentIndex = index; }

    public void addFiles(List<File> files) { playlist.addAll(files); }

    public boolean removeAt(int index) {
        if (index < 0 || index >= playlist.size()) return false;
        playlist.remove(index);
        if (playlist.isEmpty()) {
            currentIndex = -1;
        } else if (index < currentIndex) {
            currentIndex--;
        } else if (index == currentIndex && currentIndex >= playlist.size()) {
            currentIndex = playlist.size() - 1;
        }
        return true;
    }

    public boolean isEmpty() { return playlist.isEmpty(); }
    public int size() { return playlist.size(); }

    public File getCurrent() {
        if (currentIndex < 0 || currentIndex >= playlist.size()) return null;
        return playlist.get(currentIndex);
    }

    public File getNext() {
        if (playlist.isEmpty()) return null;
        currentIndex = (currentIndex + 1) % playlist.size();
        return playlist.get(currentIndex);
    }

    public File getPrevious() {
        if (playlist.isEmpty()) return null;
        currentIndex = (currentIndex - 1 + playlist.size()) % playlist.size();
        return playlist.get(currentIndex);
    }
}