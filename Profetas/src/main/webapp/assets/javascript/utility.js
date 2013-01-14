/**====================================================================================*/
/**	utility.js
 * Funções de utilidade em javascript/jquery.
 * Para a utilização de algumas funções contido neste arquivo, será necessário carregar o arquivo jquery.js no html.
 */
/**====================================================================================*/

/**
 * Seleciona uma imagem de forma aleatória para ser exibido no background.
 * caso o browser não tenha suporte a javascript, o browser irá utilizar a imagem 01.jpg.
 * 
 */ 
function getImage(){
	var imageSet = ["01.jpg","02.jpg","03.jpg","04.jpg","05.jpg","06.jpg","07.jpg","08.jpg"];
	$('body').css({'background-image': 'url(/Profetas/assets/backgrounds/' + imageSet[Math.floor(Math.random() * imageSet.length)] + ')'});
	$('body').css({'background-attachment':'fixed'})
}