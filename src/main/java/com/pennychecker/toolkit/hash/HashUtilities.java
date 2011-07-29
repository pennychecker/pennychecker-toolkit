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
package com.pennychecker.toolkit.hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Steffen Kämpke mailto:steffen.kaempke@stud4u.de
 */
public final class HashUtilities {

    /** Creates a new instance of HashUtilities */
    private HashUtilities() {
    }

    /**
     * Produce a hash code of the input
     * @param value value to hash. Null is not permitted
     * @param algorithm Type of the algorithm. Null is not permitted
     * @return Hash code of the input or the given value, if the HashAlgorithm
     * is not supported
     * @exception IllegalArgumentException if value null or algorithm null
     */
    public static String getHashCode(byte[] value, HashAlgorithm algorithm) throws NoSuchAlgorithmException {
        return getHashCode(value, algorithm, false);
    }

    /**
     *
     * @param value Value to hash
     * @param algorithm used HashAlgorithm
     * @param prefix If the prefix set (TRUE) all leading Zeros are available
     * @return A hash Value of the given Object
     * @exception IllegalArgumentException if value null or algorithm null
     */
    public static String getHashCode(byte[] value, HashAlgorithm algorithm, boolean prefix) throws NoSuchAlgorithmException {

        if (value == null) {
            throw new IllegalArgumentException("null 'value' argument.");
        }

        if (algorithm == null) {
            throw new IllegalArgumentException("null 'algorithm'' argument.");
        }

        final StringBuffer hash = new StringBuffer();
        MessageDigest md = null;


        md = MessageDigest.getInstance(algorithm.getKey());


        final byte digest[] = md.digest(value);

        if (prefix) {
            for (int i = 0; i < digest.length; i++) {
                hash.append(Integer.toHexString(
                        (0xFF & digest[i]) | 0x100).substring(1, 3));
            }
        } else {
            for (int i = 0; i < digest.length; i++) {
                hash.append(Integer.toHexString(digest[i] & 0xff));
            }
        }

        return hash.toString();
    }
    
    public static String getMd5FileHash(File file) throws FileNotFoundException, NoSuchAlgorithmException, IOException {
        InputStream is = new FileInputStream(file);
        MessageDigest digest = MessageDigest.getInstance(HashAlgorithm.MD5.getKey());
        byte[] buffer = new byte[8192];
        int read = 0;

        try {
            while ((read = is.read(buffer)) > 0) {
                digest.update(buffer, 0, read);
            }
            byte[] md5sum = digest.digest();
            BigInteger bigInt = new BigInteger(1, md5sum);
            String output = bigInt.toString(16);
            return output;
        } catch (IOException e) {
            throw new RuntimeException("Unable to process file for MD5", e);
        } finally {
            is.close();
        }
    }
}
