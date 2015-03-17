package net.younguard.bighorn.domain.badge;

import net.younguard.bighorn.domain.JsonBeanAdapter;

public class ListBadgeNumber
		extends JsonBeanAdapter
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8241654224516156143L;

	/**
	 * gameId;
	 */
	private String id;
	private short num;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public short getNum()
	{
		return num;
	}

	public void setNum(short num)
	{
		this.num = num;
	}

}
