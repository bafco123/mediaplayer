package com.example.mediaplayerapp;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.File;
import java.util.List;

public class MediaPlayerController {

    private static final String STYLE_ROOT =
            "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #0f0c29, #302b63, #24243e);" +
                    "-fx-font-family: 'Segoe UI', 'San Francisco', Arial, sans-serif;";

    private static final String STYLE_APP_TITLE =
            "-fx-font-size: 28px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: linear-gradient(to right, #ffd166, #ff9f1c);" +
                    "-fx-padding: 0;";

    private static final String STYLE_SUBTITLE =
            "-fx-font-size: 12px;" +
                    "-fx-text-fill: #b8b8d1;" +
                    "-fx-padding: 2 0 0 0;";

    private static final String STYLE_SHORTCUT_CHIP =
            "-fx-background-color: rgba(255, 209, 102, 0.1);" +
                    "-fx-text-fill: #ffd166;" +
                    "-fx-font-size: 11px;" +
                    "-fx-padding: 5 10 5 10;" +
                    "-fx-background-radius: 12;";

    private static final String STYLE_VIDEO_CARD =
            "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #000000, #1a1a2e);" +
                    "-fx-background-radius: 20;" +
                    "-fx-border-radius: 20;" +
                    "-fx-border-color: linear-gradient(from 0% 0% to 100% 100%, #ffd166, #ff9f1c, #06d6a0);" +
                    "-fx-border-width: 2;" +
                    "-fx-effect: dropshadow(gaussian, rgba(255, 209, 102, 0.25), 25, 0, 0, 8);";

    private static final String STYLE_CARD =
            "-fx-background-color: rgba(30, 30, 60, 0.55);" +
                    "-fx-background-radius: 16;" +
                    "-fx-border-color: rgba(255, 209, 102, 0.15);" +
                    "-fx-border-radius: 16;" +
                    "-fx-border-width: 1;" +
                    "-fx-padding: 16;";

    private static final String STYLE_PLAYLIST_TITLE =
            "-fx-font-size: 18px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: #ffd166;";

    // Circular big play button
    private static final String STYLE_PLAY_BUTTON =
            "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #ffd166, #ff9f1c);" +
                    "-fx-text-fill: #1e1e2e;" +
                    "-fx-font-size: 26px;" +
                    "-fx-background-radius: 50%;" +
                    "-fx-cursor: hand;" +
                    "-fx-effect: dropshadow(gaussian, rgba(255, 159, 28, 0.6), 20, 0.4, 0, 4);";

    // Small round transport buttons
    private static final String STYLE_TRANSPORT =
            "-fx-background-color: rgba(255, 255, 255, 0.08);" +
                    "-fx-text-fill: #ffd166;" +
                    "-fx-font-size: 18px;" +
                    "-fx-background-radius: 50%;" +
                    "-fx-border-color: rgba(255, 209, 102, 0.3);" +
                    "-fx-border-radius: 50%;" +
                    "-fx-border-width: 1;" +
                    "-fx-cursor: hand;";

    private static final String STYLE_ACCENT_BUTTON =
            "-fx-background-color: linear-gradient(to right, #06d6a0, #05b587);" +
                    "-fx-text-fill: #0a1f1a;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 13px;" +
                    "-fx-background-radius: 10;" +
                    "-fx-padding: 10 16 10 16;" +
                    "-fx-cursor: hand;" +
                    "-fx-effect: dropshadow(gaussian, rgba(6, 214, 160, 0.4), 12, 0.3, 0, 3);";

    private static final String STYLE_DANGER_BUTTON =
            "-fx-background-color: linear-gradient(to right, #ef476f, #d63a5f);" +
                    "-fx-text-fill: white;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 13px;" +
                    "-fx-background-radius: 10;" +
                    "-fx-padding: 10 16 10 16;" +
                    "-fx-cursor: hand;" +
                    "-fx-effect: dropshadow(gaussian, rgba(239, 71, 111, 0.4), 12, 0.3, 0, 3);";

    private static final String STYLE_ICON_BUTTON =
            "-fx-background-color: rgba(255, 255, 255, 0.08);" +
                    "-fx-text-fill: #eaeaea;" +
                    "-fx-font-size: 14px;" +
                    "-fx-background-radius: 10;" +
                    "-fx-padding: 8 14 8 14;" +
                    "-fx-cursor: hand;" +
                    "-fx-border-color: rgba(255, 255, 255, 0.1);" +
                    "-fx-border-radius: 10;" +
                    "-fx-border-width: 1;";

