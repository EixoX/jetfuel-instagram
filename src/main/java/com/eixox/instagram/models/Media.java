package com.eixox.instagram.models;

import java.util.Date;

public class Media {

	public static class From {
		public String username;
		public String id;
	}

	public static class Caption {
		public Date created_time;
		public String text;
		public String id;
		public From from;
	}

	public static class Likes {
		public long count;
	}

	public static class Comments {
		public long count;
	}

	public static class User {
		public String username;
		public String profile_picture;
		public String id;
		public String full_name;
	}

	public static class Image {
		public String url;
		public int width;
		public int height;
	}

	public static class Images {
		public Image low_resolution;
		public Image thumbnail;
		public Image standard_resolution;
	}

	public String type;
	public String filter;
	public String[] tags;
	public Caption caption;
	public Comments comments;
	public Likes likes;
	public String link;
	public User user;
	public Date created_time;
	public Images images;
	public String id;
	public Object location;
	public Object users_in_photo;
}
