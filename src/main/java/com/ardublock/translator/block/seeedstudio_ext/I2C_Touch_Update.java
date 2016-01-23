package com.ardublock.translator.block.seeedstudio_ext;

import java.util.ResourceBundle;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.VariableNumberBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class I2C_Touch_Update extends TranslatorBlock {

	private static ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");

	public I2C_Touch_Update (Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
		translator.addHeaderFile("Wire.h");
		translator.addHeaderFile("i2c_touch_sensor.h");
	}
	
	//@Override
		public String toCode() throws SocketNullException, SubroutineNotDeclaredException
		{
			translator.addDefinitionCommand("i2ctouchsensor _touchsensor;");

			translator.addSetupCommand("Wire.begin();");
			translator.addSetupCommand("_touchsensor.initialize();");

			String ret = "_touchsensor.getTouchState();\n";
			
			return ret;
		}
}
