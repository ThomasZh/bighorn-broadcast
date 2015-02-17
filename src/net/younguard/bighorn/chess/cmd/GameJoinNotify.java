package net.younguard.bighorn.chess.cmd;

import java.io.UnsupportedEncodingException;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.ResponseCommand;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameJoinNotify
		extends ResponseCommand
{
	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;
		TlvObject tSequence = new TlvObject(i++, TlvByteUtil.int2Byte(this.getSequence()));
		TlvObject tGameId = new TlvObject(i++, gameId);
		TlvObject tPlayerId = new TlvObject(i++, playerId);
		TlvObject tColor = new TlvObject(i++, TlvByteUtil.short2Byte(color));

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);
		tlv.add(tGameId);
		tlv.add(tPlayerId);
		tlv.add(tColor);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");
		return tlv;
	}

	@Override
	public GameJoinNotify decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		this.setTag(tlv.getTag());

		int childCount = 4;
		TlvParser.decodeChildren(tlv, childCount);
		logger.debug("from tlv:(tag=" + this.getTag() + ", child=" + childCount + ") to command");

		int i = 0;
		TlvObject tSequence = tlv.getChild(i++);
		this.setSequence(TlvByteUtil.byte2Int(tSequence.getValue()));
		logger.debug("sequence: " + this.getSequence());

		TlvObject tGameId = tlv.getChild(i++);
		gameId = new String(tGameId.getValue(), "UTF-8");
		logger.debug("gameId: " + gameId);

		TlvObject tPlayerId = tlv.getChild(i++);
		playerId = new String(tPlayerId.getValue(), "UTF-8");
		logger.debug("playerId: " + playerId);

		TlvObject tColor = tlv.getChild(i++);
		color = TlvByteUtil.byte2Short(tColor.getValue());
		logger.debug("color: " + color);

		return this;
	}

	// //////////////////////////////////////////////////////

	public GameJoinNotify()
	{
		this.setTag(CommandTag.GAME_JOIN_NOTIFY);
	}

	public GameJoinNotify(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	public GameJoinNotify(int sequence, String gameId, String playerId, short color)
	{
		this(sequence);

		this.setGameId(gameId);
		this.setPlayerId(playerId);
		this.setColor(color);
	}

	private String gameId;
	private String playerId;
	private short color;

	public String getGameId()
	{
		return gameId;
	}

	public void setGameId(String gameId)
	{
		this.gameId = gameId;
	}

	public short getColor()
	{
		return color;
	}

	public void setColor(short color)
	{
		this.color = color;
	}

	public String getPlayerId()
	{
		return playerId;
	}

	public void setPlayerId(String playerId)
	{
		this.playerId = playerId;
	}

	private final static Logger logger = LoggerFactory.getLogger(GameJoinNotify.class);

}
