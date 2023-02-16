package com.touchbiz.chatgpt.controller;

import com.theokanning.openai.completion.CompletionRequest;
import com.touchbiz.chatgpt.boot.config.OpenAiConfig;
import com.touchbiz.chatgpt.common.dto.Result;
import com.touchbiz.chatgpt.common.proxy.OpenAiEventStreamService;
import com.touchbiz.chatgpt.dto.Chat;
import com.touchbiz.common.utils.tools.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Slf4j
@RequestMapping("/api/chatGpt/chatting")
@RestController
public class ChatController {

    @Autowired
    private OpenAiConfig config;

    @Autowired
    private OpenAiEventStreamService service;

    @PostMapping
    public Mono<Result<?>> prompt(@RequestBody Chat chat){
        log.info("chat:{}, length:{}", chat, chat.getPrompt().length());
        CompletionRequest completionRequest = CompletionRequest.builder()
//                .prompt("Human:" + chat.prompt +"\nAI:")
                .prompt(chat.getPrompt())
                .model(config.getModel())
//                .echo(true)
                .stop(Arrays.asList(" Human:"," AI:"))
                .maxTokens(512)
                .presencePenalty(0d)
                .frequencyPenalty(0d)
                .temperature(0.57D)
                .bestOf(1)
                .topP(1d)
//                .stream(true)
                .build();
        var result = service.createCompletion(completionRequest);
        log.info("result:{}", JsonUtils.toJson(result));
        return Mono.just(Result.ok(result));

    }

}
