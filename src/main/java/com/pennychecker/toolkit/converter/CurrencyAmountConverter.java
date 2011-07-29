/**
 *  Copyright [2011] Steffen Kämpke
 *  mailto: steffen.kaempke@pennychecker.de
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pennychecker.toolkit.converter;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 *
 * @author Steffen Kämpke mailto:steffen.kaempke@stud4u.de
 */
public final class CurrencyAmountConverter implements Converter<Long, String>{

	private final String currencySymbol;
	private final Locale locale;
	
	
	/**
	 * Der Konverter konvertiert eine Währung zu long und zurück.
	 * 
	 * @param currencySymbol z.B. € oder EUR. Wird das symbol gefunden, wird es aus dem String vor der Konvertierung entfernt.
	 * @param locale wird für das Rpckparsen von Long -> benötigt.
	 * 
	 * @exception AssertionError wenn currencySymbol leer oder null ist.
	 * @exception AssertionError wenn locale null ist.
	 */
	public CurrencyAmountConverter(String currencySymbol, Locale locale) {
		super();
		assert null != currencySymbol;
		assert !currencySymbol.isEmpty();
		assert null != locale;
		
		this.currencySymbol = currencySymbol;
		this.locale = locale;
	}

	/**
	 * von String zu Long
	 * @exception IllegalStateException wenn beim rückparsen ein fehler aufgetreten ist.
	 * @exception AssertionError wenn currency null oder leer ist 
	 */
	public Long convert(String val) {
        assert null != val;
        assert !val.isEmpty();        

        if (val.contains(currencySymbol)) {
        	val = val.replace(currencySymbol, "").trim();
        }


        final Double currency;
		try {
			currency = DecimalFormat.getInstance(locale).parse(val).doubleValue();
		} catch (ParseException e) {
			throw new IllegalStateException("Could not parse string: '"+val+"' to long value.",e);
		}
		
        return Math.round(currency * 10000) / 100;
    }

	/**
	 * von Long zu String
	 * @exception AssertionError wenn currency null ist
	 */
    public String convertBack(Long val) {
    	assert val != null;
        double currency = val / 100.;
        return DecimalFormat.getInstance(locale).format(currency);
    }
}
