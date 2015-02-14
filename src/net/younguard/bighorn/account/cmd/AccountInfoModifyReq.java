package net.younguard.bighorn.account.cmd;

import java.io.UnsupportedEncodingException;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.RequestCommand;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountInfoModifyReq
		extends RequestCommand
{
	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;
		TlvObject tSequence = new TlvObject(i++, TlvByteUtil.int2Byte(this.getSequence()));
		TlvObject tDeviceId = new TlvObject(i++, deviceId);
		TlvObject tAccountId = new TlvObject(i++, accountId);
		TlvObject tNickname = new TlvObject(i++, nickname);
		TlvObject tAvatarUrl = new TlvObject(i++, avatarUrl);

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);
		tlv.add(tDeviceId);
		tlv.add(tAccountId);
		tlv.add(tNickname);
		tlv.add(tAvatarUrl);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");
		return tlv;
	}

	@Override
	public AccountInfoModifyReq decode(TlvObject tlv)
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

		TlvObject tDeviceId = tlv.getChild(i++);
		deviceId = new String(tDeviceId.getValue(), "UTF-8");
		logger.debug("deviceId: " + deviceId);

		TlvObject tAccountId = tlv.getChild(i++);
		accountId = new String(tAccountId.getValue(), "UTF-8");
		logger.debug("accountId: " + accountId);

		TlvObject tNickname = tlv.getChild(i++);
		nickname = new String(tNickname.getValue(), "UTF-8");
		logger.debug("nickname: " + nickname);

		TlvObject tAvatarUrl = tlv.getChild(i++);
		avatarUrl = new String(tAvatarUrl.getValue(), "UTF-8");
		logger.debug("avatarUrl: " + avatarUrl);

		return this;
	}

	// //////////////////////////////////////////////////////

	public AccountInfoModifyReq()
	{
		this.setTag(CommandTag.ACCOUNT_INFO_MODIFY_REQUEST);
	}

	public AccountInfoModifyReq(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	public AccountInfoModifyReq(int sequence, String deviceId, String accountId, String nickname, String avatarUrl)
	{
		this(sequence);

		this.setDeviceId(deviceId);
		this.setAccountId(accountId);
		this.setNickname(nickname);
		this.setAvatarUrl(avatarUrl);
	}

	private String deviceId;
	private String accountId;
	private String nickname;
	private String avatarUrl;

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

	public String getAvatarUrl()
	{
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl)
	{
		this.avatarUrl = avatarUrl;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getDeviceId()
	{
		return deviceId;
	}

	public void setDeviceId(String deviceId)
	{
		this.deviceId = deviceId;
	}

	private final static Logger logger = LoggerFactory.getLogger(AccountInfoModifyReq.class);

}
