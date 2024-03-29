package net.younguard.bighorn.chess.cmd;

import java.io.UnsupportedEncodingException;
import java.util.List;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.QueryPaginationResp;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;
import net.younguard.bighorn.domain.PlayerMasterInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PlayersQueryPaginationResp
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
		String jsonPlayers = gson.toJson(players);
		TlvObject tPlayers = new TlvObject(i++, jsonPlayers);

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);
		tlv.add(tRespState);
		tlv.add(tPlayers);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");

		return tlv;
	}

	@Override
	public PlayersQueryPaginationResp decode(TlvObject tlv)
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

		TlvObject tPlayers = tlv.getChild(i++);
		String jsonPlayers = new String(tPlayers.getValue(), "UTF-8");
		logger.debug("jsonGames: " + jsonPlayers);
		Gson gson = new Gson();
		players = gson.fromJson(jsonPlayers, new TypeToken<List<PlayerMasterInfo>>()
		{
		}.getType());

		return this;
	}

	// //////////////////////////////////////////////////////

	public PlayersQueryPaginationResp()
	{
		this.setTag(CommandTag.GAME_PLAYERS_QUERY_PAGINATION_RESPONSE);
	}

	public PlayersQueryPaginationResp(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	public PlayersQueryPaginationResp(int sequence, short state)
	{
		this(sequence);

		this.setRespState(state);
	}

	public PlayersQueryPaginationResp(int sequence, short state, List<PlayerMasterInfo> players)
	{
		this(sequence);

		this.setRespState(state);
		this.setPlayers(players);
	}

	private List<PlayerMasterInfo> players;

	public List<PlayerMasterInfo> getPlayers()
	{
		return players;
	}

	public void setPlayers(List<PlayerMasterInfo> players)
	{
		this.players = players;
	}

	private final static Logger logger = LoggerFactory.getLogger(GameHistoryQueryPaginationResp.class);
}
