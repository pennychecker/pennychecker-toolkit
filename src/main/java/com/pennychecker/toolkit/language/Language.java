/**
 *  Copyright [2011] Steffen Kämpke
 *  mailto: steffen.kaempke@stud4u.de
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
package com.pennychecker.toolkit.language;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import com.pennychecker.toolkit.collection.Gollection;

/**
 * 
 * @author Steffen Kämpke mailto:steffen.kaempke@stud4u.de
 */
public final class Language implements Localizer {

	private List<ResourceBundle> languageBundels = Gollection.newList();
	/**
     *
     */
	public static final long serialVersionUID = 1L;

	private Language(final ResourceBundle languageBundel) {

		this.languageBundels.clear();
		this.languageBundels.add(languageBundel);
	}

	/**
	 * Create a new Threadsave singleton instance. Set a alternative
	 * ResourceBundle with public synchronized void setResourceBundle(final
	 * ResourceBundle languageBundel)
	 * 
	 * @param languageBundel
	 * @return
	 */
	public static Localizer instance(final ResourceBundle languageBundel) {
		return new Language(languageBundel);
	}

	/**
	 * return the locale from the actual resourcebundle
	 * 
	 * @return
	 */
	@Override
	public Locale getLocale() {
		final Locale locale = languageBundels.get(0).getLocale();
		if (null == locale) {
			return Locale.ENGLISH;
		}
		return locale;
	}

	/**
	 * Return a text represent from the given label. Replace the '&' from the
	 * text to an empty String.
	 * 
	 * @param key
	 *            - a allocation label.
	 * @return
	 */
	@Override
	public String getLabel(final String key) {

		if (null == key) {
			throw new IllegalArgumentException("null 'key' argument.");
		}

		ResourceBundle rb = null;
		for (ResourceBundle resourceBundle : languageBundels) {
			if (resourceBundle.containsKey(key)) {
				rb = resourceBundle;
			}
		}

		if (null == rb) {
			assert false : "languageBundel does not contain the key: " + key;
		}

		return rb.containsKey(key) ? rb.getString(key) : "WRONG_KEY->" + key;
	}

	/**
	 * Return a text represent from the given labels and add a empty char
	 * between the texts
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public String getLabel(String... key) {

		String text = "";

		for (int cnt = 0; cnt < key.length; cnt++) {

			assert null != key[cnt] : "null 'params[cnt]' argument.";

			final String regEx = "^[,;.]$";

			System.out.println(regEx);
			if (key[cnt].matches(regEx)) {
				text += key[cnt];
			} else {
				text += getLabel(key[cnt]);
			}

			text += " ";
		}

		return text;
	}

	/**
	 * Replace a placeholder in the text behind the label.
	 * 
	 * @param key
	 *            at the database
	 * @param replaceKey
	 *            user variable
	 * @return value of the user variable
	 * 
	 */
	@Override
	public String getLabel(final String key, final String replaceKey,
			final String replaceValue) {
		final String text = getLabel(key);
		final String replacedText = text.replace(replaceKey, replaceValue);
		return replacedText;
	}

	@Override
	public String getReplacedLabel(String key, final String placeholder,
			String... replaces) {

		if (null == placeholder) {
			throw new IllegalArgumentException("null 'placeholder' argument.");
		}

		if (placeholder.length() < 2) {
			throw new IllegalArgumentException(
					"The placeholder must have minimum 2 chars. Your Placeholder: "
							+ placeholder);
		}

		if (placeholder.contains(" ")) {
			throw new IllegalArgumentException(
					"The placeholder may not contains whitespaces.");
		}

		if (null == replaces) {
			throw new IllegalArgumentException("null 'replaces' argument.");
		}

		final char placeholderStartTag = placeholder.charAt(0);
		final char placeholderEndTag = placeholder
				.charAt(placeholder.length() - 1);

		final String text = getLabel(key);
		final String pattern = "\\" + placeholderStartTag + "[a-zA-Z0-9]*\\"
				+ placeholderEndTag;

		String repText = text;
		int i = 0;
		Pattern p = Pattern.compile(pattern);
		while (p.matcher(repText).find()) {
			repText = repText.replaceFirst(pattern, replaces[i]);
			i++;
		}

		if (i == 0) {
			throw new IllegalArgumentException(
					"The text, represented by the label must contains one placeholder at least. But it doesn't seems so.");

		}

		if (i != replaces.length || p.matcher(repText).find()) {
			throw new IllegalArgumentException(
					"The count of replecements in the text represented by the label is not equal as the count of your replaces string array. The text represented by the label: "
							+ text
							+ ". The count of yor replaces array is "
							+ replaces.length + ".");
		}

		assert null != repText;
		assert !repText.isEmpty();

		return repText;
	}

	public void addResourceBundle(ResourceBundle rb) {
		this.languageBundels.add(rb);
	}
}
