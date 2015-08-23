package net.runelite.deob.deobfuscators.arithmetic;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.runelite.deob.Field;
import net.runelite.deob.attributes.code.instruction.types.PushConstantInstruction;

public class Encryption
{
	private Map<Field, Pair> fields = new HashMap<>();
	private Map<PushConstantInstruction, Integer> changes = new HashMap<>();
	
	public Pair getField(Field field)
	{
		if (field.getName().equals("field1170"))
		{
			Pair p = new Pair();
			p.field = field;
			p.getter = -1570098313;
			p.setter = DMath.modInverse(p.getter);
			assert p.setter == 1237096007;
			return p;
		}
		return null;
		//return fields.get(field);
	}
	
	public void change(PushConstantInstruction pci, int value)
	{
		assert !changes.containsKey(pci) || changes.get(pci) == value;
		changes.put(pci, value);
	}
	
	public void doChange()
	{
		for (Entry<PushConstantInstruction, Integer> e : changes.entrySet())
		{
			PushConstantInstruction pci = e.getKey();
			int value = e.getValue();
			
			pci.setConstant(new net.runelite.deob.pool.Integer(value));
		}
	}
}
