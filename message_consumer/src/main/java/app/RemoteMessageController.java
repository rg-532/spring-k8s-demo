package app;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = { "/message" })
public class RemoteMessageController {
	private WebClient webClient;

	@Value("${remote.message.service.url:http://localhost:8080/message}")
	public void setRemoteUrl(String remoteUrl) {
		this.webClient = WebClient.create(remoteUrl);
	}

	@PostMapping(
			consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<RemoteMessageBoundary> createMessage(@RequestBody RemoteMessageBoundary message) {
		return this.webClient
				.post()
				.bodyValue(message)
				.retrieve()
				.bodyToMono(RemoteMessageBoundary.class)
				.log();
	}

	@GetMapping(produces = { MediaType.TEXT_EVENT_STREAM_VALUE })
	public Flux<RemoteMessageBoundary> getAllMessages() {
		return this.webClient
				.get()
				.retrieve()
				.bodyToFlux(RemoteMessageBoundary.class)
				.log();
	}
}
