package com.pennychecker.toolkit.converter;

public interface Converter<C, CB> {
	C convert(CB val);

	CB convertBack(C val);
}
