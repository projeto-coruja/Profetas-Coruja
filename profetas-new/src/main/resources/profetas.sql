DROP TABLE IF EXISTS personagem_view;
DROP VIEW IF EXISTS personagem_view;
DROP TABLE IF EXISTS fonteobra_view;
DROP VIEW IF EXISTS fonteobra_view;

CREATE OR REPLACE VIEW personagem_view AS 
 SELECT p.id_personagem AS id, ((((((((((((((((((COALESCE(p.p_nome, ''::character varying)::text || ' '::text) || COALESCE(p.p_sobrenome, ''::character varying)::text) || ' '::text) || COALESCE(p.p_apelido, ''::character varying)::text) || ' '::text) || COALESCE(p.p_biografia, ''::character varying::text)) || ' '::text) || COALESCE(p.p_ocupacao, ''::character varying)::text) || ' '::text) || COALESCE(p.p_ocupacao, ''::character varying)::text) || ' '::text) || COALESCE(p.p_formacao, ''::character varying)::text) || ' '::text) || COALESCE(p.p_ref_bibliografica, ''::character varying::text)) || ' '::text) || COALESCE(( SELECT ln.l_name
           FROM local ln
          WHERE p.id_loc_nascimento = ln.id_local), ''::character varying)::text) || ' '::text) || COALESCE(( SELECT lm.l_name
           FROM local lm
          WHERE p.id_loc_morte = lm.id_local), ''::character varying)::text) || ' '::text AS texto
   FROM personagem p
  WHERE p.active = 'Y'::bpchar
  ORDER BY p.id_personagem;
  
CREATE OR REPLACE VIEW fonteobra_view AS 
 SELECT f.id_fontes AS id, ((((((((((((((COALESCE(f.f_titulo, ''::character varying::text) || ' '::text) || COALESCE(f.f_autor, ''::character varying::text::character varying)::text) || ' '::text) || COALESCE(f.f_ref_completa, ''::character varying::text)) || ' '::text) || COALESCE(f.f_ref_circulacao, ''::character varying::text)) || ' '::text) || COALESCE(f.f_comentarios, ''::character varying::text)) || ' '::text) || COALESCE(f.f_copias, ''::character varying::text)) || ' '::text) || COALESCE(f.f_traducoes, ''::character varying::text)) || ' '::text) || COALESCE(array_to_string(ARRAY( SELECT p.pc_palavra_chave
           FROM palavra_chave p
          WHERE p.id_fontes = f.id_fontes), ' '::text), ''::character varying::text)) || ' '::text AS texto
   FROM fontes_obras f
  WHERE f.active = 'Y'::bpchar
  ORDER BY f.id_fontes;
