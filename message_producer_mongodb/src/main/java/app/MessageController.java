package app;

import org.springframework.beans.factory.annotation.Value;
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
	private MessageCrud messageCrud;
	
	public MessageController(MessageCrud messageCrud) {
		this.messageCrud = messageCrud;
	}
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public Mono<MessageBoundary> createMessage (
			@RequestBody MessageBoundary message) {
		return Mono.just(message)
				.map(boundary -> {
					boundary.setId(null);
					return boundary;
				})
				.map(MessageBoundary::toEntity)
				.flatMap(this.messageCrud::save)
				.map(MessageBoundary::new);
	}
	
	@GetMapping(
			produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
	public Flux<MessageBoundary> getAllMessages (){
		return this.messageCrud
				.findAll()
				.map(MessageBoundary::new);
	}
}
