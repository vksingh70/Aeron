/*
 * Copyright 2014 Real Logic Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.real_logic.aeron.examples;

import java.util.concurrent.TimeUnit;

/**
 * Configuration used for examples all in one place.
 */
public class ExampleConfiguration
{
    public static final String DESTINATION_PROP = "aeron.example.destination";
    public static final String CHANNEL_ID_PROP = "aeron.example.channelId";
    public static final String FRAME_COUNT_LIMIT_PROP = "aeron.example.frameCountLimit";
    public static final String MESSAGE_LENGTH_PROP = "aeron.example.messageLength";
    public static final String NUMBER_OF_MESSAGES_PROP = "aeron.example.numberOfMessages";
    public static final String LINGER_TIMEOUT_MS_PROP = "aeron.example.lingerTimeout";

    public static final String DESTINATION;
    public static final int CHANNEL_ID;
    public static final int FRAME_COUNT_LIMIT;
    public static final int MESSAGE_LENGTH;
    public static final long NUMBER_OF_MESSAGES;
    public static final long LINGER_TIMEOUT_MS;

    static
    {
        DESTINATION = System.getProperty(DESTINATION_PROP, "udp://localhost:40123");
        CHANNEL_ID = Integer.getInteger(CHANNEL_ID_PROP, 10);
        FRAME_COUNT_LIMIT = Integer.getInteger(FRAME_COUNT_LIMIT_PROP, 10);
        MESSAGE_LENGTH = Integer.getInteger(MESSAGE_LENGTH_PROP, 256);
        NUMBER_OF_MESSAGES = Long.getLong(NUMBER_OF_MESSAGES_PROP, 100);
        LINGER_TIMEOUT_MS = Long.getLong(LINGER_TIMEOUT_MS_PROP, TimeUnit.SECONDS.toMillis(5));
    }
}
