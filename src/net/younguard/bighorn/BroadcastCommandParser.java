package net.younguard.bighorn;

import java.io.UnsupportedEncodingException;

import net.younguard.bighorn.account.cmd.AccountInfoModifyReq;
import net.younguard.bighorn.account.cmd.AccountInfoModifyResp;
import net.younguard.bighorn.account.cmd.DeviceLoginReq;
import net.younguard.bighorn.account.cmd.DeviceLoginResp;
import net.younguard.bighorn.account.cmd.RegisterDeviceNotifyTokenReq;
import net.younguard.bighorn.account.cmd.RegisterDeviceNotifyTokenResp;
import net.younguard.bighorn.broadcast.cmd.MsgPangResp;
import net.younguard.bighorn.broadcast.cmd.MsgPingReq;
import net.younguard.bighorn.broadcast.cmd.MsgPongNotify;
import net.younguard.bighorn.broadcast.cmd.QueryOnlineNumReq;
import net.younguard.bighorn.broadcast.cmd.QueryOnlineNumResp;
import net.younguard.bighorn.chess.cmd.GameHistoryQueryPaginationReq;
import net.younguard.bighorn.chess.cmd.GameHistoryQueryPaginationResp;
import net.younguard.bighorn.chess.cmd.GameInviteCreateReq;
import net.younguard.bighorn.chess.cmd.GameInviteCreateResp;
import net.younguard.bighorn.chess.cmd.GameInviteQueryPaginationReq;
import net.younguard.bighorn.chess.cmd.GameInviteQueryPaginationResp;
import net.younguard.bighorn.chess.cmd.GameJoinNotify;
import net.younguard.bighorn.chess.cmd.GameJoinReq;
import net.younguard.bighorn.chess.cmd.GameJoinResp;
import net.younguard.bighorn.chess.cmd.GameLoadManualReq;
import net.younguard.bighorn.chess.cmd.GameLoadManualResp;
import net.younguard.bighorn.chess.cmd.GamePlayStepNotify;
import net.younguard.bighorn.chess.cmd.GamePlayStepReq;
import net.younguard.bighorn.chess.cmd.GamePlayStepResp;
import net.younguard.bighorn.chess.cmd.GamePlayingQueryPaginationReq;
import net.younguard.bighorn.chess.cmd.GamePlayingQueryPaginationResp;
import net.younguard.bighorn.chess.cmd.GameResignNotify;
import net.younguard.bighorn.chess.cmd.GameResignReq;
import net.younguard.bighorn.chess.cmd.GameResignResp;
import net.younguard.bighorn.chess.cmd.GameSyncStepReq;
import net.younguard.bighorn.chess.cmd.GameSyncStepResp;
import net.younguard.bighorn.chess.cmd.MyHistoryQueryPaginationReq;
import net.younguard.bighorn.chess.cmd.MyHistoryQueryPaginationResp;
import net.younguard.bighorn.chess.cmd.MyInviteQueryPaginationReq;
import net.younguard.bighorn.chess.cmd.MyInviteQueryPaginationResp;
import net.younguard.bighorn.chess.cmd.MyPlayingQueryPaginationReq;
import net.younguard.bighorn.chess.cmd.MyPlayingQueryPaginationResp;
import net.younguard.bighorn.chess.cmd.PlayerHistoryQueryPaginationReq;
import net.younguard.bighorn.chess.cmd.PlayerHistoryQueryPaginationResp;
import net.younguard.bighorn.chess.cmd.PlayerInviteQueryPaginationReq;
import net.younguard.bighorn.chess.cmd.PlayerInviteQueryPaginationResp;
import net.younguard.bighorn.chess.cmd.PlayerPlayingQueryPaginationReq;
import net.younguard.bighorn.chess.cmd.PlayerPlayingQueryPaginationResp;
import net.younguard.bighorn.comm.Command;
import net.younguard.bighorn.comm.CommandParser;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.session.cmd.SocketCloseReq;

/**
 * command parser used by client.
 * 
 * Copyright 2014-2015 by Young Guard Salon Community, China. All rights
 * reserved. http://www.younguard.net
 * 
 * NOTICE ! You can copy or redistribute this code freely, but you should not
 * remove the information about the copyright notice and the author.
 * 
 * @author ThomasZhang, thomas.zh@qq.com
 */
