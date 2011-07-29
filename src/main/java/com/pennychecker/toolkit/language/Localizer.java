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

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Steffen Kämpke mailto:steffen.kaempke@stud4u.de
 */
public interface Localizer {

    /**
     * Return a String represents the label. Remove Mnemonics from label.
     * @param key
     * @return
     */
    public String getLabel(String key);

    /**
     * Return a concated string represented the given label. You can insert
     * a delimeter like the regex pattern ^[,;.\/]$. After a delimeter insert
     * a empty string.
     * @param params
     * @return
     */
    public String getLabel(String... params);

    /**
     * Return a concated string represented the given label. You can insert
     * a delimeter like the regex pattern ^[,;.\/]$. After a delimeter insert
     * a empty string.
     *
     * @param params
     * @return
     */
    /**
     * Return the Locale from the bundle
     * @return
     */
    public Locale getLocale();

    /**
     *
     * @param key
     * @param replaceKey
     * @param replaceValue
     * @return
     */
    public String getLabel(final String key, final String replaceKey, final String replaceValue);

    /**
     *
     * Label which contains placeholder will be replaced.
     *
     * E.g. Your Mother is {age} years old and {height} feet height. Its
     * very important, that the placeholder starts and ends with tags
     * in the whole text that are equal.
     *
     * You can choose a symbol for tagging the placeholder by yourself. E.g. <> or {} or [].
     *
     * Its not allowed e.g. Your Mother is <age> years old and {height} feet heigh.
     * You have two different tags like < > and { }. Avoid this!
     *
     * The text beetween the placeholder tags are unimportant. Only whitesoaces
     * are not allowed.
     * So, you can write e.g.
     *
     * My text with a placeholder {} and a other one like {unimportant}.
     *
     * @pre key is not null @see getLabel(final String key)
     * @pre placeholder is not null
     * @pre placeholder string contains placholder with start and end tag.
     * @pre placeholder length is minimum 2 chars length
     * @pre the placeholder doesn't contains whitespaces
     * @pre replaces is not null
     * @pre replaces length has the same length like the count of placeholders
     * in the text, represented by the label.
     * @pre the represented text by the label contains the same placeholder
     * like the given string placeholder each time.
     * @pre The text, represented by the label must contains one placeholder at least.
     *
     * @post all replacements are replaced.
     * @post no placeholder exist in the text represented by the label.
     *
     * @param key is the label, that represents the text.
     * @param placeholder is a placeholder like {age} or {height} or just {}
     * @param replaces it is an array with the replacements. The length of this array
     * must be the same like the count of placeholders in the represented text from
     * the label.
     *
     * @throws IllegalArgumentException if one precondition, described in the doku, are disabled
     *
     * @return the text with the replacements
     */
    public String getReplacedLabel(final String key, final String placeholder, final String... replaces);
    
    public void addResourceBundle(ResourceBundle rb);
}
