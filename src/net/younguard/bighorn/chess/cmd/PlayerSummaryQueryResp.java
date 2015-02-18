package net.younguard.bighorn.chess.cmd;

import java.io.UnsupportedEncodingException;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.ResponseCommand;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;
import net.younguard.bighorn.domain.PlayerMasterInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerSummaryQueryResp
		extends ResponseCommand
{
	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;
		TlvObject tSequence = new TlvObject(i++, TlvByteUtil.INTEGER_LENGTH, TlvByteUtil.int2Byte(this.getSequence()));
		TlvObject tPlayerId = new TlvObject(i++, playerId);
		TlvObject tNickname = new TlvObject(i++, nickname);
		TlvObject tAvatarUrl = new TlvObject(i++, avatarUrl);
		TlvObject tInviteNum = new TlvObject(i++, TlvByteUtil.SHORT_LENGTH, TlvByteUtil.short2Byte(inviteNum));
		TlvObject tPlayingNum = new TlvObject(i++, TlvByteUtil.SHORT_LENGTH, TlvByteUtil.short2Byte(playingNum));
		TlvObject tPlayedNum = new TlvObject(i++, TlvByteUtil.SHORT_LENGTH, TlvByteUtil.short2Byte(playedNum));

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);
		tlv.add(tPlayerId);
		tlv.add(tNickname);
		tlv.add(tAvatarUrl);
		tlv.add(tInviteNum);
		tlv.add(tPlayingNum);
		tlv.add(tPlayedNum);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");
		return tlv;
	}

	@Override
	public PlayerSummaryQueryResp decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		this.setTag(tlv.getTag());

		int childCount = 7;
		TlvParser.decodeChildren(tlv, childCount);
		logger.debug("from tlv:(tag=" + this.getTag() + ", child=" + childCount + ") to command");

		int i = 0;
		TlvObject tSequence = tlv.getChild(i++);
		this.setSequence(TlvByteUtil.byte2Int(tSequence.getValue()));
		logger.debug("sequence: " + this.getSequence());

		TlvObject tPlayerId = tlv.getChild(i++);
		playerId = new String(tPlayerId.getValue(), "UTF-8");
		logger.debug("playerId: " + playerId);

		TlvObject tNickname = tlv.getChild(i++);
		nickname = new String(tNickname.getValue(), "UTF-8");
		logger.debug("nickname: " + nickname);

		TlvObject tAvatarUrl = tlv.getChild(i++);
		avatarUrl = new String(tAvatarUrl.getValue(), "UTF-8");
		logger.debug("avatarUrl: " + avatarUrl);

		TlvObject tInviteNum = tlv.getChild(i++);
		inviteNum = TlvByteUtil.byte2Short(tInviteNum.getValue());
		logger.debug("inviteNum: " + inviteNum);

		TlvObject tPlayingNum = tlv.getChild(i++);
		playingNum = TlvByteUtil.byte2Short(tPlayingNum.getValue());
		logger.debug("playingNum: " + playingNum);

		TlvObject tPlayedNum = tlv.getChild(i++);
		playedNum = TlvByteUtil.byte2Short(tPlayedNum.getValue());
		logger.debug("playedNum: " + playedNum);

		return this;
	}

	// //////////////////////////////////////////////////////

	public PlayerSummaryQueryResp()
	{
		this.setTag(CommandTag.GAME_PLAYER_SUMMARY_QUERY_RESPONSE);
	}

	public PlayerSummaryQueryResp(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	public PlayerSummaryQueryResp(int sequence, short respState)
	{
		this(sequence);

		this.setRespState(respState);
	}

	public PlayerSummaryQueryResp(int sequence, short respState, String playerId, String nickname, String avatarUrl,
			short inviteNum, short playingNum, short playedNum)
	{
		this(sequence, respState);

		this.setPlayerId(playerId);
		this.setNickname(nickname);
		this.setAvatarUrl(avatarUrl);
		this.setInviteNum(inviteNum);
		this.setPlayingNum(playingNum);
		this.setPlayedNum(playedNum);
	}

	public PlayerSummaryQueryResp(int sequence, short respState, PlayerMasterInfo player)
	{
		this(sequence, respState);

		this.setPlayerId(player.getAccountId());
		this.setNickname(player.getNickname());
		this.setAvatarUrl(player.getAvatarUrl());
		this.setInviteNum(player.getInviteNum());
		this.setPlayingNum(player.getPlayingNum());
		this.setPlayedNum(player.getCompletedNum());
	}

	private String playerId;
	private String nickname;
	private String avatarUrl;
	private short inviteNum;
	private short playingNum;
	private short playedNum;

	public String getPlayerId()
	{
		return playerId;
	}

	public void setPlayerId(String playerId)
	{
		this.playerId = playerId;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getAvatarUrl()
	{
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl)
	{
		this.avatarUrl = avatarUrl;
	}

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

	public short getPlayedNum()
	{
		return playedNum;
	}

	public void setPlayedNum(short playedNum)
	{
		this.playedNum = playedNum;
	}

	private final static Logger logger = LoggerFactory.getLogger(PlayerSummaryQueryResp.class);

}
