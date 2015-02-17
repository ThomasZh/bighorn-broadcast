package net.younguard.bighorn.domain;

import com.google.gson.Gson;

public class GameMemberMasterInfo
		extends AccountBaseInfo
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 265216537483229071L;
	/**
	 * playing=130,invited=131
	 */
	private short state;

	public GameMemberMasterInfo()
	{
	}

	@Override
	public String encode()
	{
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	public static GameMemberMasterInfo decode(String json)
	{
		Gson gson = new Gson();
		GameMemberMasterInfo member = gson.fromJson(json, GameMemberMasterInfo.class);
		return member;
	}

	public short getState()
	{
		return state;
	}

	public void setState(short state)
	{
		this.state = state;
	}
}
