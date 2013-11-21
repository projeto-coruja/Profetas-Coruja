DROP TABLE IF EXISTS personagem_view;
DROP VIEW IF EXISTS personagem_view;
DROP TABLE IF EXISTS fonteobra_view;
DROP VIEW IF EXISTS fonteobra_view;

CREATE OR REPLACE VIEW personagem_view AS SELECT p.id_personagem AS id, ((COALESCE(p.p_nome, ''::character varying)::text || ' '::text) || COALESCE(ln.l_name, ''::character varying)::text) || ' '::text AS texto FROM personagem p, local ln WHERE p.id_loc_nascimento = ln.id_local ORDER BY p.id_personagem;

CREATE OR REPLACE VIEW fonteobra_view AS SELECT f.id_fontes AS id, ((COALESCE(f.f_autor, ''::character varying)::text || ' '::text) || COALESCE(f.f_titulo, ''::character varying::text)) || ' '::text AS texto FROM fontes_obras f ORDER BY f.id_fontes;