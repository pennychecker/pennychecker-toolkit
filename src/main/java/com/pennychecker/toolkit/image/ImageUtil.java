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
package com.pennychecker.toolkit.image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Steffen Kämpke mailto:steffen.kaempke@stud4u.de
 */
public class ImageUtil {

    /** Creates a new instance of ImageUtil */
    private ImageUtil() {
    }

    /**
     * Convert a ImageIcon object to a BufferedImage object
     * @param icon Image Object
     * @param additionalHeight
     * @exception IllegalArgumentException throw new if image null
     * @return A BufferedImage image
     */
    public static BufferedImage getBufferedImage(ImageIcon icon) {

        if (icon == null) {
            throw new IllegalArgumentException("null 'icon' argument.");
        }

        final int h = icon.getIconHeight();
        final int w = icon.getIconWidth();
        final BufferedImage bi = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2d = bi.createGraphics();

        g2d.drawImage(icon.getImage(), 0, 0, w, h, null);
        g2d.dispose();
        return bi;
    }

    /**
     * Convert a BufferedImage object to a byte[] stream
     * @param icon 
     * @throws IOException
     * @exception IllegalArgumentException throw new if bIResult null
     * @return A byte[] image
     */
    public static byte[] getByteImage(ImageIcon icon) throws IOException {
        if (icon == null) {
            throw new IllegalArgumentException("null 'image' argument.");
        }
        final BufferedImage bi = getBufferedImage(icon);
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bi, "png", bos);
        return bos.toByteArray();
    }
}
