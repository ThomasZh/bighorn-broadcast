package net.younguard.bighorn.domain;

import com.google.gson.Gson;

public class GameInviteInfo
		extends JsonBeanAdapter
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4990176984708878083L;
	private String gameId;
	private short timeRule;
	private int createTime;
	private String playerId;
	private short playerColor;

	@Override
	public String encode()
	{
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	public static GameInviteInfo decode(String json)
	{
		Gson gson = new Gson();
		GameInviteInfo info = gson.fromJson(json, GameInviteInfo.class);
		return info;
	}

	public String getGameId()
	{
		return gameId;
	}

	public void setGameId(String gameId)
	{
		this.gameId = gameId;
	}

	public short getTimeRule()
	{
		return timeRule;
	}

	public void setTimeRule(short timeRule)
	{
		this.timeRule = timeRule;
	}

	public int getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(int createTime)
	{
		this.createTime = createTime;
	}

	public String getPlayerId()
	{
		return playerId;
	}

	public void setPlayerId(String playerId)
	{
		this.playerId = playerId;
	}

	public short getPlayerColor()
	{
		return playerColor;
	}

	public void setPlayerColor(short playerColor)
	{
		this.playerColor = playerColor;
	}

}
