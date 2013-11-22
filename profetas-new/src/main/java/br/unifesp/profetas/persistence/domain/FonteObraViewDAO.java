package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.FonteObraView;

public interface FonteObraViewDAO {

	public List<FonteObraView> search(int page, int numRows, String words);
}