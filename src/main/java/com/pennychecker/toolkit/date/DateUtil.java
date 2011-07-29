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
package com.pennychecker.toolkit.date;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Steffen Kämpke mailto:steffen.kaempke@stud4u.de
 */
public class DateUtil {

    public static Date getWeekdayDate(Date actualDate) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(actualDate);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        final Date newDate = calendar.getTime();
        if (newDate.before(actualDate)) {
            calendar.add(Calendar.WEEK_OF_YEAR, Calendar.SATURDAY);
        }
        return calendar.getTime();
    }
}
