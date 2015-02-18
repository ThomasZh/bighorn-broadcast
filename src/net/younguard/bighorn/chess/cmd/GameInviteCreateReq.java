package net.younguard.bighorn.chess.cmd;

import java.io.UnsupportedEncodingException;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.RequestCommand;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameInviteCreateReq
		extends RequestCommand
{
	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;
		TlvObject tSequence = new TlvObject(i++, TlvByteUtil.INTEGER_LENGTH, TlvByteUtil.int2Byte(this.getSequence()));
		TlvObject tColor = new TlvObject(i++, TlvByteUtil.SHORT_LENGTH, TlvByteUtil.short2Byte(color));
		TlvObject tTimeRule = new TlvObject(i++, TlvByteUtil.SHORT_LENGTH, TlvByteUtil.short2Byte(timeRule));

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);
		tlv.add(tColor);
		tlv.add(tTimeRule);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");
		return tlv;
	}

	@Override
	public GameInviteCreateReq decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		this.setTag(tlv.getTag());

		int childCount = 3;
		TlvParser.decodeChildren(tlv, childCount);
		logger.debug("from tlv:(tag=" + this.getTag() + ", child=" + childCount + ") to command");

		int i = 0;
		TlvObject tSequence = tlv.getChild(i++);
		this.setSequence(TlvByteUtil.byte2Int(tSequence.getValue()));
		logger.debug("sequence: " + this.getSequence());

		TlvObject tColor = tlv.getChild(i++);
		color = TlvByteUtil.byte2Short(tColor.getValue());
		logger.debug("color: " + color);

		TlvObject tTimeRule = tlv.getChild(i++);
		timeRule = TlvByteUtil.byte2Short(tTimeRule.getValue());
		logger.debug("timeRule: " + timeRule);

		return this;
	}

	// //////////////////////////////////////////////////////

	public GameInviteCreateReq()
	{
		this.setTag(CommandTag.GAME_INVITE_CREATE_REQUEST);
	}

	public GameInviteCreateReq(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	public GameInviteCreateReq(int sequence, short color, short timeRule)
	{
		this(sequence);

		this.setColor(color);
		this.setTimeRule(timeRule);
	}

	private short color;
	private short timeRule;

	public short getColor()
	{
		return color;
	}

	public void setColor(short color)
	{
		this.color = color;
	}

	public short getTimeRule()
	{
		return timeRule;
	}

	public void setTimeRule(short timeRule)
	{
		this.timeRule = timeRule;
	}

	private final static Logger logger = LoggerFactory.getLogger(GameInviteCreateReq.class);

}
