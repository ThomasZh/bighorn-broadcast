package net.younguard.bighorn.domain;

import com.google.gson.Gson;

public class AccountBaseInfo
		extends JsonBeanAdapter
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6584206417937297198L;
	private String accountId;
	private String nickname;
	private String avatarUrl;

	public AccountBaseInfo()
	{
	}

	public AccountBaseInfo(String accountId, String nickname, String avatarUrl)
	{
		this.setAccountId(accountId);
		this.setNickname(nickname);
		this.setAvatarUrl(avatarUrl);
	}

	@Override
	public String encode()
	{
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	public static AccountBaseInfo decode(String json)
	{
		Gson gson = new Gson();
		AccountBaseInfo info = gson.fromJson(json, AccountBaseInfo.class);
		return info;
	}

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getAvatarUrl()
	{
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl)
	{
		this.avatarUrl = avatarUrl;
	}

}