    private static final String STYLE_PLAYLIST_VIEW =
            "-fx-background-color: transparent;" +
                    "-fx-control-inner-background: transparent;" +
                    "-fx-background-insets: 0;" +
                    "-fx-padding: 0;";

    private static final String STYLE_NOW_PLAYING =
            "-fx-font-size: 16px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: #06d6a0;";

    private static final String STYLE_STATUS =
            "-fx-font-size: 12px;" +
                    "-fx-text-fill: #b8b8d1;";

    private static final String STYLE_TIME =
            "-fx-font-size: 12px;" +
                    "-fx-text-fill: #b8b8d1;" +
                    "-fx-min-width: 110px;" +
                    "-fx-alignment: center;";

    private static final String STYLE_VOLUME_ICON =
            "-fx-text-fill: #ffd166;" +
                    "-fx-font-size: 16px;";

    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private final PlaylistManager playlistManager = new PlaylistManager();

    private final BorderPane root = new BorderPane();
    private Label currentMediaLabel;
    private Label statusLabel;
    private Label timeLabel;
    private Slider volumeSlider;
    private Slider seekSlider;
    private ListView<File> playlistView;
    private Button playPauseButton;
    private Button muteButton;

  
    private boolean isMuted = false;
    private double volumeBeforeMute = 0.7;
    private boolean isSeeking = false;
    private boolean mediaEndedManually = false;

    private static final String[] SUPPORTED_EXTENSIONS = {
            "*.mp3", "*.mp4", "*.wav", "*.m4a", "*.m4v"
    };
    //  CONSTRUCTOR
    
    public MediaPlayerController() {
        root.setPadding(new Insets(20));
        root.setStyle(STYLE_ROOT);

        root.setTop(buildHeader());
        root.setCenter(buildCenterArea());
        root.setRight(buildPlaylistPanel());
        root.setBottom(buildControlBar());

        // Add fade-in animation on launch.
        FadeTransition ft = new FadeTransition(Duration.millis(600), root);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    public BorderPane getRoot() { return root; }

    private VBox buildHeader() {
        Label title = new Label("  Sound player");
        title.setStyle(STYLE_APP_TITLE);

        Label subtitle = new Label("media player");
        subtitle.setStyle(STYLE_SUBTITLE);

        VBox titleBox = new VBox(2, title, subtitle);

        // Shortcut chips — one per key
        HBox chips = new HBox(6,
                chip("Space", "Play/Pause"),
                chip("S", "Stop"),
                chip("N", "Next"),
                chip("P", "Prev"),
                chip("↑ ↓", "Volume"),
                chip("M", "Mute")
        );
        chips.setAlignment(Pos.CENTER_LEFT);

        HBox headerRow = new HBox(20, titleBox, spacer(), chips);
        headerRow.setAlignment(Pos.CENTER_LEFT);
        headerRow.setPadding(new Insets(0, 0, 18, 0));
        HBox.setHgrow(titleBox, Priority.ALWAYS);

        return new VBox(headerRow);
    }

    private Label chip(String key, String action) {
        Label l = new Label(key + " · " + action);
        l.setStyle(STYLE_SHORTCUT_CHIP);
        return l;
    }

    private VBox buildCenterArea() {
        mediaView = new MediaView();
        mediaView.setPreserveRatio(true);
        mediaView.setSmooth(true);

        StackPane videoContainer = new StackPane(mediaView);
        videoContainer.setStyle(STYLE_VIDEO_CARD);
        videoContainer.setMinHeight(420);

        mediaView.fitWidthProperty().bind(videoContainer.widthProperty().subtract(40));
        mediaView.fitHeightProperty().bind(videoContainer.heightProperty().subtract(40));

        // Seek slider
        seekSlider = new Slider(0, 100, 0);
        seekSlider.setStyle("-fx-pref-height: 6px;");
        HBox.setHgrow(seekSlider, Priority.ALWAYS);

        timeLabel = new Label("00:00 / 00:00");
        timeLabel.setStyle(STYLE_TIME);

        HBox seekBar = new HBox(14, seekSlider, timeLabel);
        seekBar.setAlignment(Pos.CENTER);
        seekBar.setPadding(new Insets(14, 4, 0, 4));

        seekSlider.setOnMousePressed(e -> isSeeking = true);
        seekSlider.setOnMouseReleased(e -> {
            if (mediaPlayer != null) {
                mediaPlayer.seek(Duration.millis(seekSlider.getValue()));
            }
            isSeeking = false;
        });

        VBox center = new VBox(4, videoContainer, seekBar);
        VBox.setVgrow(videoContainer, Priority.ALWAYS);
        return center;
    }

    private VBox buildPlaylistPanel() {
        Label playlistTitle = new Label(" Playlist");
        playlistTitle.setStyle(STYLE_PLAYLIST_TITLE);

        Label subtitle = new Label("Click a track to play it");
        subtitle.setStyle(STYLE_SUBTITLE);

        VBox titleBox = new VBox(2, playlistTitle, subtitle);
        titleBox.setPadding(new Insets(0, 0, 10, 0));

        playlistView = new ListView<>(playlistManager.getPlaylist());
        playlistView.setStyle(STYLE_PLAYLIST_VIEW);
        playlistView.setPrefWidth(280);
        VBox.setVgrow(playlistView, Priority.ALWAYS);

        playlistView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(File file, boolean empty) {
                super.updateItem(file, empty);
                if (empty || file == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText("♪  " + file.getName());
                    setStyle(
                            "-fx-background-color: transparent;" +
                                    "-fx-text-fill: #d5dae2;" +
                                    "-fx-font-size: 13px;" +
                                    "-fx-padding: 10 12 10 12;" +
                                    "-fx-background-radius: 8;"
                    );
                }
            }

            @Override
            public void updateSelected(boolean selected) {
                super.updateSelected(selected);
                if (selected && getItem() != null) {
                    setStyle(
                            "-fx-background-color: linear-gradient(to right, rgba(255,209,102,0.35), rgba(255,159,28,0.15));" +
                                    "-fx-text-fill: #ffd166;" +
                                    "-fx-font-size: 13px;" +
                                    "-fx-font-weight: bold;" +
                                    "-fx-padding: 10 12 10 12;" +
                                    "-fx-background-radius: 8;"
                    );
                } else if (getItem() != null) {
                    setStyle(
                            "-fx-background-color: transparent;" +
                                    "-fx-text-fill: #d5dae2;" +
                                    "-fx-font-size: 13px;" +
                                    "-fx-padding: 10 12 10 12;" +
                                    "-fx-background-radius: 8;"
                    );
                }
            }
        });

