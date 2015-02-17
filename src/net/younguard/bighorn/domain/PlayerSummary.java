package net.younguard.bighorn.domain;

import com.google.gson.Gson;

public class PlayerSummary
		extends AccountBaseInfo
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5953813171873887076L;
	private short inviteNum;
	private short playingNum;
	private short completedNum;

	public PlayerSummary()
	{
	}

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

	public short getCompletedNum()
	{
		return completedNum;
	}

	public void setCompletedNum(short playedNum)
	{
		this.completedNum = playedNum;
	}

}
