document.observe("dom:loaded", registerObservers); 

function registerObservers(){
	$('thoughtCreator').observe('click', createThought);
}

var Mind = Class.create({
	
	show: function(quickThought){
		this.thoughtDisplayBoard().insert(this.format(quickThought));
	}
	
	,format: function(quickThought){
		var list=this.list();
		list.insert(this.content(quickThought));		
		list.insert(this.when(quickThought));
		return list;
	}
	
	,when: function(quickThought){
		var span = new Element('span');
		span.addClassName("time");
		span.insert(quickThought.when());
		return span;
	}
	
	,content: function(quickThought){
		var span = new Element('span');
		span.addClassName("thought");
		span.insert(quickThought.content());
		return span;
	}
	
	,list: function(){
		return new Element("<li>");
	}

	,thoughtDisplayBoard: function(){
		return $('newThoughts');
	}
});

var QuickThought = Class.create({
	
	initialize: function(quickThoughtJSONString){
		this.quickThoughtJSONString = quickThoughtJSONString;
	}

	,content: function(){
		return this.quickThoughtJSONString.newThought;
	}
	
	,when: function(){
		return this.quickThoughtJSONString.created;
	}
});

var mindDump = new Mind();

function createThought(event){

	var newThought = $('thoughtBox').getValue();
	
	new Ajax.Request('quickThought/think/'+newThought,{
		method: 'get',
		
		onSuccess: function(transport){
			console.debug("response received"+transport.responseJSON.created);
			var quickThought = new QuickThought(transport.responseJSON);
			mindDump.show(quickThought);
		}
	});
}