public class BroadcastCommandParser
		extends CommandParser
{
	public static TlvObject encode(Command cmd)
			throws UnsupportedEncodingException
	{
		return cmd.encode();
	}

	public static Command decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		switch (tlv.getTag()) {
		case CommandTag.ACCOUNT_INFO_MODIFY_REQUEST:
			return new AccountInfoModifyReq().decode(tlv);
		case CommandTag.ACCOUNT_INFO_MODIFY_RESPONSE:
			return new AccountInfoModifyResp().decode(tlv);
		case CommandTag.DEVICE_LOGIN_REQUEST:
			return new DeviceLoginReq().decode(tlv);
		case CommandTag.DEVICE_LOGIN_RESPONSE:
			return new DeviceLoginResp().decode(tlv);
		case CommandTag.REGISTER_NOTIFY_TOKEN_REQUEST:
			return new RegisterDeviceNotifyTokenReq().decode(tlv);
		case CommandTag.REGISTER_NOTIFY_TOKEN_RESPONSE:
			return new RegisterDeviceNotifyTokenResp().decode(tlv);
		case CommandTag.SOCKET_CLOSE_REQUEST:
			return new SocketCloseReq().decode(tlv);

		case CommandTag.GAME_HISTORY_QUERY_PAGINATION_REQUEST:
			return new GameHistoryQueryPaginationReq().decode(tlv);
		case CommandTag.GAME_HISTORY_QUERY_PAGINATION_RESPONSE:
			return new GameHistoryQueryPaginationResp().decode(tlv);
		case CommandTag.GAME_INVITE_QUERY_PAGINATION_REQUEST:
			return new GameInviteQueryPaginationReq().decode(tlv);
		case CommandTag.GAME_INVITE_QUERY_PAGINATION_RESPONSE:
			return new GameInviteQueryPaginationResp().decode(tlv);
		case CommandTag.GAME_PLAYING_QUERY_PAGINATION_REQUEST:
			return new GamePlayingQueryPaginationReq().decode(tlv);
		case CommandTag.GAME_PLAYING_QUERY_PAGINATION_RESPONSE:
			return new GamePlayingQueryPaginationResp().decode(tlv);
		case CommandTag.GAME_INVITE_CREATE_REQUEST:
			return new GameInviteCreateReq().decode(tlv);
		case CommandTag.GAME_INVITE_CREATE_RESPONSE:
			return new GameInviteCreateResp().decode(tlv);
		case CommandTag.GAME_JOIN_REQUEST:
			return new GameJoinReq().decode(tlv);
		case CommandTag.GAME_JOIN_RESPONSE:
			return new GameJoinResp().decode(tlv);
		case CommandTag.GAME_JOIN_NOTIFY:
			return new GameJoinNotify().decode(tlv);
		case CommandTag.GAME_LOAD_MANUAL_REQUEST:
			return new GameLoadManualReq().decode(tlv);
		case CommandTag.GAME_LOAD_MANUAL_RESPONSE:
			return new GameLoadManualResp().decode(tlv);
		case CommandTag.GAME_MY_HISTORY_QUERY_PAGINATION_REQUEST:
			return new MyHistoryQueryPaginationReq().decode(tlv);
		case CommandTag.GAME_MY_HISTORY_QUERY_PAGINATION_RESPONSE:
			return new MyHistoryQueryPaginationResp().decode(tlv);
		case CommandTag.GAME_MY_INVITE_QUERY_PAGINATION_REQUEST:
			return new MyInviteQueryPaginationReq().decode(tlv);
		case CommandTag.GAME_MY_INVITE_QUERY_PAGINATION_RESPONSE:
			return new MyInviteQueryPaginationResp().decode(tlv);
		case CommandTag.GAME_MY_PLAYING_QUERY_PAGINATION_REQUEST:
			return new MyPlayingQueryPaginationReq().decode(tlv);
		case CommandTag.GAME_MY_PLAYING_QUERY_PAGINATION_RESPONSE:
			return new MyPlayingQueryPaginationResp().decode(tlv);
		case CommandTag.GAME_PLAY_STEP_REQUEST:
			return new GamePlayStepReq().decode(tlv);
		case CommandTag.GAME_PLAY_STEP_RESPONSE:
			return new GamePlayStepResp().decode(tlv);
		case CommandTag.GAME_PLAY_STEP_NOTIFY:
			return new GamePlayStepNotify().decode(tlv);
		case CommandTag.GAME_PLAYER_HISTORY_QUERY_PAGINATION_REQUEST:
			return new PlayerHistoryQueryPaginationReq().decode(tlv);
		case CommandTag.GAME_PLAYER_HISTORY_QUERY_PAGINATION_RESPONSE:
			return new PlayerHistoryQueryPaginationResp().decode(tlv);
		case CommandTag.GAME_PLAYER_INVITE_QUERY_PAGINATION_REQUEST:
			return new PlayerInviteQueryPaginationReq().decode(tlv);
		case CommandTag.GAME_PLAYER_INVITE_QUERY_PAGINATION_RESPONSE:
			return new PlayerInviteQueryPaginationResp().decode(tlv);
		case CommandTag.GAME_PLAYER_PLAYING_QUERY_PAGINATION_REQUEST:
			return new PlayerPlayingQueryPaginationReq().decode(tlv);
		case CommandTag.GAME_PLAYER_PLAYING_QUERY_PAGINATION_RESPONSE:
			return new PlayerPlayingQueryPaginationResp().decode(tlv);
		case CommandTag.GAME_RESIGN_REQUEST:
			return new GameResignReq().decode(tlv);
		case CommandTag.GAME_RESIGN_RESPONSE:
			return new GameResignResp().decode(tlv);
		case CommandTag.GAME_RESIGN_NOTIFY:
			return new GameResignNotify().decode(tlv);
		case CommandTag.GAME_SYNC_STEP_REQUEST:
			return new GameSyncStepReq().decode(tlv);
		case CommandTag.GAME_SYNC_STEP_RESPONSE:
			return new GameSyncStepResp().decode(tlv);

		case CommandTag.MESSAGE_PING_REQUEST:
			return new MsgPingReq().decode(tlv);
		case CommandTag.MESSAGE_PANG_RESPONSE:
			return new MsgPangResp().decode(tlv);
		case CommandTag.MESSAGE_PONG_RESPONSE:
			return new MsgPongNotify().decode(tlv);
		case CommandTag.QUERY_ONLINE_NUMBER_REQUEST:
			return new QueryOnlineNumReq().decode(tlv);
		case CommandTag.QUERY_ONLINE_NUMBER_RESPONSE:
			return new QueryOnlineNumResp().decode(tlv);

		default:
			throw new UnsupportedEncodingException("Unknown command=[" + tlv.getTag() + "]");
		}
	}

}
