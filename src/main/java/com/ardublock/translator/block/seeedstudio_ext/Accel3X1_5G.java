package com.ardublock.translator.block.seeedstudio_ext;

import java.util.ResourceBundle;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.VariableNumberBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Accel3X1_5G extends TranslatorBlock {

	private static ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");

	public Accel3X1_5G (Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
		translator.addHeaderFile("MMA7660.h");
		translator.addHeaderFile("Wire.h");
	}
	
	//@Override
		public String toCode() throws SocketNullException, SubroutineNotDeclaredException
		{
			translator.addDefinitionCommand("MMA7660 _acc;");
			translator.addDefinitionCommand("int8_t _acc_x, _acc_y, _acc_z;");
			translator.addSetupCommand("_acc.init(); \n ");

			String ret = "_acc.getXYZ(&_acc_x, &_acc_y, &_acc_z);\n";
			
			TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
			if (!(tb instanceof VariableNumberBlock))
				throw new BlockException(blockId, uiMessageBundle.getString("ardublock.error_msg.number_var_slot"));
			ret = ret + tb.toCode() + " = _acc_x;\n";

			tb = this.getRequiredTranslatorBlockAtSocket(1);
			if (!(tb instanceof VariableNumberBlock))
				throw new BlockException(blockId, uiMessageBundle.getString("ardublock.error_msg.number_var_slot"));
			ret = ret + tb.toCode() + " = _acc_y;\n";

			tb = this.getRequiredTranslatorBlockAtSocket(2);
			if (!(tb instanceof VariableNumberBlock))
				throw new BlockException(blockId, uiMessageBundle.getString("ardublock.error_msg.number_var_slot"));
			ret = ret + tb.toCode() + " = _acc_z;\n";

			return ret;
		}
}
