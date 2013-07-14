/**
 * Função executado durante o carregamento da página.
 * Faz a veriicação se o browser é compatível e redimensiona a barra lateral para 
 * ficar de acordo com o tamanho do conteúdo.
 */
$(window).bind("load",function(){
	var $height = 0,
		$sidebarHeight = 0,
		$element,
		agent = navigator.userAgent,
		count = 0,
		browserWhitelist = ["firefox","chrome", "opera", "safari"];
	if ($(".content")[0])	$element = $('.content');
	else	$element = $('.text');
	
	$height = $element.innerHeight();
	
	$('.bordaBox').each(function(){
		$sidebarHeight += $(this).innerHeight();
	});
	
	for(var i = 0; i < browserWhitelist.length; i++ ){
		count += agent.toLocaleLowerCase().indexOf(browserWhitelist[i], 0);
	}
	
	if(count <= browserWhitelist.length * -1){
		window.location.replace("atualizarBrowser.jsp");
	}
	
	if($height < $sidebarHeight){
		$height = $sidebarHeight;
	}
	$('.sidebar1').css({height:$height});
});
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
