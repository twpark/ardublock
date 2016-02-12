package com.ardublock.translator.block.hid;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class MouseMoveBlock extends TranslatorBlock
{
	public MouseMoveBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
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
		TranslatorBlock t1 = this.getRequiredTranslatorBlockAtSocket(1);
		TranslatorBlock t2 = this.getRequiredTranslatorBlockAtSocket(2);
		
		String ret = "Mouse.move(" + t0.toCode() + ", " + t1.toCode() + ", " + t2.toCode() + ");\n";

		return ret;
	}
}
