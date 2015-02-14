package net.younguard.bighorn.account.cmd;

import java.io.UnsupportedEncodingException;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.ResponseCommand;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeviceLoginResp
		extends ResponseCommand
{
	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;
		TlvObject tSequence = new TlvObject(i++, TlvByteUtil.int2Byte(this.getSequence()));
		TlvObject tRespState = new TlvObject(i++, TlvByteUtil.short2Byte(this.getRespState()));
		TlvObject tSessionTicket = new TlvObject(i++, sessionTicket);

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);
		tlv.add(tRespState);
		tlv.add(tSessionTicket);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");
		return tlv;
	}

	@Override
	public DeviceLoginResp decode(TlvObject tlv)
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

		TlvObject tSessionTicket = tlv.getChild(i++);
		sessionTicket = new String(tSessionTicket.getValue(), "UTF-8");
		logger.debug("sessionTicket: " + sessionTicket);

		return this;
	}

	// //////////////////////////////////////////////////////

	public DeviceLoginResp()
	{
		this.setTag(CommandTag.DEVICE_LOGIN_RESPONSE);
	}

	public DeviceLoginResp(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	public DeviceLoginResp(int sequence, short state)
	{
		this(sequence);

		this.setRespState(state);
	}

	public DeviceLoginResp(int sequence, short state, String sessionTicket)
	{
		this(sequence, state);

		this.setSessionTicket(sessionTicket);
	}

	private String sessionTicket;

	public String getSessionTicket()
	{
		return sessionTicket;
	}

	public void setSessionTicket(String sessionTicket)
	{
		this.sessionTicket = sessionTicket;
	}

	private final static Logger logger = LoggerFactory.getLogger(DeviceLoginResp.class);

}
