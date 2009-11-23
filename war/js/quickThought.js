document.observe("dom:loaded", registerObservers); 

function registerObservers(){
	$('thoughtCreator').observe('click', createThought);
}

function createThought(event){

	var newThought = $('thoughtBox').getValue();
	
	new Ajax.Request('quickThought/think/'+newThought,{
		method: 'get',
		
		onSuccess: function(transport){
			var newThought = transport.responseText;
			$('newThoughts').insert("<li>"+transport.responseJSON.newThought+"</li>");
		}
	});
}
