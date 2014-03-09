DROP TABLE IF EXISTS personagem_view;
DROP VIEW IF EXISTS personagem_view;
DROP TABLE IF EXISTS fonteobra_view;
DROP VIEW IF EXISTS fonteobra_view;

CREATE OR REPLACE FUNCTION notnull(text) RETURNS text
	AS $$ SELECT COALESCE($1, '') $$
	LANGUAGE SQL
    IMMUTABLE;

CREATE VIEW personagem_view AS 
 SELECT p.id_personagem AS id, 
 		notnull(p.p_nome::text) || ' ' || notnull(p.p_sobrenome::text) || ' ' || notnull(p.p_apelido::text)|| ' ' || 
 		notnull(p.p_biografia::text) || ' ' || notnull(p.p_ocupacao::text) || ' ' || notnull(p.p_ocupacao::text) || ' ' ||
 		notnull(p.p_formacao::text) || ' ' || notnull(p.p_ref_bibliografica) || ' ' || 
 		COALESCE((SELECT ln.l_name
           FROM local ln
          WHERE p.id_loc_nascimento = ln.id_local), '')::text || ' ' ||
        COALESCE((SELECT lm.l_name
           FROM local lm
          WHERE p.id_loc_morte = lm.id_local), '')::text || ' ' AS texto
   FROM personagem p
  WHERE p.active = 'Y'::bpchar
  ORDER BY p.id_personagem;
  
CREATE VIEW fonteobra_view AS 
 SELECT f.id_fontes AS id, 
 		notnull(f.f_titulo::text) || ' ' || notnull(f.f_ref_completa::text) || ' ' ||
 		notnull(f.f_ref_circulacao::text) || ' ' || notnull(f.f_comentarios::text) || ' ' || notnull(f.f_copias::text) || ' ' || 
 		notnull(f.f_traducoes::text) || ' ' || notnull(f.f_editor::text) || ' ' || notnull(f.f_localizacao::text) || ' ' || 
 		notnull(array_to_string(
 			ARRAY(
 				SELECT p.pc_palavra_chave
           		 FROM palavra_chave p
         		WHERE p.id_fontes = f.id_fontes), ' ')) || ' ' AS texto
   FROM fontes_obras f
  WHERE f.active = 'Y'::bpchar
  ORDER BY f.id_fontes;
