package net.younguard.bighorn.chess.cmd;

import java.io.UnsupportedEncodingException;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.ResponseCommand;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameInviteCreateResp
		extends ResponseCommand
{
	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;
		TlvObject tSequence = new TlvObject(i++, TlvByteUtil.int2Byte(this.getSequence()));
		TlvObject tRespState = new TlvObject(i++, TlvByteUtil.short2Byte(this.getRespState()));
		TlvObject tGameId = new TlvObject(i++, gameId);

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);
		tlv.add(tRespState);
		tlv.add(tGameId);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");
		return tlv;
	}

	@Override
	public GameInviteCreateResp decode(TlvObject tlv)
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

		TlvObject tGameId = tlv.getChild(i++);
		gameId = new String(tGameId.getValue(), "UTF-8");
		logger.debug("gameId: " + gameId);

		return this;
	}

	// //////////////////////////////////////////////////////

	public GameInviteCreateResp()
	{
		this.setTag(CommandTag.GAME_INVITE_CREATE_RESPONSE);
	}

	public GameInviteCreateResp(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	public GameInviteCreateResp(int sequence, short state)
	{
		this(sequence);

		this.setRespState(state);
	}

	public GameInviteCreateResp(int sequence, short state, String gameId)
	{
		this(sequence, state);

		this.setGameId(gameId);
	}

	private String gameId;

	public String getGameId()
	{
		return gameId;
	}

	public void setGameId(String gameId)
	{
		this.gameId = gameId;
	}

	private final static Logger logger = LoggerFactory.getLogger(GameInviteCreateResp.class);

}
