package net.younguard.bighorn.chess.cmd;

import java.io.UnsupportedEncodingException;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.RequestCommand;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GamePlayStepReq
		extends RequestCommand
{
	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;
		TlvObject tSequence = new TlvObject(i++, TlvByteUtil.int2Byte(this.getSequence()));
		TlvObject tGameId = new TlvObject(i++, gameId);
		TlvObject tColor = new TlvObject(i++, TlvByteUtil.short2Byte(color));
		TlvObject tStep = new TlvObject(i++, TlvByteUtil.short2Byte(step));
		TlvObject tX = new TlvObject(i++, TlvByteUtil.short2Byte(x));
		TlvObject tY = new TlvObject(i++, TlvByteUtil.short2Byte(y));

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);
		tlv.add(tGameId);
		tlv.add(tColor);
		tlv.add(tStep);
		tlv.add(tX);
		tlv.add(tY);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");
		return tlv;
	}

	@Override
	public GamePlayStepReq decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		this.setTag(tlv.getTag());

		int childCount = 6;
		TlvParser.decodeChildren(tlv, childCount);
		logger.debug("from tlv:(tag=" + this.getTag() + ", child=" + childCount + ") to command");

		int i = 0;
		TlvObject tSequence = tlv.getChild(i++);
		this.setSequence(TlvByteUtil.byte2Int(tSequence.getValue()));
		logger.debug("sequence: " + this.getSequence());

		TlvObject tGameId = tlv.getChild(i++);
		gameId = new String(tGameId.getValue(), "UTF-8");
		logger.debug("gameId: " + gameId);

		TlvObject tColor = tlv.getChild(i++);
		color = TlvByteUtil.byte2Short(tColor.getValue());
		logger.debug("color: " + color);

		TlvObject tStep = tlv.getChild(i++);
		step = TlvByteUtil.byte2Short(tStep.getValue());
		logger.debug("step: " + step);

		TlvObject tX = tlv.getChild(i++);
		x = TlvByteUtil.byte2Short(tX.getValue());
		logger.debug("x: " + x);

		TlvObject tY = tlv.getChild(i++);
		y = TlvByteUtil.byte2Short(tY.getValue());
		logger.debug("y: " + y);

		return this;
	}

	// //////////////////////////////////////////////////////

	public GamePlayStepReq()
	{
		this.setTag(CommandTag.GAME_PLAY_STEP_REQUEST);
	}

	public GamePlayStepReq(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	public GamePlayStepReq(int sequence, String gameId, short color, short step, short x, short y)
	{
		this(sequence);

		this.setGameId(gameId);
		this.setColor(color);
		this.setStep(step);
		this.setX(x);
		this.setY(y);
	}

	private String gameId;
	private short color;
	private short step;
	private short x;
	private short y;

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

	public short getStep()
	{
		return step;
	}

	public void setStep(short step)
	{
		this.step = step;
	}

	public short getX()
	{
		return x;
	}

	public void setX(short x)
	{
		this.x = x;
	}

	public short getY()
	{
		return y;
	}

	public void setY(short y)
	{
		this.y = y;
	}

	private final static Logger logger = LoggerFactory.getLogger(GamePlayStepReq.class);
}
