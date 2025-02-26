package com.decimate.content.youtube;

import com.decimate.Client;
import com.decimate.Sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;


public class Youtube {

    private static List<YoutubeChannel> channels = new ArrayList<YoutubeChannel>();

    private static List<YoutubeVideo> videos = new ArrayList<YoutubeVideo>();

    public static Map<String, Sprite> thumbnailForVideo = new HashMap<String, Sprite>();

    public static Map<String, Sprite> thumbnailForChannel = new HashMap<String, Sprite>();

    private static String currentType = "video";

    public static String getCurrentType() {
        return currentType;
    }

    public static void setCurrentType(String type) {
        currentType = type;
    }

    public static void addThumbnail(String videoId, String imageURL, String type) {

        try {
            URL url = new URL(imageURL);
            try {
                if (type.equalsIgnoreCase("video")) {
                    BufferedImage image = Sprite.cropImage(ImageIO.read(url), 11, 11);
                    thumbnailForVideo.putIfAbsent(videoId, new Sprite(image));
                } else if (type.equalsIgnoreCase("channel")) {
                    BufferedImage image = ImageIO.read(url);
                    thumbnailForChannel.putIfAbsent(videoId, Sprite.getResizedSprite(new Sprite(image), 66, 66));
                }
                if (videos.size() == thumbnailForVideo.size() && channels.size() == thumbnailForChannel.size()) {
                    Client.instance.updateYoutubeInterface();
                }
            } catch (IOException ef) {
                ef.printStackTrace();
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
    }

    public static List<YoutubeChannel> getChannels() {
        return channels;
    }

    public static List<YoutubeVideo> getVideos() {
        return videos;
    }

    public static Sprite thumbnailForVideoId(String id) {
        if (thumbnailForVideo.get(id) != null) {
            return thumbnailForVideo.get(id);
        }
        return null;
    }

    public static Sprite thumbnailForChannelId(String id) {
        if (thumbnailForChannel.get(id) != null) {
            return thumbnailForChannel.get(id);
        }
        return null;
    }

    public static void handleYoutubeData(String type) {
        int totalParts = Client.instance.inStream.readUnsignedWord();
        switch (type) {
            case "video":
                Youtube.getVideos().clear();
                for (int i = 0; i < totalParts; i++) {
                    String iden = Client.instance.inStream.readString();
                    String title = Client.instance.inStream.readString();
                    long views = Client.instance.inStream.readQWord();
                    long likes = Client.instance.inStream.readQWord();
                    long comments = Client.instance.inStream.readQWord();
                    String channelName = Client.instance.inStream.readString();
                    String channelId = Client.instance.inStream.readString();
                    String thumbnail = Client.instance.inStream.readString();
                    Youtube.getVideos().add(new YoutubeVideo(iden, title, (int) views, (int) likes, (int) comments,
                            channelName, channelId));
                    Youtube.addThumbnail(iden, thumbnail, "video");
                }
                break;

            case "channel":
                Youtube.getChannels().clear();
                for (int i = 0; i < totalParts; i++) {
                    String iden = Client.instance.inStream.readString();
                    String title = Client.instance.inStream.readString();
                    long subscribers = Client.instance.inStream.readQWord();
                    String profilePic = Client.instance.inStream.readString();
                    Youtube.getChannels().add(new YoutubeChannel(iden, title, subscribers, profilePic));
                    Youtube.addThumbnail(iden, profilePic, "channel");
                }
                break;
        }
    }
}
