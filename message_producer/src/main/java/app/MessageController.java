package app;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = {"/message"})
public class MessageController {
	private ArrayList<MessageBoundary> messages;
	
	public MessageController() {
		this.messages = new ArrayList<>();
	}
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
		public Mono<MessageBoundary> createMessage (
				@RequestBody MessageBoundary message) {
			this.messages.add(message);
			return Mono.just(this.messages.get(this.messages.size() - 1));
		}
	
	@GetMapping(
			produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
		public Flux<MessageBoundary> getAllMessages (){
			return Flux.fromIterable(this.messages);
		}
}
