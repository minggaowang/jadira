/*
 *  Copyright 2010, 2011 Christopher Pheby
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.jadira.usertype.dateandtime.threeten.columnmapper;

import java.sql.Time;
import java.time.Instant;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import org.jadira.usertype.spi.shared.AbstractTimeColumnMapper;
import org.jadira.usertype.spi.shared.JavaZoneConfigured;

public class TimeColumnOffsetTimeMapper extends AbstractTimeColumnMapper<OffsetTime> implements JavaZoneConfigured<ZoneOffset> {

    private static final long serialVersionUID = 6734385103313158326L;

    public static final DateTimeFormatter LOCAL_TIME_FORMATTER = new DateTimeFormatterBuilder().appendPattern("HH:mm:ss").toFormatter();

    private ZoneOffset javaZone = null;

	public TimeColumnOffsetTimeMapper() {
	}

	public TimeColumnOffsetTimeMapper(ZoneOffset javaZone) {
		this.javaZone = javaZone;
	}
    
    @Override
    public OffsetTime fromNonNullString(String s) {
        return OffsetTime.parse(s);
    }

    @Override
    public OffsetTime fromNonNullValue(Time value) {
        
        return Instant.ofEpochMilli(value.getTime()).atOffset(javaZone).toOffsetTime();
    }

    @Override
    public String toNonNullString(OffsetTime value) {
        return value.toString();
    }

    @Override
    public Time toNonNullValue(OffsetTime value) {

        return Time.valueOf(LOCAL_TIME_FORMATTER.format(value));
    }
        
    @Override
    public void setJavaZone(ZoneOffset javaZone) {
        this.javaZone = javaZone;
    }
        
	@Override
	public ZoneOffset parseJavaZone(String zoneString) {
		return ZoneOffset.of(zoneString);
	}
}
