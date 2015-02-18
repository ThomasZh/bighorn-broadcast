package net.younguard.bighorn.chess.cmd;

import java.io.UnsupportedEncodingException;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.RequestCommand;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerSummaryQueryReq
		extends RequestCommand
{
	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;
		TlvObject tSequence = new TlvObject(i++, TlvByteUtil.INTEGER_LENGTH, TlvByteUtil.int2Byte(this.getSequence()));
		TlvObject tPlayerId = new TlvObject(i++, playerId);

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);
		tlv.add(tPlayerId);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");
		return tlv;
	}

	@Override
	public PlayerSummaryQueryReq decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		this.setTag(tlv.getTag());

		int childCount = 2;
		TlvParser.decodeChildren(tlv, childCount);
		logger.debug("from tlv:(tag=" + this.getTag() + ", child=" + childCount + ") to command");

		int i = 0;
		TlvObject tSequence = tlv.getChild(i++);
		this.setSequence(TlvByteUtil.byte2Int(tSequence.getValue()));
		logger.debug("sequence: " + this.getSequence());

		TlvObject tPlayerId = tlv.getChild(i++);
		playerId = new String(tPlayerId.getValue(), "UTF-8");
		logger.debug("gameId: " + playerId);

		return this;
	}

	// //////////////////////////////////////////////////////

	public PlayerSummaryQueryReq()
	{
		this.setTag(CommandTag.GAME_PLAYER_SUMMARY_QUERY_REQUEST);
	}

	public PlayerSummaryQueryReq(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	public PlayerSummaryQueryReq(int sequence, String playerId)
	{
		this(sequence);

		this.setPlayerId(playerId);
	}

	private String playerId;

	public String getPlayerId()
	{
		return playerId;
	}

	public void setPlayerId(String playerId)
	{
		this.playerId = playerId;
	}

	private final static Logger logger = LoggerFactory.getLogger(PlayerSummaryQueryReq.class);

}
