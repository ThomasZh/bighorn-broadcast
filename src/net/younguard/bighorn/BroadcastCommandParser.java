package net.younguard.bighorn;

import java.io.UnsupportedEncodingException;

import net.younguard.bighorn.account.cmd.RegisterDeviceNotifyTokenReq;
import net.younguard.bighorn.account.cmd.RegisterDeviceNotifyTokenResp;
import net.younguard.bighorn.broadcast.cmd.MsgPangResp;
import net.younguard.bighorn.broadcast.cmd.MsgPingReq;
import net.younguard.bighorn.broadcast.cmd.MsgPongNotify;
import net.younguard.bighorn.broadcast.cmd.QueryOnlineNumReq;
import net.younguard.bighorn.broadcast.cmd.QueryOnlineNumResp;
import net.younguard.bighorn.comm.Command;
import net.younguard.bighorn.comm.CommandParser;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.session.cmd.SocketCloseReq;

/**
 * command parser used by client.
 * 
 * Copyright 2014-2015 by Young Guard Salon Community, China. All rights reserved.
 * http://www.younguard.net
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
		case CommandTag.REGISTER_NOTIFY_TOKEN_REQUEST:
			return new RegisterDeviceNotifyTokenReq().decode(tlv);
		case CommandTag.REGISTER_NOTIFY_TOKEN_RESPONSE:
			return new RegisterDeviceNotifyTokenResp().decode(tlv);
		case CommandTag.SOCKET_CLOSE_REQUEST:
			return new SocketCloseReq().decode(tlv);
		default:
			throw new UnsupportedEncodingException("Unknown command=[" + tlv.getTag() + "]");
		}
	}

}