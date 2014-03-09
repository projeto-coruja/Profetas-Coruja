function getGraph(){
	$.ajax({
        dataType:'json',
        type:'get',
        cache:false,
        url:URL_SECTION+'/view.html',
        data:{},
        success: function(data, textStatus, jqXHR){
        	if(data == null)
        		return;
        	paintGraph(data);
        }
    });
}

var render = function(r, n) {
    var label = r.text(0, 30, n.label).attr({opacity:0});
    var set = r.set().push(
        r.rect(-30, -13, 62, 86)
          .attr({"fill": "#fa8",
                "stroke-width": 2
                , r : 9 }))
      .push(label);
      set.hover(
        function mouseIn() {
          label.animate({opacity:1,"fill-opacity":1}, 500);
        },
        function mouseOut() {
          label.animate({opacity:0},300);
        }
      );

      tooltip = r.set()
      .push(
        r.rect(0, 0, 90, 30).attr({"fill": "#fec", "stroke-width": 1, r : 9 })
      ).push(
        r.text(25, 15, "overlay").attr({"fill": "#000000"})
      );
      for(i in set.items) {
        set.items[i].tooltip(tooltip);
      };
      return set;
  };

function paintGraph(data){
	var width = 700;
	var height = 400;
    var g = new Graph();

    addNodes(data.nodes);
    addEdges(data.edges);
    var layouter = new Graph.Layout.Spring(g);
    layouter.layout();
    var renderer = new Graph.Renderer.Raphael('canvas', g, width, height);
    renderer.draw();
    
    function addNodes(nodes){
    	for(var n in nodes){
    		g.addNode(nodes[n]['id'], { label: nodes[n]['label'] });
        }
    }
       
    function addEdges(edges) {
    	for(var e in edges){
    		g.addEdge(edges[e]['source']['id'], edges[e]['target']['id'], {directed: true});
    	}
    }
}