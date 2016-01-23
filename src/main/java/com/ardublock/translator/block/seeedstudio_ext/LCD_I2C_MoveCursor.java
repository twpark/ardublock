package com.ardublock.translator.block.seeedstudio_ext;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class LCD_I2C_MoveCursor  extends TranslatorBlock {

	public LCD_I2C_MoveCursor (Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	//@Override
		public String toCode() throws SocketNullException, SubroutineNotDeclaredException
		{
			String line_number, column_number;
			TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
			line_number = translatorBlock.toCode();
			TranslatorBlock translatorBlock2 = this.getRequiredTranslatorBlockAtSocket(1);
			column_number = translatorBlock2.toCode();
			
			translator.addHeaderFile("Wire.h");
			translator.addHeaderFile("rgb_lcd.h");
			translator.addDefinitionCommand("//libraries at https://github.com/Seeed-Studio/Grove_LCD_RGB_Backlight\nrgb_lcd lcd;");
			translator.addSetupCommand("lcd.begin(16,2);");
			
			String ret =  "lcd.setCursor("+ column_number + "," + line_number + ");\n";
			
			return ret ;	
		}
}
