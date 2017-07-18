package com.eixox.instagram.models;

public class InstagramResponse<TData> {

	public static class Meta {

		public int code;
		public String error_type;
		public String error_message;
	}

	public static class Pagination {
		public String next_url;
		public String next_max_id;
	}

	public Meta meta;
	public TData data;
	public Pagination pagination;
}
