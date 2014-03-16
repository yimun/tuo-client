package com.myapp.base;

public final class C {
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	// core settings (important)
	public static final class packageName{
		public static final String model = "com.myapp.model";
	}
	
	
	public static final class dir {
		public static final String base				= "/sdcard/demos";
		public static final String faces			= base + "/faces";
		public static final String images			= base + "/images";
	}
	
	public static final class api {
//		public static final String base				= "http://192.168.9.254:8007";
		public static final String base				= "http://202.118.67.200:1234";
		public static final String index			= "/index/index";
		public static final String login			= "/index/login";
		public static final String logout			= "/index/logout";
		public static final String register			= "/index/register";
		public static final String updateUserInfo 	= "/index/update";
		
		public static final String classifyList     = "/eio/classifyList";
		public static final String eioList     		= "/eio/eioList";
		public static final String eioSearch        = "/eio/search";
		public static final String quesList         = "/eio/quesList";
		public static final String eioDispose       = "/eio/dispose";
		public static final String eioComment       = "/eio/comment";
		public static final String listComment      = "/eio/listcomment";
		public static final String eioStamp         = "/eio/stamp";
		public static final String eioPraise        = "/eio/praise";
		public static final String myEioList        = "/eio/myEioList";
		public static final String myEioResult      = "/eio/myEioResult";
		
		public static final String userBlogList     = "/microblog/userBlogList";
		public static final String blogList     	= "/microblog/blogList";
		public static final String checkIn			= "/microblog/checkin";
		public static final String stamp			= "/microblog/stamp";
		
		public static final String noticeList		= "/notify/noticeList";
		public static final String noticeSetRead 	= "/notify/noticeSetRead";
		
		public static final String fansList			= "/friends/fansList";
		public static final String fansSearch		= "/friends/search";
		public static final String fansAdd			= "/friends/add";
		public static final String fansDel			= "/friends/del";
		
		public static final String feedBack 		= "/feedback/add";
		
	}
	
	public static final class task {
		public static final int index		     = 1001;
		public static final int login			 = 1002;
		public static final int logout		     = 1003;
		public static final int register		 = 1004;
		public static final int updateUserInfo   = 1005;
		
		public static final int classifyList     = 1006;
		public static final int eioList     	 = 1007;
		public static final int eioSearch        = 1008;
		public static final int quesList         = 1009;
		public static final int eioDispose       = 1010;
		public static final int eioComment       = 1011;
		public static final int listComment      = 1027;
		public static final int eioStamp         = 1012;
		public static final int eioPraise        = 1013;
		public static final int myEioList        = 1014;
		public static final int myEioResult      = 1015;
		
		public static final int userBlogList     = 1016;
		public static final int blogList         = 1017;
		public static final int checkIn			 = 1018;
		public static final int stamp			 = 1019;
		
		public static final int noticeList		 = 1020;
		public static final int noticeSetRead 	 = 1021;
		
		public static final int fansList		 = 1022;
		public static final int fansSearch		 = 1023;
		public static final int fansAdd			 = 1024;
		public static final int fansDel			 = 1025;
		
		public static final int feedBack 		 = 1026;
	}
	
	public static final class err {
		public static final String network			= "网络错误";
		public static final String message			= "消息错误";
		public static final String jsonFormat		= "消息格式错误";
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	// intent & action settings
	
	public static final class intent {
		public static final class action {
			public static final String EDITTEXT		= "com.myapp.EDITTEXT";
			public static final String EDITBLOG		= "com.myapp.EDITBLOG";
		}
	}
	
	public static final class action {
		public static final class edittext {
			public static final int CONFIG			= 2001;
			public static final int COMMENT			= 2002;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	// additional settings
	
	public static final class web {
		public static final String base				= "http://192.168.9.122:8002";
		public static final String index			= base + "/index.php";
		public static final String gomap			= base + "/gomap.php";
	}
}