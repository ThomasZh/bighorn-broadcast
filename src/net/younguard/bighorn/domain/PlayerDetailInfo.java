package net.younguard.bighorn.domain;

import com.google.gson.Gson;

public class PlayerDetailInfo
		extends PlayerMasterInfo
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5593199906490912466L;

	public PlayerDetailInfo()
	{
	}

	@Override
	public String encode()
	{
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	public static PlayerDetailInfo decode(String json)
	{
		Gson gson = new Gson();
		PlayerDetailInfo info = gson.fromJson(json, PlayerDetailInfo.class);
		return info;
	}
}
