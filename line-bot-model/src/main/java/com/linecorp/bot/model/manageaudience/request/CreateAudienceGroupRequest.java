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

package com.linecorp.bot.model.manageaudience.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import com.linecorp.bot.model.manageaudience.request.CreateAudienceGroupRequest.CreateAudienceGroupRequestBuilder;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = CreateAudienceGroupRequestBuilder.class)
public class CreateAudienceGroupRequest {
    /**
     * The audience's name. Audience names must be unique. Note that comparisons are case-insensitive, so the
     * names AUDIENCE and audience are considered identical.
     *
     * <p>Max character limit: 120
     */
    String description;

    /**
     * If this is false (default), recipients are specified by user IDs. If true, recipients must be specified
     * by IFAs.
     */
    Boolean isIfaAudience;

    /**
     * The description to register for the job (in jobs[].description).
     */
    String uploadDescription;

    /**
     * An array of up to 10,000 user IDs or IFAs.
     */
    @JsonInclude(Include.NON_NULL)
    List<Audience> audiences;

    @JsonPOJOBuilder(withPrefix = "")
    public static class CreateAudienceGroupRequestBuilder {
        // Filled by lombok
    }
}
