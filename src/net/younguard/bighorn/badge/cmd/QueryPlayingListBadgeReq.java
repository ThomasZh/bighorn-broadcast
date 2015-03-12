package net.younguard.bighorn.badge.cmd;

import java.io.UnsupportedEncodingException;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.RequestCommand;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryPlayingListBadgeReq
		extends RequestCommand
{
	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;
		TlvObject tSequence = new TlvObject(i++, TlvByteUtil.INTEGER_LENGTH, TlvByteUtil.int2Byte(this.getSequence()));

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");
		return tlv;
	}

	@Override
	public QueryPlayingListBadgeReq decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		this.setTag(tlv.getTag());

		int childCount = 1;
		TlvParser.decodeChildren(tlv, childCount);
		logger.debug("from tlv:(tag=" + this.getTag() + ", child=" + childCount + ") to command");

		int i = 0;
		TlvObject tSequence = tlv.getChild(i++);
		this.setSequence(TlvByteUtil.byte2Int(tSequence.getValue()));
		logger.debug("sequence: " + this.getSequence());

		return this;
	}

	// //////////////////////////////////////////////////////

	public QueryPlayingListBadgeReq()
	{
		this.setTag(CommandTag.QUERY_PLAYING_LIST_BADGE_NUMBER_REQUEST);
	}

	public QueryPlayingListBadgeReq(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	private final static Logger logger = LoggerFactory.getLogger(QueryPlayingListBadgeReq.class);

}
