package net.younguard.bighorn;

/**
 * command define.
 * 
 * Copyright 2014-2015 by Young Guard Salon Community, China. All rights
 * reserved. http://www.younguard.net
 * 
 * NOTICE ! You can copy or redistribute this code freely, but you should not
 * remove the information about the copyright notice and the author.
 * 
 * @author ThomasZhang, thomas.zh@qq.com
 */
public class CommandTag
{
	// //////////////////////////////////////////////////
	// account session
	public static final short REGISTER_NOTIFY_TOKEN_REQUEST = 20101;
	public static final short REGISTER_NOTIFY_TOKEN_RESPONSE = 20102;
	public static final short DEVICE_LOGIN_REQUEST = 20103;
	public static final short DEVICE_LOGIN_RESPONSE = 20104;
	public static final short ACCOUNT_INFO_MODIFY_REQUEST = 20105;
	public static final short ACCOUNT_INFO_MODIFY_RESPONSE = 20106;
	public static final short SOCKET_CLOSE_REQUEST = 20109;

	// //////////////////////////////////////////////////
	// summary
	public static final short QUERY_SUMMSRY_BADGE_NUMBER_REQUEST = 20201;
	public static final short QUERY_SUMMSRY_BADGE_NUMBER_RESPONSE = 20202;
	public static final short QUERY_HISTORY_LIST_BADGE_NUMBER_REQUEST = 20203;
	public static final short QUERY_HISTORY_LIST_BADGE_NUMBER_RESPONSE = 20204;
	public static final short QUERY_INVITE_LIST_BADGE_NUMBER_REQUEST = 20205;
	public static final short QUERY_INVITE_LIST_BADGE_NUMBER_RESPONSE = 20206;
	public static final short QUERY_PLAYING_LIST_BADGE_NUMBER_REQUEST = 20207;
	public static final short QUERY_PLAYING_LIST_BADGE_NUMBER_RESPONSE = 20208;

	// //////////////////////////////////////////////////
	// game
	public static final short GAME_INVITE_CREATE_REQUEST = 20301;
	public static final short GAME_INVITE_CREATE_RESPONSE = 20302;
	public static final short GAME_INVITE_CREATE_NOTIFY = 20304;
	public static final short GAME_JOIN_REQUEST = 20305;
	public static final short GAME_JOIN_RESPONSE = 20306;
	public static final short GAME_JOIN_NOTIFY = 20308;
	public static final short GAME_PLAY_STEP_REQUEST = 20309;
	public static final short GAME_PLAY_STEP_RESPONSE = 20310;
	public static final short GAME_PLAY_STEP_NOTIFY = 20312;
	public static final short GAME_LOAD_MANUAL_REQUEST = 20313;
	public static final short GAME_LOAD_MANUAL_RESPONSE = 20314;
	public static final short GAME_SYNC_STEP_REQUEST = 20315;
	public static final short GAME_SYNC_STEP_RESPONSE = 20316;
	public static final short GAME_RESIGN_REQUEST = 20317;
	public static final short GAME_RESIGN_RESPONSE = 20318;
	public static final short GAME_RESIGN_NOTIFY = 20320;
	public static final short GAME_SYNC_REQUEST = 20321;
	public static final short GAME_SYNC_RESPONSE = 20322;

	public static final short GAME_INVITE_QUERY_PAGINATION_REQUEST = 20331;
	public static final short GAME_INVITE_QUERY_PAGINATION_RESPONSE = 20332;
	public static final short GAME_PLAYING_QUERY_PAGINATION_REQUEST = 20333;
	public static final short GAME_PLAYING_QUERY_PAGINATION_RESPONSE = 20334;
	public static final short GAME_HISTORY_QUERY_PAGINATION_REQUEST = 20335;
	public static final short GAME_HISTORY_QUERY_PAGINATION_RESPONSE = 20336;

	public static final short GAME_MY_INVITE_QUERY_PAGINATION_REQUEST = 20341;
	public static final short GAME_MY_INVITE_QUERY_PAGINATION_RESPONSE = 20342;
	public static final short GAME_MY_PLAYING_QUERY_PAGINATION_REQUEST = 20343;
	public static final short GAME_MY_PLAYING_QUERY_PAGINATION_RESPONSE = 20344;
	public static final short GAME_MY_HISTORY_QUERY_PAGINATION_REQUEST = 20345;
	public static final short GAME_MY_HISTORY_QUERY_PAGINATION_RESPONSE = 20346;

	public static final short GAME_PLAYER_INVITE_QUERY_PAGINATION_REQUEST = 20351;
	public static final short GAME_PLAYER_INVITE_QUERY_PAGINATION_RESPONSE = 20352;
	public static final short GAME_PLAYER_PLAYING_QUERY_PAGINATION_REQUEST = 20353;
	public static final short GAME_PLAYER_PLAYING_QUERY_PAGINATION_RESPONSE = 20354;
	public static final short GAME_PLAYER_HISTORY_QUERY_PAGINATION_REQUEST = 20355;
	public static final short GAME_PLAYER_HISTORY_QUERY_PAGINATION_RESPONSE = 20356;

	public static final short GAME_PLAYERS_QUERY_PAGINATION_REQUEST = 20361;
	public static final short GAME_PLAYERS_QUERY_PAGINATION_RESPONSE = 20362;
	public static final short GAME_PLAYER_SUMMARY_QUERY_REQUEST = 20363;
	public static final short GAME_PLAYER_SUMMARY_QUERY_RESPONSE = 20364;

	// //////////////////////////////////////////////////
	// broadcast message
	public static final short MESSAGE_PING_REQUEST = 21001;
	public static final short MESSAGE_PANG_RESPONSE = 21002;
	public static final short MESSAGE_PONG_RESPONSE = 21004;
	public static final short BADGE_NOTIFY = 21006;

}
