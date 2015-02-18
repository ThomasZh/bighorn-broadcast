package net.younguard.bighorn.account.cmd;

import java.io.UnsupportedEncodingException;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.RequestCommand;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * after connect socket, client send first package for server. put device
 * ID,notify token and nickname.
 * 
 * Copyright 2014-2015 by Young Guard Salon Community, China. All rights
 * reserved. http://www.younguard.net
 * 
 * NOTICE ! You can copy or redistribute this code freely, but you should not
 * remove the information about the copyright notice and the author.
 * 
 * @author ThomasZhang, thomas.zh@qq.com
 */
public class RegisterDeviceNotifyTokenReq
		extends RequestCommand
{
	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;
		TlvObject tSequence = new TlvObject(i++, TlvByteUtil.INTEGER_LENGTH, TlvByteUtil.int2Byte(this.getSequence()));
		TlvObject tDeviceId = new TlvObject(i++, deviceId);
		TlvObject tNotifyToken = new TlvObject(i++, notifyToken);
		TlvObject tOsVersion = new TlvObject(i++, osVersion);

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);
		tlv.add(tDeviceId);
		tlv.add(tNotifyToken);
		tlv.add(tOsVersion);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");
		return tlv;
	}

	@Override
	public RegisterDeviceNotifyTokenReq decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		this.setTag(tlv.getTag());

		int childCount = 4;
		TlvParser.decodeChildren(tlv, childCount);
		logger.debug("from tlv:(tag=" + this.getTag() + ", child=" + childCount + ") to command");

		int i = 0;
		TlvObject tSequence = tlv.getChild(i++);
		this.setSequence(TlvByteUtil.byte2Int(tSequence.getValue()));
		logger.debug("sequence: " + this.getSequence());

		TlvObject tDeviceId = tlv.getChild(i++);
		deviceId = new String(tDeviceId.getValue(), "UTF-8");
		logger.debug("deviceId: " + deviceId);

		TlvObject tNotifyToken = tlv.getChild(i++);
		notifyToken = new String(tNotifyToken.getValue(), "UTF-8");
		logger.debug("notifyToken: " + notifyToken);

		TlvObject tOsVersion = tlv.getChild(i++);
		osVersion = new String(tOsVersion.getValue(), "UTF-8");
		logger.debug("osVersion: " + osVersion);

		return this;
	}

	// //////////////////////////////////////////////////////

	public RegisterDeviceNotifyTokenReq()
	{
		this.setTag(CommandTag.REGISTER_NOTIFY_TOKEN_REQUEST);
	}

	public RegisterDeviceNotifyTokenReq(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	public RegisterDeviceNotifyTokenReq(int sequence, String deviceId, String notifyToken, String osVersion)
	{
		this(sequence);

		this.setDeviceId(deviceId);
		this.setNotifyToken(notifyToken);
		this.setOsVersion(osVersion);
	}

	private String deviceId;
	private String notifyToken;
	private String osVersion;

	public String getDeviceId()
	{
		return deviceId;
	}

	public void setDeviceId(String deviceId)
	{
		this.deviceId = deviceId;
	}

	public String getNotifyToken()
	{
		return notifyToken;
	}

	public void setNotifyToken(String notifyToken)
	{
		this.notifyToken = notifyToken;
	}

	public String getOsVersion()
	{
		return osVersion;
	}

	public void setOsVersion(String osVersion)
	{
		this.osVersion = osVersion;
	}

	private final static Logger logger = LoggerFactory.getLogger(RegisterDeviceNotifyTokenReq.class);

}
