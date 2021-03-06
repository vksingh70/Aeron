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

import static uk.co.real_logic.aeron.common.concurrent.logbuffer.LogBufferDescriptor.computePosition;

public class UnicastSenderFlowControl implements SenderFlowControl
{
    private long positionLimit = 0;
    private int positionBitsToShift;
    private int initialTermId;

    public long onStatusMessage(
        final int termId, final int rebuildTermOffset, final int receiverWindowLength, final InetSocketAddress address)
    {
        final long position = computePosition(termId, rebuildTermOffset, positionBitsToShift, initialTermId);
        final long newPositionLimit = position + receiverWindowLength;
        positionLimit = Math.max(positionLimit, newPositionLimit);

        return positionLimit;
    }

    public long initialPositionLimit(final int initialTermId, final int termBufferCapacity)
    {
        this.initialTermId = initialTermId;
        positionBitsToShift = Long.numberOfTrailingZeros(termBufferCapacity);
        positionLimit = computePosition(initialTermId, 0, positionBitsToShift, initialTermId);

        return positionLimit;
    }
}
