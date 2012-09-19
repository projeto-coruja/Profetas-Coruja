$(document).ready(function() {
	$('ul#usermenu li ul').hide();
	$('ul#usermenu li').hover(
		function() {
			$('ul#usermenu li').not($('ul', this)).stop();
			$('ul', this).slideDown('fast');
		},
		function() {
			$('ul', this).slideUp('fast');
		}
	);
});