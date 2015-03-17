package net.younguard.bighorn.domain;

public class MyGameMasterInfo
		extends GameMasterInfo
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5231390628359877970L;

	private short badgeNum;

	public short getBadgeNum()
	{
		return badgeNum;
	}

	public void setBadgeNum(short badgeNum)
	{
		this.badgeNum = badgeNum;
	}

}
