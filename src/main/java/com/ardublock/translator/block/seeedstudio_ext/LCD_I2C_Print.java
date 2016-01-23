package com.ardublock.translator.block.seeedstudio_ext;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class LCD_I2C_Print  extends TranslatorBlock {

	public LCD_I2C_Print (Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	//@Override
		public String toCode() throws SocketNullException, SubroutineNotDeclaredException
		{
			translator.addHeaderFile("Wire.h");
			translator.addHeaderFile("rgb_lcd.h");
			translator.addDefinitionCommand("//libraries at https://github.com/Seeed-Studio/Grove_LCD_RGB_Backlight\nrgb_lcd lcd;");
			translator.addSetupCommand("lcd.begin(16,2);");
			
			String ret =  this.getRequiredTranslatorBlockAtSocket(0, "lcd.print(", " );\n").toCode() ;
			
			return ret ;	
		}
}
