package net.younguard.bighorn;

public class GlobalArgs
{
	public final static short ACCOUNT_STATE_INACTIVE = 100;
	public final static short ACCOUNT_STATE_REGISTER = 101;
	public final static short ACCOUNT_STATE_BIND = 102;

	public final static short DEVICE_ACCOUNT_STATE_ACTIVE = 110;
	public final static short DEVICE_ACCOUNT_STATE_INACTIVE = 111;

	public final static short GAME_STATE_INVITE = 120;
	public final static short GAME_STATE_PLAYING = 121;
	public final static short GAME_STATE_COMPLETE = 122;

	public final static short GAME_MEMBER_STATE_PLAYING = 130;
	public final static short GAME_MEMBER_STATE_INVITED = 131;

	public final static short PLAYER_COLOR_RED = 140;
	public final static short PLAYER_COLOR_BLACK = 141;

	public final static short GAME_TIME_RULE_1DAY_25STEPS = 150;

	public final static short NOTIFY_TYPE_CLUB_CHAT_MSG = 160;
	public final static short NOTIFY_TYPE_GAME_CHAT_MSG = 161;
	public final static short NOTIFY_TYPE_GAME_DRAW = 162;
	public final static short NOTIFY_TYPE_GAME_DRAW_FEEDBACK = 163;
	public final static short NOTIFY_TYPE_GAME_INVITE = 164;
	public final static short NOTIFY_TYPE_GAME_INVITE_FEEDBACK = 165;
	public final static short NOTIFY_TYPE_GAME_JOIN = 166;
	public final static short NOTIFY_TYPE_GAME_PLAY_STEP = 167;
	public final static short NOTIFY_TYPE_GAME_PLAY_RESIGN = 168;

	public final static short CHANNEL_TYPE_INVITE = 170;
	public final static short CHANNEL_TYPE_PLAYING = 171;
	public final static short CHANNEL_TYPE_HISTORY = 172;

}
