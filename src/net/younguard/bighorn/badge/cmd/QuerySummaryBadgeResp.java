package net.younguard.bighorn.badge.cmd;

import java.io.UnsupportedEncodingException;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.ResponseCommand;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuerySummaryBadgeResp
		extends ResponseCommand
{
	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;
		TlvObject tSequence = new TlvObject(i++, TlvByteUtil.INTEGER_LENGTH, TlvByteUtil.int2Byte(this.getSequence()));
		TlvObject tRespState = new TlvObject(i++, TlvByteUtil.SHORT_LENGTH, TlvByteUtil.short2Byte(this.getRespState()));
		TlvObject tInviteNum = new TlvObject(i++, TlvByteUtil.SHORT_LENGTH, TlvByteUtil.short2Byte(inviteNum));
		TlvObject tPlayingNum = new TlvObject(i++, TlvByteUtil.SHORT_LENGTH, TlvByteUtil.short2Byte(playingNum));
		TlvObject tHistoryNum = new TlvObject(i++, TlvByteUtil.SHORT_LENGTH, TlvByteUtil.short2Byte(historyNum));

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);
		tlv.add(tRespState);
		tlv.add(tInviteNum);
		tlv.add(tPlayingNum);
		tlv.add(tHistoryNum);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");
		return tlv;
	}

	@Override
	public QuerySummaryBadgeResp decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		this.setTag(tlv.getTag());

		int childCount = 5;
		TlvParser.decodeChildren(tlv, childCount);
		logger.debug("from tlv:(tag=" + this.getTag() + ", child=" + childCount + ") to command");

		int i = 0;
		TlvObject tSequence = tlv.getChild(i++);
		this.setSequence(TlvByteUtil.byte2Int(tSequence.getValue()));
		logger.debug("sequence: " + this.getSequence());

		TlvObject tRespState = tlv.getChild(i++);
		this.setRespState(TlvByteUtil.byte2Short(tRespState.getValue()));
		logger.debug("respState: " + this.getRespState());

		TlvObject tInviteNum = tlv.getChild(i++);
		inviteNum = TlvByteUtil.byte2Short(tInviteNum.getValue());
		logger.debug("inviteNum: " + inviteNum);

		TlvObject tPlayingNum = tlv.getChild(i++);
		playingNum = TlvByteUtil.byte2Short(tPlayingNum.getValue());
		logger.debug("playingNum: " + playingNum);

		TlvObject tHistoryNum = tlv.getChild(i++);
		historyNum = TlvByteUtil.byte2Short(tHistoryNum.getValue());
		logger.debug("historyNum: " + historyNum);

		return this;
	}

	// //////////////////////////////////////////////////////

	public QuerySummaryBadgeResp()
	{
		this.setTag(CommandTag.QUERY_SUMMSRY_BADGE_NUMBER_RESPONSE);
	}

	public QuerySummaryBadgeResp(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	public QuerySummaryBadgeResp(int sequence, short state)
	{
		this(sequence);

		this.setRespState(state);
	}

	public QuerySummaryBadgeResp(int sequence, short state, short inviteNum, short playingNum, short historyNum)
	{
		this(sequence, state);

		this.setInviteNum(inviteNum);
		this.setPlayingNum(playingNum);
		this.setHistoryNum(historyNum);
	}

	private short inviteNum;
	private short playingNum;
	private short historyNum;

	public short getInviteNum()
	{
		return inviteNum;
	}

	public void setInviteNum(short inviteNum)
	{
		this.inviteNum = inviteNum;
	}

	public short getPlayingNum()
	{
		return playingNum;
	}

	public void setPlayingNum(short playingNum)
	{
		this.playingNum = playingNum;
	}

	public short getHistoryNum()
	{
		return historyNum;
	}

	public void setHistoryNum(short historyNum)
	{
		this.historyNum = historyNum;
	}

	private final static Logger logger = LoggerFactory.getLogger(QuerySummaryBadgeResp.class);

}
