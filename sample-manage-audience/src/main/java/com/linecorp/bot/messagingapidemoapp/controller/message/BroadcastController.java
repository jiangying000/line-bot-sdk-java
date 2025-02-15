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

package com.linecorp.bot.messagingapidemoapp.controller.message;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.Broadcast;
import com.linecorp.bot.model.message.Message;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class BroadcastController {
    private final LineMessagingClient client;
    private final MessageHelper messageHelper;

    @GetMapping("/message/broadcast")
    public String broadcast() {
        return "message/broadcast/form";
    }

    @PostMapping("/message/broadcast")
    public CompletableFuture<RedirectView> postBroadcast(@RequestParam String[] messages,
                                                         @RequestParam Boolean notificationDisabled) {
        List<Message> messageList = messageHelper.buildMessages(messages);
        return client.broadcast(
                new Broadcast(messageList, notificationDisabled))
                     .thenApply(response -> {
                         return new RedirectView("/message/broadcast/" + response.getRequestId());
                     });
    }

    @GetMapping("/message/broadcast/{requestId}")
    public String result(Model model, @PathVariable String requestId) {
        model.addAttribute("requestId", requestId);
        return "message/broadcast/result";
    }
}
