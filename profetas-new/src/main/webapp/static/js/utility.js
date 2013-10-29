$(window).ready(function(){
	var top = $('.sidebar1').offset().top - parseFloat($('.sidebar1').css('marginTop').replace(/auto/,0));
	$(window).scroll(function () {
		var y = $(this).scrollTop();
		if (y >= top) {
			$('.sidebar1').css('position','fixed');
			$('.sidebar1').css('margin','-185px 0 0 0');
		} else {
			$('.sidebar1').css('position','absolute');
			$('.sidebar1').css('margin','0 0 0 0');
		}
	});
});

/**
 * Função executado durante o carregamento da página.
 * Faz a veriicação se o browser é compatível e redimensiona a barra lateral para 
 * ficar de acordo com o tamanho do conteúdo.
 */
$(window).load(function(){
	var	agent = navigator.userAgent,
		count = 0,
		browserWhitelist = ["firefox","chrome", "opera", "safari"];

	for(var i = 0; i < browserWhitelist.length; i++ ){
		count += agent.toLocaleLowerCase().indexOf(browserWhitelist[i], 0);
	}
	
	if(count <= browserWhitelist.length * -1){
		window.location.replace("updateBrowser.jsp");
	}
	adjustToSidebar();
});

function adjustToSidebar(){
	var $sidebarHeight = 0;
	
	$sidebarHeight = $('.sidebar1').height();
	
	if ($(".content")[0]){
		$sidebarHeight -= 46;
		$('.content').css('min-height',$sidebarHeight);
	}
	else{
		$sidebarHeight -= 16;
		$('.text').css('min-height',$sidebarHeight);
	}
	return false;
}
/**
 * Exibe uma janela de confirmação.
 * @param msg - Menssagem a ser exibido.
 * @param url - Url que será passado para o browser caso a pessoa clique em "ok"
 */
function confirmAction(msg, url){
	var r=confirm(msg);
	if (r==true){
		document.location = url;
	}
}

/**
 * Função usado no grão-pará. Deletarei ele caso não seja utilizado.
 * @param obj
 */
function changeToInput(obj) {
	check = obj.options[obj.selectedIndex].value;
	if(check === 'newKeyWord') {
		tb = document.createElement('INPUT');
		tb.type = 'text';
		tb.value = '';
		tb.size = 18;
		tb.name = obj.name;
		tb.id = obj.id;
		obj.parentNode.insertBefore(tb, obj);
		obj.parentNode.removeChild(obj);
	}
	if(check === 'newDocType') {
		tb = document.createElement('INPUT');
		tb.type = 'text';
		tb.value = 'Tipo - Descrição';
		tb.size = 64;
		tb.name = obj.name;
		tb.id = obj.id;
		obj.parentNode.insertBefore(tb, obj);
		obj.parentNode.removeChild(obj);
	}
	else true;
}
