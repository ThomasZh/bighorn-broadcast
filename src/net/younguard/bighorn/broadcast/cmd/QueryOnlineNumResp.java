package net.younguard.bighorn.broadcast.cmd;

import java.io.UnsupportedEncodingException;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.ResponseCommand;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * this the the message response from server for query online device number
 * request.
 * 
 * Copyright 2014-2015 by Young Guard Salon Community, China. All rights
 * reserved. http://www.younguard.net
 * 
 * NOTICE ! You can copy or redistribute this code freely, but you should not
 * remove the information about the copyright notice and the author.
 * 
 * @author ThomasZhang, thomas.zh@qq.com
 */
public class QueryOnlineNumResp
		extends ResponseCommand
{
	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;
		TlvObject tSequence = new TlvObject(i++, TlvByteUtil.int2Byte(this.getSequence()));
		TlvObject tRespState = new TlvObject(i++, TlvByteUtil.short2Byte(this.getRespState()));
		TlvObject tNum = new TlvObject(i++, TlvByteUtil.int2Byte(this.getNum()));

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);
		tlv.add(tRespState);
		tlv.add(tNum);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");
		return tlv;
	}

	@Override
	public QueryOnlineNumResp decode(TlvObject tlv)
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

		TlvObject tRespState = tlv.getChild(i++);
		this.setRespState(TlvByteUtil.byte2Short(tRespState.getValue()));
		logger.debug("respState: " + this.getRespState());

		TlvObject tNum = tlv.getChild(i++);
		this.setNum(TlvByteUtil.byte2Int(tNum.getValue()));
		logger.debug("num: " + this.getNum());

		return this;
	}

	// //////////////////////////////////////////////////////

	public QueryOnlineNumResp()
	{
		this.setTag(CommandTag.QUERY_ONLINE_NUMBER_RESPONSE);
	}

	public QueryOnlineNumResp(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	public QueryOnlineNumResp(int sequence, short state)
	{
		this(sequence);

		this.setRespState(state);
	}

	public QueryOnlineNumResp(int sequence, short state, int num)
	{
		this(sequence, state);

		this.setNum(num);
	}

	private int num = 0;

	public int getNum()
	{
		return num;
	}

	public void setNum(int num)
	{
		this.num = num;
	}

	private final static Logger logger = LoggerFactory.getLogger(QueryOnlineNumResp.class);

}
