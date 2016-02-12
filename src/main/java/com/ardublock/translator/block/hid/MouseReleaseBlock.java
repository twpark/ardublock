package com.ardublock.translator.block.hid;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class MouseReleaseBlock extends TranslatorBlock
{
	public MouseReleaseBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
		translator.addHeaderFile("Mouse.h");
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		/**
		 * DO NOT add tab in code any more, we'll use arduino to format code, or the code will duplicated. 
		 */
		translator.addSetupCommand("Mouse.begin();");
		TranslatorBlock t0 = this.getRequiredTranslatorBlockAtSocket(0);
		
		String button = "";
		if(t0.toCode().equals("0"))
			button = "MOUSE_LEFT";
		else if (t0.toCode().equals("1"))
			button = "MOUSE_RIGHT";
		else if (t0.toCode().equals("2"))
			button = "MOUSE_MIDDLE";
		
		String ret = "Mouse.release(" + button + ");\n";

		return ret;
	}
}
