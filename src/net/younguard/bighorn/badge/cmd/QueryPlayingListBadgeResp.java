package net.younguard.bighorn.badge.cmd;

import java.io.UnsupportedEncodingException;
import java.util.List;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.ResponseCommand;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;
import net.younguard.bighorn.domain.badge.ListBadgeNumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class QueryPlayingListBadgeResp
		extends ResponseCommand
{
	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;
		TlvObject tSequence = new TlvObject(i++, TlvByteUtil.INTEGER_LENGTH, TlvByteUtil.int2Byte(this.getSequence()));
		TlvObject tRespState = new TlvObject(i++, TlvByteUtil.SHORT_LENGTH, TlvByteUtil.short2Byte(this.getRespState()));
		Gson gson = new Gson();
		String json = gson.toJson(badges);
		TlvObject tBadges = new TlvObject(i++, json);

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);
		tlv.add(tRespState);
		tlv.add(tBadges);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");

		return tlv;
	}

	@Override
	public QueryPlayingListBadgeResp decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		this.setTag(tlv.getTag());

		int childCount = 3;
		TlvParser.decodeChildren(tlv, childCount);
		logger.debug("from tlv:(tag=" + this.getTag() + ", child=" + childCount + ") to command");

		int i = 0;
		TlvObject tSequence = tlv.getChild(i++);
		this.setSequence(TlvByteUtil.byte2Int(tSequence.getValue()));
		logger.debug("sequence: " + this.getSequence());

		TlvObject tRespState = tlv.getChild(i++);
		this.setRespState(TlvByteUtil.byte2Short(tRespState.getValue()));
		logger.debug("respState: " + this.getRespState());

		TlvObject tBadges = tlv.getChild(i++);
		String json = new String(tBadges.getValue(), "UTF-8");
		logger.debug("json: " + json);
		Gson gson = new Gson();
		badges = gson.fromJson(json, new TypeToken<List<ListBadgeNumber>>()
		{
		}.getType());

		return this;
	}

	// //////////////////////////////////////////////////////

	public QueryPlayingListBadgeResp()
	{
		this.setTag(CommandTag.QUERY_PLAYING_LIST_BADGE_NUMBER_RESPONSE);
	}

	public QueryPlayingListBadgeResp(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	public QueryPlayingListBadgeResp(int sequence, short state)
	{
		this(sequence);

		this.setRespState(state);
	}

	public QueryPlayingListBadgeResp(int sequence, short state, List<ListBadgeNumber> badges)
	{
		this(sequence, state);

		this.setBadges(badges);
	}

	private List<ListBadgeNumber> badges;

	public List<ListBadgeNumber> getBadges()
	{
		return badges;
	}

	public void setBadges(List<ListBadgeNumber> badges)
	{
		this.badges = badges;
	}

	private final static Logger logger = LoggerFactory.getLogger(QueryPlayingListBadgeResp.class);

}
