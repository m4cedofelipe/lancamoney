package com.example.algamoney.api.event;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent rescursoCriadoEvent) {
		
		HttpServletResponse response = rescursoCriadoEvent.getResponse();
		Long codigo = rescursoCriadoEvent.getCodigo();

		adicionaHeaderLocation(response, codigo);
	}

	private void adicionaHeaderLocation(HttpServletResponse response, Long codigo) {
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(codigo).toUri();
		
		response.setHeader("Location", uri.toASCIIString());
	}

}
