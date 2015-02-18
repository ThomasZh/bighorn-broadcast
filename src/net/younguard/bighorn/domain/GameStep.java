package net.younguard.bighorn.domain;

import com.google.gson.Gson;

public class GameStep
		extends JsonBeanAdapter
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6329042144740644739L;
	private short step;
	/**
	 * red=140,black=141
	 */
	private short color;
	private short x;
	private short y;
	private String accountId;

	public GameStep()
	{
	}

	public static GameStep decode(String json)
	{
		Gson gson = new Gson();
		GameStep info = gson.fromJson(json, GameStep.class);
		return info;
	}

	public GameStep(short step, short color, short x, short y)
	{
		this.setStep(step);
		this.setColor(color);
		this.setX(x);
		this.setY(y);
	}

	@Override
	public String encode()
	{
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	public short getColor()
	{
		return color;
	}

	public void setColor(short color)
	{
		this.color = color;
	}

	public short getStep()
	{
		return step;
	}

	public void setStep(short step)
	{
		this.step = step;
	}

	public short getX()
	{
		return x;
	}

	public void setX(short x)
	{
		this.x = x;
	}

	public short getY()
	{
		return y;
	}

	public void setY(short y)
	{
		this.y = y;
	}

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

}
