package br.com.vrosendo.services;

import java.util.List;

import br.com.vrosendo.domain.Group;

public interface MediaService {

	List<Group> listMediaByUser(Long id);
}
