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
 * someone client send a request message to server, server broadcast this
 * message to everybody.
 * 
 * Copyright 2014-2015 by Young Guard Salon Community, China. All rights
 * reserved. http://www.younguard.net
 * 
 * NOTICE ! You can copy or redistribute this code freely, but you should not
 * remove the information about the copyright notice and the author.
 * 
 * @author ThomasZhang, thomas.zh@qq.com
 */
public class MsgPongNotify
		extends ResponseCommand
{
	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;
		TlvObject tSequence = new TlvObject(i++, TlvByteUtil.INTEGER_LENGTH, TlvByteUtil.int2Byte(this.getSequence()));
		TlvObject tUsername = new TlvObject(i++, username);
		TlvObject tContent = new TlvObject(i++, content);

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);
		tlv.add(tUsername);
		tlv.add(tContent);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");
		return tlv;
	}

	@Override
	public MsgPongNotify decode(TlvObject tlv)
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

		TlvObject tUsername = tlv.getChild(i++);
		username = new String(tUsername.getValue(), "UTF-8");
		logger.debug("username: " + username);

		TlvObject tContent = tlv.getChild(i++);
		content = new String(tContent.getValue(), "UTF-8");
		logger.debug("content: " + content);

		return this;
	}

	// //////////////////////////////////////////////////////

	public MsgPongNotify()
	{
		this.setTag(CommandTag.MESSAGE_PONG_RESPONSE);
	}

	public MsgPongNotify(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	public MsgPongNotify(int sequence, String username, String content)
	{
		this(sequence);

		this.setUsername(username);
		this.setContent(content);
	}

	private String username;
	private String content;

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	private final static Logger logger = LoggerFactory.getLogger(MsgPongNotify.class);

}
