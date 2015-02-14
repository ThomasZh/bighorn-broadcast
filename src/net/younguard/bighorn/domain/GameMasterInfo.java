package net.younguard.bighorn.domain;

import com.google.gson.Gson;

public class GameMasterInfo
		extends JsonBeanAdapter
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8314628217170723932L;
	private String gameId;
	private short timeRule;
	private int createTime;
	private short lastStep;
	private int lastUpdateTime;
	private short state;
	private String winnerId;

	@Override
	public String encode()
	{
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	public static GameMasterInfo decode(String json)
	{
		Gson gson = new Gson();
		GameMasterInfo info = gson.fromJson(json, GameMasterInfo.class);
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

	public int getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(int createTime)
	{
		this.createTime = createTime;
	}

	public short getLastStep()
	{
		return lastStep;
	}

	public void setLastStep(short lastStep)
	{
		this.lastStep = lastStep;
	}

	public int getLastUpdateTime()
	{
		return lastUpdateTime;
	}

	public void setLastUpdateTime(int lastUpdateTime)
	{
		this.lastUpdateTime = lastUpdateTime;
	}

	public short getState()
	{
		return state;
	}

	public void setState(short state)
	{
		this.state = state;
	}

	public String getWinnerId()
	{
		return winnerId;
	}

	public void setWinnerId(String winnerId)
	{
		this.winnerId = winnerId;
	}

	public short getTimeRule()
	{
		return timeRule;
	}

	public void setTimeRule(short timeRule)
	{
		this.timeRule = timeRule;
	}

}
