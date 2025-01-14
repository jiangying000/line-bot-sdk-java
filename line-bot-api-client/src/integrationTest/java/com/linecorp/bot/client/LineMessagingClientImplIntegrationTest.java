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

package com.linecorp.bot.client;

import static java.util.Collections.singleton;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.linecorp.bot.model.Broadcast;
import com.linecorp.bot.model.Multicast;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.ValidateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotInfoResponse;
import com.linecorp.bot.model.response.GetNumberOfFollowersResponse;
import com.linecorp.bot.model.response.GetNumberOfMessageDeliveriesResponse;
import com.linecorp.bot.model.response.NumberOfMessagesResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Integration test of {@link LineMessagingClient}.
 *
 * <p>To run this test, please put config file resources/integration_test_settings.yml.
 */
@Slf4j
public class LineMessagingClientImplIntegrationTest {
    private LineMessagingClient target;
    private IntegrationTestSettings settings;

    @BeforeEach
    public void setUp() throws IOException {
        settings = IntegrationTestSettingsLoader.load();
        target = LineMessagingClientFactory.create(settings);
    }

    private static void testApiCall(Callable<Object> f) throws Exception {
        final Object response = f.call();
        if (response == null) {
            log.info("null");
        } else {
            log.info(response.toString());
        }
    }

    @Test
    public void broadcast() throws Exception {
        testApiCall(
                () -> target.broadcast(new Broadcast(new TextMessage("Broadcast"), true)).get()
        );
        testApiCall(
                () -> target.broadcast(new Broadcast(new TextMessage("Broadcast"))).get()
        );
    }

    @Test
    public void multicast() throws Exception {
        testApiCall(
                () -> target.multicast(
                        new Multicast(singleton(settings.getUserId()), new TextMessage("Multicast"), true))
                            .get()
        );
        testApiCall(
                () -> target
                        .multicast(new Multicast(singleton(settings.getUserId()), new TextMessage("Multicast")))
                        .get()
        );
    }

    @Test
    public void pushMessage() throws Exception {
        testApiCall(
                () -> target.pushMessage(new PushMessage(settings.getUserId(), new TextMessage("Push"), true))
                            .get()
        );
        testApiCall(
                () -> target.pushMessage(new PushMessage(settings.getUserId(), new TextMessage("Push"))).get()
        );
    }

    @Test
    public void getNumberOfMessageDeliveries() throws Exception {
        final GetNumberOfMessageDeliveriesResponse getNumberOfMessageDeliveriesResponse =
                target.getNumberOfMessageDeliveries("20191231").get();

        log.info(getNumberOfMessageDeliveriesResponse.toString());
    }

    @Test
    public void getNumberOfSentBroadcastMessages() throws Exception {
        final NumberOfMessagesResponse getNumberOfSentBroadcastMessages =
                target.getNumberOfSentBroadcastMessages("20191231").get();

        log.info(getNumberOfSentBroadcastMessages.toString());
    }

    @Test
    public void getNumberOfFollowers() throws Exception {
        final GetNumberOfFollowersResponse getNumberOfFollowersResponse =
                target.getNumberOfFollowers("20191231").get();

        log.info(getNumberOfFollowersResponse.toString());
    }

    @Test
    public void getBotInfo() throws Exception {
        final BotInfoResponse botInfoResponse = target.getBotInfo().get();

        log.info(botInfoResponse.toString());
    }

    @Test
    public void validateReply() throws Exception {
        testApiCall(
                () -> target.validateReply(new ValidateMessage(new TextMessage("Push")))
                        .get()
        );

        String longText = new String(new char[10000]).replace('\0', 'a');
        Assertions.assertThatThrownBy(() -> {
            testApiCall(
                    () -> target.validateReply(new ValidateMessage(new TextMessage(longText))).get()
            );
        }).isInstanceOf(ExecutionException.class);
    }

    @Test
    public void validatePush() throws Exception {
        testApiCall(
                () -> target.validatePush(new ValidateMessage(new TextMessage("Push")))
                        .get()
        );

        String longText = new String(new char[10000]).replace('\0', 'a');
        Assertions.assertThatThrownBy(() -> {
            testApiCall(
                    () -> target.validatePush(new ValidateMessage(new TextMessage(longText))).get()
            );
        }).isInstanceOf(ExecutionException.class);
    }

    @Test
    public void validateMulticast() throws Exception {
        testApiCall(
                () -> target.validateMulticast(new ValidateMessage(new TextMessage("Push")))
                        .get()
        );

        String longText = new String(new char[10000]).replace('\0', 'a');
        Assertions.assertThatThrownBy(() -> {
            testApiCall(
                    () -> target.validateMulticast(new ValidateMessage(new TextMessage(longText))).get()
            );
        }).isInstanceOf(ExecutionException.class);
    }

    @Test
    public void validateNarrowcast() throws Exception {
        testApiCall(
                () -> target.validateNarrowcast(new ValidateMessage(
                        new TextMessage("Push")))
                        .get()
        );

        String longText = new String(new char[10000]).replace('\0', 'a');
        Assertions.assertThatThrownBy(() -> {
            testApiCall(
                    () -> target.validateNarrowcast(new ValidateMessage(new TextMessage(longText)))
                            .get()
            );
        }).isInstanceOf(ExecutionException.class);
    }

    @Test
    public void validateBroadcast() throws Exception {
        testApiCall(
                () -> target.validateBroadcast(new ValidateMessage(new TextMessage("Push")))
                        .get()
        );

        String longText = new String(new char[10000]).replace('\0', 'a');
        Assertions.assertThatThrownBy(() -> {
            testApiCall(
                    () -> target.validateBroadcast(new ValidateMessage(new TextMessage(longText)))
                            .get()
            );
        }).isInstanceOf(ExecutionException.class);
    }
}
