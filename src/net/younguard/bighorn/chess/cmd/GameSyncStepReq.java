package net.younguard.bighorn.chess.cmd;

import java.io.UnsupportedEncodingException;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.RequestCommand;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameSyncStepReq
		extends RequestCommand
{
	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;
		TlvObject tSequence = new TlvObject(i++, TlvByteUtil.int2Byte(this.getSequence()));
		TlvObject tGameId = new TlvObject(i++, gameId);
		TlvObject tLastStep = new TlvObject(i++, TlvByteUtil.short2Byte(lastStep));

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);
		tlv.add(tGameId);
		tlv.add(tLastStep);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");
		return tlv;
	}

	@Override
	public GameSyncStepReq decode(TlvObject tlv)
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

		TlvObject tGameId = tlv.getChild(i++);
		gameId = new String(tGameId.getValue(), "UTF-8");
		logger.debug("gameId: " + gameId);

		TlvObject tLastStep = tlv.getChild(i++);
		lastStep = TlvByteUtil.byte2Short(tLastStep.getValue());
		logger.debug("lastStep: " + lastStep);

		return this;
	}

	// //////////////////////////////////////////////////////

	public GameSyncStepReq()
	{
		this.setTag(CommandTag.GAME_SYNC_STEP_REQUEST);
	}

	public GameSyncStepReq(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	public GameSyncStepReq(int sequence, String gameId, short lastStep)
	{
		this(sequence);

		this.setGameId(gameId);
		this.setLastStep(lastStep);
	}

	private String gameId;
	private short lastStep;

	public short getLastStep()
	{
		return lastStep;
	}

	public void setLastStep(short lastStep)
	{
		this.lastStep = lastStep;
	}

	public String getGameId()
	{
		return gameId;
	}

	public void setGameId(String gameId)
	{
		this.gameId = gameId;
	}

	private final static Logger logger = LoggerFactory.getLogger(GamePlayStepReq.class);

}
