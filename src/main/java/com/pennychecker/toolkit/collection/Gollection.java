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
package com.pennychecker.toolkit.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Steffen Kämpke mailto:steffen.kaempke@stud4u.de
 */
public class Gollection {

    /**
     * Generate a new HashMap
     * @param <K> Object Key
     * @param <V> Object Value
     * @return HashMap<K, V>()
     */
    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap<K, V>();
    }

   /**
    * 
    * @param <S>
    * @return
    */
    public static <S> Set<S> newHashSet() {
        return new HashSet<S>();
    }

    /**
     * Generate a new TreeMap
     * @param <K> Object Key
     * @param <V> Object Value
     * @return TreeMap<K, V>()
     */
    public static <K, V> Map<K, V> newTreeMap() {
        return new TreeMap<K, V>();
    }

    /**
     *
     * @param <T> a Object
     * @return ArrayListObject
     */
    public static <T> List<T> newList() {
        return new ArrayList<T>();
    }
}
