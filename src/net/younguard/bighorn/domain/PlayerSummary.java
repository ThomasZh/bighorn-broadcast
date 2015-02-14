package net.younguard.bighorn.domain;

import com.google.gson.Gson;

public class PlayerSummary
		extends JsonBeanAdapter
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5953813171873887076L;
	private String accountId;
	private String nickname;
	private String avatarUrl;
	private short inviteNum;
	private short playingNum;
	private short playedNum;

	@Override
	public String encode()
	{
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	public static PlayerSummary decode(String json)
	{
		Gson gson = new Gson();
		PlayerSummary info = gson.fromJson(json, PlayerSummary.class);
		return info;
	}

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
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

}
