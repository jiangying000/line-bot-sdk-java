/*
 * Copyright 2023 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.linecorp.bot.model.event;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import com.linecorp.bot.model.event.source.Source;

import lombok.Builder;
import lombok.Value;

/**
 * Event for when a user finishes viewing a video at least once with the specified trackingId sent by the LINE
 * Official Account.
 */
@JsonTypeName("videoPlayComplete")
@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = VideoPlayCompleteEvent.VideoPlayCompleteEventBuilder.class)
public class VideoPlayCompleteEvent implements Event, ReplyEvent {
    @JsonPOJOBuilder(withPrefix = "")
    public static class VideoPlayCompleteEventBuilder {
        // Providing builder instead of public constructor. Class body is filled by lombok.
    }

    /**
     * Token for replying to this event.
     */
    String replyToken;

    /**
     * JSON object which contains the source of the event.
     */
    Source source;

    /**
     * Time of the event.
     */
    Instant timestamp;

    /**
     * Channel state.
     * <dl>
     * <dt>active</dt>
     * <dd>The channel is active. You can send a reply message or push message from the bot server that received
     * this webhook event.</dd>
     * <dt>standby (under development)</dt>
     * <dd>The channel is waiting. The bot server that received this webhook event shouldn't send any messages.
     * </dd>
     * </dl>
     */
    EventMode mode;

    /**
     * Webhook Event ID. An ID that uniquely identifies a webhook event. This is a string in ULID format.
     */
    String webhookEventId;

    /**
     * Get delivery context.
     */
    DeliveryContext deliveryContext;

    VideoPlayComplete videoPlayComplete;

    @Value
    @Builder(toBuilder = true)
    @JsonDeserialize(builder = VideoPlayCompleteEvent.VideoPlayComplete.VideoPlayCompleteBuilder.class)
    public static class VideoPlayComplete {
        @JsonPOJOBuilder(withPrefix = "")
        public static class VideoPlayCompleteBuilder {
            // Providing builder instead of public constructor. Class body is filled by lombok.
        }

        /**
         * ID used to identify a video. Returns the same value as the trackingId assigned to the video message.
         */
        String trackingId;
    }

}
