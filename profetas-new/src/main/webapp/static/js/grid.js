C_ASC	= 'asc';
C_DESC	= 'desc';
CSS_SORTABLE	= 'sortable';
CSS_ASC			= 'ascending';
CSS_DESC		= 'descending';	

function searchBy(e, div_id, orderBy, orderType, page){
	if(e.which == 13) {
		gridGoTo(div_id, orderBy, orderType, page);
	}
}
function firstPage(div_id, orderBy, orderType, page){
    if(page > 0 && page != 1){
    	var _goToPage = '1';
    	gridGoTo(div_id, orderBy, orderType, _goToPage);
    }
};
function backPage(div_id, orderBy, orderType, page){
    var _goToPage = parseInt(page) - 1;
    if(page > 0 && page != 1 && _goToPage > 0){
    	gridGoTo(div_id, orderBy, orderType, _goToPage);
    }
};
function goToPage(div_id, orderBy, orderType, page, num_rows, num_pages, event){
    var goto_page = $('#goto_page').val();
    if(page != goto_page > 0 && goto_page > 0 && page <= num_pages){
    	//console.log('goToPage > page: ' + page + ' - num_rows: ' + num_rows + ' num_pages: ' + num_pages);
    }
};
function nextPage(div_id, orderBy, orderType, page, num_pages){		
    if(page > 0 && page < num_pages){
    	var _goToPage = parseInt(page) + 1;
    	gridGoTo(div_id, orderBy, orderType, _goToPage);
    }
};
function lastPage(div_id, orderBy, orderType, page, num_pages){
	if(page > 0 && page < num_pages){
		var _goToPage = num_pages;
		gridGoTo(div_id, orderBy, orderType, _goToPage);
    }
};
function gridAscOrder(section, orderBy, page) {
	loadGrid(section, orderBy, M_ASC, page);
}
function gridDescOrder(section, orderBy, page) {
	loadGrid(section, orderBy, M_DESC, page);
}
function gridGoTo(section, orderBy, orderType, page){
	loadGrid(orderBy, orderType, page);
}

