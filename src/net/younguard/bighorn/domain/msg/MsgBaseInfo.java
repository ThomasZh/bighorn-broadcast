package net.younguard.bighorn.domain.msg;

import net.younguard.bighorn.domain.JsonBeanAdapter;

public class MsgBaseInfo
		extends JsonBeanAdapter
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8626500261075553037L;

	private String id;
	private int timestamp;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public int getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(int timestamp)
	{
		this.timestamp = timestamp;
	}

}
