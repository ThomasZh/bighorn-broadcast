package net.younguard.bighorn.chess.cmd;

import java.io.UnsupportedEncodingException;
import java.util.List;

import net.younguard.bighorn.CommandTag;
import net.younguard.bighorn.comm.ResponseCommand;
import net.younguard.bighorn.comm.tlv.TlvByteUtil;
import net.younguard.bighorn.comm.tlv.TlvObject;
import net.younguard.bighorn.comm.tlv.TlvParser;
import net.younguard.bighorn.domain.GameStep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GameLoadManualResp
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

		Gson gson = new Gson();
		String jsonSteps = gson.toJson(steps);
		TlvObject tSteps = new TlvObject(i++, jsonSteps);

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.add(tSequence);
		tlv.add(tRespState);
		tlv.add(tGameId);
		tlv.add(tSteps);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");
		
		return tlv;
	}

	@Override
	public GameLoadManualResp decode(TlvObject tlv)
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

		TlvObject tRespState = tlv.getChild(i++);
		this.setRespState(TlvByteUtil.byte2Short(tRespState.getValue()));
		logger.debug("respState: " + this.getRespState());

		TlvObject tGameId = tlv.getChild(i++);
		gameId = new String(tGameId.getValue(), "UTF-8");
		logger.debug("gameId: " + gameId);

		TlvObject tJsonSteps = tlv.getChild(i++);
		String jsonSteps = new String(tJsonSteps.getValue(), "UTF-8");
		logger.debug("jsonSteps: " + jsonSteps);
		Gson gson = new Gson();
		steps = gson.fromJson(jsonSteps, new TypeToken<List<GameStep>>()
		{
		}.getType());

		return this;
	}

	// //////////////////////////////////////////////////////

	public GameLoadManualResp()
	{
		this.setTag(CommandTag.GAME_LOAD_MANUAL_RESPONSE);
	}

	public GameLoadManualResp(int sequence)
	{
		this();

		this.setSequence(sequence);
	}

	public GameLoadManualResp(int sequence, short state)
	{
		this(sequence);

		this.setRespState(state);
	}

	public GameLoadManualResp(int sequence, short state, String gameId, List<GameStep> steps)
	{
		this(sequence);

		this.setRespState(state);
		this.setGameId(gameId);
		this.setSteps(steps);
	}

	private String gameId;
	private List<GameStep> steps;

	public String getGameId()
	{
		return gameId;
	}

	public void setGameId(String gameId)
	{
		this.gameId = gameId;
	}

	public List<GameStep> getSteps()
	{
		return steps;
	}

	public void setSteps(List<GameStep> steps)
	{
		this.steps = steps;
	}

	private final static Logger logger = LoggerFactory.getLogger(GameLoadManualResp.class);

}