var CorujaGrid = function CorujaGrid() {
	this.even_color	= 'even';
	this.odd_color	= 'odd';
	this.bg_color	= '#F0F0F0';
	this.div_id		= '';
	
	this.grid_names		= ['myForms', 'sharedForms', 'myGroups', 'answersByForm'];
	this.num_titles		= '';
	this.titles			= [];
	this.columns_key	= [];
	this.columns_size	= [];
	this.columns_sort	= [];
	this.page;
	this.orderBy;
	this.orderType;
	this.current_rows;
	this.num_pages;
	this.num_rows;
	this.total_rows;
	this.data;	
	this.total_cols;
	this.grid_type;
	
	this.paintGrid = function paintGrid(div_id, titles, columns_key, columns_size, columns_sort, data, grid_type) {
		var html = '';
		this.div_id			= div_id;
		this.titles			= titles;
		this.num_titles		= this.titles.length;
		this.columns_key	= columns_key;
		this.columns_size	= columns_size;
		this.columns_sort	= columns_sort;
		this.grid_type		= grid_type;
		
		this.data		= data.rows;
		this.orderBy		= data.orderBy;
		this.orderType		= data.orderType;
		this.page			= data.currentPage;
		this.current_rows 	= data.currentNumRows;
		this.num_pages		= data.numPages;
		this.num_rows		= data.numRows;
		this.total_rows		= data.total;
		this.total_cols		= (parseInt(this.num_titles)+2);
		this.search_words	= data.search;
		
		if(this.data != undefined && this.data != null && this.data.length != 0){
			html += this.openGrid();
			html += this.buildHeader();
			html += this.buildBody();
			html += this.buildFooter();
			html += this.closeGrid();			
		}
		else {
			html += this.openGrid();
			html += this.buildHeader();
			html += this.buildBodyNoResults();
			html += this.closeGrid();
		}
		$('#'+div_id).html(html);
	};
	
	this.openGrid = function openGrid(){
		var html = '<table class="grid">';
		return html;
	};
	this.closeGrid = function closeGrid(){
		var html = '</table>';
		return html;
	};
	this.buildBodyNoResults = function buildBodyNoResults(){
		var html = '<tbody>';
		html += '<tr>';			
        	html += '<td colspan="'+(parseInt(this.num_titles)+2)+'">No results</td>';
		html += '</tr>';
	html += '</tbody>';
	return html;
	};
	this.buildHeader = function buildHeader(){
		var html = '';
        html += '<thead><tr>';
        var i,
	        titles_size,
	        columns_size,
	        width = 10;
	    for(i = 0, titles_size = this.titles.length, columns_size = this.columns_size.length; i < titles_size; i++) {
	        if(titles_size == columns_size) {
	            width = this.columns_size[i];
	            
	            var th_class = CSS_SORTABLE;
	            if(this.orderBy == this.columns_sort[i]) {
	            	if(this.orderType == M_ASC) { th_class = CSS_ASC;}
	            	if(this.orderType == M_DESC){ th_class = CSS_DESC;}
	            }
	            
	            html += '<th width="'+width+'%" class="'+th_class+'">'+this.titles[i]+'</th>';
	        }
	    }
	    if(this.grid_type == undefined){
	    	html += '<th width="5%"></th>';
	    	html += '<th width="5%"></th>';
	    } else{
	    	//view item
	    	html += '<th width="5%"></th>';
	    }
	    html += '</tr></thead>';
        return html;
	};
	this.buildBody = function buildBody(){
		var html	= '';
        var bgcolor	= '';
        var even	= this.even_color;
        var odd		= this.odd_color;        
        var i,
        	data_size;
        
    	html += '<tbody>';
    	for (i = 0, data_size = this.data.length; i < data_size; i++){
    	    if(i % 2 == 0) bgcolor = even;
    	    else bgcolor = odd;
    	    var obj = this.data[i];
    	    
    	    var t;
    	    html += '<tr id="'+obj['id']+'" class="'+bgcolor+'">';
    	    for(t = 0; t < this.num_titles; t++) {
    	    	html += '<td>'+obj[this.columns_key[t]]+'</td>';
    	    }
    	    if(this.grid_type == undefined){
    	    	html += '<td align="center"><img onclick="updateForm(\''+obj['id']+'\')" src="static/images/edit.png" /></td>';
    	    	html += '<td align="center"><img onclick="deleteForm(\''+obj['id']+'\')" src="static/images/delete.png" /></td>';
    	    } else {
    	    	//view item
    	    	html += '<td align="center"><img onclick="viewForm(\''+obj['id']+'\')" src="static/images/edit.png" /></td>';
    	    }
    	    
    	    html += '</tr>';
        }
        html += '</tbody>';
        return html;
	};
	this.buildFooter = function buildFooter(){
        var html = '';
        if(this.total_rows > this.num_rows){
            html += '<tfoot>';
            html += '<tr>';
            html += '<td colspan="'+this.total_cols+'">';
                        
            html += '<div id="grid_foot">';
            html += '<div id="grid_foot_left"><span class="bold">Páginas:</span> ' + this.num_pages+'</div>';

            html += '<div id="grid_foot_first" onclick="firstPage('+"'"+this.div_id+"'"+', '+"'"+this.orderBy+"'"+', '+"'"+this.orderType+"'"+', '+this.page+')"><< </div>';
			html += '<div id="grid_foot_previous" onclick="backPage('+"'"+this.div_id+"'"+', '+"'"+this.orderBy+"'"+', '+"'"+this.orderType+"'"+', '+this.page+')">< </div>';
			html += '<div id="grid_foot_goto">'+this.page+'</div>';
			html += '<div id="grid_foot_next" onclick="nextPage('+"'"+this.div_id+"'"+', '+"'"+this.orderBy+"'"+', '+"'"+this.orderType+"'"+', '+this.page+', '+this.num_pages+')"> > </div>';
			html += '<div id="grid_foot_last"onclick="lastPage('+"'"+this.div_id+"'"+', '+"'"+this.orderBy+"'"+', '+"'"+this.orderType+"'"+', '+this.page+', '+this.num_pages+')"> >> </div>';

            html += '<div id="grid_foot_right"> <span class="bold">Mostrando</span> ' + this.current_rows + ' <span class="bold">de</span> ' + this.total_rows+'</div>';
            html += '</div>';
            html += '</td>';
            html += '</tr>';
            html += '</tfoot>';
        }
        return html;
    };
	
};