        playlistView.getSelectionModel().selectedIndexProperty().addListener(
                (obs, oldIdx, newIdx) -> {
                    int i = newIdx.intValue();
                    if (i >= 0 && i != playlistManager.getCurrentIndex()) {
                        playlistManager.setCurrentIndex(i);
                        playMedia(playlistManager.getCurrent());
                    }
                }
        );

        Button addButton = new Button(" Add Media");
        addButton.setStyle(STYLE_ACCENT_BUTTON);
        addButton.setMaxWidth(Double.MAX_VALUE);
        addButton.setOnAction(e -> handleAddMedia());

        Button removeButton = new Button("  Remove");
        removeButton.setStyle(STYLE_DANGER_BUTTON);
        removeButton.setMaxWidth(Double.MAX_VALUE);
        removeButton.setOnAction(e -> handleRemoveMedia());

        HBox buttonRow = new HBox(10, addButton, removeButton);
        HBox.setHgrow(addButton, Priority.ALWAYS);
        HBox.setHgrow(removeButton, Priority.ALWAYS);
        buttonRow.setPadding(new Insets(10, 0, 0, 0));

        VBox panel = new VBox(titleBox, playlistView, buttonRow);
        panel.setStyle(STYLE_CARD);
        panel.setPrefWidth(340);
        panel.setMaxWidth(360);
        panel.setPadding(new Insets(18, 0, 18, 18));
        VBox.setMargin(panel, new Insets(0, 0, 0, 20));
        return panel;
    }

   
    private VBox buildControlBar() {
        // ---- Now Playing row ----
        currentMediaLabel = new Label("No media loaded");
        currentMediaLabel.setStyle(STYLE_NOW_PLAYING);

        statusLabel = new Label("Ready");
        statusLabel.setStyle(STYLE_STATUS);

        VBox nowPlayingBox = new VBox(2, currentMediaLabel, statusLabel);
        nowPlayingBox.setAlignment(Pos.CENTER);

        // ---- Transport buttons ----
        Button prevButton  = new Button("previous");
        Button stopButton  = new Button("stop");
        Button nextButton  = new Button("Next");
        Button playButton  = new Button("Play");
        playPauseButton = playButton;

        // Small round buttons
        for (Button b : new Button[]{prevButton, stopButton, nextButton}) {
            b.setStyle(STYLE_TRANSPORT);
            b.setPrefSize(52, 52);
            attachHover(b);
        }
        // Big circular play button
        playButton.setStyle(STYLE_PLAY_BUTTON);
        playButton.setPrefSize(70, 70);
        attachHover(playButton);

        prevButton.setOnAction(e -> playPrevious());
        playButton.setOnAction(e -> play());
        stopButton.setOnAction(e -> stop());
        nextButton.setOnAction(e -> playNext());

        HBox transport = new HBox(18, prevButton, playButton, stopButton, nextButton);
        transport.setAlignment(Pos.CENTER);

       
        Label volIcon = new Label("volume");
        volIcon.setStyle(STYLE_VOLUME_ICON);

        volumeSlider = new Slider(0, 1, 0.7);
        volumeSlider.setPrefWidth(200);
        volumeSlider.valueProperty().addListener((obs, oldV, newV) -> {
            if (mediaPlayer != null) mediaPlayer.setVolume(newV.doubleValue());
            if (isMuted && newV.doubleValue() > 0) {
                isMuted = false;
                muteButton.setText("  Mute");
            }
        });

        muteButton = new Button(" Mute");
        muteButton.setStyle(STYLE_ICON_BUTTON);
        muteButton.setOnAction(e -> toggleMute());

        HBox volumeBox = new HBox(10, volIcon, volumeSlider, muteButton);
        volumeBox.setAlignment(Pos.CENTER);
        volumeBox.setPadding(new Insets(16, 0, 4, 0));

        VBox bottom = new VBox(10, nowPlayingBox, transport, volumeBox);
        bottom.setPadding(new Insets(20, 0, 0, 0));
        return bottom;
    }
   
    private Region spacer() {
        Region r = new Region();
        HBox.setHgrow(r, Priority.ALWAYS);
        return r;
    }

    /** Small scale-up effect on hover for buttons. */
    private void attachHover(Button b) {
        ScaleTransition grow = new ScaleTransition(Duration.millis(120), b);
        grow.setToX(1.08);
        grow.setToY(1.08);
        ScaleTransition shrink = new ScaleTransition(Duration.millis(120), b);
        shrink.setToX(1.0);
        shrink.setToY(1.0);
        b.setOnMouseEntered(e -> grow.playFromStart());
        b.setOnMouseExited(e -> shrink.playFromStart());
    }

    private void handleAddMedia() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Media Files");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Supported Media", SUPPORTED_EXTENSIONS),
                new FileChooser.ExtensionFilter("Audio (mp3, wav, m4a)", "*.mp3", "*.wav", "*.m4a"),
                new FileChooser.ExtensionFilter("Video (mp4, m4v)", "*.mp4", "*.m4v"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        Window owner = (root.getScene() != null) ? root.getScene().getWindow() : null;
        List<File> files = chooser.showOpenMultipleDialog(owner);
        if (files == null || files.isEmpty()) return;

        playlistManager.addFiles(files);

        if (playlistManager.getCurrentIndex() == -1) {
            playlistManager.setCurrentIndex(0);
            playlistView.getSelectionModel().select(0);
            playMedia(playlistManager.getCurrent());
        }

        statusLabel.setText(files.size() + " file(s) added to playlist.");
    }

    private void handleRemoveMedia() {
        int idx = playlistView.getSelectionModel().getSelectedIndex();
        if (idx < 0) {
            statusLabel.setText("Select an item in the playlist first.");
            return;
        }

        boolean wasCurrent = (idx == playlistManager.getCurrentIndex());
        playlistManager.removeAt(idx);

        if (playlistManager.isEmpty()) {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
                mediaPlayer = null;
            }
            currentMediaLabel.setText("No media loaded");
            statusLabel.setText("Playlist is empty.");
            return;
        }

        if (wasCurrent) {
            int newIdx = Math.min(idx, playlistManager.getPlaylist().size() - 1);
            playlistView.getSelectionModel().select(newIdx);
        } else {
            playlistView.getSelectionModel().select(playlistManager.getCurrentIndex());
        }
    }

    private void playMedia(File file) {
        if (file == null) return;

        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            }

            Media media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);

            mediaPlayer.setVolume(isMuted ? 0 : volumeSlider.getValue());

            mediaPlayer.setOnEndOfMedia(() -> {
                if (mediaEndedManually) {
                    mediaEndedManually = false;
                    return;
                }
                playNext();
            });

            mediaPlayer.setOnError(() -> {
                String err = mediaPlayer.getError() == null
                        ? "Unknown media error"
                        : mediaPlayer.getError().getMessage();
                statusLabel.setText("⚠ Playback error: " + err);
                showAlert("Playback Error",
                        "Could not play file:\n" + file.getName() + "\n\n" + err);
            });

            media.setOnError(() -> statusLabel.setText("⚠ Unsupported media file."));

            currentMediaLabel.setText("  " + file.getName());
            statusLabel.setText("Playing");

            bindSeekBar(mediaPlayer);

            mediaPlayer.play();
            playPauseButton.setText("pause");

        } catch (Exception ex) {
            showAlert("Error",
                    "Failed to load media:\n" + file.getName() + "\n\n" + ex.getMessage());
        }
    }

    private void bindSeekBar(MediaPlayer player) {
        player.currentTimeProperty().addListener((obs, oldT, newT) -> {
            if (!isSeeking) seekSlider.setValue(newT.toMillis());
            timeLabel.setText(formatTime(newT) + " / " + formatTime(player.getTotalDuration()));
        });

        player.totalDurationProperty().addListener((obs, oldD, newD) -> {
            seekSlider.setMax(newD.toMillis());
            timeLabel.setText("00:00 / " + formatTime(newD));
        });
    }

    private void play() {
        if (mediaPlayer == null) {
            if (!playlistManager.isEmpty()) {
                playlistManager.setCurrentIndex(0);
                playlistView.getSelectionModel().select(0);
                playMedia(playlistManager.getCurrent());
            } else {
                statusLabel.setText("Playlist is empty — add media first.");
            }
            return;
        }
        mediaPlayer.play();
        playPauseButton.setText("⏸");
        statusLabel.setText("Playing");
    }

    private void pause() {
        if (mediaPlayer == null) return;
        mediaPlayer.pause();
        playPauseButton.setText("▶");
        statusLabel.setText("Paused");
    }

    private void stop() {
        if (mediaPlayer == null) return;
        mediaEndedManually = true;
        mediaPlayer.stop();
        seekSlider.setValue(0);
        playPauseButton.setText("▶");
        statusLabel.setText("Stopped");
    }

    private void playNext() {
        if (playlistManager.isEmpty()) {
            statusLabel.setText("Playlist is empty.");
            return;
        }
        File next = playlistManager.getNext();
        playlistView.getSelectionModel().select(playlistManager.getCurrentIndex());
        playMedia(next);
    }

    private void playPrevious() {
        if (playlistManager.isEmpty()) {
            statusLabel.setText("Playlist is empty.");
            return;
        }
        File prev = playlistManager.getPrevious();
        playlistView.getSelectionModel().select(playlistManager.getCurrentIndex());
        playMedia(prev);
    }

    private void toggleMute() {
        if (volumeSlider == null) return;
        isMuted = !isMuted;
        if (isMuted) {
            volumeBeforeMute = volumeSlider.getValue();
            volumeSlider.setValue(0);
            if (mediaPlayer != null) mediaPlayer.setVolume(0);
            muteButton.setText("  Unmute");
            statusLabel.setText("  Muted");
        } else {
            volumeSlider.setValue(volumeBeforeMute == 0 ? 0.7 : volumeBeforeMute);
            if (mediaPlayer != null) mediaPlayer.setVolume(volumeSlider.getValue());
            muteButton.setText("  Mute");
            statusLabel.setText("  Unmuted");
        }
    }

    private void changeVolume(double delta) {
        double newVal = Math.max(0, Math.min(1, volumeSlider.getValue() + delta));
        volumeSlider.setValue(newVal);
        if (mediaPlayer != null) mediaPlayer.setVolume(newVal);
        statusLabel.setText("Volume: " + (int) (newVal * 100) + "%");
    }


    public void handleKeyPress(KeyCode code) {
        switch (code) {
            case SPACE -> {
                if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    pause();
                } else {
                    play();
                }
            }
            case S -> stop();
            case N -> playNext();
            case P -> playPrevious();
            case UP -> changeVolume(0.05);
            case DOWN -> changeVolume(-0.05);
            case M -> toggleMute();
            default -> { /* ignore */ }
        }
    }

    private String formatTime(Duration d) {
        if (d == null || d.isUnknown()) return "00:00";
        int totalSeconds = (int) d.toSeconds();
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void shutdown() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
    }
}