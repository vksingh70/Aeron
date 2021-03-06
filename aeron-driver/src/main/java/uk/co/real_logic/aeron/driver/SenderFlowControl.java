/*
 * Copyright 2014 - 2015 Real Logic Ltd.
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
package uk.co.real_logic.aeron.driver;

import java.net.InetSocketAddress;

/**
 * Strategy for applying flow control to the {@link Sender}.
 */
public interface SenderFlowControl
{
    /**
     * Update the sender flow control strategy based on a status message from the receiver.
     *
     * @param termId               currently active for the receiver.
     * @param rebuildTermOffset    within the current active term for the receiver.
     * @param receiverWindowLength supported by the receiver.
     * @param address              of the receiver.
     * @return the new position limit to be employed by the sender.
     */
    long onStatusMessage(int termId, int rebuildTermOffset, int receiverWindowLength, InetSocketAddress address);

    /**
     * Initial limit to which the {@link Sender} position can progress.
     *
     * @param initialTermId      for the term buffers
     * @param termBufferCapacity to use as the length of each term buffer
     * @return limit to which the position can go.
     */
    long initialPositionLimit(int initialTermId, int termBufferCapacity);
}
