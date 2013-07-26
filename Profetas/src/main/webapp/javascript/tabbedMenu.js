/**
 * tabbed menu
 * Script é executado quando há uma interação com o elemento coberto pelo script
 * 
 * nota: Requer jquery E utility.js
 */
$(document).ready(function() {
	$('.collapsible').hide();	// Esconde todo o conteúdo das abas.
	$('.collapsible:first').show();	// Exibe o conteúdo da primeira aba.
	$('.tabItem:first').css({'background-color': '#3E779D', 'color': '#FFFFFF'});	// Seta o estilo da aba selecionada
	$('.tabbedMenu ul li a').click(function(){	// Event Handler: aciona quando o hiperlink da aba é clicado.
		var selected = $(this).attr('href');	// Pega o href do hiperlink
		selected = $(selected);					// Pega o objeto (conteúdo da aba) usando o id (nota: href de <a> = id do conteúdo)
		if(selected.is('.collapsible') && !selected.is(':visible')){	// Verifica se o conteúdo já não está sendo exibido.
			$('.collapsible').fadeOut(200);		// Fadeout do conteúdo atual
			$('.tabItem').removeAttr('style');	// Remove o estilo da aba atualmente selecionado.
			$(this).css({'background-color': '#3E779D', 'color': '#FFFFFF'});	// seta estilo da aba que foi selecionado.
			window.setTimeout(function(){	// Delay para dar tempo do fadeOut ser executado completamente.
				selected.fadeIn(200);		// FadeIn do conteúdo que foi selecionado.
				window.setTimeout(adjustSidebar(),10);	// Delay para ajustar o sidebar.
			},250);
			
		}
		return false;	// Retorna falso caso não precise fazer nenhuma alteração (i.e. Aba selecionado = aba atual)
	});
});
