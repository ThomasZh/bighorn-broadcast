package net.younguard.bighorn.broadcast.cmd;

import java.io.UnsupportedEncodingException;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.ResponseCommand;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BadgeNotify
		extends ResponseCommand
{
	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;
		TlvObject tNotifyType = new TlvObject(i++, TlvByteUtil.SHORT_LENGTH, TlvByteUtil.short2Byte(notifyType));
		TlvObject tChannelType = new TlvObject(i++, TlvByteUtil.SHORT_LENGTH, TlvByteUtil.short2Byte(channelType));
		TlvObject tChannelId = new TlvObject(i++, channelId);

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tNotifyType);
		tlv.add(tChannelType);
		tlv.add(tChannelId);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");
		return tlv;
	}

	@Override
	public BadgeNotify decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		this.setTag(tlv.getTag());

		int childCount = 3;
		TlvParser.decodeChildren(tlv, childCount);
		logger.debug("from tlv:(tag=" + this.getTag() + ", child=" + childCount + ") to command");

		int i = 0;

		TlvObject tNotifyType = tlv.getChild(i++);
		notifyType = TlvByteUtil.byte2Short(tNotifyType.getValue());
		logger.debug("notifyType: " + notifyType);

		TlvObject tChannelType = tlv.getChild(i++);
		channelType = TlvByteUtil.byte2Short(tChannelType.getValue());
		logger.debug("channelType: " + channelType);

		TlvObject tChannelId = tlv.getChild(i++);
		channelId = new String(tChannelId.getValue(), "UTF-8");
		logger.debug("channelId: " + channelId);

		return this;
	}

	// //////////////////////////////////////////////////////

	public BadgeNotify()
	{
		this.setTag(CommandTag.BADGE_NOTIFY);
	}

	public BadgeNotify(short notifyType, short channelType, String channelId)
	{
		this();

		this.setNotifyType(notifyType);
		this.setChannelType(channelType);
		this.setChannelId(channelId);
	}

	/**
	 * clubChatMsg,gameChat,gameDraw,gameDrawFeedback,gameInvite,gameInviteFeedback,gameJoin,gamePlayStep,gameResign
	 */
	private short notifyType;
	/**
	 * invite,playing,history
	 */
	private short channelType;
	private String channelId;

	public short getNotifyType()
	{
		return notifyType;
	}

	public void setNotifyType(short notifyType)
	{
		this.notifyType = notifyType;
	}

	public short getChannelType()
	{
		return channelType;
	}

	public void setChannelType(short channelType)
	{
		this.channelType = channelType;
	}

	public String getChannelId()
	{
		return channelId;
	}

	public void setChannelId(String channelId)
	{
		this.channelId = channelId;
	}

	private final static Logger logger = LoggerFactory.getLogger(BadgeNotify.class);

}
