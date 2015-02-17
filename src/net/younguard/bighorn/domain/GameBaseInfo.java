package net.younguard.bighorn.domain;

import com.google.gson.Gson;

public class GameBaseInfo
		extends JsonBeanAdapter
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 164527247313891082L;
	private String gameId;
	/**
	 * 1day_25steps=151
	 */
	private short timeRule;
	/**
	 * invite=120,playing=121,complete=122
	 */
	private short state;
	private short lastStep;
	private int createTime;
	private int lastUpdateTime;
	private String winnerId;

	public GameBaseInfo()
	{
	}

	@Override
	public String encode()
	{
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	public static GameBaseInfo decode(String json)
	{
		Gson gson = new Gson();
		GameBaseInfo info = gson.fromJson(json, GameBaseInfo.class);
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

	public short getState()
	{
		return state;
	}

	public void setState(short state)
	{
		this.state = state;
	}

	public short getLastStep()
	{
		return lastStep;
	}

	public void setLastStep(short lastStep)
	{
		this.lastStep = lastStep;
	}

	public int getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(int createTime)
	{
		this.createTime = createTime;
	}

	public int getLastUpdateTime()
	{
		return lastUpdateTime;
	}

	public void setLastUpdateTime(int lastUpdateTime)
	{
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getWinnerId()
	{
		return winnerId;
	}

	public void setWinnerId(String winnerId)
	{
		this.winnerId = winnerId;
	}

}
