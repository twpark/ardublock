package com.ardublock.translator.block.hid;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class KeyboardReleaseBlock extends TranslatorBlock
{
	public KeyboardReleaseBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
		translator.addHeaderFile("Keyboard.h");

	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		/**
		 * DO NOT add tab in code any more, we'll use arduino to format code, or the code will duplicated. 
		 */
		translator.addSetupCommand("Keyboard.begin();");
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		String test = translatorBlock.toCode();
		test = test.substring(1, test.length() - 1);

		if (test.length() == 1) {
			test = "'" + test + "'";
		}
		
		String ret = "Keyboard.release(" + test + ");\n";

		return ret;
	}
}
