package net.younguard.bighorn.chess.cmd;

import java.io.UnsupportedEncodingException;
import java.util.List;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.QueryPaginationResp;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;
import net.younguard.bighorn.domain.GameMasterInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PlayerInviteQueryPaginationResp
		extends QueryPaginationResp
{
	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;
		TlvObject tSequence = new TlvObject(i++, TlvByteUtil.INTEGER_LENGTH, TlvByteUtil.int2Byte(this.getSequence()));
		TlvObject tRespState = new TlvObject(i++, TlvByteUtil.SHORT_LENGTH, TlvByteUtil.short2Byte(this.getRespState()));
		Gson gson = new Gson();
		String jsonInvites = gson.toJson(invites);
		TlvObject tInvites = new TlvObject(i++, jsonInvites);

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);
		tlv.add(tRespState);
		tlv.add(tInvites);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");

		return tlv;
	}

	@Override
	public PlayerInviteQueryPaginationResp decode(TlvObject tlv)
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

		TlvObject tInvites = tlv.getChild(i++);
		String jsonInvites = new String(tInvites.getValue(), "UTF-8");
		logger.debug("jsonInvites: " + jsonInvites);
		Gson gson = new Gson();
		invites = gson.fromJson(jsonInvites, new TypeToken<List<GameMasterInfo>>()
		{
		}.getType());

		return this;
	}

	// //////////////////////////////////////////////////////

	public PlayerInviteQueryPaginationResp()
	{
		this.setTag(CommandTag.GAME_PLAYER_INVITE_QUERY_PAGINATION_RESPONSE);
	}

	public PlayerInviteQueryPaginationResp(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	public PlayerInviteQueryPaginationResp(int sequence, short state)
	{
		this(sequence);

		this.setRespState(state);
	}

	public PlayerInviteQueryPaginationResp(int sequence, short state, List<GameMasterInfo> invites)
	{
		this(sequence);

		this.setRespState(state);
		this.setInvites(invites);
	}

	private List<GameMasterInfo> invites;

	public List<GameMasterInfo> getInvites()
	{
		return invites;
	}

	public void setInvites(List<GameMasterInfo> invites)
	{
		this.invites = invites;
	}

	private final static Logger logger = LoggerFactory.getLogger(PlayerInviteQueryPaginationResp.class);
}
