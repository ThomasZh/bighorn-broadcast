package net.younguard.bighorn.domain;

import java.util.List;

import com.google.gson.Gson;

public class GameMasterInfo
		extends GameBaseInfo
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8314628217170723932L;
	private List<GameMemberMasterInfo> players;

	public GameMasterInfo()
	{
	}

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

	public List<GameMemberMasterInfo> getPlayers()
	{
		return players;
	}

	public void setPlayers(List<GameMemberMasterInfo> players)
	{
		this.players = players;
	}

}
