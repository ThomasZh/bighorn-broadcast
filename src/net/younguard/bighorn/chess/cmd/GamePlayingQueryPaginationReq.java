package net.younguard.bighorn.chess.cmd;

import java.io.UnsupportedEncodingException;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.QueryPaginationReq;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GamePlayingQueryPaginationReq
		extends QueryPaginationReq
{
	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;
		TlvObject tSequence = new TlvObject(i++, TlvByteUtil.int2Byte(this.getSequence()));
		TlvObject tPageNum = new TlvObject(i++, TlvByteUtil.short2Byte(this.getPageNum()));
		TlvObject tPageSize = new TlvObject(i++, TlvByteUtil.short2Byte(this.getPageSize()));

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);
		tlv.add(tPageNum);
		tlv.add(tPageSize);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");

		return tlv;
	}

	@Override
	public GamePlayingQueryPaginationReq decode(TlvObject tlv)
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

		TlvObject tPageNum = tlv.getChild(i++);
		this.setPageNum(TlvByteUtil.byte2Short(tPageNum.getValue()));
		logger.debug("pageNum: " + this.getPageNum());

		TlvObject tPageSize = tlv.getChild(i++);
		this.setPageSize(TlvByteUtil.byte2Short(tPageSize.getValue()));
		logger.debug("pageSize: " + this.getPageSize());

		return this;
	}

	// //////////////////////////////////////////////////////

	public GamePlayingQueryPaginationReq()
	{
		this.setTag(CommandTag.GAME_PLAYING_QUERY_PAGINATION_REQUEST);
	}

	public GamePlayingQueryPaginationReq(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	public GamePlayingQueryPaginationReq(int sequence, short pageNum, short pageSize)
	{
		this(sequence);

		this.setPageNum(pageNum);
		this.setPageSize(pageSize);
	}

	private final static Logger logger = LoggerFactory.getLogger(GamePlayingQueryPaginationReq.class);
}
