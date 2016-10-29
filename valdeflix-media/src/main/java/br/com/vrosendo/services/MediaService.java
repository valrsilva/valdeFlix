package br.com.vrosendo.services;

import java.util.List;

import org.springframework.web.context.request.async.DeferredResult;

import br.com.vrosendo.domain.Group;

public interface MediaService {

	void listMediaByUser(Long id, DeferredResult<List<Group>> deferredResult);
}
